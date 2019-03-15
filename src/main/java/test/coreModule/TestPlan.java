package test.coreModule;

import org.junit.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TestPlan {
    private static TestPlan TEST_PLAN = new TestPlan() ;
    private String testPlanName ;
    private List<TestModule> testModules;
    private LocalDateTime creationTime  ;

    private TestPlan() {
        creationTime = LocalDateTime.now();
        testModules = new ArrayList<TestModule>();
    }

   public static  TestPlan getInstance() {
        return TEST_PLAN;
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
        Optional<TestModule>  testModule = testModules.stream().filter(tm -> tm.getModuleName().equals(moduleName)).findFirst() ;
        return  testModule.isPresent() ? testModule.get() : null ;

    }

    public List<TestModule>  getAllTesModules(){
        return  testModules ;
    }


}
