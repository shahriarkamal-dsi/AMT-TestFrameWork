package test.objectLocator;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import test.utility.PropertyConfig;

import java.util.Map;

public class WebObjectSearch {
    static public WebElement searchWebObject(WebDriver driver,Map objectLocator) throws Exception {
        try {
            String objectSearchType = ( (String)objectLocator.get(PropertyConfig.OBJECT_SEARCH_KEY)).toUpperCase();
            WebObjectSearchType webObjectSearchType = WebObjectSearchType.valueOf(objectSearchType);
            WebElement webElement =  webObjectSearchType.findElement(driver,(String) objectLocator.get(PropertyConfig.OBJECT_LOCATORS));
            return webElement;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new Exception("webElement not founding");
        }
    }

    static public  boolean validateObjectLocator(Map objectLocator){
        try {
            if(objectLocator.get(PropertyConfig.OBJECT_SEARCH_KEY).toString().isEmpty()) return  false ;
            if(objectLocator.get(PropertyConfig.OBJECT_LOCATORS).toString().isEmpty()) return  false ;
            return true ;
        } catch(Exception ex){
            ex.printStackTrace();
            return false;
        }
    }

    static public WebElement getWebElement(WebDriver webDriver  ,String objectLocator) {
        try {
            Map objectLocatorData = ObjectLocatorDataStorage.getObjectLocator(objectLocator);
            WebElement userWeb = searchWebObject(webDriver, objectLocatorData);
            return userWeb;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
