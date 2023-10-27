package com.tecsj.items;

import java.awt.*;
import java.util.Random;

public class Ball {
    private int x;     // x-coordinate of the ball
    private int y;     // y-coordinate of the ball
    private double vx;    // x-velocity of the ball
    private int vy;    // y-velocity of the ball
    private int radius; // radius of the ball
    private Color color; // color of the ball

    public static final int X_DEFAULT = 0;
    public static final int Y_DEFAULT = 0;
    public static final int VX_DEFAULT = 0;
    public static final int VY_DEFAULT = 4;
    public static final int RADIUS_DEFAULT = 10;

    public static final Color COLOR_DEFAULT = Color.green;


    public Ball() {
        this.x = X_DEFAULT;
        this.y = Y_DEFAULT;
        this.vx = VX_DEFAULT;
        this.vy = VY_DEFAULT;
        this.radius = RADIUS_DEFAULT;
        this.color = COLOR_DEFAULT;
    }

    public void draw(Graphics g) {
        g.setColor(color);
        g.fillOval(x - radius, y - radius, 2 * radius, 2 * radius);
    }

    public void move(int screenWidth, int screenHeight) {
        // Update the ball's position based on vectors vx and vy
        x += vx;
        y += vy;

        // Detect collisions with screen boundaries
        if (x < 0 || x > screenWidth - radius * 2) {
            // Ball hit the left or right boundary, reverse the x vector
            vx = -vx;
        }
        if (y < 0 || y > screenHeight - radius * 2) {
            // Ball hit the top or bottom boundary, reverse the y vector
            vy = -vy;
        }

        // You can add more complex collision logic if needed

        // Ensure the ball stays within the screen boundaries
        x = Math.max(0, Math.min(x, screenWidth - radius * 2));
        y = Math.max(0, Math.min(y, screenHeight - radius * 2));
    }

    public void checkCollisionWithRacket(Racket racket,int[] points) {
        Rectangle ballRect = new Rectangle(x - radius, y - radius, radius * 2, radius * 2);
        Rectangle racketRect = new Rectangle(racket.getX(), racket.getY(), racket.getWidth(), racket.getHeight());

        if (ballRect.intersects(racketRect)) {
            points[0] += 1;
            // Ball collides with the racket, reverse the y-velocity
            vy = -vy;

            // Adjust the x-velocity based on the ball's position relative to the racket
            int ballCenterX = x;
            int racketCenterX = racket.getX() + racket.getWidth() / 2;
            int offsetX = ballCenterX - racketCenterX;

            // Add a small random factor to vx to avoid a straight-line rebound
            Random rand = new Random();
            vx = (offsetX / 20) + rand.nextInt(5) - 2; // Adjust this factor as needed
        }
    }


    // Getter and setter methods for x, y, vx, vy

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

    public double getVx() {
        return vx;
    }

    public void setVx(int vx) {
        this.vx = vx;
    }

    public int getVy() {
        return vy;
    }

    public void setVy(int vy) {
        this.vy = vy;
    }

    public int getRadius() {
        return radius;
    }
}

