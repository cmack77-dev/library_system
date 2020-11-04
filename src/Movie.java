import java.io.Serializable;

public class Movie extends Product implements Serializable {
	private int runningTime;
	private float imdbRating;

	

	public Movie(int articleNumber, String productName, int status, int runningTime, float imdbRating) {
		super(articleNumber, productName, status);
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
