package Chapter5_Fork_Join_Framework_5;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;

// cancel the task 
// the first task finished will cancel the remaining tasks
// we create a auxiliary class to cancel tasks in the pool
// cancel will return false if the task cannot be canceled

// 这个demo的结果和描述不一致?见我笔记
public class Main
{
	public static void main(String[] args)
	{
		// create array
		int[] data = new ArrayGenerator().generateArray(20);
		
		// task manager
		TaskManager manager = new TaskManager();
		// pool
		ForkJoinPool pool = new ForkJoinPool();
		// task
		// each number 5
		SearchNumberTask task = new SearchNumberTask(data, 0, data.length, 5, manager);
		
		pool.execute(task);
		pool.shutdown();
		
		// wait for finish
		try
		{
			pool.awaitTermination(1, TimeUnit.DAYS);
		} catch (InterruptedException e)
		{
			e.printStackTrace();
		}
		
		System.out.println("Main: main end");
	}
}
