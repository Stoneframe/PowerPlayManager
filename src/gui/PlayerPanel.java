package gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import model.Player;
import model.PlayerEvaluator;

public class PlayerPanel extends JPanel
{
	private static final long serialVersionUID = 8319489027333955979L;

	private JLabel nameLabel;
	private JLabel sideLabel;

	private JTextField nameTextField;
	private JTextField sideTextField;

	private AttributePanel attributePanel;

	private PositionSuggestionPanel positionSuggestionPanel;
	private TrainingSuggestionPanel trainingSuggestionPanel;

	public PlayerPanel()
	{
		nameLabel = new JLabel("Name:");
		nameTextField = new JTextField(15);
		nameTextField.setEditable(false);

		sideLabel = new JLabel("Side:");
		sideTextField = new JTextField(15);
		sideTextField.setEditable(false);

		attributePanel = new AttributePanel();

		positionSuggestionPanel = new PositionSuggestionPanel();

		trainingSuggestionPanel = new TrainingSuggestionPanel();

		setBorder(BorderFactory.createTitledBorder("Player"));

		setLayout(new GridBagLayout());

		GridBagConstraints c = new GridBagConstraints();

		c.anchor = GridBagConstraints.NORTHWEST;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(5, 5, 5, 5);
		c.weighty = 1;

		c.gridx = 0;
		c.gridy = 0;

		add(nameLabel, c);

		c.gridx = 1;

		add(nameTextField, c);

		c.gridx = 0;
		c.gridy = 1;

		add(sideLabel, c);

		c.gridx = 1;

		add(sideTextField, c);

		c.gridx = 0;
		c.gridy = 2;
		c.gridwidth = 2;

		add(attributePanel, c);

		c.gridx = 0;
		c.gridy = 3;
		c.gridwidth = 2;

		add(positionSuggestionPanel, c);

		c.gridx = 0;
		c.gridy = 4;
		c.gridwidth = 2;
		c.weighty = 100;

		add(trainingSuggestionPanel, c);
	}

	public void showPlayer(Player player, List<PlayerEvaluator> evaluators)
	{
		if (player != null)
		{
			nameTextField.setText(player.getName());
			sideTextField.setText(player.getSide().toString());
			attributePanel.showAttributes(player.getAttributes());
			positionSuggestionPanel.showSuggestions(player, evaluators);
			trainingSuggestionPanel.showSuggestions(player, evaluators);
		}
		else
		{
			nameTextField.setText("");
			sideTextField.setText("");
			attributePanel.clear();
			positionSuggestionPanel.clear();
			trainingSuggestionPanel.clear();
		}
	}
}
