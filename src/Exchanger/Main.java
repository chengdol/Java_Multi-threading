package Exchanger;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Exchanger;

// one producer and one consumer
// producer add items in buffer then exchange with consumer
// 注意交换的时间点，双方都要到达那里才交换的！否则一方先sleep waiting
public class Main
{
	public static void main(String[] args)
	{	
		List<String> buffer1 = new ArrayList<>();
		List<String> buffer2 = new ArrayList<>();
		
		Exchanger<List<String>> exchanger = new Exchanger<>();
		// create producer and consumer
		Producer p = new Producer(buffer1, exchanger);
		Consumer c = new Consumer(buffer2, exchanger);
		
		// run
		new Thread(p).start();
		new Thread(c).start();
		
	}
}
