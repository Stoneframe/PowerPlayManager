package gui.football;

import gui.player.AttributePanel;
import gui.player.AttributesPanel;
import model.football.FootballAttributes;

public class FootballAttributesPanel
	extends AttributesPanel<FootballAttributes>
{
	public FootballAttributesPanel()
	{
		super(
			new AttributePanel("Goa:"),
			new AttributePanel("Def:"),
			new AttributePanel("Mid:"),
			new AttributePanel("Off:"),
			new AttributePanel("Sho:"),
			new AttributePanel("Pas:"),
			new AttributePanel("Tec:"),
			new AttributePanel("Spe:"),
			new AttributePanel("Hea:"));
	}
}
