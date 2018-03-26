package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionListener;

import model.Attributes;
import model.Roster;

public class GroupPanel<A extends Attributes>
	extends JPanel
{
	private static final long serialVersionUID = 2369854244820039552L;

	private DefaultListModel<Roster<A>.Group> groupListModel;

	private JList<Roster<A>.Group> groupList;

	private JButton addButton;
	private JButton removeButton;

	public GroupPanel()
	{
		groupListModel = new DefaultListModel<>();

		groupList = new JList<>(groupListModel);
		groupList.setPreferredSize(new Dimension(250, 350));
		groupList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		groupList.setBorder(BorderFactory.createEtchedBorder());
		groupList.addMouseListener(new MouseAdapter()
		{
			public void mouseClicked(MouseEvent evt)
			{
				if (evt.getClickCount() == 2)
				{
					Roster<A>.Group group = groupList.getSelectedValue();

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

		setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		setLayout(new BorderLayout());

		add(groupList, BorderLayout.CENTER);

		JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));

		buttonPanel.add(addButton);
		buttonPanel.add(removeButton);

		add(buttonPanel, BorderLayout.SOUTH);
	}

	public void addGroup(Roster<A>.Group group)
	{
		groupListModel.addElement(group);

		if (group.isEnabled())
		{
			final int N = groupList.getSelectedIndices().length;

			int[] indices = Arrays.copyOf(groupList.getSelectedIndices(), N + 1);

			indices[N] = groupListModel.indexOf(group);

			groupList.setSelectedIndices(indices);
		}
	}

	public void removeGroup(Roster<A>.Group group)
	{
		groupListModel.removeElement(group);
	}

	public List<Roster<A>.Group> getGroups()
	{
		return Collections.list(groupListModel.elements());
	}

	public List<Roster<A>.Group> getSelectedGroups()
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
}
