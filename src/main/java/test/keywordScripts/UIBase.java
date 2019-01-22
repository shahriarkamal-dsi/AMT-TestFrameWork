package test.keywordScripts;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import test.Log.LogMessage;

import java.util.Map;

import static test.objectLocator.ObjectLocatorDataStorage.getObjectLocator;
import static test.objectLocator.WebObjectSearch.searchWebObject;

public class UIBase {

    private WebDriver webDriver;

    public UIBase(WebDriver driver) {
        this.webDriver = driver ;
    }

    public UIBase(){

    }


    public LogMessage  click(String objectLocator) {
        try {
            WebElement webElement = getWebElement(objectLocator);
            if(null == webElement)
                return new LogMessage(false,"web element not fouding");
            webElement.click();
           // JavascriptExecutor ex = (JavascriptExecutor) webDriver;
            //ex.executeAsyncScript("arguments[0].click();",webElement);
            return new LogMessage(true,"web element is clicked");
           // ex.ExecuteScript("arguments[0].click();", elementToClick);

        }catch(Exception ex){
            ex.printStackTrace();
            return new LogMessage(false,"exception occured.");
        }
    }

    public LogMessage  navigateToAPage(String testData) {
        try {
            webDriver.navigate().to(testData);
            webDriver.manage().window().maximize();
            return new LogMessage(true,"page is navigated");
            // ex.ExecuteScript("arguments[0].click();", elementToClick);

        }catch(Exception ex){
            ex.printStackTrace();
            return new LogMessage(false,"exception occured.");
        }
    }


    private WebElement getWebElement(String objectLocator) {
        try {
            Map objectLocatorData = getObjectLocator(objectLocator);
            WebElement userWeb = searchWebObject(this.webDriver, objectLocatorData);
            return userWeb;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public LogMessage test_click(String objectLocator){
        return new LogMessage(true,objectLocator);
    }
}
