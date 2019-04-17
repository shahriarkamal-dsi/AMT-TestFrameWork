package test.model;

import javax.persistence.*;
import java.util.HashMap;
import java.util.Map;

@Entity
public class Property {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(unique = true)
    private String propertyName;
    @Column(unique = true)
    private String propertyCode;

    @Column(name = "address", length = 30)
    private String address;
    @Column(name = "country", length = 30)
    private String country;
    @Column(name = "state", length = 30)
    private String state;
    @Column(name = "postal", length = 30)
    private String postal;
    @Column(name = "city", length = 30)
    private String city;
    @Column(name = "code_type", length = 30)
    private String codeType;
    @Column(name = "status", length = 30)
    private String status;
    @Column(name = "currency", length = 30)
    private String currency;
    @Column(name = "chart_type", length = 30)
    private String chartType;
    @Column(name = "sqFt_rentable", length = 30)
    private String sqFtRentable;
    @Column(name = "property_group1", length = 30)
    private String propertyGroup1;
    @Column(name = "property_group2", length = 30)
    private String propertyGroup2;
    @Column(name = "property_group3", length = 30)
    private String propertyGroup3;
    @Column(name = "auto_manage")
    private String autoManage;



    public Property() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getpropertyName() {
        return propertyName;
    }

    public void setpropertyName(String propertyNmae) {
        this.propertyName = propertyNmae;
    }

    public String getPropertyCode() {
        return propertyCode;
    }

    public void setPropertyCode(String propertyCode) {
        this.propertyCode = propertyCode;
    }


    public String getPostal() {
        return postal;
    }

    public void setPostal(String postal) {
        this.postal = postal;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address1) {
        this.address = address1;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCodeType() {
        return codeType;
    }

    public void setCodeType(String codeType) {
        this.codeType = codeType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getChartType() {
        return chartType;
    }

    public void setChartType(String chartType) {
        this.chartType = chartType;
    }

    public String getPropertyGroup1() {
        return propertyGroup1;
    }

    public void setPropertyGroup1(String propertyGroup1) {
        this.propertyGroup1 = propertyGroup1;
    }

    public String getPropertyGroup2() {
        return propertyGroup2;
    }

    public void setPropertyGroup2(String propertyGroup2) {
        this.propertyGroup2 = propertyGroup2;
    }

    public String getPropertyGroup3() {
        return propertyGroup3;
    }

    public void setPropertyGroup3(String propertyGroup3) {
        this.propertyGroup3 = propertyGroup3;
    }

    public String getAutoManage() {
        return autoManage;
    }

    public void setAutoManage(String autoManage) {
        this.autoManage = autoManage;
    }

    public String getSqFtRentable() {
        return sqFtRentable;
    }

    public void setSqFtRentable(String sqFtRentable) {
        this.sqFtRentable = sqFtRentable;
    }
    public Map<String,String > getPropertyMap(){

        Map<String,String> propertyMap=new HashMap<>();

        propertyMap.put("propertyName",propertyName);
        propertyMap.put("propertyCode",propertyCode);
        propertyMap.put("address1",address);
        propertyMap.put("country",country);
        propertyMap.put("state",state);
        propertyMap.put("postal",postal);
        propertyMap.put("city",city);
        propertyMap.put("codeType",codeType);
        propertyMap.put("status",status);
        propertyMap.put("currency",currency);
        propertyMap.put("chartType",chartType);
        propertyMap.put("sqFtRentable",sqFtRentable);
        propertyMap.put("propertyGroup1",propertyGroup1);
        propertyMap.put("propertyGroup2",propertyGroup2);
        propertyMap.put("propertyGroup3",propertyGroup3);
        propertyMap.put("autoManage",autoManage);

        return propertyMap;
    }
}
