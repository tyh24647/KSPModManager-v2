package UserInterface;

import Models.KSPMMTableModel;
import Tasks.AsyncTask;
import Utils.Log;

import javax.annotation.Nonnull;
import javax.swing.*;
import java.awt.*;

import static Constants.StrConstants.*;
import static java.awt.Color.CYAN;
import static java.awt.Font.ITALIC;
import static java.awt.Font.PLAIN;
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

    private static final Color
            DARK_CYAN = CYAN.darker().darker().darker(),
            BRIGHT_CYAN = CYAN.brighter().brighter().brighter(),
            DEFAULT_CYAN = CYAN.darker(),
            LIGHT_CYAN = new Color(200, 250, 250),
            OFF_WHITE = new Color(230, 230, 210);

    private JPanel mainPanel, northPanel, southPanel, eastPanel, westPanel, centerPanel;
    private JLabel titleLabel, bottomLabel;
    private JScrollPane scrollPane;
    private KSPMMTableModel tableModel;
    private JTable jTable;

    public KSPMMMainView() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            Log.ERROR(e, e.getMessage());
        }
    }

    public void setupMainFrame() {
        setTitle(TITLE);
        generatePanels();   //  <-- loaded on background thread to prevent lag
        initMainFrame();
    }

    /**
     * <p>
     *     Loads panels via async - prevents the application from hanging
     *     up when loading UI graphics/data
     * </p>
     */
    private void generatePanels() {
        AsyncTask.execute(() -> {
            Log.DEBUG("Generating panel content on background thread...");
            generateMainPanel();
            generateNorthPanel();
            generateCenterPanel();
            generateSouthPanel();
            Log.DEBUG("Panels successfully created");
        });
    }

    private void initMainFrame() {
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setPreferredSize(new Dimension(1000, 800));
        setResizable(true);
        pack();
        setVisible(true);
    }

    private void generateMainPanel() {
        mainPanel = new JPanel(new BorderLayout());
        mainPanel.setOpaque(true);
        //mainPanel.setBackground(LIGHT_GRAY.brighter());
        mainPanel.setBackground(OFF_WHITE);
        mainPanel.setPreferredSize(new Dimension(1000, 800));
        add(mainPanel, BorderLayout.CENTER);
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
    }

    private void generateCenterPanel() {
        this.centerPanel = centerPanel == null ? new JPanel(new BorderLayout()) : centerPanel;
        centerPanel.setSize(mainPanel.getWidth(), mainPanel.getHeight() - 100);
        centerPanel.setBackground(OFF_WHITE);
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
    }

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
        this.jTable = jTable;
    }

    @Nonnull
    public JTable getJTable() {
        return jTable;
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

    public void setCenterPanel(@Nonnull JPanel centerPanel) {
        this.centerPanel = centerPanel;
    }

    @Nonnull
    public JPanel getCenterPanel() {
        return centerPanel;
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

    @Override
    public void setJMenuBar(@Nonnull JMenuBar menubar) {
        super.setJMenuBar(menubar);
    }

    @Override
    public JMenuBar getJMenuBar() {
        return super.getJMenuBar();
    }

    /*
        private void generateCenterPanel() {
        centerPanel = centerPanel == null ? new JPanel(new BorderLayout()) : centerPanel;
        centerPanel.setPreferredSize(DEFAULT_CENTER_PANEL_SIZE);

        // add scroll pane with jtable
        centerScrollPane = tableModel.getScrollPane();
        centerScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        centerScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        menuBar.setOpaque(true);
        menuBar.setUI(new BasicMenuBarUI() {
            @Override
            public void paint(Graphics g, JComponent c) {
                g.setColor(Color.CYAN.darker());
                g.fillRect(0, 0, c.getWidth(), c.getHeight());
            }
        });

        menuBar.setBorder(BorderFactory.createEmptyBorder());

        // add top bar
        northPanel = new JPanel(new BorderLayout()) {
            @Override
            public void setBorder(Border border) {
                super.setBorder(BorderFactory.createEmptyBorder());
            }
        };

        northPanel.setPreferredSize(TOP_PANE_PREFERRED_SIZE);
        topLabel = new JLabel(WINDOW_TITLE) {
            @Override
            public void setOpaque(boolean isOpaque) {
                super.setOpaque(true);
            }

            @Override
            public void setFont(Font font) {
                String tableFont = tableModel.getTable().getFont().deriveFont(Font.PLAIN).getFontName();
                super.setFont(new Font(tableFont, Font.PLAIN, 20));
            }

            @Override
            public void setBackground(Color bg) {
                super.setBackground(Color.cyan.darker());
            }
        };

        topLabel.setOpaque(false);
        topLabel.setHorizontalAlignment(SwingConstants.CENTER);
        topLabel.setVerticalAlignment(SwingConstants.CENTER);
        topLabel.setPreferredSize(northPanel.getSize());
        northPanel.add(topLabel, BorderLayout.CENTER);
        mainPanel.add(northPanel, BorderLayout.NORTH);


        // init south panel
        southPanel = new JPanel(new BorderLayout()) {
            @Override
            public void setBorder(Border border) {
                super.setBorder(BorderFactory.createEmptyBorder());
            }
        };

        southPanel.setPreferredSize(BOTTOM_PANE_PREFERRED_SIZE);
        bottomLabel = new JLabel(COPYRIGHT_MSG) {
            @Override
            public void setOpaque(boolean isOpaque) {
                super.setOpaque(true);
            }

            @Override
            public void setFont(Font font) {
                String tblFnt = tableModel.getTable().getFont().deriveFont(Font.PLAIN).getFontName();
                super.setFont(new Font(tblFnt, Font.PLAIN, 14));
            }

            @Override
            public void setBackground(Color bg) {
                super.setBackground(Color.CYAN.darker().darker().darker());
            }

            @Override
            public void setForeground(Color fg) {
                super.setForeground(Color.CYAN.brighter().brighter().brighter().brighter());
            }
        };

        bottomLabel.setHorizontalAlignment(SwingConstants.CENTER);
        bottomLabel.setVerticalAlignment(SwingConstants.CENTER);
        bottomLabel.setPreferredSize(southPanel.getSize());
        southPanel.add(bottomLabel, BorderLayout.CENTER);
        mainPanel.add(southPanel, BorderLayout.SOUTH);

        // add scrollpane to main panel
        mainPanel.add(centerScrollPane, BorderLayout.CENTER);
        centerScrollPane.setViewportView(tableViewController.getModel().getTable());
        repaint();
    }
     */
}
