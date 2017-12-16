package Chapter6_Parallel_Reactive_Stream_6;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.Stream;

// transform element in stream
// mapToDouble
public class Main
{
	// auxiliary method
	private static long getAge(Date birthDate) 
	{
		LocalDate start = birthDate.toInstant()
				.atZone(ZoneId
						.systemDefault())
				.toLocalDate();
		
		LocalDate now = LocalDate.now();
		// get the difference year
		long ret = ChronoUnit.YEARS.between(start, now);
		return ret;
	}
	
	public static void main(String[] args)
	{
		List<Person> persons = PersonGenerator.generatePersonList(10);
		
		// mapToDouble
		System.out.println("Main: map to double");
		DoubleStream ds = persons.parallelStream()
				.mapToDouble(p -> {
					return p.getSalary();
				});
		// show distinct salary
		ds.distinct().forEach(d -> {
			System.out.printf("%f\n", d);
		});
		// show count
		// we need create new stream here! only use once!
		ds = persons.parallelStream()
				.mapToDouble(p -> {
					return p.getSalary();
				});
		System.out.printf("size: %d\n", ds.distinct().count());
		
		// map
		System.out.println("===============================");
		System.out.println("Main: map()");
		List<BasicPerson> basicPersons = persons.parallelStream()
				.map(p -> {
					BasicPerson bp = new BasicPerson();
					bp.setAge(getAge(p.getBirthDate()));
					bp.setName(p.getLastName() + " " + p.getFirstName());
					return bp;
				})
				.collect(Collectors.toList());
		basicPersons.forEach(bp -> {
			System.out.printf("Name:%s  Age:%d\n", bp.getName(), bp.getAge());
		});
		
		System.out.println("===============================");
		System.out.println("Main: map()");
		List<String> file = FileGenerator.generateFile(100);
		// flat mapping lines into word with its counts
		// here counting() type is long
		// flatMap should return a stream
		Map<String, Long> wordCount = file.parallelStream()
				.flatMap(line -> Stream.of(line.split("[ .,]")))
				.filter(w -> w.length() > 0)
				.sorted()
				.collect(Collectors.groupingByConcurrent(w -> w, Collectors.counting()));
		// print
		wordCount.forEach((k, v) -> {
			System.out.printf("%s, %d\n", k, v);
		});

		
	}
}
