package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Function;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.CompoundBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import comparators.EvaluatorComparator;
import comparators.QualityEvaluatorComparator;
import comparators.RatingEvaluatorComparator;
import evaluators.PlayerEvaluator;
import gui.util.Colors;
import model.Attributes;
import model.Player;
import model.Roster;
import util.CollectionChangedEvent;
import util.CollectionChangedListeners;
import util.PropertyChangedEvent;
import util.PropertyChangedListener;

public class RosterPanel<A extends Attributes> extends JPanel
{
	private static final long serialVersionUID = 6702252304393306453L;

	private PlayerSelectedListener<A> playerSelectedListener;

	private List<PlayerEvaluator<A>> evaluators =
			new LinkedList<PlayerEvaluator<A>>();

	private List<ColumnData> columnDatas = Arrays.asList(
		new ColumnData("Name", p -> p.getName()),
		new ColumnData("Side", p -> p.getSide()),
		new ColumnData("Total", p -> p.getAttributes().getTotalRating()),
		new ColumnData(
				"Position",
				(p) -> getPlayersBestPositionName(
					new RatingEvaluatorComparator<A>(p.getAttributes()), evaluators)),
		new ColumnData(
				"Highest Rating",
				(p) -> getPlayersBestPositionRating(evaluators, p),
				(v) -> String.format("%.1f", v)),
		new ColumnData(
				"Training",
				(p) -> getPlayersBestPositionName(
					new QualityEvaluatorComparator<A>(p.getAttributes()), evaluators)),
		new ColumnData(
				"Highest Quality",
				(p) -> getPlayersBestPositionQuality(evaluators, p),
				(v) -> String.format("%.1f", v)));

	private JLabel highQualityLimitLabel;
	private JLabel lowQualityLimitLabel;

	private JTextField highQualityLimitTextField;
	private JTextField lowQualityLimitTextField;

	private JButton applyButton;

	private JTable rosterTable;

	private JPanel controllerPanel;
	private JPanel tablePanel;

	private RosterTableColumnModel rosterTableColumnModel;
	private RosterTableModel rosterTableModel;

	private Roster<A> roster;

	public RosterPanel()
	{
		roster = new Roster<A>();

		rosterTableColumnModel = new RosterTableColumnModel();
		rosterTableModel = new RosterTableModel();

		rosterTable = new JTable();
		rosterTable.setAutoCreateRowSorter(true);
		rosterTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		rosterTable.getSelectionModel().addListSelectionListener(
			new ListSelectionListener()
			{
				public void valueChanged(ListSelectionEvent e)
				{
					if (e.getValueIsAdjusting()) return;

					if (playerSelectedListener != null)
					{
						playerSelectedListener.playerSelected(
							this,
							new PlayerSelectedEvent<A>(this, getSelectedPlayer()));
					}
				}
			});
		rosterTable.setColumnModel(rosterTableColumnModel);
		rosterTable.setModel(rosterTableModel);

		tablePanel = new JPanel(new BorderLayout());
		tablePanel.add(rosterTable.getTableHeader(), BorderLayout.PAGE_START);
		tablePanel.add(new JScrollPane(rosterTable), BorderLayout.CENTER);

		highQualityLimitLabel = new JLabel("High Quality:");
		lowQualityLimitLabel = new JLabel("Low Quality:");

		highQualityLimitTextField = new JTextField(5);
		lowQualityLimitTextField = new JTextField(5);

		applyButton = new JButton("Apply");
		applyButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				rosterTableModel.fireAllTableCellsUpdated();
			}
		});

		controllerPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		controllerPanel.add(highQualityLimitLabel);
		controllerPanel.add(highQualityLimitTextField);
		controllerPanel.add(lowQualityLimitLabel);
		controllerPanel.add(lowQualityLimitTextField);
		controllerPanel.add(applyButton);

		setBorder(new CompoundBorder(
				BorderFactory.createTitledBorder("Roster"),
				BorderFactory.createEmptyBorder(5, 5, 5, 5)));

		setLayout(new BorderLayout());

		add(tablePanel, BorderLayout.CENTER);
		add(controllerPanel, BorderLayout.SOUTH);
	}

	public void bind(Roster<A> roster)
	{
		if (this.roster != null)
		{
			for (Player<A> player : this.roster)
			{
				player.removePropertyChangedListener(rosterTableModel);
			}

			this.roster.removeCollectionChangedListener(rosterTableModel);
		}

		this.roster = roster;

		if (this.roster != null)
		{
			for (Player<A> player : this.roster)
			{
				player.addPropertyChangedListener(rosterTableModel);
			}

			this.roster.addCollectionChangedListener(rosterTableModel);
		}

		rosterTableModel.fireTableDataChanged();
	}

	public void setPlayerSelectedListener(PlayerSelectedListener<A> listener)
	{
		this.playerSelectedListener = listener;
	}

	public void setPlayerEvaluators(List<PlayerEvaluator<A>> evaluators)
	{
		this.evaluators = evaluators;

		rosterTableModel.fireAllTableCellsUpdated();
	}

	private Player<A> getSelectedPlayer()
	{
		int selectedRow = rosterTable.getSelectedRow();

		return selectedRow >= 0
				? roster.get(rosterTable.convertRowIndexToModel(selectedRow))
				: null;
	}

	private int getHighQualityLimit()
	{
		return parseInteger(highQualityLimitTextField.getText(), 100);
	}

	private int getLowQualityLimit()
	{
		return parseInteger(lowQualityLimitTextField.getText(), 0);
	}

	private static int parseInteger(String text, int dfault)
	{
		try
		{
			return Integer.parseInt(text);
		}
		catch (NumberFormatException e)
		{
			return dfault;
		}
	}

	private String getPlayersBestPositionName(
			EvaluatorComparator<A> evaluatorComparator,
			List<PlayerEvaluator<A>> evaluators)
	{
		PlayerEvaluator<A> playerEvaluator = evaluators
				.stream()
				.max((a, b) -> evaluatorComparator.compare(a, b))
				.get();

		return playerEvaluator != null
				? playerEvaluator.getName()
				: "No suggestion";
	}

	private Double getPlayersBestPositionRating(
			List<PlayerEvaluator<A>> evaluators,
			Player<A> player)
	{
		return evaluators
				.stream()
				.max((a, b) -> new RatingEvaluatorComparator<A>(
						player.getAttributes()).compare(a, b))
				.map(e -> e.getRating(player.getAttributes()))
				.get();
	}

	private Double getPlayersBestPositionQuality(
			List<PlayerEvaluator<A>> evaluators,
			Player<A> player)
	{
		return evaluators
				.stream()
				.max((a, b) -> new QualityEvaluatorComparator<A>(
						player.getAttributes()).compare(a, b))
				.map(e -> e.getQuality(player.getAttributes()))
				.get();
	}

	private class ColumnData
	{
		private String name;
		private Function<Player<A>, Object> getValueFunction;
		private Function<Object, String> formatValueFunction;

		public ColumnData(
				String name,
				Function<Player<A>, Object> getValueAction,
				Function<Object, String> formatValueFunction)
		{
			this.name = name;
			this.getValueFunction = getValueAction;
			this.formatValueFunction = formatValueFunction;
		}

		public ColumnData(String name, Function<Player<A>, Object> getValueAction)
		{
			this.name = name;
			this.getValueFunction = getValueAction;
			this.formatValueFunction = (v) -> v.toString();
		}

		public String getName()
		{
			return name;
		}

		public Object getValue(Player<A> player)
		{
			return getValueFunction.apply(player);
		}

		public String formatValue(Object value)
		{
			return formatValueFunction.apply(value);
		}
	}

	private class RosterTableModel extends DefaultTableModel
			implements
			CollectionChangedListeners,
			PropertyChangedListener
	{
		private static final long serialVersionUID = -3862251740620048034L;

		@Override
		public String getColumnName(int column)
		{
			return columnDatas.get(column).getName();
		}

		@Override
		public Class<?> getColumnClass(int columnIndex)
		{
			return roster != null && roster.size() > 0
					? getValueAt(0, columnIndex).getClass()
					: Object.class;
		}

		@Override
		public Object getValueAt(int row, int column)
		{
			return columnDatas.get(column).getValue(roster.get(row));
		}

		@Override
		public int getRowCount()
		{
			return roster != null ? roster.size() : 0;
		}

		@Override
		public int getColumnCount()
		{
			return columnDatas.size();
		}

		@Override
		public void collectionChanged(
				Object source,
				CollectionChangedEvent event)
		{
			Player<?> player = (Player<?>) event.getObjectChanged();

			switch (event.getAction())
			{
				case CollectionChangedEvent.ADDED:
					player.addPropertyChangedListener(this);

					fireTableRowsInserted(event.getIndexChanged(), event.getIndexChanged());

					break;
				case CollectionChangedEvent.REMOVED:
					player.removePropertyChangedListener(this);

					fireTableRowsDeleted(event.getIndexChanged(), event.getIndexChanged());

					break;
			}
		}

		@Override
		public void propertyChanged(Object source, PropertyChangedEvent event)
		{
			Player<?> player = (Player<?>) source;

			fireTableRowsUpdated(roster.indexOf(player), roster.indexOf(player));
		}

		public void fireAllTableCellsUpdated()
		{
			for (int row = 0; row < getRowCount(); row++)
			{
				for (int column = 0; column < getColumnCount(); column++)
				{
					fireTableCellUpdated(row, column);
				}
			}
		}
	}

	private class RosterTableColumnModel extends DefaultTableColumnModel
	{
		private static final long serialVersionUID = -318707452817342668L;

		private final Color SELECTED_HIGH_QUALITY_AND_SYMMETRIC =
				Colors.GREEN;
		private final Color UNSELECTED_HIGH_QUALITY_AND_SYMMETRIC =
				Colors.LIGHT_GREEN;
		private final Color SELECTED_HIGH_QUALITY =
				Colors.BLUE;
		private final Color UNSELECTED_HIGH_QUALITY =
				Colors.LIGHT_BLUE;
		private final Color SELECTED_LOW_QUALITY =
				Colors.RED;
		private final Color UNSELECTED_LOW_QUALITY =
				Colors.LIGHT_RED;

		@Override
		public TableColumn getColumn(int columnIndex)
		{
			TableColumn column = super.getColumn(columnIndex);

			column.setCellRenderer(new DefaultTableCellRenderer()
			{
				private static final long serialVersionUID =
						-871237359467912116L;

				@Override
				public Component getTableCellRendererComponent(
						JTable table,
						Object value,
						boolean isSelected,
						boolean hasFocus,
						int row,
						int column)
				{
					value = columnDatas.get(columnIndex).formatValue(value);

					Component component =
							super.getTableCellRendererComponent(
								table,
								value,
								isSelected,
								hasFocus,
								row,
								column);

					Player<A> player = roster.get(table.convertRowIndexToModel(row));

					Color color = Color.WHITE;

					if (isHighQualityPlayer(player) && isSymmetricPlayer(player))
					{
						color = isSelected
								? SELECTED_HIGH_QUALITY_AND_SYMMETRIC
								: UNSELECTED_HIGH_QUALITY_AND_SYMMETRIC;
					}
					else if (isHighQualityPlayer(player))
					{
						color = isSelected
								? SELECTED_HIGH_QUALITY
								: UNSELECTED_HIGH_QUALITY;
					}
					else if (isLowQualityPlayer(player))
					{
						color = isSelected
								? SELECTED_LOW_QUALITY
								: UNSELECTED_LOW_QUALITY;
					}
					else if (isSelected)
					{
						color = table.getSelectionBackground();
					}

					component.setBackground(color);

					return component;
				}

				private boolean isHighQualityPlayer(Player<A> player)
				{
					Double bestPositionQuality =
							getPlayersBestPositionQuality(
								evaluators,
								player);

					return bestPositionQuality > getHighQualityLimit();
				}

				private boolean isLowQualityPlayer(Player<A> player)
				{
					Double bestPositionQuality =
							getPlayersBestPositionQuality(
								evaluators,
								player);

					return bestPositionQuality < getLowQualityLimit();
				}

				private boolean isSymmetricPlayer(Player<A> player)
				{
					String bestRatingPosition =
							getPlayersBestPositionName(
								new RatingEvaluatorComparator<A>(player.getAttributes()),
								evaluators);

					String bestQualityPosition =
							getPlayersBestPositionName(
								new QualityEvaluatorComparator<A>(player.getAttributes()),
								evaluators);

					return bestRatingPosition.equals(bestQualityPosition);
				}
			});

			return column;
		}
	}
}
