package test.keywordScripts;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import test.Log.LogMessage;
import test.objectLocator.WebObjectSearch;

import java.util.Map;

import static test.objectLocator.ObjectLocatorDataStorage.getObjectLocator;
import static test.objectLocator.WebObjectSearch.searchWebObject;

public class UIText {
    private WebDriver webDriver;
    public UIText(){

    }
    public UIText(WebDriver webDriver){
        this.webDriver = webDriver ;
    }

    public LogMessage SetText(String objectLocator, String textData){
        try {
            WebElement userWeb = WebObjectSearch.getWebElement(webDriver,objectLocator);
            if(null == userWeb )
                return new LogMessage(false,"webElement is not founding");
            userWeb.clear();
            userWeb.sendKeys(textData);
            return new LogMessage(true,"text is set up");
        } catch(Exception ex){
            ex.printStackTrace();
            return new LogMessage(false,"exception occured:- " + ex.getMessage());
        }
    }




}
