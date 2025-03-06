package com.stealer.scenes;

import static org.lwjgl.glfw.GLFW.GLFW_KEY_SPACE;

import com.stealer.core.KeyboardInput;
import com.stealer.core.MouseInput;
import com.stealer.core.SceneManager.State;
import static com.stealer.core.Rendering.*;

public class sceneInGame extends Scene {
double[] array;
    public sceneInGame(State state) {
        super(state);
        array = new double[100_000_000];
    }

    @Override
    public State update(KeyboardInput keyboard, MouseInput mouse) {
        if (keyboard.isPressed(GLFW_KEY_SPACE)) {
            curState = State._main_menu_;
        }
        if (mouse.isLeftPressed()) {

        }
        return curState;
    }

    @Override
    public void render() {
        drawQuad(0, 0, 0.5f, 0.5f, 1, 0, 0);
    }

    @Override
    public void delete() {
        array = null;
    }
}
