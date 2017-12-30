package Chapter7_Concurrent_Collections_8;

import java.util.concurrent.atomic.AtomicIntegerArray;

public class Incrementer implements Runnable
{
	private final AtomicIntegerArray array;
	
	public Incrementer(AtomicIntegerArray array)
	{
		super();
		this.array = array;
	}


	@Override
	public void run()
	{
		for (int i = 0; i < array.length(); i++)
		{
			array.getAndIncrement(i);
		}
	} 

}
