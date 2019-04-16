package test.repository;

import test.model.Property;
import test.model.TestDataMap;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TestDataMapRepo extends JpaRepository<TestDataMap,Long>{

    List<TestDataMap> findByTestCaseId(String testcaseId);
}
