Files needed to build:

JavaLibrarySystem.java	(main program)
Book.java
Customer.java
Movie.java
Product.java

library.bin
Help.txt


Other relevant files included in project:

README.md
UML library system.mdj



User instructions:
Command		Argument			Description
list		no arguments		Prints article number, title and if in stock or borrowed for all registered products. 
								If a product is borrowed it shows by who. Sorted by articlenumber.
help		no arguments		Prints a help screen with all available commands.
Customers	no arguments		Lists all customers sorted by article number.
checkout	<articleNumber>		Dialogue where you choose if product is to be borrowed by new or existing customer. 
								If new:  enter name and phone number. If existing: enter customerID. 
								Sets the product with <articleNumber> to borrowed to entered customer. 
								After this the product is set to borrowed and no longer in stock.
checkin		<articleNumber>		Puts a borrowed product with <articleNumber> back in stock. 
register	inga argument		Starts a dialogue where you can register a new product in to the system.
deregister	<articleNumber>		Deregister the product with <articleNumber> from the system.
info		<articleNumber>		Prints all info about the product with <articleNumber>.
quit		no arguments		Closes the program



Authors:
Markus Johansson
Stefan Kusmuk