
package controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.SkuService;
import domain.Sku;

@Controller
@RequestMapping("/sku")
public class SkuController extends AbstractController {

	// Services ------------------------------------

	@Autowired
	private SkuService	skuService;


	// Display --------------------------------------

	@RequestMapping(value = "/display", method = RequestMethod.GET)
	public ModelAndView display(@RequestParam(required = true) final int eventId) {
		ModelAndView res;
		Sku sku;

		sku = this.skuService.findOneByEvent(eventId);
		res = new ModelAndView("sku/display");

		res.addObject("sku", sku);

		return res;
	}

}
