package gui.formation;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import builders.formation.FormationBuilder;
import builders.formation.FormationTemplate;
import evaluators.AttributeEvaluator;
import model.Attributes;
import model.Formation;
import model.Roster;
import util.PropertyChangedEvent;
import util.PropertyChangedListener;

public class FormationBuilderFrame<
		A extends Attributes,
		F extends Formation,
		FT extends FormationTemplate>
	extends JFrame
{
	private static final long serialVersionUID = -5043434553464317980L;

	private DefaultListModel<FT> templateListModel;
	private JList<FT> templateList;

	private FormationTemplatePanel<FT> templatePanel;

	private JLabel nbrPlayersRemainingLabel;

	private JButton addTemplateButton;
	private JButton removeTemplateButton;

	private JButton createFormationsButton;

	public FormationBuilderFrame(
			FormationTemplatePanelFactory<FT, A> formationTemplatePanelFactory,
			FormationPanelFactory<F> formationPanelFactory,
			FormationBuilder<A, F, FT> formationBuilder,
			List<AttributeEvaluator<A>> attributeEvaluators,
			Roster<A> roster)
	{
		super("Formations Builder");

		templateListModel = new DefaultListModel<FT>();

		templateList = new JList<FT>(templateListModel);
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

		templatePanel = formationTemplatePanelFactory.newInstance(attributeEvaluators);
		templatePanel.setNameTextPropertyChangedListener(new PropertyChangedListener()
		{
			public void propertyChanged(Object source, PropertyChangedEvent event)
			{
				addTemplateButton.setEnabled(!((String)event.getPropertyValue()).equals(""));
			}
		});

		nbrPlayersRemainingLabel = new JLabel(createPlayersRemainingText(roster));
		nbrPlayersRemainingLabel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));

		addTemplateButton = new JButton("Add");
		addTemplateButton.setEnabled(false);
		addTemplateButton.setMinimumSize(new Dimension(100, 20));
		addTemplateButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				templateListModel.addElement(templatePanel.getFormationTemplate());
				createFormationsButton.setEnabled(canCreateFormations(roster));
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
					createFormationsButton.setEnabled(canCreateFormations(roster));
				}
			}
		});

		createFormationsButton = new JButton("Create Formations");
		createFormationsButton.setEnabled(canCreateFormations(roster));
		createFormationsButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				List<FT> formationTemplates = getFormationTemplates();

				List<F> formations = formationBuilder.createFormations(roster, formationTemplates);

				nbrPlayersRemainingLabel.setText(createPlayersRemainingText(roster));
				createFormationsButton.setEnabled(canCreateFormations(roster));

				SwingUtilities.invokeLater(new Runnable()
				{
					public void run()
					{
						new FormationDisplayFrame<F>(formationPanelFactory, formations);
					}
				});
			}
		});

		setLayout(new BorderLayout());

		JPanel templateListPanel = new JPanel();

		templateListPanel.setLayout(new GridLayout(1, 1));
		templateListPanel.setBorder(BorderFactory.createTitledBorder("Formations"));
		templateListPanel.add(templateList);

		add(templateListPanel, BorderLayout.WEST);

		add(templatePanel, BorderLayout.CENTER);

		JPanel addRemoveButtonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));

		addRemoveButtonPanel.add(addTemplateButton);
		addRemoveButtonPanel.add(removeTemplateButton);

		JPanel topPanel = new JPanel(new BorderLayout());

		topPanel.add(nbrPlayersRemainingLabel, BorderLayout.WEST);
		topPanel.add(addRemoveButtonPanel, BorderLayout.EAST);

		add(topPanel, BorderLayout.NORTH);

		add(createFormationsButton, BorderLayout.SOUTH);

		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}

	private static String createPlayersRemainingText(Roster<?> roster)
	{
		return "Number of players remaining: " + roster.size();
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

	private List<FT> getFormationTemplates()
	{
		List<FT> formationTemplates = new LinkedList<>();

		for (int i = 0; i < templateListModel.size(); i++)
		{
			FT formationTemplate = templateListModel.getElementAt(i);

			formationTemplates.add(formationTemplate);
		}
		return formationTemplates;
	}
}
