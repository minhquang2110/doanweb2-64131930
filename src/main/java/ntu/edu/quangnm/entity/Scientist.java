package ntu.edu.quangnm.entity;

import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name = "scientists")
public class Scientist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  
    @Column(name = "id")
    private Integer id;  

    @OneToOne(cascade = CascadeType.ALL) 
    @JoinColumn(name = "account_id")
    private Account account;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "gender")
    private String gender;

    @Column(name = "birth_year")
    private Integer birthYear;

    @Column(name = "image")
    private String image;

    @Column(name = "address")
    private String address;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "email")
    private String email;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "degree_id")
    private Degree degree;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "rank_id")
    private Rank rank;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "title_id")
    private Title title;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "field_id")
    private ResearchField researchField;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "organization_id")
    private Organization organization;


    @Column(name = "major")
    private String major;

    @Column(name = "sub_major")
    private String subMajor;

    @Column(name = "teaching_specialty")
    private String teachingSpecialty;
    


    @OneToMany(mappedBy = "scientist", fetch = FetchType.EAGER)
    private List<EducationHistory> educationHistories;
    
    // Getters & Setters
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public Account getAccount() {
        return account;
    }
    public void setAccount(Account account) {
        this.account = account;
    }
    public String getFullName() {
        return fullName;
    }
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
    public String getGender() {
        return gender;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }
    public Integer getBirthYear() {
        return birthYear;
    }
    public void setBirthYear(Integer birthYear) {
        this.birthYear = birthYear;
    }
    public String getImage() {
        return image;
    }
    public void setImage(String image) {
        this.image = image;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public Degree getDegree() {
        return degree;
    }
    public void setDegree(Degree degree) {
        this.degree = degree;
    }
    public Rank getRank() {
        return rank;
    }
    public void setRank(Rank rank) {
        this.rank = rank;
    }
    public Title getTitle() {
        return title;
    }
    public void setTitle(Title title) {
        this.title = title;
    }
    public ResearchField getResearchField() {
        return researchField;
    }
    public void setResearchField(ResearchField researchField) {
        this.researchField = researchField;
    }
    public Organization getOrganization() {
        return organization;
    }
    public void setOrganization(Organization organization) {
        this.organization = organization;
    }
    public String getMajor() {
        return major;
    }
    public void setMajor(String major) {
        this.major = major;
    }
    public String getSubMajor() {
        return subMajor;
    }
    public void setSubMajor(String subMajor) {
        this.subMajor = subMajor;
    }
    public String getTeachingSpecialty() {
        return teachingSpecialty;
    }
    public void setTeachingSpecialty(String teachingSpecialty) {
        this.teachingSpecialty = teachingSpecialty;
    }
    
   	public List<EducationHistory> getEducationHistories() {
   		return educationHistories;
   	}
   	public void setEducationHistories(List<EducationHistory> educationHistories) {
   		this.educationHistories = educationHistories;
   	}
}

