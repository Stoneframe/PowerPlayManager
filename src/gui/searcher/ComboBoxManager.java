package gui.searcher;

import gui.util.PpmComboBox;

public class ComboBoxManager<T>
{
	private final PpmComboBox<T> comboBox;

	public ComboBoxManager(PpmComboBox<T> comboBox)
	{
		this.comboBox = comboBox;
	}

	public T get()
	{
		return comboBox.getSelection();
	}

	public void set(T value)
	{
		comboBox.setSelectedItem(value);
	}
}
