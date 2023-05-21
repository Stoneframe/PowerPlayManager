package importer.handball;

import java.util.List;
import java.util.stream.Collectors;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import importer.Parser;
import model.Player;
import model.Side;
import model.handball.HandballAttributes;
import model.handball.HandballPlayer;

public abstract class HandballParser
	implements
		Parser<HandballAttributes>
{
	@Override
	public abstract String getAddress();

	@Override
	public List<Player<HandballAttributes>> parse(Document document)
	{
		Element tableElement = document.getElementById("table-1");

		Elements playerElements = tableElement.child(1).children();

		return playerElements.stream()
			.map(e -> getPlayer(e))
			.collect(Collectors.toList());
	}

	public Player<HandballAttributes> getPlayer(Element playerElement)
	{
		return new HandballPlayer(
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

	private HandballAttributes getAttributes(Element playerElement)
	{
		HandballAttributes attributes = new HandballAttributes();

		attributes.setGoa(getGoa(playerElement));
		attributes.setFip(getFip(playerElement));
		attributes.setSho(getSho(playerElement));
		attributes.setBlk(getBlk(playerElement));
		attributes.setPas(getPas(playerElement));
		attributes.setTec(getTec(playerElement));
		attributes.setSpe(getSpe(playerElement));
		attributes.setAgr(getAgr(playerElement));
		
		attributes.setQGoa(getQGoa(playerElement));
		attributes.setQFip(getQFip(playerElement));
		attributes.setQSho(getQSho(playerElement));
		attributes.setQBlk(getQBlk(playerElement));
		attributes.setQPas(getQPas(playerElement));
		attributes.setQTec(getQTec(playerElement));
		attributes.setQSpe(getQSpe(playerElement));
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

	protected abstract int getFip(Element playerElement);

	protected abstract int getSho(Element playerElement);

	protected abstract int getBlk(Element playerElement);

	protected abstract int getPas(Element playerElement);

	protected abstract int getTec(Element playerElement);

	protected abstract int getSpe(Element playerElement);

	protected abstract int getAgr(Element playerElement);

	protected abstract int getQGoa(Element playerElement);

	protected abstract int getQFip(Element playerElement);

	protected abstract int getQSho(Element playerElement);

	protected abstract int getQBlk(Element playerElement);

	protected abstract int getQPas(Element playerElement);

	protected abstract int getQTec(Element playerElement);

	protected abstract int getQSpe(Element playerElement);

	protected abstract int getQAgr(Element playerElement);
}
