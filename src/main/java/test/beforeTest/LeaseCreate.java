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
import test.keywordScripts.UITable;
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
            String rootWindow = webDriver.getWindowHandle() ;
            String  objectlocatorPrefix = "Common.Lease." ;
            UIMenu menu = new UIMenu(webDriver);
            menu.SelectMenu("Common.Homepage.pgAMTHome" , "Portfolio Insight,Add,Lease,DEFAULT,Real Estate Contract");
            switchTabs(webDriver);
            webDriver.manage().window().maximize();
            UtilKeywordScript.delay(3);
            UIDropDown uiDropDown = new UIDropDown(webDriver);
            uiDropDown.SelectItem(objectlocatorPrefix + "propertyList",(String) data.get("propertyName"));

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

            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("splashScr")));
            wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("splashScr")));
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(@class,'alert-success')]")));
            wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[contains(@class,'alert-success')]")));

            UtilKeywordScript.delay( 5);
            webDriver.close();
            webDriver.switchTo().window(rootWindow);
            UtilKeywordScript.delay(3);

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

    public LogMessage searchLease(Map data){

        try{
            //String rootWindow = webDriver.getWindowHandle() ;
            String  objectLocatorPrefix = "Common.GlobalSearch." ;

            WebElement search = WebObjectSearch.getWebElement(webDriver, objectLocatorPrefix + "search");
            search.click();

            WebElement txtSearch = WebObjectSearch.getWebElement(webDriver, objectLocatorPrefix + "txtSearch");
            txtSearch.sendKeys((String)data.get("LeaseName"));

            WebElement btnSearch = WebObjectSearch.getWebElement(webDriver, objectLocatorPrefix + "btnSearch");
            btnSearch.click();

            UtilKeywordScript.delay(10);

            UtilKeywordScript.switchLatestTab(webDriver);

            UITable uiTable  = new UITable(webDriver);
             LogMessage logMessage =  uiTable.ClickLinkInTable(objectLocatorPrefix + "leasetable","DBA Name," + (String)data.get("LeaseName"));
            UtilKeywordScript.delay(10);
             UtilKeywordScript.switchLatestTab(webDriver);
             System.out.println(logMessage.getLogMessage());
            return new LogMessage(true, " Lease found successfully");
        }catch (Exception e){
            e.printStackTrace();
            return new LogMessage(false, " Exception Occured");
        }


    }


}
