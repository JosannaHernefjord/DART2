import java.util.ArrayList;

public class Customer
{
	//-----INSTANCE VARIABLES----
	private ArrayList<Message> inbox;
	private int id;
	private String name;
	private String membership;
	private String password;
	private int credits;
	private int numberObjectsRented;
	private double totalRentProfit;
	private boolean hasRequestedMembership;
	public final String notMember = "Not a member";
	public final String silver = "Silver";
	public final String gold = "Gold";
	public final String platinum = "Platinum";


	//--------------CONSTRUCTOR---------
	public Customer(int id, String name, String password)
	{
		this.inbox = new ArrayList<>();
		this.id = id;
		this.name = name;
		this.membership = "Not a member";
		this.hasRequestedMembership = false;
		this.password = password;
		this.credits = 0;
		this.numberObjectsRented = 0;
	}

	//--------------METHODS------------
	public int getId()
	{
		return id;
	}

	public boolean getHasRequestedMembership()
	{
		return hasRequestedMembership;
	}

	public boolean checkPassword(String password)
	{
		return this.password.equals(password);
	}

	public void requestHandled()
	{
		hasRequestedMembership = false;
	}

	public void requestMembershipUpgrade()
	{
		if (!membership.equals(platinum))
			hasRequestedMembership = true;
	}

	public int getNumberObjectsRented()
	{
		return numberObjectsRented;
	}

	public double getTotalRentProfit()
	{
		return totalRentProfit;
	}

	public void addRentProfit(double cost)
	{
		totalRentProfit = totalRentProfit + cost;
	}

	public void rentedOneItem()
	{
		this.numberObjectsRented++;
	}

	public void returnedOneItem()
	{
		this.numberObjectsRented--;
	}

	public int rentLimit()
	{
		switch (membership)
		{
			case silver:
				return 3;
			case gold:
				return 5;
			case platinum:
				return 7;
			default:
				return 1;
		}
	}

	public void increaseCredits()
	{
		switch (membership)
		{
			case silver:
				credits += 1;
				break;
			case gold:
				credits += 2;
			case platinum:
				credits += 3;

		}
	}

	public boolean getsForFree()
	{
		if (credits >= 5)
		{
			credits = credits - 5;
			return true;
		}
		else
		{
			return false;
		}
	}

	public double discount()
	{
		switch (membership)
		{
			case silver:
				return 0.9;
			case gold:
				return 0.85;
			case platinum:
				return 0.75;
			default:
				return 1.0;
		}
	}

	public void upgrade()
	{
		if (membership.equals(notMember))
		{
			membership = silver;
		}
		else if (membership.equals(silver))
		{
			membership = gold;
		}
		else
		{
			membership = platinum;
		}
	}

	public void addMessage(Message message)
	{
		inbox.add(message);
	}

	public void printMessages()
	{
		System.out.println("-------------UNREAD MESSAGES------------");
		if (!inbox.isEmpty())
		{
			for (Message message : inbox)
			{
				if (!message.getIsRead())
				{
					System.out.println(message.toString());
					message.viewedMessage();
				}
			}
		}
		System.out.println("----------------------------------------");
	}

	public String toString()
	{
		return "ID: " + id + " - Name: " + name + " - Membership: " + membership + " - Total rent profit: " + totalRentProfit;
	}

}
