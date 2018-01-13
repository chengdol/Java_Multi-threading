package Chapter10_Concurrent_Design_Principle_6;

import java.util.Date;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.Collectors;

// compare the performance
// using stream and executors
// to process big data

public class Main
{
	public static void main(String[] args)
	{
		// create persons list
		List<Person> persons = PersonGenerator.generatePersonList(100000);
		
		Date start, end;
		// process by executor
		start = new Date();
		PersonMapTask mapTask = new PersonMapTask(persons, new ConcurrentHashMap<>());
		ForkJoinPool.commonPool().invoke(mapTask);
		end = new Date();
		System.out.printf("Main: fork join pool execution time %s\n"
				, end.getTime() - start.getTime());
		
		// process by stream
		start = new Date();
		persons.parallelStream().collect(Collectors.groupingByConcurrent(p -> {
			return p.getLastName();
		}));
		
		end = new Date();
		System.out.printf("Main: stream execution time %s\n"
				, end.getTime() - start.getTime());
	}
}
