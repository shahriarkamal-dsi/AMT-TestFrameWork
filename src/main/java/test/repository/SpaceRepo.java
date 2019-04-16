package test.repository;

import test.model.Property;
import test.model.Space;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SpaceRepo extends JpaRepository<Space,Long>{
     Optional<Space> findOneBySpaceName(String spaceName);
}
