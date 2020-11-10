
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.*;

/**
 * 
 * Java Library System for management of movies and books.
 * 
 * @author Markus Johanssson and Stefan Kusmuk
 * 
 */
public class JavaLibrarySystem<E extends Product> implements Serializable {
	/**
	 * Serialversion.
	 */
	private static final long serialVersionUID = 1L;

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

		LIST, HELP, CUSTOMERS, CHECKOUT, CHECKIN, REGISTER, DEREGISTER, INFO, QUIT, UNKNOWN,
	}

	/**
	 * Main function. Initializes system.
	 * 
	 * @throws IOException
	 * 
	 */
	public static void main(String[] args) {
		System.out.println("Welcome!");
		System.out.println("\nFor instructions on how to use the program enter help.\n");
		// Create instance of LibraryManager
		JavaLibrarySystem libraryManager = new JavaLibrarySystem();

		// READ LIBRARY FROM BIN FILE IF EXISTS.
		libraryManager = readFile(libraryManager);

		// RUN SYSTEM
		try {
			libraryManager.runSystem(libraryManager);
		} catch (Exception e1) {
			System.out.println("Caught exception: System won't run");
		}

		// SAVE BIN FILE AT THE END OF THE PROGRAM.
		try {
			saveFile(libraryManager);
		} catch (Exception e) {
			System.out.println("Caught exception: Could't save to file");
			e.printStackTrace();
		}

		System.out.println("Good bye!");
		System.exit(0);
	}

	/**
	 * Parse command in entered data.
	 * 
	 * @param userInput entered data to be parsed.
	 * @return parsed command to be passed to switch.
	 */
	public Command parseCommand(String userInput) {
		String commandString = userInput.split(" ")[0].toLowerCase();
		switch (commandString) {
		case "list":
			return Command.LIST;
		case "help":
			return Command.HELP; // Added function for printing out instructions for the program.
		case "customers":
			return Command.CUSTOMERS; // Added function for retrieving list of customers.
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

	/**
	 * Parse arguments in entered data.
	 * 
	 * @param userInput data to be parsed.
	 * @return parsed arguments to be passed to a method.
	 */
	public String parseArguments(String userInput) {
		String[] commandAndArguments = userInput.split(" ");
		String args = "";

		try {
			args = commandAndArguments[1];
		} catch (ArrayIndexOutOfBoundsException e) {
//			System.out.println("Error! Try again");
			args = "0";
		}

		return args;
	}

	/**
	 * The method that handles the runtime part of the program. All commands are
	 * entered through this UI.
	 */
	public void runSystem(JavaLibrarySystem<E> libraryManager) {
		listProductsCommand();
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
				listProductsCommand();
				break;
			case HELP:
				help();
				break;
			case CUSTOMERS:
				listCustomerCommand();
				break;
			case CHECKOUT:
				try {
					checkOutCommand(argument, libraryManager);
				} catch (Exception e) {
					System.out.println("IO Exception:");
					e.printStackTrace();
				}
				break;
			case CHECKIN:
				checkInCommand(argument);
				break;
			case REGISTER:
				try {
					registerCommand(libraryManager);
				} catch (Exception e) {
					System.out.println("IO Exception:");
					e.printStackTrace();
				}
				break;
			case DEREGISTER:
				deRegisterCommand(argument, libraryManager);
				break;
			case QUIT:
				running = false;
				break;
			case INFO:
				infoCommand(Integer.parseInt(argument));
				break;
			case UNKNOWN:
				break;
			}
		}
		scanner.close();
	}

	/**
	 * Reads in the readme file for instructions on the screen.
	 */
	public void help() {

		File txtObject = new File("Help.txt");
		Scanner reader = null;
		String line = "";
		try {
			reader = new Scanner(txtObject);
		} catch (FileNotFoundException e) {
			System.out.println("Error: File not found");
			e.printStackTrace();
		}
		while (reader.hasNextLine()) {
			System.out.println(reader.nextLine());

		}
	}

	/**
	 * Method for listing all customers.
	 * 
	 * @param libraryManager
	 */
	public void listCustomerCommand() {
		for (Customer customer : this.customers) {
			System.out.println(customer);
		}
	}

	public void sortProductList(JavaLibrarySystem<E> libraryManager) {
		Collections.sort(libraryManager.products);
	}

	public void sortCustomerList(JavaLibrarySystem<E> libraryManager) {
		Collections.sort(libraryManager.customers);
	}

	/**
	 * Lists all products in library.
	 */
	public void listProductsCommand() {
		for (E prod : this.products) {
			System.out.println(prod);
		}
	}

	/**
	 * Sets a specified product to borrowed by a specific customer who already
	 * exists or gets created by user.
	 * 
	 * @param argument holds the productID to be checked out.
	 */
	public void checkOutCommand(String argument, JavaLibrarySystem<E> libraryManager) {
		int productID = Integer.parseInt(argument);
		boolean exists = false;
		for (int i = 0; i < this.products.size(); i++) {
			if (this.products.get(i).getArticleNumber() == productID) {
				exists = true;
				if (this.products.get(i).getBorrowedBy() != null) {
					System.out.println("Cannot lend " + this.products.get(i).getProductName()
							+ " to another customer. It is already borrowed by " + this.products.get(i).getBorrowedBy()
							+ ".");
					return;
				}

			}
		}
		if (exists) {
			Scanner userReg = new Scanner(System.in);
			String customerName;
			String customerPhone;
			int customerID;
			Customer customer = null;
			System.out.print("Checkout for? New customer (a) or Returning customer (b)\n> ");
			String in = userReg.nextLine().toLowerCase();

			if (in.equals("a")) {
				System.out.print("Enter customer name:\n> ");
				customerName = userReg.nextLine();
				System.out.print("Enter customer phone number:\n> ");
				customerPhone = userReg.nextLine();

				// SET CUSTOMER ID
				customerID = this.customers.size() + 1;
				this.customers.add(new Customer(customerName, customerPhone, (customerID)));
				for (int n = 0; n < this.customers.size(); n++) {
					if (this.customers.get(n).getCustomerID() == customerID) {
						customer = this.customers.get(n);
					}
				}
			} else if (in.equals("b")) {
				System.out.print("Enter customer ID:\n> ");
				customerID = userReg.nextInt();
				boolean customerExists = false;
				for (int i = 0; i < this.customers.size(); i++) {
					if (this.customers.get(i).getCustomerID() == customerID) {
						customerName = this.customers.get(i).getName();
						customerPhone = this.customers.get(i).getPhone();
						customer = this.customers.get(i);
						customerExists = true;
					}
					if (!customerExists) {
						System.out.println("No such customerID");
						return;
					}
				}
			} else if (in != "a" && in != "b") {
				System.out.println("Error! Enter a or b");
				return;
			}

			for (int i = 0; i < this.products.size(); i++) {
				if (this.products.get(i).getArticleNumber() == productID) {
					this.products.get(i).setBorrowedBy(customer);
					System.out.println("Succesfully lended " + this.products.get(i).getProductName() + " to "
							+ this.products.get(i).getBorrowedBy() + ".");
				}
			}
			sortCustomerList(libraryManager);
			saveFile(libraryManager);
		} else {
			System.out.println("Product with article number " + productID + " doesn't exist.");
			return;
		}
	}

	/**
	 * Lets a borrowed product be returned in stock.
	 * 
	 * @param argument holds productID for relevant product to be checke in.
	 */
	public void checkInCommand(String argument) {
		int productID = Integer.parseInt(argument);
		boolean exists = false;
		for (int i = 0; i < this.products.size(); i++) {
			if (this.products.get(i).getArticleNumber() == productID) {
				exists = true;
				if (this.products.get(i).getBorrowedBy() == null) {
					System.out.println("Cannot return " + this.products.get(i).getProductName()
							+ ". It is not borrowed by any customer.");
					return;
				} else {
					Customer customer = this.products.get(i).getBorrowedBy();
					this.products.get(i).setBorrowedBy(null);
					System.out.println("Succesfully returned " + this.products.get(i).getProductName() + " from "
							+ customer + ".");
				}
			}
		}
		if (!exists) {
			System.out.println("Product with article number " + productID + " doesn't exist.");
			return;
		}
	}

	/**
	 * Registers a new product.
	 * 
	 * @param libraryManager
	 * 
	 */
	public void registerCommand(JavaLibrarySystem<E> libraryManager) {
		Scanner userReg = new Scanner(System.in);
		System.out.print("What do you want to register? Movie (a), Book (b)\n> ");
		String in = userReg.nextLine().toLowerCase();
		if (in.equals("a")) {
			System.out.print("Enter product ID (numbers) :\n> ");
			int productID;
			try {
				productID = userReg.nextInt();
			} catch (InputMismatchException e) {
				System.out.println("Error: Product ID should only contain numbers. Try again!");
				System.out.println("\n Enter command (For instructions on how to use the program enter help.)\n");
				return;
			}
			while (!isUniqueID(productID)) {
				System.out.print("Error: product with ID already exists. Enter unique product ID:\n> ");
				productID = userReg.nextInt();
			}

			userReg.nextLine();
			System.out.print("Enter title:\n> ");
			String title = userReg.nextLine();
			System.out.print("Enter value (numbers) :\n> ");
			double value;
			try {
				value = userReg.nextDouble();
			} catch (InputMismatchException e) {
				System.out.println("Error: Value should only contain numbers (price). Try again!");
				System.out.println("\n Enter command (For instructions on how to use the program enter help.)\n");
				return;
			}
			userReg.nextLine();
			System.out.print("Enter running time (minutes) :\n> ");
			int runningTime;
			try {
				runningTime = userReg.nextInt();
			} catch (InputMismatchException e) {
				System.out.println("Error: Running time should only contain numbers (minutes). Try again!");
				System.out.println("\n Enter command (For instructions on how to use the program enter help.)\n");
				return;
			}
			userReg.nextLine();
			System.out.print("Enter imdbRating (numbers) :\n> ");
			float imdbRating;
			try {
				imdbRating = userReg.nextFloat();
			} catch (InputMismatchException e) {
				System.out.println("Error: Rating should only contain numbers. Try again!");
				System.out.println("\n Enter command (For instructions on how to use the program enter help.)\n");
				return;
			}
			this.products.add((E) new Movie(productID, title, runningTime, imdbRating, "Movie", value));
			System.out.println("Successfully registered " + title + "!");

		} else if (in.equals("b")) {
			System.out.print("Enter product ID (numbers) :\n> ");
			int productID;
			try {
				productID = userReg.nextInt();
			} catch (InputMismatchException e) {
				System.out.println("Error: Product ID should only contain numbers. Try again!");
				System.out.println("\n Enter command (For instructions on how to use the program enter help.)\n");
				return;
			}
			while (!isUniqueID(productID)) {
				System.out.print("ID already exists. Enter unique product ID:\n> ");
				productID = userReg.nextInt();
			}
			userReg.nextLine();
			System.out.print("Enter title:\n> ");
			String title = userReg.nextLine();
			System.out.print("Enter value (numbers) :\n> ");
			double value;
			try {
				value = userReg.nextDouble();
			} catch (InputMismatchException e) {
				System.out.println("Error: Value should only contain numbers (price). Try again!");
				System.out.println("\n Enter command (For instructions on how to use the program enter help.)\n");
				return;
			}
			userReg.nextLine();
			System.out.print("Enter number of pages:\n> ");
			int numberOfPages;
			try {
				numberOfPages = userReg.nextInt();
			} catch (InputMismatchException e) {
				System.out.println("Error: Pages should only contain numbers. Try again!");
				System.out.println("\n Enter command (For instructions on how to use the program enter help.)\n");
				return;
			}
			userReg.nextLine();
			System.out.print("Enter publisher:\n> ");
			String publisher = userReg.nextLine();
			this.products.add((E) new Book(productID, title, numberOfPages, publisher, "Book", value));
			System.out.println("Successfully registered " + title + "!");
		} else {
			System.out.println("Try again!");
		}
		sortProductList(libraryManager);
		saveFile(libraryManager);

	}

	/**
	 * Decides if the productID is unique.
	 * 
	 * @param productID holds the productID to be compared to existing productID's.
	 * @return true or false.
	 */
	public boolean isUniqueID(int productID) {
		boolean unique = true;
		for (int i = 0; i < this.products.size(); i++) {
			if (this.products.get(i).getArticleNumber() == productID) {
				unique = false;
			}
		}
		return unique;
	}

	/**
	 * Deletes a product from library.
	 * 
	 * @param argument       holds the productID of product to be deleted.
	 * @param libraryManager
	 */
	public void deRegisterCommand(String argument, JavaLibrarySystem<E> libraryManager) {
		try {
			int productID = Integer.parseInt(argument);

			for (int i = 0; i < this.products.size(); i++) {
				if (this.products.get(i).getArticleNumber() == productID) {
					if (this.products.get(i).getBorrowedBy() != null) {
						System.out.println("Cannot delete " + this.products.get(i).getProductName()
								+ ". It is borrowed by \" + this.products.get(i).getBorrowedBy()\r\n" + ".");
						return;
					} else {
						this.products.remove(i);
					}
				}
			}
		} catch (NumberFormatException e) {
			System.out.println("Error. Enter deregister and an articlenumber. Try again");

		}
		sortProductList(libraryManager);
		saveFile(libraryManager);
	}

	/**
	 * Prints all products in the library.
	 * 
	 * @param productID holds the productID to be compared to existing productID's.
	 */
	public void infoCommand(int productID) {
		boolean noSuchProductID = true;
		for (int i = 0; i < this.products.size(); i++) {
			Product.showInfo = true;

			if (this.products.get(i).getArticleNumber() == productID) {
				System.out.println(this.products.get(i));
				noSuchProductID = false;
				Product.showInfo = false;
			}
		}
		if (noSuchProductID) {
			System.out.println("No product with id " + productID + " registered.");
		}
	}

	/**
	 * Reads data from bin file, if it exists, into the JavaLibrarySystem object.
	 * 
	 * @param libraryManager The JavaLibrarySystem object is passed to the method in
	 *                       order to initialize it with data from the .bin file.
	 * @return The initialized JavaLibrarySystem object.
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public static JavaLibrarySystem readFile(JavaLibrarySystem libraryManager) {

		File file = new File(FILE_PATH);
		if (file.exists()) {
			FileInputStream fis = null;
			ObjectInputStream ois = null;
			try {
				fis = new FileInputStream(FILE_PATH);
			} catch (FileNotFoundException e1) {
				System.out.println("Exception caught: File path incorrect");
				e1.printStackTrace();
			}

			try {
				ois = new ObjectInputStream(fis);
			} catch (IOException e1) {
				System.out.println("Exception caught, printing error:");
				e1.printStackTrace();
			}
			try {
				libraryManager = (JavaLibrarySystem) ois.readObject();
			} catch (ClassNotFoundException e) {
				System.out.println("Exception caught: Class is missing or corrupt");
				e.printStackTrace();
			} catch (IOException e) {
				System.out.println("Exception caught, printing error:");
				e.printStackTrace();
			}

			System.out.println("Current inventory:");
			try {
				ois.close();
			} catch (IOException e) {
				System.out.println("Exception caught, printing error:");
				e.printStackTrace();
			}
			return libraryManager;

		}

		return libraryManager;

	}

	/**
	 * Saves all to a .bin file.
	 * 
	 * @param libraryManager The JavaLibrarySystem object is passed to the method in
	 *                       order to save it to the bin file.
	 */
	public static void saveFile(JavaLibrarySystem libraryManager) {
		try {
			FileOutputStream fos = null;
			fos = new FileOutputStream(FILE_PATH);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(libraryManager);
			oos.close();
		} catch (IOException e) {
			System.out.println("Caught exception:");
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println("Caught exception:");
			e.printStackTrace();
		}
	}

}