package test.beforeTest;

import com.google.gson.JsonArray;
import org.assertj.core.error.ShouldBeAfterYear;
import org.json.JSONArray;
import org.json.JSONObject;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import test.Log.LogMessage;
import test.coreModule.PreRequiste;
import test.coreModule.TestPlan;
import test.model.*;
import test.repository.PreqExecutionHistoryRepo;
import test.service.NotExecutedPreqData;
import test.service.PreqDataService;
import test.service.PreqExecutionHistoryService;
import test.service.TestDataMapService;
import test.utility.PropertyConfig;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;



@Component
public class TestData {


    @Autowired
    private TestDataMapService testDataMapService ;
    @Autowired
    private PropertyCreateAndSearch _propertyCreateAndSearch ;
    @Autowired
    private LeaseCreateAndSearch _leaseCreateAndSearch ;
    @Autowired
    private SpaceCreateAndSearch _spaceCreateAndSearch ;
    @Autowired
    private RecurringPaymentCreateandSearch _recurringPaymentCreateandSearch ;
    @Autowired
    private PreqDataService preqDataService ;
    @Autowired
    private PreqExecutionHistoryService preqExecutionHistoryService ;

    private WebDriver driver;
   // private String clientId=PropertyConfig.getPropertyValue("client");
  //  private String environment=PropertyConfig.getPropertyValue("env");
    public TestData() {

    }



    public List<LogMessage> createTestData(String testCaseId, String notUsedValue) {

        List<LogMessage> logMessageList =new ArrayList<>();
        try {
            String clientId = TestPlan.getInstance().getCurrentTestEnvironment().getClient();
            String environment = TestPlan.getInstance().getCurrentTestEnvironment().getEnv();
            List<LogMessage> logMessages = new ArrayList<LogMessage>() ;
            //NotExecutedPreqData notExecutedPreqData =  testDataMapService.getNotExecutedPreqData(testCaseId,clientId,environment,100);
            NotExecutedPreqData notExecutedPreqData =  new NotExecutedPreqData();
            PropertyCreateAndSearch propertyCreateAndSearch = _propertyCreateAndSearch ;
            List<Map> propertyRecords=notExecutedPreqData.getPropertyList().stream().map( e -> e.getPropertyMap()).collect(Collectors.toList());
           for(Map propertyRecord: propertyRecords){
                LogMessage logMessage = propertyCreateAndSearch.createProperty(propertyRecord);
                JSONObject jso = new JSONObject();
                jso.put("dataId",propertyRecord.get("dataId") );
                jso.put("type","property" );
                jso.put("isPassed",logMessage.isPassed() );
                logMessage.addJsonObject(jso);
                logMessageList.add(logMessage);
                putPreqExecutionData(logMessageList);
            }
            LeaseCreateAndSearch leaseCreateAndSearch = _leaseCreateAndSearch ;
            List<Map> leaseRocords = notExecutedPreqData.getLeaseList().stream().map( e -> e.getLeaseMap()).collect(Collectors.toList()) ;
            if(null != leaseRocords && !leaseRocords.isEmpty()) {
                logMessages=leaseCreateAndSearch.createMultipleLeases(leaseRocords);
                putPreqExecutionData(logMessages);
                logMessageList.addAll(logMessages);
            }
            SpaceCreateAndSearch spaceCreateAndSearch = _spaceCreateAndSearch ;
            Map<String, List<Map>> spacesList = new HashMap<>();
            for (Map spaceRecord : notExecutedPreqData.getSpaceList().stream().map( e -> e.getSpaceMap()).collect(Collectors.toList())) {
                if (!spacesList.containsKey(spaceRecord.get("LeaseName"))) {
                    List<Map> record = new ArrayList<Map>();
                    record.add(spaceRecord);
                    spacesList.put((String) spaceRecord.get("LeaseName"), record);
                } else {
                    spacesList.get(spaceRecord.get("LeaseName")).add(spaceRecord);
                }

            }
            for (String key : spacesList.keySet()) {
                logMessages = spaceCreateAndSearch.createMultipleSpaces(spacesList.get(key));
                putPreqExecutionData(logMessages);
                logMessageList.addAll(logMessages);
            }


            Map<String, List<Map>> recurList = new HashMap<>();
            RecurringPaymentCreateandSearch recurringPaymentCreateandSearch = _recurringPaymentCreateandSearch ;
            for (Map spaceRecord : notExecutedPreqData.getRprList().stream().map( e -> e.getRprMap()).collect(Collectors.toList())) {
                if (!recurList.containsKey(spaceRecord.get("LeaseName"))) {
                    List<Map> record = new ArrayList<Map>();
                    record.add(spaceRecord);
                    recurList.put((String) spaceRecord.get("LeaseName"), record);
                } else {
                    recurList.get(spaceRecord.get("LeaseName")).add(spaceRecord);
                }

            }
            //System.out.println(spacesList);
            for (String key : recurList.keySet()) {
                logMessages = recurringPaymentCreateandSearch.addMultipleRecurringPayments(recurList.get(key));
                putPreqExecutionData(logMessages);
                logMessageList.addAll(logMessages);
            }
            if(logMessageList.stream().anyMatch(o -> o.isPassed().equals(false))) {
                logMessageList.add(new LogMessage(false, "Prerequisite not fulfilled"));
                return logMessageList;
            }

            return  logMessageList;
        } catch (Exception ex) {
            ex.printStackTrace();
            logMessageList.add(new LogMessage(false, "Prerequisite not fulfilled"));
            return logMessageList;
        }
    }


    public void setDriver(WebDriver wbd){
        this.driver=wbd;
        _propertyCreateAndSearch.setDriver(driver);
        _leaseCreateAndSearch.setDriver(driver);
        _spaceCreateAndSearch.setDriver(driver);
        _recurringPaymentCreateandSearch.setWebDriver(driver);
    }
    public WebDriver getDriver(){return driver;}



    private void putPreqExecutionData(List<LogMessage> logMessages) {
        try {
            for (LogMessage lm : logMessages) {
                JSONArray jsonArray = lm.getUitlData() ;
                JSONObject jso = (JSONObject) Optional.ofNullable(jsonArray.get(0)).orElse(new JSONObject());
              if(null !=jso.get("dataId")) {
                  String client = TestPlan.getInstance().getCurrentTestEnvironment().getClient() ;
                  String env = TestPlan.getInstance().getCurrentTestEnvironment().getEnv() ;
                  preqExecutionHistoryService.putPreqExecutionData(client, env, Long.valueOf(jso.get("dataId").toString()), jso.get("type").toString(), (boolean) jso.get("isPassed"));
              }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }



    public List<Map> getData(String type,String tcId) {
        if(type.toLowerCase().equals("property"))
          return  testDataMapService.getPropertyRecordsByTcId(tcId).stream().map(o->o.getPropertyMap()).collect(Collectors.toList());
      else if(type.toLowerCase().equals("lease"))
            return  testDataMapService.getLeaseRecordsByTcId(tcId).stream().map(o->o.getLeaseMap()).collect(Collectors.toList());
       else if(type.toLowerCase().equals("space"))
            return  testDataMapService.getSpaceRecordsByTcId(tcId).stream().map(o->o.getSpaceMap()).collect(Collectors.toList());
          else
              return  testDataMapService.getRprRecordsByTcId(tcId).stream().map(o->o.getRprMap()).collect(Collectors.toList());

    }

    public void  removePreqExecutionHistory(long preqId,String client, String env) {
        preqExecutionHistoryService.removePreqExecutionHistory(preqExecutionHistoryService.getPreqExecutionHistory(preqId,client,env));
    }

}

