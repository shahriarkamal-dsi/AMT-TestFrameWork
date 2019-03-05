package test.keywordScripts;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import test.Log.LogMessage;
import test.objectLocator.WebObjectSearch;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class UITable extends  UtilKeywordScript{
    private WebDriver webDriver;

    public UITable(WebDriver driver) {
        this.webDriver = driver ;
    }

    public UITable(){

    }

    /*
    a. there is multiple type table type data, like:  in single table contails header and data, multiple table contains header and data, etc
    try to differentiate from here and call other method or code here accordingly.
    b. from calling method from  excel sheet, please use this method to get all table data, then code what need to be done,
    thats how any type table can be worked properly from excel sheet.
     */
    public List<Map> getAllValuesfromTable(String objectLocatorData) {
        try {
            webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS) ;
            List<Map> tableData = new ArrayList<Map>();
            WebElement rootElement = WebObjectSearch.getWebElement(webDriver,objectLocatorData);
            List<WebElement> tables  = rootElement.findElements(By.tagName("table"));
            if(tables.size() <2) {
                return getAllValuesfromSingleTable(objectLocatorData);
            }
            WebElement head = tables.get(0) ;
            WebElement body = tables.get(1) ;
            List<WebElement> rows = head.findElements(By.tagName("tr"));
            List<WebElement> headCells = rows.get(0).findElements(By.tagName("th"));
            /*
            for(WebElement row : rows) {
                List<WebElement> headCells = row.findElements(By.tagName("th"));
                for(WebElement headcell : headCells) {
                }
            }*/

             rows = body.findElements(By.tagName("tr"));
            for(WebElement row : rows) {
                Map rowdata = new HashMap<String,WebElement>();
                List<WebElement> bodyCells = row.findElements(By.tagName("td"));
                int index = 0;
                for(WebElement bodyCell : bodyCells) {
                    String key =  String.valueOf(index) + "," + headCells.get(index).getText();
                    rowdata.put(key,bodyCell);
                    index++;
                    //System.out.println(bodyCell.getText());
                    //System.out.println(headcell.getAttribute("data-title"));
                }
                tableData.add(rowdata);
            }
            return tableData;
        } catch(Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }



    /*
  a. there is multiple type table type data, like:  in single table contails header and data, multiple table contains header and data, etc
  try to differentiate from here and call other method or code here accordingly.
  b. from calling method from  excel sheet, please use this method to get all table data, then code what need to be done,
  thats how any type table can be worked properly from excel sheet.
  c. either send columnName and columnValue or rowIndex
  d. return perticular columnName-columnVale cell and whole row
  e. row index start with 0
   */
    public Map getSingleRowfromTable(String objectLocatorData,String columnName,String columnValue, Integer rowIndex ) {
        try {
            List<Map> tableData = new ArrayList<Map>();
            WebElement rootElement = WebObjectSearch.getWebElement(webDriver,objectLocatorData);
            List<WebElement> tables  = rootElement.findElements(By.tagName("table"));
            boolean specialTable = false;
            if(tables.size() <2) {
                return getSingleRowfromSingleTable(objectLocatorData,columnName,columnValue,rowIndex);
            }
            WebElement head = tables.get(0) ;
            WebElement body = tables.get(1) ;
            List<WebElement> rows = head.findElements(By.tagName("tr"));
            if(rows.size()>1)
                specialTable = true;
            List<WebElement> headCells = rows.get(0).findElements(By.tagName("th"));
            /*
            for(WebElement row : rows) {
                List<WebElement> headCells = row.findElements(By.tagName("th"));
                for(WebElement headcell : headCells) {
                }
            }*/
            rows = body.findElements(By.tagName("tr"));
            int countRow = 0 ;
            boolean getMatchrow = false ;
            for(WebElement row : rows) {
                Map rowdata = new HashMap<String,WebElement>();
                List<WebElement> bodyCells = row.findElements(By.tagName("td"));
                int index = 0;
                for(WebElement bodyCell : bodyCells) {
                    //System.out.println("Column Name"+headCells.get(index).getText() + "Value"+bodyCell.getText());
                    if(null  != columnName && null != columnValue ) {
                        if(columnName.equals(headCells.get(index).getText())) {

                            if(columnValue.equals(bodyCell.getText())) {
                                getMatchrow = true;
                            }
                        }
                    }
                    String key = "";
                    if(specialTable) {
                      //  String clmnName = bodyCell.getAttribute("title") == "" ? "na" : bodyCell.getAttribute("title") ;
                        key = String.valueOf(index) + "," + bodyCell.getAttribute("title") ;
                    }
                     else
                         key =  String.valueOf(index) + "," + headCells.get(index).getText();
                    rowdata.put(key,bodyCell);
                    index++;

                    //System.out.println(bodyCell.getText());
                    //System.out.println(headcell.getAttribute("data-title"));
                }
                if(null  != rowIndex &&  countRow == rowIndex.intValue())
                    return rowdata ;
                else if(getMatchrow) {
                    return rowdata;
                }
                countRow++;
            }
            return new HashMap();
        } catch(Exception ex) {
            ex.printStackTrace();
            return new HashMap();
        }
    }



    private Map getSingleRowfromSingleTable(String objectLocatorData,String columnName,String columnValue, Integer rowIndex ) {
        try {
            List<Map> tableData = new ArrayList<Map>();
            WebElement rootElement = WebObjectSearch.getWebElement(webDriver,objectLocatorData);
            List<WebElement> tables  = rootElement.findElements(By.tagName("table"));
            WebElement head = tables.get(0) ;
            List<WebElement>  headCells = head.findElement(By.tagName("thead")).findElement(By.tagName("tr")).findElements(By.tagName("th"));
            /*
            for(WebElement row : rows) {
                List<WebElement> headCells = row.findElements(By.tagName("th"));
                for(WebElement headcell : headCells) {
                }
            }*/

            List<WebElement> rows = head.findElement(By.tagName("tbody")).findElements(By.tagName("tr"));
            int countRow = 0 ;
            boolean getMatchrow = false ;
            for(WebElement row : rows) {
                Map rowdata = new HashMap<String,WebElement>();
                List<WebElement> bodyCells = row.findElements(By.tagName("td"));
                int index = 0;
                for(WebElement bodyCell : bodyCells) {
                    if(null  != columnName && null != columnValue ) {
                        if(columnName.equals(headCells.get(index).getText())) {
                            if(columnValue.equals(bodyCell.getText())) {
                                getMatchrow = true;
                            }
                        }
                    }
                    String key =  String.valueOf(index) + "," + headCells.get(index).getText();
                    rowdata.put(key,bodyCell);
                    index++;

                    //System.out.println(bodyCell.getText());
                    //System.out.println(headcell.getAttribute("data-title"));
                }
                if(null  != rowIndex &&  countRow == rowIndex.intValue())
                    return rowdata ;
                else if(getMatchrow) {
                    return rowdata;
                }
                countRow++;
            }
            return new HashMap();
        } catch(Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }


    private List<Map> getAllValuesfromSingleTable(String objectLocatorData ) {
        try {
        webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS) ;
        List<Map> tableData = new ArrayList<Map>();
        WebElement rootElement = WebObjectSearch.getWebElement(webDriver,objectLocatorData);
        List<WebElement> tables  = rootElement.findElements(By.tagName("table"));
        WebElement head = tables.get(0) ;
        List<WebElement>  headCells = head.findElement(By.tagName("thead")).findElement(By.tagName("tr")).findElements(By.tagName("th"));
            /*
            for(WebElement row : rows) {
                List<WebElement> headCells = row.findElements(By.tagName("th"));
                for(WebElement headcell : headCells) {
                }
            }*/

            List<WebElement> rows = head.findElement(By.tagName("tbody")).findElements(By.tagName("tr"));
        for(WebElement row : rows) {
            Map rowdata = new HashMap<String,WebElement>();
            List<WebElement> bodyCells = row.findElements(By.tagName("td"));
            int index = 0;
            for(WebElement bodyCell : bodyCells) {
                String key =  String.valueOf(index) + "," + headCells.get(index).getText();
                rowdata.put(key,bodyCell);
                index++;
            }
            tableData.add(rowdata);
        }
        return tableData;
    } catch(Exception ex) {
        ex.printStackTrace();
        return null;
    }
    }

    public LogMessage DoubleClickCellInTable(String objectLocatorData, String testData) {
        try {
            String columnName = "" ;
            String columnValue = "" ;
            if(!validateTestData(testData,2))
                return  new LogMessage(false, "test data invalid");
            String[] data = testData.split(",");
            columnName = data[0] ;
            columnValue = data[1] ;
            Map<String, WebElement>  row = getSingleRowfromTable(objectLocatorData,columnName,columnValue,null);
            if(null == row || row.isEmpty())
                return new LogMessage(false, "no table data");
                for (String key : row.keySet()) {
                    if(key.split(",").length<2)
                        continue;
                     String clName = key.split(",")[1];
                     if(columnName.equals(clName)){
                         WebElement element = row.get(key) ;
                         String text = element.getText();
                         if(columnValue.equals(element.getText())) {
                             UIBase uibase = new UIBase(webDriver);

                             //element.click();
                             uibase.ClickDbClickRClick(element,"DBLCLICK");
                             return new LogMessage(true, "element is clicked");
                         }
                     }
                }
            return new LogMessage(true, "proper cell is not present.");
        } catch(Exception ex) {
            ex.printStackTrace();
            return new LogMessage(false,"exception occured: " + ex.getMessage());
        }
    }
    public LogMessage ClickCellInTable(String objectLocatorData, String testData) {
        try {
            String columnName = "" ;
            String columnValue = "" ;
            if(!validateTestData(testData,2))
                return  new LogMessage(false, "test data invalid");
            String[] data = testData.split(",");
            columnName = data[0] ;
            columnValue = data[1] ;
            Map<String, WebElement>  row = getSingleRowfromTable(objectLocatorData,columnName,columnValue,null);
            if(null == row || row.isEmpty())
                return new LogMessage(false, "no table data");
            for (String key : row.keySet()) {
                if(key.split(",").length<2)
                    continue;
                String clName = key.split(",")[1];
                if(columnName.equals(clName)){
                    WebElement element = row.get(key) ;
                    String text = element.getText();
                    if(columnValue.equals(element.getText())) {
                        UIBase uibase = new UIBase(webDriver);

                        element.click();
                        //uibase.ClickDbClickRClick(element,"DBLCLICK");
                        return new LogMessage(true, "element is clicked");
                    }
                }
            }
            return new LogMessage(true, "proper cell is not present.");
        } catch(Exception ex) {
            ex.printStackTrace();
            return new LogMessage(false,"exception occured: " + ex.getMessage());
        }
    }




    public LogMessage ClickLinkInTable(String objectLocatorData, String testData) {
        try {
            String columnName = "" ;
            String columnValue = "" ;
            if(!validateTestData(testData,2))
                return  new LogMessage(false, "test data invalid");
            String[] data = testData.split(",");
            columnName = data[0] ;
            columnValue = data[1] ;
            Map<String, WebElement>  row = getSingleRowfromTable(objectLocatorData,columnName,columnValue,null);
            if(null == row || row.isEmpty())
                return new LogMessage(false, "no table data");
            for (String key : row.keySet()) {
                if(key.split(",").length<2)
                    continue;
                String clName = key.split(",")[1];
                //System.out.println(clName);
                if(columnName.equals(clName)){
                    WebElement element = row.get(key) ;
                    String text = element.getText();
                    if(columnValue.equals(element.getText())) {
                        WebElement elm = element.findElement(By.linkText(columnValue));
                        //System.out.println(elm);
                        elm.click();
                        return new LogMessage(true, "element is clicked");
                    }
                }
            }
            return new LogMessage(true, "proper cell is not present.");
        } catch(Exception ex) {
            ex.printStackTrace();
            return new LogMessage(false,"exception occured: " + ex.getMessage());
        }
    }

    public LogMessage VerifyCellDataTrue(String objectLocatorData, String testData){
        try {
            String columnName = "" ;
            String columnValue = "" ;
            String rowIndex = "";
            if(!validateTestData(testData,3))
                return  new LogMessage(false, "test data invalid");
            String[] data = testData.split(",");
            columnName = data[0] ;
            rowIndex = data[1];
            columnValue = data[2] ;
            Map<String, WebElement>  row = getSingleRowfromTable(objectLocatorData,null,null,Integer.parseInt(rowIndex));
            if(null == row || row.isEmpty())
                return new LogMessage(false, "no table data");
            for (String key : row.keySet()) {
                if(key.split(",").length<2)
                    continue;
                String clName = key.split(",")[1];
                if(columnName.equals(clName)){
                    WebElement element = row.get(key) ;
                    String text = element.getText();
                    if(columnValue.equals(text)) {
                        return new LogMessage(true, "Cell data verified");
                    }
                }
            }
            return new LogMessage(true, "proper cell is not present.");
        } catch(Exception ex) {
            ex.printStackTrace();
            return new LogMessage(false,"exception occured: " + ex.getMessage());
        }
    }

    public LogMessage EnterCellData(String objectLocatorData, String testData) {
        try {
             String columnName = "" ;
             String columnValue = "" ;
             String rowIndex =  "" ;
             if(!validateTestData(testData,3))
                 return new LogMessage(false, "test data not valid");
             columnName = testData.split(",")[0] ;
             rowIndex = testData.split(",")[1] ;
            columnValue = testData.split(",")[2] ;
            Map<String, WebElement> row = getSingleRowfromTable(objectLocatorData,null,null,Integer.valueOf(rowIndex));
            //System.out.println(row);
            if(null == row || row.isEmpty())
                return new LogMessage(false, "no table data");
            for (String key : row.keySet()) {
                String clName = key.split(",")[1];
                if(columnName.equals(clName)){
                    WebElement element = row.get(key) ;
                    if(element.isEnabled()) {
                        //element.click();
                       // UtilKeywordScript.delay(3);
                        element.findElement(By.tagName("input")).sendKeys(columnValue);
                        return new LogMessage(true,"enter text data");
                    } else {
                        return new LogMessage(false," text field disabled");
                    }
                }
            }
            return new LogMessage(true, "proper cell is not present.");

        } catch ( Exception ex ) {
            return new LogMessage(false, "exception occured " + ex.getMessage());
        }
    }


    public LogMessage ClickCellData(String objectLocatorData, String testData) {
        try {
            String columnName = "" ;
            String columnValue = "" ;
            String rowIndex =  "" ;
            String inputTag = "";
            if(!validateTestData(testData,3))
                return new LogMessage(false, "test data not valid");
            columnName = testData.split(",")[0] ;
            rowIndex = testData.split(",")[1] ;
            columnValue = testData.split(",")[2] ;
            if(testData.split(",").length > 3)
                inputTag = testData.split(",")[3] ;

            Map<String, WebElement> row = getSingleRowfromTable(objectLocatorData,null,null,Integer.valueOf(rowIndex));
            //System.out.println(row);
            if(null == row || row.isEmpty())
                return new LogMessage(false, "no table data");
            for (String key : row.keySet()) {
                if(key.split(",").length <2) continue;
                String clName = key.split(",")[1];
                if(columnName.equals(clName)){
                    WebElement element = row.get(key) ;
                    if(element.isEnabled()) {
                        if(inputTag != "")
                            element.findElement(By.tagName(inputTag)).click();
                        else
                            element.click();
                        // UtilKeywordScript.delay(3);
                       // element.sendKeys(columnValue);
                        return new LogMessage(true,"click table data");
                    } else {
                        return new LogMessage(false," text field disabled");
                    }
                }
            }
            return new LogMessage(true, "proper cell is not present.");

        } catch ( Exception ex ) {
            ex.printStackTrace();
            return new LogMessage(false, "exception occured " + ex.getMessage());
        }
    }

    public LogMessage filterTableByColumn(String objectLocatorData,String testData) {
        try {
            String columnName = "" ;
            String columnValue = "" ;
            if(!validateTestData(testData,2))
                return new LogMessage(false, "test data not valid");
            columnName = testData.split(",")[0] ;
            columnValue = testData.split(",")[1] ;

                List<WebElement> headers = getCoumnWebElements(objectLocatorData);
                for(WebElement element : headers) {
                    if(element.getText().equals(columnName)) {
                        UIBase uiBase = new UIBase(webDriver);
                        uiBase.Click(element.findElement(By.linkText("")));
                       // element.findElement(By.xpath("//*[@class='k-icon k-filter']")).click();
                        UtilKeywordScript.delay(1);
                        webDriver.findElement(By.xpath("//input[@class='k-textbox']")).sendKeys(columnValue);   //button[text()='Filter']
                        UtilKeywordScript.delay(1);
                        webDriver.findElement(By.xpath("//button[text()='Filter']")).click();
                        UtilKeywordScript.delay(3);
                       return  new LogMessage(true, "column filter done");

                    }
                }
            return  new LogMessage(false, "column name not present");
        } catch (Exception ex) {
            ex.printStackTrace();
            return new LogMessage(false,"exception occured " + ex.getMessage());
        }
    }

    private List<WebElement> getCoumnWebElements(String objectLocatorData) {
        try {
            List<Map> tableData = new ArrayList<Map>();
            WebElement rootElement = WebObjectSearch.getWebElement(webDriver,objectLocatorData);
            List<WebElement> tables  = rootElement.findElements(By.tagName("table"));
            if(tables.size() <2) {
                // plz handle for single table
                return new ArrayList<WebElement>();
              //  return getSingleRowfromSingleTable(objectLocatorData,columnName,columnValue,rowIndex);
            }
            WebElement head = tables.get(0) ;
            List<WebElement> rows = head.findElements(By.tagName("tr"));
            return rows.get(0).findElements(By.tagName("th"));

        } catch (Exception ex) {
            ex.printStackTrace();
            return new ArrayList<WebElement>();
        }
    }

}
