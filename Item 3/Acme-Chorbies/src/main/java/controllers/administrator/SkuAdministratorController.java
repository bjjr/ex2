
package controllers.administrator;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.SkuService;
import controllers.AbstractController;
import domain.Sku;

@Controller
@RequestMapping("sku/administrator")
public class SkuAdministratorController extends AbstractController {

	// Services -------------------------------------

	@Autowired
	private SkuService	skuService;


	// List -----------------------------------------

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView res;
		Collection<Sku> skus, adminSkus;

		res = new ModelAndView("sku/list");
		skus = this.skuService.findAll();
		adminSkus = this.skuService.findSkusByAdministrator();

		res.addObject("skus", skus);
		res.addObject("adminSkus", adminSkus);

		return res;
	}

	// Create ---------------------------------------

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create(@RequestParam(required = true) final int eventId) {
		ModelAndView res;
		Sku sku;

		sku = this.skuService.create(eventId);
		res = this.createEditModelAndView(sku, false);

		return res;
	}

	// Save -----------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(final Sku sku, final BindingResult binding) {
		ModelAndView res;
		Sku reconstructed;

		reconstructed = this.skuService.reconstruct(sku, false, binding);

		if (binding.hasErrors())
			res = this.createEditModelAndView(sku, false);
		else
			try {
				this.skuService.save(reconstructed);
				res = new ModelAndView("redirect:/sku/administrator/list.do");
			} catch (final Throwable th) {
				res = this.createEditModelAndView(sku, "misc.commit.error", false);
			}

		return res;
	}

	// Cancel sku -----------------------------------

	@RequestMapping(value = "/cancel", method = RequestMethod.GET)
	public ModelAndView cancel(@RequestParam(required = true) final int skuId) {
		ModelAndView res;
		Sku sku;

		sku = this.skuService.findOne(skuId);
		res = this.createEditModelAndView(sku, true);

		return res;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "cancellation")
	public ModelAndView cancel(final Sku sku, final BindingResult binding) {
		ModelAndView res;
		Sku reconstructed;

		reconstructed = this.skuService.reconstruct(sku, true, binding);

		if (binding.hasErrors())
			res = this.createEditModelAndView(sku, true);
		else
			try {
				this.skuService.save(reconstructed);
				res = new ModelAndView("redirect:/sku/administrator/list.do");
			} catch (final Throwable th) {
				res = this.createEditModelAndView(sku, "misc.commit.error", true);
			}

		return res;
	}

	// Ancillary methods ----------------------------
	protected ModelAndView createEditModelAndView(final Sku sku, final Boolean isCancel) {
		ModelAndView result;

		result = this.createEditModelAndView(sku, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(final Sku sku, final String message, final Boolean isCancel) {
		ModelAndView result;

		if (isCancel)
			result = new ModelAndView("sku/cancel");
		else
			result = new ModelAndView("sku/edit");

		result.addObject("sku", sku);
		result.addObject("message", message);
		result.addObject("isCancel", isCancel);

		return result;
	}
}
