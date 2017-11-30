package Chapter4_Thread_Executors_3;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

// running multiple tasks and processing the first result return
// from the task list executed
public class Main
{
	public static void main(String[] args)
	{
		String user = "fhengdkl";
		String password = "123456";
		
		// create two user validator system
		UserValidator ADPValidator = new UserValidator("ADP");
		UserValidator BDPValidator = new UserValidator("BDP");
		
		// create task list
		List<ValidatorTask> tasks = new ArrayList<>();
		tasks.add(new ValidatorTask(ADPValidator, user, password));
		tasks.add(new ValidatorTask(BDPValidator, user, password));
		
		// create executor
		// fixed vs cached difference
		ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newCachedThreadPool();
		
		String result;
		
		try
		{
			// run list of task and return the first one successfully executed
			result = executor.invokeAny(tasks);
			System.out.printf("Main: the first returned validator %s\n"
					, result);
		} catch (InterruptedException | ExecutionException e)
		{
			e.printStackTrace();
		}
		
		executor.shutdown();
		System.out.printf("Main: end\n");
	}
}
