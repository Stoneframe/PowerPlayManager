package gui.searcher;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.function.Consumer;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import evaluators.PlayerEvaluator;
import gui.util.PpmComboBox;
import model.Attributes;
import searcher.SearchCriteria;

public abstract class SearchCriteriaPanel<A extends Attributes>
	extends JPanel
{
	private static final long serialVersionUID = -6105265417703531503L;

	protected final PlayerEvaluator<A> playerEvaluator;

	protected final JLabel nameLabel;

	protected final JPanel centerPanel;

	protected final JButton removeButton;

	protected SearchCriteriaPanel(
		PlayerEvaluator<A> playerEvaluator,
		Consumer<SearchCriteriaPanel<A>> removeCallback)
	{
		this.playerEvaluator = playerEvaluator;

		setLayout(new BorderLayout());
		setBorder(BorderFactory.createLineBorder(Color.BLACK));

		nameLabel = new JLabel(getName());
		nameLabel.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 0));

		centerPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

		removeButton = new JButton("X");
		removeButton.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		removeButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				removeCallback.accept(SearchCriteriaPanel.this);
			}
		});

		add(nameLabel, BorderLayout.NORTH);
		add(centerPanel, BorderLayout.CENTER);
		add(removeButton, BorderLayout.EAST);
	}

	public abstract String getName();

	public abstract SearchCriteria<A> getCriteria();

	public abstract void update(SearchCriteria<A> searchCritera);

	@Override
	public String toString()
	{
		return getName();
	}

	protected TextFieldManager addTextField(String label, int columns)
	{
		addLabel(label);

		JTextField textField = new JTextField(columns);

		centerPanel.add(textField);

		return new TextFieldManager(textField);
	}

	protected NumFieldManager addNumField(String label, int columns, int defaultValue)
	{
		addLabel(label);

		JTextField textField = new JTextField(columns);

		centerPanel.add(textField);

		return new NumFieldManager(textField, defaultValue);
	}

	protected <T> ComboBoxManager<T> addComboBox(String label, List<T> options, int selectedIndex)
	{
		addLabel(label);

		PpmComboBox<T> comboBox = new PpmComboBox<T>(options, selectedIndex);

		centerPanel.add(comboBox);

		return new ComboBoxManager<>(comboBox);
	}

	private void addLabel(String label)
	{
		centerPanel.add(new JLabel(label + ":"));
	}
}
