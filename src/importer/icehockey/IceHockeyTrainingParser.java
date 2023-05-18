package importer.icehockey;

import org.jsoup.nodes.Element;

import importer.TrainingParser;
import model.Side;
import model.icehockey.IceHockeyAttributes;

public class IceHockeyTrainingParser
	extends IceHockeyParser
	implements
		TrainingParser<IceHockeyAttributes>
{
	@Override
	public String getAddress()
	{
		return "https://hockey.powerplaymanager.com/sv/spelar-traning.html";
	}

	@Override
	protected String getName(Element playerElement)
	{
		return playerElement.child(0).child(1).text();
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
		Element skillElement = playerElement.child(5);
		
		String text = skillElement.ownText();

		if (text.isEmpty())
		{
			text = skillElement.child(0).text();
		}

		return Integer.parseInt(text);
	}

	@Override
	protected int getDef(Element playerElement)
	{
		Element skillElement = playerElement.child(6);
		
		String text = skillElement.ownText();

		if (text.isEmpty())
		{
			text = skillElement.child(0).text();
		}

		return Integer.parseInt(text);
	}

	@Override
	protected int getOff(Element playerElement)
	{
		Element skillElement = playerElement.child(7);
		
		String text = skillElement.ownText();

		if (text.isEmpty())
		{
			text = skillElement.child(0).text();
		}

		return Integer.parseInt(text);
	}

	@Override
	protected int getSho(Element playerElement)
	{
		Element skillElement = playerElement.child(8);
		
		String text = skillElement.ownText();

		if (text.isEmpty())
		{
			text = skillElement.child(0).text();
		}

		return Integer.parseInt(text);
	}

	@Override
	protected int getPas(Element playerElement)
	{
		Element skillElement = playerElement.child(9);
		
		String text = skillElement.ownText();

		if (text.isEmpty())
		{
			text = skillElement.child(0).text();
		}

		return Integer.parseInt(text);
	}

	@Override
	protected int getTec(Element playerElement)
	{
		Element skillElement = playerElement.child(10);
		
		String text = skillElement.ownText();

		if (text.isEmpty())
		{
			text = skillElement.child(0).text();
		}

		return Integer.parseInt(text);
	}

	@Override
	protected int getAgr(Element playerElement)
	{
		Element skillElement = playerElement.child(11);
		
		String text = skillElement.ownText();

		if (text.isEmpty())
		{
			text = skillElement.child(0).text();
		}

		return Integer.parseInt(text);
	}

	@Override
	protected int getQGoa(Element playerElement)
	{
		return Integer.parseInt(playerElement.child(5).getElementsByClass("kva").text());
	}

	@Override
	protected int getQOff(Element playerElement)
	{
		return Integer.parseInt(playerElement.child(6).getElementsByClass("kva").text());
	}

	@Override
	protected int getQDef(Element playerElement)
	{
		return Integer.parseInt(playerElement.child(7).getElementsByClass("kva").text());
	}

	@Override
	protected int getQSho(Element playerElement)
	{
		return Integer.parseInt(playerElement.child(8).getElementsByClass("kva").text());
	}

	@Override
	protected int getQPas(Element playerElement)
	{
		return Integer.parseInt(playerElement.child(9).getElementsByClass("kva").text());
	}

	@Override
	protected int getQTec(Element playerElement)
	{
		return Integer.parseInt(playerElement.child(10).getElementsByClass("kva").text());
	}

	@Override
	protected int getQAgr(Element playerElement)
	{
		return Integer.parseInt(playerElement.child(11).getElementsByClass("kva").text());
	}

}
