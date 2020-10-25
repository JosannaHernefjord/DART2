package Project.Database;

public class Review
{
	//---------INSTANCE VARIABLES-------
	private int customerId;
	private int daysRented;
	private int rating;
	private String writtenReview;
	private boolean ratingValid;

	//--------------CONSTRUCTOR---------
	public Review(int customerId, int daysRented, int rating, String writtenReview, boolean ratingValid)
	{
		this.customerId = customerId;
		this.daysRented = daysRented;
		this.rating = rating;
		this.writtenReview = writtenReview;
		this.ratingValid = ratingValid;
	}

	public int getRating()
	{
		return rating;
	}

	public int getDaysRented()
	{
		return daysRented;
	}

	public boolean isRatingValid()
	{
		return ratingValid;
	}

	public int getCustomerId()
	{
		return customerId;
	}

	public String toString()
	{
		if (ratingValid)
			return "Customer id: " + customerId + " Days rented: " + daysRented + " Rating: "
					+ rating + " Review: " + writtenReview;
		else
			return "Customer id: " + customerId + " Days rented: " + daysRented;
	}
}
