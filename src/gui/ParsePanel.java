package gui;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import model.Player;
import model.parsers.OverviewPlayersParser;
import model.parsers.ParseException;
import model.parsers.PractiseProPlayersParser;
import model.parsers.PractisePlayersParser;

public class ParsePanel extends JPanel
{
	private static final long serialVersionUID = -4697990138081430891L;

	private PlayersParsedListener parsePractiseListener;
	private PlayersParsedListener parseProPractiseListener;
	private PlayersParsedListener parseOverviewListerner;

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
					List<Player> players = new PractisePlayersParser()
							.parseRoster(textArea.getText());

					if (parsePractiseListener != null)
					{
						parsePractiseListener.playersParsed(
							this,
							new PlayersParsedEvent(this, players));
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
					List<Player> players = new PractiseProPlayersParser()
							.parseRoster(textArea.getText());

					if (parseProPractiseListener != null)
					{
						parseProPractiseListener.playersParsed(
							this,
							new PlayersParsedEvent(this, players));
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
					List<Player> players = new OverviewPlayersParser()
							.parseRoster(textArea.getText());

					if (parseOverviewListerner != null)
					{
						parseOverviewListerner.playersParsed(
							this,
							new PlayersParsedEvent(this, players));
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

	public void setParsePractiseListener(PlayersParsedListener listener)
	{
		parsePractiseListener = listener;
	}

	public void setParseProPractiseListener(PlayersParsedListener listener)
	{
		parseProPractiseListener = listener;
	}

	public void setParseOverviewListener(PlayersParsedListener listener)
	{
		parseOverviewListerner = listener;
	}
}
