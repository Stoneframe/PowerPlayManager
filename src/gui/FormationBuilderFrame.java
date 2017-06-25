package gui;

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
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import model.Formation;
import model.FormationTemplate;
import model.PlayerEvaluator;
import model.Roster;
import model.builders.PaulsFormationBuilder;

public class FormationBuilderFrame extends JFrame
{
	private static final long serialVersionUID = -5043434553464317980L;

	private DefaultListModel<FormationTemplate> templateListModel;
	private JList<FormationTemplate> templateList;

	private FormationTemplatePanel templatePanel;

	private JButton addTemplateButton;
	private JButton removeTemplateButton;

	private JButton createFormationsButton;

	public FormationBuilderFrame(
			Roster roster,
			List<PlayerEvaluator> evaluators)
	{
		templateListModel = new DefaultListModel<FormationTemplate>();

		templateList = new JList<FormationTemplate>(templateListModel);
		templateList.setPreferredSize(new Dimension(200, 1));
		templateList.setBorder(BorderFactory.createEtchedBorder());
		templateList.addListSelectionListener(new ListSelectionListener()
		{
			public void valueChanged(ListSelectionEvent e)
			{
				removeTemplateButton.setEnabled(
					!templateList.isSelectionEmpty());

				templatePanel.setFormationTemplate(
					templateList.getSelectedValue());
			}
		});

		templatePanel = new FormationTemplatePanel(evaluators);

		addTemplateButton = new JButton("Add");
		addTemplateButton.setMinimumSize(new Dimension(100, 20));
		addTemplateButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				templateListModel.addElement(
					templatePanel.getFormationTemplate());
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
				}
			}
		});

		createFormationsButton = new JButton("Create Formations");
		createFormationsButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				List<Formation> formations = new LinkedList<Formation>();

				for (int i = 0; i < templateListModel.size(); i++)
				{
					FormationTemplate template = templateListModel
							.getElementAt(i);

					Formation formation = new PaulsFormationBuilder()
							.createFormation(roster, template);

					formations.add(formation);
				}

				SwingUtilities.invokeLater(new Runnable()
				{
					public void run()
					{
						new FormationDisplayFrame(formations);
					}
				});
			}
		});

		setLayout(new BorderLayout());

		JPanel templateListPanel = new JPanel();

		templateListPanel.setLayout(new GridLayout(1, 1));
		templateListPanel.setBorder(
			BorderFactory.createTitledBorder("Formations"));
		templateListPanel.add(templateList);

		add(templateListPanel, BorderLayout.WEST);

		add(templatePanel, BorderLayout.CENTER);

		JPanel addRemoveButtonPanel = new JPanel();

		addRemoveButtonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		addRemoveButtonPanel.add(addTemplateButton);
		addRemoveButtonPanel.add(removeTemplateButton);

		add(addRemoveButtonPanel, BorderLayout.NORTH);

		add(createFormationsButton, BorderLayout.SOUTH);

		pack();
		setVisible(true);
	}
}
