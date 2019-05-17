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

import java.util.List;
import java.util.ListIterator;

public enum DriverType implements Driver {

    FIREFOX {
        @Override
        public WebDriver getDriver(DesiredCapabilities desiredCapabilities, Boolean headless) {
            WebDriver driver = null;
            List<String> versions =   WebDriverManager.firefoxdriver().arch64().getVersions() ;

            ListIterator li = versions.listIterator(versions.size());
            while (li.hasPrevious()){
                try {
                    String version = li.previous().toString();
                    WebDriverManager.firefoxdriver().version(version).setup() ;
                    FirefoxOptions options = new FirefoxOptions();
                    if (headless) {
                        //options.addArguments("--headless", "--disable-gpu", "--window-size=1920,1200","--ignore-certificate-errors", "--silent");
                    }else {
                        options.setHeadless(headless);
                    }
                    driver = new FirefoxDriver(options);

                    if(null != driver)
                        break;
                } catch (Exception ex) {
                    // ex.printStackTrace();
                }
            }
            return  driver ;
        }
    },
    CHROME {
        @Override
        public WebDriver getDriver(DesiredCapabilities desiredCapabilities, Boolean headless) {
            WebDriver driver = null;
            List<String> versions =   WebDriverManager.chromedriver().arch64().getVersions() ;
            ListIterator li = versions.listIterator(versions.size());
            while (li.hasPrevious()){
                try {
                    String version = li.previous().toString();
                    WebDriverManager.chromedriver().version(version).setup() ;
                    ChromeOptions options = new ChromeOptions();
                    if (headless) {
                        options.addArguments("--headless", "--disable-gpu", "--window-size=1920,1200","--ignore-certificate-errors", "--silent");
                    }else {
                        options.setHeadless(headless);
                    }
                    driver = new ChromeDriver(options);

                    if(null != driver)
                        break;
                } catch (Exception ex) {
                    // ex.printStackTrace();
                }
            }
            return  driver ;
        }
    },
    EDGE {
        @Override
        public WebDriver getDriver(DesiredCapabilities desiredCapabilities, Boolean headless) {
            List<String> versions =   WebDriverManager.edgedriver().arch64().getVersions() ;
            WebDriver driver = null;
            ListIterator li = versions.listIterator(versions.size());
            while (li.hasPrevious()){
                try {
                    String version = li.previous().toString();
                    WebDriverManager.edgedriver().version(version).setup() ;
                    driver = new EdgeDriver();
                    if(null != driver)
                        break;
                } catch (Exception ex) {
                    // ex.printStackTrace();
                }
            }
            return  driver ;
        }
    },
    IE {
        @Override
        public WebDriver getDriver(DesiredCapabilities desiredCapabilities, Boolean headless) {

            WebDriver driver = null;
            WebDriverManager.iedriver().arch32().setup();
            driver = new InternetExplorerDriver();

            return  driver ;
        }
    },

}
