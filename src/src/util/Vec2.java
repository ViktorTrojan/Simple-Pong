package src.util;

public class Vec2 {

    public float x, y;

    public Vec2() {
        x = 0;
        y = 0;
    }

    public Vec2(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return (int) x;
    }

    public int getY() {
        return (int) y;
    }

    public void set(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public void set(float val) {
        x = val;
        y = val;
    }
    
    public void set(Vec2 v) {
        x = v.x;
        y = v.y;
    }

    public void add(float val) {
        x += val;
        y += val;
    }

    public void sub(float val) {
        x -= val;
        y -= val;
    }

    public void mul(float val) {
        x *= val;
        y *= val;
    }

    public void div(float val) {
        x /= val;
        y /= val;
    }
    
    public void add(Vec2 v) {
        x += v.x;
        y += v.y;
    }
    
    public void sub(Vec2 v) {
        x -= v.x;
        y -= v.y;
    }
    
    public void mul(Vec2 v) {
        x *= v.x;
        y *= v.y;
    }
    
    public void div(Vec2 v) {
        x /= v.x;
        y /= v.y;
    }

    public static Vec2 add(Vec2 v1, Vec2 v2) {
        return new Vec2(v1.x + v2.x, v1.y + v2.y);
    }

    public static Vec2 sub(Vec2 v1, Vec2 v2) {
        return new Vec2(v1.x - v2.x, v1.y - v2.y);
    }

    public static Vec2 mul(Vec2 v1, Vec2 v2) {
        return new Vec2(v1.x * v2.x, v1.y * v2.y);
    }

    public static Vec2 div(Vec2 v1, Vec2 v2) {
        return new Vec2(v1.x / v2.x, v1.y / v2.y);
    }

    public void normalize() {
        float len = MH.len(x, y);
        x /= len;
        y /= len;
    }
    
    public void inverseX() {
        x = -x;
    }
    
    public void inverseY() {
        y = -y;
    }
    
    public void inverse() {
        x = -x;
        y = -y;
    }

    public static Vec2 normalize(float x, float y) {
        float len = MH.len(x, y);

        return new Vec2(x / len, y / len);
    }
    
    public float getAngle() {
        return MH.atan2(y, x);
    }
    
    public float getLen() {
        return MH.len(x, y);
    }
}
