package test.repository;

import test.model.Lease;
import test.model.Property;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LeaseRepo extends JpaRepository<Lease,Long>{
     Optional<Lease> findOneByLeaseCode(String leaseCode);
}
