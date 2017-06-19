package gui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Stream;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.CompoundBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;

import model.CollectionChangedEvent;
import model.CollectionChangedListeners;
import model.Player;
import model.PlayerEvaluator;
import model.PropertyChangedEvent;
import model.PropertyChangedListener;
import model.Roster;
import model.comparators.QualityEvaluatorComparator;
import model.comparators.RatingEvaluatorComparator;

public class RosterTablePanel extends JPanel
{
	private static final long serialVersionUID = 6702252304393306453L;

	private PlayerSelectedListener playerSelectedListener;

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
			new ColumnData("Total", p -> p.getAttributes().getTotalRating()),
	};

	private Roster roster;
	private List<PlayerEvaluator> evaluators = Collections.emptyList();

	private ColumnData[] columnDatas;

	private JTable rosterTable;

	public RosterTablePanel()
	{
		rosterTable = new JTable();
		rosterTable.setAutoCreateRowSorter(true);
		rosterTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		rosterTable.getSelectionModel().addListSelectionListener(
			new ListSelectionListener()
			{
				public void valueChanged(ListSelectionEvent e)
				{
					if (e.getValueIsAdjusting())
						return;

					if (playerSelectedListener != null)
					{
						playerSelectedListener.playerSelected(
							this,
							new PlayerSelectedEvent(this, getSelectedPlayer()));
					}
				}

				private Player getSelectedPlayer()
				{
					int selectedRow = rosterTable.getSelectedRow();

					return selectedRow >= 0
							? roster.get(
								rosterTable.convertRowIndexToModel(selectedRow))
							: null;
				}
			});

		setBorder(new CompoundBorder(
				BorderFactory.createTitledBorder("Roster"),
				BorderFactory.createEmptyBorder(5, 5, 5, 5)));

		setLayout(new BorderLayout());

		add(rosterTable.getTableHeader(), BorderLayout.PAGE_START);
		add(new JScrollPane(rosterTable));
	}

	public void setPlayerSelectedListener(PlayerSelectedListener listener)
	{
		this.playerSelectedListener = listener;
	}

	public void bind(Roster roster)
	{
		this.roster = roster;

		setColumnDatas();
		setTableModel();
		setCellRenderers();
	}

	public void setPlayerEvaluators(List<PlayerEvaluator> evaluators)
	{
		this.evaluators = evaluators;
	}

	private void setColumnDatas()
	{
		columnDatas = Stream
				.concat(
					Arrays.stream(baseColumnDatas),
					Arrays.stream(convertToColumnDatas(evaluators)))
				.toArray(ColumnData[]::new);
	}

	private void setTableModel()
	{
		rosterTable.setModel(new RosterTableModel(roster));
	}

	private void setCellRenderers()
	{
		for (int i = 0; i < rosterTable.getColumnCount(); ++i)
		{
			ColumnData columnData = columnDatas[i];

			rosterTable.getColumnModel().getColumn(i).setCellRenderer(
				new DefaultTableCellRenderer()
				{
					private static final long serialVersionUID = -3849162741094455295L;

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

	private ColumnData[] convertToColumnDatas(List<PlayerEvaluator> evaluators)
	{
		List<ColumnData> columnDatas = new LinkedList<ColumnData>();

		if (evaluators.size() > 0)
		{
			columnDatas
					.add(new ColumnData("Position", (p) -> evaluators
							.stream()
							.max(
								(a, b) -> new RatingEvaluatorComparator(
										p.getAttributes()).compare(a, b))
							.get()
							.getName()));

			columnDatas.add(new ColumnData(
					"Highest Rating",
					(p) -> evaluators
							.stream()
							.max(
								(a, b) -> new RatingEvaluatorComparator(
										p.getAttributes()).compare(a, b))
							.map(e -> e.getRating(p.getAttributes()))
							.get(),
					(v) -> String.format("%.1f", v)));

			columnDatas
					.add(new ColumnData("Training", (p) -> evaluators
							.stream()
							.max(
								(a, b) -> new QualityEvaluatorComparator(
										p.getAttributes()).compare(a, b))
							.get()
							.getName()));

			columnDatas.add(new ColumnData(
					"Highest Quality",
					(p) -> evaluators
							.stream()
							.max(
								(a, b) -> new QualityEvaluatorComparator(
										p.getAttributes()).compare(a, b))
							.map(e -> e.getQuality(p.getAttributes()))
							.get(),
					(v) -> String.format("%.1f", v)));
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

	private class RosterTableModel extends AbstractTableModel
			implements
			CollectionChangedListeners,
			PropertyChangedListener
	{
		private static final long serialVersionUID = -3862251740620048034L;

		private Roster roster;

		public RosterTableModel(Roster roster)
		{
			this.roster = roster;
			this.roster.addCollectionChangedListener(this);
		}

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

		@Override
		public void collectionChanged(
				Object source,
				CollectionChangedEvent event)
		{
			switch (event.getAction())
			{
				case CollectionChangedEvent.ADDED:
					((Player) event.getObjectChanged())
							.addPropertyChangedListener(this);
					fireTableRowsInserted(
						event.getIndexChanged(),
						event.getIndexChanged());
					break;
				case CollectionChangedEvent.REMOVED:
					((Player) event.getObjectChanged())
							.removePropertyChangedListener(this);
					fireTableRowsDeleted(
						event.getIndexChanged(),
						event.getIndexChanged());
					break;
			}
		}

		@Override
		public void propertyChanged(Object source, PropertyChangedEvent event)
		{
			Player player = (Player) source;

			fireTableRowsUpdated(
				roster.indexOf(player),
				roster.indexOf(player));
		}
	}
}
