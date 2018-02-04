package Callback_Future;

import java.util.Random;
import java.util.concurrent.TimeUnit;

// using callback mechanism to implement 
// return value from runnable object in a thread

// another version please see
// Runnable_Future package
public class Main
{
	public Main()
	{
		// placeholder
		
		// don't create thread inside the constructor! 
		// may race condition when callback
	}
	
	private void startTask()
	{
		// pass creator reference into task
		Task task = new Task(this);
		new Thread(task).start();
	}
	
	// this method will be invoked by task in run method
	public void write(Integer ret)
	{
		System.out.println("Result: " + ret);
	}
	
	// main method
	public static void main(String[] args)
	{
		for (int i = 0; i < 5; i++)
		{
			Main main = new Main();
			main.startTask();
		}
	}

}


class Task implements Runnable
{
	private Integer ret;
	private Main callback;
	
	public Task(Main callback)
	{
		super();
		this.callback = callback;
		ret = null;
	}

	@Override
	public void run()
	{
		Random random = new Random();
		ret = random.nextInt(5);
		
		try
		{
			TimeUnit.SECONDS.sleep(ret);
		} catch (InterruptedException e)
		{
			e.printStackTrace();
		}
		
		// callback
		System.out.println("Which thread? " + Thread.currentThread().getName());
		callback.write(ret);
	}
}