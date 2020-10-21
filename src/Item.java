
import java.util.ArrayList;

public interface Item extends Comparable<Item>
{
	int getId();

	void addReview(Review review);

	void rentOut();

	void returnItem();

	boolean getIsAvailable();

	double getDailyRent();

	ArrayList<Review> getReviews();

	String getTitle();

	double getTotalRentProfit();

	double getAverageRating();
}