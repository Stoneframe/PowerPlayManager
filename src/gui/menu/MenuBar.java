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

	private JMenuItem menuItemImport;
	private JMenuItem menuItemExport;

	public MenuBar(Component parentComponent, JTabbedPane tabbedPane)
	{
		// 'Import' item
		menuItemImport = new JMenuItem("Import players...", KeyEvent.VK_O);
		menuItemImport.setMnemonic(KeyEvent.VK_I);

		// 'Export' item
		menuItemExport = new JMenuItem("Export players...", KeyEvent.VK_S);
		menuItemExport.setMnemonic(KeyEvent.VK_E);

		// Build the menu
		JMenu menu = new JMenu("File");
		menu.setMnemonic(KeyEvent.VK_F);
		menu.add(menuItemImport);
		menu.add(menuItemExport);

		add(menu);
	}

	public void addImportActionListener(ActionListener listener)
	{
		menuItemImport.addActionListener(listener);
	}

	public void addExportActionListener(ActionListener listener)
	{
		menuItemExport.addActionListener(listener);
	}
}
