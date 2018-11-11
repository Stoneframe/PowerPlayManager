package gui.player;

import javax.swing.BorderFactory;
import javax.swing.border.CompoundBorder;

import gui.util.SimpleFormPanel;
import model.Attributes;
import util.PropertyChangedEvent;
import util.PropertyChangedListener;

public abstract class AttributesPanel<A extends Attributes>
	extends SimpleFormPanel
	implements
		PropertyChangedListener
{
	private static final long serialVersionUID = -7993333522332535462L;

	protected static final int TEXTFIELD_COLUMNS = 6;

	protected A attributes;

	protected AttributesPanel()
	{
		setBorder(
			new CompoundBorder(
					BorderFactory.createTitledBorder("Attributes"),
					BorderFactory.createEmptyBorder(5, 5, 5, 5)));
	}

	public void bind(A attributes)
	{
		if (this.attributes != null)
		{
			this.attributes.removePropertyChangedListener(this);
		}

		this.attributes = attributes;

		if (this.attributes != null)
		{
			this.attributes.addPropertyChangedListener(this);
		}

		update();
	}

	@Override
	public void propertyChanged(Object source, PropertyChangedEvent event)
	{
		update();
	}

	protected String toString(int value)
	{
		return Integer.toString(value);
	}

	protected String toString(double value)
	{
		return String.format("%.1f", value);
	}

	protected abstract void update();
}
