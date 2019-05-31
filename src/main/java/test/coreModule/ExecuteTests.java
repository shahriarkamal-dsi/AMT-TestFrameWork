package test.coreModule;

import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import test.Log.CreateLog;
import test.Log.LogMessage;
import test.beforeTest.LeaseCreateAndSearch;
import test.beforeTest.PropertyCreateAndSearch;
import test.beforeTest.SpaceCreateAndSearch;
import test.beforeTest.TestData;
import test.keywordScripts.UIBase;
import test.keywordScripts.UtilKeywordScript;
import test.utility.PropertyConfig;
import test.utility.ReadExcel;
import test.coreModule.TestCase;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;


@Component
public class ExecuteTests {

    @Autowired
    private TestData _testData ;
    @Autowired
    private PropertyCreateAndSearch _propertyCreateAndSearch ;
    @Autowired
    private LeaseCreateAndSearch _leaseCreateAndSearch ;
    @Autowired
    private SpaceCreateAndSearch _spaceCreateAndSearch ;

    private WebDriver webDriver ;
    public ExecuteTests() {
    }
    public void readAndExecute(String fileName,String sheetName) {
        ClassLoader classLoader = getClass().getClassLoader();
        long start = System.currentTimeMillis();
        ReadExcel readExcel = new ReadExcel(classLoader.getResource("modules/" + fileName + ".xlsx").getPath());
        List<Map> records = readExcel.read(sheetName);
        for(Map map : records) {
            ArrayList<Object> objects = new ArrayList<Object>();
           // objects.add(webDriver);
            String actionName = (String) map.get(PropertyConfig.ACTION);
            String objectLocators = (String) map.get(PropertyConfig.OBJECT_LOCATORS);
            String testData = (String) map.get(PropertyConfig.TEST_DATA);
            String executionFlag = (String) map.get(PropertyConfig.EXECUTION_FLAG);
            String critical = (String) map.get(PropertyConfig.CRITICAL);
            int numberOfParams = 0;

            if ( null == executionFlag || !executionFlag.toLowerCase().equals("yes"))
                continue;
            if(null != objectLocators && ! objectLocators.isEmpty()) {
                objects.add(objectLocators);
                numberOfParams++;
            }
            if(null != testData && ! testData.isEmpty()){
                objects.add(testData);
                numberOfParams++;
            }
            LogMessage logMessage =  invokeMethod(actionName.split("\\.")[0],actionName.split("\\.")[1],numberOfParams,objects.toArray());

        }

    }

    public void setDriver(WebDriver wbd) {
        this.webDriver = wbd ;
        _testData.setDriver(webDriver);
        _propertyCreateAndSearch.setDriver(webDriver);
        _leaseCreateAndSearch.setDriver(webDriver);
        _spaceCreateAndSearch.setDriver(webDriver);
    }
    public List<LogMessage> executeTest(TestCase testCase) {
        List<LogMessage> logMessages = new ArrayList<LogMessage>();
        try {
            UIBase uiBase = new UIBase(webDriver);
            ClassLoader classLoader = getClass().getClassLoader();
            long start = System.currentTimeMillis();
            List<TestStep> testSteps = testCase.getAllTestSteps();
            testCase.setPassed(true);
            TestData testData = _testData ;
            for (TestStep testStep : testSteps) {
                //every step either create data or remove data or prequisite or normal step, if execultion flag is not set, it will not be executed
                Boolean executionFlag = testStep.isExecutionFlagOn();
                if (!executionFlag) {
                    testStep.setPassed(true);
                    logMessages.add(new LogMessage(true, testStep.getTestStepDescription() + " --" + testStep.getFieldName() + "(Skipped)"));
                    continue;
                }
                if (isItPrequisite(testStep.getAction())) {
                    logMessages.add(new LogMessage(true, "Prerequisite started : " + testStep.getTestStepDescription()));
                    List<LogMessage> preqLogMessages = runPrequisite(testCase, testStep,true);
                    logMessages.addAll(preqLogMessages);
                    testCase.setPassed(preqLogMessages.stream().allMatch(logMessage -> logMessage.isPassed()));
                    if (!testCase.isPassed()) {
                        logMessages.add(new LogMessage(false, "Prerequisite not fullfiled : "));
                        return logMessages;
                    } else {
                        logMessages.add(new LogMessage(true, "Prerequisite fullfiled"));
                    }
                }else if(isItCommon(testStep.getAction())){
                    List<LogMessage> preqLogMessages = runPrequisite(testCase, testStep,false);
                    logMessages.addAll(preqLogMessages);
                    testCase.setPassed(preqLogMessages.stream().allMatch(logMessage -> logMessage.isPassed()));
                } else {
                    logMessages.add(runSingleStep(testStep, testCase));
                    if (!testStep.isPassed() && testStep.isCritical()) {
                        testCase.setPassed(false);
                        return logMessages;
                    }
                }
            }
            testCase.setPassed(logMessages.stream().allMatch(logMessage -> logMessage.isPassed()));
            return logMessages;
        }catch (Exception e){
            return logMessages;
        }
    }


    public LogMessage runSingleStep(TestStep testStep,TestCase testCase) {
        try {

            ArrayList<Object> objects = new ArrayList<Object>();
            // objects.add(webDriver);
            String actionName = testStep.getAction();
            String objectLocators = testStep.getObjectLocator();
            testStep.setTestData(updateTestData(testCase.getTestCaseNumber(),testStep.getTestData(),testCase.getPreqData()));
            String testData = Optional.ofNullable(testStep.getTestData()).orElse("");
            if(testData.contains("$Unq"))
                testData = testData.replace("$Unq",UtilKeywordScript.getUniqueNumber(testCase.getTestCaseNumber()));
            Boolean executionFlag = testStep.isExecutionFlagOn();
            Boolean pageRefresh = testStep.isRefreshPageOn();
            Boolean critical = testStep.isCritical();
            String[] logMeessageFromXL=null;
            String passedLogMessage="";
            String failedLogMessage="";
            if(null!=testStep.getLogMessage() && !testStep.getLogMessage().equals("")) {
                logMeessageFromXL = testStep.getLogMessage().split("\\?");
                passedLogMessage = logMeessageFromXL[0];
                if (logMeessageFromXL.length == 2) {
                    failedLogMessage = logMeessageFromXL[1];
                }

            }
            int numberOfParams = 0;

            if (! executionFlag) {
                testStep.setPassed(true);
                return new LogMessage(true,testStep.getTestStepDescription() + " --" + testStep.getFieldName() + "(Skipped)");
                //logMessage.setSkippedTrue();
            }
            if(null != objectLocators && ! objectLocators.isEmpty()) {
                objects.add(objectLocators);
                numberOfParams++;
            }
            if(null != testData && ! testData.isEmpty()){
                objects.add(testData);
                numberOfParams++;
            }
            LogMessage logMessage =  invokeMethod(actionName.split("\\.")[0],actionName.split("\\.")[1],numberOfParams,objects.toArray());
            int delayTime=delayTime(testStep.delayTime());
            if(!(delayTime<=0))
                UtilKeywordScript.delay(delayTime);
            if(logMessage.isPassed())
                logMessage.setLogMessage(createLogMessage(passedLogMessage,testStep,logMessage));
            else
                logMessage.setLogMessage(createLogMessage(failedLogMessage,testStep,logMessage));

            testStep.setPassed(logMessage.isPassed());

            if (pageRefresh){
                new UIBase(webDriver).WaitingForPageLoad();
            }
            return  logMessage ;
        } catch (Exception ex) {
            testStep.setPassed(false);
            return new LogMessage(false, "exception occured running one test step " + ex.getMessage()) ;
        }

    }

    public LogMessage invokeMethod(String className,String methodName,int numberOfParams,Object[] object) {
        try {

            Class<?> callingClass = Class.forName("test.keywordScripts." + className);
            Method callingMethod ;
            if(numberOfParams == 0)
                callingMethod = callingClass.getDeclaredMethod(methodName);
            else if(numberOfParams == 1)
                callingMethod = callingClass.getDeclaredMethod(methodName,String.class );
            else if(numberOfParams == 2)
                callingMethod = callingClass.getDeclaredMethod(methodName,String.class,String.class);
            else
                return new LogMessage(false, "number of parameter exceeds");
            Constructor<?> constructor = callingClass.getConstructor(WebDriver.class);
            LogMessage logMessage = (LogMessage) callingMethod.invoke(constructor.newInstance(webDriver),object);
            return logMessage;
        } catch(Exception ex) {
               return  new LogMessage(false,"exception occured");
        }
    }

    public List<LogMessage> runPrequisite(TestCase testCase,TestStep testStep,boolean isPrerequisite) {
        try {
           Optional<TestCase> prerequiste =  new PreRequiste().getPrequisiteTestCase(testStep.getFieldName().split(":")[0],testStep.getFieldName().split(":")[1]) ;
           if(!prerequiste.isPresent()) {
               return new ArrayList<LogMessage>()
               {{
                   add(new LogMessage(false,"no valid prequisite found for " + testStep.getFieldName()));
               }};
           }
           if(isPrerequisite)
                new UtilKeywordScript(webDriver).redirectHomePage() ;
           TestCase preqTCase = prerequiste.get() ;
           preqTCase.setTestCaseNumber(testCase.getTestCaseNumber());
           preqTCase.setPreqData(UtilKeywordScript.jsonStringToMap(testStep.getTestData()));
           List<LogMessage> preqLogMessages =  executeTest(preqTCase) ;
           if(isPrerequisite) {
               new UtilKeywordScript(webDriver).redirectHomePage();
           }
           return preqLogMessages ;
        } catch (Exception ex) {
            return new ArrayList<LogMessage>()
            {{
                add(new LogMessage(false,"exception occured handling prerequiste " + ex.getMessage()));
            }};
        }
    }


    private Boolean isItPrequisite(String action) {
        if (action.equals(PropertyConfig.PREREQ_COMMAND)) return true;
        return false;
    }
    private Boolean isItCommon(String action) {
        if (action.equals(PropertyConfig.COMMON_COMMAND)) return true;
        return false;
    }
    public String updateTestData(String testCaseId,String testData, Optional<Map> utilData){
        try{
            if(Optional.ofNullable(testData).orElse("").equals(""))
                return testData ;
            String finalTestData="";
            String[] splitTestDatas=testData.split(",");
            TestData prerequisiteTestData = _testData ;
            if(splitTestDatas.length>0)
            {
                for(String splitTestData:splitTestDatas){
                    if(splitTestData != "" && splitTestData.charAt(0)=='$'){
                        splitTestData=splitTestData.substring(1);
                        String storeValue = TestPlan.getInstance().getStoreData(splitTestData) ;
                        if(null != storeValue && !storeValue.isEmpty())
                            finalTestData = finalTestData + storeValue ;
                        else {
                            String[] testDataDetails = splitTestData.split("_");
                            List<Map> datas = prerequisiteTestData.getData(testDataDetails[0].toUpperCase(), testCaseId);
                            String indexValue = utilData.isPresent() ? Optional.ofNullable((String) utilData.get().get(testDataDetails[0])).orElse("") : "";
                            if (indexValue != "")
                                testDataDetails[2] = indexValue;
                            if (testDataDetails.length == 2) {
                                Map data = datas.get(0);
                                finalTestData = finalTestData + (String) data.get(testDataDetails[1]);

                            } else if (testDataDetails.length == 3) {
                                Map data = datas.get(Integer.parseInt(testDataDetails[2]));
                                finalTestData = finalTestData + (String) data.get(testDataDetails[1]);
                            }
                        }
                    }
                    else
                        finalTestData=finalTestData+splitTestData;
                    finalTestData=finalTestData+",";

                }
                finalTestData=finalTestData.replaceAll(",$", "");
            }
            return finalTestData;
        }
        catch (Exception ex){
            return testData;
        }
    }
    private int delayTime(String time){
        try{
            return Integer.parseInt(time.trim());

        }catch (Exception e){
            return PropertyConfig.SHORT_WAIT_TIME_SECONDS;
        }
    }

    private String createLogMessage(String message, TestStep testStep,LogMessage log){
        String logMessage = "";
        if (!( null == message || message.isEmpty())){
            return message;
        }else {
            if ((testStep.getTestStepDescription().isEmpty()) && (testStep.getFieldName().isEmpty())){
                logMessage = log.getLogMessage();
            }
            else if ((testStep.getTestStepDescription().isEmpty()) && !(testStep.getFieldName().isEmpty())){
                logMessage = testStep.getFieldName() + "--" + log.getLogMessage();
            }
            else if ((testStep.getTestStepDescription().isEmpty()) && !(testStep.getFieldName().isEmpty())){
                logMessage = testStep.getFieldName() + "--" + log.getLogMessage();
            }
            else if (!(testStep.getTestStepDescription().isEmpty()) && (testStep.getFieldName().isEmpty())){
                logMessage = testStep.getTestStepDescription() + "--" + log.getLogMessage();
            }
            else {
                logMessage = testStep.getTestStepDescription() + " --" + testStep.getFieldName() + "--" + log.getLogMessage();
            }
            return logMessage;
        }
    }
}
