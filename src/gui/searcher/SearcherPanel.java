package gui.searcher;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import evaluators.PlayerEvaluator;
import gui.searcher.criterias.AgeSearchCriteriaPanel;
import gui.searcher.criterias.BestPositionSearchCriteriaPanel;
import gui.searcher.criterias.BestPositionTrainingSearchCriteriaPanel;
import gui.searcher.criterias.ClSearchCriteriaPanel;
import gui.searcher.criterias.CountrySearchCriteriaPanel;
import gui.searcher.criterias.EffectiveRatingAtPositionInYearsSearchCriteriaPanel;
import gui.searcher.criterias.EffectiveRatingInYearsSearchCriteriaPanel;
import gui.searcher.criterias.MinimumEffectiveRatingSearchCriteriaPanel;
import gui.searcher.criterias.SideSearchCriteriaPanel;
import gui.util.PpmComboBox;
import gui.util.VerticalFlowLayout;
import model.Attributes;
import model.Player;
import model.Roster;
import searcher.SearchCriteria;
import searcher.SearchTemplate;
import searcher.SearchTemplateStorage;
import searcher.Searcher;
import searcher.criterias.AgeSearchCriteria;
import searcher.criterias.BestPositionRatingSearchCriteria;
import searcher.criterias.BestPositionTrainingSearchCriteria;
import searcher.criterias.ClSearchCriteria;
import searcher.criterias.CountrySearchCriteria;
import searcher.criterias.EffectiveRatingAtPositionInYearsSearchCriteria;
import searcher.criterias.EffectiveRatingInYearsSearchCriteria;
import searcher.criterias.MinimumEffectiveRatingSearchCriteria;
import searcher.criterias.SideSearchCriteria;
import warper.PlayerWarper;

public class SearcherPanel<A extends Attributes>
	extends JPanel
{
	private static final long serialVersionUID = 7948622117141587618L;

	private final PpmComboBox<String> searchTemplatesComboBox;

	private final JButton addButton;
	private final JButton searchButton;
	private final JButton clearButton;

	private final JButton saveButton;
	private final JButton removeButton;

	private final JPanel northPanel;
	private final JPanel southPanel;

	private final SearchCriteriaListPanel centerPanel;

	private final Map<String, Supplier<SearchCriteriaPanel<A>>> searchCriteriaPanelSuppliers;

	public SearcherPanel(
		Roster<A> roster,
		PlayerEvaluator<A> playerEvaluator,
		PlayerWarper<A> playerWarper,
		SearchTemplateStorage<A> templateStorage,
		BiConsumer<String, List<Player<A>>> playersFoundCallback)
	{
		List<String> templateNames = templateStorage.getTemplates()
			.stream()
			.map(t -> t.getName())
			.sorted()
			.collect(Collectors.toList());

		searchTemplatesComboBox = new PpmComboBox<>(templateNames, -1);
		searchTemplatesComboBox.setEditable(true);
		searchTemplatesComboBox.setPreferredSize(new Dimension(250, 25));
		searchTemplatesComboBox.addActionListener(l -> onTemplateSelected(templateStorage));

		addButton = new JButton("Add");
		addButton.addActionListener(l -> onAddSearchCriteria());

		searchButton = new JButton("Search");
		searchButton.addActionListener(l -> onSearchPlayers(roster, playersFoundCallback));

		clearButton = new JButton("Clear");
		clearButton.addActionListener(l -> onClear());

		saveButton = new JButton("Save");
		saveButton.addActionListener(l -> onSaveTemplate(templateStorage));

		removeButton = new JButton("Remove");
		removeButton.addActionListener(l -> onRemoveTemplate(templateStorage));

		northPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		northPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
		northPanel.add(addButton);
		northPanel.add(searchTemplatesComboBox);
		northPanel.add(saveButton);
		northPanel.add(removeButton);
		northPanel.add(clearButton);

		centerPanel = new SearchCriteriaListPanel();

		searchCriteriaPanelSuppliers = new HashMap<>();

		searchCriteriaPanelSuppliers.put(
			AgeSearchCriteria.NAME,
			() -> new AgeSearchCriteriaPanel<>(
				playerEvaluator,
				p -> onRemoveSearchCriteria(p)));

		searchCriteriaPanelSuppliers.put(
			ClSearchCriteria.NAME,
			() -> new ClSearchCriteriaPanel<>(
				playerEvaluator,
				p -> onRemoveSearchCriteria(p)));

		searchCriteriaPanelSuppliers.put(
			SideSearchCriteria.NAME,
			() -> new SideSearchCriteriaPanel<>(
				playerEvaluator,
				p -> onRemoveSearchCriteria(p)));

		searchCriteriaPanelSuppliers.put(
			EffectiveRatingAtPositionInYearsSearchCriteria.NAME,
			() -> new EffectiveRatingAtPositionInYearsSearchCriteriaPanel<A>(
				playerEvaluator,
				playerWarper,
				p -> onRemoveSearchCriteria(p)));

		searchCriteriaPanelSuppliers.put(
			EffectiveRatingInYearsSearchCriteria.NAME,
			() -> new EffectiveRatingInYearsSearchCriteriaPanel<A>(
				playerEvaluator,
				playerWarper,
				p -> onRemoveSearchCriteria(p)));

		searchCriteriaPanelSuppliers.put(
			BestPositionRatingSearchCriteria.NAME,
			() -> new BestPositionSearchCriteriaPanel<>(
				playerEvaluator,
				p -> onRemoveSearchCriteria(p)));

		searchCriteriaPanelSuppliers.put(
			BestPositionTrainingSearchCriteria.NAME,
			() -> new BestPositionTrainingSearchCriteriaPanel<>(
				playerEvaluator,
				p -> onRemoveSearchCriteria(p)));

		searchCriteriaPanelSuppliers.put(
			CountrySearchCriteria.NAME,
			() -> new CountrySearchCriteriaPanel<>(
				playerEvaluator,
				p -> onRemoveSearchCriteria(p)));

		searchCriteriaPanelSuppliers.put(
			MinimumEffectiveRatingSearchCriteria.NAME,
			() -> new MinimumEffectiveRatingSearchCriteriaPanel<>(
				playerEvaluator,
				playerWarper,
				p -> onRemoveSearchCriteria(p)));

		southPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		southPanel.add(searchButton);

		setPreferredSize(new Dimension(600, 600));
		setLayout(new BorderLayout());
		setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		add(BorderLayout.NORTH, northPanel);
		add(BorderLayout.CENTER, centerPanel);
		add(BorderLayout.SOUTH, southPanel);

		updateButtonsEnabled();
	}

	private void onTemplateSelected(SearchTemplateStorage<A> templateStorage)
	{
		String templateName = searchTemplatesComboBox.getSelection();

		if (templateName == null)
		{
			return;
		}

		if (!templateStorage.contains(templateName))
		{
			return;
		}

		centerPanel.clearCriteria();

		SearchTemplate<A> template = templateStorage.get(templateName);

		for (SearchCriteria<A> criteria : template.getCriterias())
		{
			SearchCriteriaPanel<A> criteriaPanel = getCriteriaPanel(criteria.getName());

			criteriaPanel.update(criteria);

			centerPanel.addCriteria(criteriaPanel);
		}

		updateButtonsEnabled();
	}

	private void onSaveTemplate(SearchTemplateStorage<A> templateStorage)
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

		updateButtonsEnabled();
	}

	private void onRemoveTemplate(SearchTemplateStorage<A> templateStorage)
	{
		String templateName = searchTemplatesComboBox.getSelection();

		templateStorage.remove(templateName);

		centerPanel.clearCriteria();

		if (searchTemplatesComboBox.getAllItems()
			.stream()
			.anyMatch(n -> n.equals(templateName)))
		{
			searchTemplatesComboBox.removeItem(templateName);
		}

		updateButtonsEnabled();
	}

	private void onClear()
	{
		searchTemplatesComboBox.setSelectedIndex(-1);
		centerPanel.clearCriteria();
		updateButtonsEnabled();
	}

	private void onAddSearchCriteria()
	{
		Object[] possibilities = searchCriteriaPanelSuppliers.keySet().toArray();

		String selection = (String)JOptionPane.showInputDialog(
			SearcherPanel.this,
			"Select criteria",
			"Select criteria",
			JOptionPane.PLAIN_MESSAGE,
			null,
			possibilities,
			possibilities[0]);

		if (selection == null)
		{
			return;
		}

		centerPanel.addCriteria(getCriteriaPanel(selection));

		updateButtonsEnabled();
	}

	private void onRemoveSearchCriteria(SearchCriteriaPanel<A> criteriaPanel)
	{
		centerPanel.removeCritera(criteriaPanel);

		updateButtonsEnabled();
	}

	private void onSearchPlayers(
		Roster<A> roster,
		BiConsumer<String, List<Player<A>>> playersFoundCallback)
	{
		List<Player<A>> players = new Searcher<A>(
			roster,
			centerPanel.getSearchCriterias()).search();

		String selectedTemplate = searchTemplatesComboBox.getSelection();

		String groupName = "Search - " + selectedTemplate;

		playersFoundCallback.accept(groupName, players);

		updateButtonsEnabled();
	}

	private SearchCriteriaPanel<A> getCriteriaPanel(String criteriaName)
	{
		return searchCriteriaPanelSuppliers.get(criteriaName).get();
	}

	private void updateButtonsEnabled()
	{
		searchButton.setEnabled(!centerPanel.getSearchCriterias().isEmpty());
	}

	private class SearchCriteriaListPanel
		extends JPanel
	{
		private static final long serialVersionUID = 9022397065845972289L;

		private List<SearchCriteriaPanel<A>> criteriaPanels = new LinkedList<>();

		public SearchCriteriaListPanel()
		{
			setLayout(new VerticalFlowLayout(10));
		}

		public void addCriteria(SearchCriteriaPanel<A> criteriaPanel)
		{
			criteriaPanels.add(criteriaPanel);

			update();
		}

		public void removeCritera(SearchCriteriaPanel<A> criteriaPanel)
		{
			criteriaPanels.remove(criteriaPanel);

			update();
		}

		public void clearCriteria()
		{
			criteriaPanels.clear();

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

			for (SearchCriteriaPanel<A> criteriaPanel : criteriaPanels)
			{
				add(criteriaPanel);
			}

			revalidate();
			repaint();
		}
	}
}
