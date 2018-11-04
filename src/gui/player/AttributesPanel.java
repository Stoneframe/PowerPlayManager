package gui.player;

import java.util.Arrays;

import javafx.geometry.Insets;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.GridPane;
import model.Attribute;
import model.Attributes;

public abstract class AttributesPanel<A extends Attributes>
	extends TitledPane
{
	protected AttributePanel[] attributePanels;

	protected AttributesPanel(AttributePanel... attributePanels)
	{
		this.attributePanels = attributePanels;

		GridPane gridPane = new GridPane();

		gridPane.setVgap(5);
		gridPane.setPadding(new Insets(10, 20, 10, 20));

		for (int i = 0; i < attributePanels.length; i++)
		{
			gridPane.add(attributePanels[i], 0, i);
		}

		setText("Attributes");
		setCollapsible(false);
		setContent(gridPane);
	}

	public void bind(A attributes)
	{
		Arrays.stream(attributePanels).forEach(ap -> ap.bind(null));

		if (attributes == null)
		{
			return;
		}

		int i = 0;
		for (Attribute attribute : attributes)
		{
			attributePanels[i++].bind(attribute);
		}
	}
}
