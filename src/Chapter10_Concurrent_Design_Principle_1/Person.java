package Chapter10_Concurrent_Design_Principle_1;

import java.util.Date;

// final class
public final class Person
{
	// final private attributes
	final private String firstName;
	final private String lastName;
	final private Date date;
	
	public Person(String firstName, String lastName, Date date)
	{
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.date = date;
		// do not leak this reference!
	}

	public String getFirstName()
	{
		return firstName;
	}

	public String getLastName()
	{
		return lastName;
	}

	public Date getDate()
	{
		// new for mutable object
		return new Date(date.getTime());
	}
}
