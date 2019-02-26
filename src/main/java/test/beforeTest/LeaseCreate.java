package test.beforeTest;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import test.Log.LogMessage;
import test.keywordScripts.*;
import test.objectLocator.ObjectLocatorDataStorage;
import test.objectLocator.WebObjectSearch;
import test.utility.PropertyConfig;

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
            String[] dropdownFields = new String[] {"leaseStatus","leaseType","billingType" , "contractTerm" } ;

            Map objectLocatorData = ObjectLocatorDataStorage.getObjectLocator(objectlocatorPrefix + "propertyList");

            UIMenu menu = new UIMenu(webDriver);
            UIBase uiBase = new UIBase(webDriver);
            UIText uiText = new UIText(webDriver);
            UtilKeywordScript utilKeywordScript = new UtilKeywordScript(webDriver);

            menu.SelectMenu("Common.Homepage.pgAMTHome" , "Portfolio Insight,Add,Lease,DEFAULT,Real Estate Contract");
            UtilKeywordScript.switchLastTab(webDriver);
            webDriver.manage().window().maximize();
            UtilKeywordScript.delay(3);
            UIDropDown uiDropDown = new UIDropDown(webDriver);
            uiDropDown.searchAndSelectItem(objectlocatorPrefix + "propertyName",(String) objectLocatorData.get(PropertyConfig.PARENT_LOCATOR),(String)data.get("propertyName"));

            uiBase.WaitingForPageLoad();

            uiText.SetText(objectlocatorPrefix +"dbaName",(String)data.get("dbaName"));
            uiText.SetText(objectlocatorPrefix +"leaseCode",(String)data.get("leaseCode"));

            for (String element : dropdownFields){
                uiDropDown.SelectItem(objectlocatorPrefix + element,(String)data.get(element));
            }
            uiText.SetText(objectlocatorPrefix +"beginDate",(String)data.get("beginDate"));
            UtilKeywordScript.delay(2);
            uiText.SetText(objectlocatorPrefix +"expirationDate",(String)data.get("expirationDate"));

            UtilKeywordScript.delay(6);

            uiBase.Click(objectlocatorPrefix + "saveButton");

            uiBase.WaitingForPageLoad();
            uiBase.WaitingForSuccessfullPopup();
            UtilKeywordScript.delay( 5);
            utilKeywordScript.redirectHomePage();
            UtilKeywordScript.delay(3);

            return new LogMessage(true,"Lease create successfully");
        }catch (Exception ex){
            ex.printStackTrace();
            return new LogMessage(false, "Exception Occurred " + ex.getMessage());
        }

    }

    public LogMessage createSpace(Map data){
        try{
            String  objectLocatorPrefix = "Common.Space.";

            UILink uiLink = new UILink(webDriver);
            UIBase uiBase = new UIBase(webDriver);
            UIText uiText = new UIText(webDriver);
            UtilKeywordScript utilKeywordScript = new UtilKeywordScript(webDriver);

            searchLease(data);

            uiLink.ClickLink("","Add New Suite");

            UtilKeywordScript.delay(5);
            UtilKeywordScript.switchLastTab(webDriver);
            webDriver.manage().window().maximize();

            UtilKeywordScript.delay(5);

            uiText.SetText(objectLocatorPrefix +"space",(String)data.get("Space"));
            uiText.SetText(objectLocatorPrefix +"floor",(String)data.get("Floor"));

            List<WebElement>  linkedItems  = WebObjectSearch.getWebElements(webDriver, objectLocatorPrefix + "linked");
            uiBase.Click(linkedItems.get(0));
            UtilKeywordScript.delay(5);

            uiBase.Click(objectLocatorPrefix + "btnSave");

            uiBase.WaitingForPageLoad();
            uiBase.WaitingForSuccessfullPopup();
            UtilKeywordScript.delay(3);
            uiBase.Click(objectLocatorPrefix + "btnClose");
            UtilKeywordScript.delay(5);

            utilKeywordScript.redirectHomePage();

            return new LogMessage(true,"Space create successfully!");
        }catch (Exception e){
            e.printStackTrace();
            return new LogMessage(false,"Exception occurred " + e.getMessage());
        }
    }

    public LogMessage addRecurringPayment(Map data){
        try{
            String  objectLocatorPrefix = "Common.RecurringPayment." ;
            String[] dropdownFields = new String[] {"chargeType","frequency","escalationType" , "leaseTermYear" , "leaseTermDefined" } ;
            WebDriverWait wait = new WebDriverWait(webDriver, 5*60);

            UILink uiLink = new UILink(webDriver);
            UIBase uiBase = new UIBase(webDriver);
            UITable uiTable = new UITable(webDriver);
            UtilKeywordScript utilKeywordScript = new UtilKeywordScript(webDriver);

            searchLease(data);

            uiLink.ClickLink("","Add New");
            UtilKeywordScript.delay(5);
            UtilKeywordScript.switchLastTab(webDriver);
            webDriver.manage().window().maximize();
            UtilKeywordScript.delay(5);

            UIDropDown uiDropDown = new UIDropDown(webDriver);
            for (String element : dropdownFields){
                uiDropDown.SelectItem(objectLocatorPrefix + element,(String)data.get(element));

            }

            uiBase.Click(objectLocatorPrefix + "btnSave");

            uiBase.WaitingForPageLoad();
            uiBase.WaitingForSuccessfullPopup();

            wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Add Rental Activity")));

            uiLink.ClickLink("","Add Rental Activity");
            UtilKeywordScript.delay(3);

            uiTable.ClickCellInTable(objectLocatorPrefix + "tableRecurrentPayment", "*Eff Date,0," + (String)data.get("effDate"));
            uiTable.EnterCellData(objectLocatorPrefix + "tableRecurrentPayment", "*Eff Date,0,"+ (String)data.get("effDate"));

            uiTable.EnterCellData(objectLocatorPrefix + "tableRecurrentPayment", "*End Date,0," + (String)data.get("effDate"));
            uiTable.EnterCellData(objectLocatorPrefix + "tableRecurrentPayment", "*End Date,0," + (String)data.get("effDate"));

            uiTable.ClickCellInTable(objectLocatorPrefix + "tableRecurrentPayment", "*Amount,0," + (String)data.get("amount"));
            uiTable.EnterCellData(objectLocatorPrefix + "tableRecurrentPayment", "*Amount,0," + (String)data.get("amount"));

            uiTable.ClickCellInTable(objectLocatorPrefix + "tableRecurrentPayment", "Annual,0,0");

            UtilKeywordScript.delay(3);

            uiBase.Click(objectLocatorPrefix + "saveRentalActivity");

            uiBase.WaitingForPageLoad();
            uiBase.WaitingForSuccessfullPopup();

            uiBase.Click(objectLocatorPrefix + "btnSave");

            uiBase.WaitingForPageLoad();
            uiBase.WaitingForSuccessfullPopup();

            UtilKeywordScript.delay(3);

            uiBase.Click(objectLocatorPrefix + "btnClose");

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
