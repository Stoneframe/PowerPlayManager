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
import model.PlayersParser;
import model.parsers.OverviewPlayersParser;
import model.parsers.ParseException;
import model.parsers.PractiseProPlayersParser;
import model.parsers.PractisePlayersParser;

public class ParsePanel extends JPanel
{
	private static final long serialVersionUID = -4697990138081430891L;

	private PlayersParsedListener playersParsedListener;

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
				parsePlayers(new PractisePlayersParser());
			}
		});

		parseProPractiseButton = new JButton("Practise (Pro) Parse");
		parseProPractiseButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				parsePlayers(new PractiseProPlayersParser());
			}
		});

		parseOverviewButton = new JButton("Overview Parse");
		parseOverviewButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				parsePlayers(new OverviewPlayersParser());
			}
		});

		setLayout(new FlowLayout());

		add(new JScrollPane(textArea));
		add(parsePractiseButton);
		add(parseProPractiseButton);
		add(parseOverviewButton);
	}

	public void setPlayersParseListener(PlayersParsedListener listener)
	{
		playersParsedListener = listener;
	}

	private void parsePlayers(PlayersParser playersParser)
	{
		try
		{
			List<Player> players = playersParser
					.parseRoster(textArea.getText());

			if (playersParsedListener != null)
			{
				playersParsedListener.playersParsed(
					this,
					new PlayersParsedEvent(this, players));
			}
		}
		catch (ParseException ex)
		{
			System.out.println("Unable to parse input");
		}
	}
}
