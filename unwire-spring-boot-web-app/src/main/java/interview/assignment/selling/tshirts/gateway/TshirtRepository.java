package interview.assignment.selling.tshirts.gateway;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;
import interview.assignment.selling.tshirts.model.Tshirt;

@Repository
public class TshirtRepository {

	private static ArrayList<Tshirt> tshirts;

	static {
		tshirts = new ArrayList<Tshirt>();

		// Add t-shirts to the repository
		tshirts.add(new Tshirt("tshirt1", "size1", "colour1", "price1", "image1"));
		tshirts.add(new Tshirt("tshirt2", "size2", "colour2", "price2", "image2"));
		tshirts.add(new Tshirt("tshirt3", "size3", "colour3", "price3", "image3"));

	}

	public List<Tshirt> findAllTshirtsWithTheirStockLevel() {

		for (Tshirt tshirt : tshirts) {

			// Find current stock level for the t-shirt
			StockRepository stockRepository = new StockRepository();
			int currentStockLevel = stockRepository.findCurrentStockLevelForTshirt(tshirt.getId());

			// Set stock level for the t-shirt
			tshirt.setStockLevelForTheTshirt(currentStockLevel);

		} // For Loop

		return tshirts;
	}

	public List<Tshirt> findAvailableTshirts() {

		// All T-shirts
		List<Tshirt> resultsAttTshirts = findAllTshirtsWithTheirStockLevel();

		/*
		 * Return only the ones available in the stock
		 */
		List<Tshirt> results = new ArrayList<Tshirt>();
		for (Tshirt tshirt : resultsAttTshirts) {

			// t-shirt is avaliable in the stock
			if (tshirt.getStockLevelForTheTshirt() > 0)
				results.add(tshirt);

		} // For Loop

		return results;
	}

	public Tshirt findTshirtByAttributes(Tshirt tshirt) {

		for (Tshirt tshirtInTheGayeway : tshirts) {

			if (tshirtInTheGayeway.equals(tshirt))
				return tshirtInTheGayeway;

		} // For Loop

		return null;
	}

	public Tshirt findTshirtById(String tshirtId) {

		for (Tshirt tshirt : tshirts) {
			if (tshirt.getId().equals(tshirtId))
				return tshirt;
		} // For Loop

		return null;
	}

	public void saveTshirt(Tshirt tshirt) {

		// Add t-shirt to the gateway
		tshirts.add(tshirt);

	}

	public void deleteTshirt(Tshirt tshirt) {

		// Remove t-shirt from the gateway
		tshirts.remove(tshirt);

	}

	public void updateTshirt(Tshirt tshirt) {

		// Remove t-shirt from the gateway first
		Tshirt tshirtToBeRemoved = findTshirtById(tshirt.getId());

		if (tshirtToBeRemoved != null) {

			// Remove the old t-shirt
			tshirts.remove(tshirtToBeRemoved);

			// Add the updated t-shirt to the gateway
			tshirts.add(tshirt);
		}

	}

}
