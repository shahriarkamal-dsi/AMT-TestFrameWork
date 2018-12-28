package test.objectLocator;

import java.util.HashMap;
import java.util.Map;

public class ObjectLocatorDataStorage {
    private Map<String,Map> objectLocatorStorage ;
   // private Map<String,Boolean> sheetCalled ;

    public ObjectLocatorDataStorage(){
        this.objectLocatorStorage = new HashMap<String,Map>() ;
    }

    public Map getObjectLocator(String orPath) throws Exception {
        if(objectLocatorStorage.containsKey(orPath) && null != objectLocatorStorage.get(orPath)) {
            return objectLocatorStorage.get(orPath) ;
        } else {
            OrRead orRead = new OrRead(orPath);
            this.addObjectLocaor(orRead.getOrFromSheet());
            if(objectLocatorStorage.containsKey(orPath) && null != objectLocatorStorage.get(orPath)) {
                return objectLocatorStorage.get(orPath) ;
            } else {
                throw  new Exception("ObjectLocator not found.");
            }
        }
    }

    public void addObjectLocaor(Map objectLocator){
        this.objectLocatorStorage.putAll(objectLocator);
    }
}
