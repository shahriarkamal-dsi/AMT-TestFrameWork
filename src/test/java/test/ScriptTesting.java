package test;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;
import test.coreModule.ExecuteTests;
import test.coreModule.MainController;
import test.driver.DriverFactory;
import test.keywordScripts.UtilKeywordScript;
import test.utility.PropertyConfig;

public class ScriptTesting {
    private static WebDriver driver;
    private ClassLoader classLoader = getClass().getClassLoader();;

    @Test(priority = 1)
    public static void login(){
        driver = DriverFactory.createDriver("chrome", false);
        new UtilKeywordScript(driver).login(PropertyConfig.getLoginUrl(),PropertyConfig.getPropertyValue("userName"),PropertyConfig.getPropertyValue("password"),PropertyConfig.getPropertyValue("client"));

    }

    @Test(priority = 2)
    public void run () {
        ExecuteTests executeTests = new ExecuteTests(driver);
        executeTests.readAndExecute("Fasb","TC001_TC050");

    }
}
