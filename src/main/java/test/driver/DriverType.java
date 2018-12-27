package test.driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

public enum DriverType implements Driver {
    FIREFOX {
        @Override
        public WebDriver getDriver(DesiredCapabilities desiredCapabilities, Boolean headless) {
            WebDriverManager.firefoxdriver().setup();
            FirefoxOptions options = new FirefoxOptions();
            options.setHeadless(headless);
            WebDriver driver = new FirefoxDriver(options);
            return  driver ;
        }
    },
    CHROME {
        @Override
        public WebDriver getDriver(DesiredCapabilities desiredCapabilities, Boolean headless) {
            WebDriverManager.chromedriver().setup();
            ChromeOptions options = new ChromeOptions();
            options.setHeadless(headless);
            WebDriver driver = new ChromeDriver(options);
            return  driver ;
        }
    },
    EDGE {
        @Override
        public WebDriver getDriver(DesiredCapabilities desiredCapabilities, Boolean headless) {
            WebDriverManager.edgedriver().setup();
            //ChromeOptions options = new Edge();
          //  options.setHeadless(headless);
            WebDriver driver = new EdgeDriver();
            return  driver ;
        }
    },
    IE {
        @Override
        public WebDriver getDriver(DesiredCapabilities desiredCapabilities, Boolean headless) {
            WebDriverManager.iedriver().setup();
           // ChromeOptions options = new  I();
           // options.setHeadless(headless);
            WebDriver driver = new InternetExplorerDriver();
            return  driver ;
        }
    },

}
