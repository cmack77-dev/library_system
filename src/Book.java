import java.io.Serializable;

public class Book extends Product implements Serializable {
	private static final long serialVersionUID = 1L;
	private int pages;
	private String publisher;

	public Book(int articleNumber, String productName, int pages, String publisher, String type, double value) {
		super(articleNumber, productName, type, value);
		this.pages = pages;
		this.publisher = publisher;
	}

	public int getPages() {
		return pages;
	}

	public void setPages(int pages) {
		this.pages = pages;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	@Override
	public String toString() {
		String s;
		if (!showInfo) {
			s = getArticleNumber() + " (" + getType() + "): " + getProductName() + ".";
			if (getBorrowedBy() != null) {
				s += "\n\tBorrowed by: " + getBorrowedBy();
			} else {
				s += " (in stock)";
			}
		} else {
			s = " (" + getType() + "): " + getProductName() + ": " + getValue() + "kr, Pages " + getPages()
					+ ", Publisher " + getPublisher();
		}
		return s;
	}
}
