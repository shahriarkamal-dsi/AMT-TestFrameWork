package test.model;

import javax.persistence.*;

@Entity
@Table(uniqueConstraints={
        @UniqueConstraint(columnNames = {"preq_id", "test_case_id"})
})
public class TestDataMap {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name="preq_id")
    private long preqId;

    @Column(name="test_case_id" , length = 20)
    private String testCaseId;

    public TestDataMap() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getPreqId() {
        return preqId;
    }

    public void setPreqId(long preqId) {
        this.preqId = preqId;
    }

    public String getTestCaseId() {
        return testCaseId;
    }

    public void setTestCaseId(String testCaseId) {
        this.testCaseId = testCaseId;
    }
}
