package Chapter7_Concurrent_Collections_6;

import java.util.Date;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedDeque;

// each filler create 100 random operation objects
public class HashFiller implements Runnable
{
	private final ConcurrentHashMap<String, ConcurrentLinkedDeque<Operation>> map;
	
	public HashFiller(ConcurrentHashMap<String, ConcurrentLinkedDeque<Operation>> map)
	{
		super();
		this.map = map;
	}


	@Override
	public void run()
	{
		Random random = new Random();
		for (int i = 0; i < 100; i++)
		{
			Operation operation = new Operation();
			// set attributes
			operation.setDate(new Date());
			operation.setUser("USER" + random.nextInt(100));
			operation.setOperation("OP" + random.nextInt(10));
			
			// store in map
			addOperationToMap(operation);
		}
	}
	
	private void addOperationToMap(Operation operation)
	{
		map.computeIfAbsent(operation.getUser(), k -> new ConcurrentLinkedDeque<>())
			.add(operation);
	}

}
