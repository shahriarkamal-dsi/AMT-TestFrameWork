package test.beforeTest;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import test.Log.LogMessage;
import test.keywordScripts.UILink;
import test.keywordScripts.UITable;
import test.keywordScripts.UtilKeywordScript;
import test.objectLocator.WebObjectSearch;
import test.utility.ReadExcel;

import java.beans.Expression;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class SpaceCreate {

    private WebDriver webDriver;

    public SpaceCreate(org.openqa.selenium.WebDriver driver){
        this.webDriver = driver;
    }

    public SpaceCreate(){

    }

    public LogMessage spaceCreate(){
        try{
            WebDriverWait wait = new WebDriverWait(webDriver, 5*60);

            ClassLoader classLoader = getClass().getClassLoader();
            ReadExcel readExcel = new ReadExcel(classLoader.getResource("dataCreate/PropertyCreate.xlsx").getPath());
            List<Map> records = readExcel.read("Space");

            String  objectLocatorPrefix = "Common.Space." ;

            UtilKeywordScript.delay(15);

            WebElement txtSpace = WebObjectSearch.getWebElement(webDriver,objectLocatorPrefix + "space");
            txtSpace.sendKeys((String)records.get(0).get("Space"));
            WebElement txtFloor = WebObjectSearch.getWebElement(webDriver,objectLocatorPrefix + "floor");
            txtFloor.sendKeys((String)records.get(0).get("Floor"));
            WebElement linked = WebObjectSearch.getWebElement(webDriver, objectLocatorPrefix + "linked");
            linked.click();

            WebElement btnSave = WebObjectSearch.getWebElement(webDriver,objectLocatorPrefix + "btnSave");
            btnSave.click();

            wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("alert alert-success")));
            wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("alert alert-success")));

            //UtilKeywordScript.delay(5);
            WebElement btnClose = WebObjectSearch.getWebElement(webDriver,objectLocatorPrefix + "btnClose");
            btnClose.click();

            UtilKeywordScript.delay(5);

            return new LogMessage(true,"Space create successfully!");
        }catch (Exception e){
            e.printStackTrace();
            return new LogMessage(false,"Exception occurred");
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
          /*
            if(winNames.length > 1) {
                for(i = winNames.length; i > 1; i--) {
                    webDriver.switchTo().window(winNames[i - 1]);
                }
            }
          */
            webDriver.switchTo().window(winNames[windows.size()-1]);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }


}
