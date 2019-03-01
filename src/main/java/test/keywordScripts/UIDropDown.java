package test.keywordScripts;

import com.aventstack.extentreports.model.Log;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import test.Log.LogMessage;
import test.objectLocator.ObjectLocatorDataStorage;
import test.objectLocator.WebObject;
import test.objectLocator.WebObjectSearch;
import test.utility.PropertyConfig;

import java.util.List;
import java.util.Map;

public class UIDropDown {

    private WebDriver webDriver;
    public UIDropDown(WebDriver driver) {
        this.webDriver = driver ;
    }

    public UIDropDown(){

    }

    public LogMessage SelectItem(String objectLocator,String testData) {
        try {
            Map objectLocatorData = ObjectLocatorDataStorage.getObjectLocator(objectLocator);
            if(null != objectLocatorData.get(PropertyConfig.PARENT_LOCATOR)) {
                return SelectItem(objectLocator,(String) objectLocatorData.get(PropertyConfig.PARENT_LOCATOR),testData);
            }

            WebElement dropDownElement = WebObjectSearch.getWebElement(webDriver,objectLocator);
            if (null == dropDownElement)
                return new LogMessage(false," element is not found.");
            Select dropDown = new Select(dropDownElement);
               dropDown.selectByVisibleText(testData);
            return new LogMessage(true, "drop down item is selected") ;
        } catch (Exception ex) {
           return  new LogMessage(false, "exception occured: " + ex.getMessage()) ;
        }
    }


    public LogMessage SelectItem(String objectLocatorData,String dropDownObjectLocatorData,String testData) {
        try {
            WebElement dropDownElement = WebObjectSearch.getWebElement(webDriver,objectLocatorData);
            if (null == dropDownElement)
                return new LogMessage(false," drop down element is not found.");
            UtilKeywordScript.delay(3);

            dropDownElement.click();
            UtilKeywordScript.delay(3);
            List<WebElement> dropDownDataElements = webDriver.findElements(By.xpath(dropDownObjectLocatorData + "//*[text() = '" + testData + "']"));
            if (null == dropDownDataElements || dropDownDataElements.isEmpty())
                return new LogMessage(false," drop down list element is not found.");
            dropDownDataElements.get(dropDownDataElements.size()-1).click();
            //UIBase uiBase = new UIBase(webDriver);
           // uiBase.Click(dropDownDataElement);
            return new LogMessage(true, "drop down item is selected") ;
        } catch ( Exception ex) {
            ex.printStackTrace();
            return new LogMessage(false, "exception occured: " + ex.getMessage()) ;
        }
    }
    public LogMessage searchAndSelectItem(String objectLocatorData,String dropDownObjectLocatorData,String testData) {
        try {
            WebElement dropDownElement = WebObjectSearch.getWebElement(webDriver,objectLocatorData);
            if (null == dropDownElement)
                return new LogMessage(false," drop down element is not found.");
            dropDownElement.clear();
            UtilKeywordScript.delay(2);
            dropDownElement.sendKeys(testData);
            UtilKeywordScript.delay(3);
            List<WebElement> dropDownDataElements = webDriver.findElements(By.xpath(dropDownObjectLocatorData + "//*[text() = '" + testData + "']"));
            if (null == dropDownDataElements || dropDownDataElements.isEmpty())
                return new LogMessage(false," drop down list element is not found.");
            dropDownDataElements.get(dropDownDataElements.size()-1).click();
            //UIBase uiBase = new UIBase(webDriver);
            // uiBase.Click(dropDownDataElement);
            return new LogMessage(true, "drop down item is selected") ;
        } catch ( Exception ex) {
            ex.printStackTrace();
            return new LogMessage(false, "exception occured: " + ex.getMessage()) ;
        }
    }




}
