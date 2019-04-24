package test.service;

import test.model.PreqExecutionHistory;
import test.model.PrequisiteData;
import test.model.TestDataMap;
import test.repository.PreqExecutionHistoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Supplier;
import java.util.stream.Stream;

@Service
public class PreqExecutionHistoryService {

    @Autowired
    private PreqExecutionHistoryRepo preqExecutionHistoryRepo ;
    @Autowired
    private PreqDataService preqDataService ;

    @Autowired
    PreqExecutionHistoryService preqExecutionHistoryService ;

    public void createOrUpdatePreqExecutionHistory(PreqExecutionHistory preqExecutionHistory) {
        PreqExecutionHistory existItem = getPreqExecutionHistory(preqExecutionHistory.getPreqId(),preqExecutionHistory.getClientId(),preqExecutionHistory.getEnvironment());
        if(null!= existItem)
            removePreqExecutionHistory(existItem);
        preqExecutionHistoryRepo.save(preqExecutionHistory) ;
    }

    public PreqExecutionHistory getPreqExecutionHistory(Long id) {
       return preqExecutionHistoryRepo.findById(id).orElse(null);
    }

    public void removePreqExecutionHistory(PreqExecutionHistory preqExecutionHistory) {
        preqExecutionHistoryRepo.delete(preqExecutionHistory);
    }

    public PreqExecutionHistory getPreqExecutionHistory(long preqId, String clientId, String env) {
        return preqExecutionHistoryRepo.findFirstByPreqIdAndClientIdAndEnvironmentOrderByCreationTimeDesc(preqId,clientId,env) ;
    }
    public void putPreqExecutionData(String clientId,String environment,long dataId,String type,boolean isPassed){
        PreqExecutionHistory preqExecutionHistory=new PreqExecutionHistory();
        PrequisiteData prequisiteData =preqDataService.getPrequisiteDataByDataIdAndType(dataId,type);
        preqExecutionHistory.setClientId(clientId);
        preqExecutionHistory.setEnvironment(environment);
        preqExecutionHistory.setCreationTime(LocalDateTime.now());
        preqExecutionHistory.setPreqId(prequisiteData.getPreqId());
        preqExecutionHistory.setPassed(isPassed);
        preqExecutionHistoryService.createOrUpdatePreqExecutionHistory(preqExecutionHistory);
    }





    public NotExecutedPreqData getNotExecutedPreq(Stream<TestDataMap> items,String clientId,String env,int dayGap) {
        try {
            NotExecutedPreqData notExecutedPreqData = new NotExecutedPreqData() ;
            List<PrequisiteData> prequisiteData = new ArrayList<PrequisiteData>() ;
            AtomicBoolean isAnyPreqfailed = new AtomicBoolean(false);

            items.forEach( item ->
                    {
                        PreqExecutionHistory preqExecutionHistory = getPreqExecutionHistory(item.getPreqId(),clientId,env) ;
                        if(null == preqExecutionHistory) {
                            prequisiteData.add(preqDataService.getPrequisiteData(item.getPreqId()));
                        } else  {
                          LocalDateTime creationTime = preqExecutionHistory.getCreationTime() ;
                            int currentDayDiff =  Period.between( creationTime.toLocalDate(),LocalDate.now()).getDays();
                            if(currentDayDiff >dayGap) {
                                prequisiteData.add(preqDataService.getPrequisiteData(item.getPreqId()));
                            } else {
                                if(!preqExecutionHistory.isPassed()) {
                                    prequisiteData.add(preqDataService.getPrequisiteData(item.getPreqId()));
                                    isAnyPreqfailed.set(true);
                                }
                            }

                        }
                    }
            );
            notExecutedPreqData.setAnyPreqFailed(isAnyPreqfailed.get());
            notExecutedPreqData.setPrequisiteData(prequisiteData);
            notExecutedPreqData.setPropertyList(preqDataService.getPropertyRecords(prequisiteData.stream()));
            notExecutedPreqData.setLeaseList(preqDataService.getLeaseRecords(prequisiteData.stream()));
            notExecutedPreqData.setSpaceList(preqDataService.getSpaceRecords(prequisiteData.stream()));
            notExecutedPreqData.setRprList(preqDataService.getRprRecords(prequisiteData.stream()));
            return notExecutedPreqData;
        } catch (Exception ex) {
            ex.printStackTrace();
            return new NotExecutedPreqData() ;
        }
    }


}
