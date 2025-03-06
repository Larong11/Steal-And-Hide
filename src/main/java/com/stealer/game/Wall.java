package com.stealer.game;

import static com.stealer.core.Rendering.drawQuad;

public class Wall {
    
private Collision c;
private float[] pos, size;

    public Wall(float[] pos, float[] size) {
        this.pos = pos;
        this.size = size;
        c = new Collision(this.pos, this.size);
    }
    public Wall(float x, float y, float width, float height) {
        this.pos = new float[] {x, y};
        this.size = new float[] {width, height};
        c = new Collision(this.pos, this.size);
    }

    public void draw(boolean drawCollision) {
        drawQuad(pos, size);
        if (drawCollision) {
            c.draw();
        }
    }

    public Collision getCollision() {
        return this.c;
    }

    public void delete() {
        this.c.delete();
        this.c = null;
        this.pos = null;
        this.size = null;
    }
}
