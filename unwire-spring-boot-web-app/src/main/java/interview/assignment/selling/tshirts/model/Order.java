package interview.assignment.selling.tshirts.model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Order extends Entity {

	private String userId;
	private String tshirtId;
	private int orderLevelForTheTshirt;
	private Date orderDate;
	private boolean orderForThsirtSuccesfullyPlaced;

	// To show information in the jsp pages.
	private Tshirt tshirt;

	// For Validators
	public Order() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Order(String userId, String tshirtId, int orderLevelForTheTshirt, Date orderDate) {
		super();
		this.userId = userId;
		this.tshirtId = tshirtId;
		this.orderLevelForTheTshirt = orderLevelForTheTshirt;
		this.orderDate = orderDate;
		this.orderForThsirtSuccesfullyPlaced = false;
	}
	
	

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getTshirtId() {
		return tshirtId;
	}

	public void setTshirtId(String tshirtId) {
		this.tshirtId = tshirtId;
	}

	public int getOrderLevelForTheTshirt() {
		return orderLevelForTheTshirt;
	}

	public void setOrderLevelForTheTshirt(int orderLevelForTheTshirt) {
		this.orderLevelForTheTshirt = orderLevelForTheTshirt;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public boolean isOrderForThsirtSuccesfullyPlaced() {
		return orderForThsirtSuccesfullyPlaced;
	}

	public void setOrderForThsirtSuccesfullyPlaced(boolean orderForThsirtSuccesfullyPlaced) {
		this.orderForThsirtSuccesfullyPlaced = orderForThsirtSuccesfullyPlaced;
	}

	public Tshirt getTshirt() {
		return tshirt;
	}

	public void setTshirt(Tshirt tshirt) {
		this.tshirt = tshirt;
	}

	@Override
	public String toString() {
		return "[tshirt: '" + this.tshirtId + "', amount: " + this.orderLevelForTheTshirt + "', transaction done : "
				+ this.orderForThsirtSuccesfullyPlaced + "', date : "
				+ new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(this.orderDate) + "]";
	}

}
