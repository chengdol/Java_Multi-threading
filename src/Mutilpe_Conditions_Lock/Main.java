package Mutilpe_Conditions_Lock;

public class Main 
{
	public static void main(String[] args)
	{
		
		FileMock file = new FileMock(100, 10);
		Buffer buffer = new Buffer(20);
		
		// create one producer thread
		Producer p = new Producer(file, buffer);
		Thread pt = new Thread(p, "Producer");
		// create 3 consumers threads
		Consumer[] c = new Consumer[3];
		Thread[] ct = new Thread[3];
		for (int i = 0; i < 3; i++)
		{
			c[i] = new Consumer(buffer);
			ct[i] = new Thread(c[i], "Consumer-" + i);
		}
		
		// run threads
		pt.start();
		for (int i = 0; i < 3; i++)
		{
			ct[i].start();
		}
	}
}
