package test.beforeTest;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import test.Log.LogMessage;
import test.keywordScripts.UIBase;
import test.keywordScripts.UIMenu;
import test.keywordScripts.UtilKeywordScript;
import test.objectLocator.WebObjectSearch;

import java.util.List;
import java.util.Map;

public class PropertyCreate {
    private WebDriver webDriver;

    public PropertyCreate(WebDriver driver) {
        this.webDriver = driver ;
    }

    public PropertyCreate(){

    }



    public LogMessage createProperty(Map data) {
        UtilKeywordScript utilKeywordScript = new UtilKeywordScript(webDriver);
        try {
            String[] textFields = new String[] {"propertyName","propertyCode","address1" , "postal" , "city" , "sqFtRentable"} ;
            String[] dropDownFields = new String[] {"country","state","codeType" , "status" , "currency", "propertyGroup1", "propertyGroup2", "propertyGroup3"} ;
            String autoManageChkBox = "autoManage" ;
            String  objectlocatorPrefix = "Common.Property." ;
            UIMenu menu = new UIMenu(webDriver);
            menu.SelectMenu("Common.Homepage.pgAMTHome" , "Portfolio Insight,Add,Property") ;
            UtilKeywordScript.switchLastTab(webDriver);
            UtilKeywordScript.delay(10);

            for(String elementName : textFields) {
                WebElement element = WebObjectSearch.getWebElement(webDriver,objectlocatorPrefix  + elementName) ;
                element.clear();
                element.sendKeys( (String) data.get(elementName));
                UtilKeywordScript.delay(3);
            }

            for(String elementName : dropDownFields) {
                WebElement element = WebObjectSearch.getWebElement(webDriver,objectlocatorPrefix  + elementName) ;
                Select select = new Select(element);
                for(Object key: data.keySet()){
                    String temp= (String)key;
                    if(temp.contains(elementName))
                        elementName=temp;

                }
                select.selectByVisibleText( (String)data.get(elementName));
                if(elementName.equals("codeType")) {
                    webDriver.switchTo().alert().accept();
                    UtilKeywordScript.delay(3);
                }
                UtilKeywordScript.delay(3);
            }
            WebElement checkBoxItem = WebObjectSearch.getWebElement(webDriver,objectlocatorPrefix + autoManageChkBox) ;
            if(  data.get(autoManageChkBox).toString().toLowerCase().equals("true"))
                checkBoxItem.click();
            WebElement element = WebObjectSearch.getWebElement(webDriver,objectlocatorPrefix + "save") ;
            element.click();
            UtilKeywordScript.delay(15);

            if(webDriver.findElement(By.xpath("//*[contains(@id,'lblPropertyCodeValue')]")).getText().equals(data.get("propertyCode")))
            {
                utilKeywordScript.redirectHomePage();
                return new LogMessage(true, "Property"+data.get("propertyName")+"-"+data.get("propertyCode") +" is created successfully");
            }
            else {
                utilKeywordScript.redirectHomePage();
                return new LogMessage(false, "Property"+data.get("propertyName")+"-"+data.get("propertyCode") +" is not created");
            }
        } catch ( Exception ex) {

            ex.printStackTrace();
            utilKeywordScript.redirectHomePage();
            return new LogMessage(false, "Property"+data.get("propertyName")+"-"+data.get("propertyCode") +" is not created") ;
        }


    }

}
