package gui.player;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import evaluators.AttributeEvaluator;
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

	private AttributesPanel<A> attributePanel;

	private PositionSuggestionPanel<A> positionSuggestionPanel;
	private TrainingSuggestionPanel<A> trainingSuggestionPanel;

	public PlayerPanel(AttributesPanel<A> attributePanel)
	{
		this.attributePanel = attributePanel;

		setPreferredSize(new Dimension(275, 0));

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

		SimpleFormPanel playerFormPanel = new SimpleFormPanel();

		playerFormPanel.addRow("Name: ", nameTextField);
		playerFormPanel.addRow("Age:", ageTextField);
		playerFormPanel.addRow("Side:", sideTextField);
		playerFormPanel.addRow("Training:", trainingTextField);

		positionSuggestionPanel = new PositionSuggestionPanel<A>();
		trainingSuggestionPanel = new TrainingSuggestionPanel<A>();

		setBorder(BorderFactory.createTitledBorder("Player"));

		GridBagConstraints c = new GridBagConstraints();

		c.anchor = GridBagConstraints.NORTHWEST;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(5, 5, 5, 5);
		c.weighty = 1;
		c.gridx = 0;

		JPanel panel = new JPanel(new GridBagLayout());

		c.gridy = 0;
		panel.add(playerFormPanel, c);

		c.gridy = 1;
		panel.add(attributePanel, c);

		c.gridy = 2;
		panel.add(positionSuggestionPanel, c);

		c.gridy = 3;
		c.weighty = 100;
		panel.add(trainingSuggestionPanel, c);

		JScrollPane scrollPane = new JScrollPane(panel);

		scrollPane.setBorder(BorderFactory.createEmptyBorder());

		setLayout(new BorderLayout());

		add(scrollPane, BorderLayout.CENTER);
	}

	public void bind(Player<A> player)
	{
		if (this.player != null)
		{
			attributePanel.bind(null);
			positionSuggestionPanel.bind(null);
			trainingSuggestionPanel.bind(null);
			this.player.removePropertyChangedListener(this);
		}

		this.player = player;

		if (player != null)
		{
			attributePanel.bind(player.getAttributes());
			positionSuggestionPanel.bind(player.getAttributes());
			trainingSuggestionPanel.bind(player.getAttributes());
			this.player.addPropertyChangedListener(this);
		}

		update();
	}

	public void setAttributeEvaluators(List<AttributeEvaluator<A>> evaluators)
	{
		positionSuggestionPanel.setAttributeEvaluators(evaluators);
		trainingSuggestionPanel.setAttributeEvaluators(evaluators);
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
