package Exchanger;

import java.util.List;
import java.util.concurrent.Exchanger;

public class Producer implements Runnable
{
	private List<String> buffer;
	private final Exchanger<List<String>> exchanger;
	
	
	public Producer(List<String> buffer, Exchanger<List<String>> exchanger)
	{
		super();
		this.buffer = buffer;
		this.exchanger = exchanger;
	}

	@Override
	public void run()
	{
		// 10 cycles
		for (int i = 0; i < 10; i++)
		{
			System.out.printf("Producer: cycle %d\n", i);
			for (int j = 0; j < 10; j++)
			{
				String message = "Event-" + ((i * 10) + j);
				System.out.printf("Producer: %s\n", message);
				buffer.add(message);
			}
			
			try
			{
				buffer = exchanger.exchange(buffer);
				System.out.printf("Producer: buffer size %d\n", buffer.size());
			} catch (InterruptedException e)
			{
				e.printStackTrace();
			}
			
		}
	}

}
