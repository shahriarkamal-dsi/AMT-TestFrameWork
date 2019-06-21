package test.beforeTest;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import test.model.*;
import test.utility.PropertyConfig;
import test.utility.ReadExcel;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import test.service.* ;


@Component
public class DbDataInsertion {

    @Autowired
    private  SpaceService spaceService ;
    @Autowired
    private   PreqDataService preqDataService ;
    @Autowired
    private  TestDataMapService testDataMapService ;
    @Autowired
    private PropertyService propertyService;
    @Autowired
    private LeaseService leaseService;
    @Autowired
    private RprService rprService;


    public DbDataInsertion() {

    }
    public void dataInsertion() {
        try {
            propertyDataInsertion();
            leaseDataInsertion();
            spaceDataInsertion() ;
            rprDataInsertion();
        } catch (Exception ex)  {

        }

    }
    private void propertyDataInsertion(){
        try {
            List<Map> dataList = getData("Property");
            if(null==dataList)
                return;
            dataList.stream().forEach( item ->
                    {
                        try {
                            Property property=propertyService.getPropertyByPropertyCode((String) item.get("propertyCode"));
                            if(null==property) {
                                property = new Property();
                            }
                            property.setPropertyName((String) item.get("propertyName"));
                            property.setPropertyCode((String) item.get("propertyCode"));
                            property.setAddress((String)item.get("address1"));
                            property.setCountry((String)item.get("country"));
                            property.setState((String)item.get("state"));
                            property.setPostal((String)item.get("postal"));
                            property.setCity((String)item.get("city"));
                            property.setCodeType((String)item.get("codeType"));
                            property.setStatus((String)item.get("status"));
                            property.setCurrency((String)item.get("currency"));
                            property.setChartType((String)item.get("chartType"));
                            property.setSqFtRentable((String)item.get("sqFtRentable"));
                            property.setPropertyGroup1((String)item.get("propertyGroup1"));
                            property.setPropertyGroup2((String)item.get("propertyGroup2"));
                            property.setPropertyGroup3((String)item.get("propertyGroup3"));
                            property.setAutoManage((String)item.get("autoManage"));
                            propertyService.createOrUpdateProperty(property);
                            PrequisiteData prequisiteData =  preqDataService.getPrequisiteDataByDataIdAndType(property.getId(),"property") ;
                            TestDataMap testDataMap =  testDataMapService.getTestDataMapByPreqIdAndTcId(prequisiteData.getPreqId(),(String) item.get("TC_ID"));
                            if (null == testDataMap) {
                                testDataMap = new TestDataMap();
                                testDataMap.setPreqId(prequisiteData.getPreqId());
                                testDataMap.setTestCaseId((String)item.get("TC_ID"));
                                testDataMapService.createOrUpdateTestDataMap(testDataMap);
                            }

                        }catch (Exception ex) {
                            ex.printStackTrace();
                        }

                    }

            );
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    private void leaseDataInsertion(){
        try{
            List<Map> dataList = getData("Lease");
            if(null==dataList)
                return;
            dataList.stream().forEach( item ->
                    {
                        try {
                            Lease lease=leaseService.getLeaseByLeaseCode((String) item.get("leaseCode"));
                            if(null==lease) lease=new Lease();
                            lease.setPropertyName((String) item.get("propertyName"));
                            lease.setPropertyCode((String) item.get("propertyCode"));
                            lease.setLeaseName((String) item.get("dbaName"));
                            lease.setLeaseCode((String) item.get("leaseCode"));
                            lease.setLeaseStatus((String) item.get("leaseStatus"));
                            lease.setLeaseType((String) item.get("leaseType"));
                            lease.setBillingType((String) item.get("billingType"));
                            lease.setBeginDate((String) item.get("beginDate"));
                            lease.setExpirationDate((String) item.get("expirationDate"));
                            lease.setLeaseGroup1((String) item.get("leaseGroup1"));
                            lease.setCodeType((String) item.get("codeType"));
                            lease.setContractType((String) item.get("contractType"));
                            leaseService.createOrUpdateLease(lease);
                            PrequisiteData prequisiteData =  preqDataService.getPrequisiteDataByDataIdAndType(lease.getId(),"lease") ;
                            TestDataMap testDataMap =  testDataMapService.getTestDataMapByPreqIdAndTcId(prequisiteData.getPreqId(),(String) item.get("TC_ID"));
                            if (null == testDataMap) {
                                testDataMap = new TestDataMap();
                                testDataMap.setPreqId(prequisiteData.getPreqId());
                                testDataMap.setTestCaseId((String)item.get("TC_ID"));
                                testDataMapService.createOrUpdateTestDataMap(testDataMap);
                            }

                        }catch (Exception ex) {
                            ex.printStackTrace();
                        }

                    }

            );

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void spaceDataInsertion() {
        try {
            List<Map> dataList =getData("Space");
            if(null==dataList)
                return;
            dataList.stream().forEach( item ->
                    {
                        try {
                            Space space = spaceService.getSpaceBySpaceName((String) item.get("Space"));
                            if (null == space) space = new Space();
                            space.setSpaceName((String) item.get("Space"));
                            space.setPropertyCode((String) item.get("propertyName"));
                            space.setPropertyName((String) item.get("propertyCode"));
                            space.setLeaseName((String) item.get("LeaseName"));
                            space.setFloor((String) item.get("Floor"));
                            space.setStartDate((String) item.get("startDate"));
                            space.setEndDate((String) item.get("endDate"));
                            space.setRentableLease((String) item.get("rentableLease") );
                            spaceService.createOrUpdateSpace(space);
                            PrequisiteData prequisiteData =  preqDataService.getPrequisiteDataByDataIdAndType(space.getId(),"space") ;
                            TestDataMap testDataMap =  testDataMapService.getTestDataMapByPreqIdAndTcId(prequisiteData.getPreqId(),(String) item.get("TC_ID"));
                            if (null == testDataMap) {
                                testDataMap = new TestDataMap();
                                testDataMap.setPreqId(prequisiteData.getPreqId());
                                testDataMap.setTestCaseId((String)item.get("TC_ID"));
                                testDataMapService.createOrUpdateTestDataMap(testDataMap);
                            }

                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }


                    }

            );

        } catch ( Exception ex) {
            ex.printStackTrace();
        }
    }
    private void rprDataInsertion() {
        try {
            List<Map> dataList =getData("RecurringPayment");
            if(null==dataList)
                return;
            dataList.stream().forEach( item ->
                    {
                        try {
                            Rpr rpr=rprService.getRprBySpaceNameAndChargeType((String)item.get("spaceInfo"),(String)item.get("chargeType"));
                            if(null==rpr)rpr=new Rpr();
                            rpr.setLeaseName((String)item.get("LeaseName"));
                            rpr.setChargeType((String)item.get("chargeType"));
                            rpr.setSpaceName((String)item.get("spaceInfo"));
                            rpr.setChargeName((String)item.get("chargeName"));
                            rpr.setFrequency((String)item.get("frequency"));
                            rpr.setEscalationType((String)item.get("escalationType"));
                            rpr.setLeaseTermYear((String)item.get("leaseTermYear"));
                            rpr.setLeaseTermDefined((String)item.get("leaseTermDefined"));
                            rpr.setEffectiveDate((String)item.get("effDate"));
                            rpr.setEndDate((String)item.get("endDate"));
                            rpr.setAmount((String)item.get("amount"));
                            rpr.setFiscalYear((String)item.get("fiscalYear"));
                            rprService.createOrUpdateRpr(rpr);
                            PrequisiteData prequisiteData =  preqDataService.getPrequisiteDataByDataIdAndType(rpr.getId(),"rpr") ;
                            TestDataMap testDataMap =  testDataMapService.getTestDataMapByPreqIdAndTcId(prequisiteData.getPreqId(),(String) item.get("TC_ID"));
                            if (null == testDataMap) {
                                testDataMap = new TestDataMap();
                                testDataMap.setPreqId(prequisiteData.getPreqId());
                                testDataMap.setTestCaseId((String)item.get("TC_ID"));
                                testDataMapService.createOrUpdateTestDataMap(testDataMap);
                            }

                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }


                    }

            );

        } catch ( Exception ex) {
            ex.printStackTrace();
        }
    }



    private void propertDataInsertion() {
        try {
            List<Map> dataList = getData("Property");
            if(null == dataList)
                return;

            dataList.stream().forEach( item ->
                    {
                        try {
                            Space space = spaceService.getSpaceBySpaceName((String) item.get("Space"));
                            if (null == space) space = new Space();
                            space.setSpaceName((String) item.get("Space"));
                            space.setPropertyCode((String) item.get("propertyName"));
                            space.setPropertyName((String) item.get("propertyCode"));
                            space.setLeaseName((String) item.get("LeaseName"));
                            space.setFloor((String) item.get("Floor"));
                            space.setStartDate((String) item.get("startDate"));
                            space.setEndDate((String) item.get("endDate"));
                            spaceService.createOrUpdateSpace(space);
                            PrequisiteData prequisiteData =  preqDataService.getPrequisiteDataByDataIdAndType(space.getId(),"space") ;
                            TestDataMap testDataMap =  testDataMapService.getTestDataMapByPreqIdAndTcId(prequisiteData.getPreqId(),(String) item.get("TC_ID"));
                            if (null == testDataMap) {
                                testDataMap = new TestDataMap();
                                testDataMap.setPreqId(prequisiteData.getPreqId());
                                testDataMap.setTestCaseId((String)item.get("TC_ID"));
                                testDataMapService.createOrUpdateTestDataMap(testDataMap);
                            }

                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }


                    }

            );

        } catch ( Exception ex) {
            ex.printStackTrace();
        }
    }


    private List<Map> getData(String sheetName) {
        ClassLoader classLoader = TestData.class.getClassLoader();
        ReadExcel readExcel = new ReadExcel(classLoader.getResource("dataCreate/DataCreate.xlsx").getPath());
        return   readExcel.read(sheetName).stream().filter(row ->  row.get("ExecutionFlag").toString().toLowerCase().equals("yes")).collect(Collectors.toList());
    }
}
