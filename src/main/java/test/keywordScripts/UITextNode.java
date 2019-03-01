package test.keywordScripts;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import test.Log.LogMessage;
import test.objectLocator.WebObjectSearch;
import test.utility.PropertyConfig;

public class UITextNode {
    private WebDriver webDriver;
    public UITextNode(){

    }
    public UITextNode(WebDriver webDriver){
        this.webDriver = webDriver ;
    }

    public LogMessage ClickTextNode(String objectLocator, String testData) {
        try {
            System.out.println("obloc"+objectLocator);
            WebElement rootElement = WebObjectSearch.getWebElement(webDriver,objectLocator);
            if (null == rootElement)
                return new LogMessage(false," element is not found.");
            String testDatas[] = testData.split(",");
            WebElement childElement = rootElement.findElement(By.xpath("//*[text()='" +testDatas[0] + "']"));
            UIBase uiBase = new UIBase(webDriver);
           return uiBase.ClickDbClickRClick(childElement,testDatas[1]);
        } catch (Exception ex) {
            ex.printStackTrace();
            return new LogMessage(false,"excetion occured: " + ex.getMessage());
        }
    }

}
