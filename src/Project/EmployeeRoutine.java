package Project;

import Project.Database.AlbumLibrary;
import Project.Database.Customer;
import Project.Database.CustomerLibrary;
import Project.Database.GameLibrary;

import java.util.Scanner;

public class EmployeeRoutine
{
	//---------INSTANCE VARIABLES-----------
	private GameLibrary gameLibrary;
	private CustomerLibrary customerLibrary;
	private AlbumLibrary albumLibrary;
	private double rentProfit = 0;
	Scanner scanner = new Scanner(System.in);

	public void RunEmployeeRoutine(GameLibrary gameLibrary, CustomerLibrary customerLibrary,
								   AlbumLibrary albumLibrary)
	{
		this.gameLibrary = gameLibrary;   // initialize the lists needed.
		this.customerLibrary = customerLibrary;
		this.albumLibrary = albumLibrary;

		Print.printPasswordPrompt();
		String input = scanner.nextLine();

		if (input.equals("password123"))
		{
			while (!input.equals("10"))
			{
				Print.printEmployeeScreen();
				input = scanner.nextLine();
				int idToRemove;
				int id;

				switch (input)
				{
					case "1":
						try
						{
							addNewGame();
						} catch (Exception e)
						{
							Print.printInvalidInput();
						}
						break;

					case "2":
						System.out.println("---------------GAMES---------------");
						gameLibrary.printAllGames();
						System.out.println("-----------------------------------");
						Print.printRemoveGame();

						try
						{
						idToRemove = scanner.nextInt();
						scanner.nextLine();
						gameLibrary.removeGame(idToRemove);
						}
						catch (Exception e)
						{
						Print.printInvalidInput();
						}
						break;

					case "3":
						try
						{
							addCustomer();
						} catch (Exception e)
						{
							Print.printInvalidInput();
						}
						break;

					case "4":
						System.out.println("--------------CUSTOMERS--------------");
						customerLibrary.printAllCustomers();
						System.out.println("-------------------------------------");
						Print.printRemoveCustomer();
						try
						{
							id = scanner.nextInt();
							scanner.nextLine();
							customerLibrary.removeCustomer(id);
						}
						catch(Exception e)
						{
						Print.printInvalidInput();
						}
						break;

					case "5":
						System.out.println("Total rent profit is: " + rentProfit + " kr.");
						break;

					case "6":
						System.out.println("-----------------GAMES-----------------");
						gameLibrary.printAllGames();
						System.out.println("---------------------------------------");
						break;

					case "7":
						try
						{
							addNewSongAlbum();
						}
						catch(Exception e)
						{
							Print.printInvalidInput();
						}
						break;

					case "8":
						System.out.println("-----------------ALBUMS----------------");
						albumLibrary.printAllAlbums();
						System.out.println("---------------------------------------");
						Print.printRemoveSongAlbum();
						try
						{
							idToRemove = scanner.nextInt();
							scanner.nextLine();
							albumLibrary.removeAlbum(idToRemove);
						}
						catch(Exception e)
						{
							Print.printInvalidInput();
						}
						break;

					case "9":
						System.out.println("----------MEMBERSHIP REQUESTS----------");
						printRequestList();
						System.out.println("---------------------------------------");
						break;

					default:
					{
						if (!input.equals("10"))
						{
							Print.printInvalidInput();
						}
						break;
					}
				}
			}
		}
		else
			Print.printInvalidPassword();
	}

	public void addNewGame()
	{
		Print.printCreateGame();

		System.out.print("ID: ");
		int id = scanner.nextInt();
		scanner.nextLine();

		System.out.print("Name: ");
		String name = scanner.nextLine();

		System.out.print("Genre: ");
		String genre = scanner.nextLine();

		System.out.print("Daily rent: ");
		double dailyRent = scanner.nextDouble();
		scanner.nextLine();

		gameLibrary.addGame(id, name, genre, dailyRent);
	}

	public void addCustomer()
	{
		Print.printCreateCustomer();

		System.out.print("ID: ");
		int id = scanner.nextInt();
		scanner.nextLine();

		System.out.print("Name: ");
		String name = scanner.nextLine();

		System.out.print("Password: ");
		String password = scanner.nextLine();

		customerLibrary.addCustomer(id, name, password);
	}

	public void addNewSongAlbum()
	{
		Print.printCreateSongAlbum();

		System.out.print("ID: ");
		int id = scanner.nextInt();
		scanner.nextLine();

		System.out.print("Title: ");
		String title = scanner.nextLine();

		System.out.print("Artist: ");
		String artist = scanner.nextLine();

		System.out.print("Released in year: ");
		int releaseYear = scanner.nextInt();
		scanner.nextLine();

		System.out.print("Daily rent: ");
		double rentPerDay = scanner.nextDouble();
		scanner.nextLine();

		albumLibrary.addAlbum(id, title, artist, releaseYear, rentPerDay);
	}

	public void printRequestList()
	{
		for (Customer customer : customerLibrary.getPendingMembershipRequests())
		{
			System.out.println(customer.toString());
			System.out.print("Accept (y/n): ");
			String answer = scanner.nextLine();
			if (answer.equals("y"))
				customer.upgrade();

			customer.requestHandled();
		}
	}
}
