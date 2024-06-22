package importer.handball;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Parser;
import org.junit.Test;

import model.Player;
import model.handball.HandballAttributes;

public class HandballMarketParserTest
{
	private static final String PLAYER = "<tr>\r\n" + 
		" <td class=\"left_align tr1td1\"><a href=\"https://handball.powerplaymanager.com/sv/land-s-profil.html?data=bra\"> <img src=\"https://appspowerplaymanager.vshcdn.net/images/ppm/flags-new/bra.png\" alt=\"Brasilien\" title=\"Brasilien\" height=\"16\" style=\"vertical-align: middle\" align=\"absMiddle\"> </a> &nbsp; <a href=\"https://handball.powerplaymanager.com/sv/spelarprofil.html?data=3956108-jose-marcos-sepetiba\" title=\"José Marcos Sepetiba\"> José Marcos Sepetiba </a>\r\n" +
		"  <div align=\"center\" style=\"color: gray;\">\r\n" + 
		"   16:09:43 ( <span id=\"offer_deadline_3956108\"> 00:33:07 </span> ) <input type=\"hidden\" value=\"1986\" id=\"time_offer_deadline_3956108\">\r\n" + 
		"   <script>\r\n" + 
		"//<![CDATA[\r\n" + 
		"\r\n" + 
		"                    runSeconds('time_offer_deadline_3956108', 'offer_deadline_3956108', 'Deadline');\r\n" + 
		"                  \r\n" + 
		"//]]>\r\n" + 
		"                        </script>\r\n" + 
		"   <br>\r\n" + 
		"    Kostnad: 10 000\r\n" + 
		"  </div></td>\r\n" + 
		" <td title=\"Ålder\" class=\"tr1td2\">15</td>\r\n" +
		" <td class=\"tr1td1\"><img src=\"https://www.powerplaymanager.com/handball/_images/account/icons/scouted_none.png\" title=\"Oscoutade\" alt=\"Oscoutade\" width=\"16\" height=\"16\" border=\"0\"></td>\r\n" + 
		" <td title=\"Genomsnittlig kvalité\" class=\"tr1td2\">73</td>\r\n" +
		" <td title=\"Karriärlängd\" class=\"tr1td1\"><span title=\"perfekt\"> 6/6 </span></td>\r\n" +
		" <td title=\"Målvakt\" class=\"tr1td2\">26 <span class=\"kva\"> 78 </span></td>\r\n" +
		" <td title=\"Utespelare\" class=\"tr1td1\">96 <span class=\"kva\"> 78 </span></td>\r\n" +
		" <td title=\"Skott\" class=\"tr1td2\">39 <span class=\"kva\"> 81 </span></td>\r\n" +
		" <td title=\"Blockering\" class=\"tr1td1\">46 <span class=\"kva\"> 75 </span></td>\r\n" + 
		" <td title=\"Passning\" class=\"tr1td2\">37 <span class=\"kva\"> 67 </span></td>\r\n" + 
		" <td title=\"Teknik\" class=\"tr1td1\">39 <span class=\"kva\"> 83 </span></td>\r\n" + 
		" <td title=\"Snabbhet\" class=\"tr1td2\">33 <span class=\"kva\"> 59 </span></td>\r\n" + 
		" <td title=\"Aggressivitet\" class=\"tr1td1\">58 <span class=\"kva\"> 66 </span></td>\r\n" + 
		" <td title=\"Erfarenhet\" class=\"tr1td2\">0</td>\r\n" + 
		" <td title=\"Total skicklighet\" class=\"tr1td1\">374</td>\r\n" + 
		" <td title=\"Favoritsida\" class=\"tr1td2\">R</td>\r\n" + 
		"</tr>";
	
	@Test
	public void test()
	{
		HandballMarketParser parser = new HandballMarketParser();

		Element text = Jsoup.parse(PLAYER, "", Parser.xmlParser()).firstElementChild();

		Player<HandballAttributes> player = parser.getPlayer(text);

		System.out.println(player);
	}
}
