package gui.handball;

import gui.player.AttributePanel;
import gui.player.AttributesPanel;
import model.handball.HandballAttributes;

public class HandballAttributesPanel
	extends AttributesPanel<HandballAttributes>
{
	private static final long serialVersionUID = -550607296797083079L;

	public HandballAttributesPanel()
	{
		super(
			new AttributePanel("Goa:"),
			new AttributePanel("Fip:"),
			new AttributePanel("Sho:"),
			new AttributePanel("Blk:"),
			new AttributePanel("Pas:"),
			new AttributePanel("Tec:"),
			new AttributePanel("Spe:"),
			new AttributePanel("Agr:"));
	}
}
