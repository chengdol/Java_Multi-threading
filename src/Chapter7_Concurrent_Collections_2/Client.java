package Chapter7_Concurrent_Collections_2;

import java.util.Date;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;

// add element into deque
public class Client implements Runnable
{
	private final LinkedBlockingDeque<String> deque;
	
	public Client(LinkedBlockingDeque<String> deque)
	{
		super();
		this.deque = deque;
	}


	@Override
	public void run()
	{
		for (int i = 0; i < 3; i++)
		{
			for (int j = 0; j < 5; j++)
			{
				StringBuilder sb = new StringBuilder();
				sb.append(i).append(":").append(j);
				try
				{
					deque.put(sb.toString());
				} catch (InterruptedException e)
				{
					e.printStackTrace();
				}
				System.out.printf("Client: put %s at %s\n"
						, sb.toString()
						, new Date());
			}
			// wait
			try
			{
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e)
			{
				e.printStackTrace();
			}
		}
		System.out.println("Client: end");
	}

}
