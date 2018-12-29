package test;

/**
 * Hello world!
 *
 */

import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import test.driver.DriverFactory;
import test.objectLocator.ObjectLocatorDataStorage;
import test.objectLocator.WebObjectSearch;
import test.objectLocator.WebObjectSearchType;
import test.utility.PropertyConfig;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Map;
import java.util.Properties;

public class App 
{

    public void test() {
        //String rootPath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
        ClassLoader classLoader = getClass().getClassLoader();
      //  System.out.println(rootPath);
        String appConfigPath = classLoader.getResource("method.properties").getPath();

        Properties appProps = new Properties();
        try {
            appProps.load(new FileInputStream(appConfigPath));
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Properties catalogProps = new Properties();
        //catalogProps.load(new FileInputStream(catalogConfigPath));


        String browser = appProps.getProperty("browser");
        String headless = appProps.getProperty("headless");
        boolean hdless =  headless.equals("true") ? true : false ;
       // System.out.println(appVersion);
        WebDriver driver = DriverFactory.createDriver(browser, hdless);
        driver.navigate().to("https://qa2.testamt.com/");
        driver.manage().window().maximize();
            ObjectLocatorDataStorage objectLocatorDataStorage = new ObjectLocatorDataStorage();
            try {
                Map userName = objectLocatorDataStorage.getObjectLocator("sample.login.userName");
                Map password = objectLocatorDataStorage.getObjectLocator("sample.login.password");
                Map client = objectLocatorDataStorage.getObjectLocator("sample.login.client");
                Map loginButton = objectLocatorDataStorage.getObjectLocator("sample.login.loginButton");
                WebElement userWeb = WebObjectSearch.searchWebObject(driver,userName);
                WebElement passWeb = WebObjectSearch.searchWebObject(driver,password);
                WebElement clntWeb = WebObjectSearch.searchWebObject(driver,client);
                WebElement lognBtnWeb = WebObjectSearch.searchWebObject(driver,loginButton);
                userWeb.sendKeys("AmtAutomation");
                passWeb.sendKeys("2018Automate!");
                clntWeb.sendKeys("201481");
                lognBtnWeb.click();
                //driver.close();
               // System.out.println(webElement);
            } catch (Exception e) {
                e.printStackTrace();
            }
    }
}
