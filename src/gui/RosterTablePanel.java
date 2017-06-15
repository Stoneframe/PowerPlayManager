package gui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Stream;

import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;

import model.Player;
import model.PlayerEvaluator;
import model.Roster;

public class RosterTablePanel extends JPanel
{
	private static final long serialVersionUID = 6702252304393306453L;

	private ColumnData[] baseColumnDatas =
	{
			new ColumnData("Name", p -> p.getName()),
			new ColumnData("Side", p -> p.getSide()),
			// new Column("Goa", p -> p.getAttributes().getGoa()),
			// new Column("FiP", p -> p.getAttributes().getFip()),
			// new Column("Sho", p -> p.getAttributes().getSho()),
			// new Column("Blk", p -> p.getAttributes().getBlk()),
			// new Column("Pas", p -> p.getAttributes().getPas()),
			// new Column("Tec", p -> p.getAttributes().getTec()),
			// new Column("Spe", p -> p.getAttributes().getSpe()),
			// new Column("Agr", p -> p.getAttributes().getAgr()),
			new ColumnData("Total", p -> p.getAttributes().getTotal()),
	};

	private ColumnData[] columnDatas;

	private JTable rosterTable;

	public RosterTablePanel()
	{
		rosterTable = new JTable();
		rosterTable.setPreferredSize(new Dimension(1, 500));
		rosterTable.setAutoCreateRowSorter(true);
		rosterTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		rosterTable.getSelectionModel().addListSelectionListener(
			new ListSelectionListener()
			{
				public void valueChanged(ListSelectionEvent e)
				{
				}
			});

		setLayout(new BorderLayout());

		add(rosterTable.getTableHeader(), BorderLayout.PAGE_START);
		add(rosterTable);

		showRoster(new Roster());
	}

	public void showRoster(Roster roster, PlayerEvaluator... evaluators)
	{
		setColumnDatas(evaluators);
		setTableModel(roster);
		setCellRenderers();
	}

	private void setColumnDatas(PlayerEvaluator... evaluators)
	{
		columnDatas = Stream
				.concat(
					Arrays.stream(baseColumnDatas),
					Arrays.stream(convertToColumnDatas(evaluators)))
				.toArray(ColumnData[]::new);
	}

	private void setTableModel(Roster roster)
	{
		rosterTable.setModel(new AbstractTableModel()
		{
			private static final long serialVersionUID = -3862251740620048034L;

			@Override
			public String getColumnName(int column)
			{
				return columnDatas[column].getName();
			}

			@Override
			public Class<?> getColumnClass(int columnIndex)
			{
				return roster.size() > 0
						? getValueAt(0, columnIndex).getClass()
						: Object.class;
			}

			@Override
			public Object getValueAt(int row, int column)
			{
				return columnDatas[column].getValue(roster.get(row));
			}

			@Override
			public int getRowCount()
			{
				return roster.size();
			}

			@Override
			public int getColumnCount()
			{
				return columnDatas.length;
			}
		});
	}

	private void setCellRenderers()
	{
		for (int i = 0; i < rosterTable.getColumnCount(); ++i)
		{
			ColumnData columnData = columnDatas[i];

			rosterTable
					.getColumnModel()
					.getColumn(i)
					.setCellRenderer(new DefaultTableCellRenderer()
					{
						private static final long serialVersionUID =
								-3849162741094455295L;

						@Override
						public Component getTableCellRendererComponent(
								JTable table,
								Object value,
								boolean isSelected,
								boolean hasFocus,
								int row,
								int column)
						{
							value = columnData.formatValue(value);

							return super.getTableCellRendererComponent(
								table,
								value,
								isSelected,
								hasFocus,
								row,
								column);
						}
					});
		}
	}

	private ColumnData[] convertToColumnDatas(PlayerEvaluator... evaluators)
	{
		List<ColumnData> columnDatas = new LinkedList<ColumnData>();

		for (PlayerEvaluator evaluator : evaluators)
		{
			columnDatas.add(
					new ColumnData(
							evaluator.getName(),
							(p) -> evaluator.getRating(p.getAttributes()),
							(v) -> String.format("%.1f", v)
					));

			columnDatas.add(
					new ColumnData(
							"Q." + evaluator.getName(),
							(p) -> evaluator.getQuality(p.getAttributes()),
							(v) -> String.format("%.1f", v)
					));
		}

		if (evaluators.length > 0)
		{
			columnDatas.add(
					new ColumnData(
							"Position",
							(p) -> Arrays
									.stream(evaluators)
									.max(
										(a, b) -> Double.compare(
											a.getRating(p.getAttributes()),
											b.getRating(p.getAttributes())))
									.get()
									.getName()
					));

			columnDatas.add(
					new ColumnData(
							"Training",
							(p) -> Arrays
									.stream(evaluators)
									.max(
										(a, b) -> Double.compare(
											a.getQuality(p.getAttributes()),
											b.getQuality(p.getAttributes())))
									.get()
									.getName()
					));
		}

		return columnDatas.toArray(new ColumnData[0]);
	}

	private class ColumnData
	{
		private String name;
		private Function<Player, Object> getValueFunction;
		private Function<Object, String> formatValueFunction;

		public ColumnData(
				String name,
				Function<Player, Object> getValueAction,
				Function<Object, String> formatValueFunction)
		{
			this.name = name;
			this.getValueFunction = getValueAction;
			this.formatValueFunction = formatValueFunction;
		}

		public ColumnData(String name, Function<Player, Object> getValueAction)
		{
			this.name = name;
			this.getValueFunction = getValueAction;
			this.formatValueFunction = (v) -> v.toString();
		}

		public String getName()
		{
			return name;
		}

		public Object getValue(Player player)
		{
			return getValueFunction.apply(player);
		}

		public String formatValue(Object value)
		{
			return formatValueFunction.apply(value);
		}
	}
}
