package Chapter6_Parallel_Reactive_Stream_2;

import java.util.List;
import java.util.Optional;
import java.util.stream.DoubleStream;


// implement reduce operation
// specialized reduce operations in DoubleStream
// count
// sum
// average
// max
// min
// complex reduce
// reduce()
public class Main
{
	public static void main(String[] args)
	{
		List<Double> numbers = DoubleGenerator.generateDoubleList(1000, 1000);
		
		DoubleStream stream = DoubleGenerator.generateStreamFromList(numbers);
		long numberOfElements = stream.parallel().count();
		System.out.printf("Main: the list has %d elements\n", numberOfElements);
		
		stream = DoubleGenerator.generateStreamFromList(numbers);
		double sum = stream.parallel().sum();
		System.out.printf("Main: the sum of list is %f\n", sum);
		
		stream = DoubleGenerator.generateStreamFromList(numbers);
		double ave = stream.parallel().average().getAsDouble();
		System.out.printf("Main: the average of list is %f\n", ave);
		
		stream = DoubleGenerator.generateStreamFromList(numbers);
		double max = stream.parallel().max().getAsDouble();
		System.out.printf("Main: the max of list is %f\n", max);
		
		stream = DoubleGenerator.generateStreamFromList(numbers);
		double min = stream.parallel().min().getAsDouble();
		System.out.printf("Main: the min of list is %f\n", min);
		//================================================================
		
		System.out.println("=================================================");
		System.out.println("Main: reduce first version");
		List<Point> points = PointGenerator.generatePointList(1000);
		// optional object
		Optional<Point> point = points.parallelStream().reduce((p1, p2) -> {
			Point p = new Point();
			p.setX(p1.getX() + p2.getX());
			p.setY(p1.getY() + p2.getY());
			return p;
		});
		
		System.out.printf("Main: sum x is %f, sum y is %f\n", point.get().getX(),
															  point.get().getY());
		
		System.out.println("=================================================");
		System.out.println("Main: reduce second version");
		// here we use identity value to reduce
		// why use identity?
		double totalX = points.parallelStream().map(p -> p.getX()).reduce(0.0, (p1, p2) -> {
			return p1 + p2;
		});
		System.out.printf("Main: the total sum of X is %f\n", totalX);
		
		
		System.out.println("=================================================");
		System.out.println("Main: reduce third version");
		Integer value = 0;
		// here v 的初始值就时value，本身就是value!
		// 见笔记
		value = points.parallelStream().reduce(value, (v, p) -> {
			if (p.getX() > 0.5)
			{
				return v + 1;
			}
			return v;
		}, (v1, v2) -> {
			return v1 + v2;
		});
		System.out.printf("Main: the number of point that x value bigger than 0.5 is %d\n",
				value);
		
	}
}
