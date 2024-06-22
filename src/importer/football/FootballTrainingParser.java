package importer.football;

import org.jsoup.nodes.Element;

import importer.TrainingParser;
import model.Side;
import model.football.FootballAttributes;

public class FootballTrainingParser
	extends FootballParser
	implements
		TrainingParser<FootballAttributes>
{

	@Override
	public String getAddress()
	{
		return "https://soccer.powerplaymanager.com/sv/spelare-traning.html";
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
		return Integer.parseInt(playerElement.child(2).text());
	}

	@Override
	protected int getCl(Element playerElement)
	{
		return Integer.parseInt(playerElement.child(4).text().split("/")[0]);
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
		return Integer.parseInt(playerElement.child(5).child(0).text());
	}

	@Override
	protected int getDef(Element playerElement)
	{
		return Integer.parseInt(playerElement.child(6).child(0).text());
	}

	@Override
	protected int getMid(Element playerElement)
	{
		return Integer.parseInt(playerElement.child(7).child(0).text());
	}

	@Override
	protected int getOff(Element playerElement)
	{
		return Integer.parseInt(playerElement.child(8).child(0).text());
	}

	@Override
	protected int getSho(Element playerElement)
	{
		return Integer.parseInt(playerElement.child(9).textNodes().get(0).text().trim());
	}

	@Override
	protected int getPas(Element playerElement)
	{
		return Integer.parseInt(playerElement.child(10).child(0).text());
	}

	@Override
	protected int getTec(Element playerElement)
	{
		return Integer.parseInt(playerElement.child(11).child(0).text());
	}

	@Override
	protected int getSpe(Element playerElement)
	{
		return Integer.parseInt(playerElement.child(12).child(0).text());
	}

	@Override
	protected int getHea(Element playerElement)
	{
		return Integer.parseInt(playerElement.child(13).child(0).text());
	}

	@Override
	protected int getQGoa(Element playerElement)
	{
		return Integer.parseInt(playerElement.child(5).child(1).text());
	}

	@Override
	protected int getQDef(Element playerElement)
	{
		return Integer.parseInt(playerElement.child(6).child(1).text());
	}

	@Override
	protected int getQMid(Element playerElement)
	{
		return Integer.parseInt(playerElement.child(7).child(1).text());
	}

	@Override
	protected int getQOff(Element playerElement)
	{
		return Integer.parseInt(playerElement.child(8).child(1).text());
	}

	@Override
	protected int getQSho(Element playerElement)
	{
		return Integer.parseInt(playerElement.child(9).child(0).text());
	}

	@Override
	protected int getQPas(Element playerElement)
	{
		return Integer.parseInt(playerElement.child(10).child(1).text());
	}

	@Override
	protected int getQTec(Element playerElement)
	{
		return Integer.parseInt(playerElement.child(11).child(1).text());
	}

	@Override
	protected int getQSpe(Element playerElement)
	{
		return Integer.parseInt(playerElement.child(12).child(1).text());
	}

	@Override
	protected int getQHea(Element playerElement)
	{
		return Integer.parseInt(playerElement.child(13).child(1).text());
	}
}
