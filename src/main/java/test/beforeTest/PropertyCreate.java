package test.beforeTest;

import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import test.Log.LogMessage;
import test.keywordScripts.UIMenu;
import test.objectLocator.WebObjectSearch;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

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
            String  objectlocatorPrefix = "Common.Property." ;
            UIMenu menu = new UIMenu(webDriver);
            //menu.SelectMenu("Common.Homepage.pgAMTHome" , "Portfolio Insight,Add,Lease,DEFAULT,Real Estate Contract") ;
            menu.SelectMenu("Common.Homepage.pgAMTHome" , "Portfolio Insight,Add,Property") ;
            switchTabs(webDriver);

            Thread.sleep(10*1000);
           /*
            for(String elementName : textFields) {
                WebElement element = WebObjectSearch.getWebElement(webDriver,objectlocatorPrefix  + elementName) ;
                element.sendKeys( (String) data.get(elementName));
            }*/

            for(String elementName : dropDownFields) {
                WebElement element = WebObjectSearch.getWebElement(webDriver,objectlocatorPrefix  + elementName) ;
                Select select = new Select(element);
                select.selectByVisibleText( (String)data.get(elementName));
                Thread.sleep(1*1000);

            }
            WebElement element = WebObjectSearch.getWebElement(webDriver,objectlocatorPrefix + "save") ;
            element.click();
            return new LogMessage(true, "create property successfull") ;
        } catch ( Exception ex) {
            ex.printStackTrace();
            return new LogMessage(false, "exception occured: " + ex.getMessage()) ;
        }
    }

    public  void switchTabs(WebDriver webDriver) {
        try {
            Set<String> windows = webDriver.getWindowHandles();
            Iterator<String> iter = windows.iterator();
            String[] winNames=new String[windows.size()];
            int i=0;
            while (iter.hasNext()) {
                winNames[i]=iter.next();
                i++;
            }

            if(winNames.length > 1) {
                for(i = winNames.length; i > 1; i--) {
                    webDriver.switchTo().window(winNames[i - 1]);
                   // webDriver.close();
                }
            }
           // webDriver.switchTo().window(winNames[0]);
            //webDriver.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

}
