package test.keywordScripts;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import test.objectLocator.ObjectLocatorDataStorage;
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

    public void SetText(String objectLocator,String textData){
        try {

            Map objectLocatorData = getObjectLocator(objectLocator);
            WebElement userWeb = searchWebObject(this.webDriver,objectLocatorData);
            userWeb.sendKeys(textData);
        } catch(Exception ex){
            ex.printStackTrace();
        }
    }

}
