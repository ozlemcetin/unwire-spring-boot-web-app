package interview.assignment.selling.tshirts.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import interview.assignment.selling.tshirts.model.User;
import interview.assignment.selling.tshirts.gateway.TshirtRepository;
import interview.assignment.selling.tshirts.model.Tshirt;

@Service
public class TshirtService {

	@Autowired
	TshirtRepository tshirtRepository;

	@Autowired
	UserService userService;

	/*
	 * An admin user should be able to:
	 * 
	 * Add/remove/modify T-shirts
	 * 
	 * Set stock levels
	 */

	public boolean addTshirt(User user, Tshirt tshirt) {

		// an admin user should be able to
		if (user.isAdmin() && tshirt != null) {

			// Is t-shirt already in the repository?
			Tshirt tshirtAlreadySaved = tshirtRepository.findTshirtById(tshirt.getId());

			// If t-shirt is not saved beforehand
			if (tshirtAlreadySaved == null) {

				/*
				 * Cannot add t-shirt having all the equal attributes with other t-shirts.
				 */
				Tshirt tshirtInTheGayeway = tshirtRepository.findTshirtByAttributes(tshirt);

				// If a t-shirt having the same attributes doesn't exist
				if (tshirtInTheGayeway == null) {

					// Add Tshirt to the repository
					tshirtRepository.saveTshirt(tshirt);
					return true;

				}
			}
		}

		return false;

	}

	public boolean updateTshirt(User user, Tshirt tshirt) {

		// an admin user should be able to
		if (user.isAdmin() && tshirt != null) {

			// Is t-shirt already in the repository?
			Tshirt tshirtAlreadySaved = tshirtRepository.findTshirtById(tshirt.getId());

			// If t-shirt must be saved beforehand
			if (tshirtAlreadySaved != null) {

				// Updtae Tshirt int the repository
				tshirtRepository.updateTshirt(tshirt);
				return true;
			}

		}

		return false;

	}

	public boolean removeTshirt(User user, String tshirtId) {

		// an admin user should be able to
		if (user.isAdmin()) {

			// Is t-shirt already in the repository?
			Tshirt tshirtInTheRepository = tshirtRepository.findTshirtById(tshirtId);

			// Valid t-shirt object with a valid id value
			if (tshirtInTheRepository != null) {

				// Remove Tshirt from the repository
				tshirtRepository.deleteTshirt(tshirtInTheRepository);
				return true;

			}

		}

		return false;
	}

	/*
	 * Present the avaliable tshirts - The backend should expose the available
	 * T-shirts to the apps (via a JSON endpoint )
	 */

	public List<Tshirt> presentAllTshirts() {
		return tshirtRepository.findAllTshirtsWithTheirStockLevel();

	}

	public List<Tshirt> presentAvailableTshirts() {

		return tshirtRepository.findAvailableTshirts();

	}

	public Tshirt findTshirtByTshirtId(String tshirtId) {

		return tshirtRepository.findTshirtById(tshirtId);
	}

}
