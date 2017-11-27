package CountDownLatch;

import java.util.concurrent.CountDownLatch;

// 我觉得应该把CountDownLatch 单独拿出来做一个class
public class VideoConference implements Runnable
{
	private final CountDownLatch controller;
	
	public VideoConference(int number)
	{
		controller = new CountDownLatch(number);
	}
	
	@Override
	public void run()
	{
		System.out.printf("VideoConference: Init %d participants\n", 
						  controller.getCount());
		// await		
		try
		{
			controller.await();
			// wake up and run
			System.out.printf("VideoConference: All the participants have come\n");
			System.out.printf("VideoConference: Let's start...\n");
			
		} catch (InterruptedException e)
		{
			e.printStackTrace();
		}
	}
	
	public void arrive(String name)
	{
		System.out.printf("%s has arrived\n", name);
		// decrease 1
		controller.countDown();
		System.out.printf("VideoConference: waiting for %d participants\n",
						  controller.getCount());
	}
}
