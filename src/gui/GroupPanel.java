package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import model.Attributes;
import model.Groups;

public class GroupPanel<A extends Attributes>
	extends JPanel
{
	private static final long serialVersionUID = 2369854244820039552L;

	private static final Function<Integer, Integer> UP = index -> index - 1;
	private static final Function<Integer, Integer> DOWN = index -> index + 1;

	private DefaultListModel<Groups<A>.Group> groupListModel;

	private JList<Groups<A>.Group> groupList;

	private JButton addButton;
	private JButton removeButton;

	private JButton moveUpButton;
	private JButton moveDownButton;

	public GroupPanel()
	{
		groupListModel = new DefaultListModel<>();

		groupList = new JList<>(groupListModel);
		groupList.setPreferredSize(new Dimension(250, 350));
		groupList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		groupList.setBorder(BorderFactory.createEtchedBorder());
		groupList.addListSelectionListener(new ListSelectionListener()
		{
			public void valueChanged(ListSelectionEvent e)
			{
				moveUpButton.setEnabled(
					groupList.getSelectedIndices().length == 1
						&& groupList.getSelectedIndex() != 0);

				moveDownButton.setEnabled(
					groupList.getSelectedIndices().length == 1
						&& groupList.getSelectedIndex() != groupListModel.size() - 1);
			}
		});
		groupList.addMouseListener(new MouseAdapter()
		{
			public void mouseClicked(MouseEvent evt)
			{
				if (evt.getClickCount() == 2)
				{
					Groups<A>.Group group = groupList.getSelectedValue();

					if (group == null) return;

					String newName = JOptionPane
						.showInputDialog(GroupPanel.this, "New Name:", group.getName());

					if (newName != null)
					{
						group.setName(newName);

						groupList.updateUI();
					}
				}
			}
		});

		addButton = new JButton("Add...");
		addButton.setEnabled(false);

		removeButton = new JButton("Remove");
		removeButton.setEnabled(false);

		moveUpButton = new JButton("\u2191");
		moveUpButton.setEnabled(false);
		moveUpButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				moveSelectedGroup(UP);
			}
		});

		moveDownButton = new JButton("\u2193");
		moveDownButton.setEnabled(false);
		moveDownButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				moveSelectedGroup(DOWN);
			}
		});

		setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		setLayout(new BorderLayout());

		JPanel buttonsUpDownPanel = new JPanel();
		buttonsUpDownPanel.setLayout(new BoxLayout(buttonsUpDownPanel, BoxLayout.Y_AXIS));
		buttonsUpDownPanel.add(moveUpButton);
		buttonsUpDownPanel.add(moveDownButton);

		JPanel buttonAddRemovePanel = new JPanel();
		buttonAddRemovePanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		buttonAddRemovePanel.add(addButton);
		buttonAddRemovePanel.add(removeButton);

		add(groupList, BorderLayout.CENTER);
		add(buttonsUpDownPanel, BorderLayout.EAST);
		add(buttonAddRemovePanel, BorderLayout.SOUTH);
	}

	public void addGroup(Groups<A>.Group group)
	{
		groupListModel.addElement(group);

		if (group.isEnabled())
		{
			addGroupToSelectedGroups(group);
		}
	}

	public void removeGroup(Groups<A>.Group group)
	{
		groupListModel.removeElement(group);
	}

	public List<Groups<A>.Group> getGroups()
	{
		return Collections.list(groupListModel.elements());
	}

	public List<Groups<A>.Group> getSelectedGroups()
	{
		return groupList.getSelectedValuesList();
	}

	public void addGroupListSelectionListener(ListSelectionListener listener)
	{
		groupList.addListSelectionListener(listener);
	}

	public void addAddButtonActionListener(ActionListener listener)
	{
		addButton.addActionListener(listener);
	}

	public void addRemoveButtonActionListener(ActionListener listener)
	{
		removeButton.addActionListener(listener);
	}

	public void setAddButtonEnabled(boolean isEnabled)
	{
		addButton.setEnabled(isEnabled);
	}

	public void setRemoveButtonEnabled(boolean isEnabled)
	{
		removeButton.setEnabled(isEnabled);
	}

	private void moveSelectedGroup(Function<Integer, Integer> direction)
	{
		int index = groupList.getSelectedIndex();

		Groups<A>.Group group = groupListModel.remove(index);
		groupListModel.add(direction.apply(index), group);

		addGroupToSelectedGroups(group);
	}

	private void addGroupToSelectedGroups(Groups<A>.Group group)
	{
		final int N = groupList.getSelectedIndices().length;

		int[] indices = Arrays.copyOf(groupList.getSelectedIndices(), N + 1);

		indices[N] = groupListModel.indexOf(group);

		groupList.setSelectedIndices(indices);
	}
}
