package com.stealer.scenes;

import com.stealer.core.KeyboardInput;
import com.stealer.core.MouseInput;
import com.stealer.core.SceneManager.State;

public class Scene {

protected State curState;

    public Scene(State state) {
        this.curState = state;
    }

    public State update(KeyboardInput keyboard, MouseInput mouse) {
        return curState;
    }

    public void render() {

    }

    public void delete() {
        curState = null;
    }
}
