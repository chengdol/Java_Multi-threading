package Chapter4_Thread_Executors_1;

import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

// deal with rejected tasks
public class RejectedTaskController implements RejectedExecutionHandler
{

	@Override
	public void rejectedExecution(Runnable r, ThreadPoolExecutor executor)
	{
		System.out.printf("RejectedTaskController: task %s has been rejected\n",
						 r.toString());
		System.out.printf("RejectedTaskController: %s\n",
						 executor.toString());
		System.out.printf("RejectedTaskController: terminating... %s\n",
						 executor.isTerminating());
		System.out.printf("RejectedTaskController: terminated %s\n",
						 executor.isTerminated());
	}

}
