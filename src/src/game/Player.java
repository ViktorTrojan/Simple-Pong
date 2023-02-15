package src.game;

import java.awt.Color;
import java.awt.Graphics2D;

public class Player {

    public int UP;
    public int DOWN;

    public boolean firstUP = false, firstDOWN = false;
    public boolean pressingUP = false, pressingDOWN = false;

    // Player class holds paddle, score,...
    public int score;
    public Paddle paddle;
    public Color color;

    public Player(int x, int y, int w, int h, int UP, int DOWN) {
        score = 0;
        this.UP = UP;
        this.DOWN = DOWN;
        paddle = new Paddle(x, y, w, h);
    }

    public void draw(Graphics2D g2) {
        paddle.draw(g2);
//        g.setColor(color);
//        drawScore(g);
    }

    public void pressUP() {
        pressingUP = true;
        if (!pressingDOWN) {
            moveUP();
            return;
        }
        paddle.acc = Config.DECEL;
    }

    public void pressDOWN() {
        pressingDOWN = true;
        if (!pressingUP) {
            moveDOWN();
            return;
        }
        paddle.acc = Config.DECEL;
    }

    public void releaseUP() {
        pressingUP = false;
        if (pressingDOWN) {
            moveDOWN();
        } else {
            paddle.acc = Config.DECEL;
        }
    }

    public void releaseDOWN() {
        pressingDOWN = false;
        if (pressingUP) {
            moveUP();
        } else {
            paddle.acc = Config.DECEL;
        }
    }

    public void moveUP() {
        paddle.acc = Config.ACCEL;
        paddle.vel = -paddle.speed;
    }

    public void moveDOWN() {
        paddle.acc = Config.ACCEL;
        paddle.vel = +paddle.speed;
    }

    public void run() {
        paddle.run();
    }
}
