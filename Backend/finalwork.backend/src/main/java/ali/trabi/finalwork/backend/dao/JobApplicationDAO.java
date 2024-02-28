package ali.trabi.finalwork.backend.dao;

import ali.trabi.finalwork.backend.entity.JobApplication;
import org.springframework.data.repository.CrudRepository;

public interface JobApplicationDAO extends CrudRepository<JobApplication, Integer> {
}
