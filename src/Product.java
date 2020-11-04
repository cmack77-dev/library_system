import java.io.Serializable;

abstract class Product implements Serializable {
	private int articleNumber;
	private String productName;
	private int status;

	public Product(int articleNumber, String productName, int status) {
		this.articleNumber = articleNumber;
		this.productName = productName;
		this.status = status;
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

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

}
