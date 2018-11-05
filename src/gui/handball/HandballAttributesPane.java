package gui.handball;

import gui.player.AttributePane;
import gui.player.AttributesPane;
import model.handball.HandballAttributes;

public class HandballAttributesPane
	extends AttributesPane<HandballAttributes>
{
	public HandballAttributesPane()
	{
		super(
			new AttributePane("Goa:"),
			new AttributePane("Fip:"),
			new AttributePane("Sho:"),
			new AttributePane("Blk:"),
			new AttributePane("Pas:"),
			new AttributePane("Tec:"),
			new AttributePane("Spe:"),
			new AttributePane("Agr:"));
	}
}
