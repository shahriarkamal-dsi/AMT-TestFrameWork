package test.keywordScripts;

import org.apache.xmlbeans.impl.xb.xsdschema.Public;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import test.Log.LogMessage;
import java.time.LocalDate;
import java.time.Period;
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
        LocalDate startDate=LocalDate.parse(startDateElement.getText().trim(),formatter);
        WebElement endDateElement=row.get("FASB/IASB End Date");
        LocalDate endDate=LocalDate.parse(endDateElement.getText().trim(),formatter);

        Double noOfMonths =(double) (ChronoUnit.MONTHS.between(startDate,endDate)+1);
        WebElement noOfPeriodsElement=row.get("# of Periods");
        if(Double.parseDouble(noOfPeriodsElement.getText())==noOfMonths){
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
            Double noOfPeriods=Double.parseDouble(row.get("# of Periods").getText());
            Double amountToCapitalize=Double.parseDouble(UtilKeywordScript.convertStringToNumber(row.get("Total Amount to Be Capitalized").getText()));
            Double rentalAmount=Double.parseDouble(UtilKeywordScript.convertStringToNumber(row.get("Straight Line").getText()));
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
