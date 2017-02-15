package Controllers;

import Models.KSPMMTableModel;
import Models.KSPModManager;
import Objects.Users.User;
import Tasks.AsyncTask;
import UserInterface.KSPMMMainView;
import Utils.Log;
import Utils.OSUtils.MacOSX.MacOSXUtils;

import javax.annotation.Nonnull;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import static Constants.BoolConstants.IS_MAC;
import static Constants.StrConstants.ENTER_FULLSCREEN;
import static Constants.StrConstants.EXIT_FULLSCREEN;
import static Constants.StrConstants.Messages.Debug.InitializationMsgs.GENERATE_TABLE_MODEL;
import static Constants.StrConstants.generateTagForName;

/**
 * <p>The primary middleware controller delegate between model and UI objects.</p>
 *
 * @author Tyler Hostager
 * @version 2/13/17
 */
public class MainViewController implements ActionListener, KeyListener {
    private static final String TAG = generateTagForName(MainViewController.class.getSimpleName());

    private KSPModManager model;
    private KSPMMMainView view;

    public MainViewController(KSPModManager model, KSPMMMainView view) {
        setModel(model);
        setView(view);
    }

    public boolean initUserInterface() {
        if (model == null || view == null) {
            return false;
        }

        SwingUtilities.invokeLater(() -> {
            AsyncTask.execute(() -> {
                Log.DEBUG(TAG, GENERATE_TABLE_MODEL);
                KSPMMTableModel tableModel = new KSPMMTableModel();
                model.setTableModel(tableModel);
                view.setTableModel(tableModel);
                view.setScrollPane(tableModel.getScrollPane());
                Log.DEBUG(TAG, "Table model generated successfully");
            });

            AsyncTask.execute(() -> {
                view.setupMainFrame();
                addListeners();
            });

        });
        return true;
    }

    private void addListeners() {
        setWindowListener();
        setKeyListener();
        setActionListeners();
    }

    private void setWindowListener() {
        if (view != null) {
            view.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    fireMainWindowClosed();
                }
            });
        }
    }


    private void setKeyListener() {
        if (view != null) {
            view.addKeyListener(this);
        }
    }

    private void setActionListeners() {
        if (view != null && view.getMenuItems().size() > 0) {
            ArrayList<JMenuItem> menuItems = new ArrayList<>();
            for (JComponent item : view.getMenuItems()) {
                if (item.getClass().equals(JMenuItem.class)) {  // excludes JSeparator objects
                    menuItems.add((JMenuItem) item);
                }
            }

            if (menuItems.size() > 0) {
                for (JMenuItem menuItem : menuItems) menuItem.addActionListener(this);
            }

            /*
            TODO add more functionality
             */
        }
    }

    private void fireMainWindowClosed() {
        Log.DEBUG("Exiting application");

        try {
            model.saveData();
        } catch (FileNotFoundException e) {
            Log.ERROR(e, e.getMessage());
            e.printStackTrace();
            Log.DEBUG("Skipping procedure...");
            System.exit(0);
            Log.DEBUG("Application terminated");
        }
    }

    private void setModel(@Nonnull KSPModManager model) {
        this.model = model;
    }

    @Nonnull
    public KSPModManager getModel() {
        return model == null ? new KSPModManager(new User()) : this.model;
    }

    private void setView(@Nonnull KSPMMMainView view) {
        this.view = view;
    }

    @Nonnull
    public KSPMMMainView getView() {
        return view == null ? new KSPMMMainView() : this.view;
    }


    /**
     * Invoked when an action occurs.
     *
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        String actionPerformed = "\033[33mAction performed: ";

        if (source instanceof JMenuItem) {
            JMenuItem menuItem = ((JMenuItem) source);

            /*
            Log.DEBUG(TAG, actionPerformed.concat("{\n\t{\"class\": \"" + source.getClass().getSimpleName()
                    + "\"},\n\t{\"action\": \"" + menuItem.getText().toLowerCase().replaceAll(" ", "_") +"\"}\n}\033[0m"));
                    */
            Log.DEBUG(TAG, actionPerformed.concat("Sender: "  + source.getClass().getSimpleName() + "\", Action: \""
                    + menuItem.getText().toLowerCase().replaceAll(" ", "_") + "\"") + " ");

            if (menuItem.equals(view.getFullscreenItem())) {
                AsyncTask.execute(() -> {
                    doFullscreen();
                    Log.DEBUG(TAG, "Fullscreen toggled");
                });
            }
        }
    }

    private void doFullscreen() {
        if (view.getFullscreenItem().getText().equals(ENTER_FULLSCREEN)) {
            view.getFullscreenItem().setText(EXIT_FULLSCREEN);
        } else {
            view.getFullscreenItem().setText(ENTER_FULLSCREEN);
        }

        if (IS_MAC) {
            MacOSXUtils.requestOSXFullScreen(view);
        } else {
            GraphicsDevice device = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
            device.setFullScreenWindow(view);
        }
    }

    /**
     * Invoked when a key has been typed.
     * See the class description for {@link KeyEvent} for a definition of
     * a key typed event.
     *
     * @param e
     */
    @Override
    public void keyTyped(KeyEvent e) {

    }

    /**
     * Invoked when a key has been pressed.
     * See the class description for {@link KeyEvent} for a definition of
     * a key pressed event.
     *
     * @param e
     */
    @Override
    public void keyPressed(KeyEvent e) {

    }

    /**
     * Invoked when a key has been released.
     * See the class description for {@link KeyEvent} for a definition of
     * a key released event.
     *
     * @param e
     */
    @Override
    public void keyReleased(KeyEvent e) {

    }
}
