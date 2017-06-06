
package services;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.DataBinder;

import utilities.AbstractTest;
import domain.Sku;

@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class SkuServiceTest extends AbstractTest {

	// System under test ----------------------------

	@Autowired
	private SkuService	skuService;


	// Services -------------------------------------

	// Tests ----------------------------------------

	/*
	 * TODO INSERTAR ID DE EVENTO
	 */

	@Test
	public void createDriver() {
		final Object testingData[][] = {
			{ // Successful test
				"admin", 0, null
			}, { // User tries to create an Sku
				"user1", 0, IllegalArgumentException.class
			}
		};

		for (int i = 0; i < testingData.length; i++)
			this.createTemplate((String) testingData[i][0], (int) testingData[i][1], (Class<?>) testingData[i][1]);
	}

	// Templates ------------------------------------

	protected void createTemplate(final String username, final int eventId, final Class<?> expected) {
		Class<?> caught;

		caught = null;

		try {
			this.authenticate(username);

			Sku sku, reconstructed;
			final Sku saved;
			DataBinder dataBinder;
			BindingResult binding;

			// Simulating form

			sku = this.skuService.create(eventId);
			//TODO

			// Creating a binding

			dataBinder = new DataBinder(sku, "sku");
			binding = dataBinder.getBindingResult();

			reconstructed = this.skuService.reconstruct(sku, false, binding);
			Assert.isTrue(!binding.hasErrors());

			saved = this.skuService.save(reconstructed);
			this.skuService.flush();

			Assert.isTrue(saved.getId() != 0);

		} catch (final Throwable th) {
			caught = th.getClass();
		}

		this.checkExceptions(expected, caught);

	}

}
