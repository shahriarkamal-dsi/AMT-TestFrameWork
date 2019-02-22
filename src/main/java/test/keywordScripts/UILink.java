package test.keywordScripts;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import test.Log.LogMessage;
import test.objectLocator.WebObjectSearch;

public class UILink {

    private WebDriver webDriver;

    public UILink(WebDriver driver) {
        this.webDriver = driver ;
    }

    public UILink(){

    }

    public LogMessage ClickLink(String Objreferance, String Linkname){
        try{

            WebElement webElement = webDriver.findElement(By.linkText(Linkname));
          //  System.out.println(webElement);
            try
            {
                webElement.click();
                Thread.sleep(2000);
            } catch(Exception ex) {
                ex.printStackTrace();
                JavascriptExecutor executor = (JavascriptExecutor) webDriver;
                executor.executeScript("arguments[0].click();",webElement);
                Thread.sleep(2000);
                //((JavascriptExecutor)webDriver).executeScript("window.scrollTo(0,"+webElement.getLocation().x+")");
                //webElement.click();
                //Actions actions = new Actions(webDriver);
                //actions.moveToElement(webElement).click().build().perform();

            }
            return new LogMessage(true,"web element is clicked");
        }catch (Exception e){
            e.printStackTrace();
            return new LogMessage(false,"Exception occure");
        }
    }

}
