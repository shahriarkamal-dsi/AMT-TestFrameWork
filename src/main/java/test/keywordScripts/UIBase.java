package test.keywordScripts;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import test.Log.LogMessage;
import test.objectLocator.ObjectLocatorDataStorage;
import test.objectLocator.WebObjectSearch;
import test.utility.PropertyConfig;

import java.util.Map;
import java.util.concurrent.TimeUnit;


public class UIBase {

    private WebDriver webDriver;

    public UIBase(WebDriver driver) {
        this.webDriver = driver ;
    }

    public UIBase(){

    }

    public LogMessage Click(WebElement webElement) {
        try {
            if(null == webElement)
                return new LogMessage(false,"web element not fouding");
            try
            {
                webElement.click();
            } catch(Exception ex) {
                ex.printStackTrace();
                JavascriptExecutor executor = (JavascriptExecutor) webDriver;
                executor.executeScript("arguments[0].click();",webElement);
            }
            return new LogMessage(true,"web element is clicked");

        }catch ( Exception ex) {
            ex.printStackTrace();
            return new LogMessage(false,"exception occured:- " + ex.getMessage());
        }
    }


    public LogMessage  Click(String objectLocatorData) {
        return Click( WebObjectSearch.getWebElement(webDriver,objectLocatorData));
    }

    public LogMessage  navigateToAPage(String testData) {
        try {
            webDriver.navigate().to(testData);
            webDriver.manage().window().maximize();
            return new LogMessage(true,"page is navigated");
            // ex.ExecuteScript("arguments[0].click();", elementToClick);

        }catch(Exception ex){
            ex.printStackTrace();
            return new LogMessage(false,"exception occured:- " + ex.getMessage());
        }
    }

    public LogMessage VerifyPageLoadedTrue(String objectlocator) {
        try {
            Map objectLocatorData = ObjectLocatorDataStorage.getObjectLocator(objectlocator);
            String objectData =  (String) objectLocatorData.get(PropertyConfig.OBJECT_LOCATORS);
            if(webDriver.getCurrentUrl().contains(objectData)) {
                return new LogMessage(true,"page is loaded successfully");
            } else {
                webDriver.manage().timeouts().implicitlyWait(PropertyConfig.WAIT_TIME_SECONDS, TimeUnit.SECONDS) ;
                if(webDriver.getCurrentUrl().contains(objectData))
                    return new LogMessage(true,"page is loaded successfully");
                 else
                    return new LogMessage(false,"page is not loaded");

            }
        } catch (Exception ex) {
            return new LogMessage(false,"exception occured:- " + ex.getMessage());
        }
    }
    public LogMessage ClickDbClickRClick(WebElement element, String action) {
        try {
            action = action.toUpperCase();
            boolean clikced = false ;
            if(action.equals("CLICK")) {
                Actions act = new Actions(webDriver);
               // act.click(element).perform();
                act.doubleClick(element).perform();
               // element.click();
                clikced = true ;
            } else if(action.equals("DBLCLICK") || action.equals("DOUBLECLICK") || action.equals("DOUBLE CLICK") ) {
                Actions act = new Actions(webDriver);
                act.doubleClick(element).perform();
                clikced = true ;
            } else if(action.equals("RIGHTCLICK") || action.equals("RIGHT CLICK")) {
                Actions act = new Actions(webDriver);
                act.contextClick(element).perform();
                clikced = true ;
            }
            if(clikced)
                return  new LogMessage(true,"element clicked");
            else
                return new LogMessage(false, "action unknown");

        } catch ( Exception ex) {
            ex.printStackTrace();
            return  new LogMessage(false,"exception occured: " + ex.getMessage());
        }
    }

    public LogMessage Delay(String delayTime) {
        try {
            Thread.sleep(7*1000);
            //if(null != webDriver)
              //  webDriver.manage().timeouts().implicitlyWait(Integer.valueOf("10"), TimeUnit.SECONDS) ;
            return new LogMessage(true,"wait for delay succesffull");
        } catch(Exception ex) {
            ex.printStackTrace();
            return new LogMessage(false,  "excepption occured: " + ex.getMessage());
        }
    }

    public LogMessage VerifyVisibleOnScreenFalse(String objectLocatorData) {
        try {
               WebElement element = WebObjectSearch.getWebElement(webDriver,objectLocatorData);
               if(null == element)
                   return  new LogMessage(false, "element is not found");
               String logMessage = element.isDisplayed() == true ? "element is displayed " : "element is not displayed " ;
               return new LogMessage( !element.isDisplayed(),logMessage);
        } catch ( Exception ex) {
            ex.printStackTrace();
            return  new LogMessage(false, "exception occured " + ex.getMessage());
        }
    }

    public LogMessage VerifyVisibleOnScreenTrue(String objectLocatorData) {
        try {
            WebElement element = WebObjectSearch.getWebElement(webDriver,objectLocatorData);
            if(null == element)
                return  new LogMessage(false, "element is not found");
            String logMessage = element.isDisplayed() == true ? "element is displayed " : "element is not displayed" ;
            return new LogMessage( element.isDisplayed(),logMessage);
        } catch ( Exception ex) {
            ex.printStackTrace();
            return  new LogMessage(false, "exception occured " + ex.getMessage());
        }
    }



    public LogMessage test_click(String objectLocator){
        return new LogMessage(true,objectLocator);
    }
}
