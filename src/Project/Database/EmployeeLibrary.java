package Project.Database;

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
		if (contains(id))
		{
			System.out.println("Employee with ID: " + id + " already exist.");
		}
		else if (name.isEmpty())
		{
			System.out.println("Invalid data. Employee name cannot be empty.");
		}
		else if (birthYear < 0)
		{
			System.out.println("Invalid data. Employee birth year cannot be negative.");
		}
		else if (grossSalary < 0)
		{
			System.out.println("Invalid data. Employee salary cannot be negative.");
		}
		else
		{
			Employee e = new Employee(id, name, birthYear, address, grossSalary);
			employeeList.add(e);
			System.out.println("Employee added! ");
		}
	}

	public void removeEmployee(int idToRemove)
	{
		boolean foundEmployee = false;

		for (Employee employee : employeeList)
		{
			if (employee.getId() == idToRemove)
			{
				employeeList.remove(employee);
				foundEmployee = true;
				System.out.println("Employee removed.");
				break;
			}
		}

		if (!foundEmployee)
		{
			System.out.println("Employee with ID: " + idToRemove + " was not found.");
		}
	}

	public void printAllEmployees()
	{
		for (Employee employee : employeeList)
		{
			System.out.println(employee.toString());
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

		if (!foundEmployee)
		{
			System.out.println("Employee with ID: " + id + " was not found.");
		}
	}

	public void addBonus(int id)
	{
		boolean foundEmployee = false;

		for (Employee employee : employeeList)
		{
			if (employee.getId() == id)
			{
				System.out.println(employee.employeeBonus());
				foundEmployee = true;
				break;
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