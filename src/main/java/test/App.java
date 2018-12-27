package test;

/**
 * Hello world!
 *
 */

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import test.driver.DriverFactory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class App 
{
    @Test
    public static void main( String[] args )  {
        String rootPath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
        System.out.println(rootPath);
        String appConfigPath = rootPath + "method.properties";
        String catalogConfigPath = rootPath + "catalog";

        Properties appProps = new Properties();
        try {
            appProps.load(new FileInputStream(appConfigPath));
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Properties catalogProps = new Properties();
        //catalogProps.load(new FileInputStream(catalogConfigPath));


        String appVersion = appProps.getProperty("version");
        System.out.println(appVersion);
       WebDriver driver = DriverFactory.createDriver("chrome",false);
       driver.navigate().to("http://www.calculator.net/");
        driver.manage().window().maximize();
    }

    public void test() {
        String rootPath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
        System.out.println(rootPath);
        String appConfigPath = rootPath + "method.properties";
        String catalogConfigPath = rootPath + "catalog";

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
