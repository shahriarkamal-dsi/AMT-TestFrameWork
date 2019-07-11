package test.keywordScripts;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import test.Log.LogMessage;
import test.coreModule.TestPlan;
import test.objectLocator.WebObjectSearch;

public class UICheckBox {
    private WebDriver webDriver;
    public UICheckBox(WebDriver driver) {
        this.webDriver = driver ;
    }

    public UICheckBox(){

    }

    public LogMessage CheckChkBox(String objectLocator){
        try{
            UIBase uiBase = new UIBase(webDriver);
            WebElement webElement = WebObjectSearch.getWebElement(webDriver,objectLocator);
            uiBase.Click(webElement);
            return new LogMessage(true,"CheckBox click successfully");
        }catch (Exception e){
            e.printStackTrace();
            return new LogMessage(false,"Exception occurred");
        }
    }

    public LogMessage VerifyCheckboxCheckedFalse(String objectLocatorData) {
        try {

            System.out.println("ObjectLocator :"+objectLocatorData);
            WebElement element = WebObjectSearch.getWebElement(webDriver, objectLocatorData);
            if (null == element)
                return new LogMessage(false, "UI element is not found");
            else {
                if (!element.isSelected())
                    return new LogMessage(true, "Checkbox is not Checked");
                else return new LogMessage(false, "Checkbox is Checked");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return new LogMessage(false, "Exception occurred : " + ex.getMessage());
        }
    }
    public LogMessage VerifyCheckboxCheckedTrue(String objectLocatorData) {
        try {
            WebElement element = WebObjectSearch.getWebElement(webDriver, objectLocatorData);
            if (null == element)
                return new LogMessage(false, "UI element is not found");
            else {
                if (element.isSelected())
                    return new LogMessage(true, "Checkbox is Checked");
                else return new LogMessage(false, "Checkbox is not Checked");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return new LogMessage(false, "Exception occurred : " + ex.getMessage());
        }
    }


    public LogMessage IsCheckboxChecked(String objectLocatorData) {
        return VerifyCheckboxCheckedFalse(objectLocatorData);
    }


}
