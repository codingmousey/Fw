package ali.trabi.finalwork.backend.dao;

import ali.trabi.finalwork.backend.entity.Company;
import org.springframework.data.repository.CrudRepository;

public interface CompanyDAO extends CrudRepository<Company, Integer> {
}
