package Chapter6_Parallel_Reactive_Stream_3;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Collectors;

public class Main
{
	public static void main(String[] args)
	{
		List<Person> persons = PersonGenerator.generatePersonList(100);
		
		System.out.println("Main: starting...");
		// grouping by value type here is Person
		System.out.println("Main: grouping persons by first name");
		Map<String, List<Person>> personsByName = persons
				.parallelStream()
				.collect(Collectors
						.groupingByConcurrent(Person::getFirstName));
		// print value numbers
		personsByName.keySet().forEach(k -> {
			System.out.printf("Main: name %s has %d person%s\n"
					, k
					, personsByName.get(k).size()
					, personsByName.get(k).size() > 1? "s" : "");
		});
		
		System.out.println("==========================================");
		System.out.println("Main: concatenating persons' name");
		String result = persons
				.parallelStream()
				.map(p -> p.toString())
				.collect(Collectors.joining(","));
		System.out.printf("Main: name concatenating result is\n %s\n", result);
		
		
		System.out.println("==========================================");
		System.out.println("Main: using partition by divide persons into 2 groups");
		Map<Boolean, List<Person>> personBySalary = persons.parallelStream()
				.collect(Collectors.partitioningBy(p -> p.getSalary() > 50000));
		personBySalary.keySet().forEach(k -> {
			List<Person> value = personBySalary.get(k);
			System.out.printf("Main: %s has %d people\n"
					, k
					, value.size());
		});
		
		System.out.println("==========================================");
		System.out.println("Main: customized map");
		ConcurrentMap<String, String> personByNameString = persons
				.parallelStream()
				.collect(Collectors
						.toConcurrentMap(p -> p.getFirstName()
						, p -> p.getLastName()
						, (s1, s2) -> s1 + " " + s2));
		personByNameString.forEach((k ,v) -> {
			System.out.printf("Main: %s: %s\n"
					,k
					,v);
		});
		
		System.out.println("==========================================");
		System.out.println("Main: using collect() generate list");
		List<Person> highSalary = persons
				.parallelStream()
				.collect(ArrayList::new
						// 每次这个list都是新的
						, (list, p) -> {
							if (p.getSalary() > 50000)
							{
								list.add(p);
							}
						}
						// 叠加list中的元素
						// 不太明白为什么要这么设计？？
						, ArrayList::addAll);
		System.out.printf("Main: high salary people %d\n", highSalary.size());
		
		System.out.println("==========================================");
		System.out.println("Main: complex customized map");
		// 这个例子有点恶心
		
		
		
		
		
		
		
		
	}
}
