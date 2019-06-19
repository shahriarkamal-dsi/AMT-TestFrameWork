package test.keywordScripts;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import sun.rmi.runtime.Log;
import test.Log.LogMessage;
import test.objectLocator.WebObjectSearch;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class UIScheduleTable extends UtilKeywordScript {
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


    public List getAllSpecificColumnValues(String objectLocatorData, String clName) {

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

    public LogMessage isDulicayPresent(String objectLocatorData,String clName ) {
        try {
            List list =  getAllSpecificColumnValues(objectLocatorData,clName)  ;
             Long distinctValue =  list.stream().distinct().count() ;
            return list.size() == distinctValue.intValue() ? new LogMessage(true, "no duplicacy value present in " + clName) :  new LogMessage(false, "duplicacy value present in " + clName) ;
        } catch (Exception ex) {
            return new LogMessage(false, "exception occurred " + ex.getMessage()) ;
        }
    }


    public LogMessage sumCheck(String objectLocatorData,String clName ) {
        try {
            List<String> list =  getAllSpecificColumnValues(objectLocatorData,clName)  ;
          List<String> values =    list.stream().map(value -> convertStringToNumber( (String) value)).collect(Collectors.toList()) ;
          String lstValue = values.remove(values.size()-1) ;
          double totalSum  ;
          double value = 0;
          for( String val : values) {
              if(val.isEmpty())
                  continue;
              double d = Double.valueOf(val).doubleValue() ;
              value += d ;
          }
          if(lstValue.isEmpty())
              return new LogMessage(false, "total value is empty") ;
          else {
              double d = Double.valueOf(lstValue).doubleValue() ;
              return value == d ? new LogMessage(true, "total sum is right"): new LogMessage(false, "total sum is not right") ;
          }
        } catch (Exception ex) {
            return new LogMessage(false, "exception occurred " + ex.getMessage()) ;
        }
    }


    public LogMessage  checkClValue(String objectLocatorData,String testData ) {
        try {

            String[] splits = testData.split(",") ;
            List<String> list =  getAllSpecificColumnValues(objectLocatorData,splits[0])  ;
            String value = list.get(Integer.valueOf(splits[1]).intValue());
          return  value.equals(splits[2]) ? new LogMessage(true, "values are equals") :  new LogMessage(false, "values are not equals");

        } catch (Exception ex) {
            return new LogMessage(false, "exception occurred " + ex.getMessage()) ;
        }
    }

    public void test(String object) {
        try {
            System.out.println( getAllSpecificColumnValues(object,"ROU – Base Asset Rollover Amortization"));
            System.out.println("............................................") ;
            System.out.println( getSingleRowfromScheduleTable(object,"# of Periods","2"));
            System.out.println("............................................") ;
            System.out.println( getScheduleTablecolumnNames(object));
            System.out.println("............................................") ;
        } catch ( Exception ex) {
            ex.printStackTrace();
        }
    }
}
