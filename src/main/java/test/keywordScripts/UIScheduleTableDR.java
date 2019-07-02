package test.keywordScripts;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import test.Log.LogMessage;
import test.coreModule.TestPlan;
import test.objectLocator.WebObjectSearch;

import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;

public class UIScheduleTableDR extends UtilKeywordScript {
    private WebDriver webDriver;
    ScheduleDataDR scheduleDataDR = new ScheduleDataDR();
    public UIScheduleTableDR(WebDriver driver) {
        this.webDriver = driver ;
    }
    public UIScheduleTableDR(){

    }


    public List<Map> getAllvaluefromScheduleTable(String objectLocatorData ) {
        System.out.println("getAllvaluefromScheduleTable Called ");
        System.out.println("objectLocatorData "+objectLocatorData);

        try {
            List<Map> schedultable = new ArrayList<Map>() ;
            WebElement rootElement = WebObjectSearch.getWebElement(webDriver, objectLocatorData);
            List<WebElement> tables = rootElement.findElements(By.tagName("table"));
            boolean specialTable = false;
            WebElement head = tables.get(0);
            WebElement body = tables.get(1);
            List<String> columnNames = getScheduleTablecolumnNames(objectLocatorData);

            List<WebElement> rows  = body.findElements(By.tagName("tr"));
            for(WebElement row : rows) {
                Map rowdata = new HashMap<String,String>();
                List<WebElement> bodyCells = row.findElements(By.tagName("td"));
                int index = 0;
                for(WebElement bodyCell : bodyCells) {
                    if(!isItDesplayed(bodyCell))
                        continue;
                    String value =  bodyCell.getAttribute("textContent") ;
                    rowdata.put(columnNames.get(index),value);
                    index++;

                }
                schedultable.add(rowdata);
            }
                 return schedultable ;
            //schedultable.stream().forEach(r-> System.out.println(r));

        } catch (Exception ex) {
            ex.printStackTrace();
            return new ArrayList<Map>() ;
        }

    }


    public List getAllSpecificColumnValues(String objectLocatorData, String clName) {

        System.out.println("getAllSpecificColumnValues ");
        System.out.println("objectLocatorData "+objectLocatorData);
        System.out.println("column Name :"+clName);

        try {
                //List<Map> scheduleTable =  getAllvaluefromScheduleTable(objectLocatorData) ;
                List<Map> scheduleTable = ScheduleDataDR.getInstance().getScheduleTable();
                System.out.println("scheduleTable Value :"+scheduleTable);
                return  scheduleTable.stream().map(row -> row.get(clName)).collect(Collectors.toList()) ;

        } catch ( Exception ex) {
            ex.printStackTrace();
            return new ArrayList<>();
        }
    }


    public Map getSingleRowfromScheduleTable(String objectLocatorData, String clName, String clValue) {
        System.out.println("objectLocatorData "+objectLocatorData);

        try {
            List<Map> schedultable = new ArrayList<Map>() ;
            WebElement rootElement = WebObjectSearch.getWebElement(webDriver, objectLocatorData);
            List<WebElement> tables = rootElement.findElements(By.tagName("table"));
            boolean getSpecificRow = false;
            WebElement head = tables.get(0);
            WebElement body = tables.get(1);
            List<String> columnNames = getScheduleTablecolumnNames(objectLocatorData);

            List<WebElement> rows  = body.findElements(By.tagName("tr"));
            for(WebElement row : rows) {
                Map rowdata = new HashMap<String,String>();
                List<WebElement> bodyCells = row.findElements(By.tagName("td"));
                int index = 0;
                for(WebElement bodyCell : bodyCells) {
                    if(!isItDesplayed(bodyCell))
                        continue;
                    String value =  bodyCell.getAttribute("textContent") ;
                    rowdata.put(columnNames.get(index),value);
                    if(value.equals(clValue) && columnNames.get(index).contains(clName) )
                        getSpecificRow = true ;
                    index++;

                }

                if(getSpecificRow)
                    return rowdata ;
            }

            return new HashMap<>() ;

        } catch ( Exception ex) {
            ex.printStackTrace();
            return new HashMap<>() ;

        }
    }


    public List getScheduleTablecolumnNames(String objectLocatorData) {

        try {
            class Coulmn {
                public int index;
                public  String columnName ;

                public Coulmn(int indexVal, String coulmnVal) {
                    this.index = indexVal ;
                    this.columnName = coulmnVal;
                }
            }
            List<Map> tableData = new ArrayList<Map>();
            WebElement rootElement = WebObjectSearch.getWebElement(webDriver, objectLocatorData);
            List<WebElement> tables = rootElement.findElements(By.tagName("table"));
            boolean specialTable = false;
            WebElement head = tables.get(0);
            WebElement body = tables.get(1);
            List<WebElement> rows = head.findElements(By.tagName("tr"));
            List<Coulmn> columnNames = new ArrayList<Coulmn>(100);
            for (WebElement row : rows) {
                List<WebElement> headCells = row.findElements(By.tagName("th"));
                for (WebElement headCell : headCells) {
                    if (null != headCell.getAttribute("data-index")) {
                        if(!isItDesplayed(headCell))
                            continue;
                        int index = Integer.valueOf(headCell.getAttribute("data-index"));
                        String value = headCell.getAttribute("data-title");
                        if(value.contains("\uFFFD")) {
                            value = value.replace("\uFFFD", "-");
                        }
                        columnNames.add(new Coulmn(index,value));
                    }
                }
            }

            columnNames.sort((Coulmn c1,Coulmn c2) -> c1.index-c2.index);
            return columnNames.stream().map(c ->c.columnName).collect(Collectors.toList()) ;

        } catch ( Exception ex) {
            return new ArrayList<>();
        }
    }


    public boolean isItDesplayed(WebElement webElement) {
        //  return true ;

        if(null == webElement.getAttribute("style")) return true;
        String styleValue = webElement.getAttribute("style") ;
        if(styleValue.contains("display: none")) return false ;
        return true ;


    }

    public LogMessage columnNameValidation (String objectLocatorData, String testData){

        System.out.println("Method Name: columnNameValidation ");
        System.out.println("objectLocatorData "+objectLocatorData);

        List<String> splitTestDatas= Arrays.asList(testData.split(","));
        List<String> ScheduleTableColumnNames = getScheduleTablecolumnNames(objectLocatorData);

        System.out.println("splitTestDatas :"+splitTestDatas);
        System.out.println("ScheduleTableColumnNames :"+ScheduleTableColumnNames);
        System.out.println("Split Test Data :"+splitTestDatas);

        boolean Contains = ScheduleTableColumnNames.containsAll(splitTestDatas);
        System.out.println("Boolean Contains Value :"+ Contains);

        if(Contains){
            return new LogMessage(true,"Column Names have been Validated");
        }else{
            return new LogMessage(false,"Column Names Have not Validated");
        }
    }
    

    public LogMessage isDulicayPresent(String objectLocatorData,String clName ) {
        System.out.println("Method Name: isDulicayPresent");
        System.out.println("Column Name :"+clName);

        try {
            List list =  getAllSpecificColumnValues(objectLocatorData,clName)  ;
            System.out.println("List Value :"+list);

            if(list.isEmpty())
                return new LogMessage(true,"there is no value for this column: " +clName) ;
            list.remove(0) ;
            Long distinctValue = list.stream().filter(val -> !val.toString().isEmpty()).distinct().count() ;

            System.out.println(" distinctValue : "+distinctValue.intValue());
            System.out.println(" list.size() :" + list.size());

            return (list.size()-1) == distinctValue.intValue() ? new LogMessage(true, "no duplicacy value present in " + clName) :  new LogMessage(false, "duplicacy value present in " + clName) ;
        } catch (Exception ex) {
            ex.printStackTrace();
            return new LogMessage(false, "exception occurred " + ex.getMessage()) ;
        }
    }


    public LogMessage sumCheck(String objectLocatorData,String clName ) {

        try {
            List<String> list =  getAllSpecificColumnValues(objectLocatorData,clName)  ;

            System.out.println("Column Name: "+ clName);
            System.out.println("LIST Items: "+ list);

            if(list.isEmpty())
                return  new LogMessage(false,"no clm values does found for this column: " + clName) ;
          List<String> values =    list.stream().map(value -> convertStringToNumber( (String) value)).collect(Collectors.toList()) ;
          String lstValue = values.remove(values.size()-1) ;
          double totalSum  ;
          double value = 0;
          for( String val : values) {
              if(val.isEmpty())
                  continue;
              double d = Double.valueOf(val).doubleValue() ;
              value += d ;
              System.out.println("New Amount for Sum UP :"+ d);
              System.out.println("Summed UP Amount:"+ value);
              double roundOff = Math.round(value * 100.0) / 100.0;
              System.out.println("Round Value:"+ roundOff);

          }

          if(lstValue.isEmpty())
              return new LogMessage(false, "total value is empty") ;
          else {
              double d = Double.valueOf(lstValue).doubleValue() ;
              System.out.println("Last Amount:"+ d);
              double roundOff = Math.round(value * 100.0) / 100.0;
              return roundOff == d ? new LogMessage(true, "total sum is right"): new LogMessage(false, "total sum is not right") ;
          }
        } catch (Exception ex) {
            ex.printStackTrace();
            return new LogMessage(false, "exception occurred " + ex.getMessage()) ;
        }
    }


    public LogMessage  checkClValue(String objectLocatorData,String testData ) {
        try {

            String[] splits = testData.split(",") ;
            System.out.println("Column Name:"+splits[0]);
            System.out.println("Column Index:"+splits[1]);
            System.out.println("Value to be Asserted:"+splits[2]);

            List<String> list =  getAllSpecificColumnValues(objectLocatorData,splits[0])  ;
            String value = list.get(Integer.valueOf(splits[1]).intValue());

          return  value.equals(splits[2]) ? new LogMessage(true, "values are equals") :  new LogMessage(false, "values are not equals");

        } catch (Exception ex) {
            ex.printStackTrace();
            return new LogMessage(false, "exception occurred " + ex.getMessage()) ;
        }
    }
    /*This Method is for Boundary Value Checking*/
    public LogMessage  checkNotEqual(String objectLocatorData,String testData ) {
        try {

            System.out.println("Method Name: checkNotEqual ");
            System.out.println("objectLocatorData "+objectLocatorData);

            String[] splits = testData.split(",") ;
            List<String> list =  getAllSpecificColumnValues(objectLocatorData,splits[0])  ;
            String value = list.get(Integer.valueOf(splits[1]).intValue());
            return  value.equals(splits[2]) ? new LogMessage(false, "values are equals") :  new LogMessage(true, "values are not equals");

        } catch (Exception ex) {
            ex.printStackTrace();
            return new LogMessage(false, "exception occurred " + ex.getMessage()) ;
        }
    }

    public LogMessage  checkPeriodPayment(String objectLocatorData,String testData) {
        try {
            String[] splits = testData.split(",") ;
            if(Double.parseDouble((String) convertStringToNumber(scheduleDataDR.getInstance().getPaymentMap(splits[0]))) == (Double.parseDouble(splits[1]))){
                return new LogMessage(true, "Values are equal " ) ;
            }else{
                return new LogMessage(false, "Values are not equal " ) ;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return new LogMessage(false, "exception occurred " + ex.getMessage()) ;
        }
    }

    public LogMessage  checkPeriodReceivable(String objectLocatorData,String testData) {
        System.out.println("checkPeriodReceivable from UIScheduleTableDR");
        try {
            String[] splits = testData.split(",") ;
            if(Double.parseDouble((String) convertStringToNumber(scheduleDataDR.getInstance().getPaymentMap(splits[0]))) == (Double.parseDouble(splits[1]))){
                return new LogMessage(true, "Values are equal " ) ;
            }else{
                return new LogMessage(false, "Values are not equal " ) ;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return new LogMessage(false, "exception occurred " + ex.getMessage()) ;
        }
    }

    public LogMessage createPaymentMap(String objectLocatorData,String testData){
        System.out.println("createPaymentMap Method Called"+ Instant.now());
        String[] splits = testData.split(",") ;

        List<String> list3 =  getAllSpecificColumnValues(objectLocatorData,splits[1])  ;
        List<String> list4 =  getAllSpecificColumnValues(objectLocatorData,splits[0])  ;
        /*
        * This list 3 and list4 have not been used ,just for partial fix of a Selenium Issue
        * */
        List<String> list2 =  getAllSpecificColumnValues(objectLocatorData,splits[1])  ;
        List<String> list1 =  getAllSpecificColumnValues(objectLocatorData,splits[0])  ;

        System.out.println("LIST 01"+list1+ Instant.now());
        System.out.println("LIST 02"+list2+ Instant.now());
        int bound = Math.min(list1.size(), list2.size());
        System.out.println("bound :"+bound+ Instant.now());

        try{
            for (int i = 0; i < bound; i++) {
                Integer integer = i;
                if(list1.get(integer)!= null && list2.get(integer) != null){
                    System.out.println("Putting Data in Map...Time:"+ Instant.now());
                    System.out.println("Key  :"+list1.get(integer) +"    Value:  "+list2.get(integer));
                    scheduleDataDR.getInstance().setPaymentMap(list1.get(integer),list2.get(integer));
                    System.out.println("Key"+ list1.get(integer)+"  Value:"+ scheduleDataDR.getInstance().getPaymentMap(list1.get(integer)));

                }else{
                    System.out.println("Not Putting Data in Map...Time:"+ Instant.now());
                    System.out.println("Key  :"+list1.get(integer) +"    Value:  "+list2.get(integer));
                }
            }
            return new LogMessage(true, "Payment Map Created Successfully ") ;
        }catch(Exception ex){
            ex.printStackTrace();
            return new LogMessage(false, "exception occurred " + ex.getMessage()) ;
        }

    }


    public LogMessage createScheduleTable(String objectLocatorData){
        System.out.println("createScheduleTableList Method Called"+ Instant.now());
        List scheduleList = getAllvaluefromScheduleTable(objectLocatorData);

        try{
            for (int i = 0; i < scheduleList.size(); i++) {
                Integer integer = i;
                if(scheduleList.get(integer)!= null){
                    System.out.println("Putting Data in Schedule...Time:"+ Instant.now());
                    System.out.println("List Index  :"+integer+"    Value:  "+scheduleList.get(integer));
                    scheduleDataDR.getInstance().setScheduleList(integer, scheduleList.get(integer));
                    System.out.println("List Index "+integer +"  Value:"+ (scheduleDataDR.getInstance().getScheduleList(integer)));

                }else{
                    System.out.println("Not Putting Data in Schedule...Time:"+ Instant.now());
                }
            }
            System.out.println("Created Schedule Table: "+ScheduleDataDR.getInstance().getScheduleTable());
            return new LogMessage(true, "Schedule List Created Successfully ") ;
        }catch(Exception ex){
            ex.printStackTrace();
            return new LogMessage(false, "exception occurred " + ex.getMessage()) ;
        }
    }

    public LogMessage clearScheduleTable(){
        System.out.println("Method : clearScheduleTable"+ Instant.now());

        try{
            ScheduleDataDR.getInstance().clearScheduleList();
            System.out.println("Schedule Table After Clear: "+ScheduleDataDR.getInstance().getScheduleTable());
            return new LogMessage(true, "Schedule List Cleared Successfully ") ;
        }catch(Exception ex){
            ex.printStackTrace();
            return new LogMessage(false, "exception occurred " + ex.getMessage()) ;
        }
    }


    public LogMessage clearPaymentMap(){
        System.out.println("Method :clearPaymentMap "+ Instant.now());

        try{
            ScheduleDataDR.getInstance().clearPaymentMap();
            System.out.println("Payment Map After Clear: "+ScheduleDataDR.getInstance().getFullPaymentMap());
            return new LogMessage(true, "Payment Map Cleared Successfully ") ;
        }catch(Exception ex){
            ex.printStackTrace();
            return new LogMessage(false, "exception occurred " + ex.getMessage()) ;
        }
    }

    public LogMessage test(String object) {
        try {
            createScheduleTable(object);
            System.out.println("Method Name : Test");
            System.out.println("objectLocatorData "+object);
            System.out.println( getAllSpecificColumnValues(object,"Period"));
            System.out.println("............................................") ;
            // System.out.println( getSingleRowfromScheduleTable(object,"# of Periods","2"));
            // System.out.println("............................................") ;
            // System.out.println( getScheduleTablecolumnNames(object));
            // System.out.println("............................................") ;

            if(ScheduleDataDR.getInstance().getScheduleTable().isEmpty())
            { System.out.println("clearScheduleTable Not Called ");
            }else{ clearScheduleTable(); }

            if(ScheduleDataDR.getInstance().getFullPaymentMap().isEmpty()){
                System.out.println("clearPaymentMap Not Called ");
            }else{ clearPaymentMap(); }
            return new LogMessage(true, "Tested Successfully ") ;

        } catch ( Exception ex) {
            ex.printStackTrace();
            return new LogMessage(false, "exception occurred " + ex.getMessage()) ;

        }
    }
}