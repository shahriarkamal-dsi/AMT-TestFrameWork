package test.beforeTest;

import org.apache.xmlbeans.impl.xb.xsdschema.Public;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import test.Log.LogMessage;
import test.keywordScripts.UIDropDown;
import test.keywordScripts.UIMenu;
import test.keywordScripts.UITable;
import test.keywordScripts.UtilKeywordScript;

import java.util.Map;

public class AccountCreateJeMapping {

    private WebDriver webDriver ;

    public AccountCreateJeMapping(WebDriver  webDriver) {
        this.webDriver = webDriver ;
    }

    public void createAccount(Map data) {
        try {


            WebDriverWait wait = new WebDriverWait(webDriver, 5*60);

            UIMenu uiMenu = new UIMenu(webDriver);

            uiMenu.SelectMenu("Common.Homepage.pgAMTHome" , "Accounting,Accounting Setup") ;
            WaitingForPageLoad();
           // WaitingForPageLoad();
            UtilKeywordScript.delay(3);
            uiMenu.SelectMenu("","Account Numbers");
            WaitingForPageLoad();
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
            WaitingForPageLoad();

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    public void  createJEMappping(Map data) {
        try {


            WebDriverWait wait = new WebDriverWait(webDriver, 5*60);

            UIMenu uiMenu = new UIMenu(webDriver);
            uiMenu.SelectMenu("","JE Mapping");
            WaitingForPageLoad();
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
            WaitingForPageLoad();
            UtilKeywordScript.delay(2);
            uiTable.filterTableByColumn("Common.GlobalSearch.jMappingAccountTable","Account Number,101");
            UtilKeywordScript.delay(4);
            uiTable.ClickCellInTable("Common.GlobalSearch.jMappingAccountTable","Account Number,101");        //FASB/IASB - Expense Debit - Financing


            UtilKeywordScript.delay(2);

             lm =   uiTable.ClickCellData("Common.GlobalSearch.jeMappingTable","FASB/IASB - Expense Debit - Financing,0,test");
            System.out.print(lm.getLogMessage());
            UtilKeywordScript.delay(1);
            uiTable.ClickCellData("Common.GlobalSearch.jeMappingTable","FASB/IASB - Expense Debit - Financing,0,test,button");
            WaitingForPageLoad();
            UtilKeywordScript.delay(2);
            uiTable.filterTableByColumn("Common.GlobalSearch.jMappingAccountTable","Account Number,101");
            UtilKeywordScript.delay(4);
            uiTable.ClickCellInTable("Common.GlobalSearch.jMappingAccountTable","Account Number,101");        //FASB/IASB - Expense Debit - Financing


  /*
            uiTable.EnterCellData("Common.GlobalSearch.accountTable","*Account Number,0,test");
            UtilKeywordScript.delay(2);
            uiTable.ClickCellData("Common.GlobalSearch.accountTable","*Description,0,test");
            UtilKeywordScript.delay(1);
            uiTable.EnterCellData("Common.GlobalSearch.accountTable","*Description,0,test");
            UtilKeywordScript.delay(2);
            webDriver.findElement(By.linkText("Update")).click();
            WaitingForPageLoad();
*/
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }


    public LogMessage WaitingForPageLoad(){
        try{
            WebDriverWait wait = new WebDriverWait(webDriver, 5*60);
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("splashScr")));
            wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("splashScr")));
            return new LogMessage(true,"Page load successfully");
        }catch (Exception e){
            e.printStackTrace();
            return new LogMessage(false, "Exception occurred during loading page");
        }
    }

}
