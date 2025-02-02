package products;

/**
 * This class extends the Product class to provide attributes unique to mice.
 * 
 */

public class Mouse extends Product {
	
	private MouseType type;
	private int numberOfButtons;
	
	public Mouse(
		int barcode,
		String brand,
		String color,
		ConnectivityType connectivity,
		int quantityInStock,
		double originalCost,
		double retailPrice,
		MouseType type,
		int numberOfButtons) {
		
		super(barcode, brand, color, connectivity, quantityInStock,
				originalCost, retailPrice, ProductCategory.MOUSE);
		this.type = type;
		this.numberOfButtons = numberOfButtons;
	}
	
	public MouseType getType() {
		return this.type;
	}
	
	public int getNumberOfButtons() {
		return this.numberOfButtons;
	}

	@Override
	public String toString() {
		String text = "";
		text = text + this.getBarcode() + ", ";
		text = text + this.getCategory().toString().toLowerCase() + ", ";
		text = text + this.type.toString().toLowerCase() + ", ";
		text = text + this.getBrand() + ", ";
		text = text + this.getColor() + ", ";
		text = text + this.getConnectivity().toString().toLowerCase() + ", ";
		text = text + this.getQuantityInStock() + ", ";
		text = text + this.getOriginalCost() + ", ";
		text = text + this.getRetailPrice() + ", ";
		text = text + this.numberOfButtons;
		
		return text;
	}
}
