package com.tecsj.GUI;

import com.tecsj.items.Ball;
import com.tecsj.items.Racket;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Pong extends JPanel {
    private Ball ball;           // The Ball object to be drawn
    private Racket playerRacket; // The player's racket
    private int screenWidth;     // Store screen dimensions
    private int screenHeight;

    private int[] points;

    // Define action names for key events
    private static final String MOVE_LEFT_ACTION = "moveLeft";
    private static final String MOVE_RIGHT_ACTION = "moveRight";

    private Timer timer;

    public Pong() {
        points = new int[1];
        points[0] = 0;
        ball = new Ball();
        playerRacket = new Racket();
        screenWidth = 400;
        screenHeight = 400;

        // Initialize the ball
        ball.setX(screenWidth / 2);
        ball.setY(screenHeight / 2);

        // Initialize the player's racket
        playerRacket.setX(screenWidth / 2 - playerRacket.getWidth() / 2);
        playerRacket.setY(getHeight() - playerRacket.getHeight() - 10);

        // Initialize the InputMap and ActionMap
        InputMap inputMap = getInputMap(WHEN_IN_FOCUSED_WINDOW);
        ActionMap actionMap = getActionMap();

        // Define the KeyStroke and associate it with action names
        KeyStroke leftKey = KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0);
        KeyStroke rightKey = KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0);

        inputMap.put(leftKey, MOVE_LEFT_ACTION);
        inputMap.put(rightKey, MOVE_RIGHT_ACTION);

        // Define the actions
        Action moveLeftAction = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle moving the racket left
                playerRacket.moveLeft(screenWidth);
            }
        };

        Action moveRightAction = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle moving the racket right
                playerRacket.moveRight(screenWidth);
            }
        };

        actionMap.put(MOVE_LEFT_ACTION, moveLeftAction);
        actionMap.put(MOVE_RIGHT_ACTION, moveRightAction);

        // Add a ComponentListener to detect window resizing
        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                // Update screenWidth and screenHeight when the component (window) is resized
                screenWidth = getWidth();
                screenHeight = getHeight();
                playerRacket.setY(screenHeight - playerRacket.getHeight() - 10);
            }
        });

        // Create a Timer to update the ball's position
        timer = new Timer(10, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Update the ball's position here
                ball.move(screenWidth, screenHeight);
                ball.checkCollisionWithRacket(playerRacket,points); // Check for collision with the racket
                // Check if the game is over
                if (isGameOver(screenHeight)) {
                    // Game over, stop the timer
                    timer.stop();

                    // Display a game-over message (you can use JOptionPane)
                    JOptionPane.showMessageDialog(Pong.this, ("Game Over!\n" + "Score : " + points[0]));

                    // Reset the game
                    resetGame();

                    // Start the game again
                    timer.start();
                }
                repaint(); // Request repaint to show updated position
            }
        });
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Draw the Ball
        ball.draw(g);

        // Draw the player's racket
        playerRacket.draw(g);
    }

    private void resetGame() {
        points[0] = 0;
        // Reset the ball's position to the initial position
        ball = new Ball();
        ball.setX(screenWidth / 2);
        ball.setY(screenHeight / 2);

        // Reset the racket's position to the initial position
        playerRacket.setX(screenWidth / 2 - playerRacket.getWidth() / 2);
        playerRacket.setY(screenHeight - playerRacket.getHeight() - 10);
    }

    public boolean isGameOver(int screenHeight) {
        return ball.getY() + ball.getRadius() >= screenHeight - (playerRacket.getHeight()-4); // Check if the ball's lower edge is below the screen
    }



}
