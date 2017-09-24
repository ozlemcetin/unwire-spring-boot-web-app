package interview.assignment.selling.tshirts.gateway;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

@Repository
public class StockRepository {

	private static Map<String, Integer> mapStock;

	static {

		mapStock = new HashMap<String, Integer>();
	}

	public int findCurrentStockLevelForTshirt(String tshirtId) {

		if (mapStock.containsKey(tshirtId))
			return mapStock.get(tshirtId).intValue();

		return 0;

	}

	public void updateStockLevelForTshirt(String tshirtId, int newStockLevel) {

		mapStock.put(tshirtId, new Integer(newStockLevel));
	}
}
