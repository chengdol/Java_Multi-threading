package Chapter5_Fork_Join_Framework_2;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;

// looks for a word in document
// return the number of appearances of the word
// we have two tasks that extends RecursiveTas class
// all the tasks will be executed in common pool
public class Main
{
	public static void main(String[] args)
	{
		int LINE_NUMBER = 30;
		int WORD_PER_LINE = 35;
		String word = "the";
		
		String[][] doc = new DocumentMock().generateDocument(LINE_NUMBER, 
				WORD_PER_LINE, 
				word);
		
		System.out.println("Main: starting...");
		DocumentTask task = new DocumentTask(doc, 0, LINE_NUMBER, word);
		// default pool
		ForkJoinPool pool = ForkJoinPool.commonPool();
		pool.execute(task);
		
		do
		{
			System.out.println("-----------------------------");
			System.out.printf("Main: active threads %d\n",
					pool.getActiveThreadCount());
			System.out.printf("Main: task count %d\n",
					pool.getQueuedTaskCount());
			System.out.printf("Main: steal count %d\n",
					pool.getStealCount());
			
			try
			{
				TimeUnit.MILLISECONDS.sleep(1000);
			} catch (InterruptedException e)
			{
				e.printStackTrace();
			}
			
		} while (!task.isDone());
		
		// shutdown
		pool.shutdown();
		
		// wait task completed
		try
		{
			boolean val = pool.awaitTermination(1, TimeUnit.DAYS);
			System.out.printf("Main: await termination is %s\n", val);
		} catch (InterruptedException e)
		{
			e.printStackTrace();
		}
		// print result
		try
		{
			System.out.printf("Main: the word appears %d in the document\n",
					task.get());
		} catch (InterruptedException | ExecutionException e)
		{
			e.printStackTrace();
		}
		
		System.out.println("Main: end");
	}	
}
