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

	private final JMenuItem fileMenuImport;
	private final JMenuItem fileMenuExport;

	private final JMenuItem ppmMenuImportTeam;
	private final JMenuItem ppmMenuImportMarketOnTransfer;
	private final JMenuItem ppmMenuImportMarketFreeAgent;

	public MenuBar(Component parentComponent, JTabbedPane tabbedPane)
	{
		// 'Import' item
		fileMenuImport = new JMenuItem("Import players...", KeyEvent.VK_O);
		fileMenuImport.setMnemonic(KeyEvent.VK_I);

		// 'Export' item
		fileMenuExport = new JMenuItem("Export players...", KeyEvent.VK_S);
		fileMenuExport.setMnemonic(KeyEvent.VK_E);

		ppmMenuImportTeam = new JMenuItem("Import team");
		ppmMenuImportMarketOnTransfer = new JMenuItem("Import market (on transfer)");
		ppmMenuImportMarketFreeAgent = new JMenuItem("Import market (free agent)");

		// Build the menu
		JMenu fileMenu = new JMenu("File");
		fileMenu.setMnemonic(KeyEvent.VK_F);
		fileMenu.add(fileMenuImport);
		fileMenu.add(fileMenuExport);

		JMenu ppmMenu = new JMenu("PPM");
		ppmMenu.add(ppmMenuImportTeam);
		ppmMenu.add(ppmMenuImportMarketOnTransfer);
		ppmMenu.add(ppmMenuImportMarketFreeAgent);

		add(fileMenu);
		add(ppmMenu);
	}

	public void addFileImportActionListener(ActionListener listener)
	{
		fileMenuImport.addActionListener(listener);
	}

	public void addFileExportActionListener(ActionListener listener)
	{
		fileMenuExport.addActionListener(listener);
	}

	public void addPpmImportTeamActionListener(ActionListener listener)
	{
		ppmMenuImportTeam.addActionListener(listener);
	}
	
	public void addPpmImportMarketOnTransferActionListener(ActionListener listener)
	{
		ppmMenuImportMarketOnTransfer.addActionListener(listener);
	}

	
	public void addPpmImportMarketFreeAgentActionListener(ActionListener listener)
	{
		ppmMenuImportMarketFreeAgent.addActionListener(listener);
	}
}
