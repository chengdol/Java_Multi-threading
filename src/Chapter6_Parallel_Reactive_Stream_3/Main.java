package Chapter6_Parallel_Reactive_Stream_3;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Collectors;


// perform collect operations
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
				// this part is result container
				// for parallel execution may called multiple times
				.collect(ArrayList::new
						// 这个list是怎么操作的?
						// 这个list是可以重复利用的
						, (list, p) -> {
							// System.out.println(list.size());
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
		// create a concurrent hash map
		// first name is the key
		// counter object is the value
		// merge the tmp hashmap 
		ConcurrentHashMap<String, Counter> peopleNames = persons.parallelStream().collect(ConcurrentHashMap::new
				, (map, person) -> {
					// 根据name来初始化value
					map.computeIfAbsent(person.getFirstName(), (name) -> {
						Counter counter = new Counter();
						counter.setValue(name);
						return counter;
					});
					// 若有重复的key，则value + 1
					map.computeIfPresent(person.getFirstName(), (name, counter) -> {
						counter.increment();
						return counter;
					});
				}
				// merge 不同线程中的map到一起
				, (hash1, hash2) -> {
					// 遍历hash2中的每一组值
					hash2.forEach((k, v) -> {
						// merge到hash1中去
						// k, v是hash2中遍历的值
						// v1,v2 其中一个是hash1.get(k), 一个是v
						// 不确定谁是谁？
						// 当hash1.get(k) is null 的时候，才调用remapping function
						hash1.merge(k, v, (v1, v2) -> {
							v1.setCounter(v1.getCounter() + v2.getCounter());
							return v1;
						});
					});
				});
		peopleNames.forEach((k, v) -> System.out.printf("%s, %d\n", k, v.getCounter()));
		
		
		
		
		
		
	}
}
