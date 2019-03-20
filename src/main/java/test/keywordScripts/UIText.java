package test.keywordScripts;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import test.Log.LogMessage;
import test.objectLocator.WebObjectSearch;
import test.utility.PropertyConfig;

import java.util.Map;

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
            UtilKeywordScript.delay(1);
            userWeb.click();
            UtilKeywordScript.delay(1);
            userWeb.sendKeys(textData);
            return new LogMessage(true,"text is set up");
        } catch(Exception ex){
            ex.printStackTrace();
            return new LogMessage(false,"exception occured:- " + ex.getMessage());
        }
    }

}
