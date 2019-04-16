package test.service;

import test.model.*;
import test.repository.PrequisiteDataRepo;
import test.repository.PropertyRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class PreqDataService {

    @Autowired
    private PrequisiteDataRepo prequisiteDataRepo;
    @Autowired
    PropertyService propertyService ;
    @Autowired
    LeaseService leaseService ;
    @Autowired
    SpaceService spaceService ;
    @Autowired
    RprService rprService ;

    public void createOrUpdatePrequisiteData(PrequisiteData prequisiteData) {
        prequisiteDataRepo.save(prequisiteData) ;
    }

    public void createPrequisiteData(String type,long id) {
        PrequisiteData prequisiteData = new PrequisiteData() ;
        prequisiteData.setType(type);
        prequisiteData.setDataId(id);
        prequisiteDataRepo.save(prequisiteData) ;
    }


    public PrequisiteData getPrequisiteData(Long id)
    {
        return prequisiteDataRepo.findById(id).orElse(new PrequisiteData()) ;
    }


    private Stream<PrequisiteData> getFilterDataByType(Stream<PrequisiteData> data, String type) {
        try {

            return  data.filter(preq -> preq.getType().equals(type) ) ;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null ;
        }
    }

    public List<Property> getPropertyRecords(Stream<PrequisiteData> data) {
        try {
            return  getFilterDataByType(data,"property").map(o ->
                    {
                        return propertyService.getProperty(o.getDataId());
                    }
            ).collect(Collectors.toList());


        } catch (Exception ex) {
            ex.printStackTrace();
            return new ArrayList<Property>() ;
        }
    }

    public List<Lease> getLeaseRecords(Stream<PrequisiteData> data) {
        try {
            return  getFilterDataByType(data,"lease").map(o ->
                    {
                        return leaseService.getLease(o.getDataId());
                    }
            ).collect(Collectors.toList());


        } catch (Exception ex) {
            ex.printStackTrace();
            return new ArrayList<Lease>() ;
        }
    }

    public List<Space> getSpaceRecords(Stream<PrequisiteData> data) {
        try {
            return  getFilterDataByType(data,"space").map(o ->
                    {
                        return spaceService.getSpace(o.getDataId());
                    }
            ).collect(Collectors.toList());


        } catch (Exception ex) {
            ex.printStackTrace();
            return new ArrayList<Space>() ;
        }
    }

    public List<Rpr> getRprRecords(Stream<PrequisiteData> data) {
        try {
            return  getFilterDataByType(data,"rpr").map(o ->
                    {
                        return rprService.getRpr(o.getDataId());
                    }
            ).collect(Collectors.toList());


        } catch (Exception ex) {
            ex.printStackTrace();
            return new ArrayList<Rpr>() ;
        }
    }


}
