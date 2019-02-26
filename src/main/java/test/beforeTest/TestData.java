package test.beforeTest;

import test.Log.LogMessage;
import test.utility.PropertyConfig;
import test.utility.ReadExcel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestData {


    private TestData testData = new TestData() ;

    private TestData() {
        keepData();
    }

    public TestData getInstance() throws Exception {
        if(null == testData)
            throw new Exception("test data is not initialized properly");
        return testData;
    }

    public LogMessage runPrequisites(String testCaseId) {
        try {
                return  new LogMessage(true,"ok");
        } catch (Exception ex) {
            ex.printStackTrace();
            return  new LogMessage(false,"not ok");
        }
    }

    private Map<String,List> PropertyData = new HashMap<String,List>();
    private Map<String,List> LeaseData = new HashMap<String,List>();
    private Map<String,List> SpaceData = new HashMap<String,List>();
    private Map<String,List> RecurData = new HashMap<String,List>();
    private String[] sheets = new String[]{"Property","Lease","Space" , "Recur"};

   private  void fetchAndStoreTestData(String sheetName) {

        ClassLoader classLoader = TestData.class.getClassLoader();
        ReadExcel readExcel = new ReadExcel(classLoader.getResource("dataCreate/DataCreate.xlsx").getPath());
        List<Map> data = readExcel.read(sheetName);
        Map<String,List> dataList = getDataObject(sheetName);
        if(null == dataList)
            return;
        for(Map item: data) {
            if(dataList.containsKey(item.get(PropertyConfig.TC_ID))) {
                dataList.get(dataList.get(PropertyConfig.TC_ID)).add(item);
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

    private  Map<String,List> getDataObject(String sheetName) {
       switch(sheetName) {
           case "Property": return PropertyData ;
           case "Lease": return LeaseData ;
           case "Space": return SpaceData ;
           case "Recur": return  RecurData ;
           default: return null ;
       }
    }
}
