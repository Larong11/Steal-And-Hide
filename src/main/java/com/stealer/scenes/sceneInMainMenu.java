package com.stealer.scenes;

import static org.lwjgl.glfw.GLFW.GLFW_KEY_SPACE;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_UP;

import com.stealer.core.KeyboardInput;
import com.stealer.core.MouseInput;
import com.stealer.core.SceneManager.State;
import com.stealer.gui.Button;
import static com.stealer.core.Rendering.*;

public class sceneInMainMenu extends Scene {

private Button startNewGame;

    public sceneInMainMenu(State state) {
        super(state);
        startNewGame = new Button(0, 0, 0.75f, 0.3f, new float[] {0, 1, 1});
    }

    @Override
    public State update(KeyboardInput keyboard, MouseInput mouse) {
        if (keyboard.isPressed(GLFW_KEY_SPACE)) {
            curState = State._in_game_;
        }
        if (mouse.isLeftPressed()) {
            float[] pos = mouse.getPos();
            if (startNewGame.isClicked(pos)) {
                curState = State._in_game_;
            }
        }
        return curState;
    }

    @Override
    public void render() {
        drawQuad(0, 0, 2f, 1f, 0, 0, 1);
        startNewGame.draw();
    }

    @Override
    public void delete() {
        super.delete();
        startNewGame = null;
    }
}
