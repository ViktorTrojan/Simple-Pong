package src.game;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {

    private Player p1, p2;

    public KeyHandler(Player p1, Player p2) {
        this.p1 = p1;
        this.p2 = p2;
    }
    
    void paddleKeyPressed(KeyEvent e, Player p) {
        if (!(p.pressingUP && p.pressingDOWN)) { // if player is not pressing up AND down
            if (!p.pressingUP && e.getKeyCode() == p.UP) { // if player is not already pressing up
                p.pressUP();
                return;
            }
            if (!p.pressingDOWN && e.getKeyCode() == p.DOWN) { // if player is not already pressing down
                p.pressDOWN();
                return;
            }
        }
    }

    void paddleKeyReleased(KeyEvent e, Player p) {
        if (e.getKeyCode() == p.UP) {
            p.releaseUP();
        }
        if (e.getKeyCode() == p.DOWN) {
            p.releaseDOWN();
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        paddleKeyPressed(e, p1);
        paddleKeyPressed(e, p2);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        paddleKeyReleased(e, p1);
        paddleKeyReleased(e, p2);
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }
}
