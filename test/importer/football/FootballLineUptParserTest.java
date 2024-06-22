package importer.football;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Parser;
import org.junit.Test;

import model.Player;
import model.football.FootballAttributes;

public class FootballLineUptParserTest
{
	private static final String PLAYER = "<tr>\r\n"
		+ "              <td><input  type='checkbox' id='player_29321934' onClick='toggle_player_nomination(29321934);' ></td>\r\n"
		+ "              <td class='name'><a href='https://soccer.powerplaymanager.com/sv/landsprofil.html?data=swe'><img src='https://appspowerplaymanager.vshcdn.net/images/ppm/flags-new/swe.png' alt=\"Sverige\" title=\"Sverige\" height='16' style='vertical-align: middle' align='absMiddle' /></a>&nbsp;<a href='https://soccer.powerplaymanager.com/sv/spelarprofil.html?data=29321934-noel-lindqvist'>Noel Lindqvist</a></td>\r\n"
		+ "              <td></td>\r\n"
		+ "              <td>34</td>\r\n"
		+ "              <td >1</td>\r\n"
		+ "              <td >107</td>\r\n"
		+ "              <td >1</td>\r\n"
		+ "              <td >1</td>\r\n"
		+ "              <td >1</td>\r\n"
		+ "              <td >35</td>\r\n"
		+ "              <td >35</td>\r\n"
		+ "              <td >35</td>\r\n"
		+ "              <td >35</td>\r\n"
		+ "              <td>36</td>\r\n"
		+ "              <td>251</td>\r\n"
		+ "              <td>L</td>\r\n"
		+ "              <td>32</td>\r\n"
		+ "              <td title='100/100'>100</td>\r\n"
		+ "            </tr>";

	@Test
	public void test()
	{
		FootballLineUpParser parser = new FootballLineUpParser();

		Element text = Jsoup.parse(PLAYER, "", Parser.xmlParser()).firstElementChild();

		Player<FootballAttributes> player = parser.getPlayer(text);

		System.out.println(player);
	}
}
