package importer.handball;

import org.jsoup.nodes.Element;

import importer.MarketParser;
import model.Side;
import model.handball.HandballAttributes;

public class HandballMarketParser
	extends HandballParser
	implements
		MarketParser<HandballAttributes>
{
	@Override
	public String getAddress()
	{
		return "https://handball.powerplaymanager.com/sv/spelar-marknad.html";
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
		return Integer.parseInt(playerElement.child(1).text());
	}

	@Override
	protected int getCl(Element playerElement)
	{
		return Integer.parseInt(playerElement.child(4).text().split("/")[0]);
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
		return Integer.parseInt(playerElement.child(5).ownText());
	}

	@Override
	protected int getFip(Element playerElement)
	{
		return Integer.parseInt(playerElement.child(6).ownText());
	}

	@Override
	protected int getSho(Element playerElement)
	{
		return Integer.parseInt(playerElement.child(7).ownText());
	}

	@Override
	protected int getBlk(Element playerElement)
	{
		return Integer.parseInt(playerElement.child(8).ownText());
	}

	@Override
	protected int getPas(Element playerElement)
	{
		return Integer.parseInt(playerElement.child(9).ownText());
	}

	@Override
	protected int getTec(Element playerElement)
	{
		return Integer.parseInt(playerElement.child(10).ownText());
	}

	@Override
	protected int getSpe(Element playerElement)
	{
		return Integer.parseInt(playerElement.child(11).ownText());
	}

	@Override
	protected int getAgr(Element playerElement)
	{
		return Integer.parseInt(playerElement.child(12).ownText());
	}

	@Override
	protected int getQGoa(Element playerElement)
	{
		return Integer.parseInt(playerElement.child(5).child(0).text());
	}

	@Override
	protected int getQFip(Element playerElement)
	{
		return Integer.parseInt(playerElement.child(6).child(0).text());
	}

	@Override
	protected int getQSho(Element playerElement)
	{
		return Integer.parseInt(playerElement.child(7).child(0).text());
	}

	@Override
	protected int getQBlk(Element playerElement)
	{
		return Integer.parseInt(playerElement.child(8).child(0).text());
	}

	@Override
	protected int getQPas(Element playerElement)
	{
		return Integer.parseInt(playerElement.child(9).child(0).text());
	}

	@Override
	protected int getQTec(Element playerElement)
	{
		return Integer.parseInt(playerElement.child(10).child(0).text());
	}

	@Override
	protected int getQSpe(Element playerElement)
	{
		return Integer.parseInt(playerElement.child(11).child(0).text());
	}

	@Override
	protected int getQAgr(Element playerElement)
	{
		return Integer.parseInt(playerElement.child(12).child(0).text());
	}

//	@Override
//	public List<Player<HandballAttributes>> parse(Document document)
//	{
//		Element tableElement = document.getElementById("table-1");
//		System.out.println(tableElement);
//
//		return new LinkedList<>();
//	}
}
