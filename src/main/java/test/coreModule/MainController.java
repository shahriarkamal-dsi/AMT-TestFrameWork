package test.coreModule;

import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import test.Log.EmailSend;
import test.Log.LogMessage;
import test.Log.LogReport;
import test.Log.SpringEmailModule;
import test.beforeTest.TestData;
import test.driver.DriverFactory;
import test.keywordScripts.UtilKeywordScript;
import test.utility.PropertyConfig;
import test.utility.ReadExcel;

import java.io.File;
import java.time.LocalDateTime;
import java.util.*;

@Component
public class MainController {

        @Autowired
        private ExecuteTests executeTests ;
        @Autowired
         SpringEmailModule springEmailModule ;
        @Autowired
        private TestData _testData;
        final static ClassLoader CLASS_LOADER = MainController.class.getClassLoader();
        private WebDriver webDriver;

        public MainController(){

        }

        public void setDriver(WebDriver wbd) {
            this.webDriver = wbd ;
            _testData.setDriver(webDriver);
        }

    public TestPlan createTestPlanAndModule(){
        TestPlan testPlan = createTestPlan();
        List<TestModule> modules = testPlan.getAllTesModules();
        for(TestModule module : modules){
            if(module.getState().equals(PropertyConfig.INIT)){
                ReadExcel readExcel = new ReadExcel(CLASS_LOADER.getResource("modules/" + module.getModuleName() + ".xlsx").getPath());
                List<Map> records = readExcel.read(PropertyConfig.CONTROLLER);
                for (Map record : records) {
                    if(null == record.get(PropertyConfig.EXECUTION_FLAG) || record.get(PropertyConfig.EXECUTION_FLAG).toString().isEmpty()  || !record.get(PropertyConfig.EXECUTION_FLAG).toString().toLowerCase().equals("yes")  )
                        continue;
                    String sheetName = (String) record.get(PropertyConfig.SHEET_NAME);
                    String testCaseID = (String) record.get(PropertyConfig.TCID);
                    String testCaseName = (String) record.get(PropertyConfig.TEST_CASE_NAME);
                    String categoryName = (String) record.get(PropertyConfig.TEST_Category_Name);
                    String testEnvName = (String) record.get(PropertyConfig.TEST_ENV_NAME);
                    TestSuite testSuite = module.getTestSuite(sheetName);
                    if(null ==  testSuite){
                        testSuite = new TestSuite(sheetName);
                        testSuite.setState(PropertyConfig.INIT);
                        module.addTestSuite(testSuite);
                    }
                    TestCase testCase = new TestCase(testCaseID);
                    testCase.setTestCaseName(testCaseName);
                    testCase.setcategoryName(categoryName);
                    testCase.setTestEnvName(testEnvName);
                    testSuite.addTestCase(testCase);
                }
                module.setState(PropertyConfig.CREATED);
            }
        }
        return testPlan;
    }

    public void  createAndExecute() {
        deleteLogReports();
        TestPlan testPlan = createTestPlanAndModule();
        UtilKeywordScript utilKeywordScript = new UtilKeywordScript(webDriver);
        List<TestModule> modules = testPlan.getAllTesModules() ;
        List<TestEnvironment> testEnvironments = testPlan.getTestEnvironments() ;
        int testEnvIndex = 0 ;

            for (TestEnvironment testEnvironment : testEnvironments) {
                testPlan.setTestEnvId(testEnvIndex);
                testEnvIndex++;
                try {
                WebDriver driver = DriverFactory.createDriver(testEnvironment.getBrowser(), false);
                this.setDriver(driver);
                new UtilKeywordScript(driver).login(testEnvironment.getLoginUrl(), testEnvironment.getUserName(), testEnvironment.getPassword(), testEnvironment.getClient());
                for (TestModule testModule : modules) {
                    if (testModule.getModuleName().equals("Preq") || testModule.getModuleName().equals("CommonTC"))
                        continue;
                    List<TestSuite> testSuites = testModule.getAllTestSuits();
                    testSuites.stream().forEach(testSuite ->
                    {
                        readTestSuite(testSuite, testModule.getModuleName());
                        executeTestesInTestSuite(testSuite);
                    });
                }
                driver.quit();
                if(testEnvironment.getBrowser().toLowerCase().equals("ie")) {
                    closeIeBrowser() ;
                }
            } catch(Exception ex) {
                    ex.printStackTrace();
                }
        }
        utilKeywordScript.captureReportSnap("PassedTCReport.html");
        springEmailModule.emailSend();
        }

        public static void readTestSuite(TestSuite testSuite,String moduleName) {
                if(testSuite.getState().equals(PropertyConfig.CREATED))
                    return;
                ReadExcel readExcel = new ReadExcel(CLASS_LOADER.getResource("modules/" + moduleName + ".xlsx").getPath());
                List<Map> records = readExcel.read(testSuite.getTestSuiteName());
                for(Map record : records) {
                    String testCaseNumber = Optional.ofNullable((String) record.get(PropertyConfig.TC_ID)).orElse("") ;
                    if(testCaseNumber.isEmpty())
                        continue;
                    testCaseNumber = testCaseNumber.split("\\.")[0];
                    TestCase testCase = testSuite.getTestCase(testCaseNumber);
                    if(null == testCase)
                        continue;
                    testCase.addTestStep(new TestStep(record));
                }
            testSuite.setState(PropertyConfig.CREATED);
        }

        public void executeTestesInTestSuite(TestSuite testSuite){
           try {
               LogReport logReport = LogReport.getInstance();
               List<TestCase> testCases = testSuite.getAllTestCases();
               executeTests.setDriver(webDriver);
               for (TestCase testCase : testCases) {
                   if(!testCase.getTestEnvName().contains(TestPlan.getInstance().getCurrentTestEnvironment().getEnvName()))
                       continue;
                   List<LogMessage> logMessages = new ArrayList<>();
                   logMessages.addAll(_testData.createTestData(testCase.getTestCaseNumber(),"na"));
                   if(validateLogMessages(logMessages))
                    logMessages.addAll(executeTests.executeTest(testCase));
                   logReport.addTestcaseLogreport(testCase, logMessages);
                   new UtilKeywordScript(webDriver).redirectHomePage();
               }
               closeAlltabs(webDriver);
           }
           catch(Exception e)
           {
               e.printStackTrace();
           }
        }

    public  void closeAlltabs(WebDriver webDriver) {
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
            //webDriver.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }



    public TestPlan createTestPlan() {
        long start = System.currentTimeMillis();
        TestPlan testPlan = TestPlan.getInstance() ;
        testPlan.resetTestPlan();
        testPlan.setTestPlanName(LocalDateTime.now().toString());
        ReadExcel readExcel = new ReadExcel(CLASS_LOADER.getResource("testPlan/" + PropertyConfig.TEST_ENVIRONMENT + ".xlsx").getPath());
        List<Map> records = readExcel.read(PropertyConfig.CONTROLLER);
        for (Map map : records) {
            String envName = (String) map.get("EnvName") ;
            if(null == envName || envName.isEmpty())
                break;
            TestEnvironment testEnvironment = new TestEnvironment() ;
            testEnvironment.setEnvName((String) map.get("EnvName"));
            testEnvironment.setActive((String) map.get(PropertyConfig.EXECUTION_FLAG));
            testEnvironment.setUserName((String) map.get("UserName"));
            testEnvironment.setPassword((String) map.get("Password"));
            testEnvironment.setClient(((String) map.get("Client")).replaceAll("\\.0*$", "") );
            testEnvironment.setEnv((String) map.get("Env"));
            testEnvironment.setBrowser((String) map.get("Browser"));
            testEnvironment.setLoginUrl((String) map.get("LoginUrl"));
            testEnvironment.setHomeUrl((String) map.get("HomeUrl"));

            if (null == testEnvironment.getEnvName() || testEnvironment.getEnvName().isEmpty())
                continue;
            if (null == testEnvironment.getActive() || !testEnvironment.getActive().toLowerCase().equals("yes"))
                continue;
            testPlan.addTestEnvironment(testEnvironment);
        }

         readExcel = new ReadExcel(CLASS_LOADER.getResource("testPlan/" + PropertyConfig.MODULE_CONTROLLER + ".xlsx").getPath());
         records = readExcel.read(PropertyConfig.MODULE_CONTROLLER);
        for (Map map : records) {
            String moduleName = (String) map.get(PropertyConfig.MODULE_NAME);
            String executionFlag = (String) map.get(PropertyConfig.EXECUTION_FLAG);
            if (null == moduleName || moduleName.isEmpty())
                continue;
            if (null == executionFlag || !executionFlag.toLowerCase().equals("yes"))
                continue;
            TestModule testModule = new TestModule(moduleName);
            testPlan.addTestModule(testModule);
        }
        return testPlan;
    }

    private void deleteLogReports(){
        File file = new File("./Report/" + PropertyConfig.getPropertyValue("passedReprtName"));
        file.delete();
         file = new File("./Report/" + PropertyConfig.getPropertyValue("failedReprtName"));
        file.delete();
        file = new File("./Report/PassedImage/" + PropertyConfig.getPropertyValue("passedReportDashboardName"));
        file.delete();
    }
    public static Boolean validateLogMessages(List<LogMessage> logMessages){
        return logMessages.stream().noneMatch(o -> o.isPassed().equals(false));
    }

    private void closeIeBrowser() {
        try {
            Runtime.getRuntime().exec("taskkill /F /IM IEDriverServer.exe");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}

