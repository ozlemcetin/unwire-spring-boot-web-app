package interview.assignment.selling.tshirts.model;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Tshirt extends Entity {

	/*
	 * The T-shirts have a name, size, colour, price, and image
	 */

	/*
	 * Tshirt is double binded (Form to Bean and Bean to Form). For Validation
	 * purposes use the @Size for at least 10 characters entrance.
	 * 
	 * To enbale the validation, use @Valid in the controllers.
	 */
	@Size(min = 5, max = 100, message = "Name must be between 5 and 100 characters.")
	private String name;

	private String size;
	private String colour;
	private String price;
	private String image;

	// Used in the jsp pages
	@Min(value = 0, message = "Stock level should not be less 0 (zero).")
	private int stockLevelForTheTshirt;

	/*
	 * No argument constructor for data binding in the jsp pages.
	 */
	public Tshirt() {
		super();
	}

	// Constructor
	public Tshirt(String name, String size, String colour, String price, String image) {

		super();
		this.name = name;
		this.size = size;
		this.colour = colour;
		this.price = price;
		this.image = image;

		// Stock level zero by default
		this.stockLevelForTheTshirt = 0;

	}

	/*
	 * Do two t-shirts have the same name, size, colour, price, and image
	 * attributes?
	 */

	@Override
	public boolean equals(Object obj) {

		if (obj == null)
			return false;

		if (obj == this)
			return true;

		if (!(obj instanceof Tshirt))
			return false;

		Tshirt otherTshirt = (Tshirt) obj;

		/*
		 * The T-shirts have a name, size, colour, price, and image
		 */
		if (otherTshirt.getName() == this.name && otherTshirt.getSize() == this.size
				&& otherTshirt.getColour() == this.colour && otherTshirt.getPrice() == this.price
				&& otherTshirt.getImage() == this.image)

			return true;
		else
			return false;

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public String getColour() {
		return colour;
	}

	public void setColour(String colour) {
		this.colour = colour;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	@Override
	public String toString() {
		return "Tshirt [name=" + name + ", size=" + size + ", colour=" + colour + ", price=" + price + ", image="
				+ image + "]";
	}

	public int getStockLevelForTheTshirt() {
		return stockLevelForTheTshirt;
	}

	public void setStockLevelForTheTshirt(int stockLevelForTheTshirt) {
		this.stockLevelForTheTshirt = stockLevelForTheTshirt;
	}

}
