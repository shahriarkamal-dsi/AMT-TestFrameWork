package test.coreModule;

import org.openqa.selenium.WebDriver;
import test.utility.LogMessage;
import test.utility.PropertyConfig;
import test.utility.ReadExcel;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ExecuteTests {

    private WebDriver webDriver;
    public ExecuteTests(WebDriver driver) {
        webDriver = driver ;
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
            String objectLocators = (String) map.get(PropertyConfig.OBJECT_LOCATOR);
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
            invokeMethod(actionName.split("\\.")[0],actionName.split("\\.")[1],numberOfParams,objects.toArray());
        }

    }
    public void executeTest(TestCase testCase) {
        ClassLoader classLoader = getClass().getClassLoader();
        long start = System.currentTimeMillis();
        List<TestStep> testSteps = testCase.getAllTestSteps();
        for(TestStep testStep : testSteps) {
            ArrayList<Object> objects = new ArrayList<Object>();
            // objects.add(webDriver);
            String actionName = testStep.getAction();
            String objectLocators = testStep.getObjectLocator();
            String testData = testStep.getTestData();
            Boolean executionFlag = testStep.isExecutionFlagOn();
            Boolean critical = testStep.isCritical();
            int numberOfParams = 0;

            if (! executionFlag)
                continue;
            if(null != objectLocators && ! objectLocators.isEmpty()) {
                objects.add(objectLocators);
                numberOfParams++;
            }
            if(null != testData && ! testData.isEmpty()){
                objects.add(testData);
                numberOfParams++;
            }
            invokeMethod(actionName.split("\\.")[0],actionName.split("\\.")[1],numberOfParams,objects.toArray());
        }

    }

    public void invokeMethod(String className,String methodName,int numberOfParams,Object[] object) {
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
                return;
            Constructor<?> constructor = callingClass.getConstructor(WebDriver.class);
            LogMessage logMessage = (LogMessage) callingMethod.invoke(constructor.newInstance(webDriver),object);
            System.out.println(logMessage.getLogMessage());
        } catch(Exception ex) {
               ex.printStackTrace();
        }
    }
}
