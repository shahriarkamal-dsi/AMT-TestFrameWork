package test.objectLocator;

import test.utility.PropertyConfig;
import test.utility.ReadExcel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrRead {
    String orPath  ;

    public  OrRead(String path){
        this.orPath = path ;
    }
    public List<Map> getOrFromSheet() {
        List<Map> orLists = new ArrayList<Map>() ;
        String[] splitPath = orPath.split("\\.");
        List<Map> orRecords = new ArrayList<Map>();
        String filePath = "G:\\testing\\AMTDirect_NousAutomation\\Framework\\ObjectRepository\\"+splitPath[0]+".xlsx";
        ReadExcel readExcel = new ReadExcel(filePath);
        List<Map> records = readExcel.read(splitPath[1]);
        for(Map map: records) {
           String objectReference = (String) map.get(PropertyConfig.OBJECT_REFERENCE) ;
            Map objectLocator = new HashMap();
            String objectKey = splitPath[0] + "." +  splitPath[1] + "." +  objectReference ;
            objectLocator.put(objectKey,map) ;
            orLists.add(objectLocator);
        }
        return orLists ;
    }
}
