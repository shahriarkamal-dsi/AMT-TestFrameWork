package test.keywordScripts;

import org.apache.xmlbeans.impl.xb.xsdschema.Public;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import test.Log.LogMessage;
import test.coreModule.TestPlan;
import test.objectLocator.WebObjectSearch;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

import static java.time.temporal.ChronoUnit.MONTHS;
import static java.time.temporal.ChronoUnit.DAYS;
import static java.time.temporal.ChronoUnit.YEARS;

public class UtilDate extends  UtilKeywordScript {

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
            UtilKeywordScript utilKeywordScript=new UtilKeywordScript(webDriver);
           if(!utilKeywordScript.validateTestData(testData,3)){
                return new LogMessage(false, "Test data invalid");
            }

            String day1 = testData.split(",")[0].trim() ;
            String day2 = testData.split(",")[1].trim() ;
            long differenceType = Long.parseLong(testData.split(",")[2].trim()) ;
            long difference = getDateGap(day1,day2,"D") ;
            if(difference>=1)
                difference=1;
            else if(difference==0)
                difference=0;
            else
                difference =-1 ;
            return (difference==differenceType)?  new LogMessage( true, "Date Gap is correct" ) : new LogMessage( false, "Date gap is correct" );


        }catch (Exception e){
            return new LogMessage( false, "Exception occurred " + e.getMessage());
        }
    }

    public LogMessage StoreIncreasedDate(String testData){

        try {
            SimpleDateFormat dateFormat =   new SimpleDateFormat("MM/yyyy") ;
            SimpleDateFormat fullDateFormat = new SimpleDateFormat("MM/dd/yyyy");
            Date lastDate;
            Calendar cal;
            if(!validateTestData(testData,2)) {
                return new LogMessage(false, "Test data invalid");
            }
            String[] data = testData.split(",");
            String date = data[0];
            String varName = data[1];
            String[] fullData = testData.split("/");
            if (fullData.length == 3){
                lastDate = fullDateFormat.parse(date);
                cal = Calendar.getInstance();
                cal.setTime(lastDate);
                cal.add(Calendar.MONTH,1);
                date = fullDateFormat.format(cal.getTime()) ;
                TestPlan.getInstance().setStoreData(varName,date);
                return new LogMessage(true, date + " - increased date  value is stored");
            }else {
                lastDate =  dateFormat.parse(date);
                cal =  Calendar.getInstance() ;
                cal.setTime(lastDate);
                cal.add(Calendar.MONTH, 1);
                date = dateFormat.format(cal.getTime()) ;
                TestPlan.getInstance().setStoreData(varName,date);
                return new LogMessage(true, date + " - increased date  value is stored");
            }

        }catch (Exception e){
            return new LogMessage( false, "Exception occurred " + e.getMessage()) ;
        }
    }
    public LogMessage StoreDecrementDate(String testData){
        try {
            SimpleDateFormat dateFormat =   new SimpleDateFormat("MM/yyyy") ;
            SimpleDateFormat fullDateFormat = new SimpleDateFormat("MM/dd/yyyy");
            Date lastDate;
            Calendar cal;
            if(!validateTestData(testData,2)) {
                return new LogMessage(false, "Test data invalid");
            }
            String[] data = testData.split(",");
            String date = data[0];
            String varName = data[1];
            String[] fullData = testData.split("/");
            if (fullData.length == 3){
                lastDate = fullDateFormat.parse(date);
                cal = Calendar.getInstance();
                cal.setTime(lastDate);
                cal.add(Calendar.MONTH,-1);
                date = fullDateFormat.format(cal.getTime()) ;
                TestPlan.getInstance().setStoreData(varName,date);
                return new LogMessage(true, date + " - Decrement date value is stored");
            }else {
                lastDate =  dateFormat.parse(date);
                cal =  Calendar.getInstance() ;
                cal.setTime(lastDate);
                cal.add(Calendar.MONTH, -1);
                date = dateFormat.format(cal.getTime()) ;
                TestPlan.getInstance().setStoreData(varName,date);
                return new LogMessage(true, date + " - Decrement date  value is stored");
            }

        }catch (Exception e){
            e.printStackTrace();
            return new LogMessage( false, "Exception occurred " + e.getMessage()) ;
        }
    }

    public String getIncreasedDate(String testData){
        try {
            SimpleDateFormat dateFormat =   new SimpleDateFormat("MM/yyyy") ;
            if(null == testData || testData.isEmpty()) {
                return testData;
            }
            Date lastDate =  dateFormat.parse(testData);
            Calendar cal =  Calendar.getInstance() ;
            cal.setTime(lastDate);
            cal.add(Calendar.MONTH, 1);
            testData = dateFormat.format(cal.getTime()) ;
            return testData;
        }catch (Exception e){
            return testData;
        }
    }

    public  String changeDateFormat(String testData){
        try {
            SimpleDateFormat dateFormat1 =   new SimpleDateFormat("MM/DD/yyyy") ;
            SimpleDateFormat dateFormat2 =   new SimpleDateFormat("MM/yyyy") ;
            Date lastDate =  dateFormat1.parse(testData);
            Calendar cal =  Calendar.getInstance() ;
            cal.setTime(lastDate);
            return  dateFormat2.format(cal.getTime()) ;
        }catch (Exception e){
            return testData ;
        }
    }

    public LogMessage storeChangeDateFormat(String testData){
        try {
            SimpleDateFormat dateFormat1 =   new SimpleDateFormat("MM/dd/yyyy") ;
            SimpleDateFormat dateFormat2 =   new SimpleDateFormat("MM/yyyy") ;
            if(!validateTestData(testData,2)) {
                return new LogMessage(false, "Test data invalid");
            }
            String[] data = testData.split(",");
            String date = data[0];
            String varName = data[1];
            Date parseDate =  dateFormat1.parse(date);
            Calendar cal =  Calendar.getInstance() ;
            cal.setTime(parseDate);
            String changeDate = dateFormat2.format(cal.getTime());
            TestPlan.getInstance().setStoreData(varName,changeDate);
            return new LogMessage(true, changeDate +" - Date  value is stored");
        }catch (Exception e){
            return new LogMessage(false,"Exception occur " + e.getMessage());
        }
    }

    public LogMessage storeCurrentDate(String varName){
        try {
            if (null == varName || varName.isEmpty()){
                return new LogMessage(false, "Invalid variable name");
            }
            DateTimeFormatter newFormat = DateTimeFormatter.ofPattern("MM-dd-yyyy");
            LocalDate date = TestPlan.getInstance().getCreationTime().toLocalDate();
            String currentDate = date.format(newFormat);
            currentDate = currentDate.replaceAll("-","/");
            TestPlan.getInstance().setStoreData(varName,currentDate);
            return new LogMessage(true, "Current Date store in " + varName);

        }catch (Exception ex){
            ex.printStackTrace();
            return new LogMessage(false, "Exception occur " +ex.getMessage());
        }
    }


}
