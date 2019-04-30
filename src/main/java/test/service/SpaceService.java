package test.service;

import test.model.Property;
import test.model.Space;
import test.repository.PropertyRepo;
import test.repository.SpaceRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SpaceService {

    @Autowired
    private SpaceRepo spaceRepo ;
    @Autowired
    private PreqDataService preqDataService ;
    public void createOrUpdateSpace(Space space) {
        boolean isNew = space.getId() ==0 ? true : false ;
        spaceRepo.save(space) ;
        if(isNew)
            preqDataService.createPrequisiteData("space",space.getId());
    }

    public Space getSpaceBySpaceName(String name) {
       return  spaceRepo.findOneBySpaceName(name).orElse(null);
    }

    public Space getSpace(Long id) {
        return spaceRepo.findById(id).orElse(null) ;
    }


}
