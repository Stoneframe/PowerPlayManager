package gui.player;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.LinkedList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import evaluators.PlayerEvaluator;
import gui.plot.PlayerPlotPanel;
import gui.plot.PlotPanel;
import gui.util.SimpleFormPanel;
import model.Attributes;
import model.Player;
import util.PropertyChangedEvent;
import util.PropertyChangedListener;
import warper.PlayerWarper;

public class PlayerPanel<A extends Attributes>
	extends JPanel
	implements
		PropertyChangedListener
{
	private static final long serialVersionUID = 8319489027333955979L;

	private final JTextField nameTextField;
	private final JTextField ageTextField;
	private final JTextField sideTextField;
	private final JTextField trainingTextField;

	private final AttributesPanel<A> attributePanel;

	private final PlayerFormPanel<A> playerFormPanel;

	private final PositionSuggestionPanel<A> positionSuggestionPanel;
	private final TrainingSuggestionPanel<A> trainingSuggestionPanel;

	private final TrainingPlannerPanel<A> trainingPanel;

	private final JButton plotButton;

	private final PlayerEvaluator<A> playerEvaluator;

	private Player<A> player;

	public PlayerPanel(
		AttributesPanel<A> attributePanel,
		PlayerEvaluator<A> playerEvaluator,
		PlayerWarper<A> playerWarper)
	{
		this.attributePanel = attributePanel;
		this.playerEvaluator = playerEvaluator;

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

		plotButton = new JButton("Plot");
		plotButton.setEnabled(false);
		plotButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				SwingUtilities.invokeLater(new Runnable()
				{
					public void run()
					{
						A nextYearAttributes = playerWarper.warp(
							player,
							playerEvaluator.getBestEvaluatorByRating(player.getAttributes()),
							1);

						Player<A> worstCase = new Player<>(
							"Pessimistic",
							player.getCountry(),
							player.getAge() + 1,
							Math.max(0, player.getCL() - 1),
							player.getSide(),
							nextYearAttributes,
							player.getExperience(),
							player.getChemistry(),
							player.getEnergy(),
							0);

						Player<A> bestCase = new Player<>(
							"Optimistic",
							player.getCountry(),
							player.getAge() + 1,
							player.getCL(),
							player.getSide(),
							nextYearAttributes,
							player.getExperience(),
							player.getChemistry(),
							player.getEnergy(),
							0);

						PlotPanel<A> plotPanel = new PlayerPlotPanel<A>(
							playerEvaluator,
							playerWarper,
							Arrays.asList(worstCase, bestCase),
							new LinkedList<>(),
							1);

						plotPanel.draw();

						JFrame plotFrame = new JFrame("Plot player");
						plotFrame.setContentPane(plotPanel);
						plotFrame.pack();
						plotFrame.setLocationRelativeTo(PlayerPanel.this.getParent());
						plotFrame.setVisible(true);
					}
				});
			}
		});

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
		panel.add(attributePanel, gbc);

		gbc.gridy = 2;
		panel.add(playerFormPanel, gbc);

		gbc.gridy = 3;
		panel.add(positionSuggestionPanel, gbc);

		gbc.gridy = 4;
		panel.add(trainingSuggestionPanel, gbc);

		gbc.gridy = 5;
		panel.add(trainingPanel, gbc);

		gbc.gridy = 6;
		gbc.weighty = 100;
		panel.add(plotButton, gbc);

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
			plotButton.setEnabled(false);
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
			plotButton.setEnabled(true);
			this.player.addPropertyChangedListener(this);
		}

		update();
	}

	private void update()
	{
		nameTextField.setText(player == null ? "" : player.getName());
		ageTextField.setText(player == null ? "" : Integer.toString(player.getAge()));
		sideTextField.setText(player == null ? "" : player.getSide().toString());
		trainingTextField.setText(player == null ? "" : Double.toString(getPlayerTraining()));
	}

	private double getPlayerTraining()
	{
		return player.getTraining() != 0
			? player.getTraining()
			: playerEvaluator.calculatePlayerTraining(player);
	}

	@Override
	public void propertyChanged(Object source, PropertyChangedEvent event)
	{
		update();
	}
}
