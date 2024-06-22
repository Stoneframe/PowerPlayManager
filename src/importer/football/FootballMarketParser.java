package importer.football;

import org.jsoup.nodes.Element;

import importer.MarketParser;
import model.Side;
import model.football.FootballAttributes;

public class FootballMarketParser
	extends FootballParser
	implements
		MarketParser<FootballAttributes>
{

	@Override
	public String getAddress()
	{
		return "https://soccer.powerplaymanager.com/sv/transfer-marknad-spelare.html";
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
		return Integer.parseInt(playerElement.select("[title=Ålder]").first().text());
	}

	@Override
	protected int getCl(Element playerElement)
	{
		return Integer
			.parseInt(playerElement.select("[title=Karriärlängd]").first().text().split("/")[0]);
	}

	@Override
	protected Side getSide(Element playerElement)
	{
		return Side.parse(playerElement.select("[title=Favoritsida]").first().text());
	}

	@Override
	protected int getExperience(Element playerElement)
	{
		return Integer.parseInt(playerElement.select("[title=Erfarenhet]").first().text());
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
		return getAttributeValue(playerElement, "Målvakt");
	}

	@Override
	protected int getDef(Element playerElement)
	{
		return getAttributeValue(playerElement, "Försvar");
	}

	@Override
	protected int getMid(Element playerElement)
	{
		return getAttributeValue(playerElement, "Mittfält");
	}

	@Override
	protected int getOff(Element playerElement)
	{
		return getAttributeValue(playerElement, "Offensiv");
	}

	@Override
	protected int getSho(Element playerElement)
	{
		return getAttributeValue(playerElement, "Skott");
	}

	@Override
	protected int getPas(Element playerElement)
	{
		return getAttributeValue(playerElement, "Passning");
	}

	@Override
	protected int getTec(Element playerElement)
	{
		return getAttributeValue(playerElement, "Teknik");
	}

	@Override
	protected int getSpe(Element playerElement)
	{
		return getAttributeValue(playerElement, "Snabbhet");
	}

	@Override
	protected int getHea(Element playerElement)
	{
		return getAttributeValue(playerElement, "Nick");
	}

	@Override
	protected int getQGoa(Element playerElement)
	{
		return getAttributeQualityValue(playerElement, "Målvakt");
	}

	@Override
	protected int getQDef(Element playerElement)
	{
		return getAttributeQualityValue(playerElement, "Försvar");
	}

	@Override
	protected int getQMid(Element playerElement)
	{
		return getAttributeQualityValue(playerElement, "Mittfält");
	}

	@Override
	protected int getQOff(Element playerElement)
	{
		return getAttributeQualityValue(playerElement, "Offensiv");
	}

	@Override
	protected int getQSho(Element playerElement)
	{
		return getAttributeQualityValue(playerElement, "Skott");
	}

	@Override
	protected int getQPas(Element playerElement)
	{
		return getAttributeQualityValue(playerElement, "Passning");
	}

	@Override
	protected int getQTec(Element playerElement)
	{
		return getAttributeQualityValue(playerElement, "Teknik");
	}

	@Override
	protected int getQSpe(Element playerElement)
	{
		return getAttributeQualityValue(playerElement, "Snabbhet");
	}

	@Override
	protected int getQHea(Element playerElement)
	{
		return getAttributeQualityValue(playerElement, "Nick");
	}

	private int getAttributeValue(Element playerElement, String attributeName)
	{
		String attributeValue = playerElement
			.select("[title=" + attributeName + "]")
			.first()
			.ownText();

		return Integer.parseInt(attributeValue);
	}

	private int getAttributeQualityValue(Element playerElement, String attributeName)
	{
		String attributeQualityValue = playerElement
			.select("[title=" + attributeName + "]")
			.first()
			.select("[class=kva]")
			.first()
			.ownText();

		return Integer.parseInt(attributeQualityValue);
	}
}
