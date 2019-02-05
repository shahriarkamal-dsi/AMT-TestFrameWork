package test.keywordScripts;

import com.aventstack.extentreports.model.Log;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import test.Log.LogMessage;
import test.objectLocator.WebObjectSearch;

public class UIDropDown {

    private WebDriver webDriver;
    public UIDropDown(WebDriver driver) {
        this.webDriver = driver ;
    }

    public UIDropDown(){

    }

    public LogMessage SelectItem(String objectLocator,String testData) {
        try {
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



}
