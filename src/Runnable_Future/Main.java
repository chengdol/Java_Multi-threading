package Runnable_Future;

import java.util.Random;
import java.util.concurrent.TimeUnit;


// 这个例子用runnable实现了Future效果，有返回值
public class Main
{

	public static void main(String[] args) throws InterruptedException
	{
		Task[] tasks = new Task[5];
		for (int i = 0; i < tasks.length; i++)
		{
			tasks[i] = new Task();
			// 后面不管thread的事了
			new Thread(tasks[i]).start();
		}
		// check result
		for (int i = 0; i < tasks.length; i++)
		{
			System.out.printf("Result: %d\n", tasks[i].get());
		}
	}
	

}

class Task implements Runnable
{
	private Integer ret = null;
	
	@Override
	public void run()
	{
		Random random = new Random();
		int tmp = random.nextInt(5);
		try
		{
			TimeUnit.SECONDS.sleep(tmp);
		} catch (InterruptedException e)
		{
			e.printStackTrace();
		}
		ret = tmp;
		// must put inside locks
		// wait up blocked thread, here is main thread
		// why? 因为lock是class 层面的，main thread invokes wait()
		synchronized (this)
		{
			notifyAll();
		}
	}
	
	public synchronized Integer get() throws InterruptedException
	{
		while (ret == null)
		{
			// 谁调用wait 谁block
			System.out.printf("Which thread? %s\n", Thread.currentThread().getName());
			wait();
		}
		return ret;
	}
}
