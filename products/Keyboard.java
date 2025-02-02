package products;

/**
 * This class extends the Product class to provide attributes unique to keyboards.
 * 
 */

public class Keyboard extends Product {
	
	private KeyboardType type;
	private KeyboardLayout layout;
	
	public Keyboard(
		int barcode,
		String brand,
		String color,
		ConnectivityType connectivity,
		int quantityInStock,
		double originalCost,
		double retailPrice,
		KeyboardType type,
		KeyboardLayout layout) {
		
		super(barcode,brand,color,connectivity,quantityInStock,
				originalCost,retailPrice,ProductCategory.KEYBOARD);
		this.type = type;
		this.layout = layout;
	}
	
	public KeyboardType getType() {
		return this.type;
	}
	
	public KeyboardLayout getLayout() {
		return this.layout;
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
		text = text + this.layout;
		
		return text;
	}
}
