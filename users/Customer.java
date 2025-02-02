package users;

import java.io.IOException;
import java.util.HashMap;
import products.*;
import payments.Receipt;
import payments.PaymentMethod;

/**
 * This class extends the User class to include a customer's basket of products.
 * 
 */

public class Customer extends User {
	
	/**
	 * The user's basket contents are stored as a HashMap with integer keys and values.
	 * The key is a product's barcode and the associated value is the quantity of that item in the basket.
	 */
	private HashMap<Integer, Integer> basketContents;
	private double basketTotal;
	
	public Customer(
		String userId,
		String username,
		String name,
		Address address) {
		
		super(userId, username, name, address, UserRole.CUSTOMER);
		this.basketContents = new HashMap<Integer, Integer>();
		this.basketTotal = 0.0;
	}
	
	public HashMap<Integer, Integer> getBasketContents() {
		return this.basketContents;
	}
	
	public double getBasketTotal() {
		return this.basketTotal;
	}
	
	/**
	 * Add a given product to the customer's basket.
	 * 
	 * @param product
	 */
	public void addToBasket(Product product) {
		int quantityInBasket = 0;
		if (this.basketContents.get(product.getBarcode()) != null) {
			quantityInBasket = this.basketContents.get(product.getBarcode());
		}
		if (product.getQuantityInStock() <= quantityInBasket) {
			throw new IllegalArgumentException("Sorry, you can't order any more of that product.");
		}
		else {
			this.basketContents.put(product.getBarcode(), quantityInBasket + 1);
			this.basketTotal += product.getRetailPrice();
		}
	}
	
	
	/**
	 * Clear the customer's basket and reset the total.
	 */
	public void clearBasket() {
		this.basketContents.clear();
		this.basketTotal = 0.0;
	}
	
	/**
	 * Process the payment for the items in the customer's basket using the given PaymentMethod,
	 * then update the stock of each product purchased.
	 * 
	 * @param stock
	 * @param paymentMethod
	 * @return receipt
	 * @throws IOException
	 */
	public Receipt checkout(StockManager stock, PaymentMethod paymentMethod) throws IOException {
		for (HashMap.Entry<Integer, Integer> entry : this.basketContents.entrySet()) {
			int barcode = entry.getKey();
			int quantitySold = entry.getValue();
			stock.decreaseStock(barcode, quantitySold);
		}
		
		Receipt receipt = paymentMethod.processPayment(this.basketTotal, this.getAddress());
		this.clearBasket();
		return receipt;
	}
}
