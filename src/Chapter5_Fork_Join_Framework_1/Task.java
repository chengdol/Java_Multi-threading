package Chapter5_Fork_Join_Framework_1;

import java.util.List;
import java.util.concurrent.RecursiveAction;

// we do not need return a result
// so extends the recursiveAction class
public class Task extends RecursiveAction
{
	private List<Product> products;
	private int first;
	private int last;
	private double increment;
	
	
	public Task(List<Product> products, int first, int last, double increment)
	{
		super();
		this.products = products;
		this.first = first;
		this.last = last;
		this.increment = increment;
	}

	@Override
	protected void compute()
	{
		if (last - first < 10)
		{
			// update price
			updatePrices();
		}
		else
		{
			// divide and conquer
			int mid = (last + first) / 2;
			System.out.printf("Task: current queued forked tasks  %d\n",
					getQueuedTaskCount());
			
			// create 2 new tasks
			Task left = new Task(products, first, mid + 1, increment);
			Task right = new Task(products, mid + 1, last, increment);
			// execute in fork pool
			// this is a synchronous call
			// that means current task waiting for its subtasks completed
			// at the same time when waiting, look for other tasks to execute
			
			// return when task completed!
			// here we ignore the return value
			invokeAll(left, right);
			
		}
	}

	private void updatePrices()
	{
		for (int i = first; i < last; i++)
		{
			Product product = products.get(i);
			// update price
			product.setPrice(product.getPrice() * (1 + increment));
		}
	}

}
