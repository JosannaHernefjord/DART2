public class Review
{
	private int customerId;
	private int daysRented;
	private int rating;
	private String review;
	private boolean ratingValid;

	public Review(int customerId, int daysRented, int rating, String review, boolean ratingValid)
	{
		this.customerId = customerId;
		this.daysRented = daysRented;
		this.rating = rating;
		this.review = review;
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

	public String toString()
	{
		if (ratingValid)
			return "Customer id: " + customerId + " Days rented: " + daysRented + " Rating: " + rating + " Review: " + review;
		else
			return "Customer id: " + customerId + " Days rented: " + daysRented;
	}
}
