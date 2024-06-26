package importer.football;

import org.jsoup.nodes.Element;

import importer.LineUpParser;
import model.Side;
import model.football.FootballAttributes;

public class FootballLineUpParser
	extends FootballParser
	implements
		LineUpParser<FootballAttributes>
{

	@Override
	public String getAddress()
	{
		return "https://soccer.powerplaymanager.com/sv/laguppstallning.html";
	}

	@Override
	protected String getName(Element playerElement)
	{
		return playerElement.child(1).text();
	}

	@Override
	protected String getCountry(Element playerElement)
	{
		return playerElement.child(1).child(0).child(0).attributes().get("title");
	}

	@Override
	protected int getAge(Element playerElement)
	{
		return Integer.parseInt(playerElement.child(3).text());
	}

	@Override
	protected int getCl(Element playerElement)
	{
		return -1;
	}

	@Override
	protected Side getSide(Element playerElement)
	{
		return Side.parse(playerElement.child(15).text());
	}

	@Override
	protected int getExperience(Element playerElement)
	{
		return Integer.parseInt(playerElement.child(13).text());
	}

	@Override
	protected int getChemistry(Element playerElement)
	{
		return Integer.parseInt(playerElement.child(16).text());
	}

	@Override
	protected int getEnergy(Element playerElement)
	{
		return Integer.parseInt(playerElement.child(17).text());
	}

	@Override
	protected int getGoa(Element playerElement)
	{
		return Integer.parseInt(playerElement.child(4).text());
	}

	@Override
	protected int getDef(Element playerElement)
	{
		return Integer.parseInt(playerElement.child(5).text());
	}

	@Override
	protected int getMid(Element playerElement)
	{
		return Integer.parseInt(playerElement.child(6).text());
	}

	@Override
	protected int getOff(Element playerElement)
	{
		return Integer.parseInt(playerElement.child(7).text());
	}

	@Override
	protected int getSho(Element playerElement)
	{
		return Integer.parseInt(playerElement.child(8).text());
	}

	@Override
	protected int getPas(Element playerElement)
	{
		return Integer.parseInt(playerElement.child(9).text());
	}

	@Override
	protected int getTec(Element playerElement)
	{
		return Integer.parseInt(playerElement.child(10).text());
	}

	@Override
	protected int getSpe(Element playerElement)
	{
		return Integer.parseInt(playerElement.child(11).text());
	}

	@Override
	protected int getHea(Element playerElement)
	{
		return Integer.parseInt(playerElement.child(12).text());
	}

	@Override
	protected int getQGoa(Element playerElement)
	{
		return 0;
	}

	@Override
	protected int getQDef(Element playerElement)
	{
		return 0;
	}

	@Override
	protected int getQMid(Element playerElement)
	{
		return 0;
	}

	@Override
	protected int getQOff(Element playerElement)
	{
		return 0;
	}

	@Override
	protected int getQSho(Element playerElement)
	{
		return 0;
	}

	@Override
	protected int getQPas(Element playerElement)
	{
		return 0;
	}

	@Override
	protected int getQTec(Element playerElement)
	{
		return 0;
	}

	@Override
	protected int getQSpe(Element playerElement)
	{
		return 0;
	}

	@Override
	protected int getQHea(Element playerElement)
	{
		return 0;
	}
}
