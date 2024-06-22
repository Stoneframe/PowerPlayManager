package importer.handball;

import org.jsoup.nodes.Element;

import importer.TrainingParser;
import model.Side;
import model.handball.HandballAttributes;

public class HandballTrainingParser
	extends HandballParser
	implements
		TrainingParser<HandballAttributes>
{
	@Override
	public String getAddress()
	{
		return "https://handball.powerplaymanager.com/sv/spelarnas-traning.html";
	}

	@Override
	protected String getName(Element playerElement)
	{
		return playerElement.child(0).text();
	}

	@Override
	protected String getCountry(Element playerElement)
	{
		return playerElement.child(0).child(0).child(0).attr("title");
	}

	@Override
	protected int getAge(Element playerElement)
	{
		return Integer.parseInt(playerElement.child(8).text());
	}

	@Override
	protected int getCl(Element playerElement)
	{
		return Integer.parseInt(playerElement.child(10).text().split("/")[0]);
	}

	@Override
	protected Side getSide(Element playerElement)
	{
		return Side.UNKNOWN;
	}

	@Override
	protected int getExperience(Element playerElement)
	{
		return 0;
	}

	@Override
	protected int getChemistry(Element playerElement)
	{
		return 0;
	}

	@Override
	protected int getEnergy(Element playerElement)
	{
		return 100;
	}

	@Override
	protected int getGoa(Element playerElement)
	{
		return Integer.parseInt(playerElement.child(11).child(0).text());
	}

	@Override
	protected int getFip(Element playerElement)
	{
		return Integer.parseInt(playerElement.child(12).child(0).text());
	}

	@Override
	protected int getSho(Element playerElement)
	{
		return Integer.parseInt(playerElement.child(13).child(0).text());
	}

	@Override
	protected int getBlk(Element playerElement)
	{
		return Integer.parseInt(playerElement.child(14).child(0).text());
	}

	@Override
	protected int getPas(Element playerElement)
	{
		return Integer.parseInt(playerElement.child(15).child(0).text());
	}

	@Override
	protected int getTec(Element playerElement)
	{
		return Integer.parseInt(playerElement.child(16).child(0).text());
	}

	@Override
	protected int getSpe(Element playerElement)
	{
		return Integer.parseInt(playerElement.child(17).child(0).text());
	}

	@Override
	protected int getAgr(Element playerElement)
	{
		return Integer.parseInt(playerElement.child(18).child(0).text());
	}

	@Override
	protected int getQGoa(Element playerElement)
	{
		return Integer.parseInt(playerElement.child(11).child(1).text());
	}

	@Override
	protected int getQFip(Element playerElement)
	{
		return Integer.parseInt(playerElement.child(12).child(1).text());
	}

	@Override
	protected int getQSho(Element playerElement)
	{
		return Integer.parseInt(playerElement.child(13).child(1).text());
	}

	@Override
	protected int getQBlk(Element playerElement)
	{
		return Integer.parseInt(playerElement.child(14).child(1).text());
	}

	@Override
	protected int getQPas(Element playerElement)
	{
		return Integer.parseInt(playerElement.child(15).child(1).text());
	}

	@Override
	protected int getQTec(Element playerElement)
	{
		return Integer.parseInt(playerElement.child(16).child(1).text());
	}

	@Override
	protected int getQSpe(Element playerElement)
	{
		return Integer.parseInt(playerElement.child(17).child(1).text());
	}

	@Override
	protected int getQAgr(Element playerElement)
	{
		return Integer.parseInt(playerElement.child(18).child(1).text());
	}
}
