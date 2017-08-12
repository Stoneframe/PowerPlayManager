package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import evaluators.PlayerEvaluator;
import model.Attributes;
import model.Roster;
import parsers.players.PlayersParser;

public class MainPanel<A extends Attributes> extends JPanel
{
	private static final long serialVersionUID = -8438576029794021570L;

	private ParsePanel<A> parsePanel;
	private RosterPanel<A> rosterPanel;
	private PlayerPanel<A> playerPanel;

	private JPanel buttonPanel;
	private JButton createFormationsButton;
	private JButton clearRosterButton;

	private Roster<A> roster = new Roster<A>();

	public MainPanel(
			AttributesPanel<A> attributesPanel,
			List<PlayerEvaluator<A>> evaluators,
			List<PlayersParser<A>> parsers)
	{
		parsePanel = new ParsePanel<A>(parsers);
		parsePanel.setPlayersParseListener(new PlayersParsedListener<A>()
		{
			public void playersParsed(
					Object source,
					PlayersParsedEvent<A> event)
			{
				roster.addAll(event.getPlayers());
			}
		});

		rosterPanel = new RosterPanel<A>();
		rosterPanel.bind(roster);
		rosterPanel.setPlayerEvaluators(evaluators);
		rosterPanel.setPlayerSelectedListener(new PlayerSelectedListener<A>()
		{
			public void playerSelected(Object source,
					PlayerSelectedEvent<A> event)
			{
				playerPanel.bind(event.getPlayer());
			}
		});

		playerPanel = new PlayerPanel<A>(attributesPanel);
		playerPanel.setPlayerEvaluators(evaluators);

		createFormationsButton = new JButton("Create Formations");
		createFormationsButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				SwingUtilities.invokeLater(new Runnable()
				{
					public void run()
					{
						//new FormationBuilderFrame(roster.copy(), evaluators);
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
		buttonPanel.add(createFormationsButton);
		buttonPanel.add(clearRosterButton);

		setLayout(new BorderLayout());
		add(parsePanel, BorderLayout.NORTH);
		add(rosterPanel, BorderLayout.CENTER);
		add(playerPanel, BorderLayout.EAST);
		add(buttonPanel, BorderLayout.SOUTH);
	}
}
