package Project;

import java.util.Calendar;

public class Employee
{
	//-----INSTANCE VARIABLES----
	private int id;
	private String name;
	private String address;
	private int birthYear;
	private int year = Calendar.getInstance().get(Calendar.YEAR);
	private double grossSalary;
	private int bonus;
	private final int bonusLow = 4000;
	private final int bonusMedium = 6000;
	private final int bonusHigh = 7500;
	private final double taxCut = 0.7;

	//--------------CONSTRUCTOR---------
	public Employee(int id, String name, int birthYear, String address, double grossSalary)
	{
		this.id = id;
		this.name = name;
		this.birthYear = birthYear;
		this.address = address;
		this.grossSalary = grossSalary;

	}

	//---------METHODS-----------

	public int getId()
	{
		return id;
	}

	public String toString()
	{
		int age = year - birthYear;
		return id + ": " + name + " - " + birthYear + " (" + age + "): " +
				address + " - " + grossSalary + "SEK";
	}

	public String employeeSalary()
	{
		int lowSalary = 100000;
		int monthsInYear = 12;

		if (grossSalary * monthsInYear < lowSalary)
		{
			return "Monthly net salary: " + grossSalary + " - " + "Yearly net salary: " + (grossSalary * monthsInYear + bonus);
		}
		else
		{
			return "Monthly net salary: " + grossSalary * taxCut + " - " + "Yearly net salary: " + ((grossSalary * taxCut) * monthsInYear + bonus);
		}
	}

	public String dataEntryString()
	{
		return "employee," + getId() + "," + name + "," + birthYear + "," + address + "," + grossSalary;
	}

	public String employeeBonus()
	{
		int age = year - birthYear;
		if (age < 22)
		{
			bonus = bonusLow;
			return "lib.Employee will receive 4000SEK in bonus";
		}
		else if (age <= 30)
		{
			bonus = bonusMedium;
			return "lib.Employee will receive 6000SEK in bonus";
		}
		else
		{
			bonus = bonusHigh;
			return "lib.Employee will receive 7500SEK in bonus";
		}
	}
}
