package parsers.players;

import java.util.List;

import model.Attributes;
import model.Player;
import parsers.ParseException;

public abstract class PlayersParser<A extends Attributes>
{
	public abstract String getName();

	public abstract List<Player<A>> parsePlayers(String textToParse)
			throws ParseException;

	@Override
	public String toString()
	{
		return getName();
	}
}
