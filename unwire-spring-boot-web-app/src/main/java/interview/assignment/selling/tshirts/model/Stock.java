package interview.assignment.selling.tshirts.model;

import java.util.HashMap;

public class Stock {

	/*
	 *
	 * The backend should keep track of stock
	 */
	private HashMap<String, Integer> stockMapForTshirts;

	public Stock() {
		super();
		this.stockMapForTshirts = new HashMap<String, Integer>();
	}

	public HashMap<String, Integer> getStockMapForTshirts() {
		return stockMapForTshirts;
	}
	
	

}
