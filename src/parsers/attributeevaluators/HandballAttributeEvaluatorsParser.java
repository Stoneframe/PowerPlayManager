package parsers.attributeevaluators;

import java.util.LinkedList;
import java.util.List;

import evaluators.AttributeEvaluator;
import evaluators.handball.HandballAttributeEvaluator;
import model.handball.HandballAttributes;
import parsers.ParseException;

public class HandballAttributeEvaluatorsParser
{
	public List<AttributeEvaluator<HandballAttributes>> parseAttributeEvaluators(String textToParse)
			throws ParseException
	{
		try
		{
			List<AttributeEvaluator<HandballAttributes>> evaluators =
					new LinkedList<AttributeEvaluator<HandballAttributes>>();

			String[] lines = textToParse.split("\n");

			for (String line : lines)
			{
				String[] columns = line.split("\t");

				HandballAttributeEvaluator evaluator = new HandballAttributeEvaluator(
					columns[0],
					Integer.parseInt(columns[1]),
					Integer.parseInt(columns[2]),
					Integer.parseInt(columns[3]),
					Integer.parseInt(columns[4]),
					Integer.parseInt(columns[5]),
					Integer.parseInt(columns[6]),
					Integer.parseInt(columns[7]),
					Integer.parseInt(columns[8]));

				evaluators.add(evaluator);
			}

			return evaluators;
		}
		catch (Exception e)
		{
			throw new ParseException(e);
		}
	}
}
