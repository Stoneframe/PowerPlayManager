package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Stream;

import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

import model.Player;
import model.PlayerEvaluator;
import model.Roster;

public class RosterTablePanel extends JPanel
{
	private static final long serialVersionUID = 6702252304393306453L;

	private JTable rosterTable;

	public RosterTablePanel()
	{
		rosterTable = new JTable(new RosterTableModel());
		rosterTable.setPreferredSize(new Dimension(1, 500));
		rosterTable.setAutoCreateRowSorter(true);

		setLayout(new BorderLayout());
		add(rosterTable.getTableHeader(), BorderLayout.PAGE_START);
		add(rosterTable);
	}

	public void showRoster(Roster roster, PlayerEvaluator... evaluators)
	{
		rosterTable.setModel(new RosterTableModel(roster, evaluators));
	}

	private class RosterTableModel extends AbstractTableModel
	{
		private static final long serialVersionUID = -7192938036036855215L;

		private Column[] playerColumns =
		{
				new Column("Name", p -> p.getName()),
				new Column("Side", p -> p.getSide()),
				// new Column("Goa", p -> p.getAttributes().getGoa()),
				// new Column("FiP", p -> p.getAttributes().getFip()),
				// new Column("Sho", p -> p.getAttributes().getSho()),
				// new Column("Blk", p -> p.getAttributes().getBlk()),
				// new Column("Pas", p -> p.getAttributes().getPas()),
				// new Column("Tec", p -> p.getAttributes().getTec()),
				// new Column("Spe", p -> p.getAttributes().getSpe()),
				// new Column("Agr", p -> p.getAttributes().getAgr()),
				new Column("Total", p -> p.getAttributes().getTotal()),
		};

		private Column[] evaluatorColumns = new Column[0];

		private Player[] players = new Player[0];

		public RosterTableModel(Roster roster, PlayerEvaluator... evaluators)
		{
			players = roster.toArray();
			evaluatorColumns = convertToColumns(evaluators);
		}

		public RosterTableModel()
		{
		}

		@Override
		public String getColumnName(int column)
		{
			return columns()[column].getName();
		}

		@Override
		public int getColumnCount()
		{
			return columns().length;
		}

		@Override
		public int getRowCount()
		{
			return players.length;
		}

		@Override
		public Object getValueAt(int row, int col)
		{
			return columns()[col].getValue(players[row]);
		}

		private Column[] columns()
		{
			return Stream
					.concat(
						Arrays.stream(playerColumns),
						Arrays.stream(evaluatorColumns))
					.toArray(Column[]::new);
		}

		private Column[] convertToColumns(PlayerEvaluator... evaluators)
		{
			List<Column> columns = new LinkedList<Column>();

			for (PlayerEvaluator evaluator : evaluators)
			{
				columns.add(
						new Column(
								evaluator.getName(),
								(p) -> String.format(
									"%.1f",
									evaluator.getRating(p.getAttributes()))));

				columns.add(
						new Column(
								"Q." + evaluator.getName(),
								(p) -> String.format(
									"%.1f",
									evaluator.getQuality(p.getAttributes()))));
			}

			return columns.toArray(new Column[0]);
		}

		private class Column
		{
			private String name;
			private Function<Player, Object> getValueFunction;

			public Column(String name, Function<Player, Object> getValueAction)
			{
				this.name = name;
				this.getValueFunction = getValueAction;
			}

			public String getName()
			{
				return name;
			}

			public Object getValue(Player player)
			{
				return getValueFunction.apply(player);
			}
		}
	}
}
