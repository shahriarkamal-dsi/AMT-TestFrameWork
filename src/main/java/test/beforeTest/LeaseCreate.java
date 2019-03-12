package test.beforeTest;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
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

    public List<LogMessage> createMultipleLeases(List<Map> datas)
    {
        UtilKeywordScript utilKeywordScript = new UtilKeywordScript(webDriver);
        List<LogMessage> logMessageList = new ArrayList<>();
        try{
            objectlocatorPrefix = "Common.Lease.";
            UIMenu menu = new UIMenu(webDriver);
            menu.SelectMenu("Common.Homepage.pgAMTHome" , "Portfolio Insight,Add,Lease,"+datas.get(0).get("codeType")+","+datas.get(0).get("contractType"));
            UtilKeywordScript.switchLastTab(webDriver);
            webDriver.manage().window().maximize();
            UtilKeywordScript.delay(3);
            for(int i=0; i < datas.size();i++)
            {
                if(i!=0){
                    uiBase.Click(objectlocatorPrefix + "addNewButton");
                    uiBase.WaitingForPageLoad();
                }
                LogMessage lm = createLease(datas.get(i));
                logMessageList.add(lm);

            }
            utilKeywordScript.redirectHomePage();
            UtilKeywordScript.delay(3);
            return logMessageList;
        }catch (Exception ex){
            ex.printStackTrace();
            utilKeywordScript.redirectHomePage();
            logMessageList.add(new LogMessage(false, "Exception occurred while creating multiple leases" + ex.getMessage()));
            return logMessageList;
        }

    }

    public LogMessage createLease(Map data){

        try {
            WebDriverWait wait = new WebDriverWait(webDriver, 5*60);
            String[] dropDownFields = new String[] {"leaseStatus","leaseType","billingType","leaseGroup1"};
            String[] textFields = new String[] {"dbaName","leaseCode","beginDate","expirationDate"};
            Map objectLocatorData = ObjectLocatorDataStorage.getObjectLocator(objectlocatorPrefix + "propertyList");
            uiBase = new UIBase(webDriver);
            UIText uiText = new UIText(webDriver);


            UIDropDown uiDropDown = new UIDropDown(webDriver);
            uiDropDown.searchAndSelectItem(objectlocatorPrefix + "propertyName",(String) objectLocatorData.get(PropertyConfig.PARENT_LOCATOR),(String)data.get("propertyName"));

            uiBase.WaitingForPageLoad();

            for(String elementName: textFields) {
                uiText.SetText(objectlocatorPrefix + elementName, (String) data.get(elementName));
                UtilKeywordScript.delay(5);
            }
            String objectLocatorName="";
            for(String elementName : dropDownFields) {
                for (Object key : data.keySet()) {
                    String temp = (String) key;
                    if (temp.contains(elementName)) {
                        objectLocatorName=elementName;
                        elementName = temp;
                        break;
                    }
                }
                uiDropDown.SelectItem(objectlocatorPrefix + objectLocatorName, (String)data.get(elementName));
                UtilKeywordScript.delay(5);
            }

            uiBase.Click(objectlocatorPrefix + "saveButton");

            //uiBase.WaitingForPageLoad();

            LogMessage lm = uiBase.WaitingForSuccessfullPopup();

            UtilKeywordScript.delay( 5);
            if(lm.isPassed())
                return new LogMessage(lm.isPassed(),"Lease "+data.get("dbaName") +" is created successfully under "+data.get("propertyName"));
            else
                return new LogMessage(lm.isPassed(),"Lease "+data.get("dbaName") +" is not created under "+data.get("propertyName"));
        }catch (Exception ex){
            ex.printStackTrace();
            return new LogMessage(false, "Lease "+data.get("dbaName") +" is not created under "+data.get("propertyName"));
        }

    }

    public List<LogMessage> createMultipleSpaces(List<Map> datas)
    {
        List<LogMessage> logMessageList = new ArrayList<>();
        UtilKeywordScript utilKeywordScript = new UtilKeywordScript(webDriver);
        try{
            LeaseCreate leaseCreate = new LeaseCreate(webDriver);
            uiBase = new UIBase(webDriver);
            leaseCreate.searchLease(datas.get(0));
            UtilKeywordScript.delay(5);
            mainWindow = webDriver.getWindowHandle();
            for(Map data: datas)
            {
                LogMessage lm=createSpace(data);
                logMessageList.add(lm);
            }
            utilKeywordScript.redirectHomePage();
            return logMessageList;
        }catch(Exception e){
            e.printStackTrace();
            utilKeywordScript.redirectHomePage();
            logMessageList.add(new LogMessage(false, "Exception occurred while creating multiple spaces" + e.getMessage()));
            return logMessageList;
        }
    }
    public LogMessage createSpace(Map data){
        try{
            String  objectLocatorPrefix = "Common.Space." ;
            WebDriverWait wait = new WebDriverWait(webDriver, 5*60);

            UILink uiLink = new UILink(webDriver);

            UIText uiText = new UIText(webDriver);

            UtilKeywordScript.delay(1);
            uiLink.ClickLink("","Add New Space");
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
            LogMessage lm = uiBase.WaitingForSuccessfullPopup();
            UtilKeywordScript.delay(3);
            uiBase.Click(objectLocatorPrefix + "btnClose");
            UtilKeywordScript.delay(2);
            Set<String> set =webDriver.getWindowHandles();
            Iterator<String> itr= set.iterator();
            while(itr.hasNext()){
                String childWindow=itr.next();
                if(!mainWindow.equals(childWindow)){
                    webDriver.switchTo().window(childWindow);
                    webDriver.close();
                }

            }
            webDriver.switchTo().window(mainWindow);
            if(lm.isPassed())
                return new LogMessage(lm.isPassed(),"Space "+data.get("Space") +" is created successfully under "+data.get("LeaseName"));
            else
                return new LogMessage(lm.isPassed(),"Space "+data.get("Space") +" is not created under "+data.get("LeaseName"));
        }catch (Exception e){
            e.printStackTrace();
            return new LogMessage(false,"Space "+data.get("Space") +" is not created under "+data.get("LeaseName"));
        }
    }
    public List<LogMessage> addMultipleRecurringPayments(List<Map> datas){
         List<LogMessage> logMessageList = new ArrayList<>();
        UtilKeywordScript utilKeywordScript = new UtilKeywordScript(webDriver);
         try{
             uiBase = new UIBase(webDriver);
             searchLease(datas.get(0));
             UtilKeywordScript.delay(5);
             mainWindow = webDriver.getWindowHandle();
             for(Map data: datas)
             {
                 LogMessage lm=addRecurringPayment(data);
                 logMessageList.add(lm);
             }
             utilKeywordScript.redirectHomePage();
            return logMessageList;
        }catch (Exception e){
            e.printStackTrace();
             utilKeywordScript.redirectHomePage();
            logMessageList.add(new LogMessage(false, "Exception occurred while creating multiple Recurring Payments" + e.getMessage()));
            return logMessageList;
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

            uiLink.ClickLink("","Add New");
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
            LogMessage lm = uiBase.WaitingForSuccessfullPopup();

            UtilKeywordScript.delay(3);

            uiBase.Click(objectLocatorPrefix + "btnClose");
            UtilKeywordScript.delay(2);
            Set<String> set =webDriver.getWindowHandles();
            Iterator<String> itr= set.iterator();
            while(itr.hasNext()){
                String childWindow=itr.next();
                if(!mainWindow.equals(childWindow)){
                    webDriver.switchTo().window(childWindow);
                    webDriver.close();
                }

            }
            webDriver.switchTo().window(mainWindow);
            if(lm.isPassed())
                return new LogMessage(lm.isPassed(),"Reccurring Payment of "+data.get("chargeType") +" is created successfully under "+data.get("spaceInfo"));
            else
                return new LogMessage(lm.isPassed(),"Reccurring Payment of "+data.get("chargeType") +" is not created under "+data.get("spaceInfo"));
        }catch (Exception e){
            e.printStackTrace();
            return new LogMessage(false,"Reccurring Payment of "+data.get("chargeType") +" is not created under "+data.get("spaceInfo"));
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

    public LogMessage isLeaseExistWithinProperty(Map data){
        try{
            String  objectLocatorPrefix = "Common.Property.";
            String columnName = "Property Name";
            String columnValue = (String)data.get("propertyName");
            UITable uiTable  = new UITable(webDriver);
            UILink uiLink = new UILink(webDriver);
            UtilKeywordScript utilKeywordScript = new UtilKeywordScript(webDriver);


            LogMessage searchLog = utilKeywordScript.globalSearch((String)data.get("propertyCode"),"Property");

            if (searchLog.isPassed()){
                Map<String, WebElement> propertyRow = uiTable.getSingleRowfromTable(objectLocatorPrefix +"tbProperty", "Property Code",(String)data.get("propertyCode"),null);
                if(null == propertyRow || propertyRow.isEmpty()){
                    return new LogMessage(false, "Property not found");
                }
                for (String key : propertyRow.keySet()) {
                    if(key.split(",").length<2)
                        continue;
                    String clName = key.split(",")[1];
                    if(columnName.equals(clName)){
                        System.out.println("1");
                        WebElement element = propertyRow.get(key) ;
                        String text = element.getText();
                        if(columnValue.equals(text)) {
                            System.out.println("if: ");
                            WebElement elm = element.findElement(By.linkText(columnValue));
                            elm.click();
                        }
                        else {
                            return new LogMessage(false, "Property name not matching");

                        }
                    }
                }
            }else {
                return new LogMessage(false,"Exception occur in global  search");
            }

            UtilKeywordScript.delay(10);
            webDriver.close();
            UtilKeywordScript.switchLastTab(webDriver);
            UtilKeywordScript.delay(10);
            LogMessage clickLinkLog = uiLink.ClickLink(null,"Expand All");
            if (clickLinkLog.isPassed()){
                UtilKeywordScript.delay(2);

                Map<String, WebElement> row = uiTable.getSingleRowfromTable(objectLocatorPrefix +"tbLease", "DBA Name",(String)data.get("dbaName"),null);
                if(null == row || row.isEmpty()){
                    return new LogMessage(false, "Lease not exist");
                }
                else{
                    return new LogMessage(true, "Lease already exist");
                }
            }else {
                return new LogMessage(false, "exception occur during expanding property information");
            }


        }catch (Exception e){
            e.printStackTrace();
            return new LogMessage(false, "Exception occur" + e.getMessage());

        }
    }

}
