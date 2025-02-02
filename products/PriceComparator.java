package products;

import java.util.Comparator;

/**
 * This class implements a Comparator to enable sorting products by retail price.
 * 
 */

public class PriceComparator implements Comparator<Product> {
	
	public int compare(Product p1, Product p2) {
		return (Double.compare(p1.getRetailPrice(), p2.getRetailPrice()));
	}
}
