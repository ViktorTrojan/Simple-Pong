package src.game;

import java.awt.Color;
import java.awt.Graphics2D;

public class StartGameCounter {

    int stepCounter = 0;
    boolean counterStarted = false;

    public StartGameCounter() {
        stepCounter = 0;
        counterStarted = false;
    }

    public void startCounter() {
        counterStarted = true;
    }

    public boolean run() {
        if (counterStarted) {
            stepCounter++;
        }
        if (stepCounter >= 360) { //TODO: change 1 to 360
            stepCounter = 0;
            counterStarted = false;
            return true;
        }
        return false;
    }

    public void draw(Graphics2D g2) {
        if (!counterStarted) {
            return;
        }
        // draw a border around it
        g2.setColor(new Color(0.4f, 0.4f, 0.4f, 0.5f));
        g2.fillRoundRect((int) (Game.w * 0.5f - 80), 90, 160, 90, 20, 20);

        g2.setColor(Config.primaryCol);

        if (stepCounter < 90) {
            Game.drawString(g2, "3", Game.w * 0.5f, 80, 80);
        } else if (stepCounter >= 90 && stepCounter < 180) {
            Game.drawString(g2, "2", Game.w * 0.5f, 80, 80);
        } else if (stepCounter >= 180 && stepCounter < 270) {
            Game.drawString(g2, "1", Game.w * 0.5f, 80, 80);
        } else if (stepCounter >= 270 && stepCounter < 360) {
            Game.drawString(g2, "GO!", Game.w * 0.5f, 80, 80);
        }
    }

}
