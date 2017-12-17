package Chapter6_Parallel_Reactive_Stream_8;

import java.util.List;

// verify condition in stream
// allMatch()
// anyMatch()
// noneMatch()
// findAny()
// findFirst()
public class Main
{
	public static void main(String[] args)
	{
		List<Person> persons = PersonGenerator.generatePersonList(10);
		// calculate salary range
		int maxSalary = persons.parallelStream()
				// use map
				.map(p -> {
					return p.getSalary();
				})
				// here need comparator since we do not know the stream type
				.max(Integer::compare)
				.get();
		int minSalary = persons.parallelStream()
				// use map to int
				.mapToInt(p -> p.getSalary())
				// here we know its now a int stream
				.min()
				.getAsInt();
		
		System.out.printf("Salary between %d and %d\n"
				, maxSalary
				, minSalary);
		
		// all match
		Boolean condition = persons.parallelStream()
				.allMatch(p -> {
					return p.getSalary() > 0;
				});
		System.out.printf("Main: all salary > 0, %s\n", condition);
		
		// any match
		condition = persons.parallelStream()
				.anyMatch(p -> {
					return p.getSalary() > 25000;
				});
		System.out.printf("Main: any salary > 25000, %s\n", condition);
		
		// none match
		condition = persons.parallelStream()
				.noneMatch(p -> {
					return p.getSalary() > 100000;
				});
		System.out.printf("Main: none salary > 100000, %s\n", condition);
		
		// find any and find first both return Optional object
		// find any
		Person person = persons.parallelStream()
				.findAny()
				.get();
		System.out.printf("Main: find any person %s\n", person.getLastName());
		
		// find first
		person = persons.parallelStream()
				.sorted((p1, p2) -> {
					return p1.getSalary() - p2.getSalary();
				})
				.findFirst()
				.get();
		System.out.printf("Main: find first person with lowest salary %s\n", person.getLastName());
			
	}
}
