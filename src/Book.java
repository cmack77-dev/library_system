import java.io.Serializable;

public class Book extends Product implements Serializable {
	/**
	 * Serialversion.
	 */
	private static final long serialVersionUID = 1L;
	private int pages;
	private String publisher;

	/**
	 * Constructor for Book which extends Product.
	 * 
	 * @param pages         Sets amount of pages for Book.
	 * @param publisher     Sets who the publisher is for Book.
	 * @param articleNumber Gets article number for Book.
	 * @param productName   Gets product name for Book.
	 * @param type          Gets type for Product.
	 * @param value         Gets value for Product.
	 * super gets variables from Product through inheritance.
	 */
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
	
	/**
	 * Gets type from Product to identify Book or Movie and sets values to String.
	 * Shows registered information from Book or if Book is in stock through getters.
	 * Returns information if Book is Borrowed and if so by whom.
	 */
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
