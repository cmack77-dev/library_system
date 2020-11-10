import java.io.Serializable;

public class Movie extends Product implements Serializable {
	private static final long serialVersionUID = 1L;
	private int runningTime;
	private float imdbRating;

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
