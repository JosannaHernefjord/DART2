import java.util.ArrayList;
import java.util.List;

public class EmployeeLibrary
{
	//-----INSTANCE VARIABLES----
	private List<Employee> employeeList;

	//--------------CONSTRUCTOR---------
	public EmployeeLibrary()
	{
		employeeList = new ArrayList<>();
	}

	//--------------METHODS------------
	public void addEmployee(int id, String name, int birthYear, String address, double grossSalary)
	{
		Employee employee = new Employee(id, name, birthYear, address, grossSalary); //Create the employee
		employeeList.add(employee);    //Add the employee to the list
	}

	public void removeEmployee(int idToRemove)
	{
		boolean foundEmployee = false;        //False until proven true

		for (Employee employee : employeeList)        //For each Employee "employee" in EmployeeList
		{
			if (employee.getId() == idToRemove)        //If employee's ID == idToRemove
			{
				employeeList.remove(employee);        // Remove employee from gameList
				foundEmployee = true;        //Employee found!
				break;                        //No use in looking any more
			}
		}

		if (!foundEmployee)    //If ID was not found in the list
		{
			System.out.println("Employee with ID: " + idToRemove + " was not found.");
		}
	}

	public void printConsole()
	{
		for (Employee employee : employeeList)                //For each  "employee" in employeeList
		{
			System.out.println(employee.toString());        //Print the employee info
		}
	}

	public void printSalary(int id)
	{
		boolean foundEmployee = false;

		for (Employee employee : employeeList)
		{
			if (employee.getId() == id)
			{
				System.out.println(employee.employeeSalary());
				foundEmployee = true;
				break;
			}
		}

		if (!foundEmployee)        //If ID was not found in the list
		{
			System.out.println("Employee with ID: " + id + " was not found.");
		}
	}

	public void printBonus(int id)
	{
		boolean foundEmployee = false;        //False until proven true

		for (Employee employee : employeeList)        //For each Employee "employee" in customerList
		{
			if (employee.getId() == id)                //If employee's ID == idToRemove
			{
				System.out.println(employee.employeeBonus());    //Remove employee from customerList
				foundEmployee = true;                    //Employee found!
				break;                                    ////No use in looking any more
			}
		}
		if (!foundEmployee)
		{
			System.out.println("Employee with ID: " + id + "was not found");
		}
	}

}