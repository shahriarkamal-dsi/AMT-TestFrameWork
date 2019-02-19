package test.beforeTest;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import test.Log.LogMessage;
import test.keywordScripts.UIMenu;
import test.keywordScripts.UtilKeywordScript;
import test.objectLocator.WebObjectSearch;

import java.util.Map;

public class PropertyCreate {
    private WebDriver webDriver;

    public PropertyCreate(WebDriver driver) {
        this.webDriver = driver ;
    }

    public PropertyCreate(){

    }



    public LogMessage createProperty(Map data) {
        try {
            String[] textFields = new String[] {"propertyName","propertyCode","address1" , "postal" , "city" , "sqFtRentable"} ;
            String[] dropDownFields = new String[] {"country","state","codeType" , "status" , "currency", "buildingList", "region", "assetType"} ;
            String autoManageChkBox = "autoManage" ;
            String  objectlocatorPrefix = "Common.Property." ;
            UIMenu menu = new UIMenu(webDriver);
            UtilKeywordScript utilKeywordScript = new UtilKeywordScript(webDriver);

            menu.SelectMenu("Common.Homepage.pgAMTHome" , "Portfolio Insight,Add,Property") ;
            UtilKeywordScript.switchLastTab(webDriver);
            UtilKeywordScript.delay(10);

            for(String elementName : textFields) {
                WebElement element = WebObjectSearch.getWebElement(webDriver,objectlocatorPrefix  + elementName) ;
                element.sendKeys( (String) data.get(elementName));
                UtilKeywordScript.delay(3);
            }

            for(String elementName : dropDownFields) {
                WebElement element = WebObjectSearch.getWebElement(webDriver,objectlocatorPrefix  + elementName) ;
                Select select = new Select(element);
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
            utilKeywordScript.redirectHomePage();
            UtilKeywordScript.delay(3);
            return new LogMessage(true, "create property successfull") ;
        } catch ( Exception ex) {
            ex.printStackTrace();
            return new LogMessage(false, "exception occured: " + ex.getMessage()) ;
        }
    }

}
