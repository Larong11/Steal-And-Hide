package com.stealer.core;


import java.util.HashMap;
import java.util.Map;

import org.lwjgl.glfw.GLFW;

public class KeyboardInput {
    private final Map<Integer, Integer> keyStates = new HashMap<>();

    public KeyboardInput(long window) {
        GLFW.glfwSetKeyCallback(window, (win, key, scancode, action, mods) -> {
            keyStates.put(key, action);
        });
    }

    // Returns true if the key is currently held
    public boolean isPressed(int key) {
        return keyStates.getOrDefault(key, -1) == GLFW.GLFW_PRESS;
    }

    public boolean isReleased(int key) {
        return keyStates.getOrDefault(key, -1) == GLFW.GLFW_RELEASE;
    }

    public void deleteFrame() {
        keyStates.clear();
    }
}
