package Controllers;

import Models.KSPMMTableModel;
import Models.KSPModManager;
import Objects.Users.User;
import Tasks.AsyncTask;
import UserInterface.KSPMMMainView;
import Utils.Log;

import javax.annotation.Nonnull;
import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileNotFoundException;

import static Constants.StrConstants.Messages.Debug.InitializationMsgs.GENERATE_TABLE_MODEL;
import static Constants.StrConstants.generateTagForName;

/**
 * <p>The primary middleware controller delegate between model and UI objects.</p>
 *
 * @author Tyler Hostager
 * @version 2/13/17
 */
public class MainViewController {
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
    }

    private void setWindowListener() {
        view.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                fireMainWindowClosed();
            }
        });
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


}
