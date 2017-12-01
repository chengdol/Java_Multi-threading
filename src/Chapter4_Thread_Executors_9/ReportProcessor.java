package Chapter4_Thread_Executors_9;

import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

// used to get the result of the report generator tasks
public class ReportProcessor implements Runnable
{
	private final CompletionService<String> service;
	// ensure all threads access the actual value of end
	private volatile boolean end;
	
	public ReportProcessor(CompletionService<String> service)
	{
		super();
		this.service = service;
		this.end = false;
	}

	@Override
	public void run()
	{
		while (!end)
		{
			try
			{
				// use poll() method to get next finished task in service
				Future<String> finish = service.poll(150, TimeUnit.MILLISECONDS);
				// null if no task finished
				if (finish != null)
				{
					String result = finish.get();
					System.out.printf("ReportPorcessor: %s received \n",
							result);
				}
				
			} catch (InterruptedException | ExecutionException e)
			{
				e.printStackTrace();
			}
		}
		
		System.out.println("ReportProcessor: all resport processes end");
	}
	
	public void stopProcessing()
	{
		end = true;
	}
}
