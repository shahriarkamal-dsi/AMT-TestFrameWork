package test;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.openqa.selenium.WebDriver;
import test.Log.LogMessage;
import test.beforeTest.LeaseCreate;
import test.beforeTest.PropertyCreate;
import test.beforeTest.SpaceCreate;
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
        ReadExcel readExcel = new ReadExcel(classLoader.getResource("dataCreate/DataCreate.xlsx").getPath());
        List<Map> records = readExcel.read("Property");
        WebDriver driver = DriverFactory.createDriver("chrome", false);
        UtilKeywordScript utilKeywordScript = new UtilKeywordScript(driver);
        utilKeywordScript.login("https://qa4.testamt.com/","saimaalam01","amtDirect01!", "201483");
        PropertyCreate propertyCreate = new PropertyCreate(driver);
        for(Map record : records) {
            propertyCreate.createProperty(record);
        }
        UtilKeywordScript.closeAllPages(driver);

    }

    @Test
    public void leaseCreate(){

        ClassLoader classLoader = getClass().getClassLoader();
        ReadExcel readExcel = new ReadExcel(classLoader.getResource("dataCreate/DataCreate.xlsx").getPath());
        List<Map> records = readExcel.read("Lease");
        WebDriver driver = DriverFactory.createDriver("chrome", false);
        UtilKeywordScript utilKeywordScript = new UtilKeywordScript(driver);
        utilKeywordScript.login("https://qa4.testamt.com/","saimaalam01","amtDirect01!", "201483");
        LeaseCreate leaseCreate = new LeaseCreate(driver);
        for(Map record : records){
            LogMessage logMessage =  leaseCreate.createLease(record);
            assertTrue(logMessage.isPassed());
        }
        UtilKeywordScript.closeAllPages(driver);

    }

    @Test
    public void createSpace(){

        ClassLoader classLoader = getClass().getClassLoader();
        ReadExcel readExcel = new ReadExcel(classLoader.getResource("dataCreate/DataCreate.xlsx").getPath());
        List<Map> records = readExcel.read("Space");
        WebDriver driver = DriverFactory.createDriver("chrome", false);
        UtilKeywordScript utilKeywordScript = new UtilKeywordScript(driver);
        utilKeywordScript.login("https://qa4.testamt.com/","saimaalam01","amtDirect01!", "201483");
        SpaceCreate spaceCreate = new SpaceCreate(driver);
        for(Map record : records){
            LogMessage logMessage =  spaceCreate.createSpace(record);
            assertTrue(logMessage.isPassed());
        }
        UtilKeywordScript.closeAllPages(driver);

    }

}
