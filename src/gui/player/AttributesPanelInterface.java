package gui.player;

import javax.swing.JComponent;

import model.Attributes;
import util.PropertyChangedEvent;

public interface AttributesPanelInterface<A extends Attributes>
{
	void bind(A attributes);

	void propertyChanged(Object source, PropertyChangedEvent event);

	JComponent getPanel();
}