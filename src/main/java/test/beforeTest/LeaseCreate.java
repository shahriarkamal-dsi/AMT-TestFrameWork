package test.beforeTest;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import test.Log.LogMessage;
import test.keywordScripts.*;

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
            String  objectlocatorPrefix = "Common.Lease.";
            WebDriverWait wait = new WebDriverWait(webDriver, 5*60);

            UIMenu menu = new UIMenu(webDriver);
            UIBase uiBase = new UIBase(webDriver);
            UIText uiText = new UIText(webDriver);
            UtilKeywordScript utilKeywordScript = new UtilKeywordScript(webDriver);

            menu.SelectMenu("Common.Homepage.pgAMTHome" , "Portfolio Insight,Add,Lease,DEFAULT,Real Estate Contract");
            UtilKeywordScript.switchLastTab(webDriver);
            webDriver.manage().window().maximize();
            UtilKeywordScript.delay(3);
            UIDropDown uiDropDown = new UIDropDown(webDriver);
            uiDropDown.SelectItem(objectlocatorPrefix + "propertyList",(String)data.get("propertyName"));

            uiBase.WaitingForPageLoad();

            uiText.SetText(objectlocatorPrefix +"dbaName",(String)data.get("dbaName"));
            uiText.SetText(objectlocatorPrefix +"leaseCode",(String)data.get("leaseCode"));

            uiDropDown.SelectItem(objectlocatorPrefix+"leaseStatus",(String)data.get("leaseStatus"));
            uiDropDown.SelectItem(objectlocatorPrefix+"leaseType",(String)data.get("leaseType"));
            uiDropDown.SelectItem(objectlocatorPrefix + "billingType",(String)data.get("billingType"));

            uiText.SetText(objectlocatorPrefix +"beginDate",(String)data.get("beginDate"));
            UtilKeywordScript.delay(2);
            uiText.SetText(objectlocatorPrefix +"expirationDate",(String)data.get("expirationDate"));

            uiDropDown.SelectItem(objectlocatorPrefix + "contractTerm", (String)data.get("contractTerm"));
            UtilKeywordScript.delay(6);

            uiBase.Click(objectlocatorPrefix + "saveButton");

            uiBase.WaitingForPageLoad();

            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(@class,'alert-success')]")));
            wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[contains(@class,'alert-success')]")));

            UtilKeywordScript.delay( 5);
            utilKeywordScript.redirectHomePage();
            UtilKeywordScript.delay(3);

            return new LogMessage(true,"Lease create successfully");
        }catch (Exception ex){
            ex.printStackTrace();
            return new LogMessage(false, "Exception Occurred " + ex.getMessage());
        }

    }

    public LogMessage addRecurringPayment(Map data){
        try{
            String  objectLocatorPrefix = "Common.RecurringPayment." ;
            WebDriverWait wait = new WebDriverWait(webDriver, 5*60);

            UILink uiLink = new UILink(webDriver);
            UIBase uiBase = new UIBase(webDriver);
            UIText uiText = new UIText(webDriver);
            UtilKeywordScript utilKeywordScript = new UtilKeywordScript(webDriver);

            LeaseCreate leaseCreate = new LeaseCreate(webDriver);
            leaseCreate.searchLease(data);
            UtilKeywordScript.delay(5);

            uiLink.clickLink("","Add New");
            UtilKeywordScript.delay(5);
            UtilKeywordScript.switchLastTab(webDriver);
            webDriver.manage().window().maximize();
            UtilKeywordScript.delay(5);

            UIDropDown uiDropDown = new UIDropDown(webDriver);
            uiDropDown.SelectItem(objectLocatorPrefix + "chargeType",(String)data.get("ChargeType"));

            uiDropDown.SelectItem(objectLocatorPrefix + "frequency",(String)data.get("Frequency"));
            uiDropDown.SelectItem(objectLocatorPrefix + "escalationType",(String)data.get("EscalationType"));
            uiDropDown.SelectItem(objectLocatorPrefix + "leaseTermYear",(String)data.get("LeaseTermYear"));
            uiDropDown.SelectItem(objectLocatorPrefix + "leaseTermDefined",(String)data.get("LeaseTermDefined"));

            uiBase.Click(objectLocatorPrefix + "btnSave");

            uiBase.WaitingForPageLoad();

            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(@class,'alert-success')]")));
            wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[contains(@class,'alert-success')]")));

            wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Add Rental Activity")));

            UtilKeywordScript.delay(5);

            utilKeywordScript.redirectHomePage();

            return new LogMessage(true,"Recurrent payment add successfully");
        }catch (Exception e){
            e.printStackTrace();
            return new LogMessage(false, "Exception occurred " + e.getMessage());
        }
    }

    public LogMessage searchLease(Map data){

        try{
            String  objectLocatorPrefix = "Common.GlobalSearch.";
            UIText uiText = new UIText(webDriver);
            UIBase uiBase = new UIBase(webDriver);
            UITable uiTable  = new UITable(webDriver);

            uiBase.Click(objectLocatorPrefix + "search");
            uiText.SetText(objectLocatorPrefix +"txtSearch",(String)data.get("LeaseName"));
            uiBase.Click(objectLocatorPrefix + "btnSearch");

            UtilKeywordScript.delay(10);
            UtilKeywordScript.switchLastTab(webDriver);
            LogMessage logMessage =  uiTable.ClickLinkInTable(objectLocatorPrefix + "leasetable","DBA Name," + (String)data.get("LeaseName"));
            UtilKeywordScript.delay(10);
            webDriver.close();
            UtilKeywordScript.switchLastTab(webDriver);
            UtilKeywordScript.delay(10);

            return new LogMessage(true, " Lease found successfully");
        }catch (Exception e){
            e.printStackTrace();
            return new LogMessage(false, " Exception occurred " + e.getMessage());
        }

    }

}
