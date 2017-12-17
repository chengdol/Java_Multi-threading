package Chapter7_Concurrent_Collections_2;

import java.util.Date;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;


// put and take element into and from deque
// blocking operation
public class Main
{
	public static void main(String[] args)
	{
		// fixed size deque
		LinkedBlockingDeque<String> deque = new LinkedBlockingDeque<>(3);
		
		Client client = new Client(deque);
		new Thread(client).start();
		
		// take element in deque
		for (int i = 0; i < 5; i++)
		{
			for (int j = 0; j < 3; j++)
			{
				try
				{
					String res = deque.take();
					System.out.printf("Main: take %s at %s\n"
							, res
							, new Date());
				} catch (InterruptedException e)
				{
					e.printStackTrace();
				}
			}
			try
			{
				TimeUnit.MILLISECONDS.sleep(400);
			} catch (InterruptedException e)
			{
				e.printStackTrace();
			}
		}
		System.out.println("Main: end");
		
		
	}
}
