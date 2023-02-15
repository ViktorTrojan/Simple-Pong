package src.game;

import java.awt.Color;
import java.awt.Graphics2D;
import src.util.MH;
import src.util.Vec2;

public class Paddle {
    private final float maxYVel = 2f; 
    public Vec2 pos, size;
    public int rounding = 6;
    public float vel, acc; // Y - Velocity and Acceleration
    public float speed = 0.1f;

    public Paddle(int x, int y, int w, int h) {
        this.pos = new Vec2(x, y);
        this.size = new Vec2(w, h);
        vel = 0;
        acc = 1;
    }
    
    public void run() {
        vel *= acc;
        // CLAMP yVel
        
        vel = MH.clamp(vel, -maxYVel, maxYVel);
        
        int margin = 10;
        pos.y = MH.clamp(pos.y + vel, 0 + margin, Game.h - size.y - margin);

//        if (Collision.collideWallTop(y)) {
//            y = 0;
//            y2 = y + HEIGHT;
//        }
//        if (Collision.collideWallBottom(y2)) {
//            y2 = 1080;
//            y = y2 - HEIGHT;
//        }
    }

    public void draw(Graphics2D g2) {
        g2.setColor(new Color(60, 60, 60));
        g2.fillRoundRect(pos.getX(), pos.getY(), size.getX(), size.getY(), rounding, rounding);
    }

}
