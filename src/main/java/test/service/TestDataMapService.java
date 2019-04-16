package test.service;

import test.model.*;
import test.repository.TestDataMapRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class TestDataMapService {

    @Autowired
    private TestDataMapRepo testDataMapRepo ;
    @Autowired
    private PreqDataService prequisiteDataService;
    @Autowired
    PropertyService propertyService ;
    @Autowired
    LeaseService leaseService ;
    @Autowired
    SpaceService spaceService ;
    @Autowired
    RprService rprService ;
    @Autowired
    PreqDataService preqDataService ;
   @Autowired
   PreqExecutionHistoryService preqExecutionHistoryService ;
    public void createOrUpdateTestDataMap(TestDataMap testDataMap) {
        testDataMapRepo.save(testDataMap) ;
    }

    public TestDataMap getTestDataMap(Long id) {
        return testDataMapRepo.findById(id).orElse(null) ;
    }


    private Stream<TestDataMap> getFilterDataByType(String tcId,String type) {
        try {
            List<TestDataMap> records = testDataMapRepo.findByTestCaseId(tcId);
            return  records.stream().filter(record -> prequisiteDataService.getPrequisiteData(record.getPreqId()).getType().equals(type)) ;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null ;
        }
    }


    private Stream<PrequisiteData> getPrequisiteDataStreamByTcId(String tcId) {
        try {
            List<TestDataMap> records = testDataMapRepo.findByTestCaseId(tcId);
        return    records.stream().map( testDataMap -> prequisiteDataService.getPrequisiteData(testDataMap.getPreqId())).collect(Collectors.toList()).stream();
        } catch (Exception ex) {
            ex.printStackTrace();
            return null ;
        }
    }

    public List<Property> getPropertyRecordsByTcId(String tcId) {
        try {
            return preqDataService.getPropertyRecords(getPrequisiteDataStreamByTcId(tcId));
        } catch (Exception ex) {
            ex.printStackTrace();
            return new ArrayList<Property>() ;
        }
    }

    public List<Lease> getLeaseRecordsByTcId(String tcId) {
        try {
            return preqDataService.getLeaseRecords(getPrequisiteDataStreamByTcId(tcId));
        } catch (Exception ex) {
            ex.printStackTrace();
            return new ArrayList<Lease>() ;
        }
    }

    public List<Space> getSpaceRecordsByTcId(String tcId) {
        try {
            return preqDataService.getSpaceRecords(getPrequisiteDataStreamByTcId(tcId));
        } catch (Exception ex) {
            ex.printStackTrace();
            return new ArrayList<Space>() ;
        }
    }

    public List<Rpr> getRprRecordsByTcId(String tcId) {
        try {
            return preqDataService.getRprRecords(getPrequisiteDataStreamByTcId(tcId));
        } catch (Exception ex) {
            ex.printStackTrace();
            return new ArrayList<Rpr>() ;
        }
    }

    public NotExecutedPreqData getNotExecutedPreqData(String tcId,String clientId,String env,int dayGap) {
        return  preqExecutionHistoryService.getNotExecutedPreq(testDataMapRepo.findByTestCaseId(tcId).stream(),clientId,env,dayGap);
    }



}
