package test;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.openqa.selenium.WebDriver;
import test.coreModule.ExecuteTests;
import test.coreModule.MainController;
import test.driver.DriverFactory;
import test.keywordScripts.UIBase;
import org.openqa.selenium.chrome.ChromeDriver;

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
        WebDriver driver = DriverFactory.createDriver("firefox", false);
        MainController mc = new MainController(driver);
        mc.createAndExecute();
    }
}
