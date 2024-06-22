package importer.football;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Parser;
import org.junit.Test;

import model.Player;
import model.football.FootballAttributes;

public class FootballTrainingParserTest
{
	private static final String PLAYER = "<tr>\r\n"
		+ "          <td class='left_align' style='width: 200px;'><a href='https://soccer.powerplaymanager.com/sv/landsprofil.html?data=swe'><img src='https://appspowerplaymanager.vshcdn.net/images/ppm/flags-new/swe.png' alt=\"Sverige\" title=\"Sverige\" height='16' style='vertical-align: middle' align='absMiddle' /></a>&nbsp;<a href='https://soccer.powerplaymanager.com/sv/spelarprofil.html?data=30340973-olle-nyman'>Olle Nyman</a></td>\r\n"
		+ "          <td></td>\r\n"
		+ "          <td>23</td>\r\n"
		+ "          <td><img src='https://www.powerplaymanager.com/soccer/_images/account/icons/scouted_yes.png' title='Scoutade' alt='Scoutade' width='16' height='14' border='0'></td>\r\n"
		+ "          <td><span title='bra'>4/6</span></td>\r\n"
		+ "     <td class='right_align' nowrap='true'><span >19</span><span class='kva'>74</span><br /><span style=\"font-size: 0px; display: none;\">a</span><div align=\"center\" style=\"font-size: 9px; color: #9AAB00;\">&nbsp;</div></td><td class='right_align' nowrap='true'><span >187</span><span class='kva'>82</span><br /><span style=\"font-size: 0px; display: none;\">a</span><div align=\"center\" style=\"font-size: 9px; color: #9AAB00;\">100</div></td><td class='right_align' nowrap='true'><span >21</span><span class='kva'>73</span><br /><span style=\"font-size: 0px; display: none;\">a</span><div align=\"center\" style=\"font-size: 9px; color: #9AAB00;\">&nbsp;</div></td><td class='right_align' nowrap='true'><span >21</span><span class='kva'>76</span><br /><span style=\"font-size: 0px; display: none;\">a</span><div align=\"center\" style=\"font-size: 9px; color: #9AAB00;\">&nbsp;</div></td><td class='right_align' nowrap='true'>47<span class='kva'>67</span><br /><span style=\"font-size: 0px; display: none;\">a</span><div align=\"center\" style=\"font-size: 9px; color: #9AAB00;\">25</div></td><td class='right_align' nowrap='true'><span >94</span><span class='kva'>78</span><br /><span style=\"font-size: 0px; display: none;\">a</span><div align=\"center\" style=\"font-size: 9px; color: #9AAB00;\">50</div></td><td class='right_align' nowrap='true'><span >94</span><span class='kva'>63</span><br /><span style=\"font-size: 0px; display: none;\">a</span><div align=\"center\" style=\"font-size: 9px; color: #9AAB00;\">50</div></td><td class='right_align' nowrap='true' style='border: 1px darkgreen solid;' title='+0.67'><span >94</span><span class='kva'>64</span><br /><span style=\"font-size: 0px; display: none;\">a</span><div align=\"center\" style=\"font-size: 9px; color: #9AAB00;\">50</div></td><td class='right_align' nowrap='true'><span >93</span><span class='kva'>69</span><br /><span style=\"font-size: 0px; display: none;\">a</span><div align=\"center\" style=\"font-size: 9px; color: #9AAB00;\">50</div></td>\r\n"
		+ "          <td>0.67</td>\r\n"
		+ "          <td>\r\n"
		+ "                <select name='30340973'>\r\n"
		+ "             <option value='0' selected>n/a</option><option value='1'>Mål</option><option value='2'>För</option><option value='3'>Mit</option><option value='4'>Off</option><option value='5'>Sko</option><option value='6'>Pas</option><option value='7'>Tek</option><option value='8'>Sna</option><option value='9'>Nic</option><option value=\"14393\" selected>CB</option><option value=\"14397\">CF</option><option value=\"14395\">CM</option><option value=\"14392\">FB</option><option value=\"14391\">G</option><option value=\"14396\">WF</option><option value=\"14394\">WM</option>\r\n"
		+ "             </select>\r\n"
		+ "          </td>\r\n"
		+ "        </tr>";

	@Test
	public void test()
	{
		FootballTrainingParser parser = new FootballTrainingParser();

		Element text = Jsoup.parse(PLAYER, "", Parser.xmlParser()).firstElementChild();

		Player<FootballAttributes> player = parser.getPlayer(text);

		System.out.println(player);
	}
}
