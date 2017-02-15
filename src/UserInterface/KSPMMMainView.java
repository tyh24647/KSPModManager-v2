package UserInterface;

import Models.KSPMMTableModel;
import Tasks.AsyncTask;
import Utils.Log;

import javax.annotation.Nonnull;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

import static Constants.BoolConstants.*;
import static Constants.StrConstants.ApplicationDefaults.APP_TITLE;
import static Constants.StrConstants.ENTER_FULLSCREEN;
import static Constants.StrConstants.LabelText.BOTTOM_LABEL_TXT;
import static Constants.StrConstants.LabelText.TITLE_LABEL_TXT;
import static Constants.StrConstants.Messages.Debug.Success.GENERATE_UI_SUCCESS;
import static Constants.StrConstants.Tags.MAIN_VIEW_TAG;
import static Utils.OSUtils.OSUtils.configureOSLookAndFeel;
import static java.awt.Color.CYAN;
import static java.awt.Font.ITALIC;
import static java.awt.Font.PLAIN;
import static java.awt.event.KeyEvent.*;
import static javax.swing.SwingConstants.CENTER;

/**
 * <b>The main GUI for the application</b>
 *
 * @author Tyler Hostager
 * @version 2/12/17
 *
 * @note REMEMBER TO KEEP EVERYTHING CLASSED-OUT IN PROPER MVC FORMAT. NO CHEATING.
 */
public class KSPMMMainView extends JFrame {
    private static final int CMD_MASK = IS_MAC ? META_MASK : CTRL_MASK;
    private static final String TAG = MAIN_VIEW_TAG;

    private static final Color
            DARK_CYAN = CYAN.darker().darker().darker(),
            BRIGHT_CYAN = CYAN.brighter().brighter().brighter(),
            DEFAULT_CYAN = CYAN.darker(),
            LIGHT_CYAN = new Color(200, 250, 250),
            OFF_WHITE = new Color(230, 230, 210).brighter();

    private JPanel mainPanel, northPanel, southPanel, eastPanel, westPanel;
    private JLabel titleLabel, bottomLabel;
    private JScrollPane scrollPane;
    private KSPMMTableModel tableModel;
    private JTable table;
    private JMenuBar menuBar;


    private JMenu fileMenu, editMenu, viewMenu, windowMenu, toolsMenu, debugMenu, helpMenu;

    private ArrayList<JMenu> menus;

    private JMenuItem openItem, importItem, exitItem, undoItem, redoItem, prefsItem, selectAllItem,
            itemEnabledItem, viewLogsItem, showComponentsItem, showConsoleItem, openLogsFolderItem,
            fullscreenItem, themeItem, aboutItem, contactItem, helpItem, updateItem,
            viewThreadsItem, reportBugsItem, dumpProcessItem, dumpProcessToFileItem,
            enableAllItem, disableAllItem;

    private ArrayList<JComponent> fileItems, editItems, viewItems, windowItems,
            toolsItems, debugItems, helpItems, panels, menuItems;

    public KSPMMMainView() {
        try {
            configureOSLookAndFeel(this);
            Log.DEBUG(TAG, GENERATE_UI_SUCCESS);
        } catch (Exception e) {
            Log.ERROR(e, e.getMessage());
        }
    }

    private void initMainFrame() {
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setPreferredSize(new Dimension(1000, 800));
        getRootPane().setWindowDecorationStyle(JRootPane.FRAME);
        setResizable(true);
        setVisible(true);
        pack();
    }

    public void setupMainFrame() {
        setTitle(APP_TITLE);
        menus = new ArrayList<>();
        generatePanels();   //  <-- loaded on background thread to prevent lag
        initMainFrame();
        generateMenuBar();
    }

    private void generateMenuBar() {
        menuBar = new JMenuBar();
        menuItems = new ArrayList<>();

        AsyncTask.execute(() -> {
            generateMenus();
            repaint();
        });

        setJMenuBar(menuBar);
    }

    /**
     * <p>Asynchronously generates the JPanels to add to the main panel</p>
     *
     * @note These panels must be generated in this order, because the size of
     * some of the {@link JComponent}s depend on the size of one that was
     * previously generated
     */
    private void generatePanels() {
        panels = new ArrayList<>();
        AsyncTask.execute(() -> {
            Log.DEBUG(TAG, "Generating panel content on background thread...");
            generateMainPanel();
            generateNorthPanel();
            generateSouthPanel();
            generateEastPanel();
            generateWestPanel();
            generateCenterPanel();
            Log.DEBUG(TAG, "Panels successfully created");
        });

        repaint();
    }

    private void generateMenus() {
        menus = new ArrayList<>();
        generateFileMenu();
        generateEditMenu();
        generateViewMenu();
        generateToolsMenu();
        generateWindowMenu();
        generateDebugMenu();
        generateHelpMenu();
        for (JMenu menu : menus) menuBar.add(menu);
    }

    private void generateMainPanel() {
        mainPanel = new JPanel(new BorderLayout());
        mainPanel.setOpaque(true);
        mainPanel.setBackground(OFF_WHITE);
        mainPanel.setPreferredSize(new Dimension(1000, 800));
        add(mainPanel, BorderLayout.CENTER);
        panels.add(mainPanel);
    }

    private void generateNorthPanel() {
        northPanel = new JPanel(new BorderLayout());
        northPanel.setSize(mainPanel.getWidth(), 60);
        northPanel.setBackground(CYAN.darker());
        titleLabel = new JLabel(TITLE_LABEL_TXT);
        titleLabel.setFont(mainPanel.getFont().deriveFont(ITALIC, 28));
        titleLabel.setOpaque(false);
        titleLabel.setForeground(OFF_WHITE);
        titleLabel.setHorizontalAlignment(CENTER);
        titleLabel.setVerticalAlignment(CENTER);
        titleLabel.setPreferredSize(northPanel.getSize());
        northPanel.add(titleLabel, BorderLayout.CENTER);
        mainPanel.add(northPanel, BorderLayout.NORTH);
        panels.add(northPanel);
    }

    private void generateSouthPanel() {
        southPanel = new JPanel(new BorderLayout());
        southPanel.setSize(mainPanel.getWidth(), 40);
        southPanel.setBackground(DARK_CYAN);
        bottomLabel = new JLabel(BOTTOM_LABEL_TXT);
        bottomLabel.setFont(mainPanel.getFont().deriveFont(PLAIN, 12));
        bottomLabel.setOpaque(false);
        bottomLabel.setForeground(OFF_WHITE);
        bottomLabel.setHorizontalAlignment(CENTER);
        bottomLabel.setVerticalAlignment(CENTER);
        bottomLabel.setPreferredSize(southPanel.getSize());
        southPanel.add(bottomLabel, BorderLayout.CENTER);
        mainPanel.add(southPanel, BorderLayout.SOUTH);
        panels.add(southPanel);
    }

    private void generateEastPanel() {
        eastPanel = new JPanel(new BorderLayout());

        /*
        TODO
         */

        panels.add(eastPanel);
    }

    private void generateWestPanel() {
        westPanel = new JPanel(new BorderLayout());

        /*
        TODO
         */

        panels.add(westPanel);
    }

    private void generateCenterPanel() {
        scrollPane.setSize(mainPanel.getWidth(), mainPanel.getHeight() - (northPanel.getHeight() + southPanel.getHeight()));
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane.setViewportView(tableModel.getTable());
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        panels.add(scrollPane);
    }

    private void generateFileMenu() {
        fileMenu = new JMenu("File");
        fileItems = new ArrayList<>();
        openItem = new JMenuItem("Open Mods Folder...");
        openItem.setMnemonic(VK_O);
        openItem.setAccelerator(KeyStroke.getKeyStroke(VK_O, CMD_MASK));
        importItem = new JMenuItem("Import Mod...");
        importItem.setMnemonic(VK_I);
        importItem.setAccelerator(KeyStroke.getKeyStroke(VK_I, CMD_MASK));
        fileItems.add(openItem);
        addSeparator(fileItems);
        fileItems.add(importItem);

        if (!IS_MAC) {
            addSeparator(fileItems);
            exitItem = new JMenuItem("Quit KSP Mod Manager");
            exitItem.setMnemonic(VK_Q);
            exitItem.setAccelerator(KeyStroke.getKeyStroke(VK_Q, CTRL_MASK));
            fileItems.add(exitItem);
        }

        menuItems.addAll(fileItems);
        for (JComponent item : fileItems) fileMenu.add(item);
        menus.add(fileMenu);
    }

    private void generateEditMenu() {
        editMenu = new JMenu("Edit");
        editMenu.setMnemonic(VK_E);
        editItems = new ArrayList<>();
        undoItem = new JMenuItem("Undo");
        undoItem.setMnemonic(VK_Z);
        undoItem.setAccelerator(KeyStroke.getKeyStroke(VK_Z, CMD_MASK));
        editItems.add(undoItem);
        redoItem = new JMenuItem("Redo");
        redoItem.setMnemonic(VK_R);
        redoItem.setAccelerator(KeyStroke.getKeyStroke(VK_Z, CMD_MASK + SHIFT_MASK));
        editItems.add(redoItem);
        addSeparator(editItems);
        selectAllItem = new JMenuItem("Select All");
        selectAllItem.setMnemonic(VK_S);
        selectAllItem.setAccelerator(KeyStroke.getKeyStroke(VK_A, CMD_MASK));
        editItems.add(selectAllItem);
        itemEnabledItem = new JMenuItem("Enable Selected Item(s)");    // TODO: Change text in action listener to disabled if enabled
        itemEnabledItem.setMnemonic(VK_P);
        itemEnabledItem.setAccelerator(KeyStroke.getKeyStroke(VK_E, CMD_MASK));
        editItems.add(itemEnabledItem);
        addSeparator(editItems);
        prefsItem = new JMenuItem("Preferences");
        prefsItem.setMnemonic(VK_P);
        prefsItem.setAccelerator(KeyStroke.getKeyStroke(VK_P, CMD_MASK));
        editItems.add(prefsItem);
        menuItems.addAll(editItems);
        for (JComponent item : editItems) editMenu.add(item);
        menus.add(editMenu);
    }

    private void generateViewMenu() {
        viewMenu = new JMenu("View");
        viewMenu.setMnemonic(VK_V);
        viewItems = new ArrayList<>();
        viewLogsItem = new JMenuItem("Log");
        viewLogsItem.setMnemonic(VK_L);
        viewLogsItem.setAccelerator(KeyStroke.getKeyStroke(VK_L, CMD_MASK));
        viewItems.add(viewLogsItem);
        addSeparator(viewItems);

        // TODO CHANGE TO SHOW/HIDE INDIVIDUAL COMPONENTS
        showComponentsItem = new JMenuItem("Show Components");
        showComponentsItem.setMnemonic(VK_S);
        viewItems.add(showComponentsItem);
        // TODO CHANGE TO SHOW/HIDE INDIVIDUAL COMPONENTS

        menuItems.addAll(viewItems);
        for (JComponent item : viewItems) viewMenu.add(item);
        menus.add(viewMenu);
    }

    private void generateToolsMenu() {
        toolsMenu = new JMenu("Tools");
        toolsMenu.setMnemonic(VK_T);
        toolsItems = new ArrayList<>();
        addSeparator(toolsItems);
        addSeparator(toolsItems);
        updateItem = new JMenuItem("Check for updates...");
        updateItem.setMnemonic(VK_C);
        toolsItems.add(updateItem);
        menuItems.addAll(toolsItems);
        for (JComponent item : toolsItems) toolsMenu.add(item);
        menus.add(toolsMenu);
    }

    private void generateWindowMenu() {
        windowMenu = new JMenu("Window");
        windowItems = new ArrayList<>();
        themeItem = new JMenuItem("Change theme...");
        themeItem.setMnemonic(VK_C);
        windowItems.add(themeItem);
        addSeparator(windowItems);
        fullscreenItem = new JMenuItem(ENTER_FULLSCREEN); // TODO: Change to exit fullscreen in action listener
        fullscreenItem.setMnemonic(VK_F);
        fullscreenItem.setAccelerator(KeyStroke.getKeyStroke(VK_F, SHIFT_MASK + CMD_MASK));
        windowItems.add(fullscreenItem);
        menuItems.addAll(windowItems);
        for (JComponent item : windowItems) windowMenu.add(item);
        menus.add(windowMenu);
    }

    private void generateDebugMenu() {
        debugMenu = new JMenu("Debug");
        debugItems = new ArrayList<>();
        showConsoleItem = new JMenuItem("Show Debug Console");
        showConsoleItem.setMnemonic(VK_S);
        showConsoleItem.setAccelerator(KeyStroke.getKeyStroke(VK_D, CMD_MASK));
        debugItems.add(showConsoleItem);
        addSeparator(debugItems);
        viewThreadsItem = new JMenuItem("View Active Threads");
        viewThreadsItem.setMnemonic(VK_V);
        viewThreadsItem.setAccelerator(KeyStroke.getKeyStroke(VK_T, CMD_MASK + ALT_MASK));
        debugItems.add(viewThreadsItem);
        addSeparator(debugItems);
        dumpProcessItem = new JMenuItem("Show Process Dump");
        dumpProcessItem.setMnemonic(VK_S);
        debugItems.add(dumpProcessItem);
        dumpProcessToFileItem = new JMenuItem("Dump Current Process...");
        dumpProcessToFileItem.setMnemonic(VK_D);
        dumpProcessToFileItem.setAccelerator(KeyStroke.getKeyStroke(VK_P, CMD_MASK + SHIFT_MASK));
        debugItems.add(dumpProcessToFileItem);
        addSeparator(debugItems);
        openLogsFolderItem = new JMenuItem("Open Logs Folder...");
        openLogsFolderItem.setMnemonic(VK_O);
        debugItems.add(openLogsFolderItem);
        addSeparator(debugItems);
        reportBugsItem = new JMenuItem("Submit Bug Report");
        reportBugsItem.setMnemonic(VK_S);
        reportBugsItem.setAccelerator(KeyStroke.getKeyStroke(VK_R, CMD_MASK));
        debugItems.add(reportBugsItem);
        menuItems.addAll(debugItems);
        for (JComponent item : debugItems) debugMenu.add(item);
        menus.add(debugMenu);
    }

    private void generateHelpMenu() {
        helpMenu = new JMenu("Help");
        helpItems = new ArrayList<>();
        helpItem = new JMenuItem("Help");
        helpItem.setMnemonic(VK_H);
        helpItem.setAccelerator(KeyStroke.getKeyStroke(VK_H, CMD_MASK));
        helpItems.add(helpItem);
        addSeparator(helpItems);
        aboutItem = new JMenuItem("About KSPMM");
        aboutItem.setMnemonic(VK_A);
        helpItems.add(aboutItem);
        addSeparator(helpItems);
        contactItem = new JMenuItem("Contact us");
        contactItem.setMnemonic(VK_C);
        helpItems.add(contactItem);
        menuItems.addAll(helpItems);
        for (JComponent item : helpItems) helpMenu.add(item);
        menus.add(helpMenu);
    }

    public JComponent[] getAllElements() {
        ArrayList<JComponent> components = new ArrayList<>();
        components.addAll(menus);
        components.addAll(menuItems);
        components.addAll(panels);
        JComponent[] elements = new JComponent[components.size()];
        for (int i = 0; i < components.size(); i++) elements[i] = components.get(i);
        return elements;
    }

    private void addSeparator(ArrayList<JComponent> components) {
        if (IS_WINDOWS) {
            return;
        }

        if (IS_MAC || IS_LINUX || IS_SOLARIS) {
            components.add(new JSeparator());
        }
    }

    public void setMenuItems(@Nonnull ArrayList<JComponent> menuItems) {
        this.menuItems = menuItems;
    }

    @Nonnull
    public ArrayList<JComponent> getMenuItems() {
        return menuItems;
    }

    public JMenuItem getAboutItem() {
        return aboutItem;
    }

    public JMenuItem getItemEnabledItem() {
        return itemEnabledItem;
    }

    public JMenuItem getContactItem() {
        return contactItem;
    }

    public JMenuItem getExitItem() {
        return exitItem;
    }

    public JMenuItem getFullscreenItem() {
        return fullscreenItem;
    }

    public JMenuItem getHelpItem() {
        return helpItem;
    }

    public JMenuItem getImportItem() {
        return importItem;
    }

    public JMenuItem getOpenItem() {
        return openItem;
    }

    public JMenuItem getPrefsItem() {
        return prefsItem;
    }

    public JMenuItem getRedoItem() {
        return redoItem;
    }

    public JMenuItem getThemeItem() {
        return themeItem;
    }

    public JMenuItem getUndoItem() {
        return undoItem;
    }

    public JMenuItem getUpdateItem() {
        return updateItem;
    }

    //region ACCESSOR METHODS
    @Nonnull
    public JFrame getMainFrame() {
        return this;
    }

    public void setTableModel(@Nonnull KSPMMTableModel tableModel) {
        this.tableModel = tableModel;
    }

    @Nonnull
    public KSPMMTableModel getTableModel() {
        return tableModel;
    }

    public void setBottomLabel(@Nonnull JLabel bottomLabel) {
        this.bottomLabel = bottomLabel;
    }

    @Nonnull
    public JLabel getBottomLabel() {
        return bottomLabel;
    }

    public void setEastPanel(@Nonnull JPanel eastPanel) {
        this.eastPanel = eastPanel;
    }

    @Nonnull
    public JPanel getEastPanel() {
        return eastPanel;
    }

    public void setJTable(@Nonnull JTable jTable) {
        this.table = jTable;
    }

    @Nonnull
    public JTable getJTable() {
        return table;
    }

    public void setMainPanel(@Nonnull JPanel mainPanel) {
        this.mainPanel = mainPanel;
    }

    @Nonnull
    public JPanel getMainPanel() {
        return mainPanel;
    }

    public void setNorthPanel(@Nonnull JPanel northPanel) {
        this.northPanel = northPanel;
    }

    public void setScrollPane(@Nonnull JScrollPane scrollPane) {
        this.scrollPane = scrollPane;
    }

    @Nonnull
    public JScrollPane getScrollPane() {
        return scrollPane;
    }

    public void setSouthPanel(@Nonnull JPanel southPanel) {
        this.southPanel = southPanel;
    }

    @Nonnull
    public JPanel getSouthPanel() {
        return southPanel;
    }

    public void setWestPanel(@Nonnull JPanel westPanel) {
        this.westPanel = westPanel;
    }

    @Nonnull
    public JPanel getWestPanel() {
        return westPanel;
    }

    public void setTitleLabel(@Nonnull JLabel titleLabel) {
        this.titleLabel = titleLabel;
    }

    @Nonnull
    public JLabel getTitleLabel() {
        return titleLabel;
    }
    //endregion

    //region OVERRIDE METHODS
    @Override
    public void setJMenuBar(@Nonnull JMenuBar menubar) {
        super.setJMenuBar(menubar);
    }

    @Override
    public JMenuBar getJMenuBar() {
        return menuBar;
    }
    //endregion
}
