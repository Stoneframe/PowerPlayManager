package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.RowFilter;
import javax.swing.border.CompoundBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableRowSorter;

import evaluators.AttributeEvaluator;
import evaluators.PlayerEvaluator;
import gui.player.PlayerSelectedEvent;
import gui.player.PlayerSelectedListener;
import model.Attributes;
import model.Player;
import model.Roster;
import util.CollectionChangedEvent;
import util.CollectionChangedListener;
import util.Colors;
import util.PropertyChangedEvent;
import util.PropertyChangedListener;

public class RosterPanel<A extends Attributes>
	extends JPanel
{
	private static final long serialVersionUID = 6702252304393306453L;

	private PlayerSelectedListener<A> playerSelectedListener;

	private PlayerEvaluator<A> playerEvaluator;

	private List<ColumnData> columnDatas = Arrays.asList(
		new ColumnData("Name", p -> p.getName(), 200),
		new ColumnData("Age", p -> Integer.toString(p.getAge())),
		new ColumnData("CL", p -> Integer.toString(p.getCL())),
		new ColumnData("Side", p -> p.getSide()),
		new ColumnData("Total", p -> p.getAttributes().getTotalRating()),
		new ColumnData(
			"Position",
			p -> playerEvaluator.getBestPositionRating(p).getName(),
			100),
		new ColumnData(
			"Rating",
			p -> playerEvaluator.getBestPositionRating(p).getValue(),
			v -> String.format("%.1f", v)),
		new ColumnData(
			"Form",
			p -> playerEvaluator.getBestPositionForm(p).getValue(),
			v -> String.format("%.1f", v)),
		new ColumnData(
			"Training",
			p -> playerEvaluator.getBestPositionQuality(p).getName(),
			100),
		new ColumnData(
			"Quality",
			p -> playerEvaluator.getBestPositionQuality(p).getValue(),
			v -> String.format("%.1f", v)),
		new ColumnData(
			"1 Year",
			p -> playerEvaluator.calculateTotalRatingForAge(p, p.getAge() + 1),
			v -> String.format("%.0f", v)),
		new ColumnData(
			"2 Years",
			p -> playerEvaluator.calculateTotalRatingForAge(p, p.getAge() + 2),
			v -> String.format("%.0f", v)),
		new ColumnData(
			"3 Years",
			p -> playerEvaluator.calculateTotalRatingForAge(p, p.getAge() + 3),
			v -> String.format("%.0f", v)),
		new ColumnData(
			"5 Years",
			p -> playerEvaluator.calculateTotalRatingForAge(p, p.getAge() + 5),
			v -> String.format("%.0f", v)),
		new ColumnData(
			"7 Years",
			p -> playerEvaluator.calculateTotalRatingForAge(p, p.getAge() + 7),
			v -> String.format("%.0f", v)),
		new ColumnData(
			"10 Years",
			p -> playerEvaluator.calculateTotalRatingForAge(p, p.getAge() + 10),
			v -> String.format("%.0f", v)),
		new ColumnData(
			"Age 25",
			p -> playerEvaluator.calculateTotalRatingForAge(p, 25),
			v -> String.format("%.0f", v)),
		new ColumnData(
			"Age 30",
			p -> playerEvaluator.calculateTotalRatingForAge(p, 30),
			v -> String.format("%.0f", v)),
		new ColumnData(
			"Max",
			p -> playerEvaluator.calculateHighestPossibleRating(p),
			v -> String.format("%.0f", v)));

	private JTextField facilityLevelTextField;
	private JTextField staffEffectivnessTextField;

	private JTextField highQualityLimitTextField;
	private JTextField lowQualityLimitTextField;

	private Predicate<Player<A>> playerFilter;

	private JButton applyButton;

	private JLabel statLabel;

	private TableRowSorter<RosterTableModel> rosterSorter;
	private JTable rosterTable;

	private JPanel controllerPanel;
	private JPanel tablePanel;

	private RosterTableColumnModel rosterTableColumnModel;
	private RosterTableModel rosterTableModel;

	private Roster<A> roster;

	public RosterPanel(
		Roster<A> roster,
		PlayerEvaluator<A> playerEvaluator,
		Predicate<Player<A>> playerFilter)
	{
		rosterTableColumnModel = new RosterTableColumnModel();
		rosterTableModel = new RosterTableModel();

		this.roster = roster;
		this.roster.addCollectionChangedListener(rosterTableModel);
		this.roster.forEach(p -> p.addPropertyChangedListener(rosterTableModel));

		this.playerEvaluator = playerEvaluator;

		this.playerFilter = playerFilter;

		KeyStroke copy = KeyStroke.getKeyStroke(KeyEvent.VK_C, ActionEvent.CTRL_MASK, false);

		rosterSorter = new TableRowSorter<>(rosterTableModel);

		rosterTable = new JTable();
		rosterTable.setAutoCreateRowSorter(true);
		rosterTable.getSelectionModel()
			.addListSelectionListener(
				new ListSelectionListener()
				{
					public void valueChanged(ListSelectionEvent e)
					{
						if (e.getValueIsAdjusting()) return;

						if (playerSelectedListener != null)
						{
							playerSelectedListener.playerSelected(
								RosterPanel.this,
								new PlayerSelectedEvent<A>(RosterPanel.this, getSelectedPlayer()));
						}

						updateStatLabel();
					}
				});
		rosterTable.setColumnModel(rosterTableColumnModel);
		rosterTable.setModel(rosterTableModel);
		rosterTable.setRowSorter(rosterSorter);
		rosterTable.registerKeyboardAction(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				StringBuilder builder = new StringBuilder();

				for (Player<A> player : getSelectedPlayers())
				{
					builder.append(
						String.format(
							"%s (%s) - %s",
							playerEvaluator.getBestPositionRating(player).getName(),
							player.getSide(),
							player.getName()));

					Object value = columnDatas
						.get(rosterTable.getSelectedColumn())
						.getValue(player);

					if (value instanceof Double)
					{
						builder.append(String.format(", %.0f", value));
					}

					builder.append('\n');
				}

				StringSelection selection = new StringSelection(builder.toString());

				Toolkit
					.getDefaultToolkit()
					.getSystemClipboard()
					.setContents(selection, selection);
			}
		}, "Copy", copy, JComponent.WHEN_FOCUSED);

		tablePanel = new JPanel(new BorderLayout());
		tablePanel.add(rosterTable.getTableHeader(), BorderLayout.PAGE_START);
		tablePanel.add(new JScrollPane(rosterTable), BorderLayout.CENTER);

		highQualityLimitTextField = new JTextField(5);
		lowQualityLimitTextField = new JTextField(5);

		facilityLevelTextField = new JTextField(
			Integer.toString(playerEvaluator.getFacilityLevel()),
			5);
		staffEffectivnessTextField = new JTextField(
			Integer.toString(playerEvaluator.getStaffEffectivness()),
			5);

		applyButton = new JButton("Apply");
		applyButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				int facilityLevel = parseInteger(facilityLevelTextField.getText(), 1);
				int staffEffectivness = parseInteger(staffEffectivnessTextField.getText(), 0);

				playerEvaluator.setFacilityLevel(facilityLevel);
				playerEvaluator.setStaffEffectivness(staffEffectivness);

				rosterTableModel.fireAllTableCellsUpdated();
			}
		});

		statLabel = new JLabel();

		controllerPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		controllerPanel.add(new JLabel("Facility Level:"));
		controllerPanel.add(facilityLevelTextField);
		controllerPanel.add(new JLabel("Staff Effectivness:"));
		controllerPanel.add(staffEffectivnessTextField);
		controllerPanel.add(new JLabel("High Quality:"));
		controllerPanel.add(highQualityLimitTextField);
		controllerPanel.add(new JLabel("Low Quality:"));
		controllerPanel.add(lowQualityLimitTextField);
		controllerPanel.add(applyButton);
		controllerPanel.add(statLabel);

		setBorder(
			new CompoundBorder(
				BorderFactory.createTitledBorder("Roster"),
				BorderFactory.createEmptyBorder(5, 5, 5, 5)));

		setLayout(new BorderLayout());

		add(tablePanel, BorderLayout.CENTER);
		add(controllerPanel, BorderLayout.SOUTH);

		notifyPlayerFilterChanged();
	}

	public void notifyPlayerFilterChanged()
	{
		rosterSorter.setRowFilter(new RowFilter<RosterTableModel, Integer>()
		{
			@Override
			public boolean include(
				Entry<? extends RosterPanel<A>.RosterTableModel, ? extends Integer> entry)
			{
				Player<A> player = roster.get(entry.getIdentifier());

				return playerFilter.test(player);
			}
		});
	}

	public void setPlayerSelectedListener(PlayerSelectedListener<A> listener)
	{
		this.playerSelectedListener = listener;
	}

	public void setAttributeEvaluators(List<AttributeEvaluator<A>> attributeEvaluators)
	{
		playerEvaluator.setAttributeEvaluators(attributeEvaluators);

		rosterTableModel.fireAllTableCellsUpdated();
	}

	public List<Player<A>> getSelectedPlayers()
	{
		List<Player<A>> players = new LinkedList<Player<A>>();

		for (int row : rosterTable.getSelectedRows())
		{
			Player<A> player = roster.get(rosterTable.convertRowIndexToModel(row));

			players.add(player);
		}

		return players;
	}

	public Player<A> getSelectedPlayer()
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

	private void updateStatLabel()
	{
		double max = Double.MIN_VALUE;
		double min = Double.MAX_VALUE;
		double sum = 0;
		int count = 0;

		if (rosterTable.getSelectedRowCount() > 1 && rosterTable.getSelectedColumn() != -1)
		{
			for (Player<A> player : getSelectedPlayers())
			{
				Object object = columnDatas
					.get(rosterTable.getSelectedColumn())
					.getValue(player);

				if (object instanceof Integer || object instanceof Double)
				{
					double value = Double.parseDouble(object.toString());

					sum += value;
					count++;

					if (value > max) max = value;
					if (value < min) min = value;
				}
			}
		}

		String statText = count > 0
			? String.format(
				"Max: %.0f, Avg: %.0f, Min: %.0f",
				max,
				sum / count,
				min)
			: "";

		statLabel.setText(statText);
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

	private class ColumnData
	{
		private String name;
		private Function<Player<A>, Object> getValueFunction;
		private Function<Object, String> formatValueFunction;
		private int preferedWidth = 0;

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

		public ColumnData(
			String name,
			Function<Player<A>, Object> getValueAction,
			int preferedWidth)
		{
			this.name = name;
			this.getValueFunction = getValueAction;
			this.formatValueFunction = (v) -> v.toString();
			this.preferedWidth = preferedWidth;
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

		public int getPreferedWidth()
		{
			return preferedWidth;
		}
	}

	private class RosterTableModel
		extends DefaultTableModel
		implements
			CollectionChangedListener,
			PropertyChangedListener
	{
		private static final long serialVersionUID = -3862251740620048034L;

		@Override
		public boolean isCellEditable(int row, int column)
		{
			return false;
		}

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
			Player<?> player = (Player<?>)event.getObjectChanged();

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
			Player<?> player = (Player<?>)source;

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

	private class RosterTableColumnModel
		extends DefaultTableColumnModel
	{
		private static final long serialVersionUID = -318707452817342668L;

		private final Color SELECTED_HIGH_QUALITY_AND_SYMMETRIC = Colors.GREEN;
		private final Color UNSELECTED_HIGH_QUALITY_AND_SYMMETRIC = Colors.LIGHT_GREEN;
		private final Color SELECTED_HIGH_QUALITY = Colors.BLUE;
		private final Color UNSELECTED_HIGH_QUALITY = Colors.LIGHT_BLUE;
		private final Color SELECTED_LOW_QUALITY = Colors.RED;
		private final Color UNSELECTED_LOW_QUALITY = Colors.LIGHT_RED;

		@Override
		public TableColumn getColumn(int columnIndex)
		{
			TableColumn column = super.getColumn(columnIndex);

			column.setCellRenderer(new DefaultTableCellRenderer()
			{
				private static final long serialVersionUID = -871237359467912116L;

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

					Component component = super.getTableCellRendererComponent(
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
					double bestPositionQuality = playerEvaluator
						.getBestPositionQuality(player)
						.getValue();

					return bestPositionQuality > getHighQualityLimit();
				}

				private boolean isLowQualityPlayer(Player<A> player)
				{
					double bestPositionQuality = playerEvaluator
						.getBestPositionQuality(player)
						.getValue();

					return bestPositionQuality < getLowQualityLimit();
				}

				private boolean isSymmetricPlayer(Player<A> player)
				{
					String bestRatingPosition = playerEvaluator
						.getBestPositionRating(player)
						.getName();

					String bestQualityPosition = playerEvaluator
						.getBestPositionQuality(player)
						.getName();

					return bestRatingPosition.equals(bestQualityPosition);
				}
			});

			ColumnData columnData = columnDatas.get(column.getModelIndex());

			if (columnData.getPreferedWidth() != 0)
			{
				column.setPreferredWidth(columnData.getPreferedWidth());
			}

			return column;
		}
	}
}
