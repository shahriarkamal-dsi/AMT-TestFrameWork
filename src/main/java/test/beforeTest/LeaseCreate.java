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
    private String  objectlocatorPrefix;
    private UIBase uiBase;
    private String mainWindow;
    public LeaseCreate(WebDriver driver){
        this.webDriver = driver;
    }

    public LeaseCreate(){

    }

    public LogMessage createMultipleLeases(List<Map> datas)
    {
        try{
            UtilKeywordScript utilKeywordScript = new UtilKeywordScript(webDriver);
            objectlocatorPrefix = "Common.Lease.";
            UIMenu menu = new UIMenu(webDriver);
            menu.SelectMenu("Common.Homepage.pgAMTHome" , "Portfolio Insight,Add,Lease,DEFAULT,Real Estate Contract");
            UtilKeywordScript.switchLastTab(webDriver);
            webDriver.manage().window().maximize();
            UtilKeywordScript.delay(3);
            for(int i=0; i < datas.size();i++)
            {
                if(i!=0){
                    uiBase.Click(objectlocatorPrefix + "addNewButton");
                    uiBase.WaitingForPageLoad();
                }
                createLease(datas.get(i));

            }
            utilKeywordScript.redirectHomePage();
            UtilKeywordScript.delay(3);
            return new LogMessage(true,"Leases create successfully");
        }catch (Exception ex){
            ex.printStackTrace();
            return new LogMessage(false, "Exception Occurred " + ex.getMessage());
        }

    }

    public LogMessage createLease(Map data){

        try {
            WebDriverWait wait = new WebDriverWait(webDriver, 5*60);
            Map objectLocatorData = ObjectLocatorDataStorage.getObjectLocator(objectlocatorPrefix + "propertyList");
            uiBase = new UIBase(webDriver);
            UIText uiText = new UIText(webDriver);


            UIDropDown uiDropDown = new UIDropDown(webDriver);
            uiDropDown.searchAndSelectItem(objectlocatorPrefix + "propertyName",(String) objectLocatorData.get(PropertyConfig.PARENT_LOCATOR),(String)data.get("propertyName"));

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


            return new LogMessage(true,"Lease create successfully");
        }catch (Exception ex){
            ex.printStackTrace();
            return new LogMessage(false, "Exception Occurred " + ex.getMessage());
        }

    }

    public LogMessage createMultipleSpaces(List<Map> datas)
    {
        try{
            UtilKeywordScript utilKeywordScript = new UtilKeywordScript(webDriver);
            LeaseCreate leaseCreate = new LeaseCreate(webDriver);
            uiBase = new UIBase(webDriver);
            leaseCreate.searchLease(datas.get(0));
            UtilKeywordScript.delay(5);
            mainWindow = webDriver.getWindowHandle();
            for(Map data: datas)
            {
                createSpace(data);
            }

            utilKeywordScript.redirectHomePage();
            return new LogMessage(true,"Spaces create successfully!");
        }catch(Exception e){
            e.printStackTrace();
            return new LogMessage(false,"Exception occurred " + e.getMessage());
        }
    }
    public LogMessage createSpace(Map data){
        try{
            String  objectLocatorPrefix = "Common.Space." ;
            WebDriverWait wait = new WebDriverWait(webDriver, 5*60);

            UILink uiLink = new UILink(webDriver);

            UIText uiText = new UIText(webDriver);

            UtilKeywordScript.delay(1);
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
            UtilKeywordScript.delay(2);
            Set<String> set =webDriver.getWindowHandles();
            Iterator<String> itr= set.iterator();
            while(itr.hasNext()){
                String childWindow=itr.next();
                if(!mainWindow.equals(childWindow)){
                    webDriver.switchTo().window(childWindow);
                    System.out.println(webDriver.switchTo().window(childWindow).getTitle());
                    webDriver.close();
                }

            }
            webDriver.switchTo().window(mainWindow);
            return new LogMessage(true,"Space create successfully!");
        }catch (Exception e){
            e.printStackTrace();
            return new LogMessage(false,"Exception occurred " + e.getMessage());
        }
    }
    public LogMessage addMultipleRecurringPayments(List<Map> datas){
     try{
         UtilKeywordScript utilKeywordScript = new UtilKeywordScript(webDriver);
         LeaseCreate leaseCreate = new LeaseCreate(webDriver);
         uiBase = new UIBase(webDriver);
         searchLease(datas.get(0));
         UtilKeywordScript.delay(5);
         mainWindow = webDriver.getWindowHandle();
         for(Map data: datas)
         {
             System.out.println(data);
             addRecurringPayment(data);
         }

         utilKeywordScript.redirectHomePage();
        return new LogMessage(true,"Recurrent payments add successfully");
    }catch (Exception e){
        e.printStackTrace();
        return new LogMessage(false, "Exception occurred " + e.getMessage());
    }
    }
    public LogMessage addRecurringPayment(Map data){
        try{
            String  objectLocatorPrefix = "Common.RecurringPayment." ;
            String[] dropdownFields = new String[] {"spaceInfo","chargeType","frequency","escalationType" , "leaseTermYear" , "leaseTermDefined" } ;
            WebDriverWait wait = new WebDriverWait(webDriver, 5*60);

            UILink uiLink = new UILink(webDriver);
            UIBase uiBase = new UIBase(webDriver);
            UITable uiTable = new UITable(webDriver);
            UtilKeywordScript utilKeywordScript = new UtilKeywordScript(webDriver);

            //searchLease(data);

            uiLink.ClickLink("","Add New");
            System.out.println("Done");
            UtilKeywordScript.delay(5);
            UtilKeywordScript.switchLastTab(webDriver);
            webDriver.manage().window().maximize();
            UtilKeywordScript.delay(5);

            UIDropDown uiDropDown = new UIDropDown(webDriver);
            for (String element : dropdownFields){
                UtilKeywordScript.delay(1);
                uiDropDown.SelectItem(objectLocatorPrefix + element,(String)data.get(element));

            }

            uiBase.Click(objectLocatorPrefix + "btnSave");

            //uiBase.WaitingForPageLoad();
            uiBase.WaitingForSuccessfullPopup();

            wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Add Rental Activity")));

            uiLink.ClickLink("","Add Rental Activity");
            UtilKeywordScript.delay(3);

            uiTable.DoubleClickCellInTable(objectLocatorPrefix + "tableRecurrentPayment", "*Eff Date,0," + (String)data.get("effDate"));
            uiTable.EnterCellData(objectLocatorPrefix + "tableRecurrentPayment", "*Eff Date,0,"+ (String)data.get("effDate"));

            uiTable.EnterCellData(objectLocatorPrefix + "tableRecurrentPayment", "*End Date,0," + (String)data.get("effDate"));
            uiTable.EnterCellData(objectLocatorPrefix + "tableRecurrentPayment", "*End Date,0," + (String)data.get("effDate"));

            uiTable.DoubleClickCellInTable(objectLocatorPrefix + "tableRecurrentPayment", "*Amount,0," + (String)data.get("amount"));
            uiTable.EnterCellData(objectLocatorPrefix + "tableRecurrentPayment", "*Amount,0," + (String)data.get("amount"));

            uiTable.DoubleClickCellInTable(objectLocatorPrefix + "tableRecurrentPayment", "Annual,0,0");

            UtilKeywordScript.delay(3);

            uiBase.Click(objectLocatorPrefix + "saveRentalActivity");

            //uiBase.WaitingForPageLoad();
            uiBase.WaitingForSuccessfullPopup();

            uiBase.Click(objectLocatorPrefix + "btnSave");

            //uiBase.WaitingForPageLoad();
            uiBase.WaitingForSuccessfullPopup();

            UtilKeywordScript.delay(3);

            uiBase.Click(objectLocatorPrefix + "btnClose");
            UtilKeywordScript.delay(2);
            Set<String> set =webDriver.getWindowHandles();
            Iterator<String> itr= set.iterator();
            while(itr.hasNext()){
                String childWindow=itr.next();
                if(!mainWindow.equals(childWindow)){
                    webDriver.switchTo().window(childWindow);
                    System.out.println(webDriver.switchTo().window(childWindow).getTitle());
                    webDriver.close();
                }

            }
            webDriver.switchTo().window(mainWindow);
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
