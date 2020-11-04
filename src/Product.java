import java.io.Serializable;

abstract class Product implements Serializable {
	private int articleNumber;
	private String productName;
	private int status=0;
	private String type; //ENUM?

	public Product(int articleNumber, String productName, int status, String type) {
		this.articleNumber = articleNumber;
		this.productName = productName;
		this.status = status;
		this.type=type;
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
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String toString(){
		
//		12345 (Book): To Kill a Mockingbird.
//	    Borrowed by: Alice Doe, 832-337-2959
		
		String s=getArticleNumber()+" ("+getType()+"): " + getProductName()+".";
		if (getStatus() != 0) {
			s += "\n\tBorrowed by: " + "CUSTOMER";  // ÄNDRA TILL ATT HÄMTA KUNDINFO
		}
		else {
			s += " (in stock)";
		}
	return s;	
	}
}
