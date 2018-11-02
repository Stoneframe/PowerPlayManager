package gui.player;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import evaluators.PlayerEvaluator;
import gui.util.SimpleFormPanel;
import model.Attributes;
import model.Player;
import util.PropertyChangedEvent;
import util.PropertyChangedListener;

public class PlayerPanel<A extends Attributes>
	extends JPanel
	implements
		PropertyChangedListener
{
	private static final long serialVersionUID = 8319489027333955979L;

	private Player<A> player;

	private JTextField nameTextField;
	private JTextField ageTextField;
	private JTextField sideTextField;
	private JTextField trainingTextField;

	private AttributesPanelInterface<A> attributePanel;

	private PlayerFormPanel<A> playerFormPanel;

	private PositionSuggestionPanel<A> positionSuggestionPanel;
	private TrainingSuggestionPanel<A> trainingSuggestionPanel;

	private TrainingPlannerPanel<A> trainingPanel;

	public PlayerPanel(
			AttributesPanelInterface<A> attributePanel,
			PlayerEvaluator<A> playerEvaluator)
	{
		this.attributePanel = attributePanel;

		this.trainingPanel = new TrainingPlannerPanel<>(playerEvaluator);

		nameTextField = new JTextField(15);
		nameTextField.setEditable(false);

		ageTextField = new JTextField(15);
		ageTextField.setEditable(false);

		sideTextField = new JTextField(15);
		sideTextField.setEditable(false);

		trainingTextField = new JTextField(15);
		trainingTextField.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				try
				{
					double training = Double.parseDouble(trainingTextField.getText());

					player.setTraining(training);
				}
				catch (NumberFormatException ex)
				{
				}
			}
		});

		SimpleFormPanel playerInformationPanel = new SimpleFormPanel();

		playerInformationPanel.addRow("Name: ", nameTextField);
		playerInformationPanel.addRow("Age:", ageTextField);
		playerInformationPanel.addRow("Side:", sideTextField);
		playerInformationPanel.addRow("Training:", trainingTextField);

		playerFormPanel = new PlayerFormPanel<>();

		positionSuggestionPanel = new PositionSuggestionPanel<>(playerEvaluator);
		trainingSuggestionPanel = new TrainingSuggestionPanel<>(playerEvaluator);

		setBorder(BorderFactory.createTitledBorder("Player"));

		GridBagConstraints gbc = new GridBagConstraints();

		gbc.anchor = GridBagConstraints.NORTHWEST;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.insets = new Insets(5, 5, 5, 5);
		gbc.weighty = 1;
		gbc.gridx = 0;

		JPanel panel = new JPanel(new GridBagLayout());

		gbc.gridy = 0;
		panel.add(playerInformationPanel, gbc);

		gbc.gridy = 1;
		panel.add(attributePanel.getPanel(), gbc);

		gbc.gridy = 2;
		panel.add(playerFormPanel, gbc);

		gbc.gridy = 3;
		panel.add(positionSuggestionPanel, gbc);

		gbc.gridy = 4;
		panel.add(trainingSuggestionPanel, gbc);

		gbc.gridy = 5;
		gbc.weighty = 100;
		panel.add(trainingPanel, gbc);

		JScrollPane scrollPane = new JScrollPane(panel);

		scrollPane.setBorder(BorderFactory.createEmptyBorder());

		setLayout(new BorderLayout());

		add(scrollPane, BorderLayout.CENTER);

		setPreferredSize(new Dimension(275, 0));
	}

	public void bind(Player<A> player)
	{
		if (this.player != null)
		{
			attributePanel.bind(null);
			playerFormPanel.bind(null);
			positionSuggestionPanel.bind(null);
			trainingSuggestionPanel.bind(null);
			trainingPanel.bind(null);
			this.player.removePropertyChangedListener(this);
		}

		this.player = player;

		if (player != null)
		{
			attributePanel.bind(player.getAttributes());
			playerFormPanel.bind(player);
			positionSuggestionPanel.bind(player.getAttributes());
			trainingSuggestionPanel.bind(player.getAttributes());
			trainingPanel.bind(player.getAttributes());
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
