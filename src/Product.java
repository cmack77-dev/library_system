import java.io.Serializable;

abstract class Product implements Serializable {
	private int articleNumber;
	private String productName;
	private Customer borrowedBy = null;
	private String type;
	private double value;
	static boolean showInfo = false;

	public Product(int articleNumber, String productName, String type, double value) {
		this.articleNumber = articleNumber;
		this.productName = productName;
		this.type = type;
		this.value = value;
	}

	public static boolean isShowInfo() {
		return showInfo;
	}

	public static void setShowInfo(boolean showInfo) {
		Product.showInfo = showInfo;
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

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}

}
