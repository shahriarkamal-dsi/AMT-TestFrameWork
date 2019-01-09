package test.objectLocator;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public enum WebObjectSearchType implements WebObject {
    BY_ID {
        @Override
        public WebElement findElement(WebDriver driver, String searchKey) {
           return driver.findElement(By.id(searchKey));
        }
    },
    BY_CLASSNAME{
        @Override
        public WebElement findElement(WebDriver driver, String searchKey) {
            return driver.findElement(By.className(searchKey));

        }
    },
    TEST{
        @Override
        public WebElement findElement(WebDriver driver, String searchKey) {
            return null ;
        }
    },
    BY_XPATH{
        @Override
        public WebElement findElement(WebDriver driver, String searchKey) {
            return driver.findElement(By.xpath(searchKey));

        }
    },
}
