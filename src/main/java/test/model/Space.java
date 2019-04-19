package test.model;

import javax.persistence.*;
import java.util.HashMap;
import java.util.Map;

@Entity
public class Space {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(unique = true)
    private String spaceName;
    @Column(name = "floor")
    private String floor;
    @Column(name = "property_name", length = 30)
    private String propertyName;
    @Column(name = "property_code", length = 30)
    private String propertyCode;
    @Column(name = "lease_name", length = 30)
    private String leaseName;
    @Column(name = "start_date")
    private String startDate;
    @Column(name = "end_date")
    private String endDate;


    public Space() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSpaceName() {
        return spaceName;
    }

    public void setSpaceName(String name) {
        spaceName = name;
    }

    public String getFloor() {
        return floor;
    }

    public void setFloor(String floor) {
        this.floor = floor;
    }

    public String getPropertyName() {
        return propertyName;
    }

    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }

    public String getPropertyCode() {
        return propertyCode;
    }

    public void setPropertyCode(String propertyCode) {
        this.propertyCode = propertyCode;
    }

    public String getLeaseName() {
        return leaseName;
    }

    public void setLeaseName(String leaseName) {
        this.leaseName = leaseName;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public Map<String,String > getSpaceMap(){

        Map<String,String> SpaceMap=new HashMap<>();

        SpaceMap.put("dataId",String.valueOf(id));
        SpaceMap.put("propertyName",propertyName);
        SpaceMap.put("propertyCode",propertyCode);
        SpaceMap.put("LeaseName",leaseName);
        SpaceMap.put("Space",spaceName);
        SpaceMap.put("Floor",floor);
        SpaceMap.put("startDate",startDate);
        SpaceMap.put("endDate",endDate);

        return SpaceMap;
    }

}
