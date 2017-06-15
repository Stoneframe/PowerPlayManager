package gui;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import model.Roster;
import model.parsers.OverviewRosterParser;
import model.parsers.ParseException;
import model.parsers.PractiseProRosterParser;
import model.parsers.PractiseRosterParser;

public class ParsePanel extends JPanel
{
	private static final long serialVersionUID = -4697990138081430891L;

	private RosterParsedListener parsePractiseListener;
	private RosterParsedListener parseProPractiseListener;
	private RosterParsedListener parseOverviewListerner;

	private JTextArea textArea;
	private JButton parsePractiseButton;
	private JButton parseProPractiseButton;
	private JButton parseOverviewButton;

	public ParsePanel()
	{
		textArea = new JTextArea(2, 25);

		parsePractiseButton = new JButton("Practise Parse");
		parsePractiseButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				try
				{
					Roster roster = new PractiseRosterParser()
							.parseRoster(textArea.getText());

					if (parsePractiseListener != null)
					{
						parsePractiseListener.rosterParsed(
							this,
							new RosterParsedEvent(this, roster));
					}
				}
				catch (ParseException ex)
				{
					System.out.println("Unable to parse input");
				}
			}
		});

		parseProPractiseButton = new JButton("Practise (Pro) Parse");
		parseProPractiseButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				try
				{
					Roster roster = new PractiseProRosterParser()
							.parseRoster(textArea.getText());

					if (parseProPractiseListener != null)
					{
						parseProPractiseListener.rosterParsed(
							this,
							new RosterParsedEvent(this, roster));
					}
				}
				catch (ParseException ex)
				{
					System.out.println("Unable to parse input");
				}
			}
		});

		parseOverviewButton = new JButton("Overview Parse");
		parseOverviewButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				try
				{
					Roster roster = new OverviewRosterParser()
							.parseRoster(textArea.getText());

					if (parseOverviewListerner != null)
					{
						parseOverviewListerner.rosterParsed(
							this,
							new RosterParsedEvent(this, roster));
					}
				}
				catch (ParseException ex)
				{
					System.out.println("Unable to parse input");
				}
			}
		});

		setLayout(new FlowLayout());

		add(new JScrollPane(textArea));
		add(parsePractiseButton);
		add(parseProPractiseButton);
		add(parseOverviewButton);
	}

	public void setParsePractiseListener(RosterParsedListener listener)
	{
		parsePractiseListener = listener;
	}

	public void setParseProPractiseListener(RosterParsedListener listener)
	{
		parseProPractiseListener = listener;
	}

	public void setParseOverviewListener(RosterParsedListener listener)
	{
		parseOverviewListerner = listener;
	}
}
