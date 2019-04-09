package test.keywordScripts;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import test.Log.LogMessage;
import test.objectLocator.WebObjectSearch;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Map;
import java.util.Optional;

public class UtilRevision {

    private WebDriver webDriver ;

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
            UILink uiLink = new UILink(webDriver);
            UIBase uiBase = new UIBase(webDriver);
            UIPanel uiPanel = new UIPanel(webDriver);
            LogMessage subLog = uiText.WaitForVisibilityOfText(objectlocatorPrefix + "processInfoPanel","% Complete,60");
            log.setSubLogMessage(subLog);
            subLog = uiPanel.VerifyPanelContentTrue(objectlocatorPrefix + "processInfoPanel","Position in Queue:");
            log.setSubLogMessage(subLog);
            WebElement element = WebObjectSearch.getWebElement(webDriver, objectlocatorPrefix + "queueCount");
            String countString = element.getText().toString();
            subLog = uiBase.compareGreaterThanValue(countString + ",0");
            log.setSubLogMessage(subLog);
            subLog = uiLink.verifyLinkEnabledFalse(objectlocatorPrefix + "lnkContinueWithProcess");
            log.setSubLogMessage(subLog);
            subLog = uiLink.verifyLinkEnabledFalse(objectlocatorPrefix + "lnkPrint");
            log.setSubLogMessage(subLog);
            subLog = uiLink.verifyLinkEnabledTrue(objectlocatorPrefix + "lnkCancel");
            log.setSubLogMessage(subLog);
            subLog = uiPanel.VerifyPanelContentTrue(objectlocatorPrefix + "processCount","Ready To Process 0 Revisions");
            log.setSubLogMessage(subLog);
            subLog = uiText.WaitForInvisibilityOfText(objectlocatorPrefix + "processInfoPanel","% Complete ,60");
            log.setSubLogMessage(subLog);
            if (subLog.isPassed()){
                log.setPassed(true);
                log.setLogMessage("Processing done");
                return log;
            };
            log.setPassed(false);
            log.setLogMessage("Processing fail");
            return log;
        }catch (Exception e){
            log.setLogMessage("Exception occur");
            return log;
        }
    }

    public LogMessage validateProcessingComplete(){
        try{


            return new LogMessage(true,"");
        }catch (Exception e){
            return new LogMessage(false,"Exception occur");
        }
    }

    public LogMessage VerifyNoOfPeriodsInRevision(String objectLocator, String testData){
        try{
        String[] data = testData.split(",");
        final String columnName = Optional.ofNullable(data[0]).orElse("") ;
        final String columnValue = Optional.ofNullable(data[1]).orElse("") ;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        UITable uiTable=new UITable(webDriver);
        Map<String, WebElement> row = uiTable.getSingleRowfromTable(objectLocator,columnName,columnValue,null);
        if (null == row || row.isEmpty()){
            return new LogMessage(false,"no table data found");
        }
        WebElement startDateElement=row.get("FASB/IASB Start Date");
        LocalDate startDate=LocalDate.parse(startDateElement.getAttribute("textContent").trim(),formatter);
        WebElement endDateElement=row.get("FASB/IASB End Date");
        LocalDate endDate=LocalDate.parse(endDateElement.getAttribute("textContent").trim(),formatter);

        double noOfMonths =(double) (ChronoUnit.MONTHS.between(startDate,endDate)+1);
        WebElement noOfPeriodsElement=row.get("# of Periods");
        if(Double.parseDouble(noOfPeriodsElement.getAttribute("textContent"))==noOfMonths){
            return new  LogMessage(true,"No of periods verified");
        }
        else
            return new  LogMessage(false,"No of periods not verified");
        }catch (Exception e){
            e.printStackTrace();
            return new  LogMessage(false,"Exception occer " + e.getMessage());
        }
    }
    public LogMessage VerifyAmountToCapitalize(String objectLocator,String testData){
        try{
            String[] data = testData.split(",");
            final String columnName = Optional.ofNullable(data[0]).orElse("") ;
            final String columnValue = Optional.ofNullable(data[1]).orElse("") ;
            UITable uiTable=new UITable(webDriver);
            Map<String, WebElement> row = uiTable.getSingleRowfromTable(objectLocator,columnName,columnValue,null);
            if (null == row || row.isEmpty()){
                return new LogMessage(false,"no table data found");
            }
            double noOfPeriods=Double.parseDouble(row.get("# of Periods").getAttribute("textContent"));
            double amountToCapitalize=Double.parseDouble(UtilKeywordScript.convertStringToNumber(row.get("Total Amount to Be Capitalized").getAttribute("textContent")));
            double rentalAmount=Double.parseDouble(UtilKeywordScript.convertStringToNumber(row.get("Straight Line").getAttribute("textContent")));
            if(amountToCapitalize==(noOfPeriods*rentalAmount)){
                return new  LogMessage(true,"Amount to capitalize verified");
            }
            else
                return new  LogMessage(false,"Amount to capitalize not verified");
        }catch (Exception e){
            e.printStackTrace();
            return new  LogMessage(false,"Exception occer " + e.getMessage());
        }
    }

}
