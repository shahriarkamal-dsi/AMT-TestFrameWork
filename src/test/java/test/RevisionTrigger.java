package test;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;
import test.coreModule.MainController;
import test.driver.DriverFactory;
import test.keywordScripts.UtilKeywordScript;
import test.utility.PropertyConfig;

import java.io.FileInputStream;
import java.util.Properties;


public class RevisionTrigger {
    private static WebDriver driver;

    @Test (priority = 1)
    public static void login(){
        driver = DriverFactory.createDriver("chrome", false);
        new UtilKeywordScript(driver).login(PropertyConfig.getLoginUrl(),PropertyConfig.getPropertyValue("userName"),PropertyConfig.getPropertyValue("password"),PropertyConfig.getPropertyValue("client"));

    }
    @Test (priority = 2)
    public void testingCreateAndExecute() {
        try {
            ClassLoader classLoader = getClass().getClassLoader();
            String appConfigPath = classLoader.getResource("default.properties").getPath();
            Properties appProps = new Properties();
            appProps.load(new FileInputStream(appConfigPath));
           // String browser = appProps.getProperty("browser");
          //  String headless = appProps.getProperty("headless");
           // boolean hdless = headless.equals("true") ? true : false;
            // System.out.println(appVersion);
           // WebDriver driver = DriverFactory.createDriver(browser, hdless);
            MainController mc = new MainController(driver);
            mc.createAndExecute();
        }catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
