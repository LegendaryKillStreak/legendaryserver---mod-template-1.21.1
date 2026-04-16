package net.lksls.legendaryserver.ui;

public class UiParticle {
    public float x, y;
    public float vx, vy;
    public float ax, ay;
    public int life;
    public int type;

    public float tx = -1;
    public float ty = -1;
    public boolean hasTarget = false;

    public int size;
    public int color;

    public UiParticle(float x, float y, float vx, float vy, int life, int type, int size, int color) {
        this.x = x;
        this.y = y;
        this.vx = vx;
        this.vy = vy;
        this.life = life;
        this.type = type;
        this.size = size;
        this.color = color;
    }
}
