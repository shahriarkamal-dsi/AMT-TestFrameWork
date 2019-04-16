package test.model;

import javax.persistence.*;

@Entity
@Table(uniqueConstraints={
        @UniqueConstraint(columnNames = {"type", "data_id"})
})
public class PrequisiteData {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long preqId;
    @Column(name="type" , length = 10)
    private String type;
    @Column(name="data_id")
    private Long dataId;

    public PrequisiteData() {

    }

    public long getPreqId() {
        return preqId;
    }

    public void setPreqId(long preqId) {
        this.preqId = preqId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getDataId() {
        return dataId;
    }

    public void setDataId(Long dataId) {
        this.dataId = dataId;
    }
}
