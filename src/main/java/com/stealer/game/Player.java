package com.stealer.game;

import com.stealer.core.KeyboardInput;

import static com.stealer.core.Rendering.*;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_DOWN;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_LEFT;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_RIGHT;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_UP;

public class Player {

private float[] pos, size, speed;

    public Player() {
        pos = new float[] {0f, 0f};
        size = new float[] {0.1f, 0.1f};
        speed = new float[] {0f, 0f};
    }

    public void draw() {
        drawQuad(pos, size, new float[] {0, 0, 0});
    }

    private void changeSpeed(KeyboardInput keyboard, float addSpeed) {
        if (keyboard.isPressed(GLFW_KEY_RIGHT)) {
            speed[0] += addSpeed;
        } else if (keyboard.isReleased(GLFW_KEY_RIGHT)) {
            speed[0] -= addSpeed;
        }
        if (keyboard.isPressed(GLFW_KEY_LEFT)) {
            speed[0] -= addSpeed;
        } else if (keyboard.isReleased(GLFW_KEY_LEFT)) {
            speed[0] += addSpeed;
        }
        if (keyboard.isPressed(GLFW_KEY_UP)) {
            speed[1] += addSpeed;
        } else if (keyboard.isReleased(GLFW_KEY_UP)) {
            speed[1] -= addSpeed;
        }
        if (keyboard.isPressed(GLFW_KEY_DOWN)) {
            speed[1] -= addSpeed;
        } else if (keyboard.isReleased(GLFW_KEY_DOWN)) {
            speed[1] += addSpeed;
        }
    }

    public void update(KeyboardInput keyboard) {
        pos[0] = pos[0] + speed[0];
        pos[1] = pos[1] + speed[1];

        changeSpeed(keyboard, 0.03f);
    }

    public void setSpeed(float[] speed) {
        this.speed = speed;
    }

    public void delete() {
        this.speed = null;
        this.pos = null;
        this.size = null;
    }
}
