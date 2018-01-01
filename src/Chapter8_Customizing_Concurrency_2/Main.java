package Chapter8_Customizing_Concurrency_2;

import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

// use PriorityBlockingQueue as our work queue
// implement comparable interface for the task

// 由于有4个worker thread, 所以显示的顺序可能有点误差，但整体确实
// 是按照优先运行的
public class Main
{
	public static void main(String[] args)
	{
		ThreadPoolExecutor executor = new ThreadPoolExecutor(4, 4, 1, TimeUnit.SECONDS
				, new PriorityBlockingQueue<>());
		
		// send 10 tasks
		for (int i = 0; i < 10; i++)
		{
			MyPriorityTask myPriorityTask = new MyPriorityTask("task-" + i, i);
			executor.execute(myPriorityTask);
		}
		
		// sleep 1 second
		try
		{
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e)
		{
			e.printStackTrace();
		}
		
		// send 10 more tasks
		for (int i = 10; i < 20; i++)
		{
			MyPriorityTask myPriorityTask = new MyPriorityTask("task-" + i, i);
			executor.execute(myPriorityTask);
		}
		
		// shutdown
		executor.shutdown();
		
		// wait termination
		try
		{
			executor.awaitTermination(1, TimeUnit.DAYS);
		} catch (InterruptedException e)
		{
			e.printStackTrace();
		}
		
		System.out.println("Main: end");
	}
}
