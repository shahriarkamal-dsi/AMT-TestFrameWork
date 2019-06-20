package test.keywordScripts;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import test.Log.LogMessage;
import test.objectLocator.WebObjectSearch;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Map;
import java.util.Optional;
import java.util.logging.LogManager;

import test.keywordScripts.UtilDate;
import test.utility.PropertyConfig;

public class UtilRevision {

    private WebDriver webDriver ;
    UtilDate utildate = new UtilDate();
    public UtilRevision() {

    }

    public UtilRevision(WebDriver webDriver) {
        this.webDriver = webDriver ;

    }

    public LogMessage validateProcessing(){
        LogMessage log = new LogMessage();
        try{

            String objectlocatorPrefix = "FASB.FIProcess.";
            UIText uiText = new UIText(webDriver);
            UIBase uiBase = new UIBase(webDriver);
            UIPanel uiPanel = new UIPanel(webDriver);
            uiBase.refreshPage();
            LogMessage subLog = uiText.WaitForVisibilityOfText(objectlocatorPrefix + "processInfoPanel","% Complete,120");
            subLog.setLogMessage(" % Complete - " + subLog.getLogMessage());
            log.addSubLogMessage(subLog);

            subLog = uiPanel.VerifyPanelContentTrue(objectlocatorPrefix + "processInfoPanel","Position in Queue:");
            subLog.setLogMessage(" Position in Queue - " + subLog.getLogMessage());
            log.addSubLogMessage(subLog);

            WebElement element = WebObjectSearch.getWebElement(webDriver, objectlocatorPrefix + "queueCount");
            String countString = element.getAttribute("textContent");
            subLog = uiBase.compareGreaterThanValue(countString + ",0");
            subLog.setLogMessage("Queue Count - " + subLog.getLogMessage());
            log.addSubLogMessage(subLog);

            subLog = uiBase.CustomEnabledFalse(objectlocatorPrefix + "lnkContinueWithProcess");
            subLog.setLogMessage("Continue with process button - " + subLog.getLogMessage());
            log.addSubLogMessage(subLog);

            subLog = uiBase.CustomEnabledFalse(objectlocatorPrefix + "btnPrint");
            subLog.setLogMessage("Print button - " + subLog.getLogMessage());
            log.addSubLogMessage(subLog);

            subLog = uiBase.CustomEnabledTrue(objectlocatorPrefix + "btnCancel");
            subLog.setLogMessage("Cancel button - " + subLog.getLogMessage());
            log.addSubLogMessage(subLog);

            subLog = uiText.WaitForInvisibilityOfText(objectlocatorPrefix + "processInfoPanel","% Complete ,900");
            subLog.setLogMessage("% Complete - " + subLog.getLogMessage());
            log.addSubLogMessage(subLog);

            if (subLog.isPassed()){
                log.setPassed(true);
                log.setLogMessage("Processing done");
                return log;
            }
            log.setPassed(false);
            log.setLogMessage("Processing is not complete in 15m ");
            return log;
        }catch (Exception e){
            log.setLogMessage("Exception occur");
            return log;
        }
    }

    public LogMessage continueProcess(){
        try{
            String objectlocatorPrefix = "FASB.FIProcess.";
            UIText uiText = new UIText(webDriver);
            UIBase uiBase = new UIBase(webDriver);
            UIPanel uiPanel = new UIPanel(webDriver);

            LogMessage log = uiPanel.VerifyPanelContentTrue(objectlocatorPrefix + "processInfoPanel","Processing Done");
            if (log.isPassed()){
                LogMessage subLog = uiBase.CustomEnabledTrue(objectlocatorPrefix + "lnkContinueWithProcess");
                if (subLog.isPassed()){
                    LogMessage ClickLog = uiBase.Click(objectlocatorPrefix + "lnkContinueWithProcess");
                    if (ClickLog.isPassed()){
                        return new LogMessage(true, "Click post button successfully");
                    }
                }else {
                    uiBase.Click(objectlocatorPrefix + "btnCancel");
                    UtilKeywordScript.delay(PropertyConfig.ONE_SECOND*2);
                    webDriver.switchTo().alert().accept();
                    return new LogMessage(false,"Continue and process button is not enable. So cancel the process");
                }
            }else {
                uiBase.Click(objectlocatorPrefix + "btnCancel");
                UtilKeywordScript.delay(PropertyConfig.ONE_SECOND*2);
                webDriver.switchTo().alert().accept();
                return new LogMessage(false,"Continue process fail and cancel the process");
            }

            return new LogMessage(false, "Continue process fail");


        }catch (Exception ex){
            return new LogMessage(false,"Exception occur " + ex.getMessage());
        }
    }

    public LogMessage validateRevisionCalculation(){
        LogMessage log = new LogMessage();
        try{

            String objectlocatorPrefix = "FASB.FIProcess.";
            UIText uiText = new UIText(webDriver);
            UIBase uiBase = new UIBase(webDriver);
            UIPanel uiPanel = new UIPanel(webDriver);
            LogMessage subLog = uiText.WaitForVisibilityOfText(objectlocatorPrefix + "processInfoPanel","% Complete,120");
            subLog.setLogMessage(" % Complete - " + subLog.getLogMessage());
            log.addSubLogMessage(subLog);

            subLog = uiPanel.VerifyPanelContentTrue(objectlocatorPrefix + "processInfoPanel","Position in Queue:");
            subLog.setLogMessage(" Position in Queue - " + subLog.getLogMessage());
            log.addSubLogMessage(subLog);

            WebElement element = WebObjectSearch.getWebElement(webDriver, objectlocatorPrefix + "queueCount");
            String countString = element.getAttribute("textContent");
            subLog = uiBase.compareGreaterThanValue(countString + ",0");
            subLog.setLogMessage("Queue Count - " + subLog.getLogMessage());
            log.addSubLogMessage(subLog);

            subLog = uiBase.CustomEnabledFalse(objectlocatorPrefix + "btnPost");
            subLog.setLogMessage("Post button - " + subLog.getLogMessage());
            log.addSubLogMessage(subLog);

            subLog = uiBase.CustomEnabledFalse(objectlocatorPrefix + "btnTransactionReport");
            subLog.setLogMessage("Transaction Report button - " + subLog.getLogMessage());
            log.addSubLogMessage(subLog);

            subLog = uiBase.CustomEnabledFalse(objectlocatorPrefix + "btnJEReport");
            subLog.setLogMessage("JE Report button - " + subLog.getLogMessage());
            log.addSubLogMessage(subLog);

            subLog = uiBase.CustomEnabledFalse(objectlocatorPrefix + "btnFXJEReport");
            subLog.setLogMessage("FX JE Report button - " + subLog.getLogMessage());
            log.addSubLogMessage(subLog);

            subLog = uiBase.CustomEnabledTrue(objectlocatorPrefix + "btnCancel");
            subLog.setLogMessage("Cancel button - " + subLog.getLogMessage());
            log.addSubLogMessage(subLog);

            subLog = uiText.WaitForInvisibilityOfText(objectlocatorPrefix + "processInfoPanel","% Complete ,900");
            subLog.setLogMessage("% Complete - " + subLog.getLogMessage());
            log.addSubLogMessage(subLog);

            if (subLog.isPassed()){
                log.setPassed(true);
                log.setLogMessage("Processing Complete for post");
                return log;
            }
            log.setPassed(false);
            log.setLogMessage("Processing is not complete for post in 15m");
            return log;
        }catch (Exception e){
            log.setLogMessage("Exception occur");
            return log;
        }
    }

    public LogMessage postRevision(){
        try{
            String objectlocatorPrefix = "FASB.FIProcess.";
            UIText uiText = new UIText(webDriver);
            UIBase uiBase = new UIBase(webDriver);
            UIPanel uiPanel = new UIPanel(webDriver);

            LogMessage log = uiPanel.VerifyPanelContentTrue(objectlocatorPrefix + "processInfoPanel","Processing Done");
            if (log.isPassed()){
                LogMessage subLog = uiBase.CustomEnabledTrue(objectlocatorPrefix + "btnPost");
                if (subLog.isPassed()){
                    LogMessage ClickLog = uiBase.Click(objectlocatorPrefix + "btnPost");
                    if (ClickLog.isPassed()){
                        return new LogMessage(true, "Click post button successfully");
                    }
                }else {
                    uiBase.Click(objectlocatorPrefix + "btnCancel");
                    UtilKeywordScript.delay(PropertyConfig.ONE_SECOND*2);
                    webDriver.switchTo().alert().accept();
                    return new LogMessage(false,"Post button is not enable. So cancel the process");
                }

            }else {
                uiBase.Click(objectlocatorPrefix + "btnCancel");
                UtilKeywordScript.delay(PropertyConfig.ONE_SECOND*2);
                webDriver.switchTo().alert().accept();
                return new LogMessage(false,"Revision process fail and cancel the process");
            }

            return new LogMessage(false, "Post revision fail");


        }catch (Exception ex){
            return new LogMessage(false,"Exception occur " + ex.getMessage());
        }
    }


    public LogMessage VerifyNoOfPeriodsInRevision(String objectLocator, String testData){
        try{
         UtilKeywordScript utilKeywordScript=new UtilKeywordScript(webDriver);
         if(!utilKeywordScript.validateTestData(testData,2)){
             return new LogMessage(false, "Test data invalid");
         }
        String[] data = testData.split(",");
        final String columnName = Optional.ofNullable(data[0]).orElse("") ;
        final String columnValue = Optional.ofNullable(data[1]).orElse("") ;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        UITable uiTable=new UITable(webDriver);
        Map<String, WebElement> row = uiTable.getSingleRowfromTable(objectLocator,columnName,columnValue,null);
        if (null == row || row.isEmpty()){
            return new LogMessage(false,"No table data found");
        }

        WebElement startDateElement=row.get("FASB/IASB Start Date");
        LocalDate startDate=LocalDate.parse(startDateElement.getAttribute("textContent").trim(),formatter);
        WebElement endDateElement=row.get("FASB/IASB End Date");
        LocalDate endDate=LocalDate.parse(endDateElement.getAttribute("textContent").trim(),formatter);

        double noOfMonths =(double) (ChronoUnit.MONTHS.between(startDate,endDate)+1);
        WebElement noOfPeriodsElement=row.get("# of Periods");
        if(Math.round(Double.parseDouble(noOfPeriodsElement.getAttribute("textContent")))==noOfMonths){
            return new  LogMessage(true,"No of periods verified");
        }
        else
            return new  LogMessage(false,"No of periods not verified");
        }catch (Exception e){
            e.printStackTrace();
            return new  LogMessage(false,"Exception occurred " + e.getMessage());
        }
    }
    public LogMessage VerifyAmountToCapitalize(String objectLocator,String testData){
        try{
            UtilKeywordScript utilKeywordScript=new UtilKeywordScript(webDriver);
            if(!utilKeywordScript.validateTestData(testData,3)){
                return new LogMessage(false, "Test data invalid");
            }
            String[] data = testData.split(",");
            final String columnName = Optional.ofNullable(data[0]).orElse("") ;
            final String columnValue = Optional.ofNullable(data[1]).orElse("") ;
            final double rentalAmount=Double.parseDouble(Optional.ofNullable(data[2]).orElse(""));
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
            UITable uiTable=new UITable(webDriver);
            Map<String, WebElement> row = uiTable.getSingleRowfromTable(objectLocator,columnName,columnValue,null);
            if (null == row || row.isEmpty()){
                return new LogMessage(false,"No table data found");
            }
            WebElement startDateElement=row.get("FASB/IASB Start Date");
            LocalDate startDate=LocalDate.parse(startDateElement.getAttribute("textContent").trim(),formatter);
            WebElement endDateElement=row.get("FASB/IASB End Date");
            LocalDate endDate=LocalDate.parse(endDateElement.getAttribute("textContent").trim(),formatter);

            double noOfMonths =(double) (ChronoUnit.MONTHS.between(startDate,endDate)+1);
            double amountToCapitalize=Double.parseDouble(UtilKeywordScript.convertStringToNumber(row.get("Total Amount to Be Capitalized").getAttribute("textContent")));

            if(amountToCapitalize==(noOfMonths*rentalAmount)){
                return new  LogMessage(true,"Amount to capitalize verified");
            }
            else
                return new  LogMessage(false,"Amount to capitalize is not verified");
        }catch (Exception e){
            e.printStackTrace();
            return new  LogMessage(false,"Exception occurred " + e.getMessage());
        }
    }

    public LogMessage verifyPeriodof13Period(String objectLocator,String testData){
        try{
            UtilKeywordScript utilKeywordScript=new UtilKeywordScript(webDriver);
            UITable uiTable=new UITable(webDriver);
            if(!utilKeywordScript.validateTestData(testData,2)){
                return new LogMessage(false, "Test data invalid");
            }
            String[] data = testData.split(",");
            final String columnName = Optional.ofNullable(data[0]).orElse("") ;
            final String columnValue = Optional.ofNullable(data[1]).orElse("") ;
            Map<String, WebElement> row = uiTable.getSingleRowfromTable(objectLocator,columnName,columnValue,null);
            if (null == row || row.isEmpty()){
                return new LogMessage(false,"No table data found");
            }
            WebElement startDateElement=row.get("FASB/IASB Start Date");
            String startDate=startDateElement.getAttribute("textContent").trim();
            WebElement endDateElement=row.get("FASB/IASB End Date");
            String endDate=endDateElement.getAttribute("textContent").trim();
            long noOfMonths = utildate.getDateGap(startDate,endDate,"M")+utildate.getDateGap(startDate,endDate,"Y");
            WebElement noOfPeriodsElement=row.get("# of Periods");
            if(Math.round(Double.parseDouble(noOfPeriodsElement.getAttribute("textContent")))==noOfMonths){
                return new  LogMessage(true,"No of periods verified");
            }
            else
                return new  LogMessage(false,"No of periods not verified");
        }catch (Exception e){
            e.printStackTrace();
            return new  LogMessage(false,"Exception occurred " + e.getMessage());
        }
    }

    public LogMessage verifyTotalPeriodPayments(String testData){
        System.out.println("Test data: "+ testData);
        try{
            UtilKeywordScript utilKeywordScript=new UtilKeywordScript(webDriver);
            if(!utilKeywordScript.validateTestData(testData,4)){
                return new LogMessage(false, "Test data invalid");
            }
            double payments1 = Double.parseDouble(testData.split(",")[0].trim()) ;
            double payments2 = Double.parseDouble(testData.split(",")[1].trim()) ;
            String date1 = testData.split(",")[2].trim() ;
            String date2 = testData.split(",")[3].trim() ;
            payments2 =payments2 * utildate.getDateGap(date1,date2,"M");
            if(payments1==payments2) return new LogMessage(true, "Value matches with the referred value");
            else return new LogMessage(false, "Value does not match with the referred value");
        }catch (Exception e){
            return new LogMessage(false,"Exception occurred");
        }
    }
    public LogMessage checkAvailabilityofColumnInSchedule(String objectLocator){
        try{
            WebElement webElement = WebObjectSearch.getWebElement(webDriver, objectLocator);
            if (null == webElement){
                return new LogMessage(false, " Element is not found");
            }
            if(!webElement.getAttribute("style").contains("display: none"))
                return new LogMessage(true, "Column found");
            return new LogMessage(false, "Column is not found");
        }catch (Exception e){
            return new LogMessage(false,"Exception occurred"+e.getMessage());
        }
    }
    public LogMessage checkLastRevision(String objectLocator,String testData)
    {
        UtilKeywordScript utilKeywordScript=new UtilKeywordScript(webDriver);
        UITable uiTable=new UITable(webDriver);
        try{
            if(!utilKeywordScript.validateTestData(testData,2)) {
                return new LogMessage(false, "Test data invalid");
            }
            String[] data = testData.split(",");
            String columnName = data[0];
            String varName = String.valueOf(Integer.parseInt(data[1])+1);
            if(uiTable.getLastRowColumnValue(objectLocator,columnName).equals(varName))
                return new LogMessage( true, "Last row column value verified") ;
            else
                return new LogMessage( false, "Last row column value not verified") ;
        }catch (Exception e){
            return new LogMessage( false, "Exception occurred " + e.getMessage()) ;
        }
    }
    public LogMessage verifyRevisionAdjustmentPeriod(String objectLocator,String testData){
        try{
            UIText uiText = new UIText(webDriver);
            String data[] = testData.split("/");
            if(!(data.length ==3))
                return new LogMessage(false,"Test data invalid");

            String period = uiText.getText(objectLocator);
            testData = data[0] + "/" +data[2];

            if (period.equals(testData))
                return new LogMessage(true,"Revision Adjustment Period verified");

            return new LogMessage(false,"Wrong Revision Adjustment Period");

        }catch (Exception e){
            return new LogMessage(false,"Exception occurred"+e.getMessage());
        }
    }

    public LogMessage setPostPeriod(String objectLocator,String testData){

        UtilKeywordScript utilKeywordScript=new UtilKeywordScript(webDriver);
        UIText uiText = new UIText(webDriver);
        UtilDate utilDate = new UtilDate(webDriver);
        String postperiodDate = "";
        try{
            /*if(!utilKeywordScript.validateTestData(testData,2)) {
                return new LogMessage(false, "Test data invalid");
            }*/
            String[] data = testData.split(",");
            String lastPeriod = data[0];
            String postPeriod = data[1];

            WebElement userWeb = WebObjectSearch.getWebElement(webDriver,objectLocator);
            if(null == userWeb )
                return new LogMessage(false,"Web element is not found");

            if (!lastPeriod.isEmpty()){
                postperiodDate = utilDate.getIncreasedDate(lastPeriod);
                LogMessage setTextlog = uiText.SetText(objectLocator,postperiodDate);
                if (setTextlog.isPassed())
                    return new LogMessage(true," Period date set successfully");

                return new LogMessage(false, "Period date set fail");

            }else{
                postperiodDate = utilDate.changeDateFormat(postPeriod);
                LogMessage log = uiText.SetText(objectLocator,postperiodDate);
                if(log.isPassed())
                    return new LogMessage(true,"Period date set successfully");

                return new LogMessage(false, "Period date set fail");
            }
        }catch (Exception ex){
            return new LogMessage(false,"Exception occur " + ex.getMessage());
        }
    }
}
