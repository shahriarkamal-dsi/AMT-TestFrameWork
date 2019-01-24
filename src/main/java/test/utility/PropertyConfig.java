package test.utility;

import java.io.FileInputStream;
import java.util.Properties;

public class PropertyConfig {
    public static String  MODULE_CONTROLLER = "ModuleController" ;
    public static String  CONTROLLER = "Controller" ;
    public static String OBJECT_REFERENCE = "ObjReference" ;
    public static String  MODULE_NAME = "ModuleName" ;
    public static String OBJECT_SEARCH_KEY = "ObjSearchKey" ;
    public static String OBJECT_LOCATOR = "ObjectLocators" ;
    public static String ACTION = "Action" ;
    public static String TEST_DATA = "TestData" ;
    public static String EXECUTION_FLAG = "ExecutionFlag" ;
    public static String TCID= "TCID" ;
    public static String TC_ID= "TC_ID" ;
    public static String TEST_CASE_NAME= "TestCaseName" ;
    public static String STEP_NO = "StepNo" ;
    public static String  TEST_STEP_DESCRIPTION = "TestStepDescription" ;
    public static String FIELD_NAME = "FieldName" ;
    public static String CRITICAL = "Critical"  ;
    public static String SHEET_NAME = "SheetName"  ;
    public static Integer INIT = 0 ;
    public static Integer CREATED = 1 ;
    public static Integer TESTED = 2 ;




    public static String  getPropertyValue(String property){
        try {
            ClassLoader classLoader = PropertyConfig.class.getClassLoader();
            String appConfigPath = classLoader.getResource("default.properties").getPath();
            Properties appProps = new Properties();
            appProps.load(new FileInputStream(appConfigPath));
            return appProps.getProperty(property);
        }catch (Exception ex) {
            ex.printStackTrace();
            return null ;

        }
    }


}
