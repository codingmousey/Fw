package ali.trabi.finalwork.backend.dao;

import ali.trabi.finalwork.backend.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface UserDAO extends CrudRepository<User, Integer> {
    User getUserByUsername(String username);
}
