package com.stealer.game;

import static org.lwjgl.glfw.GLFW.GLFW_KEY_DOWN;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_LEFT;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_RIGHT;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_UP;

import com.stealer.core.KeyboardInput;
import com.stealer.core.MouseInput;


public class Game {

private Player player;

    public Game() {
        player = new Player();
    }

    public void update(KeyboardInput keyboard, MouseInput mouse) {
        player.update(keyboard);
    }

    public void draw() {
        player.draw();
    }

    public void delete() {
        player.delete();
        player = null;
    }

}
