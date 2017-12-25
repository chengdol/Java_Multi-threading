package Chapter7_Concurrent_Collections_4;

import java.util.Date;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

// implement delayed interface for delay queue
public class Event implements Delayed
{
	// activation date
	private final Date startDate;

	public Event(Date startDate)
	{
		super();
		this.startDate = startDate;
	}

	// order from small startDate to large
	@Override
	public int compareTo(Delayed o)
	{
		long diff = this.getDelay(TimeUnit.NANOSECONDS) 
				- o.getDelay(TimeUnit.NANOSECONDS);
		if (diff < 0)
		{
			return -1;
		} 
		else if (diff == 0)
		{
			return 0;
		}

		return 1;
	}

	// return the number of unit from now to activation date
	@Override
	public long getDelay(TimeUnit unit)
	{
		Date now = new Date();
		// diff is millisecond unit
		long diff = startDate.getTime() - now.getTime();
		// delayed queue use nano seconds!
		return unit.convert(diff, TimeUnit.MILLISECONDS);
	}

}
