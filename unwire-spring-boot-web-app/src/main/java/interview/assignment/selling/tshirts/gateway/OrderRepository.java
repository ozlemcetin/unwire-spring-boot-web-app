package interview.assignment.selling.tshirts.gateway;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Repository;

import interview.assignment.selling.tshirts.model.Order;

@Repository
public class OrderRepository {

	private static ArrayList<Order> orders;

	static {

		orders = new ArrayList<Order>();
	}

	public void saveOrder(Order order) {

		// Add order to the gateway
		orders.add(order);
	}

	public List<Order> findAllOrdersMadeByTheUserIdSortedChronologically(String userId) {

		/*
		 * Return only the ones placed by the user id
		 */
		List<Order> sortedOrders = new ArrayList<Order>();

		for (Order order : orders) {

			// t-shirt is placed by the user
			if (userId != null && Objects.equals(order.getUserId(), userId))
				sortedOrders.add(order);

		} // For Loop

		// Sort the list
		Collections.sort(sortedOrders, new Comparator<Order>() {

			@Override
			public int compare(Order o1, Order o2) {

				// Last order presented as a 1st, descending order
				return o2.getOrderDate().compareTo(o1.getOrderDate());
			}

		});

		return sortedOrders;
	}

}
