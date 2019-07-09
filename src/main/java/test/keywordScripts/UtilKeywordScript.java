package test.keywordScripts;

import com.assertthat.selenium_shutterbug.core.Shutterbug;
import com.assertthat.selenium_shutterbug.utils.web.ScrollStrategy;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import test.Log.LogMessage;
import test.coreModule.TestPlan;
import test.driver.DriverFactory;
import test.objectLocator.ObjectLocatorDataStorage;
import test.objectLocator.WebObject;
import test.utility.PropertyConfig;


import java.io.File;
import java.util.*;


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
                            if (isAlertPresent()) {
                                webDriver.switchTo().alert().dismiss();
                            }
                            webDriver.close();
                        }
                    }
                    webDriver.switchTo().window(winNames[0]);
                    String homeUrl = PropertyConfig.getHomeLoginUrl();
                    if(!webDriver.getCurrentUrl().equals(homeUrl)) {
                        webDriver.navigate().to(homeUrl);
                    }
                    return new LogMessage(true , "Redirect home page successfully");
        } catch ( Exception ex) {
                ex.printStackTrace();
              return new LogMessage(false , "Exception occurred: " + ex.getMessage());
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

            return new LogMessage(true , "All pages close successfully");
        } catch ( Exception ex) {
            return new LogMessage(false , "Exception occurred: " + ex.getMessage());
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

    public LogMessage maximizeWindow(){
        try{
            webDriver.manage().window().maximize();
            return new LogMessage(true,"Window maximize successfully");
        }catch (Exception e){
            e.printStackTrace();
            return new LogMessage(false,"Exception occur " + e.getMessage());
        }
    }


    public boolean isAlertPresent(){
        boolean foundAlert = false;
        WebDriverWait wait = new WebDriverWait(webDriver, PropertyConfig.WAIT_TIME_EXPLICIT_WAIT);
        try {
            wait.until(ExpectedConditions.alertIsPresent());
            foundAlert = true;
        } catch (Exception e) {
            foundAlert = false;
        }
        return foundAlert;
    }
    public static void switchToPreviousTab(WebDriver webDriver,String mainWindow){
        try {
            Set<String> set = webDriver.getWindowHandles();
            Iterator<String> itr = set.iterator();
            while (itr.hasNext()) {
                String childWindow = itr.next();
                if (!mainWindow.equals(childWindow)) {
                    webDriver.switchTo().window(childWindow);
                    webDriver.close();
                }

            }
            webDriver.switchTo().window(mainWindow);
        }catch (Exception e)
        {
            e.printStackTrace();
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
            LogMessage log = uiText.WaitForVisibilityOfText("Common.Login.navDashboard","Dashboard");
            if (log.isPassed()){
                return new LogMessage(true,"Login successfully");
            }
            return new LogMessage(false,"Login fail");
        }catch (Exception e){
            e.printStackTrace();
            return new LogMessage(false,"Exception occurred " + e.getMessage());
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
            UtilKeywordScript.delay(PropertyConfig.SHORT_WAIT_TIME_SECONDS *PropertyConfig.NUMBER_OF_ITERATIONS);
            UtilKeywordScript.switchLastTab(webDriver);

            return new LogMessage(true, "Search completed");

        }catch (Exception e){
            return new LogMessage(false, "Exception occurred " + e.getMessage());
        }
    }

    public static String removeSpaceAndNewline(String inputString){
        return inputString.replaceAll("\\s+","");
    }

    public static boolean  isEmpty(String inputString){
        if(null == inputString ) return  true;
        return removeSpaceAndNewline(inputString).isEmpty();
    }

    public static Optional<Map> jsonStringToMap(String value) {
            try {
                String[] items = value.split(",") ;
                Map map = new HashMap() ;
                for(String item : items) {
                    map.put(item.split(":")[0],item.split(":")[1]) ;
                }
                return Optional.ofNullable(map) ;
        } catch (Exception ex) {
            //ex.printStackTrace();
            return Optional.empty();
        }
    }
    public LogMessage switchLastTab() {

        try {
            Set<String> windows = webDriver.getWindowHandles();
            Iterator<String> iter = windows.iterator();
            String lastTab = "" ;
            while (iter.hasNext())
                lastTab=iter.next();
            webDriver.switchTo().window(lastTab);
            return new LogMessage(true,"Switch to last tab successfully");
        } catch ( Exception ex) {
            ex.printStackTrace();
            return new LogMessage(false,"Exception occurred "+ ex.getMessage());
        }
    }
    public LogMessage switchToIframe(String objectLocator){
        try {
            Map objectLocatorData = ObjectLocatorDataStorage.getObjectLocator(objectLocator);
            webDriver.switchTo().frame((String) objectLocatorData.get(PropertyConfig.OBJECT_LOCATORS));
            return new LogMessage(true,"Switch to iframe successful");
        }catch (Exception e){
            e.printStackTrace();
            return new LogMessage(false,"Cannot switch to iframe " + e.getMessage());
        }
    }
    public LogMessage backFromIframe(){
        try{
            webDriver.switchTo().defaultContent();
            return new LogMessage(true,"Returned from iframe successful");
        }catch (Exception e){
            e.printStackTrace();
            return new LogMessage(false,"Cannot return from iframe " + e.getMessage());
        }

    }
    public LogMessage verifyAddLeasePage(){
        try {
            UIBase uiBase=new UIBase(webDriver);
            String objectlocatorPrefix= "MENU.QuickLink.";
            if(uiBase.VerifyVisibleOnScreenTrue(objectlocatorPrefix+"addLeaseCode").isPassed()){
                return new LogMessage(true,"Navigated to add Lease Page successful");
            }
            else if(uiBase.VerifyVisibleOnScreenTrue(objectlocatorPrefix+"addLease").isPassed()){
                return new LogMessage(true,"Navigated to add Lease Page successful");
            }

            return new LogMessage(false,"Cannot Navigate to add Lease Page");
        }catch (Exception e){
            return new LogMessage(false,"Cannot Navigate to add Lease Page"+e.getMessage());

        }


    }


    public static boolean isItDigit(String value) {
        try {
            value = value.replaceAll("[^\\d.]", "") ;
            if (value == "" )
                return false ;
            Double digit =   Double.parseDouble(value.replaceAll("[^\\d.]", ""));
            return Optional.ofNullable(digit).isPresent()  ;

        } catch (Exception ex) {
            return false ;
        }
    }

    public static String convertStringToNumber(String value) {
        try {
            Double digit  =  Double.parseDouble(value.replaceAll("[^\\d.]", ""));
            return String.valueOf(digit) ;

        } catch (Exception ex) {
            return "" ;
        }
    }
    public static String convertStringToIntNumber(String value) {
        try {
            Integer digit  =  Integer.parseInt(value.replaceAll("[^\\d.]", ""));
            //System.out.println(String.valueOf(digit));
            return String.valueOf(digit) ;

        } catch (Exception ex) {
            return "" ;
        }
    }
    public static String getUniqueNumber(String data){
        try{
            String uniqueNumber = data.substring(2) + TestPlan.getInstance().getUniqueData() ;
            return uniqueNumber;
        }catch (Exception e){
            return "$Unq";
        }

    }

    public void openTCReport(String fileName){
        try{
            webDriver  = DriverFactory.createDriver("chrome", false);
            webDriver.manage().window().maximize();
            File reportFile = new File("./Report/PassedTCReport.html");
            String path = reportFile.getAbsolutePath();
            webDriver.get(path);

        }catch (Exception ex){
            ex.printStackTrace();
        }
    }


    public void captureReportSnap(String fileName){
        try{
            openTCReport(fileName);
            UIBase uiBase = new UIBase(webDriver);
            WebElement themeSelector = webDriver.findElement(By.id("theme-selector"));
            uiBase.Click(themeSelector);
            WebElement elementDashboard = webDriver.findElement(By.linkText("track_changes"));
            uiBase.Click(elementDashboard);
            takeSnapShot("./Report/PassedImage/","Dashboard");

            webDriver.quit();

        }catch (Exception ex){
            ex.printStackTrace();

        }
    }

    public void takeSnapShot(String filepath, String fileName){
        try{
            Shutterbug.shootPage(webDriver,ScrollStrategy.BOTH_DIRECTIONS).withName(fileName).save(filepath);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

}

