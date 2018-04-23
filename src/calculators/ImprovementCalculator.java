package calculators;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class ImprovementCalculator
{
	private final String[] attributes;

	protected ImprovementCalculator(String[] attributes)
	{
		this.attributes = attributes;
	}

	public String calculate(String input)
	{
		int numberOfAttributes = attributes.length;

		double[] sums = new double[numberOfAttributes];
		int[] counts = new int[numberOfAttributes];
		double[] averages = new double[numberOfAttributes];

		String[] rows = input.split("\n");

		Pattern pattern = Pattern.compile("\\d+\\.\\d{2} \\(T:(\\d+\\.\\d{2})\\)");

		for (String row : rows)
		{
			String[] columns = Arrays.copyOfRange(row.split("\t"), 2, 2 + numberOfAttributes);

			for (int i = 0; i < numberOfAttributes; i++)
			{
				if (columns[i] == null)
				{
					return "Unable to calculate";
				}

				Matcher matcher = pattern.matcher(columns[i]);

				if (matcher.matches())
				{
					sums[i] += Double.parseDouble(matcher.group(1));
					counts[i]++;
				}
			}
		}

		for (int i = 0; i < numberOfAttributes; i++)
		{
			if (counts[i] != 0)
			{
				averages[i] = sums[i] / counts[i];
			}
		}

		StringBuilder builder = new StringBuilder();

		for (int i = 0; i < numberOfAttributes; i++)
		{
			builder.append(String.format("%s:\t%.2f\n", attributes[i], averages[i]));
		}

		return builder.toString();
	}
}
