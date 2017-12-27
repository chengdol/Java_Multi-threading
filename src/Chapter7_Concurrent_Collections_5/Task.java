package Chapter7_Concurrent_Collections_5;

import java.util.concurrent.ConcurrentSkipListMap;

public class Task implements Runnable
{
	private final String id;
	private final ConcurrentSkipListMap<String, Contact> map;
	
	public Task(String id, ConcurrentSkipListMap<String, Contact> map)
	{
		super();
		this.id = id;
		this.map = map;
	}


	@Override
	public void run()
	{
		// add 1000 contacts into concurrent skip list map
		for (int i = 0; i < 1000; i++)
		{
			Contact contact = new Contact(id, String.valueOf(i + 1000));
			map.put(id + contact.getNumber(), contact);
		}
	}

}
