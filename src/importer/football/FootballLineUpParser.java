package importer.football;

import java.util.List;

import org.jsoup.nodes.Document;

import importer.LineUpParser;
import model.Player;
import model.handball.HandballAttributes;

public class FootballLineUpParser
	implements
		LineUpParser<HandballAttributes>
{
	@Override
	public String getAddress()
	{
		return "";
	}

	@Override
	public List<Player<HandballAttributes>> parse(Document document)
	{
		return null;
	}
}
