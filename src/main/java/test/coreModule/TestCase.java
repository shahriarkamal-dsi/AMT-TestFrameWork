package test.coreModule;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestCase {
    private String testCaseNumber;
    private String testCaseName;
    private List<TestStep> testSteps ;
    private boolean passed ;

    public TestCase(){
        testSteps = new ArrayList<TestStep>() ;
    }
    public TestCase(String testCaseNumber){
        testSteps = new ArrayList<TestStep>() ;
        this.testCaseNumber = testCaseNumber.split("\\.")[0];
    }

    public String getTestCaseNumber() {
        return testCaseNumber;
    }

    public void setTestCaseNumber(String number){
        this.testCaseNumber = number ;
    }
    public String getTestCaseName(){
        return testCaseName;
    }

    public void setTestCaseName(String name){
        this.testCaseName= name;
    }
    public void addTestStep(TestStep testStep ){
        testSteps.add(testStep);
    }

    public List<TestStep>  getAllTestSteps(){
        return  testSteps ;
    }

    public boolean isPassed() {
        return  passed;
    }
    public void setPassed(Boolean val){
        passed = val ;
    }

}
