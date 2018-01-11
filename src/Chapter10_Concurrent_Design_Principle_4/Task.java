package Chapter10_Concurrent_Design_Principle_4;

public class Task implements Runnable
{

	@Override
	public void run()
	{
		int r = 0;
		for (int i = 0; i < 100000; i++)
		{
			r++;
			r++;
			r *= 2;
		}
	}

}
