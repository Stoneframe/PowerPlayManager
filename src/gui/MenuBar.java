package gui;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import model.Roster;

public class MenuBar extends JMenuBar
{
	private static final long serialVersionUID = -5511353684955783810L;

	final JFileChooser fc = new JFileChooser();

	public MenuBar(Component parentComponent, Roster<?> roster)
	{
		JMenu menu;
		JMenuItem menuItemOpen, menuItemSave;

		// Build the menu
		menu = new JMenu("File");
		menu.setMnemonic(KeyEvent.VK_F);
		add(menu);

		// 'Open' item
		menuItemOpen = new JMenuItem("Open roster", KeyEvent.VK_O);
		menuItemOpen.setMnemonic(KeyEvent.VK_O);
		menuItemOpen.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				int returnVal = fc.showOpenDialog(parentComponent);
				if (returnVal == JFileChooser.APPROVE_OPTION)
				{
					File file = fc.getSelectedFile();
					// Roster roster =
					FileHandler.loadRosterFromFile(file);
				}
			}
		});
		menu.add(menuItemOpen);

		// 'Save' item
		menuItemSave = new JMenuItem("Save roster", KeyEvent.VK_S);
		menuItemSave.setMnemonic(KeyEvent.VK_S);
		menuItemSave.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				int returnVal = fc.showSaveDialog(parentComponent);
				if (returnVal == JFileChooser.APPROVE_OPTION)
				{
					File file = fc.getSelectedFile();
					FileHandler.saveRosterToFile(file, roster);
				}
			}
		});
		menu.add(menuItemSave);
	}
}
