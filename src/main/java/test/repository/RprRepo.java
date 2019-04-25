package test.repository;

import test.model.Property;
import test.model.Rpr;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RprRepo extends JpaRepository<Rpr,Long>{
    Optional<Rpr> findOneBySpaceNameAndChargeType(String spaceName, String chargeType);
}
