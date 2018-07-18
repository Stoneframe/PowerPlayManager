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

	private JMenuItem menuItemOpen;
	private JMenuItem menuItemSave;

	public MenuBar(Component parentComponent, JTabbedPane tabbedPane)
	{
		// 'Open' item
		menuItemOpen = new JMenuItem("Open roster", KeyEvent.VK_O);
		menuItemOpen.setMnemonic(KeyEvent.VK_O);

		// 'Save' item
		menuItemSave = new JMenuItem("Save roster", KeyEvent.VK_S);
		menuItemSave.setMnemonic(KeyEvent.VK_S);

		// Build the menu
		JMenu menu = new JMenu("File");
		menu.setMnemonic(KeyEvent.VK_F);
		menu.add(menuItemOpen);
		menu.add(menuItemSave);

		add(menu);
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
