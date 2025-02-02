package payments;
import java.time.LocalDate;
import users.Address;

/**
 * This class represents a receipt generated by a customer payment instance.
 * 
 */

public class Receipt {
	
	private double amountPaid;
	private String billingInfo;
	private String paymentMethod;
	private LocalDate paymentDate;
	private Address payeeAddress;
	
	public Receipt(
		double amountPaid,
		String billingInfo,
		Address payeeAddress) {
		
		this.amountPaid = amountPaid;
		this.billingInfo = billingInfo;
		this.paymentDate = LocalDate.now();
		this.payeeAddress = payeeAddress;
		
		if (this.billingInfo.contains("@")) paymentMethod = "PayPal";
		else paymentMethod = "Credit Card";
	}
	
	public double getAmountPaid() {
		return this.amountPaid;
	}
	
	public String getBillingInfo() {
		return this.billingInfo;
	}
	
	public LocalDate getPaymentDate() {
		return this.paymentDate;
	}
	
	public Address getPayeeAddress() {
		return this.payeeAddress;
	}
	
	@Override
	public String toString() {
		String text = "";
		text = text + String.format("%.2f", this.amountPaid);
		text = text + " paid by " + this.paymentMethod;
		text = text + " using " + this.billingInfo;
		text = text + " on " + this.paymentDate;
		text = text + ", and the delivery address is ";
		text = text + this.payeeAddress + ".";
		
		return text;
	}
}
