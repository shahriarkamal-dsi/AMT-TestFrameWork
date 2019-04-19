package test.beforeTest;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import test.model.PrequisiteData;
import test.model.Space;
import test.model.TestDataMap;
import test.utility.PropertyConfig;
import test.utility.ReadExcel;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import test.service.* ;


@Component
public class DbDataInsertion {

    @Autowired
    private  SpaceService spaceService ;
    @Autowired
    private   PreqDataService preqDataService ;
    @Autowired
    private  TestDataMapService testDataMapService ;


    public DbDataInsertion() {

    }
    public void dataInsertion() {
        try {
            spaceDataInsertion() ;
        } catch (Exception ex)  {

        }

    }

    private void spaceDataInsertion() {
        try {
             List<Map> dataList = getData("Space");
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
        ReadExcel readExcel = new ReadExcel(classLoader.getResource("dataCreate/DataCreate_temp.xlsx").getPath());
        return readExcel.read(sheetName);
    }
}
