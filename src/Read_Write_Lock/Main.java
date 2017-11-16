package Read_Write_Lock;

public class Main
{
	public static void main(String[] args)
	{
		PricesInfo pricesInfo = new PricesInfo();
		
		// create 5 readers
		Reader[] readers = new Reader[5];
		for (int i = 0; i < 5; i++)
		{
			readers[i] = new Reader(pricesInfo);
			new Thread(readers[i], "Thread-r-" + i).start();
		}
		
		// only one writer
		Writer writer = new Writer(pricesInfo);
		new Thread(writer, "Thread-w").start();
	}
}
