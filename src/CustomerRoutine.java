import java.util.Scanner;

public class CustomerRoutine
{

	private GameLibrary gameLibrary;
	private CustomerLibrary customerLibrary;
	private AlbumLibrary albumLibrary;
	private double rentProfit;
	Scanner scanner = new Scanner(System.in);

	public void RunCustomerRoutine(GameLibrary gameLibrary, CustomerLibrary customerLibrary, AlbumLibrary albumLibrary)
	{
		this.gameLibrary = gameLibrary;
		this.customerLibrary = customerLibrary;
		this.albumLibrary = albumLibrary;

		String input = "";
		Customer activeCustomer;

		System.out.print("Enter ID: ");
		int loginId = scanner.nextInt();
		scanner.nextLine();
		activeCustomer = customerLibrary.getCustomer(loginId);
		System.out.print("Enter password: ");
		String password = scanner.nextLine();
		if (activeCustomer.checkPassword(password))
		{
			while (!input.equals("11"))
			{
				Print.printCustomerScreen();
				input = scanner.nextLine();
				switch (input)
				{
					case "1":     // Rent a game
						rentGame(activeCustomer);
						break;

					case "2":        //Return a game
						returnGame(activeCustomer);
						break;

					case "3":            //rent an album
						rentAlbum(activeCustomer);
						break;

					case "4":            // return an album
						returnAlbum(activeCustomer);
						break;

					case "5":
						activeCustomer.requestMembershipUpgrade();
						System.out.println("Request for upgrade membership done.");
						break;

					case "6":
						activeCustomer.printMessages();
						break;

					case "7":
						sendMessage(activeCustomer.getId());
						break;

					case "8":
						System.out.println("-------All games by rating-------");
						gameLibrary.printAllGames();
						System.out.println("---------------------------------");
						break;

					case "9":
						System.out.println("-------All album by rating-------");
						albumLibrary.printAllAlbums();
						System.out.println("---------------------------------");
						break;

					case "10":
						System.out.println("---------------------------------");
						searchItem();
						System.out.println("---------------------------------");
						break;

					default:
						if (!input.equals("11"))
						{
							Print.printInvalidInput();
						}
						break;
				}
			}
		}
		else
		{
			Print.printInvalidPassword();
		}
	}

	public void rentGame(Customer activeCustomer)
	{
		System.out.println("-----------------GAMES------------------");
		gameLibrary.printAllGames();
		System.out.println("----------------------------------------");
		System.out.println("Write the ID of the game you want to rent: ");
		int id = scanner.nextInt();
		scanner.nextLine();

		if (gameLibrary.checkAvailability(id))
		{
			if (activeCustomer.getNumberObjectsRented() < activeCustomer.rentLimit())
			{
				gameLibrary.rentGame(id);
				activeCustomer.rentedOneItem();
				System.out.println("Game rented!");
			}
			else
			{
				System.out.println("You've reached your rent limit.");
			}
		}
		else
		{
			if (gameLibrary.contains(id))
				System.out.println("Game with ID: " + id + " is not available.");
			else
				System.out.println("Game with ID: " + id + " does not exist.");
		}
	}

	public void returnGame(Customer activeCustomer)
	{
		System.out.println("-----------------GAMES------------------");
		gameLibrary.printAllGames();
		System.out.println("----------------------------------------");

		System.out.println("Enter the following information: ");

		System.out.print("ID of game to return: ");
		int id = scanner.nextInt();
		scanner.nextLine();

		System.out.print("Number of days rented: ");
		int daysRented = scanner.nextInt();
		scanner.nextLine();

		if (gameLibrary.contains(id) && !gameLibrary.checkAvailability(id) && daysRented > 0)
		{
			gameLibrary.returnGame(id);
			activeCustomer.returnedOneItem();

			if (activeCustomer.getsForFree())
			{
				System.out.println("Game returned for free!");
			}
			else
			{
				activeCustomer.increaseCredits();
				double cost = daysRented * gameLibrary.getDailyRent(id) * activeCustomer.discount();
				rentProfit = rentProfit + cost;
//				activeCustomer.addRentProfit(cost);
				System.out.println("Game returned! You paid: " + cost + " kr.");
			}

			System.out.println("Would you like to leave a rating? (y/n)");
			String input = scanner.nextLine();
			int rating = 0;
			String review = "";
			boolean isValidRating = false;

			if (input.equals("y"))
			{
				isValidRating = true;
				System.out.println("Enter a rate from 0 to 5: ");
				rating = scanner.nextInt();
				scanner.nextLine();

				while (rating < 0 || rating > 5)
				{
					System.out.println("Invalid input, try again (0-5). ");
					rating = scanner.nextInt();
					scanner.nextLine();
				}

				System.out.println("Would you like to leave a written review? (y/n)");
				input = scanner.nextLine();

				if (input.equals("y"))
				{
					System.out.println("Write your review: ");
					review = scanner.nextLine();
				}
			}

			gameLibrary.addReview(id, new Review(activeCustomer.getId(), daysRented, rating, review, isValidRating));
		}
		else if (daysRented <= 0)
		{
			System.out.println("Days invalid");
		}
		else
		{
			if (gameLibrary.contains(id))
				System.out.println("Game with ID: " + id + " is already returned.");
			else
				System.out.println("Game with ID: " + id + " does not exist.");
		}
	}

	public void rentAlbum(Customer activeCustomer)
	{
		System.out.println("-----------------ALBUMS-----------------");
		albumLibrary.printAllAlbums();
		System.out.println("----------------------------------------");

		System.out.println("Which album would you like to rent? ID: ");
		int id = scanner.nextInt();
		scanner.nextLine();

		if (albumLibrary.checkAvailability(id))
		{
			if (activeCustomer.getNumberObjectsRented() < activeCustomer.rentLimit())
			{
				albumLibrary.rentAlbum(id);
				activeCustomer.rentedOneItem();
				System.out.println("Album rented!");
			}
			else
			{
				System.out.println("You've reached your rent limit.");
			}
		}
		else
		{
			if (albumLibrary.contains(id))
				System.out.println("Album with ID: " + id + " is already rented.");
			else
				System.out.println("Album with ID: " + id + " not found.");
		}
	}

	public void returnAlbum(Customer activeCustomer)
	{
		System.out.println("-----------------ALBUM------------------");
		albumLibrary.printAllAlbums();
		System.out.println("----------------------------------------");

		System.out.println("Which album would you like to return? ID: ");
		int id = scanner.nextInt();
		scanner.nextLine();

		System.out.println("Number of days rented: ");
		int daysRented = scanner.nextInt();
		scanner.nextLine();

		if (albumLibrary.contains(id) && !albumLibrary.checkAvailability(id))
		{
			albumLibrary.returnAlbum(id);
			activeCustomer.returnedOneItem();

			if (activeCustomer.getsForFree())
			{
				System.out.println("Game returned for free!");
			}
			else
			{
				activeCustomer.increaseCredits();
				double cost = daysRented * albumLibrary.getDailyRent(id) * activeCustomer.discount();
				rentProfit = rentProfit + cost;
//				activeCustomer.addRentProfit(cost);
				System.out.println("Song album returned! You paid: " + cost + " kr.");
			}

			System.out.println("Would you like to leave a rating? (y/n)");
			String input = scanner.nextLine();
			int rating = 0;
			String review = "";
			boolean isValidRating = false;

			if (input.equals("y"))
			{
				isValidRating = true;
				System.out.println("Print a review from 0 to 5: ");
				rating = scanner.nextInt();
				scanner.nextLine();

				while (rating < 0 || rating > 5)
				{
					System.out.println("Invalid input, try again (0-5). ");
					rating = scanner.nextInt();
					scanner.nextLine();
				}

				System.out.println("Would you like to leave a written review? (y/n)");
				input = scanner.nextLine();

				if (input.equals("y"))
				{
					System.out.println("Write your review: ");
					review = scanner.nextLine();
				}
			}

			albumLibrary.addReview(id, new Review(activeCustomer.getId(), daysRented, rating, review, isValidRating));
		}
		else
		{
			if (albumLibrary.contains(id))
				System.out.println("Album with ID: " + id + " is already returned.");
			else
				System.out.println("Album with ID: " + id + " does not exist.");
		}
	}

	private void sendMessage(int senderId)
	{
		System.out.println("Write recipient ID: ");
		int recipient = scanner.nextInt();
		scanner.nextLine();

		if (customerLibrary.contains(recipient))
		{
			System.out.println("Write your message: ");
			String message = scanner.nextLine();
			customerLibrary.getCustomer(recipient).addMessage(new Message(senderId, message));
		}
		else
		{
			System.out.println("Customer not found.");
		}
	}

	public void searchItem()
	{
		System.out.println("What item do you wish to search for? Type one of the options below: \n1) Games \n2) Song albums");
		String input = scanner.nextLine();

		if (input.equals("1"))
		{
			System.out.println("Enter genre to search for games: ");
			String genre = scanner.nextLine();
			gameLibrary.printByGenre(genre);

		}
		else if (input.equals("2"))
		{
			System.out.println("Enter release year to search for song albums: ");
			int year = scanner.nextInt();
			albumLibrary.printByYear(year);

		}
		else
		{
			Print.printInvalidInput();
		}
	}
}
