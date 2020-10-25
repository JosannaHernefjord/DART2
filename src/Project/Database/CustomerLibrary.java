package Project.Database;

import java.util.ArrayList;

public class CustomerLibrary
{
	//-----INSTANCE VARIABLES----
	private ArrayList<Customer> customerList;


	//--------------CONSTRUCTOR---------
	public CustomerLibrary()
	{
		this.customerList = new ArrayList<>();    //Make a new empty list and assign to customerList
	}


	//--------------METHODS------------
	public void addCustomer(int id, String name, String password)
	{
		if (contains(id))
		{
			System.out.println("Employee with ID: " + id + " already exist.");
		}
		else if (name.isEmpty())
		{
			System.out.println("Invalid data. Employee name cannot be empty.");
		}
		else if(password.isEmpty())
		{
			System.out.println("Invalid data. Password can not be empty.");
		}
		else
		{
			Customer customer = new Customer(id, name, password);
			customerList.add(customer);
			System.out.println("Customer added!");
		}
	}

	public void removeCustomer(int idToRemove)
	{
		boolean foundCustomer = false;        //False until proven true

		for (Customer customer : customerList)        //For each lib.Customer "c" in customerList
		{
			if (customer.getId() == idToRemove)        //If c's ID == idToRemove
			{
				customerList.remove(customer);        // Remove c from customerList
				foundCustomer = true;        //lib.Customer found!
				break;                        //No use in looking any more
			}
		}

		if (!foundCustomer)    //If ID was not found in the list
		{
			System.out.println("lib.Customer with ID: " + idToRemove + " was not found.");
		}
	}

	public Customer getCustomer(int id)
	{
		for (Customer customer : customerList)
		{
			if (customer.getId() == id)
				return customer;
		}

		return null;
	}

	public ArrayList<Customer> getPendingMembershipRequests()
	{
		ArrayList<Customer> result = new ArrayList<>();

		for (Customer customer : customerList)
		{
			if (customer.getHasRequestedMembership())
				result.add(customer);
		}

		return result;
	}

	public void printMostProfitable()
	{
		customerList.trimToSize();
		Customer mostProfitableCustomer = customerList.get(0);

		for (Customer customer : customerList)
		{
			if (mostProfitableCustomer.getTotalRentProfit() < customer.getTotalRentProfit())
			{
				mostProfitableCustomer = customer;
			}
		}
		System.out.println(mostProfitableCustomer.toString());
	}

	public boolean contains(int id)
	{
		for (Customer customer : customerList)
		{
			if (id == customer.getId())
			{
				return true;
			}
		}

		return false;
	}

	public void printAllCustomers()
	{
		for (Customer customer : customerList)
		{
			System.out.println(customer.toString());
		}
	}
}
