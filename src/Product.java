import java.io.Serializable;

abstract class Product implements Comparable<Product>, Serializable {
	/**
	 * Serialversion.
	 */
	private static final long serialVersionUID = 1L;
	private int articleNumber;
	private String productName;
	private Customer borrowedBy = null;
	private String type;
	private double value;
	static boolean showInfo = false;

	/**
	 * Constructor for Product.
	 * 
	 * @param articleNumber Sets article number for Product.
	 * @param productName   Sets product name for Product.
	 * @param type          Sets type of product.
	 * @param value         Sets the value of the product.
	 */
	public Product(int articleNumber, String productName, String type, double value) {
		this.articleNumber = articleNumber;
		this.productName = productName;
		this.type = type;
		this.value = value;
	}

	/**
	 * Specifies which info to print for the toString method
	 * 
	 * @return true or false.
	 */
	public static boolean isShowInfo() {
		return showInfo;
	}

	/**
	 * 
	 * @param showInfo sets the showInfo to true or false.
	 */
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

	@Override
    public int compareTo(Product compareProd) {
        int compareArticleNumber=((Product)compareProd).getArticleNumber();
        /* For Ascending order*/
        return this.articleNumber-compareArticleNumber;

        /* For Descending order do like this */
        //return compareArticleNumber-this.ArticleNumber;
	}
}
