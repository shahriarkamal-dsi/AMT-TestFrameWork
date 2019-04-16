package test.model;

import javax.persistence.*;

@Entity
public class Property {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(unique = true)
    private String propertyNmae;
    @Column(unique = true)
    private String propertyCode;

    public Property() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPropertyNmae() {
        return propertyNmae;
    }

    public void setPropertyNmae(String propertyNmae) {
        this.propertyNmae = propertyNmae;
    }

    public String getPropertyCode() {
        return propertyCode;
    }

    public void setPropertyCode(String propertyCode) {
        this.propertyCode = propertyCode;
    }
}
