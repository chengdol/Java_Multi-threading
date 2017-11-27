package CountDownLatch;

import java.util.concurrent.TimeUnit;

public class Participant implements Runnable
{
	private VideoConference conference;
	private String name;
	
	public Participant(VideoConference conference, String name)
	{
		this.conference = conference;
		this.name = name;
	}
	
	@Override
	public void run()
	{
		// wait for random time
		long duration = (long)(Math.random() * 1000);
		try
		{
			TimeUnit.MILLISECONDS.sleep(duration);
			conference.arrive(name);
		} catch (InterruptedException e)
		{
			e.printStackTrace();
		}
	}

}
