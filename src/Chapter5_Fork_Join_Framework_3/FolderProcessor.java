package Chapter5_Fork_Join_Framework_3;

import java.util.List;
import java.util.concurrent.CountedCompleter;

public class FolderProcessor extends CountedCompleter<List<String>>
{
	
	@Override
	public void compute()
	{
			
	}
	
	@Override
	public void onCompletion(CountedCompleter<?> caller)
	{
		super.onCompletion(caller);
	}

}
