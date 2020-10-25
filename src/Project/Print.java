package Project;

public class Print
{
	public static void printManagerScreen()
	{
		System.out.println("Manager Screen - Type one of the options below:");
		System.out.println("1. Add an employee \n2. View all employees \n3. Remove employee \n4. View employees net salary " +
				"\n5. Give out bonus to employees \n6. Print game rent history \n7. Print album rent history \n8. Print most profitable Game " +
				"\n9. Print most profitable Album \n10. Print most profitable Customer \n11. Print rent frequency of games and album \n12. Export transaction data to file " +
				"\n13. Load program data from file\n14. Return to Main Menu");
	}

	public static void printEmployeeScreen()
	{
		System.out.println("Employee Screen - Type one of the options below:");
		System.out.println("1. Register a game \n2. Remove a game \n3. Register a customer \n4. Remove a customer");
		System.out.println("5. Show total rent profit \n6. View all games \n7. Register a song album \n8. Remove song album \n9. Membership requests. \n10. Return to Main Menu");
	}

	public static void printCustomerScreen()
	{
		System.out.println("Customer Screen - Type one of the options below:");
		System.out.println("1. Rent a game \n2. Return game \n3. Rent a song album \n4. Return song album " +
				"\n5. Send request for membership upgrade \n6. View messages \n7. Send message \n8. Print games by rating " +
				"\n9. Print albums by rating \n10. Search for item \n11. Log out and return to Main Menu");
	}

	public static void printMainScreen()
	{
		System.out.println("Main menu");
		System.out.println("Welcome to DART, your good old game rental system. \nThe competition has no steam to keep up!");
		System.out.println("Please specify your role by entering one of the options given");
		System.out.println("1. Enter “M” for Manager \n2. Enter ”E” for Employee \n3. Enter ”C” for Customer \n4. Enter ”X” to exit system");

	}

	public static void printCreateEmployee()
	{
		System.out.println("Creating an Employee. Please type the Employee’s:");
	}

	public static void printRemoveEmployee()
	{
		System.out.print("Which employee should be removed? ID: ");
	}

	public static void printViewEmployeeNetSalary()
	{
		System.out.println("For which employee do you want to view net salary? ID:");
	}

	public static void printEmployeeBonus()
	{
		System.out.println("Which employee do you want to give a bonus to? ID:");
	}

	public static void printInvalidInput()
	{
		System.out.println("Invalid input.");
	}

	public static void printPasswordPrompt()
	{
		System.out.println("Please enter password:");
	}

	public static void printCreateGame()
	{
		System.out.println("Add a game. Please type the game's:");
	}

	public static void printRemoveGame()
	{
		System.out.println("Which game should be removed? ID: ");
	}

	public static void printCreateCustomer()
	{
		System.out.println("Creating an Customer. Please type the Customer’s:");
	}

	public static void printRemoveCustomer()
	{
		System.out.print("Which customer should be removed? ID: ");
	}

	public static void printCreateSongAlbum()
	{
		System.out.println("Add a song album. Please type the song album's:");
	}

	public static void printRemoveSongAlbum()
	{
		System.out.println("Which song album should be removed? ID: ");
	}

	public static void printInvalidPassword()
	{
		System.out.println("Invalid password.");
		System.out.println();
	}
}
