package gui.player;

import evaluators.PlayerEvaluator;
import gui.util.SimpleFormPane;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.GridPane;
import model.Attributes;
import model.Player;
import util.PropertyChangedEvent;
import util.PropertyChangedListener;

public class PlayerPanel<A extends Attributes>
	extends JFXPanel
	implements
		PropertyChangedListener
{
	private static final long serialVersionUID = 8319489027333955979L;

	private Player<A> player;

	private TextField nameTextField;
	private TextField ageTextField;
	private TextField sideTextField;
	private TextField trainingTextField;

	private AttributesPanel<A> attributePanel;

	private PlayerFormPanel<A> formPanel;

	// private PositionSuggestionPanel<A> positionSuggestionPanel;
	// private TrainingSuggestionPanel<A> trainingSuggestionPanel;

	// private TrainingPlannerPanel<A> trainingPanel;

	public PlayerPanel(
			AttributesPanel<A> attributePane,
			PlayerEvaluator<A> playerEvaluator)
	{
		this.attributePanel = attributePane;

		// this.trainingPanel = new TrainingPlannerPanel<>(playerEvaluator);

		nameTextField = new TextField();
		nameTextField.setEditable(false);

		ageTextField = new TextField();
		ageTextField.setEditable(false);

		sideTextField = new TextField();
		sideTextField.setEditable(false);

		trainingTextField = new TextField();
		// trainingTextField.addActionListener(new ActionListener()
		// {
		// public void actionPerformed(ActionEvent e)
		// {
		// try
		// {
		// double training = Double.parseDouble(trainingTextField.getText());
		//
		// player.setTraining(training);
		// }
		// catch (NumberFormatException ex)
		// {
		// }
		// }
		// });

		Platform.runLater(() ->
			{
				SimpleFormPane informationPane = new SimpleFormPane();

				informationPane.addRow("Name: ", nameTextField);
				informationPane.addRow("Age:", ageTextField);
				informationPane.addRow("Side:", sideTextField);
				informationPane.addRow("Training:", trainingTextField);

				formPanel = new PlayerFormPanel<>();

				GridPane gridPane = new GridPane();

				gridPane.setVgap(10);
				gridPane.setPadding(new javafx.geometry.Insets(10, 10, 10, 10));

				gridPane.addRow(0, informationPane);
				gridPane.addRow(1, attributePane);
				gridPane.addRow(2, formPanel);

				TitledPane titledPane = new TitledPane();

				titledPane.setText("Player");
				titledPane.setCollapsible(false);
				titledPane.setContent(gridPane);

				ScrollPane scrollPane = new ScrollPane();

				scrollPane.setFitToWidth(true);
				scrollPane.setContent(titledPane);

				setScene(new Scene(scrollPane));
			});
	}

	public void bind(Player<A> player)
	{
		if (this.player != null)
		{
			attributePanel.bind(null);
			formPanel.bind(null);
			// positionSuggestionPanel.bind(null);
			// trainingSuggestionPanel.bind(null);
			// trainingPanel.bind(null);
			this.player.removePropertyChangedListener(this);
		}

		this.player = player;

		if (player != null)
		{
			attributePanel.bind(player.getAttributes());
			formPanel.bind(player);
			// positionSuggestionPanel.bind(player.getAttributes());
			// trainingSuggestionPanel.bind(player.getAttributes());
			// trainingPanel.bind(player.getAttributes());
			this.player.addPropertyChangedListener(this);
		}

		update();
	}

	private void update()
	{
		nameTextField.setText(player == null ? "" : player.getName());
		ageTextField.setText(player == null ? "" : Integer.toString(player.getAge()));
		sideTextField.setText(player == null ? "" : player.getSide().toString());
		trainingTextField.setText(player == null ? "" : Double.toString(player.getTraining()));
	}

	@Override
	public void propertyChanged(Object source, PropertyChangedEvent event)
	{
		update();
	}
}
