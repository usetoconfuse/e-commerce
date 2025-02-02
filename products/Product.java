package products;

/**
 * This class provides methods for handling Product attributes
 * 
 */

public abstract class Product {
	
	private int barcode;
	private String brand;
	private String color;
	private ConnectivityType connectivity;
	private int quantityInStock;
	private double originalCost;
	private double retailPrice;
	private ProductCategory category;
	
	
	public Product(
		int barcode,
		String brand,
		String color,
		ConnectivityType connectivity,
		int quantityInStock,
		double originalCost,
		double retailPrice,
		ProductCategory category) {
		
		this.barcode = barcode;
		this.brand = brand;
		this.color = color;
		this.connectivity = connectivity;
		this.quantityInStock = quantityInStock;
		this.originalCost = originalCost;
		this.retailPrice = retailPrice;
		this.category = category;
	}
	
	public int getBarcode() {
		return this.barcode;
	}
	
	public String getBrand() {
		return this.brand;
	}
	
	public String getColor() {
		return this.color;
	}
	
	public ConnectivityType getConnectivity() {
		return this.connectivity;
	}
	
	public int getQuantityInStock() {
		return this.quantityInStock;
	}
	
	public double getOriginalCost() {
		return this.originalCost;
	}
	
	public double getRetailPrice() {
		return this.retailPrice;
	}
	
	public ProductCategory getCategory() {
		return this.category;
	}
	
	public void setQuantityInStock(int quantityInStock) {
		this.quantityInStock = quantityInStock;
	}
	
	@Override
	public abstract String toString();
}
