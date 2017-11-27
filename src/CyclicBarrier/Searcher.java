package CyclicBarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class Searcher implements Runnable
{
	private final MatrixMock mock;
	private final Results results;
	// search range
	private final int firstRow;
	private final int lastRow;
	// target number
	private final int target;
	// cyclic barrier
	private final CyclicBarrier barrier;
	
	public Searcher(MatrixMock mock, Results results, int firstRow, int lastRow, int target, CyclicBarrier barrier)
	{
		super();
		this.mock = mock;
		this.results = results;
		this.firstRow = firstRow;
		this.lastRow = lastRow;
		this.target = target;
		this.barrier = barrier;
	}

	@Override
	public void run()
	{
		System.out.printf("%s: processing line from %d to %d\n",
						  Thread.currentThread().getName(),
						  firstRow,
						  lastRow);
		int counter = 0;
		for (int i = firstRow; i < lastRow; i++)
		{
			int[] row = mock.getRow(i);
			for (int n : row)
			{
				if (n == target) { counter++; }
			}
			results.setDate(i, counter);
			counter = 0;
		}
		
		System.out.printf("%s: line processed\n",
						 Thread.currentThread().getName());
		
		// wait until specified number of threads finish something 
		try
		{
			barrier.await();
			// can do something later...
			System.out.printf("%s: wake up for next steps\n",
							 Thread.currentThread().getName());
			
		} catch (InterruptedException e)
		{
			e.printStackTrace();
		} catch (BrokenBarrierException e)
		{
			e.printStackTrace();
		}
	}

}
