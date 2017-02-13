package Models;

import Constants.Defaults;
import Utils.Log;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.*;
import java.awt.*;

import static java.awt.Color.CYAN;

/**
 * @author Tyler Hostager
 * @version 2/12/17
 */
public class KSPMMTableModel extends KSPMMAbstractTableModel implements TableModel {

    private static final Color
            OFF_WHITE = new Color(230, 230, 210),
            BRIGHT_CYAN = CYAN.brighter().brighter().brighter(),
            DARK_CYAN = CYAN.darker().darker().darker();

    public static final String[] COLUMN_NAMES = {
            "Enabled", "Mod Name", "Installation Directory", "Date Added"
    };

    private static final int
            ROW_HEIGHT = 20,
            DEFAULT_ENABLED_COLUMN_WIDTH = 50,
            DEFAULT_STRING_COLUMN_WIDTH = 300,
            DEFAULT_DATE_COLUMN_WIDTH = 200,
            DEFAULT_ROW_HEIGHT = 20;

    private KSPModManager modManagerModel;
    private JScrollPane scrollPane;
    //private Object[][] data;
    private JTable table;
    private TableColumn enabledColumn;
    
    private Object[][] data = {
            {Boolean.TRUE, "c2", "c3", "c4"},
            {Boolean.TRUE, "AFGGc4", "q346q", "asdf"},
            {Boolean.TRUE, "c6", "3433333", "tmp"}
    };

    public KSPMMTableModel() {
        generateTableData();
    }

    private void generateTableData() {
        this.table = new JTable(this) {
            @Override
            public void setColumnSelectionAllowed(boolean columnSelectionAllowed) {
                super.setColumnSelectionAllowed(true);
            }

            @Override
            public void setGridColor(@NotNull Color gridColor) {
                super.setGridColor(OFF_WHITE);
            }

            @Override
            public void setSelectionBackground(Color selectionBackground) {
                super.setSelectionBackground(BRIGHT_CYAN);
            }

            @Override
            public void setSelectionForeground(Color selectionForeground) {
                super.setSelectionForeground(DARK_CYAN);
            }

            @Override
            public void setCellSelectionEnabled(boolean cellSelectionEnabled) {
                super.setCellSelectionEnabled(true);
            }

            @Override
            public void setBorder(Border border) {
                super.setBorder(BorderFactory.createEtchedBorder());
            }

            @Override
            public void setRowHeight(int rowHeight) {
                super.setRowHeight(ROW_HEIGHT);
            }
        };



        // add row sorter for each column
        RowSorter<TableModel> sorter = new TableRowSorter<>(this);
        table.setRowSorter(sorter);
        resizeColumnWidth(table);
        table.getColumnModel().setColumnSelectionAllowed(true);

        // set specific column widths
        for (int i = 0; i < table.getColumnCount(); i++) {
            int width;
            switch (i) {
                case 0:
                    width = DEFAULT_ENABLED_COLUMN_WIDTH;
                    break;
                case 3:
                    width = DEFAULT_DATE_COLUMN_WIDTH;
                    break;
                default:
                    width = DEFAULT_STRING_COLUMN_WIDTH;
                    break;
            }

            table.getColumnModel().getColumn(i).setPreferredWidth(width);
        }

        String tableHeadersFontName = table.getTableHeader().getFont().deriveFont(Font.PLAIN).getName();
        table.getTableHeader().setFont(new Font(tableHeadersFontName, Font.PLAIN, 12));

        table.setAutoCreateColumnsFromModel(true);
        table.setPreferredScrollableViewportSize(new Dimension(1280, 800));

        scrollPane = new JScrollPane(table);
        enabledColumn = table.getColumnModel().getColumn(0);
        getTable();
    }

    public JTable getTable() {
        return table;
    }

    public void resizeColumnWidth(JTable table) {
        final TableColumnModel columnModel = table.getColumnModel();

        for (int column = 0; column < table.getColumnCount(); column++) {
            int width = 15; //  <-- Minimum width

            for (int row = 0; row < table.getRowCount(); row++) {
                TableCellRenderer renderer = table.getCellRenderer(row, column);
                Component comp = table.prepareRenderer(renderer, row, column);
                width = Math.max(comp.getPreferredSize().width + 1, width);
            }

            if (width > 300) {
                width = 300;
            }

            columnModel.getColumn(column).setPreferredWidth(width);
        }
    }

    private KSPModManager generateMMModel() {
        return setModManagerModel(new KSPModManager());
    }

    public KSPModManager setModManagerModel(KSPModManager model) {
        return this.modManagerModel = model == null ? generateMMModel() : model;
    }

    public KSPModManager getModManagerModel() {
        return modManagerModel == null ? generateMMModel() : modManagerModel;
    }

    public void setScrollPane(JScrollPane scrollPane) {
        this.scrollPane = scrollPane;
    }

    public JScrollPane getScrollPane() {
        return scrollPane;
    }

    public void setData(Object[][] data) {
        this.data = data == null ? Defaults.EMPTY_DATA_TABLE : data;
    }

    public Object[][] getData() {
        return data == null && modManagerModel.getData() == null ? Defaults.EMPTY_DATA_TABLE : data;
    }

    public void addRow(String modName, String installationDir, String dateAdded) {
        DefaultTableModel model = ((DefaultTableModel) table.getModel());
        Object[] rowData = {Boolean.TRUE, modName, installationDir, dateAdded};
        model.addRow(rowData);
    }

    @Override
    public void fireTableDataChanged() {
        //super.fireTableDataChanged();
        fireTableCellUpdated(table.getSelectedRow(), table.getSelectedColumn());
        // TODO add functionality here

        table.repaint();
    }

    @Override
    public void fireTableCellUpdated(int row, int column) {
        Log.DEBUG("Updating cell at position (" + row + ", " + column + ")...");
        String typeStr;
        if (column == 0) {
            typeStr = "Boolean";
        } else {
            typeStr = "String";
        }

        Log.DEBUG("- Title: 'Enabled'");
        Log.DEBUG("- Type:  '" + typeStr + "'");
        Log.DEBUG("- Value: '" + getValueAt(row, column) + "'");
        Log.DEBUG("Cell updated successfully");

        if (getValueAt(row, column) == Boolean.TRUE) {
            // TODO move mod file into primary location
        } else {
            // TODO move mod into disabled folder
        }

        table.repaint();
    }

    @Override
    public void fireTableChanged(TableModelEvent e) {
        fireTableCellUpdated(e.getFirstRow(), e.getColumn());
        super.fireTableChanged(e);
    }

    @Override
    public void fireTableRowsDeleted(int firstRow, int lastRow) {
        super.fireTableRowsDeleted(firstRow, lastRow);
    }

    @Override
    public void fireTableRowsInserted(int firstRow, int lastRow) {
        super.fireTableRowsInserted(firstRow, lastRow);
        fireTableCellUpdated(table.getSelectedRow(), table.getSelectedColumn());
    }

    @Override
    public void fireTableRowsUpdated(int firstRow, int lastRow) {
        super.fireTableRowsUpdated(firstRow, lastRow);
    }

    @Override
    public void fireTableStructureChanged() {
        super.fireTableStructureChanged();
        fireTableCellUpdated(table.getSelectedRow(), table.getSelectedColumn());
    }

    @Override
    public void setValueAt(Object value, int rowIndex, int columnIndex) {
        data[rowIndex][columnIndex] = value;
        fireTableCellUpdated(rowIndex, columnIndex);
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return data[rowIndex][columnIndex];
    }

    @Override
    public void addTableModelListener(TableModelListener l) {
        super.addTableModelListener(l);
    }

    @Override
    public int getRowCount() {
        return data.length;
    }

    @Override
    public int getColumnCount() {
        return COLUMN_NAMES.length;
    }

    @Override
    public String getColumnName(int column) {
        return COLUMN_NAMES[column];
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return columnIndex == 0 ? Boolean.class : String.class;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return columnIndex < 2;
    }

    @Override
    Color getBackgroundColor() {
        return null;
    }

    @Override
    String getTitle() {
        return null;
    }

    @Override
    void setSize(int size) {

    }

    @Override
    boolean clear() {
        return false;
    }

    @Override
    boolean deleteTableData() {
        return false;
    }

    @Override
    boolean isNull() {
        return false;
    }
}
