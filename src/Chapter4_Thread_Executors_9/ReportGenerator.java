package Chapter4_Thread_Executors_9;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

// simulate a report generator
public class ReportGenerator implements Callable<String>
{
	private final String sender;
	private final String title;
	
	public ReportGenerator(String sender, String title)
	{
		super();
		this.sender = sender;
		this.title = title;
	}


	@Override
	public String call() throws Exception
	{
		try
		{
			// simulate generating report
			long duration = (long)(Math.random() * 5000);
			TimeUnit.MILLISECONDS.sleep(duration);
		} catch (InterruptedException e)
		{
			e.printStackTrace();
		}
		return sender + " - " + title;
	}

}
