package test.keywordScripts;

import org.apache.xmlbeans.impl.xb.xsdschema.Public;
import org.openqa.selenium.WebDriver;
import test.Log.LogMessage;

public class RevisionUtility {

    private WebDriver webDriver ;

    public RevisionUtility() {

    }

    public  RevisionUtility(WebDriver webDriver) {
        this.webDriver = webDriver ;
    }

    public LogMessage validateProcessing(){
        try{
            String objectlocatorPrefix = "FASB.FIProcess.";
            UIText uiText = new UIText(webDriver);
            UIBase uiBase = new UIBase(webDriver);
            LogMessage log0 = uiText.WaitForVisibilityOfText(objectlocatorPrefix + "processInfoPanel","% Complete ,60");
            LogMessage log = uiText.WaitForVisibilityOfText(objectlocatorPrefix + "processInfoPanel","Position in Queue:");
            LogMessage log1 = uiBase.VerifyEnabledTrue(objectlocatorPrefix + "lnkContinueWithProcess");
            if (!log1.isPassed()){
                System.out.println("lnkContinueWithProcess");
            }
            LogMessage log2 = uiBase.VerifyEnabledTrue(objectlocatorPrefix + "lnkPrint");
            if (!log2.isPassed()){
                System.out.println("lnkPrint");
            }
            LogMessage log3 = uiBase.VerifyEnabledTrue(objectlocatorPrefix + "lnkCancel");
            if (log3.isPassed()){
                System.out.println("lnkCancel");
            }
            LogMessage log4 = uiText.WaitForVisibilityOfText(objectlocatorPrefix + "processInfoPanel","Ready To Process 0 Revisions");
            if (log4.isPassed()){
                System.out.println("Ready To Process 0 Revisions");
            }
            LogMessage log5 = uiText.WaitForInvisibilityOfText(objectlocatorPrefix + "processInfoPanel","% Complete ,60");
            if (log5.isPassed()){
                return new LogMessage(true,"Processing done");
            }
            return new LogMessage(false,"Processing fail");
        }catch (Exception e){
            return new LogMessage(false,"Exception occur");
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
