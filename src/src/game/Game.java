package src.game;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import javax.swing.JComponent;

public class Game extends JComponent {

    public FPSHandler fps;

    public static int w = 750, h = 450;
    final Color bgCol = new Color(229, 231, 245), scoreBgCol = new Color(0, 0, 0, 0.1f);

    // PLAYER 1 and 2
    public Player p1, p2;

    public Ball ball;
    public StartGameCounter gameCounter;

    public Game() {
        p1 = new Player(18, 165, 15, 120, KeyEvent.VK_W, KeyEvent.VK_S);
        p2 = new Player(714, 165, 15, 120, KeyEvent.VK_UP, KeyEvent.VK_DOWN);
        ball = new Ball();
        gameCounter = new StartGameCounter();
        gameCounter.startCounter();

        fps = new FPSHandler(this);
    }

    private void drawBg(Graphics2D g2) {
        int rounded = 40;
        g2.setColor(bgCol);
        g2.fillRoundRect(0, 0, w, h, rounded, rounded);
    }

    private void drawMiddleLine(Graphics2D g2) {
        float lineWidth = 8;
        Graphics2D g2_ = (Graphics2D) g2.create(); // so the basicstroke width doesnt influence other drawings
        g2_.setColor(new Color(160, 166, 180));
        BasicStroke bs1 = new BasicStroke(lineWidth, BasicStroke.CAP_SQUARE, BasicStroke.JOIN_ROUND, 0, new float[]{lineWidth * 3.2f}, 0);
        g2_.setStroke(bs1);
        g2_.drawLine(getWidth() / 2, 120, getWidth() / 2, getHeight() - 50);
    }

    public static void drawString(Graphics2D g2, String s, float x, float y, int size) { // always centered
        g2.setFont(new Font("Calibri", Font.PLAIN, size));
        FontMetrics fm = g2.getFontMetrics();
        int stringWidth = fm.stringWidth(s);
        x -= (stringWidth * 0.5f);
        g2.drawString(s, x, y + size);
    }

    private void drawScore(Graphics2D g2) {
        int rounded = 15;
        g2.setColor(scoreBgCol);
        g2.fillRoundRect(300, 12, 150, 60, rounded, rounded);

        // draw middle line indicator
        g2.setColor(new Color(130, 130, 130));
        g2.drawLine(300 + 75, 27, 300 + 75, 57);

        // draw Player1 Score
        drawString(g2, String.valueOf(p1.score), w * 0.5f - 35, 14, 40);

        // draw Player2 Score
        drawString(g2, String.valueOf(p2.score), w * 0.5f + 35, 14, 40);

        // draw underline leading indicator
        if (p1.score != p2.score) {
            g2.setColor(Config.primaryCol);
            g2.fillRoundRect(p1.score > p2.score ? 325 : 395, 60, 30, 3, 4, 4);
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        w = getWidth();
        h = getHeight();

        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        drawBg(g2);
        drawMiddleLine(g2);
        drawScore(g2);
        fps.run(g2);
        repaint();
    }

    public boolean collisionWall() { // for ball
        if (Collision.collideWallTop(ball.pos.y)) {
            ball.vel.y = -ball.vel.y;
            return true;
        }
        if (Collision.collideWallBottom(ball.pos.y + ball.size)) {
            ball.vel.y = -ball.vel.y;
            return true;
        }
        return false;
    }

    public boolean collisionPaddle() {
        float my = ball.pos.y + ball.size * 0.5f;

        if (Collision.collisionPaddle(ball, p1.paddle)) {
            //x = p1.x2;
            ball.speed *= 1.05;
            ball.SetDirection(1, ball.GetReflection(p1.paddle, my - p1.paddle.pos.y));
            return true;
        }

        if (Collision.collisionPaddle(ball, p2.paddle)) {
            //x = p2.x - SIZE;
            ball.speed *= 1.05;
            ball.SetDirection(-1, ball.GetReflection(p2.paddle, my - p2.paddle.pos.y));
            return true;
        }
        return false;
    }

    public boolean collisionCheck() {
        if (collisionWall()) {
           Sound.wallhit.play(70);
            return true;
        }

        if (collisionPaddle()) {
            Sound.paddlehit.play(70);
            return true;
        }
        return false;
    }
    
    public void checkWin() {
        if((ball.pos.x + ball.size) < 0) {
            p2.score++;
            ball.reset();
            gameCounter.startCounter();
        } else if(ball.pos.x > Game.w) {
            p1.score++;
            ball.reset();
            gameCounter.startCounter();
        }
    }

    public void run() {
        checkWin();
        collisionCheck();

        p1.run();
        p2.run();
        ball.run();
        if (gameCounter.run()) {
            ball.launchBall();
        }
    }

    public void draw(Graphics2D g2) {

        p1.draw(g2);
        p2.draw(g2);
        ball.draw(g2);
        gameCounter.draw(g2);
    }
}
