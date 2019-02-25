package test.objectLocator;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public enum WebObjectSearchType implements WebObject {

    BY_ID {
        @Override
        public List<WebElement> findElement(WebDriver driver, String searchKey) {
           return driver.findElements(By.id(searchKey));
        }
    },
    BY_NAME {
        @Override
        public List<WebElement> findElement(WebDriver driver, String searchKey) {
            return driver.findElements(By.name(searchKey));
        }
    },
    BY_CLASSNAME{
        @Override
        public List<WebElement> findElement(WebDriver driver, String searchKey) {
            return driver.findElements(By.className(searchKey));

        }
    },
    TEST{
        @Override
        public List<WebElement> findElement(WebDriver driver, String searchKey) {
            return null ;
        }
    },
    BY_XPATH{
        @Override
        public List<WebElement> findElement(WebDriver driver, String searchKey) {
            return driver.findElements(By.xpath(searchKey));

        }
    },
    BY_LINKTEXT{
        @Override
        public List<WebElement> findElement(WebDriver driver, String searchKey) {
            return driver.findElements(By.linkText(searchKey));

        }
    },
}
