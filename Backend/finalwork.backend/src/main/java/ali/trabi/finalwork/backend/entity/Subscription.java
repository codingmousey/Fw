package ali.trabi.finalwork.backend.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Subscription {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "job_listing_id")
    private JobListing jobListing;

    private LocalDateTime subscriptionDate;

    public Subscription() {
    }

    public Subscription(Integer id, User user, JobListing jobListing, LocalDateTime subscriptionDate) {
        this.id = id;
        this.user = user;
        this.jobListing = jobListing;
        this.subscriptionDate = subscriptionDate;
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

    public LocalDateTime getSubscriptionDate() {
        return subscriptionDate;
    }

    public void setSubscriptionDate(LocalDateTime subscriptionDate) {
        this.subscriptionDate = subscriptionDate;
    }
}
