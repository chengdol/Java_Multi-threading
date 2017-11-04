package Thread_factory;

public class Main
{
	public static void main(String[] args)
	{
		MyThreadFactory factory = new MyThreadFactory("factory");
		Runnable task = new Task();
		
		System.out.println("Created and start the threads");
		for (int i = 0; i < 10; i++)
		{
			factory.newThread(task).start();
		}
		
		// print statistic info
		System.out.println("Factory statistic:");
		System.out.println(factory.getStats());
	}
}
