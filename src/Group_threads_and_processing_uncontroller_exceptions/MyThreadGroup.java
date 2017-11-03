package Group_threads_and_processing_uncontroller_exceptions;

public class MyThreadGroup extends ThreadGroup
{

	public MyThreadGroup(String name)
	{
		super(name);
	}

	@Override
	public void uncaughtException(Thread t, Throwable e)
	{
		super.uncaughtException(t, e);
		System.out.printf("The thread %d is thrown an Exception!\n", t.getId());
		
		e.printStackTrace(System.out);
		System.out.println("Terminate the rest of threads\n");
		// method from ThreadGroup
		interrupt();
	}
}