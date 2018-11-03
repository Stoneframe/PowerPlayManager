package gui.football;

import javax.swing.JComponent;

import gui.player.AttributePanel;
import gui.player.AttributesPanelInterface;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.GridPane;
import model.football.FootballAttributes;
import util.PropertyChangedEvent;

public class FootballAttributesPanel
	extends JFXPanel
	implements
		AttributesPanelInterface<FootballAttributes>
{
	private static final long serialVersionUID = -4623262476204460582L;

	private AttributePanel goaAttributePanel;
	private AttributePanel defAttributePanel;
	private AttributePanel midAttributePanel;
	private AttributePanel offAttributePanel;
	private AttributePanel shoAttributePanel;
	private AttributePanel pasAttributePanel;
	private AttributePanel tecAttributePanel;
	private AttributePanel speAttributePanel;
	private AttributePanel heaAttributePanel;

	public FootballAttributesPanel()
	{
		goaAttributePanel = new AttributePanel("Goa:");
		defAttributePanel = new AttributePanel("Def:");
		midAttributePanel = new AttributePanel("Mid:");
		offAttributePanel = new AttributePanel("Off:");
		shoAttributePanel = new AttributePanel("Sho:");
		pasAttributePanel = new AttributePanel("Pas:");
		tecAttributePanel = new AttributePanel("Tec:");
		speAttributePanel = new AttributePanel("Spe:");
		heaAttributePanel = new AttributePanel("Hea:");

		Platform.runLater(new Runnable()
		{
			public void run()
			{
				GridPane gridPane = new GridPane();

				gridPane.setVgap(5);
				gridPane.setPadding(new Insets(10, 20, 10, 20));

				gridPane.add(goaAttributePanel, 0, 0);
				gridPane.add(defAttributePanel, 0, 1);
				gridPane.add(midAttributePanel, 0, 2);
				gridPane.add(offAttributePanel, 0, 3);
				gridPane.add(shoAttributePanel, 0, 4);
				gridPane.add(pasAttributePanel, 0, 5);
				gridPane.add(tecAttributePanel, 0, 6);
				gridPane.add(speAttributePanel, 0, 7);
				gridPane.add(heaAttributePanel, 0, 8);

				TitledPane titledPane = new TitledPane();

				titledPane.setText("Attributes");
				titledPane.setCollapsible(false);
				titledPane.setContent(gridPane);

				setScene(new Scene(titledPane));
			}
		});
	}

	protected void update()
	{

	}

	@Override
	public void bind(FootballAttributes attributes)
	{
		Platform.runLater(new Runnable()
		{
			public void run()
			{
				if (attributes != null)
				{
					goaAttributePanel.bind(attributes.getGoa());
					defAttributePanel.bind(attributes.getDef());
					midAttributePanel.bind(attributes.getMid());
					offAttributePanel.bind(attributes.getOff());
					shoAttributePanel.bind(attributes.getSho());
					pasAttributePanel.bind(attributes.getPas());
					tecAttributePanel.bind(attributes.getTec());
					speAttributePanel.bind(attributes.getSpe());
					heaAttributePanel.bind(attributes.getHea());
				}
				else
				{
					goaAttributePanel.bind(null);
					defAttributePanel.bind(null);
					midAttributePanel.bind(null);
					offAttributePanel.bind(null);
					shoAttributePanel.bind(null);
					pasAttributePanel.bind(null);
					tecAttributePanel.bind(null);
					speAttributePanel.bind(null);
					heaAttributePanel.bind(null);
				}
			}
		});
	}

	@Override
	public void propertyChanged(Object source, PropertyChangedEvent event)
	{
	}

	@Override
	public JComponent getPanel()
	{
		return this;
	}
}
