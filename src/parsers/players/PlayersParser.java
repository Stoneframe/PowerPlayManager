package parsers.players;

import java.util.List;

import model.Attributes;
import model.Player;
import parsers.ParseException;

public interface PlayersParser<A extends Attributes>
{
	public List<Player<A>> parsePlayers(String textToParse)
			throws ParseException;
}
