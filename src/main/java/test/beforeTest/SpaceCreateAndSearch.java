package test.beforeTest;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import test.Log.LogMessage;
import test.keywordScripts.*;
import test.objectLocator.WebObjectSearch;

import java.util.*;

public class SpaceCreateAndSearch {
    private WebDriver webDriver;
    private UIBase uiBase;
    private String mainWindow;
    public SpaceCreateAndSearch(WebDriver driver){
        this.webDriver = driver;
    }
    public List<LogMessage> createMultipleSpaces(List<Map> datas)
    {
        List<LogMessage> logMessageList = new ArrayList<>();
        UtilKeywordScript utilKeywordScript = new UtilKeywordScript(webDriver);
        try{
            LeaseCreateAndSearch leaseCreateAndSearch = new LeaseCreateAndSearch(webDriver);
            uiBase = new UIBase(webDriver);
            leaseCreateAndSearch.searchLease(datas.get(0));
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
    public LogMessage isSpaceExistWithinProperty(Map data){
        try{
            String  objectLocatorPrefix = "Common.Property.";
            UIPanel uiPanel = new UIPanel(webDriver);
            UILink uiLink = new UILink(webDriver);
            PropertyCreateAndSearch propertyCreateAndSearch = new PropertyCreateAndSearch(webDriver);

            LogMessage navigationLog = propertyCreateAndSearch.navigateToProperty(data);
            if (!navigationLog.isPassed())
                return new LogMessage(false, "Incomplete navigation");

            LogMessage clickLinkLog = uiLink.ClickLink(null,"Expand All");

            if (!clickLinkLog.isPassed())
                return new LogMessage(false, "exception occur during expanding property information");

            UtilKeywordScript.delay(2);
            LogMessage log = uiPanel.VerifyPanelContentTrue(objectLocatorPrefix +"tbSpace", (String)data.get("Space"));
            if (log.isPassed()){
                return new LogMessage(true, "Space already exist");
            }else {
                return new LogMessage(false, "Space not exist");
            }

        }catch (Exception e){
            e.printStackTrace();
            return new LogMessage(false, "Exception occur" + e.getMessage());

        }
    }
}
