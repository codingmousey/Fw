package ali.trabi.finalwork.backend.dao;

import ali.trabi.finalwork.backend.entity.City;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CityDAO extends CrudRepository <City, Integer> {
}
