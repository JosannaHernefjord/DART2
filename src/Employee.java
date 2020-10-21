public class Employee
{
	//-----INSTANCE VARIABLES----
	private int id;
	private String name;
	private String address;
	private int birthYear;
	private int age;
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
		this.age = 2020 - birthYear;
		this.grossSalary = grossSalary;

	}

	//---------METHODS-----------

	public int getId()
	{
		return id;
	}

	public String toString()
	{
		return id + ": " + name + " - " + birthYear + " (" + age + "): " +
				address + " - " + grossSalary + "SEK";
	}

	public String employeeSalary()
	{
		if (grossSalary * 12 < 100000)
		{
			return "Monthly net salary: " + grossSalary + " - " + "Yearly net salary: " + (grossSalary * 12 + bonus);
		}
		else
		{
			return "Montlhy net salary: " + grossSalary * taxCut + " - " + "Yearly net salary: " + ((grossSalary * taxCut) * 12 + bonus);
		}
	}

	public String employeeBonus()
	{
		if (age < 22)
		{
			bonus = bonusLow;
			return "Employee will receive 4000SEK in bonus";
		}
		else if (age <= 30)
		{
			bonus = bonusMedium;
			return "Employee will receive 6000SEK in bonus";
		}
		else
		{
			bonus = bonusHigh;
			return "Employee will receive 7500SEK in bonus";
		}
	}
}
