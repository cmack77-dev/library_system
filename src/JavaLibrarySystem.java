
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

		LIST, CUSTOMERS, CHECKOUT, CHECKIN, REGISTER, DEREGISTER, INFO, QUIT, UNKNOWN,
	}

	/**
	 * Main function. Initializes system.
	 * 
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public static void main(String[] args) throws Exception{
		System.out.println("Welcome!");
		// Create instance of LibraryManager
		JavaLibrarySystem libraryManager = new JavaLibrarySystem();

		// READ LIBRARY FROM BIN FILE IF EXISTS.
			libraryManager = readFile(libraryManager);
			
		// RUN SYSTEM
		try {
			libraryManager.runSystem(libraryManager);
		} catch (IOException e1) {
			System.out.println("Caught exception: System wont run");
			e1.printStackTrace();
		}
		  

		// SAVE BIN FILE AT THE END OF THE PROGRAM.
		  try {
		    saveFile(libraryManager);
	      } catch (Exception e) {
	    	System.out.println("Caught exception: Could not save");
		    e.printStackTrace();
		    
	      }

		System.out.println("Exiting library.");
		System.exit(0);
	}

	/**
	 * Saves all to a .bin file.
	 * 
	 * @param libraryManager The JavaLibrarySystem object is passed to the method in
	 *                       order to save it to the bin file.
	 */
	private static void saveFile(JavaLibrarySystem libraryManager) {
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

	/**
	 * Reads data from bin file, if it exists, into the JavaLibrarySystem object.
	 * 
	 * @param libraryManager The JavaLibrarySystem object is passed to the method in
	 *                       order to initialize it with data from the .bin file.
	 * @return The initialized JavaLibrarySystem object.
	 */
	private static JavaLibrarySystem readFile(JavaLibrarySystem libraryManager)  {
		
		  File file = new File(FILE_PATH);
		  if (file.exists()) {
		  	  FileInputStream fis = null;
		  	  ObjectInputStream ois = null;
			try {
				fis = new FileInputStream(FILE_PATH);
			} catch (FileNotFoundException e1) {
				System.out.println("Exception caught: Filepath incorrect");
				e1.printStackTrace();
			}
			  
			try {
				ois = new ObjectInputStream(fis);
			} catch (IOException e1) {
				System.out.println("Exception caught:");
				e1.printStackTrace();
			}
				try {
					libraryManager = (JavaLibrarySystem) ois.readObject();
				} catch (ClassNotFoundException e) {
					System.out.println("Exception caught:");
					e.printStackTrace();
				} catch (IOException e) {
					System.out.println("Exception caught:");
					e.printStackTrace();
				} catch (Exception e) {
					System.out.println("Exception caught:");
					e.printStackTrace();
				}
			 
			  System.out.println("Current inventory:");
			  try {
				ois.close();
			} catch (IOException e) {
				System.out.println("Exception caught:");
				e.printStackTrace();
			}
			  return libraryManager;
		  
		  }
		 
		
		return libraryManager;
		
	}

	/**
	 * The method that handles the runtime part of the program. All commands are
	 * entered through this UI.
	 * @throws Exception 
	 */
	public void runSystem(JavaLibrarySystem<E> libraryManager) throws Exception{

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
			case CUSTOMERS:
				listCustomerCommand();
				break;
			case CHECKOUT:
				checkOutCommand(argument);
				sortCustomerList(libraryManager);
				saveFile(libraryManager);
				break;
			case CHECKIN:
				checkInCommand(argument);
				break;
			case REGISTER:
				registerCommand();
				sortProductList(libraryManager);
				saveFile(libraryManager);
				break;
			case DEREGISTER:
				deRegisterCommand(argument);
				sortProductList(libraryManager);
				saveFile(libraryManager);
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
	 * Method for listing all customers.
	 * 
	 * @param libraryManager
	 */
	private void listCustomerCommand() {
		for (Customer customer : this.customers) {
			System.out.println(customer);
		}
	}

	private void sortProductList(JavaLibrarySystem<E> libraryManager) {
		Collections.sort(libraryManager.products);
	}

	private void sortCustomerList(JavaLibrarySystem libraryManager) {
		Collections.sort(libraryManager.customers);
	}

	/**
	 * Parse arguments in entered data.
	 * 
	 * @param userInput data to be parsed.
	 * @return parsed arguments to be passed to a method.
	 */
	private String parseArguments(String userInput) {

		String[] commandAndArguments = userInput.split(" ");
		String args = "";
		if (commandAndArguments.length > 1) {
			try {
				args = commandAndArguments[1];
			} catch (ArrayIndexOutOfBoundsException e) {
				System.out.println("Array out of bounds. Too many arguments after command");
			}
		}

		return args;
	}

	/**
	 * Lists all products in library.
	 */
	private void listProductsCommand() {
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
	private void checkOutCommand(String argument) {
		int productID = Integer.parseInt(argument);

		for (int i = 0; i < this.products.size(); i++) {
			if (this.products.get(i).getArticleNumber() == productID) {
				if (this.products.get(i).getBorrowedBy() != null) {
					System.out.println("Cannot lend " + this.products.get(i).getProductName()
							+ " to another customer. It is already borrowed by " + this.products.get(i).getBorrowedBy()
							+ ".");
					return;
				}
			}
		}

		Scanner userReg = new Scanner(System.in);
		String customerName;
		String customerPhone;
		int customerID;
		Customer customer = null;
		System.out.println("Checkout for? New customer (a) or Returning customer (b)");
		String in = userReg.nextLine().toLowerCase();
		if (in.equals("a")) {
			System.out.println("Enter customer name:");
			customerName = userReg.nextLine();
			System.out.println("Enter customer phone number:");
			customerPhone = userReg.nextLine();

			// SET CUSTOMER ID
			customerID = this.customers.size() + 1;
			this.customers.add(new Customer(customerName, customerPhone, (customerID)));
			for (int n = 0; n < this.customers.size(); n++) {
				if (this.customers.get(n).getCustomerID() == customerID) {
					customer = this.customers.get(n);
				}
			}
		}
		if (in.equals("b")) {
			System.out.println("Enter customer ID:");
			customerID = userReg.nextInt();

			for (int i = 0; i < this.customers.size(); i++) {
				if (this.customers.get(i).getCustomerID() == customerID) {
					customerName = this.customers.get(i).getName();
					customerPhone = this.customers.get(i).getPhone();
					customer = this.customers.get(i);
				} else {
					System.out.println("No such customerID");
				}
			}
		}

		for (int i = 0; i < this.products.size(); i++) {
			if (this.products.get(i).getArticleNumber() == productID) {
				this.products.get(i).setBorrowedBy(customer);
				System.out.println("Succesfully lended " + this.products.get(i).getProductName() + " to "
						+ this.products.get(i).getBorrowedBy() + ".");
			}
		}
		userReg.close();

	}

	/**
	 * Lets a borrowed product be returned in stock.
	 * 
	 * @param argument holds productID for relevant product to be checked in.
	 */
	private void checkInCommand(String argument) {
		int productID = Integer.parseInt(argument);

		for (int i = 0; i < this.products.size(); i++) {
			if (this.products.get(i).getArticleNumber() == productID) {
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
	}

	/**
	 * Registers a new product.
	 */
	private void registerCommand() {
		Scanner userReg = new Scanner(System.in);
		System.out.print("What do you want to register? Movie (a), Book (b)\n> ");
		String in = userReg.nextLine().toLowerCase();
		if (in.equals("a")) {
			System.out.print("Enter product ID:\n> ");
			int productID = userReg.nextInt();
			try {
				int productIDTest = userReg.nextInt();
				
			} catch (InputMismatchException e) {
				System.out.println("Error: ID should contain numbers. Try again.");
			}
			while (!isUniqueID(productID)) {
				System.out.print("Error: product with ID already exists. Enter unique product ID:\n> ");
				productID = userReg.nextInt();
		    }

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
		} else if (in.equals("b")) {
			System.out.print("Enter product ID:\n> ");
			int productID = userReg.nextInt();
			while (!isUniqueID(productID)) {
				System.out.print("ID already exists. Enter unique product ID:\n> ");
				productID = userReg.nextInt();
			}
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
		} else {
			System.out.println("Try again!");
		}

	}

	/**
	 * Decides if the productID is unique.
	 * 
	 * @param productID holds the productID to be compared to existing productID's.
	 * @return true or false.
	 */
	private boolean isUniqueID(int productID) {
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
	private void deRegisterCommand(String argument) {
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
		} catch (NumberFormatException e){
			System.out.println("Error. Enter deregister and articlenumber. Try again");
			
		}
	}

	/**
	 * Prints all products in the library.
	 * 
	 * @param productID holds the productID to be compared to existing productID's.
	 */
	private void infoCommand(int productID) {
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
	 * Parse command in entered data.
	 * 
	 * @param userInput entered data to be parsed.
	 * @return parsed command to be passed to switch.
	 */
	private Command parseCommand(String userInput) {
		String commandString = userInput.split(" ")[0].toLowerCase();
		switch (commandString) {
		case "list":
			return Command.LIST;
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

}