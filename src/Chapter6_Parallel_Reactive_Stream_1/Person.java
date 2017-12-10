package Chapter6_Parallel_Reactive_Stream_1;

// person class
public class Person implements Comparable<Person>
{
	// fields
	private String firstName;
	private String lastName;
	
	
	public Person(String firstName, String lastName)
	{
		super();
		this.firstName = firstName;
		this.lastName = lastName;
	}


	@Override
	public int compareTo(Person o)
	{
		int compareLastName = this.lastName.compareTo(o.lastName);
		
		if (compareLastName != 0)
		{
			return compareLastName;
		}
		return this.firstName.compareTo(o.firstName);
	}

}
