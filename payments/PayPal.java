package payments;
import users.Address;

/**
 * This class represents a PayPal payment instance.
 * 
 */

public class PayPal implements PaymentMethod {

	private String email;
	
	public PayPal(
		String email) {
		
		this.email = email;
	}
	
	public String getEmail() {
		return this.email;
	}
	
	/**
	 * Generates a Receipt object providing information on the transaction.
	 * 
	 * @param amountPaid
	 * @param payeeAddress
	 * @return receipt
	 */
	public Receipt processPayment(double amountPaid, Address payeeAddress) {
		Receipt receipt = new Receipt(amountPaid, this.email, payeeAddress);
		return receipt;
	}
}
