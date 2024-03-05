package ali.trabi.finalwork.backend.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class JobApplication {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    // https://stackoverflow.com/questions/69396793/how-to-use-jsonignore-in-spring-boot-to-stop-infinite-loop

    private User user;

    @ManyToOne
    @JoinColumn(name = "job_listing_id")
    private JobListing jobListing;

    private LocalDateTime applicationDate;

    public JobApplication() {
    }

    public JobApplication(Integer id, User user, JobListing jobListing, LocalDateTime applicationDate) {
        this.id = id;
        this.user = user;
        this.jobListing = jobListing;
        this.applicationDate = applicationDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public JobListing getJobListing() {
        return jobListing;
    }

    public void setJobListing(JobListing jobListing) {
        this.jobListing = jobListing;
    }

    public LocalDateTime getApplicationDate() {
        return applicationDate;
    }

    public void setApplicationDate(LocalDateTime applicationDate) {
        this.applicationDate = applicationDate;
    }
}
