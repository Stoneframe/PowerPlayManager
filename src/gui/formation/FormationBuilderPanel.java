package gui.formation;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.LinkedList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListCellRenderer;
import javax.swing.SwingUtilities;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import evaluators.PlayerEvaluator;
import formation.Formation;
import formation.FormationBuilder;
import formation.FormationTemplate;
import model.Attributes;
import model.Player;
import model.Roster;
import util.PropertyChangedEvent;
import util.PropertyChangedListener;

public class FormationBuilderPanel<A extends Attributes>
	extends JPanel
{
	private static final long serialVersionUID = -5043434553464317980L;

	private DefaultListModel<Player<A>> playerListModel;
	private JList<Player<A>> playerList;

	private DefaultListModel<FormationTemplate<A>> templateListModel;
	private JList<FormationTemplate<A>> templateList;

	private FormationTemplatePanel<A> templatePanel;

	private JLabel nbrPlayersSelectedLabel;

	private JButton addTemplateButton;
	private JButton removeTemplateButton;

	private JButton createFormationsButton;

	public FormationBuilderPanel(
			FormationTemplatePanelFactory<A> formationTemplatePanelFactory,
			FormationBuilder<A> formationBuilder,
			PlayerEvaluator<A> playerEvaluator,
			Roster<A> roster)
	{
		playerListModel = new DefaultListModel<>();
		roster.forEach(playerListModel::addElement);

		playerList = new JList<>(playerListModel);
		playerList.setCellRenderer(new ListCellRenderer<Player<A>>()
		{
			public Component getListCellRendererComponent(
					JList<? extends Player<A>> list,
					Player<A> value,
					int index,
					boolean isSelected,
					boolean cellHasFocus)
			{
				JCheckBox checkBox = new JCheckBox(value.getName());

				checkBox.setBackground(Color.WHITE);
				checkBox.setSelected(roster.contains(value));

				return checkBox;
			}
		});
		playerList.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent e)
			{
				if (e.getClickCount() > 1)
				{
					return;
				}

				int index = playerList.locationToIndex(e.getPoint());

				if (index == -1)
				{
					return;
				}

				Player<A> player = playerListModel.get(index);

				if (roster.contains(player))
				{
					roster.remove(player);
				}
				else
				{
					roster.add(player);
				}

				playerListModel.set(index, player);

				updatePlayersSelectedList(roster);
				updateCreateFormationsButton(roster);
			}
		});

		templateListModel = new DefaultListModel<>();

		templateList = new JList<>(templateListModel);
		templateList.setPreferredSize(new Dimension(200, 1));
		templateList.setBorder(BorderFactory.createEtchedBorder());
		templateList.addListSelectionListener(new ListSelectionListener()
		{
			public void valueChanged(ListSelectionEvent e)
			{
				removeTemplateButton.setEnabled(!templateList.isSelectionEmpty());

				templatePanel.setFormationTemplate(templateList.getSelectedValue());
			}
		});

		templatePanel = formationTemplatePanelFactory.newInstance(playerEvaluator);
		templatePanel.setNameTextPropertyChangedListener(new PropertyChangedListener()
		{
			public void propertyChanged(Object source, PropertyChangedEvent event)
			{
				addTemplateButton.setEnabled(!((String)event.getPropertyValue()).equals(""));
			}
		});

		nbrPlayersSelectedLabel = new JLabel();
		nbrPlayersSelectedLabel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));

		addTemplateButton = new JButton("Add");
		addTemplateButton.setEnabled(false);
		addTemplateButton.setMinimumSize(new Dimension(100, 20));
		addTemplateButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				templateListModel.addElement(templatePanel.getFormationTemplate());
				updateCreateFormationsButton(roster);
			}
		});

		removeTemplateButton = new JButton("Remove");
		removeTemplateButton.setEnabled(false);
		removeTemplateButton.setMinimumSize(new Dimension(100, 20));
		removeTemplateButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				if (!templateList.isSelectionEmpty())
				{
					templateListModel.remove(templateList.getSelectedIndex());
					updateCreateFormationsButton(roster);
				}
			}
		});

		createFormationsButton = new JButton("Create Formations");
		updateCreateFormationsButton(roster);
		createFormationsButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				List<FormationTemplate<A>> formationTemplates = getFormationTemplates();

				List<Formation<A>> formations =
						formationBuilder.createFormations(roster, formationTemplates);

				updatePlayersSelectedList(roster);
				updateCreateFormationsButton(roster);

				playerList.updateUI();

				SwingUtilities.invokeLater(new Runnable()
				{
					public void run()
					{
						new FormationDisplayFrame<A>(formations);
					}
				});
			}
		});

		setLayout(new BorderLayout());

		JScrollPane playerListPanel = new JScrollPane(playerList);
		playerListPanel.setBorder(
			BorderFactory.createCompoundBorder(
				BorderFactory.createTitledBorder("Players"),
				BorderFactory.createEtchedBorder()));
		playerListPanel.setPreferredSize(new Dimension(200, 1));

		add(playerListPanel, BorderLayout.WEST);

		JPanel templateListPanel = new JPanel();
		templateListPanel.setLayout(new BorderLayout());
		templateListPanel.setBorder(BorderFactory.createTitledBorder("Formations"));
		templateListPanel.add(templateList);

		add(templateListPanel, BorderLayout.CENTER);

		add(templatePanel, BorderLayout.EAST);

		JPanel addRemoveButtonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		addRemoveButtonPanel.add(addTemplateButton);
		addRemoveButtonPanel.add(removeTemplateButton);

		JPanel topPanel = new JPanel(new BorderLayout());
		topPanel.add(nbrPlayersSelectedLabel, BorderLayout.WEST);
		topPanel.add(addRemoveButtonPanel, BorderLayout.EAST);

		add(topPanel, BorderLayout.NORTH);

		add(createFormationsButton, BorderLayout.SOUTH);

		updatePlayersSelectedList(roster);
	}

	private boolean canCreateFormations(Roster<A> roster)
	{
		return isEnoughPlayers(roster) && !getFormationTemplates().isEmpty();
	}

	private boolean isEnoughPlayers(Roster<?> roster)
	{
		int totalNbrOfRequiredPlayers = getFormationTemplates()
				.stream()
				.mapToInt(ft -> ft.getNumberOfRequiredPlayers())
				.sum();

		return totalNbrOfRequiredPlayers <= roster.size();
	}

	private List<FormationTemplate<A>> getFormationTemplates()
	{
		List<FormationTemplate<A>> formationTemplates = new LinkedList<>();

		for (int i = 0; i < templateListModel.size(); i++)
		{
			FormationTemplate<A> formationTemplate = templateListModel.getElementAt(i);

			formationTemplates.add(formationTemplate);
		}

		return formationTemplates;
	}

	private void updatePlayersSelectedList(Roster<A> roster)
	{
		nbrPlayersSelectedLabel.setText("Number of players selected: " + roster.size());
	}

	private void updateCreateFormationsButton(Roster<A> roster)
	{
		createFormationsButton.setEnabled(canCreateFormations(roster));
	}
}
