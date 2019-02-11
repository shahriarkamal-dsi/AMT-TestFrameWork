package test;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.openqa.selenium.WebDriver;
import sun.rmi.runtime.Log;
import test.Log.LogMessage;
import test.beforeTest.LeaseCreate;
import test.beforeTest.PropertyCreate;
import test.coreModule.ExecuteTests;
import test.coreModule.MainController;
import test.driver.DriverFactory;
import test.keywordScripts.UIBase;
import test.keywordScripts.UIText;
import test.keywordScripts.UtilKeywordScript;
import test.utility.ReadExcel;

import java.io.FileInputStream;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    /*
    @Test
    public void testingReadAndExecute() {
        WebDriver driver = DriverFactory.createDriver("chrome", false);
        ExecuteTests executeTests = new ExecuteTests(driver);
        executeTests.readAndExecute("Fasb2","TC001_TC050");
    }
    @Test
    public void testingCreateAndExecute() {
        try {
            ClassLoader classLoader = getClass().getClassLoader();
            String appConfigPath = classLoader.getResource("default.properties").getPath();
            Properties appProps = new Properties();
            appProps.load(new FileInputStream(appConfigPath));
            String browser = appProps.getProperty("browser");
            String headless = appProps.getProperty("headless");
            boolean hdless = headless.equals("true") ? true : false;
            // System.out.println(appVersion);
            WebDriver driver = DriverFactory.createDriver(browser, hdless);
            MainController mc = new MainController(driver);
            mc.createAndExecute();
        }catch (Exception ex) {
            ex.printStackTrace();
        }
    }*/

    @Test
    public void propertyCreate() {
        ClassLoader classLoader = getClass().getClassLoader();
        ReadExcel readExcel = new ReadExcel(classLoader.getResource("dataCreate/PropertyCreate.xlsx").getPath());
        List<Map> records = readExcel.read("Property");
        WebDriver driver = DriverFactory.createDriver("chrome", false);
        UIBase uiBase = new UIBase(driver) ;
        UIText uiText = new UIText(driver) ;
        uiBase.navigateToAPage("https://qa4.testamt.com/");
        uiText.SetText("Common.Login.txtUserName","saimaalam01");
        uiText.SetText("Common.Login.txtPassword","amtDirect01!");
        uiText.SetText("Common.Login.txtClientID","201483");
        uiBase.Click("Common.Login.btnLogIn");
        PropertyCreate propertyCreate = new PropertyCreate(driver);
        for(Map record : records) {
            propertyCreate.createProperty(record);
        }
        UtilKeywordScript.closeAllPages(driver);
    }

    @Test
    public void leaseCreate(){
        ClassLoader classLoader = getClass().getClassLoader();
        ReadExcel readExcel = new ReadExcel(classLoader.getResource("dataCreate/PropertyCreate.xlsx").getPath());
        List<Map> records = readExcel.read("Lease");
        WebDriver driver = DriverFactory.createDriver("chrome", false);
        UIBase uiBase = new UIBase(driver) ;
        UIText uiText = new UIText(driver) ;
        uiBase.navigateToAPage("https://qa4.testamt.com/");
        uiText.SetText("Common.Login.txtUserName","saimaalam01");
        uiText.SetText("Common.Login.txtPassword","amtDirect01!");
        uiText.SetText("Common.Login.txtClientID","201483");
        uiBase.Click("Common.Login.btnLogIn");

        LeaseCreate leaseCreate = new LeaseCreate(driver);
       LogMessage logMessage =  leaseCreate.createLease(records.get(0));
       assertTrue(logMessage.isPassed());
      //  UtilKeywordScript utilKeywordScript = new UtilKeywordScript(driver);
      //  utilKeywordScript.redirectHomePage();
        //UtilKeywordScript.closeAllPages(driver);
    }
}
