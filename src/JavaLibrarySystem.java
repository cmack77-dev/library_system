import java.util.Scanner;

public class JavaLibrarySystem {
	
	 enum Command {
			
			LIST,
			CHECKOUT,
			CHECKIN,
			REGISTER,
			DEREGISTER,
			INFO,
			QUIT,
			UNKNOWN,
		}


	enum Command {

		LIST, CHECKOUT, CHECKIN, REGISTER, DEREGISTER, INFO, QUIT, UNKNOWN,
	}

	public static void main(String[] args) {
		System.out.println("Welcome!");
<<<<<<< HEAD
=======
		
		JavaLibrarySystem libraryManager = new JavaLibrarySystem();
		JavaLibrarySystem.startSystem();
		/* lÃ¶ses efter hand vi fÃ¥r pli pÃ¥ resten*/
		
>>>>>>> branch 'master' of https://github.com/cmack77-dev/library_system.git

		JavaLibrarySystem libraryManager = new JavaLibrarySystem();
		JavaLibrarySystem.startSystem();
		/* löses efter hand vi får pli på resten */

		Customer customer = new Customer("Lars Larsson", "0736-832334", 00001);

		Movie movie = new Movie(1001, "Conan barbaren", 124, 7.2f, "Movie");
		System.out.println(movie);
		System.out.println(customer);

		movie.setBorrowedBy(customer);
		System.out.println(movie);

	}

	public void startSystem() {

		boolean running = true;
		Scanner scanner = new Scanner(System.in);

		while (running) {

			String userInput = scanner.nextLine();
			Command command = parseCommand(userInput);

			if (command == Command.QUIT) {
				running = false;
				System.out.println("Exiting library.");
				continue;

			} else if (command == Command.UNKNOWN) {
				System.out.println("Unknown command. Try again");
				continue;
			}

			String[] arguments = parseArguments(userInput);

			switch (command) {
			case LIST:
				listCommand(arguments);
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
			case INFO:
				infoCommand(arguments);
				break;
			}

		}

		scanner.close();

	}

	private void listCommand() {
		System.out.println(" /* listnamn */ ");
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
		String commandString = userInput.split(" ")[0];
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
		public void startSystem() {
			
			boolean running = true;
			Scanner scanner = new Scanner(System.in); 
			
			while (running) {
				
				String userInput = scanner.nextLine();
				Command command = parseCommand(userInput);
				
				if (command == Command.QUIT) {
					running = false;
					System.out.println("Exiting library.");
					continue;
					
				} else if (command == Command.UNKNOWN) {
					System.out.println("Unknown command. Try again");
					continue;
				}
				
	            String[] arguments = parseArguments(userInput);
				
				switch (command) {
				case LIST:
					listCommand(arguments);
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
				case INFO:
					infoCommand(arguments);
					break;
				}
				
			}
			
			scanner.close();
				
		}
		
		
		private void listCommand() {
			System.out.println(" /* listnamn */ ");
		}
		private void checkOutCommand(String[] arguments) {
			
		}
		private void checkInCommand(String[] arguments) {
		
		}
		private void registerCommand(String[] arguments) {
			Scanner userReg = new Scanner(System.in); 
			System.out.println("What do you want to register? A - Movie B - Book");
			 /* bygger vidare pÃ¥ det nu*/
			
		}
		private void deRegisterCommand(String[] arguments) {
			
		}
		private void infoCommand() {
			
		}

		
		
			private Command parseCommand(String userInput) {
		    	String commandString = userInput.split(" ")[0];
		    	switch(commandString) {
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

}
