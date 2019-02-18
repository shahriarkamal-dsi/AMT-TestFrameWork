package test.beforeTest;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import test.Log.LogMessage;
import test.keywordScripts.UIBase;
import test.keywordScripts.UILink;
import test.keywordScripts.UIText;
import test.keywordScripts.UtilKeywordScript;
import test.objectLocator.WebObjectSearch;

import java.util.List;
import java.util.Map;

public class SpaceCreate {

    private WebDriver webDriver;

    public SpaceCreate(org.openqa.selenium.WebDriver driver){
        this.webDriver = driver;
    }

    public SpaceCreate(){

    }

    public LogMessage createSpace(Map data){
        try{
            String  objectLocatorPrefix = "Common.Space." ;
            WebDriverWait wait = new WebDriverWait(webDriver, 5*60);

            UILink uiLink = new UILink(webDriver);
            UIBase uiBase = new UIBase(webDriver);
            UIText uiText = new UIText(webDriver);
            UtilKeywordScript utilKeywordScript = new UtilKeywordScript(webDriver);

            LeaseCreate leaseCreate = new LeaseCreate(webDriver);
            leaseCreate.searchLease(data);
            UtilKeywordScript.delay(5);

            uiLink.clickLink("","Add New Suite");

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
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(@class,'alert-success')]")));
            wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[contains(@class,'alert-success')]")));
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


}
