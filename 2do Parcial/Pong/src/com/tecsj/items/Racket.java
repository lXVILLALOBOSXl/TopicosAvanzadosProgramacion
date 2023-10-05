package com.tecsj.items;

import java.awt.*;

public class Racket {
    private int x;          // x-coordinate of the racket
    private int y;          // y-coordinate of the racket
    private int width;      // width of the racket
    private int height;     // height of the racket
    private Color color;    // color of the racket
    private int speed;      // speed of the racket

    // Constants for default values
    public static final int X_DEFAULT = 0;
    public static final int Y_DEFAULT = 0;
    public static final int WIDTH_DEFAULT = 100;
    public static final int HEIGHT_DEFAULT = 15;
    public static final Color COLOR_DEFAULT = Color.BLACK;
    public static final int SPEED_DEFAULT = 28;

    public Racket() {
        // Initialize with default values
        this.x = X_DEFAULT;
        this.y = Y_DEFAULT;
        this.width = WIDTH_DEFAULT;
        this.height = HEIGHT_DEFAULT;
        this.color = COLOR_DEFAULT;
        this.speed = SPEED_DEFAULT;
    }

    public void draw(Graphics g) {
        g.setColor(color);
        g.fillRect(x, y, width, height);
    }

    public void moveLeft(int screenWidth) {
        // Move the racket to the left (decrease x-coordinate)
        x -= speed;

        // Ensure the racket stays within the screen boundaries
        if (x < 0) {
            x = 0;
        }
    }

    public void moveRight(int screenWidth) {
        // Move the racket to the right (increase x-coordinate)
        x += speed;

        // Ensure the racket stays within the screen boundaries
        if (x + width > screenWidth) {
            x = screenWidth - width;
        }
    }

    // Getter and setter methods for x, y, width, height, and speed

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }
}
