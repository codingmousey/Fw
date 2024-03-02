package ali.trabi.finalwork.backend.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
public class JobListing {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private String description;
    @ElementCollection
    private List<String> programmingLanguages;
    private String status;
    private String country;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;

    private Integer companyId;

    private String city;

    @OneToMany(mappedBy = "jobListing")
    private List<Subscription> subscriptions;

    @OneToMany(mappedBy = "jobListing")
    private List<JobApplication> jobApplications;

    @OneToMany(mappedBy = "suggestedJobListing")
    private List<Suggestion> suggestions;

    public JobListing() {
    }

    public JobListing(Integer id, String name, String description, List<String> programmingLanguages, String status, String country, LocalDateTime createdDate, LocalDateTime updatedDate, Integer companyId, String city) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.programmingLanguages = programmingLanguages;
        this.status = status;
        this.country = country;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
        this.companyId = companyId;
        this.city = city;
    }

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getProgrammingLanguages() {
        return programmingLanguages;
    }

    public void setProgrammingLanguages(List<String> programmingLanguages) {
        this.programmingLanguages = programmingLanguages;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public LocalDateTime getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(LocalDateTime updatedDate) {
        this.updatedDate = updatedDate;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer company) {
        this.companyId = company;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
    @Override
    public String toString() {
        return "JobListing{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", programmingLanguages=" + programmingLanguages +
                ", companyId=" + companyId +
                ", city=" + city +
                ", country='" + country + '\'' +
                ", createdDate=" + createdDate +
                ", updatedDate=" + updatedDate +
                '}';
    }
}
