
public class JavaLibrarySystem {

	public static void main(String[] args) {
		System.out.println("test");
//		int articleNumber, String productName, int status, int runningTime, float imdbRating, String type
Movie movie = new Movie(1001, "Conan barbaren", 0, 124, 7.2f, "Movie");
System.out.println(movie);
movie.setStatus(2);
System.out.println(movie);

	}

}
