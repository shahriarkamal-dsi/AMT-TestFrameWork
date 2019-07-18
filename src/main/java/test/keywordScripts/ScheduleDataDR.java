package test.keywordScripts;

import java.util.*;

public class ScheduleDataDR {
    public static ScheduleDataDR SCHEDULE_DATA = new ScheduleDataDR() ;

    public Map paymentMap = new HashMap<String,String>() ;
    public List scheduleList = new ArrayList();
    public double amountToCapitalize;

    /*
    * Adding paymentMap,scheduleTable
    * Adding setPaymentMap,getPaymentMap and setScheduleTable,getScheduleTable
    * */


    public String getPaymentMap(String key) {
        return (String) paymentMap.get(key);
    }

    public void setPaymentMap(String key,String value) {
        paymentMap.put(key,value);
    }

    public void clearPaymentMap() { paymentMap.clear();
        System.out.println("paymentMap value after clear"+paymentMap);
    }

    public Object getScheduleList(int index) {
        return scheduleList.get(index);
    }

    public Map<String, String> getFullPaymentMap()
    {
        Map<String, String> theThings = new HashMap<>();
        theThings = paymentMap;
        return theThings;
    }

    public void setScheduleList(int index, Object value) {
        scheduleList.add(index,value);
    }

    public void clearScheduleList() {
        scheduleList.clear();
        System.out.println("scheduleList value after clear"+scheduleList);
    }

    public <T> List<T> getScheduleTable() {
        System.out.println("getScheduleTable Method Called ");
        List<T> list = new ArrayList<>();
        list = scheduleList;
        System.out.println("List from getScheduleTable: "+list);
        return list;
    }

    public ScheduleDataDR() {
    }
   public static ScheduleDataDR getInstance() {
        return SCHEDULE_DATA;
   }

    public double getAmountToCapitalize() {
        return amountToCapitalize;
    }

    public void setAmountToCapitalize(double amountToCapitalize) {
        this.amountToCapitalize = amountToCapitalize;
    }
}
