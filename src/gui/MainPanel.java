package gui;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.apache.http.auth.InvalidCredentialsException;

import evaluators.PlayerEvaluator;
import files.FileHandler;
import formation.Formation;
import formation.FormationBuilder;
import formation.Position;
import gui.formation.FormationBuilderPanel;
import gui.formation.FormationTemplatePanelFactory;
import gui.menu.MenuBar;
import gui.player.AttributesPanel;
import gui.player.PlayerPanel;
import gui.player.PlayerSelectedEvent;
import gui.player.PlayerSelectedListener;
import gui.player.PlayersParsedEvent;
import gui.player.PlayersParsedListener;
import gui.plot.PlayerPlotPanel;
import gui.plot.PlotPanel;
import gui.searcher.SearcherPanel;
import gui.util.PpmLoginDialog;
import gui.util.PpmLoginDialog.Credientials;
import importer.Importer;
import model.Attributes;
import model.Groups;
import model.Player;
import model.Roster;
import parsers.players.PlayersParser;
import searcher.SearchTemplateStorage;
import warper.PlayerWarper;

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
	private JButton searchButton;

	private Roster<A> roster = new Roster<>();
	private Groups<A> groups = new Groups<>();

	public MainPanel(
		MenuBar menuBar,
		FileHandler<A> fileHandler,
		AttributesPanel<A> attributesPanel,
		FormationTemplatePanelFactory<A> formationTemplatePanelFactory,
		FormationBuilder<A> formationBuilder,
		PlayerWarper<A> playerWarper,
		List<PlayersParser<A>> parsers,
		PlayerEvaluator<A> playerEvaluator,
		SearchTemplateStorage<A> searchTemplateStorage,
		Importer<A> importer)
	{
		menuBar.addFileImportActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				if (isShowing())
				{
					File file = chooseFile(true);

					if (file != null)
					{
						fileHandler.loadPlayersFromFile(file, roster);
					}
				}
			}
		});
		menuBar.addFileExportActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				if (isShowing())
				{
					File file = chooseFile(false);

					if (file != null)
					{
						fileHandler.savePlayersToFile(file, roster);
					}
				}
			}
		});
		menuBar.addPpmImportTeamActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				if (isShowing())
				{
					importPlayers(importer, Importer.TEAM, "Team");
				}
			}
		});
		menuBar.addPpmImportMarketOnTransferActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				if (isShowing())
				{
					importPlayers(importer, Importer.MARKET_ON_TRANSFER, "Market (on transfer)");
				}
			}
		});
		menuBar.addPpmImportMarketFreeAgentActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				if (isShowing())
				{
					importPlayers(importer, Importer.MARKET_FREE_AGENT, "Market (free agent)");
				}
			}
		});

		rosterPanel = new RosterPanel<A>(roster, playerEvaluator, p -> groups.isEnabled(p));
		rosterPanel.setPlayerSelectedListener(new PlayerSelectedListener<A>()
		{
			public void playerSelected(Object source, PlayerSelectedEvent<A> event)
			{
				playerPanel.bind(event.getPlayer());

				groupPanel.setAddButtonEnabled(!rosterPanel.getSelectedPlayers().isEmpty());
			}
		});

		playerPanel = new PlayerPanel<A>(attributesPanel, playerEvaluator, playerWarper);

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

				for (Groups<A>.Group group : groupPanel.getGroups())
				{
					boolean isSelected = groupPanel.getSelectedGroups().contains(group);

					group.setEnabled(isSelected);
				}

				groupPanel.setRemoveButtonEnabled(!groupPanel.getSelectedGroups().isEmpty());

				rosterPanel.notifyPlayerFilterChanged();
			}
		});
		groupPanel.addAddButtonActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				List<Player<A>> players = rosterPanel.getSelectedPlayers();

				String initalValue = players.size() == 1
					? players.get(0).getName()
					: "";

				String name = JOptionPane.showInputDialog(groupPanel, "Name:", initalValue);

				if (name != null)
				{
					Groups<A>.Group group = groups.add(name, players);

					groupPanel.addGroup(group);
				}

				rosterPanel.notifyPlayerFilterChanged();
			}
		});
		groupPanel.addRemoveButtonActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				List<Groups<A>.Group> selectedGroups = groupPanel.getSelectedGroups();

				for (Groups<A>.Group group : selectedGroups)
				{
					groups.remove(group);
					groupPanel.removeGroup(group);
				}

				rosterPanel.notifyPlayerFilterChanged();
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
					groups.remove(player);
				}
			}
		});

		createFormationsButton = new JButton("Formations");
		createFormationsButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				SwingUtilities.invokeLater(new Runnable()
				{
					public void run()
					{
						JFrame formationBuilderFrame = new JFrame("Formations");

						formationBuilderFrame.setContentPane(
							new FormationBuilderPanel<A>(
								formationTemplatePanelFactory,
								formationBuilder,
								playerEvaluator,
								playerWarper,
								roster.copy(p -> groups.isEnabled(p)),
								formations -> createGroups(formations)));
						formationBuilderFrame.pack();
						formationBuilderFrame.setLocationRelativeTo(MainPanel.this);
						formationBuilderFrame.setVisible(true);
					}

					private void createGroups(List<Formation<A>> formations)
					{
						createGroupsByFormation(formations);
						createGroupsByPositions(formations);
					}

					private void createGroupsByFormation(List<Formation<A>> formations)
					{
						for (Formation<A> formation : formations)
						{
							Groups<A>.Group group = groups.add(
								formation.getName(),
								formation.getPlayers());

							groupPanel.addGroup(group);
						}
					}

					private void createGroupsByPositions(List<Formation<A>> formations)
					{
						Map<String, List<Player<A>>> o = formations
							.stream()
							.flatMap(f -> f.getPositions().stream())
							.collect(
								Collectors.groupingBy(
									Position<A>::getName,
									Collectors.mapping(p -> p.getPlayer(), Collectors.toList())));

						for (Entry<String, List<Player<A>>> entry : o.entrySet())
						{
							Groups<A>.Group group = groups.add(
								entry.getKey(),
								entry.getValue());

							groupPanel.addGroup(group);
						}
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
						PlotPanel<A> plotPanel = new PlayerPlotPanel<A>(
							playerEvaluator,
							playerWarper,
							rosterPanel.getSelectedPlayers(),
							groupPanel.getSelectedGroups(),
							0);

						plotPanel.draw();

						JFrame plotFrame = new JFrame("Plot");
						plotFrame.setContentPane(plotPanel);
						plotFrame.pack();
						plotFrame.setLocationRelativeTo(MainPanel.this);
						plotFrame.setVisible(true);
					}
				});
			}
		});

		searchButton = new JButton("Search");
		searchButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				SwingUtilities.invokeLater(new Runnable()
				{
					public void run()
					{
						JFrame searcherFrame = new JFrame("Search");

						searcherFrame.setContentPane(
							new SearcherPanel<A>(
								roster.copy(p -> groups.isEnabled(p)),
								playerEvaluator,
								playerWarper,
								searchTemplateStorage,
								(groupName, players) -> createGroup(groupName, players)));
						searcherFrame.pack();
						searcherFrame.setLocationRelativeTo(MainPanel.this);
						searcherFrame.setVisible(true);
					}

					private void createGroup(String groupName, List<Player<A>> players)
					{
						Groups<A>.Group group = groups.add(groupName, players);

						groupPanel.addGroup(group);
					}
				});
			}
		});

		buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 5, 5));
		buttonPanel.add(addPlayersButton);
		buttonPanel.add(removePlayersButton);
		buttonPanel.add(createFormationsButton);
		buttonPanel.add(groupsButton);
		buttonPanel.add(plotButton);
		buttonPanel.add(searchButton);

		setLayout(new BorderLayout());

		add(rosterPanel, BorderLayout.CENTER);
		add(playerPanel, BorderLayout.EAST);
		add(buttonPanel, BorderLayout.SOUTH);
	}

	private File chooseFile(boolean open)
	{
		JFileChooser fc = new JFileChooser();

		fc.setFileFilter(new FileNameExtensionFilter("PPM Files", "ppm"));

		int returnVal = open
			? fc.showOpenDialog(MainPanel.this)
			: fc.showSaveDialog(MainPanel.this);

		if (returnVal == JFileChooser.APPROVE_OPTION)
		{
			FileNameExtensionFilter fileFilter =
				(FileNameExtensionFilter)fc.getFileFilter();

			return fc.accept(fc.getSelectedFile())
				? fc.getSelectedFile()
				: new File(
					fc.getSelectedFile() + "." + fileFilter.getExtensions()[0]);
		}
		else
		{
			return null;
		}
	}

	private void importPlayers(Importer<A> importer, int importType, String groupName)
	{
		Credientials credientials = PpmLoginDialog.show();

		if (credientials == null)
		{
			return;
		}

		try
		{
			MainPanel.this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));

			List<Player<A>> players = importer.importPlayers(
				importType,
				credientials.getUsername(),
				credientials.getPassword());

			roster.addAll(players);

			Groups<A>.Group group = groups.add(groupName, players);

			groupPanel.addGroup(group);
		}
		catch (InvalidCredentialsException e1)
		{
			JOptionPane.showMessageDialog(
				MainPanel.this,
				"Invalid credentials",
				"Login",
				JOptionPane.ERROR_MESSAGE);
		}
		catch (IOException ex)
		{
			ex.printStackTrace();
		}
		finally
		{
			MainPanel.this.setCursor(Cursor.getDefaultCursor());
		}
	}
}
