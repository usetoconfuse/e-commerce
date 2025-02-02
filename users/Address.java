package users;

/**
 * This class represent's a user's address.
 * 
 */

public class Address {
	
	private String houseNumber;
	private String postcode;
	private String city;
	
	public Address(
		String houseNumber,
		String postcode,
		String city) {
		
		this.houseNumber = houseNumber;
		this.postcode = postcode;
		this.city = city;
	}
	
	public String getHouseNumber() {
		return this.houseNumber;
	}
	
	public String getPostcode() {
		return this.postcode;
	}
	
	public String getCity() {
		return this.city;
	}
	
	@Override
	public String toString() {
		String text = "";
		text = text + this.houseNumber + ", ";
		text = text + this.postcode + ", ";
		text = text + this.city;
		
		return text;
	}
}
