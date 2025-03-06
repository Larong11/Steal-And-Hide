package com.stealer.game;

import java.util.Vector;

import com.stealer.core.KeyboardInput;
import com.stealer.core.MouseInput;


public class Game {
private Boolean drawCollision;
private Vector<Wall> walls;
private Player player;

    public Game() {
        this.player = new Player();
        this.walls = new Vector<Wall>();
        this.drawCollision = false;

        initGame();
    }

    private void initGame() {
        walls.addLast(new Wall(new float[] {0f, 0.5f}, new float[] {1f, 0.1f}));
        // walls.addLast(new Wall(new float[] {0.5f, 0f}, new float[] {0.1f, 1f}));
        walls.addLast(new Wall(new float[] {0f, -0.5f}, new float[] {1f, 0.1f}));
        walls.addLast(new Wall(new float[] {-0.5f, 0f}, new float[] {0.1f, 1f}));
    }

    public void update(KeyboardInput keyboard, MouseInput mouse) {
        this.player.update(keyboard, walls);
    }

    public void draw() {
        this.player.draw(drawCollision);
        for (Wall w : walls) {
            w.draw(drawCollision);
        }
    }
    public void changeViewCollision() {
        this.drawCollision = !this.drawCollision;
    }
    
    public void delete() {
        this.player.delete();
        this.player = null;
        for (Wall w : walls) {
            w.delete();
        }
        walls = null;
    }


}
