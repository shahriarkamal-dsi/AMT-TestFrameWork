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

            JavascriptExecutor js = (JavascriptExecutor) webDriver;
            js.executeScript("arguments[0].scrollIntoView();", dropDownElement);
            Thread.sleep(3*1000);
            dropDownElement.click();
            Thread.sleep(3*1000);
            WebElement dropDownDataElement = webDriver.findElement(By.xpath(dropDownObjectLocatorData + "/*[text() = '" + testData + "']"));
            if (null == dropDownDataElement)
                return new LogMessage(false," drop down list element is not found.");
            dropDownDataElement.click();
            return new LogMessage(true, "drop down item is selected") ;
        } catch ( Exception ex) {
            ex.printStackTrace();
            return new LogMessage(false, "exception occured: " + ex.getMessage()) ;
        }
    }



}
