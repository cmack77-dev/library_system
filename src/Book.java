import java.io.Serializable;

public class Book extends Product implements Serializable {
	private int pages;
	private String publisher;

	public Book(int articleNumber, String productName, int pages, String publisher, String type) {
		super(articleNumber, productName, type);
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

}
