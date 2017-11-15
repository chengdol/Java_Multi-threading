package producer_consumer_buffer;

public class Main
{
	public static void main(String[] args)
	{
		EventStorage storage = new EventStorage();
		Producer p = new Producer(storage);
		Consumer c = new Consumer(storage);
		
		Thread thread1 = new Thread(p);
		Thread thread2 = new Thread(c);
		
		thread1.start();
		thread2.start();
	}
}
