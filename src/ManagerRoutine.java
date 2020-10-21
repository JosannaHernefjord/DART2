import java.util.Scanner;

public class ManagerRoutine {

	private EmployeeLibrary employeeLibrary;
	private GameLibrary gameLibrary;
	private AlbumLibrary albumLibrary;
	private CustomerLibrary customerLibrary;

	Scanner scanner = new Scanner(System.in);

	public void RunManagerRoutine(EmployeeLibrary employeeLibrary, GameLibrary gameLibrary, AlbumLibrary albumLibrary, CustomerLibrary customerLibrary)
	{
		this.employeeLibrary = employeeLibrary;
		this.gameLibrary = gameLibrary;
		this.albumLibrary = albumLibrary;
		this.customerLibrary = customerLibrary;

		Print.printPasswordPrompt();
		String input = scanner.nextLine();

		if (input.equals("admin1234"))
		{
			while (!input.equals("12"))
			{
				Print.printManagerScreen();
				input = scanner.nextLine();
				int id;
				switch (input)
				{
					case "1":
						addNewEmployee();
						break;

					case "2":
						System.out.println("---------------EMPLOYEES---------------");
						employeeLibrary.printAllEmployees();
						System.out.println("---------------------------------------");
						break;

					case "3":
						employeeLibrary.printAllEmployees();
						Print.printRemoveEmployee();
						id = scanner.nextInt();
						scanner.nextLine();
						employeeLibrary.removeEmployee(id);
						break;

					case "4":
						employeeLibrary.printAllEmployees();
						Print.printViewEmployeeNetSalary();
						id = scanner.nextInt();
						scanner.nextLine();
						employeeLibrary.printSalary(id);
						break;

					case "5":
						employeeLibrary.printAllEmployees();
						Print.printEmployeeBonus();
						id = scanner.nextInt();
						scanner.nextLine();
						employeeLibrary.printBonus(id);
						break;

					case "6":
						System.out.println("-------------Game rent history------------");
						gameLibrary.printAllGames();
						System.out.println("------------------------------------------");
						break;

					case "7":
						System.out.println("-------------Album rent history------------");
						albumLibrary.printAlbumsHistory();
						System.out.println("-------------------------------------------");
						break;

					case "8":
						System.out.println("--------Print most profitable game--------");
						gameLibrary.printMostProfitable();
						System.out.println("------------------------------------------");
						break;

					case "9":
						System.out.println("--------Print most profitable Album--------");
						albumLibrary.printMostProfitable();
						System.out.println("-------------------------------------------");
						break;

					case"10":
						System.out.println("-------Print most profitable Customer------");
						customerLibrary.printMostProfitable();
						System.out.println("-------------------------------------------");
						break;

					case"11":
						System.out.println("-----Print rent frequency of games and album -----");
						gameLibrary.printRentFrequency();
						albumLibrary.printRentFrequency();
						System.out.println("--------------------------------------------------");
						break;

					default:
						if (!input.equals("12"))
						{
							Print.printInvalidInput();
						}
						break;
				}
			}
		}
		else Print.printInvalidPassword();
	}

	public void addNewEmployee()
	{
		Print.printCreateEmployee();

		System.out.print("ID: ");
		int id = scanner.nextInt();
		scanner.nextLine();

		System.out.print("Name: ");
		String name = scanner.nextLine();

		System.out.print("Birth year: ");
		int birthYear = scanner.nextInt();
		scanner.nextLine();

		System.out.print("Address: ");
		String address = scanner.nextLine();

		System.out.print("Monthly gross salary: ");
		double grossSalary = scanner.nextDouble();
		scanner.nextLine();

		employeeLibrary.addEmployee(id, name, birthYear, address, grossSalary);
	}

}
