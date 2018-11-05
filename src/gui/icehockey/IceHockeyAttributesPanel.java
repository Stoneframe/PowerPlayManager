package gui.icehockey;

import gui.player.AttributePanel;
import gui.player.AttributesPanel;
import model.icehockey.IceHockeyAttributes;

public class IceHockeyAttributesPanel
	extends AttributesPanel<IceHockeyAttributes>
{
	public IceHockeyAttributesPanel()
	{
		super(
			new AttributePanel("Goa:"),
			new AttributePanel("Def:"),
			new AttributePanel("Off:"),
			new AttributePanel("Sho:"),
			new AttributePanel("Pas:"),
			new AttributePanel("Tec:"),
			new AttributePanel("Agr:"));
	}
}
