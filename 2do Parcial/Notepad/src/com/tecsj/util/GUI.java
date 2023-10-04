package com.tecsj.util;

import com.tecsj.notepad.NotePad;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class GUI {
    public static JMenuItem createMenuItem(JMenu parentMenu, String label, String iconFileName, ActionListener actionListener) {
        JMenuItem menuItem = new JMenuItem(label);
        // Load and set the icon
        Icon icon = new ImageIcon(NotePad.class.getResource(iconFileName));
        menuItem.setIcon(icon);
        parentMenu.add(menuItem);
        menuItem.addActionListener(actionListener);
        return menuItem;
    }

    public static JButton convertJMenuItemToJButton(JMenuItem menuItem) {
        JButton button = new JButton();
        button.setIcon(menuItem.getIcon());
        // Set the action command for the JButton to match the JMenuItem
        button.setActionCommand(menuItem.getActionCommand());

        // Copy the JMenuItem's action listeners to the JButton
        ActionListener[] actionListeners = menuItem.getActionListeners();
        for (ActionListener listener : actionListeners) {
            button.addActionListener(listener);
        }
        return button;
    }
}
