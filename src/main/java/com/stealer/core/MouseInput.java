package com.stealer.core;

import java.nio.IntBuffer;

import org.lwjgl.BufferUtils;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.system.MemoryStack;

public class MouseInput {
    private float xPos, yPos;
    private int leftButtonState = 0, rightButtonState = 0;
    public MouseInput(long window) {
        GLFW.glfwSetCursorPosCallback(window, (long win, double x, double y) -> {
            try (MemoryStack stack = MemoryStack.stackPush()) {
                IntBuffer widthBuffer = stack.mallocInt(1);
                IntBuffer heightBuffer = stack.mallocInt(1);
                GLFW.glfwGetWindowSize(window, widthBuffer, heightBuffer);
                xPos = ((float)x - (float)widthBuffer.get(0) / 2f) / ((float)widthBuffer.get(0) / 2f) * (float)widthBuffer.get(0) / (float)heightBuffer.get(0);
                yPos = ((float)heightBuffer.get(0) / 2f - (float)y) / ((float)heightBuffer.get(0) / 2f);
            }
        });

        GLFW.glfwSetMouseButtonCallback(window, (win, button, action, mods) -> {
            if (button == GLFW.GLFW_MOUSE_BUTTON_LEFT) {
                leftButtonState = action;
            } else if (button == GLFW.GLFW_MOUSE_BUTTON_RIGHT) {
                rightButtonState = action;
            }
        });
    }

    public double getX() { return xPos; }
    public double getY() { return yPos; }
    public float[] getPos() { return new float[] {xPos, yPos}; }
    public boolean isLeftPressed() { return leftButtonState == GLFW.GLFW_PRESS; }
    public boolean isRightPressed() { return rightButtonState == GLFW.GLFW_PRESS; }
    public void deleteFrame() {
        leftButtonState = -1;
        rightButtonState = -1;
    }
}
