package ali.trabi.finalwork.backend.dao;

import ali.trabi.finalwork.backend.entity.JobListing;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface JobListingDAO extends CrudRepository<JobListing, Integer> {

    @Query("SELECT j.programmingLanguages FROM JobListing j WHERE j.companyId = :companyId")
    List<String> findProgrammingLanguagesByCompanyId(@Param("companyId") Integer companyId);

    JobListing getJobListingById(Integer jobListingId);
    List<JobListing> getAllJobListingsByCompanyId(Integer companyId);
}
