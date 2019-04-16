package test.model;

import javax.persistence.*;

@Entity
public class Lease {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(unique = true)
    private String leaseNmae;
    @Column(unique = true)
    private String leaseCode;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLeaseNmae() {
        return leaseNmae;
    }

    public void setLeaseNmae(String leaseNmae) {
        this.leaseNmae = leaseNmae;
    }

    public String getLeaseCode() {
        return leaseCode;
    }

    public void setLeaseCode(String leaseCode) {
        this.leaseCode = leaseCode;
    }
}
