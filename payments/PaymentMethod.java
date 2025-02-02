package payments;
import users.Address;

/**
 * The PaymentMethod interface represents a payment method that can be used to process payments.
 * 
 */

public interface PaymentMethod {
	
	/**
	 * Generates a Receipt object providing information on the transaction.
	 * 
	 * @param amountPaid
	 * @param payeeAddress
	 * @return receipt
	 */
	public abstract Receipt processPayment(double amount, Address payeeAddress);
}
