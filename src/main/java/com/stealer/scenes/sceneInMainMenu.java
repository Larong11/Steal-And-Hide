package com.stealer.scenes;

import static org.lwjgl.glfw.GLFW.GLFW_KEY_SPACE;

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
            System.out.println("Space pressed");
            curState = State._in_game_;
        }
        if (mouse.isLeftPressed()) {
        }
        return curState;
    }

    @Override
    public void render() {
        drawQuad(0, 0, 2f, 1f, 0, 0, 1);
        startNewGame.draw();
    }
}
