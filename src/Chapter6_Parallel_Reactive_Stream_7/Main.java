package Chapter6_Parallel_Reactive_Stream_7;

import java.util.Arrays;
import java.util.List;
import java.util.TreeSet;
import java.util.stream.Collectors;

// sort stream
// unordered() usage
public class Main
{
	public static void main(String[] args)
	{
		// sort numbers and print result
		int[] numbers = {1,3,4,8,9,5,3,4,6,7,45};
		Arrays.stream(numbers)
			.parallel()
			.sorted()
			.forEachOrdered(n -> {
				System.out.printf("%d\n", n);
			});
		
		List<Person> persons = PersonGenerator.generatePersonList(10);
		// tree set ordered items internally
		TreeSet<Person> personSet = new TreeSet<>(persons);
		// let's see the difference between ordered and unordered
		// this stream respects to the encounter order of tree set
		// the print will always be the same
		for (int i = 0; i < 10; i++)
		{
			// order
			Person p = personSet
				// the stream has encounter order
				.stream()
				.parallel()
				.limit(1)
				.collect(Collectors.toList())
				.get(0);
			System.out.printf("%s\n", p.getLastName() + "-" + p.getFirstName());
			
			// unordered, eliminate the encounter order
			// the print will be vary
			p = personSet
					.stream()
					// unordered
					.unordered()
					.parallel()
					.limit(1)
					.collect(Collectors.toList())
					.get(0);
			System.out.printf("%s\n", p.getLastName() + "-" + p.getFirstName());
			// delimiter
			System.out.println("------------------------");
		}
		
	}
}
