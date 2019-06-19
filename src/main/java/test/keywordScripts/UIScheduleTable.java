package test.keywordScripts;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import test.Log.LogMessage;
import test.beforeTest.TestData;
import test.objectLocator.WebObjectSearch;
import test.utility.PropertyConfig;

import java.lang.reflect.Array;
import java.util.*;
import java.util.stream.Collectors;
import java.util.Collection;

public class UIScheduleTable {
    private WebDriver webDriver;

    public UIScheduleTable(WebDriver driver) {
        this.webDriver = driver ;
    }

    public UIScheduleTable(){

    }


    public List<Map> getAllvaluefromScheduleTable(String objectLocatorData ) {
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


    public List getAllSpecificColumnValues(String objectLocatorData,String clName) {

        try {
            List<Map> scheduleTable =  getAllvaluefromScheduleTable(objectLocatorData) ;
            return  scheduleTable.stream().map(row -> row.get(clName)).collect(Collectors.toList()) ;

        } catch ( Exception ex) {
            ex.printStackTrace();
            return new ArrayList<>();
        }
    }


    public Map getSingleRowfromScheduleTable(String objectLocatorData, String clName, String clValue) {

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

    /*
    * Checking Column Data Presence in getScheduleTablecolumnNames arraylist
    *
    * */
    public LogMessage columnNameValidation (String objectLocatorData, String testData){

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

    public LogMessage duplicacyCheck (String scheduleTableLocator, String columnName){

        System.out.println("checkDuplicacyInSingleColumnValues:");

        List columnValue = getAllSpecificColumnValues(scheduleTableLocator,columnName);

        System.out.println("Checking Duplicacy In : "+ columnName);
        System.out.println("columnValue" + columnValue);

        Set<String> set = new HashSet<String>(columnValue);

        if(set.size() < columnValue.size()){
            /* There are duplicates */
            System.out.println("Duplicate Data Exists in "+ columnName);
            return new LogMessage(false,"Duplicate Data Exists in "+ columnName);


        }else{
            System.out.println("No Duplicate Data Exists in "+ columnName);
            return new LogMessage(true,"No Duplicate in Columns "+ columnName);
        }
    }
    public LogMessage checkDuplicacyInColumnValues (String objectLocatorData, String testData){

        System.out.println("checkDuplicacyInColumnValues:");

        boolean duplicacy = false;
        List<String> columnNames= Arrays.asList(testData.split(","));
        List<String> columnValue;
        ListIterator<String> columnNameIterator = columnNames.listIterator();
        //List<String> ScheduleTableColumnNames = getScheduleTablecolumnNames(objectLocatorData);

        System.out.println("checkDuplicacyInColumnValues    splitTestDatas :"+columnNames);
        System.out.println("checkDuplicacyInColumnValues columnNameIterator :"+columnNameIterator);

        while (columnNameIterator.hasNext()) {
            String columnName = columnNameIterator.next();
            System.out.println("Checking Duplicacy In : "+ columnName);
            columnValue = getAllSpecificColumnValues(objectLocatorData,columnName);
            System.out.println("columnValue" + columnValue);
            Set<String> set = new HashSet<String>(columnValue);
            if(set.size() < columnValue.size()){
                /* There are duplicates */
                duplicacy = true;
                System.out.println("There is duplicates in :"+columnName);

            }else{
                duplicacy = false;
            }
        }

        if(duplicacy){
            return new LogMessage(false,"Duplicate Exists");
        }else{
            return new LogMessage(true,"No Duplicate in Columns");
        }
    }


    public void test(String object) {
        try {
            System.out.println("ObjectLocator :" + object);
            System.out.println("getAllSpecificColumnValues Method Data:");
            System.out.println( getAllSpecificColumnValues(object,"JE Posting Period"));
            System.out.println("............................................") ;
            System.out.println("getSingleRowfromScheduleTable Method Data:");
            System.out.println( getSingleRowfromScheduleTable(object,"# of Periods","2"));
            System.out.println("............................................") ;
            System.out.println("getScheduleTablecolumnNames Method Data:");
            System.out.println( getScheduleTablecolumnNames(object));
            System.out.println("............................................") ;
        } catch ( Exception ex) {
            ex.printStackTrace();
        }
    }

}