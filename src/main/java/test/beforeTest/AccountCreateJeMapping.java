package test.beforeTest;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import test.Log.LogMessage;
import test.keywordScripts.*;

import java.util.Map;

public class AccountCreateJeMapping {

    private WebDriver webDriver ;

    public AccountCreateJeMapping(WebDriver  webDriver) {
        this.webDriver = webDriver ;
    }

    public void createAccount(Map data) {
        try {
            UIBase uibase = new UIBase(webDriver);
            UIMenu uiMenu = new UIMenu(webDriver);

            uiMenu.SelectMenu("Common.Homepage.pgAMTHome" , "Accounting,Accounting Setup") ;
            uibase.WaitingForPageLoad();
           // WaitingForPageLoad();
            UtilKeywordScript.delay(3);
            uiMenu.SelectMenu("","Account Numbers");
            uibase.WaitingForPageLoad();
            UtilKeywordScript.delay(10);
            webDriver.findElement(By.linkText("Add New")).click();
            UITable uiTable = new UITable(webDriver);
            uiTable.ClickCellData("Common.GlobalSearch.accountTable","*Account Number,0,test");
            UtilKeywordScript.delay(1);
            uiTable.EnterCellData("Common.GlobalSearch.accountTable","*Account Number,0,test");
            UtilKeywordScript.delay(2);
            uiTable.ClickCellData("Common.GlobalSearch.accountTable","*Description,0,test");
            UtilKeywordScript.delay(1);
            uiTable.EnterCellData("Common.GlobalSearch.accountTable","*Description,0,test");
            UtilKeywordScript.delay(2);
            webDriver.findElement(By.linkText("Update")).click();
            uibase.WaitingForPageLoad();

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    public void  createJEMappping(Map data) {
        try {

            UIBase uibase = new UIBase(webDriver);
            WebDriverWait wait = new WebDriverWait(webDriver, 5*60);

            UIMenu uiMenu = new UIMenu(webDriver);
            uiMenu.SelectMenu("","JE Mapping");
            uibase.WaitingForPageLoad();
            UtilKeywordScript.delay(10);
            webDriver.findElement(By.linkText("Add New")).click();
            UtilKeywordScript.delay(3);
            UIDropDown dropDown = new UIDropDown(webDriver);
            dropDown.SelectItem("Common.GlobalSearch.contractType","Real Estate Contract - DEFAULT");

            UITable uiTable = new UITable(webDriver);
            uiTable.ClickCellData("Common.GlobalSearch.jeMappingTable","*Payment Type,0,test");
            UtilKeywordScript.delay(1);
            dropDown.SelectItem("Common.GlobalSearch.paymentType","CAM - Common Area Maintenance");
            LogMessage lm =   uiTable.ClickCellData("Common.GlobalSearch.jeMappingTable","Expense Credit,0,test");
            System.out.print(lm.getLogMessage());
            UtilKeywordScript.delay(1);
            uiTable.ClickCellData("Common.GlobalSearch.jeMappingTable","Expense Credit,0,test,button");
            uibase.WaitingForPageLoad();
            UtilKeywordScript.delay(2);
            uiTable.filterTableByColumn("Common.GlobalSearch.jMappingAccountTable","Account Number,101");
            UtilKeywordScript.delay(4);
            uiTable.ClickCellInTable("Common.GlobalSearch.jMappingAccountTable","Account Number,101");        //FASB/IASB - Expense Debit - Financing


            UtilKeywordScript.delay(2);

             lm =   uiTable.ClickCellData("Common.GlobalSearch.jeMappingTable","FASB/IASB - Expense Debit - Financing,0,test");
            System.out.print(lm.getLogMessage());
            UtilKeywordScript.delay(1);
            uiTable.ClickCellData("Common.GlobalSearch.jeMappingTable","FASB/IASB - Expense Debit - Financing,0,test,button");
            uibase.WaitingForPageLoad();
            UtilKeywordScript.delay(2);
            uiTable.filterTableByColumn("Common.GlobalSearch.jMappingAccountTable","Account Number,101");
            UtilKeywordScript.delay(4);
            uiTable.ClickCellInTable("Common.GlobalSearch.jMappingAccountTable","Account Number,101");
            UtilKeywordScript.delay(2);
            webDriver.findElement(By.linkText("Update")).click();
            uibase.WaitingForSuccessfullPopup();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }




}
