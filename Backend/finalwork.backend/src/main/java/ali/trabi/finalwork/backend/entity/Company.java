package ali.trabi.finalwork.backend.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    String name;
    Mode mode;
    @OneToMany(mappedBy = "company")
    private List<JobListing> jobListings;

    public Company() {
    }

    public Company(Integer id, String name, Mode mode) {
        this.id = id;
        this.name = name;
        this.mode = mode;
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

    public Mode getMode() {
        return mode;
    }

    public void setMode(Mode mode) {
        this.mode = mode;
    }
}