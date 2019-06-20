package test.beforeTest;

import org.json.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import test.Log.LogMessage;
import test.keywordScripts.*;
import test.objectLocator.WebObjectSearch;
import test.utility.PropertyConfig;

import java.util.*;

@Component
public class SpaceCreateAndSearch {
    private WebDriver webDriver;
    private UIBase uiBase;
    private String mainWindow;
    @Autowired
    LeaseCreateAndSearch _leaseCreateAndSearch ;
    @Autowired
    PropertyCreateAndSearch _propertyCreateAndSearch ;
    public SpaceCreateAndSearch(){

    }
    public void setDriver(WebDriver wbd) {
        this.webDriver = wbd ;
        _leaseCreateAndSearch.setDriver(webDriver);
        _propertyCreateAndSearch.setDriver(webDriver);
    }
    public List<LogMessage> createMultipleSpaces(List<Map> datas)
    {
        List<LogMessage> logMessageList = new ArrayList<>();
        UtilKeywordScript utilKeywordScript = new UtilKeywordScript(webDriver);
        try{
            LeaseCreateAndSearch leaseCreateAndSearch = _leaseCreateAndSearch ;
            uiBase = new UIBase(webDriver);
            leaseCreateAndSearch.searchLease(datas.get(0));
            UtilKeywordScript.delay(PropertyConfig.SHORT_WAIT_TIME_SECONDS);
            mainWindow = webDriver.getWindowHandle();
            for(Map data: datas)
            {
                LogMessage lm=createSpace(data);
                JSONObject jso = new JSONObject();
                jso.put("Space",data.get("Space") );
                jso.put("dataId",data.get("dataId") );
                jso.put("type","space" );
                jso.put("isPassed",lm.isPassed() );
                lm.addJsonObject(jso);
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
            UILink uiLink = new UILink(webDriver);
            UIInput uiInput = new UIInput(webDriver);
            UIText uiText = new UIText(webDriver);

            UtilKeywordScript.delay(PropertyConfig.ONE_SECOND);
            WebElement addSpaceElement=WebObjectSearch.getWebElement(webDriver,objectLocatorPrefix+"btnNewSpace");
            uiBase.Click(addSpaceElement);
            UtilKeywordScript.delay(PropertyConfig.SHORT_WAIT_TIME_SECONDS);
            UtilKeywordScript.switchLastTab(webDriver);
            webDriver.manage().window().maximize();

            UtilKeywordScript.delay(PropertyConfig.ONE_SECOND);

            uiText.SetText(objectLocatorPrefix +"space",(String)data.get("Space"));
            uiText.SetText(objectLocatorPrefix +"floor",(String)data.get("Floor"));

            List<WebElement>  linkedItems  = WebObjectSearch.getWebElements(webDriver, objectLocatorPrefix + "linked");
            uiBase.Click(linkedItems.get(0));
            UtilKeywordScript.delay(PropertyConfig.ONE_SECOND);
            uiText.SetText(objectLocatorPrefix+"startDate", (String) data.get("startDate"));
            UtilKeywordScript.delay(PropertyConfig.ONE_SECOND);
            uiText.SetText(objectLocatorPrefix+"endDate", (String) data.get("endDate"));
            UtilKeywordScript.delay(PropertyConfig.ONE_SECOND);

            WebElement rentableLease = (webDriver.findElements(By.xpath("//input[contains(@id,'__RENTABLE_SQFT')]/preceding-sibling::input"))).get(0);
            rentableLease.sendKeys((String) data.get("rentableLease"));

            UtilKeywordScript.delay(PropertyConfig.ONE_SECOND);
            uiBase.Click(objectLocatorPrefix + "btnSave");

            uiBase.WaitingForPageLoad();
            LogMessage lm = uiBase.WaitingForSuccessfullPopup();
            UtilKeywordScript.delay(PropertyConfig.ONE_SECOND*3);
            uiBase.Click(objectLocatorPrefix + "btnClose");
            UtilKeywordScript.delay(PropertyConfig.ONE_SECOND*2);
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
            PropertyCreateAndSearch propertyCreateAndSearch = _propertyCreateAndSearch ;

            LogMessage navigationLog = propertyCreateAndSearch.navigateToProperty(data);
            if (!navigationLog.isPassed())
                return new LogMessage(false, "Incomplete navigation");

            LogMessage clickLinkLog = uiLink.ClickLink(null,"Expand All");

            if (!clickLinkLog.isPassed())
                return new LogMessage(false, "exception occur during expanding property information");

            UtilKeywordScript.delay(PropertyConfig.ONE_SECOND*2);
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
    public LogMessage navigateToSpacePageFromProperty(String spaceName){
        String  objectLocatorPrefix = "Common.Property.";
        UILink uiLink = new UILink(webDriver);
        LogMessage log =uiLink.ClickLink(objectLocatorPrefix +"tbSpace", spaceName);
        UtilKeywordScript.delay(PropertyConfig.SHORT_WAIT_TIME_SECONDS);
        UtilKeywordScript.switchLastTab(webDriver);
        if (log.isPassed()){
            return new LogMessage(true, "Navigated to Space page");
        }else {
            return new LogMessage(false, "Space does not exist");
        }
    }
    public List<LogMessage> deleteSpace(String propertyName,String propertyCode,List<Map> spaceNames){
        List<LogMessage> logMessages=new ArrayList<>();
        UtilKeywordScript utilKeywordScript=new UtilKeywordScript(webDriver);
        try {
            PropertyCreateAndSearch propertyCreateAndSearch = _propertyCreateAndSearch ;
            UIBase uiBase=new UIBase(webDriver);
            //Lease and space delete have the same object locator
            String objectLocatorData="Common.Lease.";
            Map<String, String> data = new HashMap<>();
            data.put("propertyName", propertyName);
            data.put("propertyCode", propertyCode);
            LogMessage logMessageProperty = propertyCreateAndSearch.navigateToProperty(data);
            String mainWindow=webDriver.getWindowHandle();
            UtilKeywordScript.delay(PropertyConfig.SHORT_WAIT_TIME_SECONDS);
            if(logMessageProperty.isPassed()) {
                for (Map spaceName : spaceNames) {
                    LogMessage logMessageSpace = navigateToSpacePageFromProperty((String) spaceName.get("Space"));
                    UtilKeywordScript.delay(PropertyConfig.SHORT_WAIT_TIME_SECONDS);
                    if (logMessageSpace.isPassed()) {
                        WebElement webElement = WebObjectSearch.getChildWebElement(webDriver, objectLocatorData + "header", objectLocatorData + "delete");
                        uiBase.Click(webElement);
                        while (utilKeywordScript.isAlertPresent()) {
                            webDriver.switchTo().alert().accept();
                        }
                        LogMessage logMessage = uiBase.WaitingForSuccessfullPopup();
                        webDriver.close();
                        utilKeywordScript.switchToPreviousTab(webDriver,mainWindow);
                        if (logMessage.isPassed()) {
                            logMessages.add(new LogMessage(true, "Space is deleted"));
                        }
                        else {
                            logMessages.add(new LogMessage(false, "Space cannot be deleted"));
                        }
                    } else
                        logMessages.add(new LogMessage(false, "Space doesnot exist"));

                }
            }
            else {
                logMessages.add(new LogMessage(false, "Property doesnot exist"));
            }
            utilKeywordScript.redirectHomePage();
            UtilKeywordScript.delay(PropertyConfig.ONE_SECOND*2);
            return logMessages;
        }catch (Exception e){
            e.printStackTrace();
            utilKeywordScript.redirectHomePage();
            logMessages.add(new LogMessage(false,"Exception occur in space delete "+e.getMessage()));
            return logMessages;
        }
    }
}