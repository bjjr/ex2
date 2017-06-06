
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Event;
import domain.Sku;

@Repository
public interface SkuRepository extends JpaRepository<Sku, Integer> {

	@Query("select e from Sku s join s.event e where s.cancelled = false")
	Collection<Event> eventsWithSkuNotCancelled();

	@Query("select s from Sku s where s.event.id = ?1")
	Sku findOneByEvent(int eventId);

}
