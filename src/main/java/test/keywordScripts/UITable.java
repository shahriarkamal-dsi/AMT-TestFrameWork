package test.keywordScripts;

import com.google.common.collect.Ordering;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import test.Log.LogMessage;
import test.coreModule.TestPlan;
import test.objectLocator.WebObjectSearch;
import test.utility.PropertyConfig;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

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
    public List<Map<String,WebElement>> getAllValuesfromTable(String objectLocatorData) {
        try {
            List<Map<String,WebElement>> tableData = new ArrayList<Map<String,WebElement>>();
            WebElement rootElement = WebObjectSearch.getWebElement(webDriver,objectLocatorData);
            List<WebElement> tables  = rootElement.findElements(By.tagName("table"));
            if(tables.size() <2) {
                return getAllValuesfromSingleTable(objectLocatorData);
            }
            WebElement head = tables.get(0) ;
            WebElement body = tables.get(1) ;
            List<WebElement> rows = head.findElements(By.tagName("tr"));
            List<WebElement> headCells = rows.get(0).findElements(By.tagName("th"));
            rows = body.findElements(By.tagName("tr"));
           tableData =  rows.stream().map(row -> {
               AtomicInteger index = new AtomicInteger();
               List<WebElement> bodyCells = row.findElements(By.tagName("td"));
               Map<String,WebElement> data = bodyCells.stream().collect(Collectors.toMap( bodycell ->
               {
                   int count = index.getAndIncrement() ;
                 return UtilKeywordScript.isEmpty(headCells.get(count).getAttribute("textContent")) ? String.valueOf(count) :  headCells.get(count).getAttribute("textContent") ;
               }, bodycell -> bodycell));
               return data;
           }).collect(Collectors.toList());

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
            rows = body.findElements(By.tagName("tr"));
            int countRow = 0 ;
            boolean getMatchrow = false ;
            for(WebElement row : rows) {
                Map rowdata = new HashMap<String,WebElement>();
                List<WebElement> bodyCells = row.findElements(By.tagName("td"));
                int index = 0;
                for(WebElement bodyCell : bodyCells) {
                    if(null  != columnName && null != columnValue ) {
                        if(headCells.get(index).getText().contains(columnName)) {

                            if(bodyCell.getText().contains(columnValue)) {
                                getMatchrow = true;
                            }
                        }
                    }
                    String key = "";
                    if(specialTable) {
                        key =  Optional.ofNullable(bodyCell.getAttribute("title")).orElse(String.valueOf(index));
                    }
                    else {
                        key = Optional.ofNullable(headCells.get(index).getAttribute("textContent")).orElse(String.valueOf(index));
                    }
                    rowdata.put(key,bodyCell);
                    index++;
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
            List<WebElement>  headCells = null;
            //List<WebElement>  headCells = head.findElement(By.tagName("thead")).findElement(By.tagName("tr")).findElements(By.tagName("th"));
            int headRows = head.findElement(By.tagName("thead")).findElements(By.tagName("tr")).size();
            int i = 0;
            while ((null == headCells || headCells.isEmpty()) && i < headRows){
                headCells = head.findElement(By.tagName("thead")).findElements(By.tagName("tr")).get(i).findElements(By.tagName("th"));
                i++;
            }
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
                        if(headCells.get(index).getText().contains(columnName)) {
                            if(bodyCell.getText().contains(columnValue)) {
                                getMatchrow = true;
                            }
                        }
                    }
                   // String key =  String.valueOf(index) + "," + headCells.get(index).getText();
                   String key =  Optional.ofNullable(headCells.get(index).getText()).orElse(String.valueOf(index));

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


    private List<Map<String, WebElement>> getAllValuesfromSingleTable(String objectLocatorData ) {
        try {
        webDriver.manage().timeouts().implicitlyWait(PropertyConfig.SHORT_WAIT_TIME_SECONDS *2, TimeUnit.SECONDS) ;
        List<Map<String,WebElement>> tableData = new ArrayList<Map<String,WebElement>>();
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
              //  String key =  String.valueOf(index) + "," + headCells.get(index).getText();
               String key =  Optional.ofNullable(headCells.get(index).getAttribute("textContent")).orElse(String.valueOf(index));
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
                return  new LogMessage(false, "Test data invalid");
            String[] data = testData.split(",");
            columnName = data[0] ;
            columnValue = data[1] ;
            Map<String, WebElement>  row = getSingleRowfromTable(objectLocatorData,columnName,columnValue,null);

            if(null == row || row.isEmpty())
                return new LogMessage(false, "No table data found");
            if(!row.containsKey(columnName))
                return new LogMessage(false, "Column name is not present");

            WebElement element = row.get(columnName) ;
            String text = element.getText();
            if(element.getText().contains(columnValue)) {
                UIBase uibase = new UIBase(webDriver);
                uibase.ClickDbClickRClick(element,"DBLCLICK");
                return new LogMessage(true, "Element is double clicked");
            }
            return new LogMessage(true, "Proper cell is not present.");
        } catch(Exception ex) {
            ex.printStackTrace();
            return new LogMessage(false,"Exception occurred: " + ex.getMessage());
        }
    }
    public LogMessage ClickCellInTable(String objectLocatorData, String testData) {
        try {
            String columnName = "" ;
            String columnValue = "" ;
            if(!validateTestData(testData,2))
                return  new LogMessage(false, "Test data invalid");
            String[] data = testData.split(",");
            columnName = data[0] ;
            columnValue = data[1] ;
            Map<String, WebElement>  row = getSingleRowfromTable(objectLocatorData,columnName,columnValue,null);
            if(null == row || row.isEmpty())
                return new LogMessage(false, "No table data found");
            if(!row.containsKey(columnName))
                return new LogMessage(false, "Column name is not present");
            WebElement element = row.get(columnName) ;
            String text = element.getText();
            if(columnValue.equals(element.getText())) {
                element.click();
                return new LogMessage(true, "Element is clicked");
            }
            return new LogMessage(true, "Proper cell is not present.");

        } catch(Exception ex) {
            ex.printStackTrace();
            return new LogMessage(false,"Exception occurred: " + ex.getMessage());
        }
    }




    public LogMessage ClickLinkInTable(String objectLocatorData, String testData) {
        try {
            String columnName = "" ;
            String columnValue = "" ;
            if(!validateTestData(testData,2))
                return  new LogMessage(false, "Test data invalid");
            String[] data = testData.split(",");
            columnName = data[0] ;
            columnValue = data[1] ;
            Map<String, WebElement>  row = getSingleRowfromTable(objectLocatorData,columnName,columnValue,null);
            if(null == row || row.isEmpty())
                return new LogMessage(false, "No table data found");
            if(!row.containsKey(columnName))
                return new LogMessage(false, "Column name is not present");
            WebElement element = row.get(columnName) ;
            String text = element.getText();
            if(columnValue.equals(element.getText())) {
                WebElement elm = element.findElement(By.linkText(columnValue));
                elm.click();
                return new LogMessage(true, " Link element is clicked");
            }
            return new LogMessage(true, "Proper cell is not present.");

        } catch(Exception ex) {
            ex.printStackTrace();
            return new LogMessage(false,"Exception occurred: " + ex.getMessage());
        }
    }

    public LogMessage VerifyCellDataTrue(String objectLocatorData, String testData){
        try {
            String columnName = "" ;
            String columnValue = "" ;
            String rowIndex = "";
            if(!validateTestData(testData,3))
                return  new LogMessage(false, "Test data invalid");
            String[] data = testData.split(",");
            columnName = data[0] ;
            rowIndex = data[1];
            columnValue = data[2] ;
            Map<String, WebElement>  row = getSingleRowfromTable(objectLocatorData,null,null,Integer.parseInt(rowIndex));
            if(null == row || row.isEmpty())
                return new LogMessage(false, "No table data found");
            if(!row.containsKey(columnName))
                return new LogMessage(false, "Column name is not present");

            WebElement element = row.get(columnName) ;
            String text = element.getText();
            if(columnValue.equals(element.getText())) {
                return new LogMessage(true, "Proper cell is present");
            }
            return new LogMessage(false, "Proper cell is not present.");
        } catch(Exception ex) {
            ex.printStackTrace();
            return new LogMessage(false,"Exception occurred: " + ex.getMessage());
        }
    }

    public LogMessage VerifyCorrespondingColumnDataTrue(String objectLocatorData, String testData){

        try{
            if(!validateTestData(testData,4)){
                return  new LogMessage(false, "Test data invalid");
            }
            boolean isMatched=false;
            String[] data = testData.split(",");
            final String columnName1 = Optional.ofNullable(data[0]).orElse("") ;
            final String columnValue1 = Optional.ofNullable(data[1].trim()).orElse("") ;
            final  String columnName2 = Optional.ofNullable(data[2]).orElse("") ;
            final String columnValue2 = Optional.ofNullable(data[3].trim()).orElse("") ;
            List<Map<String,WebElement>> rows = getAllValuesfromTable(objectLocatorData);

            if (null == rows || rows.isEmpty()){
                return new LogMessage(false,"No table data found");
            }
            if(columnValue2.toUpperCase().equals("NA"))
                isMatched =  rows.stream().anyMatch(row ->
                        Optional.ofNullable(row.get(columnName1).getAttribute("textContent")).orElse("").trim().equals(columnValue1) &&
                                !Optional.ofNullable(row.get(columnName2).getAttribute("textContent")).orElse("").trim().isEmpty()
                ) ;
            else
                isMatched =  rows.stream().anyMatch(row ->
                        Optional.ofNullable(row.get(columnName1).getAttribute("textContent")).orElse("").trim().equals(columnValue1) &&
                                Optional.ofNullable(row.get(columnName2).getAttribute("textContent")).orElse("").trim().equals(columnValue2)
                ) ;
            return  isMatched  ? new LogMessage(true,"Corresponding column data verified") :  new LogMessage(false,"Proper cell is not present");
        }catch (Exception e){
            e.printStackTrace();
            return new  LogMessage(false,"Exception occurred " + e.getMessage());
        }
    }

    public LogMessage EnterCellData(String objectLocatorData, String testData) {
        try {
             String columnName = "" ;
             String columnValue = "" ;
             String rowIndex =  "" ;
             if(!validateTestData(testData,3))
                 return new LogMessage(false, "Test data invalid");
             columnName = testData.split(",")[0] ;
             rowIndex = testData.split(",")[1] ;
            columnValue = testData.split(",")[2] ;
            Map<String, WebElement> row = getSingleRowfromTable(objectLocatorData,null,null,Integer.valueOf(rowIndex));
            //System.out.println(row);
            if(null == row || row.isEmpty())
                return new LogMessage(false, "No table data found");
            if(!row.containsKey(columnName))
                return new LogMessage(false, "Column name is not present");

            WebElement element = row.get(columnName) ;
            if(element.isEnabled()) {
                //element.click();
                // UtilKeywordScript.delay(3);
                WebElement webElement=element.findElement(By.tagName("input"));
                UtilKeywordScript.delay(PropertyConfig.ONE_SECOND);
                webElement.sendKeys(columnValue);
                return new LogMessage(true,"Enter text data");
            } else {
                return new LogMessage(false," Text field disabled");
            }
        } catch ( Exception ex ) {
            return new LogMessage(false, "Exception occurred " + ex.getMessage());
        }
    }


    public LogMessage ClickCellData(String objectLocatorData, String testData) {
        try {
            String columnName = "" ;
            String columnValue = "" ;
            String rowIndex =  "" ;
            String inputTag = "";
            if(!validateTestData(testData,3))
                return new LogMessage(false, "Test data invalid");
            columnName = testData.split(",")[0] ;
            rowIndex = testData.split(",")[1] ;
            columnValue = testData.split(",")[2] ;
            if(testData.split(",").length > 3)
                inputTag = testData.split(",")[3] ;

            Map<String, WebElement> row = getSingleRowfromTable(objectLocatorData,null,null,Integer.valueOf(rowIndex));
            //System.out.println(row);
            if(null == row || row.isEmpty())
                return new LogMessage(false, "No table data found");
            if(!row.containsKey(columnName))
                return new LogMessage(false, "Column name is not present");
            WebElement element = row.get(columnName) ;
            if(element.isEnabled()) {
                if(inputTag != "")
                    element.findElement(By.tagName(inputTag)).click();
                else
                    element.click();
                return new LogMessage(true,"Click table data");
            } else {
                return new LogMessage(false," Text field disabled");
            }

        } catch ( Exception ex ) {
            ex.printStackTrace();
            return new LogMessage(false, "Exception occurred " + ex.getMessage());
        }
    }

    public LogMessage filterTableByColumn(String objectLocatorData,String testData) {
        try {
            String columnName = "" ;
            String columnValue = "" ;
            if(!validateTestData(testData,2))
                return new LogMessage(false, "Test data invalid");
            columnName = testData.split(",")[0] ;
            columnValue = testData.split(",")[1] ;

                List<WebElement> headers = getCoumnWebElements(objectLocatorData);
                for(WebElement element : headers) {
                    if(element.getText().equals(columnName)) {
                        UIBase uiBase = new UIBase(webDriver);
                        uiBase.Click(element.findElement(By.linkText("")));
                        UtilKeywordScript.delay(PropertyConfig.ONE_SECOND);
                        List<WebElement> webElements=webDriver.findElements(By.xpath("//input[@class='k-textbox']"));
                        for(WebElement webElement: webElements)
                        {
                            if(webElement.isDisplayed() && webElement.isEnabled()){
                                webElement.sendKeys(columnValue);
                                break;
                            }

                        }
                        UtilKeywordScript.delay(PropertyConfig.ONE_SECOND);
                        webElements=webDriver.findElements(By.xpath("//button[text()='Filter']"));
                        for(WebElement webElement: webElements)
                        {
                            if(webElement.isDisplayed() && webElement.isEnabled()){
                                uiBase.Click(webElement);
                                break;
                            }

                        }
                        UtilKeywordScript.delay(PropertyConfig.ONE_SECOND*2);
                       return  new LogMessage(true, "Column filter done");

                    }
                }
            return  new LogMessage(false, "Column name is not present");
        } catch (Exception ex) {
            ex.printStackTrace();
            return new LogMessage(false,"Exception occurred " + ex.getMessage());
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

    public LogMessage StoreColumnValue(String objectLocatorData,String testData) {
        try {
            String varName ;
            String columnName = "" ;
            String rowIndex = "" ;
            if(!validateTestData(testData,3))
                return new LogMessage(false, "Test data invalid");
            varName = testData.split(",")[0] ;
            columnName = testData.split(",")[1] ;
            rowIndex = testData.split(",")[2] ;
            Map<String, WebElement> row = getSingleRowfromTable(objectLocatorData,null,null,Integer.valueOf(rowIndex));
            if(!row.containsKey(columnName))
                return new LogMessage( false, "Column name is not present") ;
            String columnValue = row.get(columnName).getText() ;
            columnValue =  UtilKeywordScript.isItDigit(columnValue) ? UtilKeywordScript.convertStringToNumber(columnValue) : columnValue ;
            TestPlan.getInstance().setStoreData(varName,columnValue);

            return new LogMessage( true, "Column value is stored") ;

        } catch (Exception ex) {
            ex.printStackTrace();
            return new LogMessage( false, "Exception occurred " + ex.getMessage()) ;
        }
    }
    public LogMessage CompareEqualInTable(String objectLocator,String testData){
        try{
            if(!validateTestData(testData,4)){
                return  new LogMessage(false, "Test data invalid");
            }
            String[] data=testData.split(",");
            String columnName1=data[0];
            String columnValue1=data[1];
            String columnName2=data[2];
            String columnValue2=data[3];
            Map<String, WebElement> row = getSingleRowfromTable(objectLocator,columnName1,columnValue1,null);//
            WebElement element=row.get(columnName2);
            String columnValueOfUI  = UtilKeywordScript.convertStringToNumber(element.getAttribute("textContent")) ;
            if(Double.parseDouble(columnValue2)==Double.parseDouble(columnValueOfUI)){
                return new LogMessage( true, "Verified equal value");
            }
            else
                return new LogMessage( false, "Verified equal value false");
        } catch (Exception ex) {
            ex.printStackTrace();
            return new LogMessage( false, "Exception occurred " + ex.getMessage()) ;
        }

    }

    public LogMessage storeUITableValue(String objectLocator, String testData) {
        try {
            UtilKeywordScript utilKeywordScript = new UtilKeywordScript(webDriver);
            if (!utilKeywordScript.validateTestData(testData, 4)) {
                return new LogMessage(false, "Test data invalid");
            }
            String[] data = testData.split(",");
            final String columnName = Optional.ofNullable(data[0]).orElse("");
            final String columnValue = Optional.ofNullable(data[1]).orElse("");
            final String columnName2=Optional.ofNullable(data[2]).orElse("");
            final String varName=Optional.ofNullable(data[3]).orElse("");
            UITable uiTable = new UITable(webDriver);
            Map<String, WebElement> row = uiTable.getSingleRowfromTable(objectLocator, columnName, columnValue, null);
            if (null == row || row.isEmpty()) {
                return new LogMessage(false, "No table data found");
            }
            String varValue = Optional.ofNullable(row.get(columnName2).getAttribute("textContent")).orElse("");
            TestPlan.getInstance().setStoreData(varName, varValue);
            return new LogMessage(true, "UI value :" + varValue + " is stored");
        } catch (Exception ex) {
            ex.printStackTrace();
            return new LogMessage(false, "Exception occurred  " + ex.getMessage());
        }
    }
    public LogMessage CheckAllColumnInOrder(String objectLocator,String testData)
    {
        try{
            if(!validateTestData(testData,2)) {
                return new LogMessage(false, "Test data invalid");
            }
            boolean sorted=false;
            String[] data=testData.split(",");
            String columnName=data[0];
            String order=data[1];
            List<Map<String,WebElement>> rows = getAllValuesfromTable(objectLocator);
            List<String> columnValues=new ArrayList<>();
            for(Map<String,WebElement> row:rows){
                columnValues.add(row.get(columnName).getAttribute("textContent"));
            }
            if(order.toUpperCase().contains("ASC"))
                sorted= Ordering.natural().isOrdered(columnValues);
            else if(order.toUpperCase().contains("DES"))
                sorted= Ordering.natural().reverse().isOrdered(columnValues);
            return sorted?new LogMessage(sorted,"All column values are sorted"): new LogMessage(sorted,"Column values are unsorted");
        }catch (Exception e){
            return new LogMessage( false, "Exception occurred " + e.getMessage()) ;
        }
    }
    public LogMessage StoreLastRowColumnValue(String objectLocator,String testData){
        try {
            if(!validateTestData(testData,2)) {
                return new LogMessage(false, "Test data invalid");
            }
            String[] data = testData.split(",");
            String columnName = data[0];
            String varName = data[1];
            TestPlan.getInstance().setStoreData(varName,getLastRowColumnValue(objectLocator,columnName));
            return new LogMessage(true, "Last column value is stored");
        }catch (Exception e){
            return new LogMessage( false, "Exception occurred " + e.getMessage()) ;
        }
    }

    public LogMessage StoreLastRowIncreasedDateValue(String objectLocator,String testData){
        try {
            SimpleDateFormat dateFormat =   new SimpleDateFormat("MM/yyyy") ;
            if(!validateTestData(testData,2)) {
                return new LogMessage(false, "Test data invalid");
            }
            String[] data = testData.split(",");
            String columnName = data[0];
            String varName = data[1];
            String date = getLastRowColumnValue(objectLocator,columnName) ;
            Date lastDate =  dateFormat.parse(date);
            Calendar cal =  Calendar.getInstance() ;
            cal.setTime(lastDate);
            cal.add(Calendar.MONTH, 1);
            date = dateFormat.format(cal.getTime()) ;
            TestPlan.getInstance().setStoreData(varName,date);
            return new LogMessage(true, "Last column value is stored");
        }catch (Exception e){
            return new LogMessage( false, "Exception occurred " + e.getMessage()) ;
        }
    }
    public String getLastRowColumnValue(String objectLocator,String columnName){
        try {
            List<Map<String, WebElement>> rows = getAllValuesfromTable(objectLocator);
            if (null == rows || rows.isEmpty()){
                return "";
            }
            return Optional.ofNullable(rows.get(rows.size() - 1).get(columnName).getAttribute("textContent")).orElse("");
        }catch (Exception e){
            return "" ;
        }
    }
    public String getSpecificRowColumnValue(String objectLocator,String testData){
        try {
            String[] data = testData.split(",");
            String columnName = data[0];
            int rowNumber= Integer.parseInt(data[1]);
            List<Map<String, WebElement>> rows = getAllValuesfromTable(objectLocator);
            //System.out.println(rows.size());
            if (null == rows || rows.isEmpty()){
                return "";
            }
            return Optional.ofNullable(rows.get(rowNumber).get(columnName).getAttribute("textContent")).orElse("");
        }catch (Exception e){
            return "" ;
        }
    }
    public LogMessage compareSpecificRowColumnValue(String objectLocator,String testData)
    {
        try{
            if(!validateTestData(testData,3)) {
                return new LogMessage(false, "Test data invalid");
            }
            String[] data = testData.split(",");
            if(getSpecificRowColumnValue(objectLocator,data[0]+","+data[1]).equals(data[2]))
                return new LogMessage( true, "Column value verified") ;
            else
                return new LogMessage( false, "Column value is not verified") ;
        }catch (Exception e){
            return new LogMessage( false, "exception occurred " + e.getMessage()) ;
        }
    }
    public LogMessage compareLastRowColumnValue(String objectLocator,String testData)
    {
        UtilKeywordScript utilKeywordScript=new UtilKeywordScript(webDriver);
        UITable uiTable=new UITable(webDriver);
        try{
            if(!utilKeywordScript.validateTestData(testData,2)) {
                return new LogMessage(false, "Test data invalid");
            }
            String[] data = testData.split(",");
            String columnName = data[0];
            if(uiTable.getLastRowColumnValue(objectLocator,columnName).equals(data[1]))
                return new LogMessage( true, "Last row column value verified") ;
            else
                return new LogMessage( false, "Last row column value is not verified") ;
        }catch (Exception e){
            return new LogMessage( false, "Exception occurred " + e.getMessage()) ;
        }
    }

}
