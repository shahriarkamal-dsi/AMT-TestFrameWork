package test.keywordScripts;

import org.openqa.selenium.WebDriver;

public class UiBase {

    private WebDriver webDriver;

    public UiBase(WebDriver driver) {
        this.webDriver = driver ;
    }

    public UiBase(){

    }


    public static void  click(String test) {
        System.out.println("test " + test);
    }
}
