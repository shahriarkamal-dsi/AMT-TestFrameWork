package test.keywordScripts;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import test.Log.LogMessage;
import test.objectLocator.ObjectLocatorDataStorage;
import test.objectLocator.WebObjectSearch;
import test.utility.PropertyConfig;

import java.util.ArrayList;
import java.util.List;
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
        }catch(Exception ex){
            ex.printStackTrace();
            return new LogMessage(false,"exception occured:- " + ex.getMessage());
        }
    }

    public LogMessage VerifyPageLoadedTrue(String objectlocator) {
        try {
            UtilKeywordScript.switchLastTab(webDriver);

            Map objectLocatorData = ObjectLocatorDataStorage.getObjectLocator(objectlocator);
            String objectData =  (String) objectLocatorData.get(PropertyConfig.OBJECT_LOCATORS);
            String[] splittedObjectData= objectData.split("(\\*)|(\\s+)");
            String matchString="(.*)";
            for(String split:splittedObjectData){
                matchString=matchString+split+"(.*)";
            }
            if(webDriver.getCurrentUrl().matches(matchString)) {
                return new LogMessage(true,"page is loaded successfully");
            } else {
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
                element.click();
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
            Thread.sleep(Integer.valueOf(delayTime).intValue()*1000);
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
            WebDriverWait wait = new WebDriverWait(webDriver, PropertyConfig.WAIT_TIME_EXPLICIT_WAIT);
            wait.until(ExpectedConditions.visibilityOf(element));
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

    public LogMessage WaitingForPageLoad(){
        try{
            WebDriverWait wait = new WebDriverWait(webDriver, PropertyConfig.WAIT_TIME_EXPLICIT_WAIT);
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("splashScr")));
            wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("splashScr")));
            return new LogMessage(true,"Page load successfully");
        }catch (Exception e){
            e.printStackTrace();
            return new LogMessage(false, "Exception occurred during loading page");
        }
    }


    public LogMessage WaitingForSuccessfullPopup(){
        try{
            WebDriverWait wait = new WebDriverWait(webDriver, PropertyConfig.WAIT_TIME_EXPLICIT_WAIT);
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(@class,'alert-success')]")));
            wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[contains(@class,'alert-success')]")));
            return new LogMessage(true,"Page load successfully");
        }catch (Exception e){
            e.printStackTrace();
            return new LogMessage(false, "Exception occurred during loading page");
        }
    }
    public LogMessage VerifyEnabledTrue(String objectLocatorData){
        try{
            WebElement element = WebObjectSearch.getWebElement(webDriver,objectLocatorData);
            if(null == element)
                return  new LogMessage(false, "Element is not found");
            String logMessage = element.isEnabled() ? "Element is enabled " : "Element is not enabled" ;
            return new LogMessage(element.isEnabled(), logMessage);

        }catch (Exception e)
        {
            e.printStackTrace();
            return new LogMessage(false, "Element is not clickable");
        }
    }


    public LogMessage compareGreaterThanValue(String value,String compareTovalue ) {
        try {
            Integer num1 = Integer.valueOf(value) ;
            Integer num2 = Integer.valueOf(compareTovalue) ;
           return  num1.compareTo(num1) == 1 ? new LogMessage(true,  "given value greater than  " + compareTovalue) :  new LogMessage(false,  "given value is not greater than  " + compareTovalue) ;
        } catch (Exception ex) {
            ex.printStackTrace();
            return new LogMessage(false, "exception occured " + ex.getMessage());

        }

    }
    public LogMessage refreshPage(){
        try{
            webDriver.navigate().refresh();
            return new LogMessage(true,"Page refreshed");
        }catch (Exception e){
            return new LogMessage(false,"Exception occur " + e.getMessage());
        }
    }


}
