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

    @Test
    public void login() throws  Exception {

        mainController.createAndExecute();
    }

}
