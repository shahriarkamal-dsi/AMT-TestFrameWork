package test.beforeTest;

import org.apache.xmlbeans.impl.xb.xsdschema.Public;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import test.Log.LogMessage;
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
