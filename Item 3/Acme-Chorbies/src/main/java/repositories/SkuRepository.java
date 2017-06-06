
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Event;
import domain.Sku;

@Repository
public interface SkuRepository extends JpaRepository<Sku, Integer> {

	@Query("select s.event from Sku s where s.cancelled = false")
	Collection<Event> findEventsWithSkuNotCancelled();

	@Query("select s from Sku s where s.event.id = ?1")
	Sku findOneByEvent(int eventId);

	@Query("select s from Sku s where s.administrator.id = ?1")
	Collection<Sku> findSkusByAdministrator(int administratorId);

	@Query("select s.event from Sku s")
	Collection<Event> findEventsWithSku();
}
