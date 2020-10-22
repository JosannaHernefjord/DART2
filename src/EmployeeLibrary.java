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
		if (grossSalary >= 0 && !name.isEmpty() && !contains(id))
		{
			Employee e = new Employee(id, name, birthYear, address, grossSalary);
			employeeList.add(e);
			System.out.println("Employee added!");
		}
		else if (grossSalary < 0)
		{
			System.out.println("Invalid data. Employee salary cannot be negative.");
		}
		else if (name.isEmpty())
		{
			System.out.println("Invalid data. Employee name cannot be empty.");
		}
		else if (contains(id))
		{
			System.out.println("Employee with ID: " + id + " already exist.");
		}
	}

	public List<Employee> getEmployeeList()
	{
		return employeeList;
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
				System.out.println("Employee removed.");
				break;
			}
		}

		if (!foundEmployee)    //If ID was not found in the list
		{
			System.out.println("Employee with ID: " + idToRemove + " was not found.");
		}
	}

	public void printAllEmployees()
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

	public boolean contains(int id)
	{
		for (Employee employee : employeeList)
		{
			if (id == employee.getId())
			{
				return true;
			}
		}

		return false;
	}

}