package interview.assignment.selling.tshirts.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import interview.assignment.selling.tshirts.model.Tshirt;
import interview.assignment.selling.tshirts.model.Order;
import interview.assignment.selling.tshirts.model.User;
import interview.assignment.selling.tshirts.service.OrderService;
import interview.assignment.selling.tshirts.service.StockService;
import interview.assignment.selling.tshirts.service.TshirtService;

/*
 * Use @SessionAttributes("userObj") to use the User session attribute which was put into the session by the LoginController.
 */

@Controller
@SessionAttributes("userObj")
public class OrderController {

	@Autowired
	OrderService orderService;

	@Autowired
	StockService stockService;

	@Autowired
	TshirtService tshirtService;

	/*
	 * Used @InitBinder to bind the orderDate attribute
	 */
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		/*
		 * Use the same date format with the java script date picker get initialized in
		 * the place-order-for-tshirt.jsp.
		 */

		// The date format muct be same with the date picker format.
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, false));

	}

	@RequestMapping(value = "/orders/list-place-orders", method = RequestMethod.GET)
	public String showAvailableTshirtsListToPlaceOrder(ModelMap model) {

		// Read user from the session
		User loggedInUser = (User) model.get("userObj");

		// Present available t-shirts to place order
		bussiness_presentAvailableThsirtsToPlaceOrderPutListIntoModel(model, loggedInUser.getId());

		/*
		 * Search for a view named list-place-orders (aka list-place-orders.jsp)
		 */
		return "orders/list-place-orders";
	}

	private void bussiness_presentAvailableThsirtsToPlaceOrderPutListIntoModel(ModelMap model, String userId) {

		// See the all available t-shirts
		List<Tshirt> tshirtsList = tshirtService.presentAvailableTshirts();

		List<Order> ordersForTshirtsList = new ArrayList<Order>();
		for (Tshirt tshirt : tshirtsList) {

			Order order = new Order(userId, tshirt.getId(), 0, new Date());
			order.setTshirt(tshirt);

			ordersForTshirtsList.add(order);
		}

		// Add available t-shirts
		model.put("ordersForTshirtsList", ordersForTshirtsList);

		List<Order> previouslyMadeOrdersList = orderService.presentOrdersMadeByTheUserSortedChronologically(userId);

		// Add previously made orders
		model.put("previouslyMadeOrdersList", previouslyMadeOrdersList);

	}

	@RequestMapping(value = "/orders/place-order-for-tshirt", method = RequestMethod.GET)
	public String showPlaceOrderForTshirtPage(ModelMap model, @RequestParam String userId,
			@RequestParam String tshirtId) {

		// Adding new model attributes
		Order orderAtt = new Order(userId, tshirtId, 0, new Date());

		// Find t-shirt
		Tshirt tshirt = tshirtService.findTshirtByTshirtId(tshirtId);
		orderAtt.setTshirt(tshirt);

		model.addAttribute("orderAtt", orderAtt);

		// Go to the place-order-for-tshirt.jsp
		return "orders/place-order-for-tshirt";
	}

	/*
	 * By using @Valid I'm enabling validation in Order obj. To see the validation
	 * results use the BindingResult obj.
	 */
	@RequestMapping(value = "/orders/place-order-for-tshirt", method = RequestMethod.POST)
	public String placeOrderForTshirtPage(ModelMap model, @Valid @ModelAttribute("orderAtt") Order orderAtt,
			BindingResult bindingResult) {

		/*
		 * If there are any validation errors send the user back to
		 * place-order-for-tshirt.jsp.
		 */
		if (bindingResult.hasErrors()) {
			return "orders/place-order-for-tshirt";

		}

		// Add order to the repository
		boolean isTransactionDone = orderService.placeOrderForTheTshirtIdInTheStockOfOrderFormat(orderAtt);

		if (isTransactionDone) {
			String successMessage = "Order is successfully placed.";
			model.put("successMessageForTshirtTransaction", successMessage);

		} else {
			String errorMessage = "An error occured in placing the order.";
			model.put("errorMessageForTshirtTransaction", errorMessage);
		}

		/*
		 * TODO Redirect to the list/place orders by returning the "list-place-orders"
		 * to re-populate the orders again. Later find a way show the relevant
		 * success/error message for the transaction.
		 */
		return "redirect:/orders/list-place-orders";

	}

}
