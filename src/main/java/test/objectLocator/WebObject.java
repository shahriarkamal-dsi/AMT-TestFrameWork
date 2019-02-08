package test.objectLocator;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public interface WebObject {
    public List<WebElement> findElement(WebDriver driver, String searchKey) ;
}
