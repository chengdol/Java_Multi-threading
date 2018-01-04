package Chapter8_Customizing_Concurrency_5;

import java.util.concurrent.TimeUnit;

// custom task running in schedule thread pool
// 
public class Main
{
	public static void main(String[] args) throws Exception
	{
		MyScheduledThreadPoolExecutor executor = new MyScheduledThreadPoolExecutor(4);
		
		// runnable object
		Task task = new Task();
		System.out.println("Main: start delay task test");
		executor.schedule(task, 1, TimeUnit.SECONDS);
		
		
		TimeUnit.SECONDS.sleep(3);
		System.out.println("===================================");
		
		// start periodic task example
		task = new Task();
		System.out.println("Main: start periodic task test");
		// 已经override了这个方法
		executor.scheduleAtFixedRate(task, 1, 3, TimeUnit.SECONDS);
		
		// let main sleep 10 seconds
		TimeUnit.SECONDS.sleep(10);
		
		executor.shutdown();
		executor.awaitTermination(1, TimeUnit.DAYS);
		
		System.out.println("Main: end");
		
	}
}
