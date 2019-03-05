package test.beforeTest;

import org.openqa.selenium.WebDriver;
import test.Log.LogMessage;
import test.utility.PropertyConfig;
import test.utility.ReadExcel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestData {


    private static TestData testData = new TestData() ;
    private WebDriver driver;
    private TestData() {
        keepData();
    }

    public static TestData getInstance() throws Exception {
        if(null == testData)
            throw new Exception("test data is not initialized properly");
        return testData;
    }

    public LogMessage runPrequisites(String testCaseId) {
        try {
            //System.out.println(SpaceData);
            if(PropertyData.containsKey(testCaseId)){
                List<Map> propertyRecords=PropertyData.get(testCaseId);
                PropertyCreate propertyCreate = new PropertyCreate(driver);
                for(Map propertyRecord: propertyRecords){
                    LogMessage logMessage = propertyCreate.createProperty(propertyRecord);
                    if(!logMessage.isPassed())
                        return  new LogMessage(false,"Prerequisite not completed");
                }
            }
            if(LeaseData.containsKey(testCaseId))
            {
                List<Map> leaseRecords=LeaseData.get(testCaseId);
                LeaseCreate leaseCreate = new LeaseCreate(driver);
                LogMessage logMessage = leaseCreate.createMultipleLeases(leaseRecords);
                if(!logMessage.isPassed())
                    return  new LogMessage(false,"Prerequisite not completed");
            }
            if(SpaceData.containsKey(testCaseId))
            {
                List<Map> spaceRecords = SpaceData.get(testCaseId);
                LeaseCreate leaseCreate = new LeaseCreate(driver);
                Map<String, List<Map>> spacesList = new HashMap<>();
                for (Map spaceRecord : spaceRecords) {
                    if (!spacesList.containsKey(spaceRecord.get("LeaseName"))) {
                        List<Map> record = new ArrayList<Map>();
                        record.add(spaceRecord);
                        spacesList.put((String) spaceRecord.get("LeaseName"), record);
                    } else {
                        spacesList.get(spaceRecord.get("LeaseName")).add(spaceRecord);
                    }

                }
                for (String key : spacesList.keySet()) {
                    LogMessage logMessage = leaseCreate.createMultipleSpaces(spacesList.get(key));
                    if(!logMessage.isPassed())
                        return  new LogMessage(false,"Prerequisite not completed");
                }
            }
            if(RecurData.containsKey(testCaseId))
            {
                List<Map> recurRecords = RecurData.get(testCaseId);
                LeaseCreate leaseCreate = new LeaseCreate(driver);
                Map<String, List<Map>> recurList = new HashMap<>();

                for (Map spaceRecord : recurRecords) {
                    if (!recurList.containsKey(spaceRecord.get("LeaseName"))) {
                        List<Map> record = new ArrayList<Map>();
                        record.add(spaceRecord);
                        recurList.put((String) spaceRecord.get("LeaseName"), record);
                    } else {
                        recurList.get(spaceRecord.get("LeaseName")).add(spaceRecord);
                    }

                }
                //System.out.println(spacesList);
                for (String key : recurList.keySet()) {
                    LogMessage logMessage = leaseCreate.addMultipleRecurringPayments(recurList.get(key));
                    if(!logMessage.isPassed())
                        return  new LogMessage(false,"Prerequisite not completed");
                }
            }

            return  new LogMessage(true,"Prerequisite Completed");
        } catch (Exception ex) {
            ex.printStackTrace();
            return  new LogMessage(false,"Exception in prerequisite");
        }
    }
    public void setDriver(WebDriver driver){this.driver=driver; }
    public WebDriver getDriver(){return driver;}

    private Map<String,List> PropertyData = new HashMap<String,List>();
    private Map<String,List> LeaseData = new HashMap<String,List>();
    private Map<String,List> SpaceData = new HashMap<String,List>();
    private Map<String,List> RecurData = new HashMap<String,List>();
    private String[] sheets = new String[]{"Property","Lease","Space" , "RecurringPayment"};

   private  void fetchAndStoreTestData(String sheetName) {

        ClassLoader classLoader = TestData.class.getClassLoader();
        ReadExcel readExcel = new ReadExcel(classLoader.getResource("dataCreate/DataCreate.xlsx").getPath());
        List<Map> data = readExcel.read(sheetName);
        Map<String,List> dataList = getDataObject(sheetName);
        System.out.println(sheetName);
        if(null == dataList)
            return;
        for(Map item: data) {
           if (null == item.get(PropertyConfig.EXECUTION_FLAG) || item.get(PropertyConfig.EXECUTION_FLAG).toString().isEmpty() || !item.get(PropertyConfig.EXECUTION_FLAG).toString().toLowerCase().equals("yes"))
                continue;
           if(dataList.containsKey(item.get(PropertyConfig.TC_ID))) {
                dataList.get(item.get(PropertyConfig.TC_ID)).add(item);
           } else {
                List<Map> record = new ArrayList<Map>() ;
                record.add(item);
                dataList.put((String) item.get(PropertyConfig.TC_ID),record);
            }
        }
    }


   private void keepData() {
       try {
           for(String sheet: sheets) {
               fetchAndStoreTestData(sheet);
           }
       } catch (Exception ex) {
           testData = null;
           ex.printStackTrace();
       }
   }
   public Map<String,List> getDataBySheet(String sheetName){
       return getDataObject(sheetName);

   }

    private  Map<String,List> getDataObject(String sheetName) {
       switch(sheetName) {
           case "Property": return PropertyData ;
           case "Lease": return LeaseData ;
           case "Space": return SpaceData ;
           case "RecurringPayment": return  RecurData ;
           default: return null ;
       }
    }
}
