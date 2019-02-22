package test.objectLocator;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import test.utility.PropertyConfig;

import java.util.List;
import java.util.Map;

public class WebObjectSearch {
    static public List<WebElement> searchWebObject(WebDriver driver,Map objectLocator) throws Exception {
        try {
            String objectSearchType = ( (String)objectLocator.get(PropertyConfig.OBJECT_SEARCH_KEY)).toUpperCase();
            WebObjectSearchType webObjectSearchType = WebObjectSearchType.valueOf(objectSearchType);
            List<WebElement> webElements =  webObjectSearchType.findElement(driver,(String) objectLocator.get(PropertyConfig.OBJECT_LOCATORS));
            return webElements;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new Exception("webElements not founding");
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
            List<WebElement> userelemnts = searchWebObject(webDriver, objectLocatorData);
            if(null == userelemnts)
                return null;
            else
                return userelemnts.get(userelemnts.size()-1);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    static public List<WebElement> getWebElements(WebDriver webDriver  ,String objectLocator) {
        try {
            Map objectLocatorData = ObjectLocatorDataStorage.getObjectLocator(objectLocator);
            return searchWebObject(webDriver, objectLocatorData);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
