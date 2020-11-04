
public class JavaLibrarySystem {

	public static void main(String[] args) {
		System.out.println("Welcome!");
		


Customer customer = new Customer("Lars Larsson", "0736-832334", 00001);
		
Movie movie = new Movie(1001, "Conan barbaren", 124, 7.2f, "Movie");
System.out.println(movie);
System.out.println(customer);


movie.setBorrowedBy(customer);
System.out.println(movie);

	}

}
