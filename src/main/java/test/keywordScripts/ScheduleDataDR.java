package test.keywordScripts;

import java.util.*;

public class ScheduleDataDR {
    public static ScheduleDataDR SCHEDULE_DATA = new ScheduleDataDR() ;
    public Map storeData = new HashMap<String,String>() ;
    public Map paymentMap = new HashMap<String,String>() ;
    /*
    * Adding paymentMap,scheduleTable
    * Adding setPaymentMap,getPaymentMap and setScheduleTable,getScheduleTable
    * */

    public String getStoreData(String key) {
        return (String) storeData.get(key);
    }
    public void setStoreData(String key,String value) {
        storeData.put(key,value) ;
    }
    public String getPaymentMap(String key) {
        return (String) paymentMap.get(key);
    }
    public void setPaymentMap(String key,String value) {
        paymentMap.put(key,value) ;
    }

    public ScheduleDataDR() {
    }
   public static ScheduleDataDR getInstance() {
        return SCHEDULE_DATA;
   }

}
