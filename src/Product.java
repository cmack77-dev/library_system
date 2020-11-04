import java.io.Serializable;

abstract class Product implements Serializable {
	private int articleNumber;
	private String productName;
//	private int status=0;
	private Customer borrowedBy = null;
	private String type; // ENUM?

	public Product(int articleNumber, String productName, String type) {
		this.articleNumber = articleNumber;
		this.productName = productName;
//		this.status = status;
		this.type = type;
	}

	public int getArticleNumber() {
		return articleNumber;
	}

	public void setArticleNumber(int articleNumber) {
		this.articleNumber = articleNumber;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

//	public int getStatus() {
//		return status;
//	}
//
//	public void setStatus(int status) {
//		this.status = status;
//	}

	public Customer getBorrowedBy() {
		return borrowedBy;
	}

	public void setBorrowedBy(Customer borrowedBy) {
		this.borrowedBy = borrowedBy;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {

//		12345 (Book): To Kill a Mockingbird.
//	    Borrowed by: Alice Doe, 832-337-2959

		String s = getArticleNumber() + " (" + getType() + "): " + getProductName() + ".";
		if (getBorrowedBy() != null) {
			s += "\n\tBorrowed by: " + getBorrowedBy(); 
		} else {
			s += " (in stock)";
		}
		return s;
	}
}
