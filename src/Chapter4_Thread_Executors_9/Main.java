package Chapter4_Thread_Executors_9;

import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

// separate launching task from processing result
// using CompletionService interface
// using ExecutorCompletionService class 

// two threads create two report generators, submit them to service
// one thread to receive result and process them from the service
public class Main
{
	public static void main(String[] args)
	{
		// create executor
		ExecutorService executor = Executors.newCachedThreadPool();
		// create completion service
		CompletionService<String> service = new ExecutorCompletionService<>(executor);
		
		// create request tasks
		// each task will create their own report generator
		ReportRequest faceReqest = new ReportRequest("Face", service);
		ReportRequest onlineReqest = new ReportRequest("Online", service);
		Thread faceThread = new Thread(faceReqest);
		Thread onlineThread = new Thread(onlineReqest);
		
		// create task result processor
		ReportProcessor resultProcessor = new ReportProcessor(service);
		Thread resultThread = new Thread(resultProcessor);
		
		// start threads
		System.out.println("Main: starting...");
		faceThread.start();
		onlineThread.start();
		resultThread.start();
		
		// join the request tasks
		System.out.println("Main: waiting for report generating...");
		try
		{
			faceThread.join();
			onlineThread.join();
		} catch (InterruptedException e)
		{
			e.printStackTrace();
		}
		
		// shutdown executor and waiting for the processing task
		System.out.println("Main: shutting down the executor");
		executor.shutdown();
		
		// executor中就2个 report request tasks 提供的report generator
		// generator 运行完就结束了
		try
		{
			executor.awaitTermination(1, TimeUnit.DAYS);
		} catch (InterruptedException e)
		{
			e.printStackTrace();
		}
		
		// stop result process thread
		// 因为run 中是一个while 死循环
		resultProcessor.stopProcessing();
		System.out.println("Main: end");
	}
}
