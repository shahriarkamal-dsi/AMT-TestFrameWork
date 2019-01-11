package test.coreModule;

import org.junit.Test;
import test.utility.PropertyConfig;
import test.utility.ReadExcel;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MainController {
        final ClassLoader CLASS_LOADER = getClass().getClassLoader();

    public void run(){
        TestPlan testPlan = createTestPlan();
        List<TestModule> modules = testPlan.getAllTesModules();
        for(TestModule module : modules){
            if(module.getState().equals(PropertyConfig.INIT)){
                ReadExcel readExcel = new ReadExcel(CLASS_LOADER.getResource("modules/" + module.getModuleName() + ".xlsx").getPath());
                List<Map> records = readExcel.read(PropertyConfig.CONTROLLER);
            }
        }
    }

    public TestPlan createTestPlan(){
        long start = System.currentTimeMillis();
        ReadExcel readExcel = new ReadExcel(CLASS_LOADER.getResource("testPlan/" + PropertyConfig.MODULE_CONTROLLER + ".xlsx").getPath());
        List<Map> records = readExcel.read(PropertyConfig.MODULE_CONTROLLER);
        TestPlan testPlan = new TestPlan();
        testPlan.setTestPlanName(LocalDateTime.now().toString());

        for(Map map : records) {
            String  moduleName = (String) map.get(PropertyConfig.MODULE_NAME);
            String executionFlag = (String) map.get(PropertyConfig.EXECUTION_FLAG);
            if ( null == moduleName || moduleName.isEmpty())
                continue;
            if ( null == executionFlag || !executionFlag.toLowerCase().equals("yes"))
                continue;
            TestModule testModule = new TestModule(moduleName);
            testPlan.addTestModule(testModule);
            }
        return testPlan ;
        }
    }
