
public class Game extends ItemBase implements Item
{
	//---------INSTANCE VARIABLES-----------
	private String genre;

	//Constructor
	public Game(int id, String title, String genre, double dailyRent)
	{
		super(id, title, dailyRent);
		this.genre = genre;
	}

	//---------METHODS-----------

	public String getGenre()
	{
		return genre;
	}

	public String toString()
	{
		String status = "Can be returned";

		if (isAvailable)
			status = "Not rented";

		return getId() + ": " + getTitle() + " (" + genre + "). " + getDailyRent() + "Average rating: " + getAverageRating() + ". Status: " + status;
	}

}
