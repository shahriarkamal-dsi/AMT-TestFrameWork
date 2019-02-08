package test.beforeTest;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import test.Log.LogMessage;
import test.keywordScripts.UIDropDown;
import test.keywordScripts.UIMenu;
import test.keywordScripts.UtilKeywordScript;
import test.objectLocator.WebObject;
import test.objectLocator.WebObjectSearch;
import test.utility.PropertyConfig;

import java.rmi.server.UID;
import java.util.*;

public class LeaseCreate {

    private WebDriver webDriver;

    public LeaseCreate(WebDriver driver){
        this.webDriver = driver;
    }

    public LeaseCreate(){

    }

    public LogMessage createLease(Map data){

        try {

            String  objectlocatorPrefix = "Common.Lease." ;
            UIMenu menu = new UIMenu(webDriver);
            menu.SelectMenu("Common.Homepage.pgAMTHome" , "Portfolio Insight,Add,Lease,DEFAULT,Real Estate Contract");
            switchTabs(webDriver);
            webDriver.manage().window().maximize();
            UtilKeywordScript.delay(3);
            UIDropDown uiDropDown = new UIDropDown(webDriver);
            uiDropDown.SelectItem(objectlocatorPrefix + "propertyList",(String) data.get("propertyName"));

          // WebElement propertyName = WebObjectSearch.getWebElement(webDriver,objectlocatorPrefix +"propertyName");
           // propertyName.sendKeys((String) data.get("propertyName"));
            //WebElement dbaName = WebObjectSearch.getWebElement(webDriver,objectlocatorPrefix+"dbaName");
           // dbaName.click();
           // UtilKeywordScript.delay(1);
            WebDriverWait wait = new WebDriverWait(webDriver, 5*60);
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("splashScr")));
            wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("splashScr")));
             WebElement dbaName = WebObjectSearch.getWebElement(webDriver,objectlocatorPrefix+"dbaName");
            dbaName.sendKeys((String)data.get("dbaName"));
            WebElement leaseCode = WebObjectSearch.getWebElement(webDriver, objectlocatorPrefix+"leaseCode");
            leaseCode.sendKeys((String)data.get("leaseCode"));
            uiDropDown.SelectItem(objectlocatorPrefix+"leaseStatus",(String)data.get("leaseStatus"));
            uiDropDown.SelectItem(objectlocatorPrefix+"leaseType",(String)data.get("leaseType"));
            uiDropDown.SelectItem(objectlocatorPrefix + "billingType",(String)data.get("billingType"));
            WebElement beginDate = WebObjectSearch.getWebElement(webDriver, objectlocatorPrefix+"beginDate");
             UtilKeywordScript.delay(2);
            beginDate.click();
            beginDate.sendKeys((String)data.get("beginDate"));
            WebElement expirationDate = WebObjectSearch.getWebElement(webDriver, objectlocatorPrefix+"expirationDate");
            UtilKeywordScript.delay(2);
            expirationDate.sendKeys((String)data.get("expirationDate"));
            uiDropDown.SelectItem(objectlocatorPrefix + "contractTerm", (String)data.get("contractTerm"));
            UtilKeywordScript.delay(6);
            WebElement btnSave = WebObjectSearch.getWebElement(webDriver,objectlocatorPrefix + "saveButton");
            btnSave.click();

            /*
            Map findData = new HashMap();
            findData.put(PropertyConfig.OBJECT_LOCATORS, "//div[@class,'alert alert-warning']");
            findData.put(PropertyConfig.OBJECT_SEARCH_KEY, "by_xpath");
            List<WebElement> elements = WebObjectSearch.searchWebObject(webDriver,findData);
            if(null != elements || !elements.isEmpty()) {
                return new LogMessage(false,elements.get(0).getText());
            }*/



            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("splashScr")));
            wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("splashScr")));
            return new LogMessage(true,"Lease create successfully");
        }catch (Exception ex){
            ex.printStackTrace();
            return new LogMessage(false, "Exception Occurred");
        }

    }
    //alert alert-warning

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
                }
            }

        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public LogMessage select(Map data, String objectLocator, String propertyName){

        WebElement propertyListBox = WebObjectSearch.getWebElement(webDriver,objectLocator);
        List<WebElement> propertyNames = propertyListBox.findElements(By.tagName("li"));
        for (WebElement property : propertyNames){
            if (property.getText().equals(data.get(propertyName).toString())){
                property.click();
                return new LogMessage(true, "Property found successfully");
            }
        }
        return new LogMessage(false, " Invalid property name");
    }
}
