package test.beforeTest;

import org.apache.xmlbeans.impl.xb.xsdschema.Public;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import test.Log.LogMessage;
import test.keywordScripts.*;
import test.objectLocator.ObjectLocatorDataStorage;
import test.utility.PropertyConfig;

import java.util.List;
import java.util.Map;

public class AccountCreateJeMapping {

    private WebDriver webDriver ;

    public AccountCreateJeMapping(WebDriver  webDriver) {
        this.webDriver = webDriver ;
    }

    public void createAccount(Map data) {
        try {


            WebDriverWait wait = new WebDriverWait(webDriver, 5*60);
            UtilKeywordScript.delay(3);
            webDriver.findElement(By.linkText("Add New")).click();
            UtilKeywordScript.delay(2);
            UITable uiTable = new UITable(webDriver);
            uiTable.ClickCellData("Common.GlobalSearch.accountTable","*Account Number,0,"+(String)data.get("Account Number"));
            UtilKeywordScript.delay(1);
            uiTable.EnterCellData("Common.GlobalSearch.accountTable","*Account Number,0,"+(String)data.get("Account Number"));
            UtilKeywordScript.delay(2);
            uiTable.ClickCellData("Common.GlobalSearch.accountTable","*Description,0,"+(String)data.get("Description"));
            UtilKeywordScript.delay(1);
            uiTable.EnterCellData("Common.GlobalSearch.accountTable","*Description,0,"+(String)data.get("Description"));
            UtilKeywordScript.delay(2);

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }
    public void createAccounts(List<Map> accountDatas, String chartOfAccount){
        try {
            UIBase uibase = new UIBase(webDriver);
            UIMenu uiMenu = new UIMenu(webDriver);

            uiMenu.SelectMenu("Common.Homepage.pgAMTHome" , "Accounting,Accounting Setup") ;
            uibase.WaitingForPageLoad();
            UtilKeywordScript.delay(3);
            uiMenu.SelectMenu("","Account Numbers");
            uibase.WaitingForPageLoad();
            UIDropDown uiDropDown = new UIDropDown(webDriver);
            Map objectLocatorData = ObjectLocatorDataStorage.getObjectLocator("Common.GlobalSearch.chartOfAccountList");
            uiDropDown.searchAndSelectItem("Common.GlobalSearch.chartOfAccountInput", (String) objectLocatorData.get(PropertyConfig.PARENT_LOCATOR), chartOfAccount);
            UtilKeywordScript.delay(10);
            for (Map accountData : accountDatas) {
                if (null == accountData.get(PropertyConfig.EXECUTION_FLAG) || accountData.get(PropertyConfig.EXECUTION_FLAG).toString().isEmpty() || !accountData.get(PropertyConfig.EXECUTION_FLAG).toString().toLowerCase().equals("yes"))
                    continue;
                else {
                    createAccount(accountData);
                    UtilKeywordScript.delay(1);
                }
            }
            webDriver.findElement(By.linkText("Update")).click();
            uibase.WaitingForPageLoad();
            UtilKeywordScript.delay(2);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    public void  createJEMappping(List<Map> accountDatas , Map JMappingData) {
        try {

            UIBase uibase = new UIBase(webDriver);
            WebDriverWait wait = new WebDriverWait(webDriver, 5*60);

            UIMenu uiMenu = new UIMenu(webDriver);
            uiMenu.SelectMenu("","JE Mapping");
            uibase.WaitingForPageLoad();
            UtilKeywordScript.delay(05);
            UIDropDown uiDropDown = new UIDropDown(webDriver);
            Map objectLocatorData = ObjectLocatorDataStorage.getObjectLocator("Common.GlobalSearch.leaseTypes");

            uiDropDown.SelectItem("Common.GlobalSearch.leaseTypes", (String) objectLocatorData.get(PropertyConfig.PARENT_LOCATOR), (String) JMappingData.get("Lease Types"));
            uibase.WaitingForPageLoad();
            webDriver.findElement(By.linkText("Add New")).click();
            UtilKeywordScript.delay(3);
            UIDropDown dropDown = new UIDropDown(webDriver);
            dropDown.SelectItem("Common.GlobalSearch.contractType", (String) JMappingData.get("Contract Type"));

            UITable uiTable = new UITable(webDriver);
            uiTable.ClickCellData("Common.GlobalSearch.jeMappingTable","*Payment Type,0,test");
            UtilKeywordScript.delay(1);
            dropDown.SelectItem("Common.GlobalSearch.paymentType",(String) JMappingData.get("Payment Type"));
            for(Map accountData: accountDatas) {
                LogMessage lm = uiTable.ClickCellData("Common.GlobalSearch.jeMappingTable", accountData.get("Description")+",0,test");
                UtilKeywordScript.delay(1);
                uiTable.ClickCellData("Common.GlobalSearch.jeMappingTable", accountData.get("Description")+",0,test,button");
                uibase.WaitingForPageLoad();
                UtilKeywordScript.delay(2);
                uiTable.filterTableByColumn("Common.GlobalSearch.jMappingAccountTable", "Account Number,"+accountData.get("Account Number"));
                UtilKeywordScript.delay(4);
                uiTable.ClickCellInTable("Common.GlobalSearch.jMappingAccountTable", "Account Number,"+accountData.get("Account Number"));        //FASB/IASB - Expense Debit - Financing
                UtilKeywordScript.delay(2);
            }
            webDriver.findElement(By.linkText("Update")).click();
            uibase.WaitingForSuccessfullPopup();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }



}
