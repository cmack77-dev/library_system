import java.io.Serializable;

abstract class Product implements Serializable {
	private int articleNumber;
	private String productName;
	private Customer borrowedBy = null;
	private String type; // ENUM?

	public Product(int articleNumber, String productName, String type) {
		this.articleNumber = articleNumber;
		this.productName = productName;
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

		String s = getArticleNumber() + " (" + getType() + "): " + getProductName() + ".";
		if (getBorrowedBy() != null) {
			s += "\n\tBorrowed by: " + getBorrowedBy();
		} else {
			s += " (in stock)";
		}
		return s;
	}
}
