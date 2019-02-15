package test;

import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import test.beforeTest.AccountCreateJeMapping;
import test.driver.DriverFactory;
import test.keywordScripts.UIBase;
import test.keywordScripts.UIMenu;
import test.keywordScripts.UIText;
import test.utility.ReadExcel;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JMapping {

    private static WebDriver webDriver ;

    @BeforeClass
    public static void login() {
     //   ClassLoader classLoader = getClass().getClassLoader();
      //  ReadExcel readExcel = new ReadExcel(classLoader.getResource("dataCreate/PropertyCreate.xlsx").getPath());
       // List<Map> records = readExcel.read("Property");
        webDriver = DriverFactory.createDriver("chrome", false);
        UIBase uiBase = new UIBase(webDriver) ;
        UIText uiText = new UIText(webDriver) ;
        uiBase.navigateToAPage("https://qa4.testamt.com/");
        uiText.SetText("Common.Login.txtUserName","saimaalam01");
        uiText.SetText("Common.Login.txtPassword","amtDirect01!");
        uiText.SetText("Common.Login.txtClientID","201483");
        uiBase.Click("Common.Login.btnLogIn");
    }

    @Test
    public void createAccount() {
        try {
            AccountCreateJeMapping accountCreateJeMapping = new AccountCreateJeMapping(webDriver) ;
            accountCreateJeMapping.createAccount(new HashMap());
        }catch ( Exception ex) {
            ex.printStackTrace();
        }

    }



}
