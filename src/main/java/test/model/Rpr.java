package test.model;

import javax.persistence.*;
import java.util.HashMap;
import java.util.Map;

@Entity
@Table(uniqueConstraints={
        @UniqueConstraint(columnNames = {"space_name", "charge_type"})
})
public class Rpr {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(name="space_name" , length = 30)
    private String spaceName;
    @Column(name="charge_type" , length = 40)
    private String chargeType ;
    @Column(name="charge_name" , length = 30)
    private String chargeName ;
    @Column(name="lease_name" , length = 30)
    private String leaseName ;
    @Column(name="frequency" , length = 30)
    private String frequency ;
    @Column(name="escalation_type" , length = 30)
    private String escalationType ;
    @Column(name="lease_term_year" , length = 30)
    private String leaseTermYear ;
    @Column(name="lease_term_defined" , length = 30)
    private String leaseTermDefined ;
    @Column(name="effective_date" , length = 30)
    private String effectiveDate ;
    @Column(name="end_date" , length = 30)
    private String endDate ;
    @Column(name="amount" , length = 30)
    private String amount ;

    @Column(name="fiscal_year" , length = 30)
    private String fiscalYear ;

    @Column(name="base_amount" , length = 30)
    private String baseAmount ;

    @Column(name="percent_increase" , length = 30)
    private String percentIncrease ;

    @Column(name="cal_frequency" , length = 30)
    private String calFrequency ;

    @Column(name="rental_activity" , length = 30)
    private String rentalActivity ;

    public Rpr() {
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

    public void setSpaceName(String spaceName) {
        this.spaceName = spaceName;
    }

    public String getChargeType() {
        return chargeType;
    }

    public void setChargeType(String chargeType) {
        this.chargeType = chargeType;
    }

    public String getChargeName() { return chargeName; }

    public void setChargeName(String chargeName) { this.chargeName = chargeName; }

    public String getFrequency() { return frequency; }

    public void setFrequency(String frequency) { this.frequency = frequency; }

    public String getEscalationType() { return escalationType; }

    public void setEscalationType(String escalationType) { this.escalationType = escalationType; }

    public String getLeaseName() { return leaseName; }

    public void setLeaseName(String leaseName) { this.leaseName = leaseName; }

    public String getLeaseTermYear() { return leaseTermYear; }

    public void setLeaseTermYear(String leaseTermYear) { this.leaseTermYear = leaseTermYear; }

    public String getLeaseTermDefined() { return leaseTermDefined; }

    public void setLeaseTermDefined(String leaseTermDefined) { this.leaseTermDefined = leaseTermDefined; }

    public String getEffectiveDate() { return effectiveDate; }

    public void setEffectiveDate(String effectiveDate) { this.effectiveDate = effectiveDate; }

    public String getEndDate() { return endDate; }

    public void setEndDate(String endDate) { this.endDate = endDate; }

    public String getAmount() { return amount; }

    public void setAmount(String amount) { this.amount = amount; }
    public String getFiscalYear() {
        return fiscalYear;
    }

    public void setFiscalYear(String fiscalYear) {
        this.fiscalYear = fiscalYear;
    }

    public Map<String,String> getRprMap(){
        Map<String,String> rprMap=new HashMap<String, String>();
        rprMap.put("dataId",Long.toString(id));
        rprMap.put("LeaseName",leaseName);
        rprMap.put("spaceInfo",spaceName);
        rprMap.put("chargeType",chargeType);
        rprMap.put("chargeName",chargeName);
        rprMap.put("frequency",frequency);
        rprMap.put("escalationType",escalationType);
        rprMap.put("leaseTermYear",leaseTermYear);
        rprMap.put("leaseTermDefined",leaseTermDefined);
        rprMap.put("effDate",effectiveDate);
        rprMap.put("endDate",endDate);
        rprMap.put("amount",amount);
        rprMap.put("fiscalYear",fiscalYear);
        rprMap.put("baseAmount",baseAmount);
        rprMap.put("percentIncrease",percentIncrease);
        rprMap.put("calFrequency",calFrequency);
        rprMap.put("rentalActivity",rentalActivity);
        return rprMap;
    }


    public String getBaseAmount() {
        return baseAmount;
    }

    public void setBaseAmount(String baseAmount) {
        this.baseAmount = baseAmount;
    }

    public String getPercentIncrease() {
        return percentIncrease;
    }

    public void setPercentIncrease(String percentIncrease) {
        this.percentIncrease = percentIncrease;
    }

    public String getCalFrequency() {
        return calFrequency;
    }

    public void setCalFrequency(String calFrequency) {
        this.calFrequency = calFrequency;
    }

    public String getRentalActivity() {
        return rentalActivity;
    }

    public void setRentalActivity(String rentalActivity) {
        this.rentalActivity = rentalActivity;
    }
}