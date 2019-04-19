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




@RunWith(SpringRunner.class)
@SpringBootTest(classes = AmtTestFrameworkApplication.class)
public class TestCaseCreate {

    @Autowired
   private MainController mainController ;
    private static WebDriver driver;
    private ClassLoader classLoader = getClass().getClassLoader();;

    @Test
    public void login(){
        driver = DriverFactory.createDriver("chrome", false);
        new UtilKeywordScript(driver).login(PropertyConfig.getLoginUrl(),PropertyConfig.getPropertyValue("userName"),PropertyConfig.getPropertyValue("password"),PropertyConfig.getPropertyValue("client"));
        mainController.setDriver(driver);
        mainController.createAndExecute();

    }
    /*
    @Test(priority = 2)
    public  void runTestCase(){
        mainController.setDriver(driver);
        mainController.createAndExecute();

    }*/
}
