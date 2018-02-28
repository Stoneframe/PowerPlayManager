package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import evaluators.PlayerEvaluator;
import formation.FormationBuilder;
import gui.formation.FormationBuilderPanel;
import gui.formation.FormationTemplatePanelFactory;
import gui.player.AttributesPanel;
import gui.player.PlayerPanel;
import gui.player.PlayerSelectedEvent;
import gui.player.PlayerSelectedListener;
import gui.player.PlayersParsedEvent;
import gui.player.PlayersParsedListener;
import gui.player.TrainingPlannerPanel;
import gui.plot.PlotFrame;
import model.Attributes;
import model.Player;
import model.Roster;
import parsers.players.PlayersParser;

public class MainPanel<A extends Attributes>
	extends JPanel
{
	private static final long serialVersionUID = -8438576029794021570L;

	private ParsePanel<A> parsePanel;
	private RosterPanel<A> rosterPanel;
	private PlayerPanel<A> playerPanel;

	private JPanel buttonPanel;
	private JButton addPlayersButton;
	private JButton removePlayersButton;
	private JButton createFormationsButton;
	private JButton plotButton;
	private JButton clearRosterButton;

	private Roster<A> roster = new Roster<A>();

	public MainPanel(
			AttributesPanel<A> attributesPanel,
			TrainingPlannerPanel<A> trainingPanel,
			FormationTemplatePanelFactory<A> formationTemplatePanelFactory,
			FormationBuilder<A> formationBuilder,
			List<PlayersParser<A>> parsers,
			PlayerEvaluator<A> playerEvaluator)
	{
		parsePanel = new ParsePanel<A>(parsers);
		parsePanel.setPlayersParseListener(new PlayersParsedListener<A>()
		{
			public void playersParsed(Object source, PlayersParsedEvent<A> event)
			{
				roster.addAll(event.getPlayers());
			}
		});

		rosterPanel = new RosterPanel<A>(playerEvaluator);
		rosterPanel.bind(roster);
		rosterPanel.setPlayerSelectedListener(new PlayerSelectedListener<A>()
		{
			public void playerSelected(Object source, PlayerSelectedEvent<A> event)
			{
				playerPanel.bind(event.getPlayer());
			}
		});

		playerPanel = new PlayerPanel<A>(attributesPanel, trainingPanel, playerEvaluator);

		addPlayersButton = new JButton("Add Players");
		addPlayersButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				SwingUtilities.invokeLater(new Runnable()
				{
					public void run()
					{
						JFrame parseFrame = new JFrame("Add Players");

						parseFrame.setContentPane(parsePanel);
						parseFrame.pack();
						parseFrame.setLocationRelativeTo(MainPanel.this);
						parseFrame.setVisible(true);
					}
				});
			}
		});

		removePlayersButton = new JButton("Remove Players");
		removePlayersButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				for (Player<A> player : rosterPanel.getSelectedPlayers())
				{
					roster.remove(player);
				}
			}
		});

		createFormationsButton = new JButton("Create Formations");
		createFormationsButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				SwingUtilities.invokeLater(new Runnable()
				{
					public void run()
					{
						JFrame formationBuilderFrame = new JFrame("Formation Builder");

						formationBuilderFrame.setContentPane(
							new FormationBuilderPanel<A>(
									formationTemplatePanelFactory,
									formationBuilder,
									playerEvaluator,
									roster.copy()));
						formationBuilderFrame.pack();
						formationBuilderFrame.setLocationRelativeTo(MainPanel.this);
						formationBuilderFrame.setVisible(true);
					}
				});
			}
		});

		plotButton = new JButton("Plot");
		plotButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				SwingUtilities.invokeLater(new Runnable()
				{
					public void run()
					{
						new PlotFrame<A>(playerEvaluator, rosterPanel.getSelectedPlayers());
					}
				});
			}
		});

		clearRosterButton = new JButton("Clear Roster");
		clearRosterButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				roster.clear();
			}
		});

		buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 5, 5));
		buttonPanel.add(addPlayersButton);
		buttonPanel.add(removePlayersButton);
		buttonPanel.add(createFormationsButton);
		buttonPanel.add(plotButton);
		buttonPanel.add(clearRosterButton);

		setLayout(new BorderLayout());

		add(rosterPanel, BorderLayout.CENTER);
		add(playerPanel, BorderLayout.EAST);
		add(buttonPanel, BorderLayout.SOUTH);
	}
}
