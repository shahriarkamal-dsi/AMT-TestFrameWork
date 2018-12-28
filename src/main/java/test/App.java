package test;

/**
 * Hello world!
 *
 */

import org.junit.Test;
import org.openqa.selenium.WebDriver;
import test.driver.DriverFactory;

import java.io.FileInputStream;
import java.io.IOException;
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
        driver.navigate().to("http://www.calculator.net/");
        driver.manage().window().maximize();

    }
}
