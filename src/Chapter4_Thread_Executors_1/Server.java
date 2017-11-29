package Chapter4_Thread_Executors_1;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class Server
{
	private final ThreadPoolExecutor executor;

	public Server()
	{
		super();
		// set number of pool threads
		this.executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(
								Runtime.getRuntime().availableProcessors());
		
		RejectedTaskController controller = new RejectedTaskController();
		// set rejected task handler
		executor.setRejectedExecutionHandler(controller);
	}
	
	public void executeTask(Task task)
	{
		System.out.printf("Server: a task has arrived\n");
		// execute it
		executor.execute(task);
		// write some executor data
		System.out.printf("Server: pool size %d\n",
				executor.getPoolSize());
		System.out.printf("Server: active count %d\n",
				executor.getActiveCount());
		System.out.printf("Server: task count %d\n",
				executor.getTaskCount());
		System.out.printf("Server: completed task count %d\n",
				executor.getCompletedTaskCount());
	}
	
	public void endServer()
	{
		executor.shutdown();
	}
}
