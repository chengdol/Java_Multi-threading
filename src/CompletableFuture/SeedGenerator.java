package CompletableFuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

public class SeedGenerator implements Runnable
{
	// completable future object
	private CompletableFuture<Integer> communicator;
	
	
	public SeedGenerator(CompletableFuture<Integer> communicator)
	{
		super();
		this.communicator = communicator;
	}

	@Override
	public void run()
	{
		System.out.printf("SeedGenerator: generating seed...\n");
		// wait 3 seconds
		try
		{
			TimeUnit.SECONDS.sleep(3);
		} catch (InterruptedException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int seed = (int)(Math.random() * 10);
		System.out.printf("SeedGenerator: seed generated %d\n", seed);
		
		// complete and pass result
		communicator.complete(seed);
	}

}
