package ali.trabi.finalwork.backend.dao;

import ali.trabi.finalwork.backend.entity.Subscription;
import org.springframework.data.repository.CrudRepository;

public interface SubscriptionDAO extends CrudRepository<Subscription, Integer> {
}
