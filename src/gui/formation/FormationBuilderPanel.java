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
import java.util.function.Consumer;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListCellRenderer;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import evaluators.PlayerEvaluator;
import formation.Formation;
import formation.FormationBuilder;
import formation.FormationTemplate;
import formation.PlayerManipulator;
import formation.manipulators.PlayerEnergyManipulator;
import formation.manipulators.PlayerFormManipulator;
import formation.manipulators.PlayerNoneManipulator;
import formation.manipulators.PlayerWarpManipulator;
import model.Attributes;
import model.Player;
import model.Roster;
import util.PropertyChangedEvent;
import util.PropertyChangedListener;
import warper.PlayerWarper;

public class FormationBuilderPanel<A extends Attributes>
	extends JPanel
{
	private static final long serialVersionUID = -5043434553464317980L;

	private DefaultListModel<Player<A>> playerListModel;
	private JList<Player<A>> playerList;
	private JButton selectAllButton;

	private DefaultListModel<FormationTemplate<A>> templateListModel;
	private JList<FormationTemplate<A>> templateList;

	private FormationTemplatePanel<A> templatePanel;

	private JLabel nbrPlayersSelectedLabel;

	private JButton addTemplateButton;
	private JButton removeTemplateButton;

	private JCheckBox callbackCheckbox;
	private JRadioButton noneRadioButton;
	private JRadioButton formRadioButton;
	private JRadioButton energyRadioButton;
	private JRadioButton warpRadioButton;
	private JLabel yearsLabel;
	private JTextField yearsTextField;

	private JButton createFormationsButton;

	public FormationBuilderPanel(
		FormationTemplatePanelFactory<A> formationTemplatePanelFactory,
		FormationBuilder<A> formationBuilder,
		PlayerEvaluator<A> playerEvaluator,
		PlayerWarper<A> playerWarper,
		Roster<A> roster,
		Consumer<List<Formation<A>>> formationsCreatedCallback)
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

				updatePlayersSelectedLabel(roster);
				updateCreateFormationsButton(roster);
			}
		});

		selectAllButton = new JButton("Select All");
		selectAllButton.setPreferredSize(new Dimension(1, 15));
		selectAllButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				for (int i = 0; i < playerListModel.size(); i++)
				{
					Player<A> player = playerListModel.get(i);

					if (!roster.contains(player))
					{
						roster.add(player);
					}

					playerListModel.set(i, player);
				}

				updatePlayersSelectedLabel(roster);
				updateCreateFormationsButton(roster);
			}
		});

		templateListModel = new DefaultListModel<>();

		templateList = new JList<>(templateListModel);
		templateList.setPreferredSize(new Dimension(200, 1));
		templateList.setBorder(BorderFactory.createEtchedBorder());
		templateList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
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

		callbackCheckbox = new JCheckBox("Create groups");
		noneRadioButton = new JRadioButton("None");
		formRadioButton = new JRadioButton("Form");
		energyRadioButton = new JRadioButton("Energy");
		warpRadioButton = new JRadioButton("Warp");
		yearsLabel = new JLabel("Years:");
		yearsTextField = new JTextField(3);

		ButtonGroup buttonGroup = new ButtonGroup();
		buttonGroup.add(noneRadioButton);
		buttonGroup.add(formRadioButton);
		buttonGroup.add(energyRadioButton);
		buttonGroup.add(warpRadioButton);

		ActionListener radioButtonListener = new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				yearsLabel.setEnabled(warpRadioButton.isSelected());
				yearsTextField.setEnabled(warpRadioButton.isSelected());
			}
		};

		noneRadioButton.setSelected(true);
		noneRadioButton.addActionListener(radioButtonListener);
		formRadioButton.addActionListener(radioButtonListener);
		energyRadioButton.addActionListener(radioButtonListener);
		warpRadioButton.addActionListener(radioButtonListener);
		yearsLabel.setEnabled(false);
		yearsTextField.setEnabled(false);

		createFormationsButton = new JButton("Create Formations");
		updateCreateFormationsButton(roster);
		createFormationsButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				List<FormationTemplate<A>> formationTemplates = getFormationTemplates();

				List<Formation<A>> formations =
						formationBuilder.createFormations(
							roster,
							formationTemplates,
							getPlayerManipulator());

				updatePlayersSelectedLabel(roster);
				updateCreateFormationsButton(roster);

				playerList.updateUI();

				SwingUtilities.invokeLater(new Runnable()
				{
					public void run()
					{
						FormationDisplayFrame<A> display = new FormationDisplayFrame<A>(formations);

						display.setLocationRelativeTo(FormationBuilderPanel.this);
						display.setVisible(true);
					}
				});

				if (callbackCheckbox.isSelected())
				{
					formationsCreatedCallback.accept(formations);
				}
			}

			private PlayerManipulator<A> getPlayerManipulator()
			{
				if (formRadioButton.isSelected())
				{
					return new PlayerFormManipulator<>(playerEvaluator);
				}

				if (energyRadioButton.isSelected())
				{
					return new PlayerEnergyManipulator<>(playerEvaluator);
				}

				if (warpRadioButton.isSelected())
				{
					return new PlayerWarpManipulator<>(playerEvaluator, playerWarper, getYears());
				}

				return new PlayerNoneManipulator<>();
			}

			private int getYears()
			{
				try
				{
					return Integer.parseInt(yearsTextField.getText());
				}
				catch (NumberFormatException e)
				{
					JOptionPane.showMessageDialog(
						FormationBuilderPanel.this,
						"The 'Years' input is not a valid number. Using 0 as default.",
						"Bad Year Input",
						JOptionPane.WARNING_MESSAGE);

					return 0;
				}
			}
		});

		setLayout(new BorderLayout());

		JScrollPane playerListScrollPanel = new JScrollPane(playerList);
		playerListScrollPanel.setBorder(BorderFactory.createEtchedBorder());

		JPanel playerListPanel = new JPanel(new BorderLayout());
		playerListPanel.setPreferredSize(new Dimension(200, 1));
		playerListPanel.setBorder(BorderFactory.createTitledBorder("Players"));
		playerListPanel.add(playerListScrollPanel, BorderLayout.CENTER);
		playerListPanel.add(selectAllButton, BorderLayout.SOUTH);

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

		JPanel southPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		southPanel.add(callbackCheckbox);
		southPanel.add(noneRadioButton);
		southPanel.add(formRadioButton);
		southPanel.add(energyRadioButton);
		southPanel.add(warpRadioButton);
		southPanel.add(yearsLabel);
		southPanel.add(yearsTextField);
		southPanel.add(createFormationsButton);

		add(southPanel, BorderLayout.SOUTH);

		updatePlayersSelectedLabel(roster);
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

	private void updatePlayersSelectedLabel(Roster<A> roster)
	{
		nbrPlayersSelectedLabel.setText("Number of players selected: " + roster.size());
	}

	private void updateCreateFormationsButton(Roster<A> roster)
	{
		createFormationsButton.setEnabled(canCreateFormations(roster));
	}
}
