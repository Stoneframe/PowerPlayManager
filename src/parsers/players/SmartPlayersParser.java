package parsers.players;

import java.util.List;

import model.Attributes;
import model.Player;
import parsers.ParseException;

public class SmartPlayersParser<A extends Attributes>
	extends PlayersParser<A>
{
	private List<PlayersParser<A>> playersParsers;

	public SmartPlayersParser(List<PlayersParser<A>> playersParsers)
	{
		this.playersParsers = playersParsers;
	}

	@Override
	public String getName()
	{
		return "Smart";
	}

	@Override
	public List<Player<A>> parsePlayers(String textToParse) throws ParseException
	{
		for (PlayersParser<A> playersParser : playersParsers)
		{
			try
			{
				return playersParser.parsePlayers(textToParse);
			}
			catch (ParseException e)
			{
				continue;
			}
		}

		throw new ParseException();
	}
}
