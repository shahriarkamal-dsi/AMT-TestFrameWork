package test;

import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import test.coreModule.MainController;
import test.driver.DriverFactory;
import test.keywordScripts.UtilKeywordScript;
import test.utility.PropertyConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import java.net.URL;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;




@RunWith(SpringRunner.class)
@SpringBootTest(classes = AmtTestFrameworkApplication.class)
public class TestCaseCreate {

    @Autowired
   private MainController mainController ;
    private static WebDriver driver;
    private ClassLoader classLoader = getClass().getClassLoader();;


    @Test
    public void runInChrome(){
        driver = DriverFactory.createDriver("chrome", false);
        //new UtilKeywordScript(driver).login(PropertyConfig.getLoginUrl(),PropertyConfig.getPropertyValue("userName"),PropertyConfig.getPropertyValue("password"),PropertyConfig.getPropertyValue("client"));
        mainController.setDriver(driver);
        mainController.createAndExecute();
        driver.quit();
    }
    public void login() throws  Exception {
        //driver = DriverFactory.createDriver("chrome", false);

        /*
         String username = "ITLicensing%40amtdirect.com"; // Your username
         String authkey = "u21a9b4e09bad6f5";  // Your authkey
         String testScore = "unset";

        DesiredCapabilities caps = new DesiredCapabilities();


        caps.setCapability("name", "Basic Test Example");
        caps.setCapability("build", "1.0");
        caps.setCapability("browserName", "Chrome");
        caps.setCapability("version", "74");
        caps.setCapability("platform", "Windows 10");
        caps.setCapability("screenResolution", "1366x768");


        RemoteWebDriver driver = new RemoteWebDriver(new URL("http://" + username + ":" + authkey +"@hub.crossbrowsertesting.com:80/wd/hub"), caps);
        System.out.println(driver.getSessionId());
        */
        //new UtilKeywordScript(driver).login(PropertyConfig.getLoginUrl(),PropertyConfig.getPropertyValue("userName"),PropertyConfig.getPropertyValue("password"),PropertyConfig.getPropertyValue("client"));
        //  mainController.setDriver(driver);
        mainController.createAndExecute();
        //    driver.quit();
    }

    @Test
    public void runInFirefox(){
        driver = DriverFactory.createDriver("FIREFOX", false);
        //new UtilKeywordScript(driver).login(PropertyConfig.getLoginUrl(),PropertyConfig.getPropertyValue("userName"),PropertyConfig.getPropertyValue("password"),PropertyConfig.getPropertyValue("client"));
        mainController.setDriver(driver);
        mainController.createAndExecute();
        driver.quit();
    }

    @Test
    public void runInEdge(){
        driver = DriverFactory.createDriver("EDGE", false);
        //new UtilKeywordScript(driver).login(PropertyConfig.getLoginUrl(),PropertyConfig.getPropertyValue("userName"),PropertyConfig.getPropertyValue("password"),PropertyConfig.getPropertyValue("client"));
        mainController.setDriver(driver);
        mainController.createAndExecute();
        driver.quit();

    }

    @Test
    public void runInIE(){
        driver = DriverFactory.createDriver("IE", false);
        //new UtilKeywordScript(driver).login(PropertyConfig.getLoginUrl(),PropertyConfig.getPropertyValue("userName"),PropertyConfig.getPropertyValue("password"),PropertyConfig.getPropertyValue("client"));
        mainController.setDriver(driver);
        mainController.createAndExecute();
        driver.quit();
    }
}
