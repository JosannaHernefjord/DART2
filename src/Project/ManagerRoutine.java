package Project;

import java.io.*;
import java.util.Locale;
import java.util.Scanner;

public class ManagerRoutine
{
	//---------INSTANCE VARIABLES-----------
	private EmployeeLibrary employeeLibrary;
	private GameLibrary gameLibrary;
	private AlbumLibrary albumLibrary;
	private CustomerLibrary customerLibrary;

	Scanner scanner = new Scanner(System.in);

	public void RunManagerRoutine(EmployeeLibrary employeeLibrary, GameLibrary gameLibrary,
								  AlbumLibrary albumLibrary, CustomerLibrary customerLibrary)
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
						try
						{
							addNewEmployee();
						}
						catch(Exception e)
						{
							Print.printInvalidInput();
						}
						break;

					case "2":
						System.out.println("---------------EMPLOYEES---------------");
						employeeLibrary.printAllEmployees();
						System.out.println("---------------------------------------");
						break;

					case "3":
						System.out.println("---------------EMPLOYEES---------------");
						employeeLibrary.printAllEmployees();
						System.out.println("---------------------------------------");
						Print.printRemoveEmployee();
						id = scanner.nextInt();
						scanner.nextLine();
						employeeLibrary.removeEmployee(id);
						break;

					case "4":
						System.out.println("---------------EMPLOYEES---------------");
						employeeLibrary.printAllEmployees();
						System.out.println("---------------------------------------");
						Print.printViewEmployeeNetSalary();
						id = scanner.nextInt();
						scanner.nextLine();
						employeeLibrary.printSalary(id);
						break;

					case "5":
						System.out.println("---------------EMPLOYEES---------------");
						employeeLibrary.printAllEmployees();
						System.out.println("---------------------------------------");
						Print.printEmployeeBonus();
						id = scanner.nextInt();
						scanner.nextLine();
						employeeLibrary.printBonus(id);
						break;

					case "6":
						System.out.println("-------------Game rent history------------");
						gameLibrary.printGamesHistory();
						System.out.println("------------------------------------------");
						break;

					case "7":
						System.out.println("-------------Album rent history------------");
						albumLibrary.printAlbumsHistory();
						System.out.println("-------------------------------------------");
						break;

					case "8":
						System.out.println("-----------Most profitable game----------");
						gameLibrary.printMostProfitable();
						System.out.println("------------------------------------------");
						break;

					case "9":
						System.out.println("-----------Most profitable album----------");
						albumLibrary.printMostProfitable();
						System.out.println("-------------------------------------------");
						break;

					case "10":
						System.out.println("---------Most profitable customer---------");
						customerLibrary.printMostProfitable();
						System.out.println("-------------------------------------------");
						break;

					case "11":
						System.out.println("----Rent frequency for games and albums----");
						gameLibrary.printRentFrequency();
						albumLibrary.printRentFrequency();
						System.out.println("--------------------------------------------");
						break;

					case "12":
					{
						try
						{
							exportTransactionData();
						} catch (Exception e)
						{
							System.out.println("Unable to export data. Check if path is correct.");
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
			String entry = scanner.next(); //reading the first line in the file and saving to entry

			Scanner entryScanner = new Scanner(entry); //Creating a scanner that overlooks the entry line
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

	public void exportTransactionData() throws IOException
	{
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter path and file name of where to save data:");
		String path = scanner.nextLine();
		File outputFile = new File(path); //Creating a file with the path address

		if (outputFile.createNewFile()) //If the address/path is empty, we can create an outputFile
		{
			System.out.println("Output file created.");
		}
		else //If it already exists a file at that address/path
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

		FileWriter fileWriter = new FileWriter(outputFile); //Creating a FileWriter that writes in to this new file

		for (Item item : albumLibrary.itemList)
		{
			Album album = (Album)item; //Casting/parse to be sure that we get lib.Album in item

			for(Review review : item.getReviews())
			{
				double profit = review.getDaysRented()*album.getDailyRent();
				String data = review.getCustomerId() + ";" + album.getId() + ";" + album.getTitle() + ";" + profit + "\n";
				fileWriter.write(data);
			}
		}

		for (Item item : gameLibrary.itemList)
		{
			Game game = (Game)item; //Casting/parse to be sure that we get lib.Game in item

			for(Review review : item.getReviews())
			{
				double profit = review.getDaysRented()*game.getDailyRent();
				String data = review.getCustomerId() + ";" + game.getId() + ";" + game.getTitle() + ";" + profit + "\n";
				fileWriter.write(data);
			}
		}

		fileWriter.close();
		System.out.println("Data exported!");
	}
}
