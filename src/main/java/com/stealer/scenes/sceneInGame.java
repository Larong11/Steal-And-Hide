package com.stealer.scenes;

import static org.lwjgl.glfw.GLFW.GLFW_KEY_SPACE;

import com.stealer.core.KeyboardInput;
import com.stealer.core.MouseInput;
import com.stealer.core.SceneManager.State;
import com.stealer.game.Game;

public class sceneInGame extends Scene {
    Game game;

    public sceneInGame(State state) {
        super(state);
        game = new Game();
    }

    @Override
    public State update(KeyboardInput keyboard, MouseInput mouse) {
        if (keyboard.isPressed(GLFW_KEY_SPACE)) {
            curState = State._main_menu_;
        }
        game.update(keyboard, mouse);
        return curState;
    }

    @Override
    public void render() {
        game.draw();
    }

    @Override
    public void delete() {
        super.delete();
        game.delete();
        game = null;
    }
}
