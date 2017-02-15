package Utils.OSUtils.MacOSX;

import Utils.Log;
import com.apple.mrj.MRJAboutHandler;
import com.apple.mrj.MRJPrefsHandler;
import com.apple.mrj.MRJQuitHandler;
import com.google.common.base.Preconditions;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.Method;

import static Constants.StrConstants.Messages.Debug.Actions.APP_EXIT;
import static Constants.StrConstants.Messages.Debug.Success.APP_TERMINATED;
import static Constants.StrConstants.Messages.JOptionPane.*;
import static Constants.StrConstants.SystemPreferences.MacOSX.*;
import static Constants.StrConstants.Tags.MAC_OSX_UTILS_TAG;
import static Constants.StrConstants.Tags.SYSTEM_TAG;
import static javax.swing.JOptionPane.INFORMATION_MESSAGE;

/**
 * @author Tyler Hostager
 * @version 2/14/17
 */
public class MacOSXUtils implements MRJAboutHandler, MRJQuitHandler, MRJPrefsHandler {
    private static final String TAG = MAC_OSX_UTILS_TAG;
    private JComponent parentComponent;

    public MacOSXUtils() {
        setParentComponent(null);
    }

    public MacOSXUtils(JComponent parentComponent) {
        setParentComponent(parentComponent);
    }

    @Override
    public void handleAbout() {
        JOptionPane.showMessageDialog(
                parentComponent, JOPTIONPANE_ABOUT,
                JOPTIONPANE_ABOUT, INFORMATION_MESSAGE
        );
    }

    @Override
    public void handlePrefs() throws IllegalStateException {
        JOptionPane.showMessageDialog(
                parentComponent, JOPTIONPANE_PREFS,
                JOPTIONPANE_PREFS, INFORMATION_MESSAGE
        );
    }

    @Override
    public void handleQuit() {
        JOptionPane.showMessageDialog(
                parentComponent, JOPTIONPANE_QUIT,
                JOPTIONPANE_QUIT, INFORMATION_MESSAGE
        );

        exitApplication();
    }

    private void exitApplication() {
        Log.DEBUG(TAG, APP_EXIT);

        // TODO add saving xml data method call here

        System.exit(0);
        Log.DEBUG(TAG, APP_TERMINATED);
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    public static void enableOSXFullscreen(Window window) {
        Log.DEBUG(TAG, "Enabling OSX Fullscreen...");
        Preconditions.checkNotNull(window);
        try {
            Class util = Class.forName(FULLSCREEN_UTILS);
            Class params[] = new Class[] { Window.class, Boolean.TYPE };
            Method method = util.getMethod("setWindowCanFullScreen", params);
            method.invoke(util, window, true);
            Log.DEBUG(TAG, "Fullscreen successfully enabled");
        } catch (Exception e) {
            Log.ERROR(TAG, "Unable to enable fullscreen");
            Log.ERROR(e, e.getMessage());
            Log.ERROR(TAG, "Skipping procedure");
        }
    }

    public static void configureOSXProperties() {
        try {
            Log.DEBUG(SYSTEM_TAG, "Configuring OSX system properties...");
            System.setProperty("apple.laf.useScreenMenuBar", "true");
            System.setProperty("apple.awt.application.name", "KSP Mod Manager");
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            System.setProperty("com.apple.mrj.application.apple.menu.about.name", "WikiTeX");
            System.setProperty("apple.awt.textantialiasing", "true");
            System.setProperty("apple.awt.graphics.EnableQ2DX", "false");
            System.setProperty("apple.awt.fileDialogForDirectories", "true");
            javax.swing.UIManager.getInstalledLookAndFeels();
            Log.DEBUG("Properties configured successfully");
        } catch (Exception e) {
            Log.ERROR(e, e.getMessage());
        }
    }

    public void setParentComponent(JComponent parentComponent) {
        this.parentComponent = parentComponent;
    }

    public JComponent getParentComponent() {
        return parentComponent;
    }
}

/*
package com.devdaily.opensource.jelly.controller;

import javax.swing.JOptionPane;
import com.apple.mrj.MRJAboutHandler;
import com.apple.mrj.MRJPrefsHandler;
import com.apple.mrj.MRJQuitHandler;

public class MacOSXController
implements MRJAboutHandler, MRJQuitHandler, MRJPrefsHandler
{

  public void handleAbout()
  {
    JOptionPane.showMessageDialog(null,
                                  "about",
                                  "about",
                                  JOptionPane.INFORMATION_MESSAGE);
  }

  public void handlePrefs() throws IllegalStateException
  {
    JOptionPane.showMessageDialog(null,
                                  "prefs",
                                  "prefs",
                                  JOptionPane.INFORMATION_MESSAGE);
  }

  public void handleQuit() throws IllegalStateException
  {
    JOptionPane.showMessageDialog(null,
                                  "quit",
                                  "quit",
                                  JOptionPane.INFORMATION_MESSAGE);
    // handle exit here
    // System.exit(0);
  }

}
 */