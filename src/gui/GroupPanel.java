package gui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.util.Collections;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
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
		groupList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		groupList.setBorder(BorderFactory.createEtchedBorder());
		groupList.setCellRenderer(new DefaultListCellRenderer()
		{
			private static final long serialVersionUID = -7350967499676451807L;

			@Override
			public Component getListCellRendererComponent(
					JList<?> list,
					Object value,
					int index,
					boolean isSelected,
					boolean cellHasFocus)
			{
				Roster<?>.Group group = (Roster<?>.Group)value;

				return super.getListCellRendererComponent(
					list,
					value,
					index,
					group.isEnabled(),
					cellHasFocus);
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
