package interview.assignment.selling.tshirts.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import interview.assignment.selling.tshirts.model.Tshirt;
import interview.assignment.selling.tshirts.model.User;
import interview.assignment.selling.tshirts.service.StockService;
import interview.assignment.selling.tshirts.service.TshirtService;

/*
 * Use @SessionAttributes("userObj") to use the User session attribute which was put into the session by the LoginController.
 */

@Controller
@SessionAttributes("userObj")
public class TshirtController {

	@Autowired
	TshirtService tshirtService;

	@Autowired
	StockService stockService;

	@RequestMapping(value = "/tshirts/list-manage-tshirts", method = RequestMethod.GET)
	public String presentTshirts(ModelMap model) {

		// Read user from the session
		User loggedInUser = (User) model.get("userObj");

		List<Tshirt> tshirtsList;
		if (loggedInUser.isAdmin()) {
			// See the all t-shirts
			tshirtsList = tshirtService.presentAllTshirts();
		} else {
			// See the all available t-shirts
			tshirtsList = tshirtService.presentAvailableTshirts();
		}

		// Add available t-shirts
		model.put("tshirtsList", tshirtsList);

		// Go to tshirts/manage-tshirts.jsp
		return "tshirts/list-manage-tshirts";
	}

	@RequestMapping(value = "tshirts/add-tshirt", method = RequestMethod.GET)
	public String showAddTshirtPage(ModelMap model) {

		// Add t-shirt att to the model
		model.addAttribute("tshirtAtt", new Tshirt("", "", "", "", ""));

		// Go to tshirts/add-update-tshirt.jsp
		return "tshirts/add-update-tshirt";
	}

	@RequestMapping(value = "tshirts/add-tshirt", method = RequestMethod.POST)
	public String addTshirt(ModelMap model, @Valid @ModelAttribute("tshirtAtt") Tshirt tshirtsAtt,
			BindingResult result) {

		if (result.hasErrors()) {
			// Go to tshirts/add-update-tshirt.jsp
			return "tshirts/add-update-tshirt";
		}

		// Read user from the session
		User loggedInUser = (User) model.get("userObj");

		// Add t-shirt
		tshirtService.addTshirt(loggedInUser, tshirtsAtt);

		// Resuest the page with GET
		return "redirect:/tshirts/list-manage-tshirts";
	}

	@RequestMapping(value = "tshirts/update-tshirt", method = RequestMethod.GET)
	public String showUpdateTshirtPage(ModelMap model, @RequestParam String tshirtId) {

		Tshirt tshirtsAtt = tshirtService.findTshirtByTshirtId(tshirtId);

		// Add t-shirt att to the model
		model.addAttribute("tshirtAtt", tshirtsAtt);

		// Go to tshirts/add-update-tshirt.jsp
		return "tshirts/add-update-tshirt";

	}

	@RequestMapping(value = "tshirts/update-tshirt", method = RequestMethod.POST)
	public String updateTshirt(ModelMap model, @Valid @ModelAttribute("tshirtAtt") Tshirt tshirtsAtt,
			BindingResult result) {

		if (result.hasErrors()) {
			// Go to tshirts/add-update-tshirt.jsp
			return "tshirts/add-update-tshirt";
		}

		// Read user from the session
		User loggedInUser = (User) model.get("userObj");

		// Updte t-shirt
		tshirtService.updateTshirt(loggedInUser, tshirtsAtt);

		// Resuest the page with GET
		return "redirect:/tshirts/list-manage-tshirts";
	}

	@RequestMapping(value = "tshirts/delete-tshirt", method = RequestMethod.GET)
	public String deleteTshirt(ModelMap model, @RequestParam String tshirtId) {

		// Read user from the session
		User loggedInUser = (User) model.get("userObj");

		// Delete t-shirt
		tshirtService.removeTshirt(loggedInUser, tshirtId);

		// Resuest the page with GET
		return "redirect:/tshirts/list-manage-tshirts";
	}

	@RequestMapping(value = "tshirts/update-stock-level-for-tshirt", method = RequestMethod.GET)
	public String showUpdateStockLevelForTshirt(ModelMap model, @RequestParam String tshirtId) {

		Tshirt tshirtsAtt = tshirtService.findTshirtByTshirtId(tshirtId);

		// Add t-shirt att to the model
		model.addAttribute("tshirtAtt", tshirtsAtt);

		// Use this to active the stock input
		model.addAttribute("isUpdateStockLevelForTshirt", "true");

		// Go to tshirts/add-update-tshirt.jsp
		return "tshirts/add-update-tshirt";

	}

	@RequestMapping(value = "tshirts/update-stock-level-for-tshirt", method = RequestMethod.POST)
	public String updateStockLevelForTshirt(ModelMap model, @Valid @ModelAttribute("tshirtAtt") Tshirt tshirtsAtt,
			BindingResult result) {

		if (result.hasErrors()) {
			// Go to tshirts/add-update-tshirt.jsp
			return "tshirts/add-update-tshirt";
		}

		// Read user from the session
		User loggedInUser = (User) model.get("userObj");

		// Updte stock level for the t-shirt
		stockService.updateStockLevelForTheTshirtInTheStock(loggedInUser, tshirtsAtt.getId(),
				tshirtsAtt.getStockLevelForTheTshirt());

		// Resuest the page with GET
		return "redirect:/tshirts/list-manage-tshirts";
	}

}
