package Chapter4_Thread_Executors_9;

import java.util.concurrent.CompletionService;

// this task used to create report generator
// submit generator to service

// 这只是一个单纯的runnable task
public class ReportRequest implements Runnable
{
	private final String name;
	private final CompletionService<String> service;
	
	public ReportRequest(String name, CompletionService<String> service)
	{
		super();
		this.name = name;
		this.service = service;
	}

	@Override
	public void run()
	{
		// generate report generator task
		ReportGenerator producer = new ReportGenerator(name, "Report");
		// submit to service
		System.out.printf("%s ReportRequest: submit report generator to service\n",
				name);
		service.submit(producer);
	}

}
