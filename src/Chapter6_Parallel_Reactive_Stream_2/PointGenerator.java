package Chapter6_Parallel_Reactive_Stream_2;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PointGenerator
{
	public static List<Point> generatePointList(int size)
	{
		List<Point> ret = new ArrayList<>();
		Random random = new Random();
		for (int i = 0; i < size; i++)
		{
			Point p = new Point();
			p.setX(random.nextDouble());
			p.setY(random.nextDouble());
			ret.add(p);
		}
		
		return ret;
	}
}
