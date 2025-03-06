package com.stealer.core;

import java.nio.IntBuffer;

import org.lwjgl.BufferUtils;
import org.lwjgl.glfw.GLFW;

public class MouseInput {
    private double xPos, yPos;
    private int leftButtonState = 0, rightButtonState = 0;
    public MouseInput(long window) {
        GLFW.glfwSetCursorPosCallback(window, (long win, double x, double y) -> {
            IntBuffer widthBuffer = BufferUtils.createIntBuffer(1);
            IntBuffer heightBuffer = BufferUtils.createIntBuffer(1);
            GLFW.glfwGetWindowSize(window, widthBuffer, heightBuffer);
            xPos = (x - (float)widthBuffer.get(0) / 2f) / ((float)widthBuffer.get(0) / 2f) * (float)widthBuffer.get(0) / (float)heightBuffer.get(0);
            yPos = ((float)heightBuffer.get(0) / 2f - y) / ((float)heightBuffer.get(0) / 2f);
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
    public double[] getPos() { return new double[] {xPos, yPos}; }
    public boolean isLeftPressed() { return leftButtonState == GLFW.GLFW_PRESS; }
    public boolean isRightPressed() { return rightButtonState == GLFW.GLFW_PRESS; }
    public void deleteFrame() {
        leftButtonState = -1;
        rightButtonState = -1;
    }
}
