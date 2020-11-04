import java.io.Serializable;

public class Movie extends Product implements Serializable {
	private int runningTime;
	private float imdbRating;

	public Movie(int articleNumber, String productName, int runningTime, float imdbRating, String type) {
		super(articleNumber, productName, type);
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

}
