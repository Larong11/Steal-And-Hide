package com.stealer.core;

import com.stealer.scenes.*;

public final class SceneManager {
public enum State {
    _main_menu_,
    _in_game_
}
private Scene curScene;
private State curState;

    private void updateState(State newState) {
        if (this.curState == null) {
            this.curScene = new sceneInMainMenu(State._main_menu_);
        }
        if (this.curState != newState) {
            if (newState == State._main_menu_) {
                this.curScene.delete();
                this.curScene = new sceneInMainMenu(State._main_menu_);
            } 
            else if (newState == State._in_game_) {
                this.curScene.delete();
                this.curScene = new sceneInGame(State._in_game_);
            }
            System.gc();
            this.curState = newState;
        }
    } 

    public SceneManager(long window) {
        updateState(State._main_menu_);
    }

    public void render() {
        curScene.render();
    }

    public void update(KeyboardInput keyboard, MouseInput mouse) {
        updateState(curScene.update(keyboard, mouse));
    }
}
