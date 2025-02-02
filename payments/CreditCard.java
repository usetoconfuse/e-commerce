package payments;
import users.Address;

/**
 * This class represents a credit card payment instance.
 * 
 */

public class CreditCard implements PaymentMethod {
	
	private int cardNumber;
	private int securityCode;
	
	public CreditCard(
		int cardNumber,
		int securityCode) {
		
		this.cardNumber = cardNumber;
		this.securityCode = securityCode;
	}
	
	public int getCardNumber() {
		return this.cardNumber;
	}
	
	public int getSecurityCode() {
		return this.securityCode;
	}
	
	
	/**
	 * Generates a Receipt object providing information on the transaction.
	 * 
	 * @param amountPaid
	 * @param payeeAddress
	 * @return receipt
	 */
	public Receipt processPayment(double amountPaid, Address payeeAddress) {
		String cnString = String.format("%06d", this.cardNumber);
		Receipt receipt = new Receipt(amountPaid, cnString, payeeAddress);
		return receipt;
	}
}
