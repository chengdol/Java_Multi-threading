package Chapter7_Concurrent_Collections_6;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedDeque;

// learn to use concurrent hash map
public class Main
{
	public static void main(String[] args)
	{
		// create concurrent hash map
		ConcurrentHashMap<String, ConcurrentLinkedDeque<Operation>> map =
				new ConcurrentHashMap<>();
		// create hash filler
		// here only create one runnable
		// the threads below will share the field of this runnable
		HashFiller filler = new HashFiller(map);
		
		// create 10 threads then start
		Thread[] threads = new Thread[10];
		for (int i = 0; i < threads.length; i++)
		{
			threads[i] = new Thread(filler);
			threads[i].start();
		}
		
		// join
		for (Thread thread : threads)
		{
			try
			{
				thread.join();
			} catch (InterruptedException e)
			{
				e.printStackTrace();
			}
		}
		
		// check
		System.out.printf("Main: map size is %s\n", map.size());
		
		// foreach
		// 10 is the threshold that can apply parallelism
		// of the foreach operation
		// so you can see some "main" thread, that is because less then 10
		// only be executed sequentially
		map.forEach(10, (key, value) -> {
			System.out.printf("forEach: %s: %s-%s\n"
					, Thread.currentThread().getName()
					, key
					, value.size());
		});
		
		// for each entry
		map.forEachEntry(10, entry -> {
			System.out.printf("forEachEntry: %s: %s-%s\n"
					, Thread.currentThread().getName()
					, entry.getKey()
					, entry.getValue().size());
		});
		
		// search
		// find the first satisfied element
		Operation op = map.search(10, (key, value) -> {
			for (Operation tmp : value)
			{
				if (tmp.getOperation().endsWith("1"))
				{
					return tmp;
				}
			}
			return null;
		});
		System.out.printf("Search: the first found is %s-%s-%s\n"
				, op.getUser()
				, op.getOperation()
				, op.getDate());
		
		// reduce
		// find total number of operations
		int num = map.reduce(10, (key, value) -> {
			return value.size();
		}, (n1, n2) -> {
			return n1 + n2;
		});
		
		System.out.printf("Reduce: total number of operation is %d\n", num);
	}
}
