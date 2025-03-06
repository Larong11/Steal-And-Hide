package com.stealer.core;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;

import org.lwjgl.nanovg.NVGColor;
import org.lwjgl.nanovg.NanoVG;
import org.lwjgl.nanovg.NanoVGGL2;

import static org.lwjgl.nanovg.NanoVG.nvgBeginFrame;
import static org.lwjgl.nanovg.NanoVG.nvgEndFrame;
import static org.lwjgl.nanovg.NanoVG.nvgFillColor;
import static org.lwjgl.nanovg.NanoVG.nvgFontFace;
import static org.lwjgl.nanovg.NanoVG.nvgFontSize;
import static org.lwjgl.nanovg.NanoVG.nvgText;
import static org.lwjgl.nanovg.NanoVG.nvgTextBounds;

public class TextRenderer {
    protected float screen_height=800;
    protected float screen_width=500;
    long vg;
    private String fontName = "roboto"; // Имя шрифта


    public TextRenderer() {
        vg = NanoVGGL2.nvgCreate(NanoVG.NVG_ALIGN_BASELINE);
        loadFont();
    }
    private void loadFont() {
        String fontPath = "/Users/larong/Desktop/steal_and_hide/src/main/resources/fonts/Arial.ttf";
        int font = NanoVG.nvgCreateFont(vg, fontName, fontPath);
        if (font == -1) {
            System.out.println("fontPath");
        }
    }

    private float  calc( float max_width,float  max_height, String label, float x, float y){
        float fontSize = 60.0f; 
        float[] bounds = new float[4];

        while (true) {
            nvgFontSize(vg, fontSize);
            nvgTextBounds(vg, 0, 0,  label, bounds);

            float textWidth = bounds[2] - bounds[0]; // Ширина текста
            float textHeight = bounds[3] - bounds[1]; // Высота текста

            if (textWidth <= max_width && textHeight <= max_height) {
                break; // Текст помещается в заданные размеры
            }

            fontSize -= 1.0f; // Уменьшаем размер шрифта

            if (fontSize <= 0) {
                throw new RuntimeException("Не удалось подобрать размер шрифта для текста: " + label);
            }
        }
        return fontSize;
    }

    // public void update_text(String new_text){
    //     label=new_text;
    //     calc( max_width,  max_height);
    // }

    public void drawText(float x, float y, String label,float[] color, float max_height,float  max_width) {
        x = (int)((x+1)/2*screen_width);
        y =(int)((1-(y+1)/2) *screen_height);
        max_height=max_height/2*screen_height;
        max_width=max_width/2*screen_width;
        nvgBeginFrame(vg, screen_width, screen_height, 1); // Начинаем фрейм
        float fontSize = calc( max_width, max_height, label, x, y);
        nvgFontSize(vg, fontSize);
         nvgFontFace(vg, fontName); // Используем загруженный шрифт

        NVGColor color1 = NVGColor.create();
        color1.r(0.0f);  // Красный
        color1.g(0.0f);  // Зеленый
        color1.b(0.0f);  // Синий
        color1.a(1.0f);  // Альфа (непрозрачность)
        nvgFillColor(vg, color1);

        float[] bounds = new float[4];
        nvgTextBounds(vg, 0, 0, label, bounds);
        float textWidth = bounds[2] - bounds[0]; // Ширина текста
        float textHeight = bounds[3] - bounds[1]; // Высота текста


        nvgText(vg,  x-textWidth/2,  y+textHeight/2, label);
    
        nvgEndFrame(vg);
    }
}