package gui;

import java.util.function.Supplier;

import javax.swing.JPanel;

import model.Attributes;
import util.PropertyChangedEvent;
import util.PropertyChangedListener;

public abstract class AttributesPanel<A extends Attributes> extends JPanel
		implements
		PropertyChangedListener
{
	private static final long serialVersionUID = -7993333522332535462L;

	protected static final int TEXTFIELD_COLUMNS = 4;

	protected A attributes;

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

	protected String intToString(Supplier<Integer> getValueSupplier)
	{
		return attributes != null
				? Integer.toString(getValueSupplier.get())
				: "";
	}

	protected String doubleToString(Supplier<Double> getValueSupplier)
	{
		return attributes != null
				? String.format("%.1f", getValueSupplier.get())
				: "";
	}

	protected abstract void update();
}
