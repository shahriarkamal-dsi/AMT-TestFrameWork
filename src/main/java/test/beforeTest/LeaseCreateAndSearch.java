package test.beforeTest;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import test.Log.LogMessage;
import test.keywordScripts.*;
import test.objectLocator.ObjectLocatorDataStorage;
import test.utility.PropertyConfig;

import java.util.*;

public class LeaseCreateAndSearch {

    private WebDriver webDriver;
    private String  objectlocatorPrefix;
    private UIBase uiBase;
    private String mainWindow;
    public LeaseCreateAndSearch(WebDriver driver){
        this.webDriver = driver;
    }

    public LeaseCreateAndSearch(){

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
            UtilKeywordScript.delay(PropertyConfig.WAIT_TIME_SECONDS*PropertyConfig.NUMBER_OF_ITERATIONS);
            webDriver.close();
            UtilKeywordScript.switchLastTab(webDriver);
            UtilKeywordScript.delay(PropertyConfig.WAIT_TIME_SECONDS*PropertyConfig.NUMBER_OF_ITERATIONS);

            return new LogMessage(true, " Lease found successfully");
        }catch (Exception e){
            e.printStackTrace();
            return new LogMessage(false, " Exception occurred " + e.getMessage());
        }

    }

    public LogMessage isLeaseExistWithinProperty(Map data){
        try{
            String  objectLocatorPrefix = "Common.Property.";
            UILink uiLink = new UILink(webDriver);
            PropertyCreateAndSearch propertyCreateAndSearch = new PropertyCreateAndSearch(webDriver);

            LogMessage navigationLog = propertyCreateAndSearch.navigateToProperty(data);
            if (!navigationLog.isPassed())
                return new LogMessage(false, "Incomplete navigation");

            LogMessage clickLinkLog = uiLink.ClickLink(null,"Expand All");

            if (!clickLinkLog.isPassed())
                return new LogMessage(false, "exception occur during expanding property information");

            UtilKeywordScript.delay(PropertyConfig.WAIT_TIME_SECONDS);

            UIPanel uiPanel = new UIPanel(webDriver);
            LogMessage log = uiPanel.VerifyPanelContentTrue(objectLocatorPrefix +"tbLease", (String)data.get("dbaName"));
            if (log.isPassed()){
                return new LogMessage(true, "Lease already exist");
            }else {
                return new LogMessage(false, "Lease not exist");
            }

        }catch (Exception e){
            e.printStackTrace();
            return new LogMessage(false, "Exception occur" + e.getMessage());
        }
    }




    public LogMessage navigateToLeasePageFromHome(Map data){
        try{
            String  objectLocatorPrefix = "Common.Property.";
            UILink uiLink = new UILink(webDriver);
            PropertyCreateAndSearch propertyCreateAndSearch = new PropertyCreateAndSearch(webDriver);

            LogMessage navigationLog = propertyCreateAndSearch.navigateToProperty(data);
            if (!navigationLog.isPassed())
                return new LogMessage(false, "Cannot navigate to property page");

            LogMessage clickLinkLog = uiLink.ClickLink(null,"Expand All");
            if (!clickLinkLog.isPassed())
                return new LogMessage(false, "exception occur during expanding property information");
            UtilKeywordScript.delay(PropertyConfig.WAIT_TIME_SECONDS);
            LogMessage logMessage=navigateToLeasePageFromProperty((String) data.get("dbaName"));
            if(logMessage.isPassed())
                return new LogMessage(true, "Navigated to lease page");
            else
                return new LogMessage(false, "Lease does not exist");

        }catch (Exception e){
            e.printStackTrace();
            return new LogMessage(false, "Exception occur" + e.getMessage());
        }
    }
    public LogMessage navigateToLeasePageFromProperty(String leaseName){
        String  objectLocatorPrefix = "Common.Property.";
        UILink uiLink = new UILink(webDriver);
        LogMessage log =uiLink.ClickLink(objectLocatorPrefix +"tbLease", leaseName);
        UtilKeywordScript.delay(PropertyConfig.WAIT_TIME_SECONDS);
        if (log.isPassed()){
            return new LogMessage(true, "Navigated to lease page");
        }else {
            return new LogMessage(false, "Lease does not exist");
        }
    }
    public List<LogMessage> isLeaseandSpaceExists(List<Map> leaseList, List<Map> spaceList, List<Map> recurList){
        List<LogMessage> logMessages= new ArrayList<>();
        try{
            PropertyCreateAndSearch propertyCreateAndSearch=new PropertyCreateAndSearch(webDriver);
            propertyCreateAndSearch.navigateToProperty(leaseList.get(0));
            String leaseObjectLocator="Common.Property.tbLease";
            String spaceObjectLocator="Common.Property.tbSpace";
            String recurObjectLocator="Common.Lease.tbRPayment";
            UILink uiLink=new UILink(webDriver);
            LeaseCreateAndSearch leaseCreateAndSearch =new LeaseCreateAndSearch(webDriver);
            LogMessage clickLinkLog = uiLink.ClickLink(null,"Expand All");

            if (!clickLinkLog.isPassed()){
                logMessages.add(new  LogMessage(false, "Error occur while expanding all in property named "+leaseList.get(0).get("propertyName")+" and code "+leaseList.get(0).get("propertyCode")));
                return logMessages;
            }
            UtilKeywordScript.delay(PropertyConfig.WAIT_TIME_SECONDS);

            UIPanel uiPanel = new UIPanel(webDriver);
            for(Map lease:leaseList){
                LogMessage logMessage=uiPanel.VerifyPanelContentTrue(leaseObjectLocator, (String) lease.get("dbaName"));
                if(logMessage.isPassed()) {
                    logMessages.add(new LogMessage(true, "Lease named " + lease.get("dbaName") + " found under " + lease.get("propertyName") + " and code " + lease.get("propertyCode")));
                }
                else {
                    logMessages.add(new LogMessage(false, "Lease named " + lease.get("dbaName") + " not found under " + lease.get("propertyName") + " and code " + lease.get("propertyCode")));
                }
            }
            for(Map space:spaceList){
                LogMessage logMessage=uiPanel.VerifyPanelContentTrue(spaceObjectLocator, (String) space.get("Space"));
                if(logMessage.isPassed()) {
                    logMessages.add(new LogMessage(true, "Space named " + space.get("Space") + " found under " + space.get("propertyName") + " and code " + space.get("propertyCode")));
                }
                else {
                    logMessages.add(new LogMessage(false, "Space named " + space.get("dbaName") + " not found under " + space.get("propertyName") + " and code " + space.get("propertyCode")));
                }
            }
            Map<String, List<Map>> allRecur = new HashMap<>();

            for (Map recur : recurList) {
                if (null == recur.get(PropertyConfig.EXECUTION_FLAG) || recur.get(PropertyConfig.EXECUTION_FLAG).toString().isEmpty() || !recur.get(PropertyConfig.EXECUTION_FLAG).toString().toLowerCase().equals("yes"))
                    continue;
                if (!allRecur.containsKey(recur.get("LeaseName"))) {
                    List<Map> record = new ArrayList<Map>();
                    record.add(recur);
                    allRecur.put((String) recur.get("LeaseName"), record);
                } else {
                    allRecur.get(recur.get("LeaseName")).add(recur);
                }

            }
            for (String key : allRecur.keySet()) {
                leaseCreateAndSearch.navigateToLeasePageFromProperty(key);
                UtilKeywordScript.switchLastTab(webDriver);
                UIBase uiBase=new UIBase(webDriver);
                UtilKeywordScript.delay(PropertyConfig.WAIT_TIME_SECONDS);
                LogMessage log = uiLink.ClickLink(null,"Expand All");
                UtilKeywordScript.delay(PropertyConfig.WAIT_TIME_SECONDS);
                if (!log.isPassed()) {
                    logMessages.add(new LogMessage(false, "exception occur during expanding property information"));
                    return logMessages;
                }
                List<Map> recurUnderALease=allRecur.get(key);
                for(Map recur:recurUnderALease){
                    String chargetype=(String) recur.get("chargeType");
                    LogMessage logMessageofCharge=uiPanel.VerifyPanelContentTrue(recurObjectLocator, chargetype.split("-")[0].trim());
                    LogMessage logMessageofSpace=uiPanel.VerifyPanelContentTrue(recurObjectLocator, (String) recur.get("spaceInfo"));
                    if(logMessageofCharge.isPassed() && logMessageofSpace.isPassed()) {
                        logMessages.add(new LogMessage(true, "Recurring Payment with charge type " + recur.get("chargeType") + " found under lease named " + recur.get("LeaseName")));
                    }
                    else {
                        logMessages.add(new LogMessage(false, "Recurring Payment with charge type " + recur.get("chargeType") + " was not found under lease named " + recur.get("LeaseName")));
                    }
                }

            }
            return logMessages;
        }catch (Exception e){
            logMessages.add(new LogMessage(false, "Error occured in searching"+e.getMessage()));
            return logMessages;
        }
    }


}
