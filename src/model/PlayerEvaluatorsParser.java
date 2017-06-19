package model;

import java.util.LinkedList;
import java.util.List;

import model.parsers.ParseException;

public class PlayerEvaluatorsParser
{
	public List<PlayerEvaluator> parsePlayerEvaluators(String textToParse)
			throws ParseException
	{
		try
		{
			List<PlayerEvaluator> evaluators = new LinkedList<PlayerEvaluator>();

			String[] lines = textToParse.split("\n");

			for (String line : lines)
			{
				String[] columns = line.split("\t");

				PlayerEvaluator evaluator = new PlayerEvaluator(
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
