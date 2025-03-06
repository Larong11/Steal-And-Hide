package com.stealer.game;

import static com.stealer.core.Rendering.drawQuad;

public class Collision {

public enum collideFrom {
    NONE(1), TOP(2), RIGHT(4), BOTTOM(8), LEFT(16),
    TOP_RIGHT(add(TOP, RIGHT)), TOP_LEFT(add(TOP, LEFT)),
    BOTTOM_RIGHT(add(BOTTOM, RIGHT)), BOTTOM_LEFT(add(BOTTOM, LEFT));

    private int id;
    private collideFrom(int x) {
        this.id = x;
    }
    private static int add(collideFrom a, collideFrom b) {
        return a.id + b.id;
    }
    public static int add(int a, collideFrom b) {
        return a + b.id;
    }
    public int getVal() {
        return this.id;
    }
}

private float[] pos, size;

    public Collision(float[] pos, float[] size) {
        this.pos = pos;
        this.size = size;
    } 
    public void update(float[] pos, float[] size) {
        this.pos = pos;
        this.size = size;
    }

    public float top() {
        return pos[1] + size[1] / 2f;
    }
    public float bottom() {
        return pos[1] - size[1] / 2f;
    }
    public float right() {
        return pos[0] + size[0] / 2f;
    }
    public float left() {
        return pos[0] - size[0] / 2f;
    }


    public void draw(float[] color3fv) {
        drawQuad(pos, size, color3fv);
    }
    public void draw() {
        draw(new float[] {0, 0.5f, 0});
    }

    public static int check(Collision a, float[] speedA, Collision b, float[] speedB) {
        int touchedFrom = collideFrom.NONE.getVal();

        if (a.left() < b.right() && a.right() > b.left()) {
            if (speedA[1] - speedB[1] != 0) {
                float tTop = (b.bottom() - a.top()) / (speedA[1] - speedB[1]);
                float tBot = (b.top() - a.bottom()) / (speedA[1] - speedB[1]);
                if (0 <= tTop && tTop < 1) {
                    touchedFrom = collideFrom.add(touchedFrom, collideFrom.TOP);
                }
                if (0 <= tBot && tBot < 1) {
                    touchedFrom = collideFrom.add(touchedFrom, collideFrom.BOTTOM);
                }
            }
        }
        if (a.top() > b.bottom() && a.bottom() < b.top()) {
            if (speedA[0] - speedB[0] != 0) {
                float tLeft = (a.left() - b.right()) / (speedA[0] - speedB[0]);
                float tRight = (a.right() - b.left()) / (speedA[0] - speedB[0]);
                if (0 <= tLeft && tLeft < 1) {
                    touchedFrom = collideFrom.add(touchedFrom, collideFrom.LEFT);
                }
                if (0 <= tRight && tRight < 1) {
                    touchedFrom = collideFrom.add(touchedFrom, collideFrom.RIGHT);
                }
            }
        }
        return touchedFrom;
    }

    public static int check(Collision a, float[] speedA, Collision b) {
        return check(a, speedA, b, new float[] {0, 0});
    }

    public static int check(Collision a, Collision b) {
        return check(a, new float[] {0, 0}, b, new float[] {0, 0});
    }

    public void delete() {
        pos = null;
        size = null;
    }
}
