import java.io.Serializable;

public class Customer implements Comparable<Customer>, Serializable {
	private String name;
	private String phone;
	private int customerID;

	public Customer(String name, String phone, int customerID) {
		this.name = name;
		this.phone = phone;
		this.customerID = customerID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public int getCustomerID() {
		return customerID;
	}

	public void setCustomerID(int customerID) {
		this.customerID = customerID;
	}

	@Override
	public String toString() {

		String c = getName() + ", " + getPhone() + ", " + getCustomerID();

		return c;
	}

	@Override
	public int compareTo(Customer compareCust) {
		int compareCustomerID = ((Customer) compareCust).getCustomerID();
		/* For Ascending order */
		return this.customerID - compareCustomerID;

		/* For Descending order do like this */
		// return compareCustomerID-this.customerID;
	}

}
