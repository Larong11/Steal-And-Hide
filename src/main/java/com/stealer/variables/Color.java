package com.stealer.variables;

public class Color {
    public static float[] 
    RED = new float[] {1, 0, 0},
    GREEN = new float[] {0, 1, 0},
    BLUE = new float[] {0, 0, 1},
    YELLOW = new float[] {1, 1, 0},
    CYAN = new float[] {0, 1, 1},
    MAGENTA = new float[] {1, 0, 1},
    WHITE = new float[] {1, 1, 1},
    BLACK = new float[] {0, 0, 0};
    public static float[] getColor(float r, float g, float b) {
        return new float[] {r, g, b};
    }
}
