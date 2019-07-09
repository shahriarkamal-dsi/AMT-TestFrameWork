package test.keywordScripts;

import org.apache.xmlbeans.impl.xb.xsdschema.Public;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import test.Log.LogMessage;
import test.objectLocator.ObjectLocatorDataStorage;
import test.objectLocator.WebObjectSearch;
import test.utility.PropertyConfig;

import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.JavascriptExecutor;


import java.util.Map;
import java.util.Optional;
import java.util.logging.LogManager;

public class UIText {
    private WebDriver webDriver;

    public UIText(){

    }
    public UIText(WebDriver webDriver){
        this.webDriver = webDriver ;
    }

    public LogMessage SetText(String objectLocator, String textData){
        try {
            UIBase uiBase = new UIBase(webDriver);
            WebElement userWeb = WebObjectSearch.getWebElement(webDriver,objectLocator);
            if(null == userWeb )
                return new LogMessage(false,"Web element is not found");
            ((JavascriptExecutor) webDriver).executeScript("arguments[0].scrollIntoView(true);", userWeb);
            UtilKeywordScript.delay(PropertyConfig.ONE_SECOND);
            uiBase.Click(userWeb);
            userWeb.clear();
            UtilKeywordScript.delay(PropertyConfig.ONE_SECOND);
            userWeb.sendKeys(textData);
            return new LogMessage(true,textData + "- Text is set up");
        } catch(Exception ex){
            ex.printStackTrace();
            return new LogMessage(false,"Exception occurred:- " + ex.getMessage());
        }
    }
    public LogMessage SetText(WebElement element, String textData){
        try {
            UIBase uiBase = new UIBase(webDriver);
            if(null == element )
                return new LogMessage(false,"Web element is not found");
            ((JavascriptExecutor) webDriver).executeScript("arguments[0].scrollIntoView(true);", element);
            UtilKeywordScript.delay(PropertyConfig.ONE_SECOND);
            uiBase.Click(element);
            UtilKeywordScript.delay(PropertyConfig.ONE_SECOND);
            element.sendKeys(textData);
            return new LogMessage(true,"Text is set up");
        } catch(Exception ex){
            ex.printStackTrace();
            return new LogMessage(false,"Exception occurred:- " + ex.getMessage());
        }
    }


    public LogMessage WaitForVisibilityOfText(String objectLocator, String textData){
        try {
            String[] splittedTestData = textData.split(",");
            int time = PropertyConfig.WAIT_TIME_EXPLICIT_WAIT;
            if (splittedTestData.length >= 2) {
                time = Integer.parseInt(splittedTestData[1]);
                textData = splittedTestData[0];
            }

            Map objectLocatorData = ObjectLocatorDataStorage.getObjectLocator(objectLocator);
            String objectLocatorPath= (String) objectLocatorData.get(PropertyConfig.OBJECT_LOCATORS);
            WebDriverWait wait = new WebDriverWait(webDriver, time);
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(objectLocatorPath +"//*[contains(text(),'" + textData + "')]")));
            return new LogMessage(true, "Text is visible");
        }catch (Exception e){
            e.printStackTrace();
            return new LogMessage(false, "Exception occurred " + e.getMessage());
        }
    }

    public LogMessage WaitForInvisibilityOfText(String objectLocator, String textData){
        try {
            String[] splittedTestData = textData.split(",");
            int time = PropertyConfig.WAIT_TIME_EXPLICIT_WAIT;
            if (splittedTestData.length >= 2) {
                time = Integer.parseInt(splittedTestData[1]);
                textData = splittedTestData[0];
            }
            WebDriverWait wait = new WebDriverWait(webDriver, time);
            Map objectLocatorData = ObjectLocatorDataStorage.getObjectLocator(objectLocator);
            String objectLocatorPath= (String) objectLocatorData.get(PropertyConfig.OBJECT_LOCATORS);
            wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(objectLocatorPath + "//*[contains(text(),'" + textData + "')]")));
            return new LogMessage(true, "Text is invisible");
        }catch (Exception e){
            return new LogMessage(false, "Exception occurred " + e.getMessage());
        }
    }

    public LogMessage SetTextWithoutClear(String objectLocator, String textData){
        System.out.println("Test Data for second revision: " + textData);
        try {
            WebElement element = WebObjectSearch.getWebElement(webDriver,objectLocator);
            if(null == element )
                return new LogMessage(false,"Element is not found");
            ((JavascriptExecutor) webDriver).executeScript("arguments[0].scrollIntoView(true);", element);
            UtilKeywordScript.delay(PropertyConfig.ONE_SECOND);
            UIBase uiBase = new UIBase(webDriver);
            uiBase.Click(element);
            UtilKeywordScript.delay(PropertyConfig.ONE_SECOND);
            element.sendKeys(textData);
            return new LogMessage(true,textData + " - Text is set up");
        } catch(Exception ex){
            ex.printStackTrace();
            return new LogMessage(false,"Exception occurred:- " + ex.getMessage());
        }
    }

    public String getText(String objectLocator){
        try {
            WebElement webElement = WebObjectSearch.getWebElement(webDriver, objectLocator);
            if (null == webElement) {
                return "";
            }
            return Optional.ofNullable(webElement.getAttribute("textContent")).orElse("").trim();
        }catch (Exception e){
            return "";
        }
    }
    public LogMessage compareText(String objectLocator, String testData){
        try{
            String[] splittedTestData=testData.split(",");
            String attribute = getText(objectLocator);
            if(attribute.equals(splittedTestData[0].trim()))
                return new LogMessage(true, "Value is verified");
            else
                return new LogMessage(false, "Value is not verified");
        }catch (Exception e){
            return new LogMessage(false,"Exception occurred" + e.getMessage());
        }
    }
    public LogMessage compareTextWithComma(String objectLocator, String testData){
        try{

            String attribute = getText(objectLocator);
            if(attribute.equals(testData.trim()))
                return new LogMessage(true, testData + " Value is verified");
            else
                return new LogMessage(false, testData + " Value is not verified");
        }catch (Exception e){
            return new LogMessage(false,"Exception occurred" + e.getMessage());
        }
    }

    public LogMessage compareNumericText(String objectLocator, String testData){
        try{
            String[] splittedTestData=testData.split(",");
            String attribute = UtilKeywordScript.convertStringToNumber(getText(objectLocator));
            if(attribute.equals(UtilKeywordScript.convertStringToNumber(splittedTestData[0].trim())))
                return new LogMessage(true, "Value is verified");
            else
                return new LogMessage(false, "Value is not verified");
        }catch (Exception e){
            return new LogMessage(false,"Exception occured" + e.getMessage());
        }
    }

    public LogMessage compareNumberAfterIncrement(String testData){

         UtilKeywordScript utilKeywordScript = new UtilKeywordScript(webDriver);
        try{
            if(!utilKeywordScript.validateTestData(testData,3)) {
                return new LogMessage(false, "Test data invalid");
            }
            String[] splittedTestData = testData.split(",");
            Integer data = Integer.valueOf(splittedTestData[0].trim()) + Integer.valueOf(splittedTestData[1].trim());
            String numberToInc = UtilKeywordScript.convertStringToNumber(String.valueOf(data));
            String numberToCompare = UtilKeywordScript.convertStringToNumber(splittedTestData[2].trim());

            if (numberToCompare.equals(numberToInc)){
                return new LogMessage(true,"Value is verified");
            }
            else {
                return new LogMessage(false,"Value is not verified");
            }
        }catch (Exception e){
            return new LogMessage(false,"Exception occurred" + e.getMessage());
        }

    }


}
