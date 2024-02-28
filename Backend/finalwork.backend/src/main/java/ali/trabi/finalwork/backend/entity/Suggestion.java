package ali.trabi.finalwork.backend.entity;

import jakarta.persistence.*;
import jakarta.persistence.Entity;

@Entity
public class Suggestion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "suggested_job_listing_id")
    private JobListing suggestedJobListing;

    public Suggestion() {
    }

    public Suggestion(Integer id, User user, JobListing suggestedJobListing) {
        this.id = id;
        this.user = user;
        this.suggestedJobListing = suggestedJobListing;
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

    public JobListing getSuggestedJobListing() {
        return suggestedJobListing;
    }

    public void setSuggestedJobListing(JobListing suggestedJobListing) {
        this.suggestedJobListing = suggestedJobListing;
    }
}
