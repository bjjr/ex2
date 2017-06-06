
package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;

import repositories.SkuRepository;
import domain.Event;
import domain.Sku;

@Service
@Transactional
public class SkuService {

	// Managed repository ---------------------------

	@Autowired
	private SkuRepository			skuRepository;

	// Supporting services --------------------------

	@Autowired
	private ActorService			actorService;

	@Autowired
	private AdministratorService	administratorService;

	@Autowired
	private EventService			eventService;

	// Validator -----------------------------------

	@Autowired
	private Validator				validator;


	// Constructor ---------------------------------

	public SkuService() {
		super();
	}

	// Simple CRUD methods --------------------------

	public Sku create() {
		Assert.isTrue(this.actorService.checkAuthority("ADMIN"));

		final Sku sku = new Sku();

		return sku;
	}

	public Sku save(final Sku sku) {
		Assert.notNull(sku);

		Sku res;

		res = this.skuRepository.save(sku);

		return res;
	}

	public Sku reconstruct(final Sku sku, final boolean isCancelled, final BindingResult bindingResult) {
		//TODO
		return null;
	}

	public Collection<Sku> findAll() {
		Collection<Sku> skus;

		skus = this.skuRepository.findAll();
		Assert.notNull(skus);

		return skus;
	}

	public Sku findOne(final int skuId) {
		Sku sku;

		sku = this.skuRepository.findOne(skuId);
		Assert.notNull(sku);

		return sku;
	}

	public void flush() {
		this.skuRepository.flush();
	}

	// Other business methods -----------------------

	public Collection<Event> eventsWithSkuNotCancelled() {
		Collection<Event> events;

		events = this.skuRepository.eventsWithSkuNotCancelled();
		Assert.notNull(events);

		return events;
	}

	public Sku findOneByEvent(final int eventId) {
		Sku sku;

		sku = this.skuRepository.findOneByEvent(eventId);

		return sku;
	}

	private String generateIdCode() {
		final List<String> allUniqueIDs = new ArrayList<>();

		for (final Sku s : this.findAll())
			allUniqueIDs.add(s.getLabel());

		String uniqueID = null;

		do {
			final String uniqueString = RandomStringUtils.randomAlphabetic(5).toUpperCase();
			final String uniqueNumber = RandomStringUtils.randomNumeric(5);

			uniqueID = uniqueString + "-" + uniqueNumber;
		} while (allUniqueIDs.contains(uniqueID));

		return uniqueID;
	}

}
