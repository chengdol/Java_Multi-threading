package Chapter6_Parallel_Reactive_Stream_4;

import java.util.List;

// perform 
// foreEach
// peek
// forEachOrdered
public class Main
{
	public static void main(String[] args)
	{
		List<Person> persons = PersonGenerator.generatePersonList(10);
		// for each
		System.out.println("Main: forEach operaton");
		persons.parallelStream().forEach(p -> {
			System.out.printf("%s-%s\n"
					, p.getLastName()
					, p.getFirstName());
		});
		System.out.println("================================");
		
		System.out.println("Main: forEachOrdered operaton");
		List<Double> doubles = DoubleGenerator.generateDoubleList(10, 100);
		// here must use sorted with forEachOrdered
		doubles.parallelStream().sorted().forEachOrdered(d -> {
			System.out.printf("%f\n", d);
		});
		
		persons.parallelStream().sorted().forEachOrdered(p -> {
			System.out.printf("%s-%s\n"
					, p.getLastName()
					, p.getFirstName());
		});
		
		
		System.out.println("================================");
		doubles
			.parallelStream()
			.peek(d -> {
				System.out.printf("peek: %f\n", d);
			})
			.forEach(d -> {
				System.out.printf("%f\n", d);
			});
		
		
	}
}
