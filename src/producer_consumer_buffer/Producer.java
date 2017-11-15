package producer_consumer_buffer;

public class Producer implements Runnable
{
	private EventStorage storage;
	
	public Producer(EventStorage storage)
	{
		super();
		this.storage = storage;
	}

	@Override
	public void run()
	{
		for (int i = 0; i < 100; i++)
		{
			storage.set();
		}
	}
}
