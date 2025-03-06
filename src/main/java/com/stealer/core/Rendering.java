package com.stealer.core;

import static org.lwjgl.opengl.GL40.*;

public class Rendering {
    public static void drawQuad(float x, float y, float width, float height) {
        glColor3f(0,0,0);
        glBegin(GL_QUADS);

        glVertex2f(x + width / 2f, y + height / 2f);
        glVertex2f(x + width / 2f, y - height / 2f);
        glVertex2f(x - width / 2f, y - height / 2f);
        glVertex2f(x - width / 2f, y + height / 2f);

        glEnd();
    }
    public static void drawQuad(float[] pos, float[] size) {
        glColor3fv(new float[] {0,0,0});
        glBegin(GL_QUADS);
        glVertex2f(pos[0] + size[0] / 2f, pos[1] + size[1] / 2f);
        glVertex2f(pos[0] + size[0] / 2f, pos[1] - size[1] / 2f);
        glVertex2f(pos[0] - size[0] / 2f, pos[1] - size[1] / 2f);
        glVertex2f(pos[0] - size[0] / 2f, pos[1] + size[1] / 2f);

        glEnd();
    }
    public static void drawQuad(float x, float y, float width, float height, float r, float g, float b) {
        glColor3f(r,g,b);
        glBegin(GL_QUADS);

        glVertex2f(x + width / 2f, y + height / 2f);
        glVertex2f(x + width / 2f, y - height / 2f);
        glVertex2f(x - width / 2f, y - height / 2f);
        glVertex2f(x - width / 2f, y + height / 2f);

        glEnd();
    }
    public static void drawQuad(float[] pos, float[] size, float[] color3f) {
        glColor3fv(color3f);
        glBegin(GL_QUADS);

        glVertex2f(pos[0] + size[0] / 2f, pos[1] + size[1] / 2f);
        glVertex2f(pos[0] + size[0] / 2f, pos[1] - size[1] / 2f);
        glVertex2f(pos[0] - size[0] / 2f, pos[1] - size[1] / 2f);
        glVertex2f(pos[0] - size[0] / 2f, pos[1] + size[1] / 2f);

        glEnd();
    }
}
