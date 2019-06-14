package test;

import org.openqa.selenium.WebDriver;
import test.beforeTest.DbDataInsertion;
import test.beforeTest.TestData;
import test.driver.DriverFactory;
import test.keywordScripts.UtilKeywordScript;
import test.model.*;
import test.repository.PreqExecutionHistoryRepo;
import test.service.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import test.utility.PropertyConfig;

import java.time.LocalDateTime;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = AmtTestFrameworkApplication.class)
public class TestDataApplicationTests {

	@Autowired
	private PropertyService propertyService;
	@Autowired
	private LeaseService leaseService;
	@Autowired
	private SpaceService spaceService;
	@Autowired
	private RprService rprService;
	@Autowired
	private PreqDataService preqDataService ;
	@Autowired
	private TestDataMapService testDataMapService ;
	@Autowired
	private PreqExecutionHistoryService preqExecutionHistoryService ;
	@Autowired
	private PreqExecutionHistoryRepo preqExecutionHistoryRepo ;
	@Autowired
	private DbDataInsertion dbDataInsertion ;
	@Autowired
    TestData _testData;



	@Test
	public void contextLoads() {

	}

	@Test
	public void testDBInsertion() {
		dbDataInsertion.dataInsertion();
	}



}
