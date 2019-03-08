package test.keywordScripts;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import test.Log.LogMessage;
import test.objectLocator.WebObject;
import test.utility.PropertyConfig;

import java.util.Iterator;
import java.util.Set;
import java.util.logging.LoggingMXBean;

public class UtilKeywordScript {

    private WebDriver webDriver ;

    public UtilKeywordScript() {

    }

    public  UtilKeywordScript(WebDriver webDriver) {
        this.webDriver = webDriver ;
    }
    protected boolean validateTestData(String testData,int numberOfParams) {
        try {
            String data[] = testData.split(",");
            if(data.length >=numberOfParams) return  true;
            return  false;
        } catch (Exception ex) {
            return  false;
        }

    }

    public LogMessage redirectHomePage() {
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
                            webDriver.close();
                        }
                    }
                    webDriver.switchTo().window(winNames[0]);
                    String homeUrl = PropertyConfig.getHomeLoginUrl();
                    if(!webDriver.getCurrentUrl().equals(homeUrl)) {
                        webDriver.navigate().to(homeUrl);
                    }
                    return new LogMessage(true , "redirect home page successsfully");
        } catch ( Exception ex) {
              return new LogMessage(false , "exception occured: " + ex.getMessage());
        }
    }


    public static LogMessage closeAllPages(WebDriver webDriver) {
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
                    webDriver.close();
                }
            }
            webDriver.switchTo().window(winNames[0]);
            webDriver.close();

            return new LogMessage(true , "redirect home page successsfully");
        } catch ( Exception ex) {
            return new LogMessage(false , "exception occured: " + ex.getMessage());
        }
    }


    public static void switchLastTab(WebDriver webDriver) {
        try {
            Set<String> windows = webDriver.getWindowHandles();
            Iterator<String> iter = windows.iterator();
            String lastTab = "" ;
            while (iter.hasNext())
                lastTab=iter.next();
            webDriver.switchTo().window(lastTab);
        } catch ( Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void delay(int time) {
        try {
            Thread.sleep(time * 1000);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public  LogMessage login(String url, String userName, String password, String client){

        try{
            UIBase uiBase = new UIBase(webDriver) ;
            UIText uiText = new UIText(webDriver) ;
            uiBase.navigateToAPage(url);
            uiText.SetText("Common.Login.txtUserName",userName);
            uiText.SetText("Common.Login.txtPassword",password);
            uiText.SetText("Common.Login.txtClientID",client);
            uiBase.Click("Common.Login.btnLogIn");
            return new LogMessage(true,"Login successfully");
        }catch (Exception e){
            e.printStackTrace();
            return new LogMessage(false,"Login fail " + e.getMessage());
        }

    }

    public LogMessage globalSearch(String name, String selectOption){
        try{
            String  objectLocatorPrefix = "Common.GlobalSearch.";
            UIText uiText = new UIText(webDriver);
            UIBase uiBase = new UIBase(webDriver);
            UIDropDown uiDropDown = new UIDropDown(webDriver);

            uiBase.Click(objectLocatorPrefix + "search");
            uiText.SetText(objectLocatorPrefix +"txtSearch",name);
            uiDropDown.SelectItem(objectLocatorPrefix + "searchOption", selectOption);
            uiBase.Click(objectLocatorPrefix + "btnSearch");

            UtilKeywordScript.delay(10);
            UtilKeywordScript.switchLastTab(webDriver);

            return new LogMessage(true, "Search complete");

        }catch (Exception e){
            return new LogMessage(false, "Exception occur " + e.getMessage());
        }
    }
}
