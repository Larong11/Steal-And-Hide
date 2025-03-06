package com.stealer.core;

import java.util.HashMap;
import java.util.Map;

import org.lwjgl.glfw.GLFW;

public class KeyboardInput {

private final Map<Integer, Boolean> keyPressed = new HashMap<>();
private final Map<Integer, Boolean> keyHold = new HashMap<>();
private final Map<Integer, Boolean> keyReleased = new HashMap<>();

    public KeyboardInput(long window) {
        GLFW.glfwSetKeyCallback(window, (win, key, scancode, action, mods) -> {
            if (action == GLFW.GLFW_PRESS) {
                keyPressed.put(key, true);
                keyHold.put(key, true);
            } else if (action == GLFW.GLFW_RELEASE) {
                keyReleased.put(key, true);
                keyHold.put(key, false);
            }
        });
    }
    

    // Returns true if the key is currently held
    public boolean isHold(int key) {
        return keyHold.getOrDefault(key, false);
    }
    public boolean isPressed(int key) {
        return keyPressed.getOrDefault(key, false);
    }
    public boolean isReleased(int key) {
        return keyReleased.getOrDefault(key, false);
    }

    public void deleteFrame() {
        keyPressed.clear();
        keyReleased.clear();
    }
}
