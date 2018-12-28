package test.objectLocator;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public interface WebObject {
    public WebElement findElement(WebDriver driver,String searchKey) ;
}
