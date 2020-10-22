import java.io.*;
import java.util.Locale;
import java.util.Scanner;

public class ManagerRoutine
{

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
			while (!input.equals("14"))
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

					case "10":
						System.out.println("-------Print most profitable Customer------");
						customerLibrary.printMostProfitable();
						System.out.println("-------------------------------------------");
						break;

					case "11":
						System.out.println("-----Print rent frequency of games and album -----");
						gameLibrary.printRentFrequency();
						albumLibrary.printRentFrequency();
						System.out.println("--------------------------------------------------");
						break;

					case "12":
					{
						try
						{
							saveData();
						} catch (Exception e)
						{
							System.out.println("Unable to save data. Is path correct?");
						}
						break;
					}

					case "13":
						loadData();
						break;

					default:
						if (!input.equals("14"))
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

	public void loadData()
	{
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter path and file name for data file:");
		String path = scanner.nextLine();
		File inputFile = new File(path);

		try
		{
			scanner = new Scanner(inputFile); // This is where the exception might occur if the file is invalid.
			scanner.useDelimiter("\n");
			System.out.println("File found. Read operation started.");
		} catch (FileNotFoundException e)
		{
			System.out.println("Failed to open file. Make sure path is entered correctly.");
			return;
		}

		while (scanner.hasNext())
		{
			String entry = scanner.next();

			Scanner entryScanner = new Scanner(entry);
			entryScanner.useDelimiter(";");
			entryScanner.useLocale(Locale.US);

			String dataType = entryScanner.next();

			switch (dataType)
			{
				case "employee":
				{
					int id = entryScanner.nextInt();
					String name = entryScanner.next();
					int birthYear = entryScanner.nextInt();
					String address = entryScanner.next();
					double grossSalary = entryScanner.nextDouble();
					employeeLibrary.addEmployee(id, name, birthYear, address, grossSalary);
					break;
				}
				case "customer":
				{
					int id = entryScanner.nextInt();
					String name = entryScanner.next();
					String password = entryScanner.next();
					customerLibrary.addCustomer(id, name, password);
					break;
				}
				case "game":
				{
					int id = entryScanner.nextInt();
					String title = entryScanner.next();
					String genre = entryScanner.next();
					double dailyRent = entryScanner.nextDouble();
					gameLibrary.addGame(id, title, genre, dailyRent);
					break;
				}
				case "album":
				{
					int id = entryScanner.nextInt();
					String title = entryScanner.next();
					String artist = entryScanner.next();
					int releaseYear = entryScanner.nextInt();
					double dailyRent = entryScanner.nextDouble();
					albumLibrary.addAlbum(id, title, artist, releaseYear, dailyRent);
					break;
				}
				default:
					System.out.println("Input file incorrectly formatted.");
					break;
			}
		}
	}

	public void saveData() throws IOException
	{
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter path and file name of where to save data:");
		String path = scanner.nextLine();
		File outputFile = new File(path);

		if (outputFile.createNewFile())
		{
			System.out.println("Output file created.");
		}
		else
		{
			System.out.println("Output file already exists. This operation will " +
					"overwrite existing data. Continue? (y/n)");

			String answer = scanner.nextLine();

			if (!answer.toLowerCase().equals("y"))
			{
				System.out.println("Aborting.");
				return;
			}
			else
			{
				System.out.println("Overwriting...");
			}
		}

		FileWriter fileWriter = new FileWriter(outputFile);

		for (Item item : albumLibrary.itemList)
		{
			Album album = (Album)item;
			fileWriter.write(album.dataEntryString() + "\n");
		}

		for (Item item : gameLibrary.itemList)
		{
			Game game = (Game)item;
			fileWriter.write(game.dataEntryString() + "\n");
		}

		for (Customer customer : customerLibrary.getCustomerList())
		{
			fileWriter.write(customer.dataEntryString() + "\n");
		}

		for (Employee employee : employeeLibrary.getEmployeeList())
		{
			fileWriter.write(employee.dataEntryString() + "\n");
		}

		fileWriter.close();
		System.out.println("Data saved!");
	}
}
