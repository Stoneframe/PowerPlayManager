package importer.icehockey;

import java.util.List;
import java.util.stream.Collectors;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import importer.Parser;
import model.Player;
import model.Side;
import model.icehockey.IceHockeyAttributes;
import model.icehockey.IceHockeyPlayer;

public abstract class IceHockeyParser
	implements
		Parser<IceHockeyAttributes>
{
	@Override
	public abstract String getAddress();

	@Override
	public List<Player<IceHockeyAttributes>> parse(Document document)
	{
		Element tableElement = document.getElementById("table-1");

		Elements playerElements = tableElement.child(1).children();

		return playerElements.stream()
			.map(e -> getPlayer(e))
			.collect(Collectors.toList());
	}

	private Player<IceHockeyAttributes> getPlayer(Element playerElement)
	{
		return new IceHockeyPlayer(
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

	private IceHockeyAttributes getAttributes(Element playerElement)
	{
		IceHockeyAttributes attributes = new IceHockeyAttributes();

		attributes.setGoa(getGoa(playerElement));
		attributes.setOff(getOff(playerElement));
		attributes.setDef(getDef(playerElement));
		attributes.setSho(getSho(playerElement));
		attributes.setPas(getPas(playerElement));
		attributes.setTec(getTec(playerElement));
		attributes.setAgr(getAgr(playerElement));

		attributes.setQGoa(getQGoa(playerElement));
		attributes.setQOff(getQOff(playerElement));
		attributes.setQDef(getQDef(playerElement));
		attributes.setQSho(getQSho(playerElement));
		attributes.setQPas(getQPas(playerElement));
		attributes.setQTec(getQTec(playerElement));
		attributes.setQAgr(getQAgr(playerElement));

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

	protected abstract int getOff(Element playerElement);

	protected abstract int getDef(Element playerElement);

	protected abstract int getSho(Element playerElement);

	protected abstract int getPas(Element playerElement);

	protected abstract int getTec(Element playerElement);

	protected abstract int getAgr(Element playerElement);

	protected abstract int getQGoa(Element playerElement);

	protected abstract int getQOff(Element playerElement);

	protected abstract int getQDef(Element playerElement);

	protected abstract int getQSho(Element playerElement);

	protected abstract int getQPas(Element playerElement);

	protected abstract int getQTec(Element playerElement);

	protected abstract int getQAgr(Element playerElement);
}
