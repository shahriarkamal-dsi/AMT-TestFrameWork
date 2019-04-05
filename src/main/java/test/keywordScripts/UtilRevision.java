package test.keywordScripts;

import org.apache.xmlbeans.impl.xb.xsdschema.Public;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import test.Log.LogMessage;
import test.objectLocator.WebObjectSearch;

public class UtilRevision {

    private WebDriver webDriver ;

    public UtilRevision() {

    }

    public UtilRevision(WebDriver webDriver) {
        this.webDriver = webDriver ;
    }

    public LogMessage validateProcessing(){
        LogMessage log = new LogMessage();
        try{
            String objectlocatorPrefix = "FASB.FIProcess.";
            UIText uiText = new UIText(webDriver);
            UILink uiLink = new UILink(webDriver);
            UIBase uiBase = new UIBase(webDriver);
            UIPanel uiPanel = new UIPanel(webDriver);
            LogMessage subLog = uiText.WaitForVisibilityOfText(objectlocatorPrefix + "processInfoPanel","% Complete,60");
            log.setSubLogMessage(subLog);
            subLog = uiPanel.VerifyPanelContentTrue(objectlocatorPrefix + "processInfoPanel","Position in Queue:");
            log.setSubLogMessage(subLog);
            WebElement element = WebObjectSearch.getWebElement(webDriver, objectlocatorPrefix + "queueCount");
            String countString = element.getText().toString();
            subLog = uiBase.compareGreaterThanValue(countString + ",0");
            log.setSubLogMessage(subLog);
            subLog = uiLink.verifyLinkEnabledFalse(objectlocatorPrefix + "lnkContinueWithProcess");
            log.setSubLogMessage(subLog);
            subLog = uiLink.verifyLinkEnabledFalse(objectlocatorPrefix + "lnkPrint");
            log.setSubLogMessage(subLog);
            subLog = uiLink.verifyLinkEnabledTrue(objectlocatorPrefix + "lnkCancel");
            log.setSubLogMessage(subLog);
            subLog = uiPanel.VerifyPanelContentTrue(objectlocatorPrefix + "processCount","Ready To Process 0 Revisions");
            log.setSubLogMessage(subLog);
            subLog = uiText.WaitForInvisibilityOfText(objectlocatorPrefix + "processInfoPanel","% Complete ,60");
            log.setSubLogMessage(subLog);
            if (subLog.isPassed()){
                log.setPassed(true);
                log.setLogMessage("Processing done");
                return log;
            };
            log.setPassed(false);
            log.setLogMessage("Processing fail");
            return log;
        }catch (Exception e){
            log.setLogMessage("Exception occur");
            return log;
        }
    }

    public LogMessage validateProcessingComplete(){
        try{


            return new LogMessage(true,"");
        }catch (Exception e){
            return new LogMessage(false,"Exception occur");
        }
    }

}
