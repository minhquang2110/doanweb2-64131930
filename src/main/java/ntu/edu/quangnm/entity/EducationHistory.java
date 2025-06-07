package ntu.edu.quangnm.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "education_history")
public class EducationHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    @Column(name = "id")
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "scientist_id")
    private Scientist scientist;

    @Column(name = "level")
    private String level;

    @Column(name = "institution")
    private String institution;

    @Column(name = "major")
    private String major;

    @Column(name = "graduation_year")
    private Integer graduationYear;

    // Getters & Setters
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public Scientist getScientist() {
        return scientist;
    }
    public void setScientist(Scientist scientist) {
        this.scientist = scientist;
    }
    public String getLevel() {
        return level;
    }
    public void setLevel(String level) {
        this.level = level;
    }
    public String getInstitution() {
        return institution;
    }
    public void setInstitution(String institution) {
        this.institution = institution;
    }
    public String getMajor() {
        return major;
    }
    public void setMajor(String major) {
        this.major = major;
    }
    public Integer getGraduationYear() {
        return graduationYear;
    }
    public void setGraduationYear(Integer graduationYear) {
        this.graduationYear = graduationYear;
    }
}

