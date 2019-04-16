package test.repository;

import test.model.Property;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PropertyRepo extends JpaRepository<Property,Long>{
     Optional<Property> findOneByPropertyCode(String propertyCode);
}
