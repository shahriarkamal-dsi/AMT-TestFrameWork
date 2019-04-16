package test.model;

import javax.persistence.*;

@Entity
public class Space {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(unique = true)
    private String spaceName;
    private String floor;

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
        floor = floor;
    }
}
