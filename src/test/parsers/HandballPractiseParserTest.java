package test.parsers;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import model.Player;
import model.handball.HandballAttributes;
import parsers.ParseException;
import parsers.players.handball.HandballPractisePlayersParser;

public class HandballPractiseParserTest
{

	private HandballPractisePlayersParser parser;

	@Before
	public void setUp() throws Exception
	{
		parser = new HandballPractisePlayersParser();
	}

	@Test
	public void parsePractiseWithPosition() throws ParseException
	{
		// TODO
	}

	@Test
	public void parsePractiseWithoutPosition() throws ParseException
	{
		// TODO
	}

	@Test
	public void parsePractiseOnCampWithPosition() throws ParseException
	{
		String input =
			"Belgien Frank Ponnet [Spelaren är på träningsläger] 	GK 	28 	Scoutade 	1/6 	44988	4886	4576	38286	24785	13653	15280	4255 	0.49";

		List<Player<HandballAttributes>> parsedPlayers = parser.parsePlayers(input);

		assertEquals(1, parsedPlayers.size());
		Player<HandballAttributes> player = parsedPlayers.get(0);

		assertEquals("Frank Ponnet", player.getName());
		assertEquals(28, player.getAge());
		assertEquals(0.49, player.getTraining(), 0);
	}

	@Test
	public void parsePractiseOnCampWithoutPosition() throws ParseException
	{
		String input =
			"Sverige Johan Ottosson [Spelaren är på träningsläger] 		21 	Scoutade 	3/6 	39480	2775	2758	33485	20686	13173	16175	2578 	2.00 	";

		List<Player<HandballAttributes>> parsedPlayers = parser.parsePlayers(input);

		assertEquals(1, parsedPlayers.size());
		Player<HandballAttributes> player = parsedPlayers.get(0);

		assertEquals("Johan Ottosson", player.getName());
		assertEquals(21, player.getAge());
		assertEquals(2.0, player.getTraining(), 0);
	}
}
