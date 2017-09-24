package interview.assignment.selling.tshirts.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import interview.assignment.selling.tshirts.model.User;
import interview.assignment.selling.tshirts.gateway.StockRepository;
import interview.assignment.selling.tshirts.gateway.TshirtRepository;
import interview.assignment.selling.tshirts.model.Tshirt;

@Service
public class StockService {

	@Autowired
	StockRepository stockRepository;

	@Autowired
	TshirtRepository tshirtRepository;

	@Autowired
	UserService userService;

	public boolean updateStockLevelForTheTshirtInTheStock(User user, String tshirtId, int stockLevel) {

		// an admin user should be able to
		if (user.isAdmin()) {

			// if t-shirt is valid and found in the gateway
			Tshirt tshirt = tshirtRepository.findTshirtById(tshirtId);

			// stock level cannot be set to a negaitve number
			if (tshirt != null && stockLevel >= 0) {

				// Update the stock level for the t-shirt in the stock
				stockRepository.updateStockLevelForTshirt(tshirt.getId(), stockLevel);

				return true;

			}
		}

		return false;
	}

}
