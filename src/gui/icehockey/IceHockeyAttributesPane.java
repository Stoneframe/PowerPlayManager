package gui.icehockey;

import gui.player.AttributePane;
import gui.player.AttributesPane;
import model.icehockey.IceHockeyAttributes;

public class IceHockeyAttributesPane
	extends AttributesPane<IceHockeyAttributes>
{
	public IceHockeyAttributesPane()
	{
		super(
			new AttributePane("Goa:"),
			new AttributePane("Def:"),
			new AttributePane("Off:"),
			new AttributePane("Sho:"),
			new AttributePane("Pas:"),
			new AttributePane("Tec:"),
			new AttributePane("Agr:"));
	}
}
