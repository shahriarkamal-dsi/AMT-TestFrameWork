package test;
import org.testng.annotations.Test;
import org.junit.BeforeClass;
import org.openqa.selenium.WebDriver;
import test.Log.LogMessage;
import test.beforeTest.LeaseCreate;
import test.beforeTest.PropertyCreate;
import test.driver.DriverFactory;
import test.keywordScripts.UIBase;
import test.keywordScripts.UIText;

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
        UIBase uiBase = new UIBase(driver) ;
        UIText uiText = new UIText(driver) ;
        uiBase.navigateToAPage("https://qa4.testamt.com/");
        uiText.SetText("Common.Login.txtUserName","saimaalam01");
        uiText.SetText("Common.Login.txtPassword","amtDirect01!");
        uiText.SetText("Common.Login.txtClientID","201483");
        uiBase.Click("Common.Login.btnLogIn");

    }
    @Test (priority = 2)
    public void createProperty(){
        if(PropertyConfig.getPropertyValue("dataCreate").contains("all") || PropertyConfig.getPropertyValue("dataCreate").toLowerCase().contains("property")) {
            ReadExcel readExcel = new ReadExcel(classLoader.getResource("dataCreate/PropertyCreate.xlsx").getPath());
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
            ReadExcel readExcel = new ReadExcel(classLoader.getResource("dataCreate/PropertyCreate.xlsx").getPath());
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
}
