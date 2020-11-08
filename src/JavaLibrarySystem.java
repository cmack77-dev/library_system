
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
		// Create instance of LibraryManager
		JavaLibrarySystem libraryManager = new JavaLibrarySystem();

		// READ LIBRARY FROM BIN FILE IF EXISTS OTHERWISE CREATE NEW!
		File file = new File(FILE_PATH);
		if (file.exists()) {
			FileInputStream fis = new FileInputStream(FILE_PATH);
			ObjectInputStream ois = new ObjectInputStream(fis);
			libraryManager = (JavaLibrarySystem) ois.readObject();
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

	/*
	 * 
	 */
	public void runSystem() {
		System.out.println("Welcome!");
		System.out.println("Current inventory:");
		listCommand();

		boolean running = true;
		Scanner scanner = new Scanner(System.in);

		while (running) {

			String userInput = scanner.nextLine();
			Command command = parseCommand(userInput);

			if (command == Command.UNKNOWN) {
				System.out.println("Unknown command. Try again");
				continue;
			}

			String[] arguments = parseArguments(userInput);

			switch (command) {
			case LIST:
				listCommand();
				break;
			case CHECKOUT:
				checkOutCommand(arguments);
				break;
			case CHECKIN:
				checkInCommand(arguments);
				break;
			case REGISTER:
				registerCommand(arguments);
				break;
			case DEREGISTER:
				deRegisterCommand(arguments);
				break;
			case QUIT:
				running = false;
				break;
			case INFO:
				infoCommand();
				break;
			}

		}

		scanner.close();

	}

	private String[] parseArguments(String userInput) {

		return null;
	}

	private void listCommand() {
		for (E prod : this.products) {
			System.out.println(prod);
		}

	}

	private void checkOutCommand(String[] arguments) {

	}

	private void checkInCommand(String[] arguments) {

	}

	private void registerCommand(String[] arguments) {
		Scanner userReg = new Scanner(System.in);
		System.out.println("What do you want to register? A - Movie B - Book");
		/* bygger vidare på det nu */

	}

	private void deRegisterCommand(String[] arguments) {

	}

	private void infoCommand() {

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