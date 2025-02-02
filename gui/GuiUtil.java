package gui;

import products.*;

import javax.swing.JTextField;

/**
 * This class provides utility functions for the gui package.
 * 
 */

public final class GuiUtil {
	
	/**
	 * Retrieves the value of a text field and performs a presence check on the data.
	 * This ensures that no text fields are empty.
	 * 
	 * @param txtField
	 */
	public static String getTextSafe(JTextField txtField) {
		String text = txtField.getText().trim();
		if (text.equals("")) {
			throw new IllegalArgumentException("Text fields cannot be empty!");
		}
		else {
			return text;
		}
	}
	
	
	/**
	 * Take a product and generate row data formatted for the admin stock table.
	 * 
	 * @param product
	 */
	public static Object[] getStockEntry(Product product) {
		String bcString = String.format("%06d", product.getBarcode());
		String opString = String.format("%.2f", product.getOriginalCost());
		String rpString = String.format("%.2f", product.getRetailPrice());
		if (product.getCategory() == ProductCategory.KEYBOARD) {
			Keyboard k = (Keyboard) product;
			Object[] rowData = new Object[] {bcString,k.getCategory(),k.getType(),k.getBrand(),k.getColor(),k.getConnectivity(),
					k.getQuantityInStock(),opString,rpString,k.getLayout()};
			return rowData;
		}
		else {
			Mouse m = (Mouse) product;
			Object[] rowData = new Object[] {bcString,m.getCategory(),m.getType(),m.getBrand(),m.getColor(),m.getConnectivity(),
					m.getQuantityInStock(),opString,rpString,m.getNumberOfButtons()};
			return rowData;
		}
	}
	
	
	/**
	 * Take a product and generate row data formatted for the customer products table.
	 * 
	 * @param product
	 */
	public static Object[] getProductsEntry(Product product) {
		String bcString = String.format("%06d", product.getBarcode());
		String rpString = String.format("%.2f", product.getRetailPrice());
		if (product.getCategory() == ProductCategory.KEYBOARD) {
			Keyboard k = (Keyboard) product;
			Object[] rowData = new Object[] {bcString,k.getCategory(),k.getType(),k.getBrand(),k.getColor(),k.getConnectivity(),
					k.getQuantityInStock(),rpString,k.getLayout()};
			return rowData;
		}
		else {
			Mouse m = (Mouse) product;
			Object[] rowData = new Object[] {bcString,m.getCategory(),m.getType(),m.getBrand(),m.getColor(),m.getConnectivity(),
					m.getQuantityInStock(),rpString,m.getNumberOfButtons()};
			return rowData;
		}
	}
	
	
	/**
	 * Take a product and generate row data formatted for the customer basket table.
	 * 
	 * @param product
	 */
	public static Object[] getBasketEntry(Product product, int quantityInBasket) {
		String bcString = String.format("%06d", product.getBarcode());
		String rpString = String.format("%.2f", product.getRetailPrice());
		if (product.getCategory() == ProductCategory.KEYBOARD) {
			Keyboard k = (Keyboard) product;
			Object[] rowData = new Object[] {bcString,k.getCategory(),k.getType(),k.getBrand(),k.getColor(),k.getConnectivity(),
					rpString,k.getLayout(),quantityInBasket};
			return rowData;
		}
		else {
			Mouse m = (Mouse) product;
			Object[] rowData = new Object[] {bcString,m.getCategory(),m.getType(),m.getBrand(),m.getColor(),m.getConnectivity(),
					rpString,m.getNumberOfButtons(),quantityInBasket};
			return rowData;
		}
	}
}
