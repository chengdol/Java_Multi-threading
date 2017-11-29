package Chapter4_Thread_Executors_1;

// demo summary:
// using Executors class to create new ThreadPoolExecuto object
// send 20 tasks to Executor
// shutdown executor
// control the rejected tasks of Executor class
public class Main
{
	public static void main(String[] args)
	{
		Server server = new Server();
		// create 20 tasks
		System.out.println("Main: starting...");
		for (int i = 0; i < 20; i++)
		{
			Task task = new Task("Task-" + i);
			server.executeTask(task);
		}
		
		// call end server must explicitly
		// otherwise won'e end application
		System.out.println("Main: shutting down the executor...");
		server.endServer();
		
		// send new task after shutting down
		System.out.println("Main: sending another task");
		server.executeTask(new Task("RejectedTask"));
		
		System.out.println("Main: end");
	}
}
