package test.parsers;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import model.Player;
import model.Side;
import model.handball.HandballAttributes;
import parsers.ParseException;
import parsers.players.handball.HandballOverviewPlayersParser;

public class HandballOverviewParserTest
{

	private HandballOverviewPlayersParser parser;

	@Before
	public void setUp() throws Exception
	{
		parser = new HandballOverviewPlayersParser();
	}

	@Test
	public void parseOverviewWithPosition() throws ParseException
	{
		String input =
			"Sverige Alexander Ottosson 	CB 	24 	Scoutade 	73 	3/6 	28 	347 	41 	305 	254 	231 	102 	107 	21 	1415 	U";

		List<Player<HandballAttributes>> parsedPlayers = parser.parsePlayers(input);

		assertEquals(1, parsedPlayers.size());
		Player<HandballAttributes> player = parsedPlayers.get(0);

		assertEquals("Alexander Ottosson", player.getName());
		assertEquals(24, player.getAge());
		assertEquals(3, player.getCL());
		assertEquals(21, player.getExperience());
		assertEquals(Side.UNIVERSAL, player.getSide());
	}

	@Test
	public void parseOverviewWithoutPosition() throws ParseException
	{
		String input =
			"Sverige Kim Kjellberg 		32 	Scoutade 	78 	0/6 	15 	367 	256 	29 	112 	208 	275 	107 	75 	1369 	U";

		List<Player<HandballAttributes>> parsedPlayers = parser.parsePlayers(input);

		assertEquals(1, parsedPlayers.size());
		Player<HandballAttributes> player = parsedPlayers.get(0);

		assertEquals("Kim Kjellberg", player.getName());
		assertEquals(32, player.getAge());
		assertEquals(0, player.getCL());
		assertEquals(75, player.getExperience());
		assertEquals(Side.UNIVERSAL, player.getSide());
	}
}
