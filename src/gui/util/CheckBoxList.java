package gui.util;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Function;

import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class CheckBoxList<T>
	extends JPanel
{
	private static final long serialVersionUID = 3607844450834825260L;

	private List<ListSelectionListener> listeners = new LinkedList<>();

	private GridBagConstraints gbc = new GridBagConstraints();

	private List<T> list = new LinkedList<>();

	private Function<T, String> getText;

	public CheckBoxList(Function<T, String> getText)
	{
		this.getText = getText;

		setLayout(new GridBagLayout());
		setBackground(Color.WHITE);

		gbc.anchor = GridBagConstraints.WEST;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.weighty = 1;
		gbc.gridy = 0;
	}

	public void addItem(T item)
	{
		addItem(item, false);
	}

	public void addItem(T item, boolean isChecked)
	{
		list.add(item);

		JCheckBox checkBox = new JCheckBox(getText.apply(item));
		checkBox.setSelected(isChecked);
		checkBox.setBackground(Color.WHITE);
		checkBox.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				listeners.forEach(
					l -> l.valueChanged(
						new ListSelectionEvent(
								this,
								list.indexOf(item),
								list.indexOf(item),
								false)));
			}
		});

		add(checkBox, gbc);

		gbc.gridy++;
	}

	public void addListSelectionListener(ListSelectionListener listener)
	{
		listeners.add(listener);
	}

	public T get(int index)
	{
		return list.get(index);
	}
}
