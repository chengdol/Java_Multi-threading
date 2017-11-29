package CompletableFuture;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

// 这个例子没太看懂语法上的东西，用到了
// 各个部分是怎么运作的？
// CompletableFuture class
// supplier interface
// function interface
public class Main
{
	public static void main(String[] args)
	{
		System.out.println("Main: start");
		CompletableFuture<Integer> seedFuture = new CompletableFuture<>();
		// execute seed thread
		Thread seedThread = new Thread(new SeedGenerator(seedFuture));
		seedThread.start();
		
		System.out.println("Main: getting the seed");
		int seed = 0;
		try
		{
			// waiting for complete in SeedGenerator object
			seed = seedFuture.get();
		} catch (InterruptedException | ExecutionException e)
		{
			e.printStackTrace();
		}
		System.out.println("Main: the seed is " + seed);
		
		// create another CompletableFuture object
		System.out.println("Main: launching the list of numbers generator");
		NumberListGenerator task = new NumberListGenerator(seed);
		// implements Supplier interface
		CompletableFuture<List<Long>> startFuture = CompletableFuture.supplyAsync(task);
		
		// have three parallel tasks to use result of previous task, the list
		// first 2 tasks perform in functional way
		System.out.println("Main: launch task1");
		CompletableFuture<Long> step1Futrure = startFuture.thenApplyAsync(list -> {
			
			System.out.printf("%s: task1 start\n", Thread.currentThread().getName());
			
			long nearest = 0;
			long distance = Long.MAX_VALUE;
			long tmp = 0;
			for (Long number : list)
			{
				tmp = Math.abs(1000 - number);
				if (tmp < distance)
				{
					distance = tmp;
					nearest = number;
				}
			}
			System.out.printf("%s: task1 result %d\n", Thread.currentThread().getName(),
							 nearest);
			return nearest;
		});
		
		
		System.out.println("Main: launch task2");
		CompletableFuture<Long> step2Futrure = startFuture.thenApplyAsync(list -> 
			list.stream().max(Long::compare).get()
		);
		CompletableFuture<Void> step2result = step2Futrure.thenAccept(max -> {
			System.out.printf("%s: step2 result %d\n", Thread.currentThread().getName(),
							  max);
		});
		
		
		// objected way
		System.out.println("Main: launch task3");
		NumberSelector ns = new NumberSelector();
		CompletableFuture<Long> step3Future = startFuture.thenApplyAsync(ns);
		
		// waiting for the end of three tasks
		System.out.println("Main: waiting for the end of the three tasks");
		CompletableFuture<Void> wait = CompletableFuture.allOf(step1Futrure,
																step2result,
																step3Future);
		CompletableFuture<Void> finalFuture = wait.thenAcceptAsync(param -> {
			System.out.println("Main: finished all");
		});
		
		finalFuture.join();
	}
}
