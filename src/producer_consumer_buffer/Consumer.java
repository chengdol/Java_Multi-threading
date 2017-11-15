package producer_consumer_buffer;

public class Consumer implements Runnable
{
	private EventStorage storage;
	
	
	
	public Consumer(EventStorage storage)
	{
		super();
		this.storage = storage;
	}

	@Override
	public void run()
	{
		for (int i = 0; i < 100 ; i++)
		{
			storage.get();
		}
	}

}
