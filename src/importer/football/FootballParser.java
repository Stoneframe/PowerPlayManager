package importer.football;

import java.util.List;
import java.util.stream.Collectors;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import importer.Parser;
import model.Player;
import model.Side;
import model.football.FootballAttributes;
import model.football.FootballPlayer;

public abstract class FootballParser
	implements
		Parser<FootballAttributes>
{

	@Override
	public abstract String getAddress();

	@Override
	public List<Player<FootballAttributes>> parse(Document document)
	{
		Element tableElement = document.getElementById("table-1");

		Elements playerElements = tableElement.child(1).children();

		return playerElements.stream()
			.map(e -> getPlayer(e))
			.collect(Collectors.toList());
	}
	
	public Player<FootballAttributes> getPlayer(Element playerElement)
	{
		return new FootballPlayer(
			getName(playerElement),
			getCountry(playerElement),
			getAge(playerElement),
			getCl(playerElement),
			getSide(playerElement),
			getAttributes(playerElement),
			getExperience(playerElement),
			getChemistry(playerElement),
			getEnergy(playerElement),
			0);
	}
	
	private FootballAttributes getAttributes(Element playerElement)
	{
		FootballAttributes attributes = new FootballAttributes();

		attributes.setGoa(getGoa(playerElement));
		attributes.setDef(getDef(playerElement));
		attributes.setMid(getMid(playerElement));
		attributes.setOff(getOff(playerElement));
		attributes.setSho(getSho(playerElement));
		attributes.setPas(getPas(playerElement));
		attributes.setTec(getTec(playerElement));
		attributes.setSpe(getSpe(playerElement));
		attributes.setHea(getHea(playerElement));
		
		attributes.setQGoa(getQGoa(playerElement));
		attributes.setQDef(getQDef(playerElement));
		attributes.setQMid(getQMid(playerElement));
		attributes.setQOff(getQOff(playerElement));
		attributes.setQSho(getQSho(playerElement));
		attributes.setQPas(getQPas(playerElement));
		attributes.setQTec(getQTec(playerElement));
		attributes.setQSpe(getQSpe(playerElement));
		attributes.setQHea(getQHea(playerElement));

		return attributes;
	}

	protected abstract String getName(Element playerElement);

	protected abstract String getCountry(Element playerElement);

	protected abstract int getAge(Element playerElement);

	protected abstract int getCl(Element playerElement);

	protected abstract Side getSide(Element playerElement);

	protected abstract int getExperience(Element playerElement);

	protected abstract int getChemistry(Element playerElement);

	protected abstract int getEnergy(Element playerElement);
	
	protected abstract int getGoa(Element playerElement);

	protected abstract int getDef(Element playerElement);

	protected abstract int getMid(Element playerElement);

	protected abstract int getOff(Element playerElement);

	protected abstract int getSho(Element playerElement);

	protected abstract int getPas(Element playerElement);

	protected abstract int getTec(Element playerElement);

	protected abstract int getSpe(Element playerElement);

	protected abstract int getHea(Element playerElement);

	protected abstract int getQGoa(Element playerElement);

	protected abstract int getQDef(Element playerElement);

	protected abstract int getQMid(Element playerElement);

	protected abstract int getQOff(Element playerElement);

	protected abstract int getQSho(Element playerElement);

	protected abstract int getQPas(Element playerElement);

	protected abstract int getQTec(Element playerElement);

	protected abstract int getQSpe(Element playerElement);

	protected abstract int getQHea(Element playerElement);
}
