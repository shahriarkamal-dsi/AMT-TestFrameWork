package test.beforeTest;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import test.Log.LogMessage;
import test.keywordScripts.UIDropDown;
import test.keywordScripts.UIMenu;
import test.objectLocator.WebObjectSearch;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class LeaseCreate {

    private WebDriver webDriver;

    public LeaseCreate(WebDriver driver){
        this.webDriver = driver;
    }

    public LeaseCreate(){

    }

    public LogMessage createLease(Map data){

        try {

            String  objectlocatorPrefix = "Common.Lease." ;
            UIMenu menu = new UIMenu(webDriver);
            menu.SelectMenu("Common.Homepage.pgAMTHome" , "Portfolio Insight,Add,Lease,DEFAULT,Real Estate Contract");
            switchTabs(webDriver);
            webDriver.manage().window().maximize();

            Thread.sleep(10*1000);
            WebElement propertyList = WebObjectSearch.getWebElement(webDriver,objectlocatorPrefix+"propertyList");
            propertyList.click();
            Thread.sleep(2*1000);

            select(data,objectlocatorPrefix +"propertyName","propertyName");


            Thread.sleep(10*1000);

            WebElement dbaName = WebObjectSearch.getWebElement(webDriver,objectlocatorPrefix+"dbaName");
            dbaName.sendKeys((String)data.get("dbaName"));

            WebElement leaseCode = WebObjectSearch.getWebElement(webDriver, objectlocatorPrefix+"leaseCode");
            leaseCode.sendKeys((String)data.get("leaseCode"));

            UIDropDown uiDropDown = new UIDropDown(webDriver);

            uiDropDown.SelectItem(objectlocatorPrefix+"leaseStatus",(String)data.get("leaseStatus"));
            uiDropDown.SelectItem(objectlocatorPrefix+"leaseType",(String)data.get("leaseType"));
            uiDropDown.SelectItem(objectlocatorPrefix + "billingType",(String)data.get("billingType"));

            WebElement beginDate = WebObjectSearch.getWebElement(webDriver, objectlocatorPrefix+"beginDate");
            Thread.sleep(2*1000);
            beginDate.click();
            //Thread.sleep(5*1000);
            beginDate.sendKeys((String)data.get("beginDate"));

            WebElement expirationDate = WebObjectSearch.getWebElement(webDriver, objectlocatorPrefix+"expirationDate");
            Thread.sleep(2*1000);
            expirationDate.sendKeys((String)data.get("expirationDate"));

            uiDropDown.SelectItem(objectlocatorPrefix + "contractTerm", (String)data.get("contractTerm"));

            WebElement btnSave = WebObjectSearch.getWebElement(webDriver,objectlocatorPrefix + "saveButton");
            btnSave.click();

            return new LogMessage(true,"Lease create successfully");


        }catch (Exception ex){
            ex.printStackTrace();
            return new LogMessage(false, "Exception Occurred");
        }

    }

    public  void switchTabs(WebDriver webDriver) {
        try {
            Set<String> windows = webDriver.getWindowHandles();
            Iterator<String> iter = windows.iterator();
            String[] winNames=new String[windows.size()];
            int i=0;
            while (iter.hasNext()) {
                winNames[i]=iter.next();
                i++;
            }

            if(winNames.length > 1) {
                for(i = winNames.length; i > 1; i--) {
                    webDriver.switchTo().window(winNames[i - 1]);
                }
            }

        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public LogMessage select(Map data, String objectLocator, String propertyName){

        WebElement propertyListBox = WebObjectSearch.getWebElement(webDriver,objectLocator);
        List<WebElement> propertyNames = propertyListBox.findElements(By.tagName("li"));
        for (WebElement property : propertyNames){
            if (property.getText().equals(data.get(propertyName).toString())){
                property.click();
                return new LogMessage(true, "Property found successfully");
            }
        }
        return new LogMessage(false, " Invalid property name");
    }
}
