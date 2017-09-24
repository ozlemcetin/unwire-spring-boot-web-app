package interview.assignment.selling.tshirts.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import interview.assignment.selling.tshirts.model.User;
import interview.assignment.selling.tshirts.model.Order;
import interview.assignment.selling.tshirts.gateway.OrderRepository;
import interview.assignment.selling.tshirts.gateway.StockRepository;
import interview.assignment.selling.tshirts.gateway.TshirtRepository;
import interview.assignment.selling.tshirts.gateway.UserRepository;
import interview.assignment.selling.tshirts.model.Tshirt;

@Service
public class OrderService {

	@Autowired
	OrderRepository orderRepository;

	@Autowired
	TshirtRepository tshirtRepository;

	@Autowired
	UserRepository userRepository;

	@Autowired
	StockRepository stockRepository;

	/*
	 * Place orders for the t-shirts - The backend should expose an endpoint where
	 * the apps can place orders (JSON, no payment etc. required)
	 */

	public boolean placeOrderForTheTshirtIdInTheStockOfOrderFormat(Order order) {

		boolean orderIsPlaced = false;

		// Valid order object with a valid id value
		if (order != null && order.getId() != null) {

			Tshirt tshirt = tshirtRepository.findTshirtById(order.getTshirtId());
			User user = userRepository.findUserByUserId(order.getUserId());

			// If t-shirt id is valid,t-shirt is found
			// If user id is valid, user is found
			if (tshirt != null && user != null) {

				// Stock level for tshirt
				int stockLevel = stockRepository.findCurrentStockLevelForTshirt(tshirt.getId());

				// Order level for tshirt
				int orderLevelForTheTshirt = order.getOrderLevelForTheTshirt();

				/*
				 * If order level is bigger than zero and stock level is greater than this
				 * value, place the order
				 */
				if (orderLevelForTheTshirt > 0 && stockLevel >= order.getOrderLevelForTheTshirt()) {

					// Update stock level for the t-shirt
					int newStockLevel = stockLevel - orderLevelForTheTshirt;
					stockRepository.updateStockLevelForTshirt(tshirt.getId(), newStockLevel);

					orderIsPlaced = true;

				}

				// Save the order for history
				order.setOrderForThsirtSuccesfullyPlaced(orderIsPlaced);
				orderRepository.saveOrder(order);

			}
		}

		return orderIsPlaced;
	}

	public List<Order> presentOrdersMadeByTheUserSortedChronologically(String userId) {

		List<Order> sortedOrders = orderRepository.findAllOrdersMadeByTheUserIdSortedChronologically(userId);
		for (Order order : sortedOrders) {

			Tshirt tshirt = tshirtRepository.findTshirtById(order.getTshirtId());
			order.setTshirt(tshirt);
		}

		return orderRepository.findAllOrdersMadeByTheUserIdSortedChronologically(userId);
	}
}
