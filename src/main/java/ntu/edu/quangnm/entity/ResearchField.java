package ntu.edu.quangnm.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "research_fields")
public class ResearchField {

    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "name", nullable = false)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_field_id")
    private ResearchField parentField;

    @Column(name = "description")
    private String description;

    @Column(name = "code", unique = true)
    private String code;

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
    public ResearchField getParentField() {
        return parentField;
    }
    public void setParentField(ResearchField parentField) {
        this.parentField = parentField;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }
}
