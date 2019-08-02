package test.beforeTest;

import org.json.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import test.Log.LogMessage;
import test.keywordScripts.*;
import test.model.Lease;
import test.utility.PropertyConfig;

import java.util.*;

@Component
public class RecurringPaymentCreateandSearch {
    private WebDriver webDriver;
    private UIBase uiBase;
    private String mainWindow;
    @Autowired
    LeaseCreateAndSearch _leaseCreateAndSearch ;
    public RecurringPaymentCreateandSearch(){


    }

    public void setWebDriver(WebDriver wbd) {
        this.webDriver = wbd;
        _leaseCreateAndSearch.setDriver(webDriver);
    }
    public List<LogMessage> addMultipleRecurringPayments(List<Map> datas){
        List<LogMessage> logMessageList = new ArrayList<>();
        UtilKeywordScript utilKeywordScript = new UtilKeywordScript(webDriver);
        try{
            uiBase = new UIBase(webDriver);
            LeaseCreateAndSearch leaseCreateAndSearch = _leaseCreateAndSearch;
            leaseCreateAndSearch.searchLease(datas.get(0));
            UtilKeywordScript.delay(PropertyConfig.SHORT_WAIT_TIME_SECONDS);
            mainWindow = webDriver.getWindowHandle();
            for(Map data: datas)
            {
                LogMessage lm=addRecurringPayment(data);
                JSONObject jso = new JSONObject();
                jso.put("dataId",data.get("dataId") );
                jso.put("type","rpr" );
                jso.put("isPassed",lm.isPassed() );
                lm.addJsonObject(jso);
                logMessageList.add(lm);
            }
            utilKeywordScript.redirectHomePage();
            return logMessageList;
        }catch (Exception e){
            e.printStackTrace();
            utilKeywordScript.redirectHomePage();
            logMessageList.add(new LogMessage(false, "Exception occurred while creating multiple Recurring Payments" + e.getMessage()));
            return logMessageList;
        }
    }
    public LogMessage addRecurringPayment(Map data){
        try{
            String  objectLocatorPrefix = "Common.RecurringPayment." ;
            //String[] dropdownFields = new String[] {"spaceInfo","chargeType","frequency","escalationType" , "leaseTermYear" , "leaseTermDefined" , "fiscalYear"} ;
            String[] dropdownFields = new String[] {"spaceInfo","chargeType","frequency","escalationType" , "leaseTermYear" , "leaseTermDefined" } ;
            WebDriverWait wait = new WebDriverWait(webDriver, PropertyConfig.WAIT_TIME_EXPLICIT_WAIT);

            UILink uiLink = new UILink(webDriver);
            UIBase uiBase = new UIBase(webDriver);
            UITable uiTable = new UITable(webDriver);
            UIText uiText = new UIText(webDriver);

            uiLink.ClickLink("Common.Lease.tbRPayment","Add New");
            UtilKeywordScript.delay(PropertyConfig.SHORT_WAIT_TIME_SECONDS);
            UtilKeywordScript.switchLastTab(webDriver);
            webDriver.manage().window().maximize();
            UtilKeywordScript.delay(PropertyConfig.SHORT_WAIT_TIME_SECONDS);

            UIDropDown uiDropDown = new UIDropDown(webDriver);
            for (String element : dropdownFields){
                UtilKeywordScript.delay(PropertyConfig.ONE_SECOND);
                uiDropDown.SelectSpecialItem(objectLocatorPrefix + element,(String)data.get(element));
            }

            /* 435_18.03.2019 Setting Value for Fiscal Year*/

            String mapValue = (String) data.get("leaseTermDefined");
            String FiscalYaerObjectLocator = objectLocatorPrefix+"txtFiscalYear";
            String FiscalYaerObjectLocatorByAreaOwns = objectLocatorPrefix+"fiscalYearByAreaOwns";

            System.out.println("mapValue: "+mapValue);

            if(mapValue.equals("Fiscal Year" ) ){
                UtilKeywordScript.delay(PropertyConfig.ONE_SECOND);

                // Getting Object locator data from fiscalYear from OR"
                System.out.println("(String) data.get(fiscalYear)"+ data.get("fiscalYear"));
                uiBase.VerifyVisibleOnScreenTrue(FiscalYaerObjectLocator);
                uiBase.VerifyEnabledTrue(FiscalYaerObjectLocator);
                try {
                    uiBase.Click(FiscalYaerObjectLocator);
                    UtilKeywordScript.delay(PropertyConfig.ONE_SECOND*2);
                    uiText.SetText(FiscalYaerObjectLocator, (String) data.get("fiscalYear"));
                    System.out.println("Successfully Set Fiscal year");
                } catch (Exception e) {
                    e.printStackTrace();
                    uiBase.Click(FiscalYaerObjectLocatorByAreaOwns);
                    UtilKeywordScript.delay(PropertyConfig.ONE_SECOND*2);
                    uiText.SetText(FiscalYaerObjectLocatorByAreaOwns, (String) data.get("fiscalYear"));
                    System.out.println("Successfully Set Fiscal year");
                }
            }else{
                System.out.println("Fiscal year Not Set");
            }

            /* 435_18.03.2019 Setting Value for Fiscal Year*/

            uiBase.Click(objectLocatorPrefix + "btnSave");
            uiBase.WaitingForSuccessfullPopup();

            String autoPopulate = (String) data.get("rentalActivity");
            System.out.println("Auto populated " + autoPopulate);
            if (null != autoPopulate  && !autoPopulate.isEmpty() && autoPopulate.toLowerCase().equals("auto-populate")){
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Auto-Populate Rental Activity")));
                //findElement();
                uiLink.ClickLink("","Auto-Populate Rental Activity");
                UtilKeywordScript.delay(PropertyConfig.ONE_SECOND*3);
                UtilKeywordScript.switchLastTab(webDriver);
                webDriver.manage().window().maximize();
                UtilKeywordScript.delay(PropertyConfig.ONE_SECOND*3);
                uiText.SetText(objectLocatorPrefix + "dtEffectiveDate", (String) data.get("effDate"));
                UtilKeywordScript.delay(PropertyConfig.ONE_SECOND*2);
                //Add base amount
                WebElement baseAmount = (webDriver.findElements(By.xpath("//input[contains(@id,'txtAutoPopulate_BaseAmount')]/preceding-sibling::input"))).get(0);
                UtilKeywordScript.delay(PropertyConfig.ONE_SECOND*2);
                baseAmount.sendKeys((String) data.get("baseAmount"));
                UtilKeywordScript.delay(PropertyConfig.ONE_SECOND*2);
                //Add percent increase
                WebElement percentIncrease = (webDriver.findElements(By.xpath("//input[contains(@id,'txtAutoPopulate_PercentIncrease')]/preceding-sibling::input"))).get(0);
                UtilKeywordScript.delay(PropertyConfig.ONE_SECOND*2);
                percentIncrease.sendKeys((String) data.get("percentIncrease"));
                UtilKeywordScript.delay(PropertyConfig.ONE_SECOND*2);
                // Add cal frequency
                WebElement calFrequency = (webDriver.findElements(By.xpath("//input[contains(@id,'txtAutoPopulate_CalcFreq')]/preceding-sibling::input"))).get(0);
                UtilKeywordScript.delay(PropertyConfig.ONE_SECOND*2);
                calFrequency.sendKeys((String) data.get("calFrequency"));
                UtilKeywordScript.delay(PropertyConfig.ONE_SECOND*3);
                uiText.SetText(objectLocatorPrefix + "dtEndDate", (String) data.get("endDate"));

                uiBase.Click(objectLocatorPrefix + "btnRecalculate");
                uiText.WaitForInvisibilityOfText(objectLocatorPrefix + "grdAutoPopulate","No items to display");
                UtilKeywordScript.delay(PropertyConfig.ONE_SECOND*2);


                uiBase.Click(objectLocatorPrefix + "btnApply");

                UtilKeywordScript.delay(PropertyConfig.ONE_SECOND*3);
                UtilKeywordScript.switchLastTab(webDriver);

                //uiBase.WaitingForPageLoad();
                //uiBase.WaitingForPageLoad();

                UtilKeywordScript.delay(PropertyConfig.SHORT_WAIT_TIME_SECONDS);
                UtilKeywordScript.switchLastTab(webDriver);
                //System.out.println("Driver after first switch: " + webDriver);

                uiText.WaitForInvisibilityOfText(objectLocatorPrefix + "grdRentalActivityDetail","No items to display");
                UtilKeywordScript.delay(PropertyConfig.SHORT_WAIT_TIME_SECONDS);
                UtilKeywordScript.switchLastTab(webDriver);
                //System.out.println("Driver in final state: " + webDriver);



            }else{
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Add Rental Activity")));

                uiLink.ClickLink("","Add Rental Activity");
                UtilKeywordScript.delay(PropertyConfig.ONE_SECOND*3);

                uiTable.DoubleClickCellInTable(objectLocatorPrefix + "tableRecurrentPayment", "*Eff Date,0," + (String)data.get("effDate"));
                UtilKeywordScript.delay(PropertyConfig.ONE_SECOND);
                uiTable.EnterCellData(objectLocatorPrefix + "tableRecurrentPayment", "*Eff Date,0,"+ (String)data.get("effDate"));
                UtilKeywordScript.delay(PropertyConfig.ONE_SECOND);
                uiTable.DoubleClickCellInTable(objectLocatorPrefix + "tableRecurrentPayment", "*End Date,0," + (String)data.get("endDate"));
                UtilKeywordScript.delay(PropertyConfig.ONE_SECOND);
                uiTable.EnterCellData(objectLocatorPrefix + "tableRecurrentPayment", "*End Date,0," + (String)data.get("endDate"));
                UtilKeywordScript.delay(PropertyConfig.ONE_SECOND);
                uiTable.DoubleClickCellInTable(objectLocatorPrefix + "tableRecurrentPayment", "*Amount,0," + (String)data.get("amount"));
                UtilKeywordScript.delay(PropertyConfig.ONE_SECOND);
                uiTable.EnterCellData(objectLocatorPrefix + "tableRecurrentPayment", "*Amount,0," + (String)data.get("amount"));
                UtilKeywordScript.delay(PropertyConfig.ONE_SECOND);

                uiTable.DoubleClickCellInTable(objectLocatorPrefix + "tableRecurrentPayment", "Annual,0,0");

                UtilKeywordScript.delay(PropertyConfig.ONE_SECOND*3);

                uiBase.Click(objectLocatorPrefix + "saveRentalActivity");

                //uiBase.WaitingForPageLoad();
                uiBase.WaitingForSuccessfullPopup();
            }

            uiBase.Click(objectLocatorPrefix + "btnSave");

            //uiBase.WaitingForPageLoad();
            LogMessage lm = uiBase.WaitingForSuccessfullPopup();

            UtilKeywordScript.delay(PropertyConfig.ONE_SECOND*3);

            uiBase.Click(objectLocatorPrefix + "btnClose");
            UtilKeywordScript.delay(PropertyConfig.ONE_SECOND*2);
            Set<String> set =webDriver.getWindowHandles();
            Iterator<String> itr= set.iterator();
            while(itr.hasNext()){
                String childWindow=itr.next();
                if(!mainWindow.equals(childWindow)){
                    webDriver.switchTo().window(childWindow);
                    webDriver.close();
                }

            }
            webDriver.switchTo().window(mainWindow);
            if(lm.isPassed())
                return new LogMessage(lm.isPassed(),"Reccurring Payment of "+data.get("chargeType") +" is created successfully under "+data.get("spaceInfo"));
            else
                return new LogMessage(lm.isPassed(),"Reccurring Payment of "+data.get("chargeType") +" is not created under "+data.get("spaceInfo"));
        }catch (Exception e){
            e.printStackTrace();
            return new LogMessage(false,"Reccurring Payment of "+data.get("chargeType") +" is not created under "+data.get("spaceInfo"));
        }
    }
    public LogMessage isRecurringPaymentExist(Map data){
        try{
            String  objectLocatorPrefix = "Common.Lease.";
            String columnValue1 = (String)data.get("chargeType");
            String columnValue2 = (String)data.get("spaceInfo");
            UIBase uiBase = new UIBase(webDriver);

            UITable uiTable  = new UITable(webDriver);
            LeaseCreateAndSearch leaseCreateAndSearch = _leaseCreateAndSearch ;
            leaseCreateAndSearch.searchLease(data);

            LogMessage log = uiBase.Click(objectLocatorPrefix + "link");

            if (!log.isPassed())
                return new LogMessage(false, "exception occur during expanding property information");

            LogMessage logMessage = uiTable.VerifyCorrespondingColumnDataTrue(objectLocatorPrefix + "tbRPayment","Type,"+columnValue1.split("-")[0].trim() +",Space,"+columnValue2);

            if (logMessage.isPassed()){
                return new LogMessage(true,"Recurring Payment already exist");
            }else{
                return new LogMessage(false," Recurring Payment not exist");
            }
        }catch (Exception e){
            e.printStackTrace();
            return new LogMessage(false,"Exception occur");
        }
    }

    public LogMessage deleteRecurringPayment(Map data){
        try{
            String  objectLocatorPrefix = "Common.RecurringPayment.";
            UIBase uiBase = new UIBase(webDriver);
            UIText uiText = new UIText(webDriver);
            WebElement element = null;
            LeaseCreateAndSearch leaseCreateAndSearch = _leaseCreateAndSearch;
            //LeaseCreateAndSearch leaseCreateAndSearch = new LeaseCreateAndSearch(webDriver);
            LogMessage log = leaseCreateAndSearch.searchLease(data);
            if (log.isPassed()){
                try{
                    element = webDriver.findElement(By.partialLinkText((String)data.get("chargeName")));
                }catch (Exception e){
                    return new LogMessage(false,"No such RPR found");
                }
                LogMessage rprLog = uiBase.Click(element);
                if (rprLog.isPassed()){
                    UtilKeywordScript.delay(PropertyConfig.SHORT_WAIT_TIME_SECONDS *PropertyConfig.NUMBER_OF_ITERATIONS);
                    UtilKeywordScript.switchLastTab(webDriver);
                    UtilKeywordScript.delay(PropertyConfig.SHORT_WAIT_TIME_SECONDS );
                    uiBase.Click(objectLocatorPrefix + "btnDelete");
                    webDriver.switchTo().alert().accept();
                    UtilKeywordScript.delay(PropertyConfig.ONE_SECOND*2);
                    webDriver.switchTo().alert().accept();
                    LogMessage successLog = uiText.WaitForVisibilityOfText(objectLocatorPrefix + "grdRentalActivity","No items to display,120");
                    if (successLog.isPassed()){
                        return new LogMessage(true,"RPR remove successfully");
                    }else{
                        return new LogMessage(false," RPR remove fail");
                    }

                }else{
                    return new LogMessage(false,"No such RPR found");
                }

            }else{
                return new LogMessage(false,"No such lease found");
            }

        }catch (Exception ex){
            return new LogMessage(false, "Exception occurred " + ex.getMessage());
        }
    }
}
