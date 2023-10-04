package com.tecsj;

import com.tecsj.GUI.Pong;

import javax.swing.*;

public class MyApp {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Pong Game");
            frame.setSize(400, 400);
            Pong pongPanel = new Pong();
            frame.add(pongPanel);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            frame.setVisible(true);
        });
    }
}
