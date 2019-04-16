package test.service;

import test.model.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class NotExecutedPreqData {
   private List<PrequisiteData> prequisiteData ;
  private  boolean isAnyPreqFailed =false ;
    private List<Property> propertyList = new ArrayList<>();
    private List<Lease> leaseList = new ArrayList<>();
    private List<Space> spaceList = new ArrayList<>() ;  ;
    private List<Rpr> rprList  = new ArrayList<>();

   public NotExecutedPreqData() {

    }

    public List<PrequisiteData> getPrequisiteData() {
        return prequisiteData;
    }

    public void setPrequisiteData(List<PrequisiteData> prequisiteData) {
        this.prequisiteData = prequisiteData;
    }

    public boolean isAnyPreqFailed() {
        return isAnyPreqFailed;
    }

    public void setAnyPreqFailed(boolean anyPreqFailed) {
        isAnyPreqFailed = anyPreqFailed;
    }

    public List<Property> getPropertyList() {
        return propertyList;
    }

    public void setPropertyList(List<Property> propertyList) {
        this.propertyList = propertyList;
    }

    public List<Lease> getLeaseList() {
        return leaseList;
    }

    public void setLeaseList(List<Lease> leaseList) {
        this.leaseList = leaseList;
    }

    public List<Space> getSpaceList() {
        return spaceList;
    }

    public void setSpaceList(List<Space> spaceList) {
        this.spaceList = spaceList;
    }

    public List<Rpr> getRprList() {
        return rprList;
    }

    public void setRprList(List<Rpr> rprList) {
        this.rprList = rprList;
    }
}
