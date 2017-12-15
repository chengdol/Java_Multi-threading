package Chapter6_Parallel_Reactive_Stream_5;

import java.util.List;

// filter element in stream
// distinct
// filter
// limit
// skip
public class Main
{
	public static void main(String[] args)
	{
		List<Person> persons = PersonGenerator.generatePersonList(10);
		// distinct
		System.out.println("Main: =================distinct");
		persons
			.parallelStream()
			.distinct()
			.forEach(p -> {
				System.out.printf("%s-%s\n"
						, p.getLastName()
						, p.getFirstName());
			});
		
		// filter
		System.out.println("Main: =================filter");
		persons.parallelStream()
			.filter(p -> p.getSalary() < 30000)
			.forEach(p -> {
				System.out.printf("%s-%s\n"
						, p.getLastName()
						, p.getFirstName());
			});
		
		// limit
		// show first 5 items
		System.out.println("Main: =================limit ");
		persons.parallelStream()
			.mapToDouble(p -> p.getSalary())
			.sorted()
			.limit(5)
			// here must use forEachOrdered
			.forEachOrdered(d -> {
				System.out.printf("%f\n", d);
			});
		
		// skip
		// show last 5 items
		System.out.println("Main: =================skip");
		persons.parallelStream()
			.mapToDouble(p -> p.getSalary())
			.sorted()
			.skip(5)
			.forEachOrdered(d -> {
				System.out.printf("%f\n", d);
			});
	}
}
