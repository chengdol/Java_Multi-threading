package Chapter6_Parallel_Reactive_Stream_1;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PersonGenerator
{
	public static List<Person> generatePersonList(int size)
	{
		List<Person> ret = new ArrayList<>();
		String firstNames[] = {"Mary","Patricia","Linda",
				"Barbara","Elizabeth","James",
				"John","Robert","Michael",
				"William"};
		String lastNames[] = {"Smith","Jones","Taylor",
				"Williams","Brown","Davies",
				"Evans","Wilson","Thomas",
				"Roberts"};
		
		Random random = new Random();
		for (int i = 0; i < size; i++)
		{
			Person p = new Person(firstNames[random.nextInt(firstNames.length)],
					lastNames[random.nextInt(lastNames.length)]);
			ret.add(p);
		}
		
		return ret;
	}
}
