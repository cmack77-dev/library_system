import java.io.Serializable;

public class Movie extends Product implements Serializable {
	/**
	 * Serialversion.
	 */
	private static final long serialVersionUID = 1L;
	private int runningTime;
	private float imdbRating;

	/**
	 * Constructor for Movie which extends Product.
	 * 
	 * @param runningTime Sets running time for Movie.
	 * @param imdbRating  Sets rating for Movie.
	 * @param articleNumber Gets article number for Book.
	 * @param productName   Gets product name for Book.
	 * @param type          Gets type for Product.
	 * @param value         Gets value for Product.
	 * super gets variables from Product through inheritance.
	 */
	public Movie(int articleNumber, String productName, int runningTime, float imdbRating, String type, double value) {
		super(articleNumber, productName, type, value);
		this.runningTime = runningTime;
		this.imdbRating = imdbRating;
	}

	public int getRunningTime() {
		return runningTime;
	}

	public void setRunningTime(int runningTime) {
		this.runningTime = runningTime;
	}

	public float getImdbRating() {
		return imdbRating;
	}

	public void setImdbRating(float imdbRating) {
		this.imdbRating = imdbRating;
	}

	
	/**
	 * Gets type from Product to identify Book or Movie and sets values to String.
	 * Shows registered information from Movie or if Movie is in stock through getters.
	 * Returns information if Movie is Borrowed and if so by whom.
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
			s = " (" + getType() + "): " + getProductName() + ": Value " + getValue() + "kr, Length " + getRunningTime()
					+ ", Rating " + getImdbRating();
		}
		return s;
	}

}
