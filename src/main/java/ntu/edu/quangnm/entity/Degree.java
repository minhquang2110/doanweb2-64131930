package ntu.edu.quangnm.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "degrees")
public class Degree {

    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "name", nullable = false)
    private String name;

    // Getters & Setters
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}
