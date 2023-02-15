package src.game;

public class Collision {

    public static boolean collideWallTop(float y) {
        return y <= 0;
    }

    public static boolean collideWallBottom(float y) {
        return y >= Game.h;
    }

    public static boolean collisionPaddle(Ball b, Paddle p) {
        return ((b.pos.x + b.size) > p.pos.x && b.pos.x < (p.pos.x + p.size.x) && (b.pos.y + b.size) > p.pos.y && b.pos.y < (p.pos.y + p.size.y));
    }
}
