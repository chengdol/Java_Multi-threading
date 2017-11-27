package CyclicBarrier;

import java.util.concurrent.CyclicBarrier;

// 这里实现了一个divide and conquer的计算任务
// 有5个threads 分别在矩阵中找一个数，最后汇总这个数有多少
public class Main
{
	public static void main(String[] args)
	{
		final int ROWS = 10000;
		final int COLUMNS = 1000;
		final int TARGET = 1;
		final int PARTICIPANTS = 5;
		final int LINE_PER_PARTICIPANT = 2000;
		
		MatrixMock mock = new MatrixMock(ROWS, COLUMNS, TARGET);
		Results results = new Results(ROWS);
		
		Grouper grouper = new Grouper(results);
		// 5 participants and add runnable component
		// 到达阈值后, 会create new thread to run grouper
		CyclicBarrier barrier = new CyclicBarrier(PARTICIPANTS, grouper);
		
		for (int i = 0; i < PARTICIPANTS; i++)
		{
			Searcher searcher = new Searcher(mock, results, 
											i * LINE_PER_PARTICIPANT, 
											i * LINE_PER_PARTICIPANT + LINE_PER_PARTICIPANT
											, TARGET
											, barrier);
			new Thread(searcher).start();
		}
		System.out.println("Main thread finished...");
	}
}
