package model;

import java.util.List;

import parsers.ParseException;

public interface PlayersParser<A extends Attributes>
{
	public List<Player<A>> parsePlayers(String textToParse)
			throws ParseException;
}
