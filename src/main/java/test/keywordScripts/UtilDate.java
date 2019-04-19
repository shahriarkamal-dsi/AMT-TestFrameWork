package test.keywordScripts;

import org.apache.xmlbeans.impl.xb.xsdschema.Public;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import test.Log.LogMessage;
import test.coreModule.TestPlan;
import test.objectLocator.WebObjectSearch;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import static java.time.temporal.ChronoUnit.MONTHS;
import static java.time.temporal.ChronoUnit.DAYS;
import static java.time.temporal.ChronoUnit.YEARS;

public class UtilDate {

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");

    private WebDriver webDriver ;

    public UtilDate() {

    }

    public UtilDate(WebDriver webDriver) {
        this.webDriver = webDriver ;
    }


    public long getDateGap(String startDate, String endDate, String type){
        try{

            LocalDate date1 = LocalDate.parse(startDate, formatter);
            LocalDate date2 = LocalDate.parse(endDate, formatter);
            if(type.equals("D"))
                return DAYS.between(date1, date2)+1;
            else if(type.equals("M"))
                return MONTHS.between(date1, date2)+1;
            else  return YEARS.between(date1, date2)+1;

        }catch (Exception e){
            return -11111111  ;
        }
    }


    public LogMessage CheckDate(String testData){
        try{

            String day1 = testData.split(",")[0] ;
            String day2 = testData.split(",")[1] ;
            String differenceType = testData.split(",")[2] ;
            long difference = getDateGap(day1,day2,"D") ;


            if(differenceType.equals("1"))
                return  difference >=1 ?  new LogMessage(true,"passed") : new LogMessage(false," not passed");
            else if(differenceType.equals("0"))
                return  difference == 0 ?  new LogMessage(true,"passed") : new LogMessage(false," not passed");
            else //if(differenceType.equals("-1"))
                return  difference <=-1 ?  new LogMessage(true,"passed") : new LogMessage(false," not passed");


        }catch (Exception e){
            return new LogMessage( false, "exception occurred " + e.getMessage());
        }
    }


}
