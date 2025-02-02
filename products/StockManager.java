package products;

import java.util.HashMap;
import java.util.Scanner;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

/**
 * This class provides methods to interface with the stock database file.
 * 
 */

public class StockManager {
	
	/**
	 * The stock list is represented by a HashMap with integer keys and Product values.
	 * The key is the product's barcode, and the associated value is the Product object.
	 */
	private HashMap<Integer, Product> stockList;
	
	public StockManager() {
		this.stockList = new HashMap<Integer, Product>();
	}
	
	public HashMap<Integer, Product> getStockList() {
		return this.stockList;
	}
	
	/**
	 * Returns the list of products in stock, sorted ascending by retail price.
	 * 
	 * @return A sorted ArrayList of products.
	 */
	public ArrayList<Product> sortedProducts() {
		ArrayList<Product> productList = new ArrayList<Product>();
		productList.addAll(this.stockList.values());
		
		PriceComparator pc = new PriceComparator();
		Collections.sort(productList, pc);
		return productList;
	};
	
	/**
	 * Adds a product to the stock list.
	 * 
	 * @param product
	 * @throws IOException
	 */
	public void addProduct(Product product) throws IOException {
		if (!this.stockList.containsKey(product.getBarcode())) {
			this.stockList.put(product.getBarcode(), product);
			this.writeToFile();
		}
		else {
			throw new IllegalArgumentException("A product already exists with this barcode");
		}
	}
	
	/**
	 * Decreases the stock of a product in the list.
	 * 
	 * @param barcode
	 * @param decreaseBy
	 * @throws IOException
	 */
	public void decreaseStock(int barcode, int decreaseBy) throws IOException {
	    this.readStockFile(); // Read stock file in case a product's stock was updated by a different user
		if (decreaseBy < 0) {
			throw new IllegalArgumentException("Cannot decrease stock a negative amount");
		}
		else if (!this.stockList.containsKey(barcode)) {
			throw new IllegalArgumentException("No product exists with this barcode");
		}
		
		else if (this.stockList.get(barcode).getQuantityInStock() == 0) {
			throw new IllegalArgumentException("Product " + barcode + " is out of stock");
		}
		else {
			Product product = this.stockList.get(barcode);
			product.setQuantityInStock(product.getQuantityInStock() - decreaseBy);
			this.stockList.put(barcode, product);
			this.writeToFile();
		}
	}
	
	/**
	 * Search the stock list for a product with a certain barcode.
	 * 
	 * @param barcode
	 * @return product
	 */
	public Product searchBarcode(int barcode) {
		return this.stockList.get(barcode);
	}
	
	public ArrayList<Mouse> filterMouseButtons(int filterNumber) {
		ArrayList<Mouse> mouseList = new ArrayList<Mouse>();
		
		for (Product entry : this.stockList.values()) {
			if (entry.getCategory() == ProductCategory.MOUSE) {
				Mouse mEntry = (Mouse) entry;
				if (mEntry.getNumberOfButtons() == filterNumber) {
					mouseList.add(mEntry);
				}
			}
		}
		
		PriceComparator pc = new PriceComparator();
		Collections.sort(mouseList, pc);
		return mouseList;
	}
	
	/**
	 * Write the contents of the stock list to the stock database file.
	 * 
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public void writeToFile() throws FileNotFoundException, IOException {
		FileWriter outputFile = new FileWriter("Stock.txt");
		BufferedWriter bWriter = new BufferedWriter(outputFile);
		for (Product p : this.stockList.values()) {
			bWriter.write(p.toString() + "\n");
		}
		bWriter.close();
	}
	
	/**
	 * Read and store the stock list from the database file.
	 * 
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public void readStockFile() throws FileNotFoundException, IOException {
		this.stockList.clear();
		Scanner fileReader = new Scanner(new File("Stock.txt"));
		fileReader.useDelimiter(",|\n");
		while(fileReader.hasNext()) {
			int barcode = Integer.parseInt(fileReader.next().trim());
			ProductCategory category = ProductCategory.valueOf(fileReader.next().trim().toUpperCase());
			if (category == ProductCategory.KEYBOARD) {
				KeyboardType type = KeyboardType.valueOf(fileReader.next().trim().toUpperCase());
				String brand = fileReader.next().trim();
				String color = fileReader.next().trim();
				ConnectivityType connectivity = ConnectivityType.valueOf(fileReader.next().trim().toUpperCase());
				int quantityInStock = Integer.parseInt(fileReader.next().trim());
				double originalCost = Double.parseDouble(fileReader.next().trim());
				double retailPrice = Double.parseDouble(fileReader.next().trim());
				KeyboardLayout layout = KeyboardLayout.valueOf(fileReader.next().trim());
				
				Keyboard keyboard = new Keyboard(barcode,brand, color,connectivity,
						quantityInStock,originalCost,retailPrice,type,layout);
				this.addProduct(keyboard);
			}
			else {
				MouseType type = MouseType.valueOf(fileReader.next().trim().toUpperCase());
				String brand = fileReader.next().trim();
				String color = fileReader.next().trim();
				ConnectivityType connectivity = ConnectivityType.valueOf(fileReader.next().trim().toUpperCase());
				int quantityInStock = Integer.parseInt(fileReader.next().trim());
				double originalCost = Double.parseDouble(fileReader.next().trim());
				double retailPrice = Double.parseDouble(fileReader.next().trim());
				int numberOfButtons = Integer.parseInt(fileReader.next().trim());
				
				Mouse mouse = new Mouse(barcode,brand, color,connectivity,
						quantityInStock,originalCost,retailPrice,type,numberOfButtons);
				this.addProduct(mouse);
			}
		}
	}
}
