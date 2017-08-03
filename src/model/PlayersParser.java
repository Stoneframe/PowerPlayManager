package model;

import java.util.List;

import parsers.ParseException;

public interface PlayersParser
{
	public List<Player<?>> parsePlayers(String textToParse)
			throws ParseException;
}
