package com.stealer.game;

import com.stealer.core.KeyboardInput;

import static com.stealer.core.Rendering.*;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_DOWN;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_LEFT;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_RIGHT;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_S;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_UP;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_W;

import java.util.Vector;

public class Player {

private float[] pos, size, speed, speedCol;
private Collision col;

    public Player() {
        pos = new float[] {0f, 0f};
        size = new float[] {0.1f, 0.1f};
        speed = new float[] {0f, 0f};
        speedCol = new float[] {0f, 0f};
        col = new Collision(pos, size);
    }

    public void draw(boolean drawCollision) {
        drawQuad(pos, size, new float[] {0, 0, 0});
        if (drawCollision) {
            col.draw();
        }
    }

    private void changeSpeed(KeyboardInput keyboard, float addSpeed) {

        if (keyboard.isPressed(GLFW_KEY_RIGHT)) {
            speed[0] += addSpeed;
        } else if (keyboard.isReleased(GLFW_KEY_RIGHT)) {
            speed[0] -= addSpeed;
            speedCol[0] = 0;
        }
        if (keyboard.isPressed(GLFW_KEY_LEFT)) {
            speed[0] -= addSpeed;
        } else if (keyboard.isReleased(GLFW_KEY_LEFT)) {
            speed[0] += addSpeed;
            speedCol[0] = 0;
        }
        if (keyboard.isPressed(GLFW_KEY_UP)) {
            speed[1] += addSpeed;
        } else if (keyboard.isReleased(GLFW_KEY_UP)) {
            speed[1] -= addSpeed;
            speedCol[1] = 0;
        }
        if (keyboard.isPressed(GLFW_KEY_DOWN)) {
            speed[1] -= addSpeed;
        } else if (keyboard.isReleased(GLFW_KEY_DOWN)) {
            speed[1] += addSpeed;
            speedCol[1] = 0;
        }
        if (keyboard.isPressed(GLFW_KEY_S)) {
            speed[0] -= addSpeed;
            speed[1] -= addSpeed;
        } else if (keyboard.isReleased(GLFW_KEY_S)) {
            speed[0] += addSpeed;
            speed[1] += addSpeed;
            speedCol[0] = 0;
            speedCol[1] = 0;
        }
        if (keyboard.isPressed(GLFW_KEY_W)) {
            speed[0] += addSpeed;
            speed[1] += addSpeed;
        } else if (keyboard.isReleased(GLFW_KEY_W)) {
            speed[0] -= addSpeed;
            speed[1] -= addSpeed;
            speedCol[0] = 0;
            speedCol[1] = 0;
        }
    }

    public void update(KeyboardInput keyboard, Vector<Wall> walls) {
        this.pos[0] = this.pos[0] + this.speed[0] + this.speedCol[0];
        this.pos[1] = this.pos[1] + this.speed[1] + this.speedCol[1];
        float addSpeed = 0.01f;
        changeSpeed(keyboard, addSpeed);
        this.speedCol[0] = 0;
        this.speedCol[1] = 0;
        for (Wall w : walls) {
            int x = Collision.check(col, speed, w.getCollision());
            if ((x & Collision.collideFrom.BOTTOM.getVal()) != 0) {
                if (this.speed[1] < 0) {
                    this.pos[1] = w.getCollision().top() + this.size[1] / 2f;
                    this.speedCol[1] = addSpeed;
                }
            }
            if ((x & Collision.collideFrom.TOP.getVal()) != 0) {
                if (this.speed[1] > 0) {
                    this.pos[1] = w.getCollision().bottom() - this.size[1] / 2f;
                    this.speedCol[1] = -addSpeed;
                }
            }
            if ((x & Collision.collideFrom.LEFT.getVal()) != 0) {
                if (this.speed[0] < 0) {
                    this.pos[0] = w.getCollision().right() + this.size[0] / 2f;
                    this.speedCol[0] = addSpeed;
                }
            }
            if ((x & Collision.collideFrom.RIGHT.getVal()) != 0) {
                if (this.speed[0] > 0) {
                    this.pos[0] = w.getCollision().left() - this.size[0] / 2f;
                    this.speedCol[0] = -addSpeed;
                }
            }
        }        
    }

    public void setSpeed(float[] speed) {
        this.speed = speed;
    }

    public void delete() {
        this.col.delete();
        this.speed = null;
        this.pos = null;
        this.size = null;
        this.col = null;
        this.speedCol = null;
    }
}
