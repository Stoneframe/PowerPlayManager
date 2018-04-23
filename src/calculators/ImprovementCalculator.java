package calculators;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ImprovementCalculator
{
	private final String[] ATTRIBUTES = new String[]
	{
			"Goa",
			"Fip",
			"Sho",
			"Blk",
			"Pas",
			"Tec",
			"Spe",
			"Agr",
	};
	private final int NUMBER_OF_ATTRIBUTES = ATTRIBUTES.length;

	public String calculate(String input)
	{
		double[] sums = new double[NUMBER_OF_ATTRIBUTES];
		int[] counts = new int[NUMBER_OF_ATTRIBUTES];
		double[] averages = new double[NUMBER_OF_ATTRIBUTES];

		String[] rows = input.split("\n");

		Pattern pattern = Pattern.compile("\\d+\\.\\d{2} \\(T:(\\d+\\.\\d{2})\\)");

		for (String row : rows)
		{
			String[] columns = Arrays.copyOfRange(row.split("\t"), 2, 2 + NUMBER_OF_ATTRIBUTES);

			for (int i = 0; i < NUMBER_OF_ATTRIBUTES; i++)
			{
				Matcher matcher = pattern.matcher(columns[i]);

				if (matcher.matches())
				{
					sums[i] += Double.parseDouble(matcher.group(1));
					counts[i]++;
				}
			}
		}

		for (int i = 0; i < NUMBER_OF_ATTRIBUTES; i++)
		{
			if (counts[i] != 0)
			{
				averages[i] = sums[i] / counts[i];
			}
		}

		StringBuilder builder = new StringBuilder();

		for (int i = 0; i < NUMBER_OF_ATTRIBUTES; i++)
		{
			builder.append(String.format("%s:\t%.2f\n", ATTRIBUTES[i], averages[i]));
		}

		return builder.toString();
	}
}
