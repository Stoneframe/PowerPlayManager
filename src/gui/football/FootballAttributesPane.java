package gui.football;

import gui.player.AttributePane;
import gui.player.AttributesPane;
import model.football.FootballAttributes;

public class FootballAttributesPane
	extends AttributesPane<FootballAttributes>
{
	public FootballAttributesPane()
	{
		super(
			new AttributePane("Goa:"),
			new AttributePane("Def:"),
			new AttributePane("Mid:"),
			new AttributePane("Off:"),
			new AttributePane("Sho:"),
			new AttributePane("Pas:"),
			new AttributePane("Tec:"),
			new AttributePane("Spe:"),
			new AttributePane("Hea:"));
	}
}
