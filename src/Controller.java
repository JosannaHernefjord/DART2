import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Controller
{
	private Scanner scanner;
	private EmployeeLibrary employeeLibrary;
	private CustomerLibrary customerLibrary;
	private GameLibrary gameLibrary;
	private AlbumLibrary albumLibrary;
	private ManagerRoutine managerRoutine;
	private EmployeeRoutine employeeRoutine;
	private CustomerRoutine customerRoutine;

	public Controller()
	{
		scanner = new Scanner(System.in);
		employeeLibrary = new EmployeeLibrary();
		customerLibrary = new CustomerLibrary();
		gameLibrary = new GameLibrary();
		albumLibrary = new AlbumLibrary();
		managerRoutine = new ManagerRoutine();
		employeeRoutine = new EmployeeRoutine();
		customerRoutine = new CustomerRoutine();
	}

	public void runApplication()
	{
		String Input = "";

		//testSetup();

		while (!Input.equals("X"))
		{
			Print.printMainScreen();
			Input = scanner.nextLine();
			Input = Input.toUpperCase();    //To make small letters to capital letters

			switch (Input)
			{
				case "M":
					managerRoutine.RunManagerRoutine(employeeLibrary, gameLibrary, albumLibrary, customerLibrary);
					break;
				case "E":
					employeeRoutine.RunEmployeeRoutine(gameLibrary, customerLibrary, albumLibrary);
					break;
				case "C":
					customerRoutine.RunCustomerRoutine(gameLibrary, customerLibrary, albumLibrary);
					break;
				case "X":
					break;
				default:
					Print.printInvalidInput();
					break;
			}
		}

		scanner.close();
	}

	private void testSetup()
	{
		customerLibrary.addCustomer(1, "Adam", "123");
		customerLibrary.addCustomer(2, "Bob", "123");
		customerLibrary.addCustomer(3, "Linda", "123");
		gameLibrary.addGame(1, "Destiny 2", "FPS", 20);
		gameLibrary.addGame(2, "CS:GO", "FPS", 15);
		gameLibrary.addGame(3, "WOW", "RPG", 10);
		employeeLibrary.addEmployee(1, "Carl", 1992, "Java Street", 250000);
		albumLibrary.addAlbum(1, "Top hits", "Various", 2020, 5);
		albumLibrary.addAlbum(2, "Mozart", "Wolfgang Amadeus Mozart", 1779, 10);
		albumLibrary.addAlbum(3, "Rap God", "Eminem", 2010, 8);
	}

}
