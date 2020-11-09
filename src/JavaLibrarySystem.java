
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.*;
import java.util.Scanner;

/**
 * 
 * Java Library System for management of movies and books.
 * 
 * @author Markus Johanssson & Stefan Kusmuk
 * 
 */
public class JavaLibrarySystem<E extends Product> implements Serializable {

	/**
	 * Holds a list of all books and movies. Initialized from bin file and saved
	 * when system exits.
	 */
	List<E> products = new ArrayList<E>();

	/**
	 * Holds a list of all customers. Initialized from bin file and saved when
	 * system exits.
	 */
	List<Customer> customers = new ArrayList<Customer>();

	/**
	 * Constant that holds the path to bin file.
	 */
	static final String FILE_PATH = "library.bin";

	/**
	 * List of available commands in enum form.
	 */
	enum Command {

		LIST, CHECKOUT, CHECKIN, REGISTER, DEREGISTER, INFO, QUIT, UNKNOWN,
	}

	/**
	 * Main function. Initializes system.
	 * 
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		System.out.println("Welcome!");
		// Create instance of LibraryManager
		JavaLibrarySystem libraryManager = new JavaLibrarySystem();

		// READ LIBRARY FROM BIN FILE IF EXISTS OTHERWISE CREATE NEW!
		File file = new File(FILE_PATH);
		if (file.exists()) {
			FileInputStream fis = new FileInputStream(FILE_PATH);
			ObjectInputStream ois = new ObjectInputStream(fis);
			libraryManager = (JavaLibrarySystem) ois.readObject();
			System.out.println("Current inventory:");
		}

//		libraryManager.customers.add(new Customer("Lars Larsson", "0736-832334", 00001));
//		libraryManager.customers.add(new Customer("Jan Persson", "0732-626273", 00002));
//
//		libraryManager.products.add(new Movie(1001, "Conan barbaren", 124, 7.2f, "Movie"));
//		System.out.println(libraryManager.customers.get(0));
//		System.out.println(libraryManager.customers.get(1));
//		System.out.println(libraryManager.products.get(0));
//		System.out.println(customer);
//
//		movie.setBorrowedBy(customer);
//		System.out.println(movie);

//		RUN SYSTEM
		libraryManager.runSystem();

		// SAVE BIN FILE AT THE END OF THE PROGRAM
		FileOutputStream fos = null;
		fos = new FileOutputStream(FILE_PATH);
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		oos.writeObject(libraryManager);
		System.out.println("Exiting library.");
		System.exit(0);

	}

	/**
	 * 
	 */
	public void runSystem() {

		listCommand();
		boolean running = true;
		Scanner scanner = new Scanner(System.in);

		while (running) {
			System.out.print("\nEnter command\n> ");
			String userInput = scanner.nextLine();
			Command command = parseCommand(userInput);

			if (command == Command.UNKNOWN) {
				System.out.println("Unknown command. Try again");
				continue;
			}

			String argument = parseArguments(userInput);

			switch (command) {
			case LIST:
				listCommand();
				break;
			case CHECKOUT:
				checkOutCommand(argument);
				break;
			case CHECKIN:
				checkInCommand(argument);
				break;
			case REGISTER:
				registerCommand();
				break;
			case DEREGISTER:
				deRegisterCommand(argument);
				break;
			case QUIT:
				running = false;
				break;
			case INFO:
				infoCommand(Integer.parseInt(argument));
				break;
			}

		}

		scanner.close();

	}

	private String parseArguments(String userInput) {

		String[] commandAndArguments = userInput.split(" ");
		String args = "";
		try {
			args = commandAndArguments[1];
		} catch (ArrayIndexOutOfBoundsException e) {

		}

		return args;
	}

	private void listCommand() {
		for (E prod : this.products) {
			System.out.println(prod);
		}

	}

	private void checkOutCommand(String argument) {

	}

	private void checkInCommand(String argument) {

	}

	private void registerCommand() {
		Scanner userReg = new Scanner(System.in);
		System.out.print("What do you want to register? A - Movie B - Book\n> ");
		/* bygger vidare på det nu */
		String in = userReg.nextLine().toUpperCase();
		if (in.equals("A")) {
			System.out.print("Enter product ID:\n> ");
			int productID = userReg.nextInt();
			userReg.nextLine();
			System.out.print("Enter title:\n> ");
			String title = userReg.nextLine();
			System.out.print("Enter value:\n> ");
			double value = userReg.nextDouble();
			userReg.nextLine();
			System.out.print("Enter running time:\n> ");
			int runningTime = userReg.nextInt();
			userReg.nextLine();
			System.out.print("Enter imdbRating:\n> ");
			float imdbRating = userReg.nextFloat();
			this.products.add((E) new Movie(productID, title, runningTime, imdbRating, "Movie", value));
		} else {
			System.out.print("Enter product ID:\n> ");
			int productID = userReg.nextInt();
			userReg.nextLine();
			System.out.print("Enter title:\n> ");
			String title = userReg.nextLine();
			System.out.print("Enter value:\n> ");
			double value = userReg.nextDouble();
			userReg.nextLine();
			System.out.print("Enter number of pages:\n> ");
			int numberOfPages = userReg.nextInt();
			userReg.nextLine();
			System.out.print("Enter publisher:\n> ");
			String publisher = userReg.nextLine();
			this.products.add((E) new Book(productID, title, numberOfPages, publisher, "Book", value));
		}
//		this.products.add(new Movie(1001, "Conan barbaren", 124, 7.2f, "Movie"));

	}

	private void deRegisterCommand(String argument) {

	}

	private void infoCommand(int productID) {

		for (int i = 0; i < this.products.size(); i++) {
			Product.showInfo = true;
			if (this.products.get(i).getArticleNumber() == productID) {
				if (this.products.get(i).getType().equals("Movie")) {
					System.out.println((Movie) this.products.get(i));
				} else {
					System.out.println((Book) this.products.get(i));
				}
				Product.showInfo = false;
			}
		}

	}

	private Command parseCommand(String userInput) {
		String commandString = userInput.split(" ")[0].toLowerCase();
		switch (commandString) {
		case "list":
			return Command.LIST;
		case "checkout":
			return Command.CHECKOUT;
		case "checkin":
			return Command.CHECKIN;
		case "register":
			return Command.REGISTER;
		case "deregister":
			return Command.DEREGISTER;
		case "info":
			return Command.INFO;
		case "quit":
			return Command.QUIT;
		default:
			return Command.UNKNOWN;
		}

	}

}