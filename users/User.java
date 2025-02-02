package users;

/**
 * This class represents a user in the system.
 * 
 */

public class User {
	
	private String userId;
	private String username;
	private String name;
	private Address address;
	private UserRole role;
	
	public User(
		String userId,
		String username,
		String name,
		Address address,
		UserRole role) {
		
		this.userId = userId;
		this.username = username;
		this.name = name;
		this.address = address;
		this.role = role;
	}
	
	
	public String getUserId() {
		return this.userId;
	}
	
	public String getUsername() {
		return this.username;
	}
	
	public String getName() {
		return this.name;
	}
	
	public Address getAddress() {
		return this.address;
	}
	
	public UserRole getRole() {
		return this.role;
	}
	
	@Override
	public String toString() {
		return this.username;
	}
}
