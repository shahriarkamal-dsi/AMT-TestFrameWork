package test.coreModule;

import org.junit.Test;

import javax.jws.WebParam;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TestPlan {
    private String testPlanName ;
    private List<TestModule> testModules;
    private LocalDateTime creationTime  ;

    public TestPlan() {
        creationTime = LocalDateTime.now();
        testModules = new ArrayList<TestModule>();
    }

    public String  getTestPlanName(){
        return this.testPlanName;
    }
    public void  setTestPlanName(String name){
         this.testPlanName = name ;
    }
    public LocalDateTime  getCreationTime(){
        return this.creationTime;
    }

    public void addTestModule(TestModule  testModule){
        testModules.add(testModule);
    }

    public TestModule getTestModule(String moduleName){
        for(TestModule testModule : testModules){
            if(testModule.getModuleName().equals(moduleName))
                return  testModule ;
        }
        return null;
    }

    public List<TestModule>  getAllTesModules(){
        return  testModules ;
    }


}
