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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected String getCountry(Element playerElement)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected int getAge(Element playerElement)
	{
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	protected int getCl(Element playerElement)
	{
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	protected Side getSide(Element playerElement)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected int getExperience(Element playerElement)
	{
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	protected int getChemistry(Element playerElement)
	{
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	protected int getEnergy(Element playerElement)
	{
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	protected int getGoa(Element playerElement)
	{
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	protected int getOff(Element playerElement)
	{
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	protected int getDef(Element playerElement)
	{
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	protected int getSho(Element playerElement)
	{
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	protected int getPas(Element playerElement)
	{
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	protected int getTec(Element playerElement)
	{
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	protected int getAgr(Element playerElement)
	{
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	protected int getQGoa(Element playerElement)
	{
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	protected int getQOff(Element playerElement)
	{
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	protected int getQDef(Element playerElement)
	{
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	protected int getQSho(Element playerElement)
	{
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	protected int getQPas(Element playerElement)
	{
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	protected int getQTec(Element playerElement)
	{
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	protected int getQAgr(Element playerElement)
	{
		// TODO Auto-generated method stub
		return 0;
	}
}
