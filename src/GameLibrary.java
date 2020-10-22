
public class GameLibrary extends ItemLibrary
{
	//--------------CONSTRUCTOR---------
	public GameLibrary()
	{
		super();
	}


	//--------------METHODS------------
	public void addGame(int id, String title, String genre, double dailyRent)
	{
		addItem(new Game(id, title, genre, dailyRent));
	}

	public void removeGame(int idToRemove)
	{
		removeItem(idToRemove);
	}

	public void rentGame(int id)
	{
		rentItem(id);
	}

	public void returnGame(int id)
	{
		returnItem(id);
	}

	//prints all games in list to console
	public void printAllGames()
	{
		printAllItems();
	}

	public void printByGenre(String genre)
	{
		for (Item item : itemList)
		{
			Game game = (Game) item;

			if (genre.equals(game.getGenre()))
			{
				System.out.println(game.toString());
			}
		}
	}

	public void printGamesHistory()
	{
		printItemHistory();
	}
}

