package ntu.edu.quangnm.dto;

import org.springframework.web.multipart.MultipartFile;

public class ScientistDTO {
    private Integer id; 
    
    private String fullName;
    
    private String email;
    
    private String gender;
    
    private Integer birthYear;
    
    private String address;
    
    private MultipartFile imageUrl;
    
    private String phone;
    
    private String major;
    
    private String subMajor;
    
    private String teachingSpecialty;
    
    private String degree;
    
    private String rank;
    
    private String title;
    
    private String researchField;
    
    private String organization;
    
    private String languageLevel;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public MultipartFile getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(MultipartFile imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getResearchField() {
        return researchField;
    }

    public void setResearchField(String researchField) {
        this.researchField = researchField;
    }

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public String getLanguageLevel() {
        return languageLevel;
    }

    public void setLanguageLevel(String languageLevel) {
        this.languageLevel = languageLevel;
    }
    
    @Override
    public String toString() {
        return "ScientistDTO{" +
                "fullName='" + fullName + '\'' +
                ", email='" + email + '\'' +
                ", gender='" + gender + '\'' +
                ", birthYear=" + birthYear +
                ", address='" + address + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", phone='" + phone + '\'' +
                ", major='" + major + '\'' +
                ", subMajor='" + subMajor + '\'' +
                ", teachingSpecialty='" + teachingSpecialty + '\'' +
                ", degree='" + degree + '\'' +
                ", rank='" + rank + '\'' +
                ", title='" + title + '\'' +
                ", researchField='" + researchField + '\'' +
                ", organization='" + organization + '\'' +
                ", languageLevel='" + languageLevel + '\'' +
                '}';
    }

}
