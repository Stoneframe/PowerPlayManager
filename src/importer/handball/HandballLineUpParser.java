package importer.handball;

import org.jsoup.nodes.Element;

import importer.LineUpParser;
import model.Side;
import model.handball.HandballAttributes;

public class HandballLineUpParser
	extends HandballParser
	implements
		LineUpParser<HandballAttributes>
{
	@Override
	public String getAddress()
	{
		return "https://handball.powerplaymanager.com/sv/lag-upp-stall-ning.html";
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
		return Side.parse(playerElement.child(14).text());
	}

	@Override
	protected int getExperience(Element playerElement)
	{
		return Integer.parseInt(playerElement.child(12).text());
	}

	@Override
	protected int getChemistry(Element playerElement)
	{
		return Integer.parseInt(playerElement.child(15).text());
	}

	@Override
	protected int getEnergy(Element playerElement)
	{
		return Integer.parseInt(playerElement.child(16).text());
	}

	@Override
	protected int getGoa(Element playerElement)
	{
		return Integer.parseInt(playerElement.child(4).text());
	}

	@Override
	protected int getFip(Element playerElement)
	{
		return Integer.parseInt(playerElement.child(5).text());
	}

	@Override
	protected int getSho(Element playerElement)
	{
		return Integer.parseInt(playerElement.child(6).text());
	}

	@Override
	protected int getBlk(Element playerElement)
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
	protected int getSpe(Element playerElement)
	{
		return Integer.parseInt(playerElement.child(10).text());
	}

	@Override
	protected int getAgr(Element playerElement)
	{
		return Integer.parseInt(playerElement.child(11).text());
	}

	@Override
	protected int getQGoa(Element playerElement)
	{
		return 0;
	}

	@Override
	protected int getQFip(Element playerElement)
	{
		return 0;
	}

	@Override
	protected int getQSho(Element playerElement)
	{
		return 0;
	}

	@Override
	protected int getQBlk(Element playerElement)
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
	protected int getQAgr(Element playerElement)
	{
		return 0;
	}
}
