package test.keywordScripts;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import test.Log.LogMessage;
import test.objectLocator.WebObjectSearch;

public class UILink {

    private WebDriver webDriver;

    public UILink(WebDriver driver) {
        this.webDriver = driver ;
    }

    public UILink(){

    }

    public LogMessage clickLink(String Objreferance, String Linkname){
        try{

            WebElement element = webDriver.findElement(By.linkText(Linkname));
            element.click();

            return new LogMessage(true,"Link clicked");
        }catch (Exception e){
            e.printStackTrace();
            return new LogMessage(false,"Exception occure");
        }
    }

}
