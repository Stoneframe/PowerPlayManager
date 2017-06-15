package model;

import model.parsers.ParseException;

public interface RosterParser
{
	public Roster parseRoster(String textToParse) throws ParseException;
}
