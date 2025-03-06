package com.stealer.gui;

import static com.stealer.core.Rendering.drawQuad;

public class Button {
private float x, y;
private float height, width;
private float[] color3fv;

    public Button(float x, float y, float width, float height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public Button(float x, float y, float width, float height, float[] color3fv) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.color3fv = color3fv;
    }

    public void draw() {
        drawQuad(new float[] {x, y}, new float[] {width, height}, color3fv);
    }

    public boolean isClicked(float[] pos) {
        return this.x - this.width / 2 <= pos[0] && pos[0] <= this.x + this.width / 2 &&
             this.y - this.height / 2 <= pos[1] && pos[1] <= this.y + this.height / 2;
    }
}
