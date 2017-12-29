package Chapter7_Concurrent_Collections_7;

import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.DoubleAccumulator;
import java.util.concurrent.atomic.LongAdder;

public class Account 
{	
	private final AtomicLong balance;
	// # of operations
	private final LongAdder operations;
	private final DoubleAccumulator commission;
	


	public Account()
	{
		super();
		balance = new AtomicLong();
		operations = new LongAdder();
		commission = new DoubleAccumulator((x, y) -> {
			return x + y * 0.2;
		}, 0);
	}

	// create getter
	public long getBalance()
	{
		return balance.get();
	}

	public long getOperations()
	{
		return operations.longValue();
	}

	public double getCommission()
	{
		return commission.get();
	}
	
	// create setter
	public void setBalance(long balance)
	{
		this.balance.set(balance);;
		operations.reset();
		commission.reset();
	}
	
	public void addAmount(long amount)
	{
		balance.getAndAdd(amount);
		operations.increment();
		commission.accumulate(amount);
	}
	
	public void subtractAmount(long amount)
	{
		balance.getAndAdd(-amount);
		operations.increment();
		commission.accumulate(amount);
	}
	
	
	
	
}
