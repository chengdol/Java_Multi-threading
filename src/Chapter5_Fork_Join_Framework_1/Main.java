package Chapter5_Fork_Join_Framework_1;

import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;

// updated the price of a list of product
// divide and conquer
// create one ForkJoinPool
// create task extends ForkJoinTask's subclass RecursiveAction class

public class Main
{
	public static void main(String[] args)
	{
		// create 10000 products
		ProductListGenerator generator = new ProductListGenerator();
		List<Product> products = generator.generate(10000);
		
		// create one task
		// init price is 10
		// updated price should be 12
		Task task = new Task(products, 0, products.size(), 0.2);
		
		// create fork join pool
		// this is the default constructor
		// number of threads = number of processors
		ForkJoinPool pool = new ForkJoinPool();
		pool.execute(task);
		
		// write information
		do
		{
			System.out.printf("Main: thread count %s\n",
					pool.getActiveThreadCount());
			
			System.out.printf("Main: thread steal %d\n",
					pool.getStealCount());
			
			System.out.printf("Main: parallelism %d\n",
					pool.getParallelism());
			
			// wait
			try
			{
				TimeUnit.MILLISECONDS.sleep(5);
			} catch (InterruptedException e)
			{
				e.printStackTrace();
			}
		} while (!task.isDone());
		
		// shurdown
		pool.shutdown();
		
		// check task finished without errors
		if (task.isCompletedNormally())
		{
			System.out.println("Main: the task has completed normally");
		}
		
		// check result
		// should be 12
		for (int i = 0; i < products.size(); i++)
		{
			double update = products.get(i).getPrice();
			if (update != 12)
			{
				System.out.printf("Main: wrong price %f\n", update);
			}
		}
		
		System.out.println("Main: end");
	}
}
