package test;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.openqa.selenium.WebDriver;
import test.coreModule.ExecuteTests;
import test.coreModule.MainController;
import test.driver.DriverFactory;

import java.io.FileInputStream;
import java.util.Properties;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void testingReadAndExecute() {
        WebDriver driver = DriverFactory.createDriver("chrome", false);
        ExecuteTests executeTests = new ExecuteTests(driver);
        executeTests.readAndExecute("sample","TC001_TC050");
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
    }
}
