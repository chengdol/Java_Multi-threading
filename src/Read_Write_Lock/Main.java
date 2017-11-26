package Read_Write_Lock;


// 不要和producer-consumer problem弄混了
// 这里只是读写操作，没有buffer
// 对于producer-consumer problem
// 每次只能一个consumer fetch one cell in buffer
// cannot fetch simultaneously!
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
