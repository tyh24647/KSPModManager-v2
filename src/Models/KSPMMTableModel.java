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
import java.io.File;
import java.util.ArrayList;
import java.util.Date;

import static Constants.StrConstants.Characters.EMPTY;
import static Constants.StrConstants.DECIMAL_FORMAT;
import static Constants.StrConstants.Headers.*;
import static Constants.StrConstants.MODS_FOLDER_PATH;
import static java.awt.Color.CYAN;

/**
 * @author Tyler Hostager
 * @version 2/12/17
 */
public class KSPMMTableModel extends KSPMMAbstractTableModel implements TableModel {

    private static final Color
            OFF_WHITE = new Color(230, 230, 210),
            BRIGHT_CYAN = CYAN.brighter().brighter().brighter().brighter().brighter(),
            DARK_CYAN = CYAN.darker().darker().darker();

    public static final String[] COLUMN_NAMES = {
            "Enabled", "Mod Name", "Size", "Installation Directory", "Date Added"
    };

    enum Columns {
        ENABLED(0, COLUMN_NAMES[0]),
        MOD_NAME(1, COLUMN_NAMES[1]),
        SIZE(2, COLUMN_NAMES[2]),
        MOD_DIR(3, COLUMN_NAMES[3]),
        DATE_ADDED(4, COLUMN_NAMES[4])
        ;

        private String name = null;
        private Integer val = null;
        Columns(Integer val, String name) {
            this.val = val;
            this.name = name;
        }

        public int getVal() {
            return val == null ? -1 : this.val;
        }

        public String getName() {
            return name == null ? EMPTY : this.name;
        }
    }

    private static final int
            ROW_HEIGHT = 20,
            DEFAULT_ENABLED_COLUMN_WIDTH = 50,
            DEFAULT_STRING_COLUMN_WIDTH = 300,
            DEFAULT_SIZE_COLUMN_WIDTH = 75,
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
                super.setSelectionBackground(OFF_WHITE.brighter());
            }

            @Override
            public TableCellRenderer getCellRenderer(int row, int column) {
                TableCellRenderer renderer;
                switch (column) {
                    case 0:
                        renderer = super.getDefaultRenderer(Boolean.class);
                        break;
                    case 1:
                        renderer = super.getCellRenderer(row, column);
                        break;
                    case 2 & 3:
                        renderer = new DefaultTableCellRenderer() {
                            @Override
                            public void setHorizontalAlignment(int alignment) {
                                super.setHorizontalAlignment(SwingConstants.RIGHT);
                            }
                        };
                        break;
                    default:
                        renderer = super.getCellRenderer(row, column);
                        break;
                }

                return renderer;
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

        loadUserDataFromDefaultPath();

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
                case 2:
                    width = DEFAULT_SIZE_COLUMN_WIDTH;
                    break;
                case 4:
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
    }

    private void loadUserDataFromDefaultPath() {
        File kspDir = new File(MODS_FOLDER_PATH);
        ArrayList<Object[]> userData = new ArrayList<>();
        try {
            Log.DEBUG("Loading mods data from directory \"" + MODS_FOLDER_PATH + "\"");
            if (kspDir.exists() && kspDir.isDirectory()) {
                File[] dirFiles = kspDir.listFiles();
                if (dirFiles != null && dirFiles.length > 0) {
                    for (File file : dirFiles) {
                        if (file.getName().charAt(0) != '.') {
                            userData.add(new Object[] {
                                    Boolean.TRUE,
                                    file.getCanonicalFile().getName().replace(".dll", "").replace(".ksp", ""),
                                    getFileSizeStrForFile(file),
                                    (" C:").concat(file.getAbsolutePath()),
                                    new Date(file.lastModified() * 1000).toString()
                            });
                        }
                    }
                }
            } else {
                Log.ERROR("No mods folder specified");
                Log.DEBUG("Generating mods folder at default path...");
                if (kspDir.mkdirs()) {
                    Log.DEBUG("Mods folder created successfully");
                    Log.DEBUG("Recursively reloading data after folder generation...");
                    loadUserDataFromDefaultPath();
                } else {
                    Log.ERROR("Unable to generate mods folder");
                    Log.DEBUG("Skipping procedure");
                }
            }
        } catch (Exception e) {
            Log.ERROR(e, e.getMessage());
        }

        Object[][] tmp = null;
        if (userData.size() > 0) {
            tmp = new Object[userData.size()][COLUMN_NAMES.length];

            for (int i = 0; i < userData.size(); i++) {
                if (userData.get(i) == null) {
                    continue;
                }

                tmp[i] = userData.get(i);
            }
        }

        data = tmp != null && tmp.length > 0 ? tmp : getData();
    }

    private String getFileSizeStrForFile(File file) {
        if (!file.exists() && file.length() > 0) { return 0.00 + GIGABYTES; }
        double bytes = file.length();
        if (bytes < 1000) { return String.format(DECIMAL_FORMAT, bytes) + BYTES; }
        double kilobytes = (bytes / 1024);
        if (kilobytes < 1000) { return String.format(DECIMAL_FORMAT, kilobytes)  + KILOBYTES; }
        double megabytes = (kilobytes / 1024);
        if (megabytes < 1000) { return String.format(DECIMAL_FORMAT, megabytes) + MEGABYTES; }
        double gigabytes = (megabytes / 1024);
        return String.format(DECIMAL_FORMAT, gigabytes) + GIGABYTES;
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
