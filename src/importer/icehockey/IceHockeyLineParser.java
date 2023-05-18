package importer.icehockey;

import org.jsoup.nodes.Element;

import importer.LineUpParser;
import model.Side;
import model.icehockey.IceHockeyAttributes;

public class IceHockeyLineParser
	extends IceHockeyParser
	implements
		LineUpParser<IceHockeyAttributes>
{
	@Override
	public String getAddress()
	{
		return "https://hockey.powerplaymanager.com/sv/uppstallning.html?data=5";
	}

	@Override
	protected String getName(Element playerElement)
	{
		return playerElement.child(1).text();
	}

	@Override
	protected String getCountry(Element playerElement)
	{
		return playerElement.child(1).child(0).child(0).attr("title");
	}

	@Override
	protected int getAge(Element playerElement)
	{
		return Integer.parseInt(playerElement.child(2).text());
	}

	@Override
	protected int getCl(Element playerElement)
	{
		return -1;
	}

	@Override
	protected Side getSide(Element playerElement)
	{
		return Side.parse(playerElement.child(3).text());
	}

	@Override
	protected int getExperience(Element playerElement)
	{
		return Integer.parseInt(playerElement.child(11).text());
	}

	@Override
	protected int getChemistry(Element playerElement)
	{
		return Integer.parseInt(playerElement.child(13).text());
	}

	@Override
	protected int getEnergy(Element playerElement)
	{
		return Integer.parseInt(playerElement.child(12).text().split("/")[0]);
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
	protected int getOff(Element playerElement)
	{
		return Integer.parseInt(playerElement.child(6).text());
	}

	@Override
	protected int getSho(Element playerElement)
	{
		return Integer.parseInt(playerElement.child(7).text());
	}

	@Override
	protected int getPas(Element playerElement)
	{
		return Integer.parseInt(playerElement.child(8).text());
	}

	@Override
	protected int getTec(Element playerElement)
	{
		return Integer.parseInt(playerElement.child(9).text());
	}

	@Override
	protected int getAgr(Element playerElement)
	{
		return Integer.parseInt(playerElement.child(10).text());
	}

	@Override
	protected int getQGoa(Element playerElement)
	{
		return 0;
	}

	@Override
	protected int getQOff(Element playerElement)
	{
		return 0;
	}

	@Override
	protected int getQDef(Element playerElement)
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
	protected int getQAgr(Element playerElement)
	{
		return 0;
	}
}
