package test.model;

import javax.persistence.*;
import java.util.HashMap;
import java.util.Map;

@Entity
public class Lease {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(unique = true)
    private String leaseName;
    @Column(unique = true)
    private String leaseCode;

    @Column(name = "property_name", length = 30)
    private String propertyName;
    @Column(name = "property_code", length = 30)
    private String propertyCode;
    @Column(name = "lease_status", length = 30)
    private String leaseStatus;
    @Column(name = "lease_type", length = 30)
    private String leaseType;
    @Column(name = "billing_type", length = 30)
    private String billingType;
    @Column(name = "begin_date")
    private String beginDate;
    @Column(name = "expiration_date")
    private String expirationDate;
    @Column(name = "lease_group1", length = 30)
    private String leaseGroup1;
    @Column(name = "code_type", length = 30)
    private String codeType;
    @Column(name = "contract_type", length = 30)
    private String contractType;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLeaseName() {
        return leaseName;
    }

    public void setLeaseName(String leaseNmae) {
        this.leaseName = leaseNmae;
    }

    public String getLeaseCode() {
        return leaseCode;
    }

    public void setLeaseCode(String leaseCode) {
        this.leaseCode = leaseCode;
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

    public String getLeaseStatus() {
        return leaseStatus;
    }

    public void setLeaseStatus(String leaseStatus) {
        this.leaseStatus = leaseStatus;
    }

    public String getLeaseType() {
        return leaseType;
    }

    public void setLeaseType(String leaseType) {
        this.leaseType = leaseType;
    }

    public String getBillingType() {
        return billingType;
    }

    public void setBillingType(String billingType) {
        this.billingType = billingType;
    }

    public String getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(String beginDate) {
        this.beginDate = beginDate;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getLeaseGroup1() {
        return leaseGroup1;
    }

    public void setLeaseGroup1(String leaseGroup1) {
        this.leaseGroup1 = leaseGroup1;
    }

    public String getCodeType() {
        return codeType;
    }

    public void setCodeType(String codeType) {
        this.codeType = codeType;
    }

    public String getContractType() {
        return contractType;
    }

    public void setContractType(String contractType) {
        this.contractType = contractType;
    }

    public Map<String, String> getLeaseMap(){

        Map<String,String> leaseMap = new HashMap<String, String>();

        leaseMap.put("propertyName",propertyName);
        leaseMap.put("propertyCode",propertyCode);
        leaseMap.put("dbaName",leaseName);
        leaseMap.put("leaseCode",leaseCode);
        leaseMap.put("leaseStatus",leaseStatus);
        leaseMap.put("leaseType",leaseType);
        leaseMap.put("billingType",billingType);
        leaseMap.put("beginDate",beginDate);
        leaseMap.put("expirationDate",expirationDate);
        leaseMap.put("leaseGroup1",leaseGroup1);
        leaseMap.put("codeType",codeType);
        leaseMap.put("contractType",contractType);

        return  leaseMap;
    }
}
