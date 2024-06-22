package importer.football;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Parser;
import org.junit.Test;

import model.Player;
import model.Side;
import model.football.FootballAttributes;
import model.football.FootballPlayer;

public class FootballMarketParserTest
{
	private static final String PLAYER_1 = "<tr >\r\n"
		+ "        <td  class='left_align'>\r\n"
		+ "        <a href='https://soccer.powerplaymanager.com/sv/landsprofil.html?data=hun'><img src='https://appspowerplaymanager.vshcdn.net/images/ppm/flags-new/hun.png' alt=\"Ungern\" title=\"Ungern\" height='16' style='vertical-align: middle' align='absMiddle' /></a>&nbsp;<a href='https://soccer.powerplaymanager.com/sv/spelarprofil.html?data=29848662-benjamin-bartha'>Benjamin Bartha</a>\r\n"
		+ "        <div align='center' style='color: gray;'>\r\n"
		+ "        15:50:26\r\n"
		+ "           (<span id='offen_deadline_29848662'></span>)\r\n"
		+ "            <input type='hidden' value='7141' id='time_offen_deadline_29848662'>\r\n"
		+ "            <script>\r\n"
		+ "              runSeconds('time_offen_deadline_29848662', 'offen_deadline_29848662', \"Deadline\");\r\n"
		+ "            </script>\r\n"
		+ "          <br />Kostnad: 8 500 000\r\n"
		+ "        </div>\r\n"
		+ "        </td>\r\n"
		+ "        <td  title='Ålder'>30</td>\r\n"
		+ "        <td  title='Genomsnittlig kvalité'>81</td>\r\n"
		+ "        <td  title='Karriärlängd'><span title='Minimal'>1/6</span></td>\r\n"
		+ "   <td  title='Målvakt'>45<span class='kva'>73</span></td><td  title='Försvar'>49<span class='kva'>85</span></td><td  title='Mittfält'>581<span class='kva'>98</span></td><td  title='Offensiv'>45<span class='kva'>85</span></td><td  title='Skott'>221<span class='kva'>73</span></td><td  title='Passning'>417<span class='kva'>77</span></td><td  title='Teknik'>408<span class='kva'>74</span></td><td  title='Snabbhet'>188<span class='kva'>68</span></td><td  title='Nick'>177<span class='kva'>87</span></td>\r\n"
		+ "        <td  title='Erfarenhet'>90</td>\r\n"
		+ "        <td  title='Total skicklighet'>2131</td>\r\n"
		+ "        <td  title='Favoritsida'>R</td>\r\n"
		+ "      </tr>";

	private static final String PLAYER_2 = "<tr>\r\n"
		+ " <td class=\"left_align tr1td1\"><a href=\"https://soccer.powerplaymanager.com/sv/landsprofil.html?data=cze\"> <img src=\"https://appspowerplaymanager.vshcdn.net/images/ppm/flags-new/cze.png\" alt=\"Tjeckien\" title=\"Tjeckien\" height=\"16\" style=\"vertical-align: middle\" align=\"absMiddle\"> </a> &nbsp; <a href=\"https://soccer.powerplaymanager.com/sv/spelarprofil.html?data=30554650-borek-ribar\"> Bořek Ribár </a>\r\n"
		+ "  <div align=\"center\" style=\"color: gray;\">\r\n"
		+ "   17:45:00 ( <span id=\"offen_deadline_30554650\"> 00:22:59 </span> ) <input type=\"hidden\" value=\"1378\" id=\"time_offen_deadline_30554650\">\r\n"
		+ "   <script>\r\n"
		+ "//<![CDATA[\r\n"
		+ "\r\n"
		+ "              runSeconds('time_offen_deadline_30554650', 'offen_deadline_30554650', \"Deadline\");\r\n"
		+ "            \r\n"
		+ "//]]>\r\n"
		+ "                        </script>\r\n"
		+ "   <br>\r\n"
		+ "    Kostnad: 231 525\r\n"
		+ "  </div></td>\r\n"
		+ " <td title=\"Ålder\" class=\"tr1td2\">20</td>\r\n"
		+ " <td title=\"Genomsnittlig kvalité\" class=\"tr1td1\">77</td>\r\n"
		+ " <td title=\"Karriärlängd\" class=\"tr1td2\"><span title=\"bra\"> 4/6 </span></td>\r\n"
		+ " <td title=\"Målvakt\" class=\"tr1td1\">42 <span class=\"kva\"> 77 </span></td>\r\n"
		+ " <td title=\"Försvar\" class=\"tr1td2\">271 <span class=\"kva\"> 83 </span></td>\r\n"
		+ " <td title=\"Mittfält\" class=\"tr1td1\">40 <span class=\"kva\"> 61 </span></td>\r\n"
		+ " <td title=\"Offensiv\" class=\"tr1td2\">37 <span class=\"kva\"> 73 </span></td>\r\n"
		+ " <td title=\"Skott\" class=\"tr1td1\">50 <span class=\"kva\"> 52 </span></td>\r\n"
		+ " <td title=\"Passning\" class=\"tr1td2\">176 <span class=\"kva\"> 64 </span></td>\r\n"
		+ " <td title=\"Teknik\" class=\"tr1td1\">176 <span class=\"kva\"> 73 </span></td>\r\n"
		+ " <td title=\"Snabbhet\" class=\"tr1td2\">230 <span class=\"kva\"> 91 </span></td>\r\n"
		+ " <td title=\"Nick\" class=\"tr1td1\">148 <span class=\"kva\"> 85 </span></td>\r\n"
		+ " <td title=\"Erfarenhet\" class=\"tr1td2\">2</td>\r\n"
		+ " <td title=\"Total skicklighet\" class=\"tr1td1\">1170</td>\r\n"
		+ " <td title=\"Favoritsida\" class=\"tr1td2\">R</td>\r\n"
		+ "</tr>";

	@Test
	public void test1()
	{
		FootballMarketParser parser = new FootballMarketParser();

		Element text = Jsoup.parse(PLAYER_1, "", Parser.xmlParser()).firstElementChild();

		Player<FootballAttributes> player = parser.getPlayer(text);

		FootballAttributes attributes = new FootballAttributes();
		
		attributes.setGoa(45);
		attributes.setDef(49);
		attributes.setMid(581);
		attributes.setOff(45);
		attributes.setSho(221);
		attributes.setPas(417);
		attributes.setTec(408);
		attributes.setSpe(188);
		attributes.setHea(177);
		
		attributes.setQGoa(73);
		attributes.setQDef(85);
		attributes.setQMid(98);
		attributes.setQOff(85);
		attributes.setQSho(73);
		attributes.setQPas(77);
		attributes.setQTec(74);
		attributes.setQSpe(68);
		attributes.setQHea(87);
		
		Player<FootballAttributes> expectedPlayer = new FootballPlayer(
			"Benjamin Bartha",
			"Ungern",
			30,
			1,
			Side.RIGHT,
			attributes,
			90,
			0,
			100,
			0);

		System.out.println(player);
		
		assertEquals(expectedPlayer, player);
	}

	@Test
	public void test2()
	{
		FootballMarketParser parser = new FootballMarketParser();

		Element text = Jsoup.parse(PLAYER_2, "", Parser.xmlParser()).firstElementChild();

		Player<FootballAttributes> player = parser.getPlayer(text);

		FootballAttributes attributes = new FootballAttributes();
		
		attributes.setGoa(42);
		attributes.setDef(271);
		attributes.setMid(40);
		attributes.setOff(37);
		attributes.setSho(50);
		attributes.setPas(176);
		attributes.setTec(176);
		attributes.setSpe(230);
		attributes.setHea(148);
		
		attributes.setQGoa(77);
		attributes.setQDef(83);
		attributes.setQMid(61);
		attributes.setQOff(73);
		attributes.setQSho(52);
		attributes.setQPas(64);
		attributes.setQTec(73);
		attributes.setQSpe(91);
		attributes.setQHea(85);
		
		Player<FootballAttributes> expectedPlayer = new FootballPlayer(
			"Bořek Ribár",
			"Tjeckien",
			20,
			4,
			Side.RIGHT,
			attributes,
			2,
			0,
			100,
			0);

		System.out.println(player);
		
		assertEquals(expectedPlayer, player);
	}
}
