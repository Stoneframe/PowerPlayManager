package model;

import java.util.LinkedList;
import java.util.List;

import evaluators.PlayerEvaluator;
import evaluators.handball.HandballPlayerEvaluator;
import model.handball.HandballAttributes;
import parsers.ParseException;

public class PlayerEvaluatorsParser
{
	public List<PlayerEvaluator<HandballAttributes>> parsePlayerEvaluators(
			String textToParse)
			throws ParseException
	{
		try
		{
			List<PlayerEvaluator<HandballAttributes>> evaluators =
					new LinkedList<PlayerEvaluator<HandballAttributes>>();

			String[] lines = textToParse.split("\n");

			for (String line : lines)
			{
				String[] columns = line.split("\t");

				HandballPlayerEvaluator evaluator = new HandballPlayerEvaluator(
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
