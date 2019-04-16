package test.repository;

import test.model.PrequisiteData;
import test.model.Property;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PrequisiteDataRepo extends JpaRepository<PrequisiteData,Long>{
}
