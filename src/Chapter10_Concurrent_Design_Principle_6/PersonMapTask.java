package Chapter10_Concurrent_Design_Principle_6;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.RecursiveAction;

public class PersonMapTask extends RecursiveAction 
{
	private final List<Person> persons;
	private final ConcurrentHashMap<String, ConcurrentLinkedDeque<Person>> personMap;
	
	
	public PersonMapTask(List<Person> persons
			, ConcurrentHashMap<String, ConcurrentLinkedDeque<Person>> personMap)
	{
		super();
		this.persons = persons;
		this.personMap = personMap;
	}



	@Override
	protected void compute()
	{
		if (persons.size() < 1000)
		{
			for (Person person: persons)
			{
				personMap.computeIfAbsent(person.getLastName()
						, k -> { return new ConcurrentLinkedDeque<>(); }).add(person);
			}
			return;
		}
		
		// divide and conquer
		PersonMapTask left, right;
		left = new PersonMapTask(persons.subList(0, persons.size() / 2), personMap);
		right = new PersonMapTask(persons.subList(persons.size() / 2, persons.size()), personMap);
		
		invokeAll(left, right);
	}

}
