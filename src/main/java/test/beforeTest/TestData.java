package test.beforeTest;

import test.Log.LogMessage;
import test.utility.PropertyConfig;
import test.utility.ReadExcel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestData {


    public static Map<String,List> PropertyData = new HashMap<String,List>();
    public static Map<String,List> LeaseData = new HashMap<String,List>();
    public static Map<String,List> SpaceData = new HashMap<String,List>();
    public static Map<String,List> RecurData = new HashMap<String,List>();

   /* public static void fetchAndStoreTestData(String sheetName) {

        ClassLoader classLoader = TestData.class.getClassLoader();
        ReadExcel readExcel = new ReadExcel(classLoader.getResource("dataCreate/PropertyCreate.xlsx").getPath());
        List<Map> data = readExcel.read(sheetName);
        Map<String,List> sotreData = getDataObject(sheetName);
        for(Map item: data) {
            if(PropertyData.containsKey(item.get(PropertyConfig.TC_ID))) {
                PropertyData.get(items.get(PropertyConfig.TC_ID)).add(property);
           } else {
                List<Map> record = new ArrayList<Map>() ;
                record.add(

                ) ;
                PropertyData.put((String) item.get(PropertyConfig.TC_ID),items);
            }
        }
    }

    public static void createLeaseData() {
        ClassLoader classLoader = TestData.class.getClassLoader();
        ReadExcel readExcel = new ReadExcel(classLoader.getResource("dataCreate/PropertyCreate.xlsx").getPath());
        //PropertyData = readExcel.read("Lease");
    }


    private static Map<String,List> getDataObject(String sheetName) {
       switch(sheetName) {
           case "Property": return PropertyData ;
           case "Lease": return LeaseData ;
           case "Space": return SpaceData ;
           case "Recur": return  RecurData ;
           default: return null ;
       }
    }


    public static LogMessage createApplicationData(String testCaseNumber) {
        try {

        } catch (Exception ex) {

        }
    }
    */


}
