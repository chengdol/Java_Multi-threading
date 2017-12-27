package Chapter7_Concurrent_Collections_5;

import java.util.Map;
import java.util.concurrent.ConcurrentNavigableMap;
import java.util.concurrent.ConcurrentSkipListMap;

// learn how to use
// concurrent navigable map, sorted
// submap operation
public class Main
{
	public static void main(String[] args)
	{
		ConcurrentSkipListMap<String, Contact> map = new ConcurrentSkipListMap<>();
		
		// create 26 threads
		// id from 'A' to 'Z'
		Thread[] threads = new Thread[26];
		int cnt = 0;
		for (char i = 'A'; i <= 'Z'; i++)
		{
			Task task = new Task(String.valueOf(i), map);
			threads[cnt] = new Thread(task);
			threads[cnt].start();
			cnt++;
		}
		
		// join
		for (int i = 0; i < threads.length; i++)
		{
			try
			{
				threads[i].join();
			} catch (InterruptedException e)
			{
				e.printStackTrace();
			}
		}
		
		// check
		System.out.printf("Main: map size is %d\n", map.size());
		// get first entry from this sorted map
		Map.Entry<String, Contact> entry = map.firstEntry();
		Contact contact = entry.getValue();
		System.out.printf("Main: First entry is %s - %s\n"
				, contact.getName()
				, contact.getNumber());
		
		// get last entry
		entry = map.lastEntry();
		contact = entry.getValue();
		System.out.printf("Main: Last entry is %s - %s\n"
				, contact.getName()
				, contact.getNumber());
		
		// get submap
		ConcurrentNavigableMap<String, Contact> subMap = map.subMap("A1996", true, "B1004", true);
		// write out to console
		do
		{
			entry = subMap.pollFirstEntry();
			if (entry != null)
			{
				contact = entry.getValue();
				System.out.printf("%s - %s\n", contact.getName(), contact.getNumber());
			}
			
		} while (entry != null);
	}
}
