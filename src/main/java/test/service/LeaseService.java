package test.service;

import test.model.Lease;
import test.model.Property;
import test.repository.LeaseRepo;
import test.repository.PropertyRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LeaseService {

    @Autowired
    private LeaseRepo leaseRepo;
    @Autowired
    private PreqDataService preqDataService ;

    public void createOrUpdateLease(Lease lease) {
        boolean isNew = lease.getId() == 0 ? true : false ;
        leaseRepo.save(lease) ;
        if(isNew)
            preqDataService.createPrequisiteData("lease",lease.getId());
    }

    public Lease getLeaseByLeaseCode(String code) {
       return   leaseRepo.findOneByLeaseCode(code).orElse(null);
    }

    public Lease getLease(Long id) {
        return leaseRepo.findById(id).orElse(null) ;
    }


}
