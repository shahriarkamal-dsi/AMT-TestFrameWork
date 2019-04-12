package test.keywordScripts;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import test.Log.LogMessage;
import test.objectLocator.WebObjectSearch;

public class UIInput {
    private WebDriver webDriver;

    public UIInput(WebDriver driver) {
        this.webDriver = driver ;
    }
    public LogMessage compareValue(String objectLocator, String testData){
        try{
            String[] splittedTestData=testData.split(",");
            String attribute = getValue(objectLocator);
            if(attribute.equals(splittedTestData[0]))
                return new LogMessage(true, "Value is verified");
            else
                return new LogMessage(false, "Value is not verified");
        }catch (Exception e){
            return new LogMessage(false,"Exception occur" + e.getMessage());
        }
    }
    public String getValue(String objectLocator){
        try {
            WebElement webElement = WebObjectSearch.getWebElement(webDriver, objectLocator);
            if (null == webElement) {
                return null;
            }
            return webElement.getAttribute("value");
        }catch (Exception e){
            return null;
        }
    }

}
