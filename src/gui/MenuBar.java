package gui;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class MenuBar extends JMenuBar
{
	private static final long serialVersionUID = -5511353684955783810L;
	// Create a file chooser
	final JFileChooser fc = new JFileChooser();

	public MenuBar(Component parentComponent)
	{
		// Where the GUI is created:
		JMenu menu;// , submenu;
		JMenuItem menuItemOpen, menuItemSave;
		// JRadioButtonMenuItem rbMenuItem;
		// JCheckBoxMenuItem cbMenuItem;

		// Build the first menu.
		menu = new JMenu("File");
		menu.setMnemonic(KeyEvent.VK_F);
		menu.getAccessibleContext().setAccessibleDescription(
			"The only menu in this program that has menu items");
		add(menu);

		// a group of JMenuItems
		menuItemOpen = new JMenuItem(
				"Open roster",
				KeyEvent.VK_O);
		// menuItem.setAccelerator(KeyStroke.getKeyStroke(
		// KeyEvent.VK_1, ActionEvent.ALT_MASK));
		// menuItem.getAccessibleContext().setAccessibleDescription(
		// "This doesn't really do anything");
		menuItemOpen.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				// In response to a button click:
				int returnVal = fc.showOpenDialog(parentComponent);
				// parsePlayers(new PractiseProPlayersParser());
			}
		});
		menu.add(menuItemOpen);

		menuItemSave = new JMenuItem(
				"Save roster",
				KeyEvent.VK_S);
		// menuItem.setAccelerator(KeyStroke.getKeyStroke(
		// KeyEvent.VK_1, ActionEvent.ALT_MASK));
		// menuItem.getAccessibleContext().setAccessibleDescription(
		// "This doesn't really do anything");
		menu.add(menuItemSave);
	}
}
