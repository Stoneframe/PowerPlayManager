package gui.menu;

import java.awt.Component;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTabbedPane;

public class MenuBar
	extends JMenuBar
{
	private static final long serialVersionUID = -5511353684955783810L;

	// private JFileChooser fc = new JFileChooser();

	private JMenuItem menuItemOpen;
	private JMenuItem menuItemSave;

	public MenuBar(Component parentComponent, JTabbedPane tabbedPane)
	{
		// Build the menu
		JMenu menu = new JMenu("File");
		menu.setMnemonic(KeyEvent.VK_F);
		add(menu);

		// 'Open' item
		menuItemOpen = new JMenuItem("Open roster", KeyEvent.VK_O);
		menuItemOpen.setMnemonic(KeyEvent.VK_O);
		// menuItemOpen.addActionListener(new ActionListener()
		// {
		// public void actionPerformed(ActionEvent e)
		// {
		// int returnVal = fc.showOpenDialog(parentComponent);
		// if (returnVal == JFileChooser.APPROVE_OPTION)
		// {
		// File file = fc.getSelectedFile();
		// MainPanel<?> selectedTab =
		// (MainPanel<?>)tabbedPane.getSelectedComponent();
		// Roster<?> roster = selectedTab.getRoster();
		// FileHandler.loadRosterFromFile(file, roster);
		// }
		// }
		// });
		menu.add(menuItemOpen);

		// 'Save' item
		menuItemSave = new JMenuItem("Save roster", KeyEvent.VK_S);
		menuItemSave.setMnemonic(KeyEvent.VK_S);
		// menuItemSave.addActionListener(new ActionListener()
		// {
		// public void actionPerformed(ActionEvent e)
		// {
		// int returnVal = fc.showSaveDialog(parentComponent);
		//
		// if (returnVal == JFileChooser.APPROVE_OPTION)
		// {
		// MainPanel<?> selectedTab =
		// (MainPanel<?>)tabbedPane.getSelectedComponent();
		//
		// int index = tabbedPane.getSelectedIndex();
		//
		// String sport = tabbedPane.getTitleAt(index);
		//
		// System.out.println("Saving " + sport);
		//
		// Roster<?> roster = selectedTab.getRoster();
		//
		// File file = fc.getSelectedFile();
		//
		// FileHandler.saveRosterToFile(file, roster, sport);
		// }
		// }
		// });
		menu.add(menuItemSave);
	}

	public void addOpenActionListener(ActionListener listener)
	{
		menuItemOpen.addActionListener(listener);
	}

	public void addSaveActionListener(ActionListener listener)
	{
		menuItemSave.addActionListener(listener);
	}
}
