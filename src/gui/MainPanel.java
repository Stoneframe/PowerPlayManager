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

import builders.formation.FormationBuilder;
import builders.formation.FormationTemplate;
import evaluators.AttributeEvaluator;
import evaluators.PlayerEvaluator;
import gui.formation.FormationBuilderFrame;
import gui.formation.FormationPanelFactory;
import gui.formation.FormationTemplatePanelFactory;
import gui.player.AttributesPanel;
import gui.player.PlayerPanel;
import gui.player.PlayerSelectedEvent;
import gui.player.PlayerSelectedListener;
import gui.player.PlayersParsedEvent;
import gui.player.PlayersParsedListener;
import model.Attributes;
import model.Formation;
import model.Player;
import model.Roster;
import parsers.players.PlayersParser;
import predictors.PlayerPredictor;

public class MainPanel<A extends Attributes, F extends Formation, FT extends FormationTemplate>
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
	private JButton clearRosterButton;

	private Roster<A> roster = new Roster<A>();

	public MainPanel(
			AttributesPanel<A> attributesPanel,
			FormationTemplatePanelFactory<FT, A> formationTemplatePanelFactory,
			FormationPanelFactory<F> formationPanelFactory,
			FormationBuilder<A, F, FT> formationBuilder,
			PlayerEvaluator<A> playerEvaluator,
			List<AttributeEvaluator<A>> attributeEvaluators,
			PlayerPredictor<A> playerPredictor,
			List<PlayersParser<A>> parsers)
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

		playerPanel = new PlayerPanel<A>(attributesPanel);
		playerPanel.setAttributeEvaluators(attributeEvaluators);

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
						parseFrame.setLocationRelativeTo(null);
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
				roster.forEach(player -> playerPredictor.predictPlayerAttributes(player, 5));

				SwingUtilities.invokeLater(new Runnable()
				{
					public void run()
					{
						new FormationBuilderFrame<A, F, FT>(
								formationTemplatePanelFactory,
								formationPanelFactory,
								formationBuilder,
								attributeEvaluators,
								roster.copy());
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
		buttonPanel.add(clearRosterButton);

		setLayout(new BorderLayout());

		add(rosterPanel, BorderLayout.CENTER);
		add(playerPanel, BorderLayout.EAST);
		add(buttonPanel, BorderLayout.SOUTH);
	}
}
