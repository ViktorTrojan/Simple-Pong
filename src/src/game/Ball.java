package src.game;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Path2D;
import java.util.ArrayList;
import src.util.MH;
import src.util.Vec2;

public class Ball {

    public final float defaultSpeed = 4;
    public int size = 25;
    public Vec2 pos, vel;
    public float speed = 4; // this speed increases in Game
    public Color col = Config.primaryCol;

    // trail //
    ArrayList<Vec2> trail;
    Color trailCol = Config.primaryCol;
    // trail //

    public void drawTrail(Graphics2D g2) {
        Graphics2D g2_ = (Graphics2D) g2.create(); // so it doesnt influence other drawings
        Vec2 lastPoint1 = new Vec2();
        Vec2 lastPoint2 = new Vec2();
        for (int i = 0; i < trail.size() - 1; i++) {

            float w = (float)i / (trail.size()-2) * (size*0.5f);
            float w1 = (float)(i+1) / (trail.size()-2) * (size*0.5f);

            // set The vector and calculate the normal vectors and normalize
            Vec2 v = new Vec2(trail.get(i + 1).x - trail.get(i).x, trail.get(i + 1).y - trail.get(i).y);
            v.set(v.y, -v.x);
            v.normalize();

            g2_.setColor(new Color(160, 50, 80));
            Path2D quad = new Path2D.Double();
            if (i == 0) {
                quad.moveTo(trail.get(i).x + v.x * w, trail.get(i).y + v.y * w);
                quad.lineTo(trail.get(i).x + -v.x * w, trail.get(i).y + -v.y * w);
            } else {
                quad.moveTo(lastPoint1.x, lastPoint1.y);
                quad.lineTo(lastPoint2.x, lastPoint2.y);
            }
            quad.lineTo(trail.get(i + 1).x + -v.x * w1, trail.get(i + 1).y + -v.y * w1);
            quad.lineTo(trail.get(i + 1).x + v.x * w1, trail.get(i + 1).y + v.y * w1);
            quad.closePath();

            
            g2_.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, i / 200f));
            // Fill the quad
            g2_.fill(quad);

            lastPoint1.set(trail.get(i + 1).x + v.x * w1, trail.get(i + 1).y + v.y * w1);
            lastPoint2.set(trail.get(i + 1).x + -v.x * w1, trail.get(i + 1).y + -v.y * w1);
        }
    }

//    public void drawTrail(Graphics2D g2) {
//        Graphics2D g2_ = (Graphics2D) g2.create(); // so the basicstroke width doesnt influence other drawings
//        for (int i = 0; i < trail.size() - 1; i++) {
//            int j = (trail.size() - 1 - i) * (20 / size); // to calculate Trail color
//            g2_.setColor(new Color(trailCol.getRed() - j, trailCol.getGreen() - j, trailCol.getBlue() - j));
//            g2_.setStroke(new BasicStroke(i + 1));
//            g2_.draw(new Line2D.Float(trail.get(i).x, trail.get(i).y, trail.get(i + 1).x, trail.get(i + 1).y));
//        }
//    }
    public Ball() {
        trail = new ArrayList<>();
        pos = new Vec2();
        vel = new Vec2();
        reset();
    }

    public void draw(Graphics2D g2) {
        drawTrail(g2);
        g2.setColor(col);
        g2.fillOval(pos.getX(), pos.getY(), size, size);
    }

    public void SetDirection(float newdirx, float newdiry) { // for vel
        Vec2 v = new Vec2(newdirx, newdiry);
        v.normalize();
        v.mul(speed);
        vel.set(v);
    }

    public float GetReflection(Paddle p, float hity) {
        // Make sure the hity variable is within the height of the paddle
        if (hity < 0) {
            hity = 0;
        } else if (hity > (p.size.y)) {
            hity = p.size.y;
        }

        // Everything above the center of the paddle is reflected upward
        // while everything below the center is reflected downward
        hity -= p.size.y * 0.5f;

        // Scale the reflection, making it fall in the range -2.0f to 2.0f
        return 2.0f * (hity / (p.size.y * 0.5f));
    }

    public void run() {
        trail.add(new Vec2(pos.x + size * 0.5f, pos.y + size * 0.5f));
        if (trail.size() > 60) { // trail-length
            trail.remove(0);
        }
        pos.add(vel);
        pos.set(pos.x, MH.clamp(pos.y, 0, Game.h - size));
    }

    public void setPosCenter() {
        pos.set(Game.w * 0.5f - size * 0.5f, Game.h * 0.5f - size * 0.5f);
    }

    public void reset() {
        trail.clear();
        speed = defaultSpeed;
        vel.set(0, 0);
        setPosCenter();
    }

    public void launchBall() {
        SetDirection(MH.random(-10f, 10f), MH.random(-1f, 1f)); // TODO: both values could be 0 and result in NaN at normalization
    }
}
