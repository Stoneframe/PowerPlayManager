package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.function.Function;

import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

import model.Player;
import model.PlayerEvaluator;
import model.Roster;
import model.evaluators.BackPlayerEvaluator;
import model.evaluators.GoalkeepingPlayerEvaluator;
import model.evaluators.PivotPlayerEvaluator;
import model.evaluators.WingPlayerEvaluator;

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

	public void showRoster(Roster roster)
	{
		rosterTable.setModel(new RosterTableModel(roster));
	}

	private class RosterTableModel extends AbstractTableModel
	{
		private static final long serialVersionUID = -7192938036036855215L;

		private Column createEvaluatorColumn(
				String name,
				PlayerEvaluator evaluator)
		{
			return new Column(
					name,
					p -> String.format(
						"%.1f(%.1f)",
						evaluator.getRating(p.getAttributes()),
						evaluator.getQuality(p.getAttributes())));
		}

		private Column[] columns =
		{
				new Column("Name", p -> p.getName()),
				new Column("Side", p -> p.getSide()),
				new Column("Goa", p -> p.getAttributes().getGoa()),
				new Column("FiP", p -> p.getAttributes().getFip()),
				new Column("Sho", p -> p.getAttributes().getSho()),
				new Column("Blk", p -> p.getAttributes().getBlk()),
				new Column("Pas", p -> p.getAttributes().getPas()),
				new Column("Tec", p -> p.getAttributes().getTec()),
				new Column("Spe", p -> p.getAttributes().getSpe()),
				new Column("Agr", p -> p.getAttributes().getAgr()),
				createEvaluatorColumn(
					"Goalie",
					new GoalkeepingPlayerEvaluator()),
				createEvaluatorColumn("Back", new BackPlayerEvaluator()),
				createEvaluatorColumn("Pivot", new PivotPlayerEvaluator()),
				createEvaluatorColumn("Wing", new WingPlayerEvaluator()),
		};

		private Player[] players;

		public RosterTableModel(Roster roster)
		{
			players = roster.toArray();
		}

		public RosterTableModel()
		{
			players = new Player[0];
		}

		@Override
		public String getColumnName(int column)
		{
			return columns[column].getName();
		}

		@Override
		public int getColumnCount()
		{
			return columns.length;
		}

		@Override
		public int getRowCount()
		{
			return players.length;
		}

		@Override
		public Object getValueAt(int row, int col)
		{
			return columns[col].getValue(players[row]);
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
