package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

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
import gui.plot.PlotPanel;
import model.Attributes;
import model.Player;
import model.Roster;
import parsers.players.PlayersParser;

public class MainPanel<A extends Attributes>
	extends JPanel
{
	private static final long serialVersionUID = -8438576029794021570L;

	private RosterPanel<A> rosterPanel;
	private PlayerPanel<A> playerPanel;
	private ParsePanel<A> parsePanel;
	private GroupPanel<A> groupPanel;

	private JFrame parseFrame;
	private JFrame groupFrame;

	private JPanel buttonPanel;
	private JButton addPlayersButton;
	private JButton removePlayersButton;
	private JButton createFormationsButton;
	private JButton groupsButton;
	private JButton plotButton;
	private JButton clearRosterButton;

	private Roster<A> roster = new Roster<A>();

	public MainPanel(
			AttributesPanel<A> attributesPanel,
			FormationTemplatePanelFactory<A> formationTemplatePanelFactory,
			FormationBuilder<A> formationBuilder,
			List<PlayersParser<A>> parsers,
			PlayerEvaluator<A> playerEvaluator)
	{
		rosterPanel = new RosterPanel<A>(roster, playerEvaluator);
		rosterPanel.setPlayerSelectedListener(new PlayerSelectedListener<A>()
		{
			public void playerSelected(Object source, PlayerSelectedEvent<A> event)
			{
				playerPanel.bind(event.getPlayer());

				groupPanel.setAddButtonEnabled(!rosterPanel.getSelectedPlayers().isEmpty());
			}
		});

		playerPanel = new PlayerPanel<A>(attributesPanel, playerEvaluator);

		parsePanel = new ParsePanel<A>(parsers);
		parsePanel.setPlayersParseListener(new PlayersParsedListener<A>()
		{
			public void playersParsed(Object source, PlayersParsedEvent<A> event)
			{
				roster.addAll(event.getPlayers());
			}
		});

		groupPanel = new GroupPanel<>();
		groupPanel.addGroupListSelectionListener(new ListSelectionListener()
		{
			public void valueChanged(ListSelectionEvent e)
			{
				if (e.getValueIsAdjusting()) return;

				for (Roster<A>.Group group : groupPanel.getGroups())
				{
					boolean isSelected = groupPanel.getSelectedGroups().contains(group);

					group.setEnabled(isSelected);
				}

				groupPanel.setRemoveButtonEnabled(!groupPanel.getSelectedGroups().isEmpty());
			}
		});
		groupPanel.addAddButtonActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				String name = JOptionPane.showInputDialog(groupPanel, "Name:");

				if (name != null)
				{
					List<Player<A>> players = rosterPanel.getSelectedPlayers();

					Roster<A>.Group group = roster.addGroup(name, players);

					groupPanel.addGroup(group);
				}
			}
		});
		groupPanel.addRemoveButtonActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				List<Roster<A>.Group> selectedGroups = groupPanel.getSelectedGroups();

				for (Roster<A>.Group group : selectedGroups)
				{
					roster.removeGroup(group);
					groupPanel.removeGroup(group);
				}
			}
		});

		parseFrame = new JFrame("Add Players");
		parseFrame.setContentPane(parsePanel);
		parseFrame.pack();

		groupFrame = new JFrame("Groups");
		groupFrame.setContentPane(groupPanel);
		groupFrame.pack();

		addPlayersButton = new JButton("Add Players");
		addPlayersButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				SwingUtilities.invokeLater(new Runnable()
				{
					public void run()
					{
						if (parseFrame.isVisible())
						{
							parseFrame.toFront();
						}
						else
						{
							parseFrame.setLocationRelativeTo(MainPanel.this);
							parseFrame.setVisible(true);
						}
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

		groupsButton = new JButton("Groups");
		groupsButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				SwingUtilities.invokeLater(new Runnable()
				{
					public void run()
					{
						if (groupFrame.isVisible())
						{
							groupFrame.toFront();
						}
						else
						{
							groupFrame.setLocationRelativeTo(MainPanel.this);
							groupFrame.setVisible(true);
						}
					}
				});
			}
		});

		plotButton = new JButton("Plot Growth");
		plotButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				SwingUtilities.invokeLater(new Runnable()
				{
					public void run()
					{
						JFrame plotFrame = new JFrame("Plot");

						plotFrame.setContentPane(
							new PlotPanel<A>(
									playerEvaluator,
									rosterPanel.getSelectedPlayers(),
									groupPanel.getSelectedGroups()));
						plotFrame.pack();
						plotFrame.setLocationRelativeTo(MainPanel.this);
						plotFrame.setVisible(true);
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
		buttonPanel.add(groupsButton);
		buttonPanel.add(plotButton);
		buttonPanel.add(clearRosterButton);

		setLayout(new BorderLayout());

		add(rosterPanel, BorderLayout.CENTER);
		add(playerPanel, BorderLayout.EAST);
		add(buttonPanel, BorderLayout.SOUTH);
	}
}
