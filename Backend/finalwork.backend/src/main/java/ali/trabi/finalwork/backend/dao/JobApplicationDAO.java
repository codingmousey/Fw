package ali.trabi.finalwork.backend.dao;

import ali.trabi.finalwork.backend.entity.JobApplication;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface JobApplicationDAO extends CrudRepository<JobApplication, Integer> {
    List<JobApplication> getJobApplicationsByUserId(Integer userId);

    void deleteById(Integer id);
    List<JobApplication> findByJobListing_CompanyId(Integer companyId);
}
