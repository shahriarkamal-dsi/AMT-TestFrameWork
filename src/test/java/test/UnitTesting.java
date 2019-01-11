package test;

import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import test.coreModule.*;
import test.keywordScripts.UiBase;
import test.objectLocator.ObjectLocatorDataStorage;
import test.objectLocator.OrRead;
import test.objectLocator.WebObjectSearchType;
import test.utility.PropertyConfig;
import test.utility.ReadExcel;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import java.lang.System.* ;

public class UnitTesting {

    @Test
    public void readExcelsheetTest(){
        ClassLoader classLoader = getClass().getClassLoader();
        long start = System.currentTimeMillis();
        ReadExcel readExcel = new ReadExcel(classLoader.getResource("modules/PortFolioInsight.xlsx").getPath());
        List<Map> records = readExcel.read("TC001_TC050");
        long finish = System.currentTimeMillis();
        long timeElapsed = finish - start;
        System.out.println(records.get(records.size()-1));
        System.out.println("execute time : "+timeElapsed/1000);
    }
    @Test
    public void orReadTesting(){
        OrRead orRead  = new OrRead("OR_PI.RPRCharge.txtndLinkedForms");
        Map records = orRead.getOrFromSheet();
        System.out.println(records);
    }
    @Test
    public void objectLocatorStorageTesting(){
        ObjectLocatorDataStorage objectLocatorDataStorage = new ObjectLocatorDataStorage();
        try {
            Map record = objectLocatorDataStorage.getObjectLocator("OR_PI.RPRCharge.txtndLinkedForms");
            System.out.println(record);
             record = objectLocatorDataStorage.getObjectLocator("OR_PI.RPRCharge.cbMasterCharge");
            System.out.println(record);
            record = objectLocatorDataStorage.getObjectLocator("OR_PI.SpaceEntryForm.lnkDateMgrIgnore");
            System.out.println(record);
            record = objectLocatorDataStorage.getObjectLocator("OR_PI.SpaceEntryForm.panelDateMgrConfirmation");
            record = objectLocatorDataStorage.getObjectLocator("OR_PI.RPRCharge.txtndLinkedForms");
            System.out.println(record);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Test
    public void webObjectTest(){
        ObjectLocatorDataStorage objectLocatorDataStorage = new ObjectLocatorDataStorage();
        try {
            WebDriver driver = null ;
            Map record = objectLocatorDataStorage.getObjectLocator("OR_PI.SpaceEntryForm.pgSpaceEntryForm");
            String objectSearchType = ( (String)record.get(PropertyConfig.OBJECT_SEARCH_KEY)).toUpperCase();
            WebObjectSearchType webObjectSearchType = WebObjectSearchType.valueOf(objectSearchType);
            WebElement webElement =  webObjectSearchType.findElement(driver,(String) record.get(PropertyConfig.OBJECT_LOCATOR));
            System.out.println(webElement);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testingInvokeMethod() {

        WebDriver driver = null ;
        ExecuteTests executeTests = new ExecuteTests(driver);
         String temp = "temp" ;
        Object[] object = new Object[]{driver,temp};
        executeTests.invokeMethod("UiBase","click",1,object);
    }
    @Test
    public void testingReadAndExecute() {
        WebDriver driver = null ;
        ExecuteTests executeTests = new ExecuteTests(driver);
        executeTests.readAndExecute("sampleTest","TC001_TC050");
    }
    @Test
    public void testinCreateTestPlan() {
        MainController mc = new MainController();
        TestPlan testPlan =  mc.createTestPlan() ;
        List<TestModule> modules = testPlan.getAllTesModules() ;
        for(TestModule md : modules){
            System.out.println(md.getModuleName());
            md.setModuleName("test");
            System.out.println(md.getAllTestSuits().size()) ;
            md.addTestSuite(new TestSuite());
        }

         modules = testPlan.getAllTesModules() ;
        for(TestModule md : modules){
            System.out.println(md.getModuleName());
            System.out.println(md.getAllTestSuits().size()) ;
        }

    }

}
