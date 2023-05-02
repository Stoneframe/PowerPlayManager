package gui.searcher;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import evaluators.PlayerEvaluator;
import gui.searcher.criterias.AgeCriteriaPanel;
import gui.searcher.criterias.BestPositionTrainingCriteriaPanel;
import gui.searcher.criterias.ClCriteriaPanel;
import gui.searcher.criterias.SideCriteriaPanel;
import gui.searcher.criterias.RatingInYearsCriteriaPanel;
import gui.util.PpmComboBox;
import model.Attributes;
import model.Player;
import model.Roster;
import searcher.SearchCriteria;
import searcher.SearchTemplate;
import searcher.SearchTemplateStorage;
import searcher.Searcher;
import searcher.criterias.AgeSearchCriteria;
import searcher.criterias.BestPositionSearchCriteria;
import searcher.criterias.ClSearchCriteria;
import searcher.criterias.SideSearchCriteria;
import searcher.criterias.RatingInYearsSearchCriteria;
import warper.PlayerWarper;

public class SearcherPanel<A extends Attributes>
	extends JPanel
{
	private static final long serialVersionUID = 7948622117141587618L;

	private final PpmComboBox<String> searchTemplatesComboBox;

	private final JButton addButton;
	private final JButton searchButton;

	private final JButton saveButton;
	private final JButton removeButton;

	private final JPanel northPanel;
	private final JPanel southPanel;

	private final SearchCriteriaListPanel centerPanel;

	private final PlayerEvaluator<A> playerEvaluator;
	private final PlayerWarper<A> playerWarper;
	private final SearchTemplateStorage<A> templateStorage;


	public SearcherPanel(
		Roster<A> roster,
		PlayerEvaluator<A> playerEvaluator,
		PlayerWarper<A> playerWarper,
		SearchTemplateStorage<A> templateStorage,
		Consumer<List<Player<A>>> playersFoundCallback)
	{
		this.playerEvaluator = playerEvaluator;
		this.playerWarper = playerWarper;
		this.templateStorage = templateStorage;

		searchTemplatesComboBox = new PpmComboBox<>(
			templateStorage.getTemplates()
				.stream()
				.map(t -> t.getName())
				.collect(Collectors.toList()),
			-1);
		searchTemplatesComboBox.setEditable(true);
		searchTemplatesComboBox.setPreferredSize(new Dimension(250, 25));
		searchTemplatesComboBox.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				update();
			}
		});

		addButton = new JButton("Add");
		addButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				Object[] possibilities =
				{
					"Age",
					"CL",
					"Side",
					"Rating in Years",
					"Best position (Training)",
				};

				String selection = (String)JOptionPane.showInputDialog(
					SearcherPanel.this,
					"Select criteria",
					"Select criteria",
					JOptionPane.PLAIN_MESSAGE,
					null,
					possibilities,
					"Age");

				if (selection == null)
				{
					return;
				}

				switch (selection)
				{
					case "Age":
						centerPanel.addCriteria(
							new AgeCriteriaPanel<>(playerEvaluator, cp -> remove(cp)));
						break;

					case "CL":
						centerPanel.addCriteria(
							new ClCriteriaPanel<>(playerEvaluator, cp -> remove(cp)));
						break;

					case "Side":
						centerPanel.addCriteria(
							new SideCriteriaPanel<>(playerEvaluator, cp -> remove(cp)));
						break;

					case "Rating in Years":
						centerPanel.addCriteria(
							new RatingInYearsCriteriaPanel<>(
								playerEvaluator,
								playerWarper,
								cp -> remove(cp)));
						break;

					case "Best position (Training)":
						centerPanel.addCriteria(
							new BestPositionTrainingCriteriaPanel<>(
								playerEvaluator,
								cp -> remove(cp)));
						break;
				}
			}
		});

		searchButton = new JButton("Search");
		searchButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				List<Player<A>> players = new Searcher<A>(
					roster,
					centerPanel.getSearchCriterias()).search();

				playersFoundCallback.accept(players);
			}
		});

		saveButton = new JButton("Save");
		saveButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				List<SearchCriteria<A>> searchCriterias = centerPanel.getSearchCriterias();
				SearchTemplate<A> template =
					new SearchTemplate<A>(
						searchTemplatesComboBox.getText(),
						searchCriterias);

				templateStorage.add(template);

				if (!searchTemplatesComboBox.getAllItems()
					.stream()
					.anyMatch(n -> n.equals(template.getName())))
				{
					searchTemplatesComboBox.addItem(template.getName());
				}

				update();
			}
		});

		removeButton = new JButton("Remove");
		removeButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				String templateName = searchTemplatesComboBox.getSelection();

				templateStorage.remove(templateName);

				centerPanel.setCriteria(Arrays.asList());

				if (searchTemplatesComboBox.getAllItems()
					.stream()
					.anyMatch(n -> n.equals(templateName)))
				{
					searchTemplatesComboBox.removeItem(templateName);
				}
			}
		});

		northPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		northPanel.add(addButton);
		northPanel.add(searchTemplatesComboBox);
		northPanel.add(saveButton);
		northPanel.add(removeButton);

		centerPanel = new SearchCriteriaListPanel();

		southPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		southPanel.add(searchButton);

		setPreferredSize(new Dimension(600, 600));
		setLayout(new BorderLayout());

		add(BorderLayout.NORTH, northPanel);
		add(BorderLayout.CENTER, centerPanel);
		add(BorderLayout.SOUTH, southPanel);

		update();
	}

	private void update()
	{
		String templateName = searchTemplatesComboBox.getSelection();

		if (templateName == null)
		{
			return;
		}

		SearchTemplate<A> template = templateStorage.get(templateName);

		List<CriteriaPanel<A>> list = new LinkedList<>();

		for (SearchCriteria<A> criteria : template.getCriterias())
		{
			if (criteria instanceof AgeSearchCriteria)
			{
				list.add(
					new AgeCriteriaPanel<A>(
						playerEvaluator,
						cp -> remove(cp),
						(AgeSearchCriteria<A>)criteria));
				continue;
			}

			if (criteria instanceof ClSearchCriteria)
			{
				list.add(
					new ClCriteriaPanel<A>(
						playerEvaluator,
						cp -> remove(cp),
						(ClSearchCriteria<A>)criteria));
				continue;
			}

			if (criteria instanceof SideSearchCriteria)
			{
				list.add(
					new SideCriteriaPanel<>(
						playerEvaluator,
						cp -> remove(cp),
						(SideSearchCriteria<A>)criteria));
				continue;
			}

			if (criteria instanceof RatingInYearsSearchCriteria)
			{
				list.add(
					new RatingInYearsCriteriaPanel<A>(
						playerEvaluator,
						playerWarper,
						cp -> remove(cp),
						(RatingInYearsSearchCriteria<A>)criteria));
				continue;
			}

			if (criteria instanceof BestPositionSearchCriteria)
			{
				list.add(
					new BestPositionTrainingCriteriaPanel<>(
						playerEvaluator,
						cp -> remove(cp),
						(BestPositionSearchCriteria<A>)criteria));
			}
		}

		centerPanel.setCriteria(list);
	}

	private void remove(CriteriaPanel<A> criteriaPanel)
	{
		centerPanel.removeCritera(criteriaPanel);
	}

	private class SearchCriteriaListPanel
		extends JPanel
	{
		private static final long serialVersionUID = 9022397065845972289L;

		private List<CriteriaPanel<A>> criteriaPanels = new LinkedList<>();

		public SearchCriteriaListPanel()
		{
			setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		}

		public void setCriteria(List<CriteriaPanel<A>> criteriaPanels)
		{
			this.criteriaPanels = criteriaPanels;

			update();
		}

		public void addCriteria(CriteriaPanel<A> criteriaPanel)
		{
			criteriaPanels.add(criteriaPanel);

			update();
		}

		public void removeCritera(CriteriaPanel<A> criteriaPanel)
		{
			criteriaPanels.remove(criteriaPanel);

			update();
		}

		public List<SearchCriteria<A>> getSearchCriterias()
		{
			return criteriaPanels.stream()
				.map(p -> p.getCriteria())
				.collect(Collectors.toList());
		}

		private void update()
		{
			removeAll();

			for (CriteriaPanel<A> criteriaPanel : criteriaPanels)
			{
				add(criteriaPanel);
			}

			revalidate();
			repaint();
		}
	}
}
