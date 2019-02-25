package test;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;
import test.Log.LogMessage;
import test.beforeTest.LeaseCreate;
import test.beforeTest.PropertyCreate;
import test.driver.DriverFactory;
import test.keywordScripts.UtilKeywordScript;
import test.utility.PropertyConfig;
import test.utility.ReadExcel;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertTrue;


public class DataCreate {
    private static WebDriver driver;
    private ClassLoader classLoader = getClass().getClassLoader();;

    @Test (priority = 1)
    public static void login(){
        driver = DriverFactory.createDriver("chrome", false);
        new UtilKeywordScript(driver).login(PropertyConfig.getLoginUrl(),PropertyConfig.getPropertyValue("userName"),PropertyConfig.getPropertyValue("password"),PropertyConfig.getPropertyValue("client"));

    }
    @Test (priority = 2)
    public void createProperty(){
        if(PropertyConfig.getPropertyValue("dataCreate").contains("all") || PropertyConfig.getPropertyValue("dataCreate").toLowerCase().contains("property")) {
            ReadExcel readExcel = new ReadExcel(classLoader.getResource("dataCreate/DataCreate.xlsx").getPath());
            List<Map> propertyRecords = readExcel.read("Property");

            PropertyCreate propertyCreate = new PropertyCreate(driver);
            for (Map propertyRecord : propertyRecords) {
                if (null == propertyRecord.get(PropertyConfig.EXECUTION_FLAG) || propertyRecord.get(PropertyConfig.EXECUTION_FLAG).toString().isEmpty() || !propertyRecord.get(PropertyConfig.EXECUTION_FLAG).toString().toLowerCase().equals("yes"))
                    continue;
                LogMessage logMessage = propertyCreate.createProperty(propertyRecord);
                assertTrue(logMessage.isPassed());
            }
        }
    }
    @Test (priority = 3)
    public void createLease(){
        if(PropertyConfig.getPropertyValue("dataCreate").contains("all") || PropertyConfig.getPropertyValue("dataCreate").toLowerCase().contains("lease")) {
            ReadExcel readExcel = new ReadExcel(classLoader.getResource("dataCreate/DataCreate.xlsx").getPath());
            List<Map> leaseRecords = readExcel.read("Lease");
            LeaseCreate leaseCreate = new LeaseCreate(driver);
            for (Map leaseRecord : leaseRecords) {
                if (null == leaseRecord.get(PropertyConfig.EXECUTION_FLAG) || leaseRecord.get(PropertyConfig.EXECUTION_FLAG).toString().isEmpty() || !leaseRecord.get(PropertyConfig.EXECUTION_FLAG).toString().toLowerCase().equals("yes"))
                    continue;
                LogMessage logMessage = leaseCreate.createLease(leaseRecord);
                assertTrue(logMessage.isPassed());
            }
        }

    }
    @Test (priority = 4)
    public void createSpace(){
        if(PropertyConfig.getPropertyValue("dataCreate").contains("all") || PropertyConfig.getPropertyValue("dataCreate").toLowerCase().contains("space")) {
            ReadExcel readExcel = new ReadExcel(classLoader.getResource("dataCreate/DataCreate.xlsx").getPath());
            List<Map> spaceRecords = readExcel.read("Space");
            LeaseCreate leaseCreate = new LeaseCreate(driver);
            for (Map spaceRecord : spaceRecords) {
                if (null == spaceRecord.get(PropertyConfig.EXECUTION_FLAG) || spaceRecord.get(PropertyConfig.EXECUTION_FLAG).toString().isEmpty() || !spaceRecord.get(PropertyConfig.EXECUTION_FLAG).toString().toLowerCase().equals("yes"))
                    continue;
                LogMessage logMessage = leaseCreate.createSpace(spaceRecord);
                assertTrue(logMessage.isPassed());
            }
        }

    }

    @Test (priority = 5)
    public void createRecurrentPayment(){
        if(PropertyConfig.getPropertyValue("dataCreate").contains("all") || PropertyConfig.getPropertyValue("dataCreate").toLowerCase().contains("recurringpayment")) {
            ReadExcel readExcel = new ReadExcel(classLoader.getResource("dataCreate/DataCreate.xlsx").getPath());
            List<Map> rpRecords = readExcel.read("RecurringPayment");
            LeaseCreate leaseCreate = new LeaseCreate(driver);
            for (Map rpRecord : rpRecords) {
                if (null == rpRecord.get(PropertyConfig.EXECUTION_FLAG) || rpRecord.get(PropertyConfig.EXECUTION_FLAG).toString().isEmpty() || !rpRecord.get(PropertyConfig.EXECUTION_FLAG).toString().toLowerCase().equals("yes"))
                    continue;
                LogMessage logMessage = leaseCreate.addRecurringPayment(rpRecord);
                assertTrue(logMessage.isPassed());
            }
        }

    }
}
