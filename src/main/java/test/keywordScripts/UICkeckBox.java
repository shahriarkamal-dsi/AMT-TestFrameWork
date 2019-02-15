package test.keywordScripts;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import test.Log.LogMessage;
import test.objectLocator.WebObjectSearch;

public class UICkeckBox {
    private WebDriver webDriver;
    public UICkeckBox(WebDriver driver) {
        this.webDriver = driver ;
    }

    public UICkeckBox(){

    }

    public LogMessage CheckChkBox(String objectLocator){
        try{
            WebElement webElement = WebObjectSearch.getWebElement(webDriver,objectLocator);
            webElement.click();
            return new LogMessage(true,"CheckBox click successfully");
        }catch (Exception e){
            e.printStackTrace();
            return new LogMessage(false,"Exception occurred");
        }
    }
}
