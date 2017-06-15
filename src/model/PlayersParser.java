package model;

import java.util.List;

import model.parsers.ParseException;

public interface PlayersParser
{
	public List<Player> parseRoster(String textToParse) throws ParseException;
}
