package evaluators;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CareerLengthCurve
{
	private static final Map<Integer, List<Integer>> map = new HashMap<Integer, List<Integer>>()
	{
		private static final long serialVersionUID = -1065609729389496722L;

		{
			put(15, Arrays.asList(5, 6));
			put(16, Arrays.asList(5, 6));
			put(17, Arrays.asList(4, 5, 6));
			put(18, Arrays.asList(4, 5, 6));
			put(19, Arrays.asList(4, 5));
			put(20, Arrays.asList(4, 5));
			put(21, Arrays.asList(3, 4, 5));
			put(22, Arrays.asList(3, 4, 5));
			put(23, Arrays.asList(2, 3, 4));
			put(24, Arrays.asList(2, 3, 4));
			put(25, Arrays.asList(1, 2, 3, 4));
			put(26, Arrays.asList(1, 2, 3, 4));
			put(27, Arrays.asList(1, 2, 3));
			put(28, Arrays.asList(0, 1, 2, 3));
			put(29, Arrays.asList(0, 1, 2, 3));
			put(30, Arrays.asList(0, 1, 2, 3));
			put(31, Arrays.asList(0, 1, 2));
			put(32, Arrays.asList(0, 1, 2));
			put(33, Arrays.asList(0, 1, 2));
			put(34, Arrays.asList(0, 1));
			put(35, Arrays.asList(0, 1));
		}
	};

	private final Map<Integer, Integer> cls = new HashMap<>();

	private final int end;

	public CareerLengthCurve(int age, int cl)
	{
		double threshold = getThreshold(age - 1, cl);

		end = populate(threshold);
	}

	public int get(int age)
	{
		return cls.getOrDefault(age, 0);
	}

	public int getEnd()
	{
		return end;
	}

	private int populate(double threshold)
	{
		int end = 0;

		for (int age = 15; age < 40; age++)
		{
			int cl = calcCl(threshold, age);

			cls.put(age, cl);

			if (cl == 0 && end == 0)
			{
				end = age;
			}
		}

		return end;
	}

	private int calcCl(double threshold, int age)
	{
		List<Integer> cls = getCls(age);

		for (int cl : cls)
		{
			double x = getThreshold(age, cl);

			if (x >= threshold)
			{
				return cl;
			}
		}

		return cls.get(cls.size() - 1);
	}

	private static double getThreshold(int age, int cl)
	{
		double min, max;

		switch (cl)
		{
			case 6:
				min = 11;
				max = 18;
				break;
			case 5:
				min = 13;
				max = 22;
				break;
			case 4:
				min = 17;
				max = 26;
				break;
			case 3:
				min = 21;
				max = 30;
				break;
			case 2:
				min = 23;
				max = 33;
				break;
			case 1:
				min = 25;
				max = 35;
				break;
			case 0:
				min = 28;
				max = 38;
				break;
			default:
				return 0;
		}

		return (age - min) / (max - min);
	}

	private static List<Integer> getCls(int age)
	{
		return map.getOrDefault(age, Arrays.asList(0));
	}
}
