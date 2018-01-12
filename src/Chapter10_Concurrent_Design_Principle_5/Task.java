package Chapter10_Concurrent_Design_Principle_5;

public class Task implements Runnable
{

	@Override
	public void run()
	{
		System.out.printf("%s, get connection...\n"
				, Thread.currentThread().getName());
		
		DBConnectionOK connectionOK = DBConnectionOK.getConnection();
		
		System.out.printf("%s, end\n"
				, Thread.currentThread().getName());
	}

}
