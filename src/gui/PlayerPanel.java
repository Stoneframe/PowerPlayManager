package gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import evaluators.PlayerEvaluator;
import model.Attributes;
import model.Player;
import util.PropertyChangedEvent;
import util.PropertyChangedListener;

public class PlayerPanel<A extends Attributes> extends JPanel
		implements PropertyChangedListener
{
	private static final long serialVersionUID = 8319489027333955979L;

	private Player<A> player;

	private JLabel nameLabel;
	private JLabel sideLabel;

	private JTextField nameTextField;
	private JTextField sideTextField;

	private AttributesPanel<A> attributePanel;

	private PositionSuggestionPanel<A> positionSuggestionPanel;
	private TrainingSuggestionPanel<A> trainingSuggestionPanel;

	public PlayerPanel(AttributesPanel<A> attributePanel)
	{
		this.attributePanel = attributePanel;

		nameLabel = new JLabel("Name:");
		nameTextField = new JTextField(15);
		nameTextField.setEditable(false);

		sideLabel = new JLabel("Side:");
		sideTextField = new JTextField(15);
		sideTextField.setEditable(false);

		positionSuggestionPanel = new PositionSuggestionPanel<A>();
		trainingSuggestionPanel = new TrainingSuggestionPanel<A>();

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

	public void setPlayerEvaluators(List<PlayerEvaluator<A>> evaluators)
	{
		positionSuggestionPanel.setPlayerEvaluators(evaluators);
		trainingSuggestionPanel.setPlayerEvaluators(evaluators);
	}

	private void update()
	{
		nameTextField.setText(
			player == null
					? ""
					: player.getName());
		sideTextField.setText(
			player == null
					? ""
					: player.getSide().toString());
	}

	@Override
	public void propertyChanged(Object source, PropertyChangedEvent event)
	{
		update();
	}
}
