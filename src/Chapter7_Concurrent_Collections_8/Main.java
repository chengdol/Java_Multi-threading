package Chapter7_Concurrent_Collections_8;

import java.util.concurrent.atomic.AtomicIntegerArray;

// practice AtomicIntegerArray class
// increment and decrement all the value in array

// the final value should be 0 for every element in array
public class Main
{
	public static void main(String[] args)
	{
		// create AtomicIntegerArray object
		// length is 1000
		AtomicIntegerArray array = new AtomicIntegerArray(1000);
		
		// create runnable object
		Incrementer incrementer = new Incrementer(array);
		Decrementer decrementer = new Decrementer(array);
		
		// create 100 threads for each operation
		final int NUM = 100;
		Thread[] incrementThread = new Thread[NUM];
		Thread[] decrementThread = new Thread[NUM];
		
		// new and start
		for (int i = 0; i < decrementThread.length; i++)
		{
			incrementThread[i] = new Thread(incrementer);
			decrementThread[i] = new Thread(decrementer);
			
			incrementThread[i].start();
			decrementThread[i].start();
		}
		
		// join
		
		for (int i = 0; i < decrementThread.length; i++)
		{
			try
			{
				incrementThread[i].join();
				decrementThread[i].join();
			} catch (InterruptedException e)
			{
				e.printStackTrace();
			}
		}
		
		int errors = 0;
		for (int i = 0; i < array.length(); i++)
		{
			if (array.get(i) != 0)
			{
				System.out.printf("Main: array[%s] is %d\n", i, array.get(i));
				errors++;
			}
		}
		if (errors == 0)
		{
			System.out.println("Main: no error found");
		}
		
		System.out.println("Main: end");
	}
}
