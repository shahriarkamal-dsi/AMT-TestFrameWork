package test.service;

import test.model.Property;
import test.model.Rpr;
import test.repository.PropertyRepo;
import test.repository.RprRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RprService {

    @Autowired
    private RprRepo rprRepo ;
    @Autowired
    private PreqDataService preqDataService ;

    public void createOrUpdateRpr(Rpr rpr) {
        boolean isNew = rpr.getId() ==0 ? true : false ;
        rprRepo.save(rpr) ;
        if(isNew)
            preqDataService.createPrequisiteData("rpr",rpr.getId());
    }

    public Rpr getRpr(Long id) {
        return rprRepo.findById(id).orElse(null) ;
    }

}
