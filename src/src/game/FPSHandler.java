package src.game;

import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;

public class FPSHandler {

    public final static int UPS = 90;  // UPS = updates per second should be the same for everyone
    public static int FPS = 120; // FPS = frames per second depends on the monitor refresh rate
    public long lastTime;
    public final double ns = 1000000000d / (double) UPS;
    double delta;
    private Game game;

    public FPSHandler(Game g) {
        game = g;
        // fetch refreshrate from monitor
        if (FPS == 0) {
            FPS = GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices()[0].getDisplayMode().getRefreshRate();
        }

        delta = 0;
        lastTime = System.nanoTime();
    }

    // PUT YOUR UPDATE LOGIC HERE !
    private void update() {
        game.run();
    }

    // PUT YOUR RENDER LOGIC HERE !
    private void render(Graphics2D g2) {
        game.draw(g2);
    }

    public void run(Graphics2D g2) {
        long now = System.nanoTime();
        delta += (now - lastTime) / ns;
        lastTime = now;

        long dur = System.nanoTime();
        while (delta >= 1) { // updates get processed here
            update();
            delta--;
        }

        render(g2);
        //int latency = (int) ((System.nanoTime() - dur) / (1000000d)); // calcs the latency of the update and render process
        //sleep((int)(1000 / FPS - latency - 1));
        sleep(8);
    }

    public void sleep(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
        }
    }
}
