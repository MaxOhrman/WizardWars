package main;

import entities.GameObject;
import entities.Player;

import java.awt.*;
import java.util.LinkedList;

/**
 * The Handlers purpose is to make sure
 * every object or entity we add to the game
 * gets updated and rendered at the game loops
 * set rate for tick() and render() speed
 */

public class Handler {

    private LinkedList<GameObject> object = new LinkedList<>();
    private LinkedList<GameObject> projectiles = new LinkedList<>();
    private Player player = null;

    private boolean up = false, down = false, right = false, left = false;

    //TODO Add player exist boolean instead of several loops doing the same in different files

    /**
     * Iterate through every GameObject in object
     * and run its tick() method
     */
    public void tick() {
        //Tick for objects
        for (GameObject tempObject : object) {
            tempObject.tick();
        }

        //Tick for projectiles
        for (GameObject projectile : projectiles) {
            if (!projectile.isAlive()) {
                projectiles.remove(projectile);
                break;
            }
            if(projectile.isAlive()) {
                projectile.tick();
                break;
            }
        }

        //Tick for player
        if (this.player != null) {
            player.tick();
        }
    }

    /**
     * Iterate through every GameObject in object
     * and run its render() method
     */
    public void render(Graphics g) {
        //Render for objects

        for (GameObject object : object) {
            object.render(g);
        }

        //Render Projectiles

        for (GameObject projectile : projectiles) {
            projectile.render(g);
        }

        //Render for player
        if (this.player != null) {
            player.render(g);
        }
    }

    public void addObject(GameObject tempObject) {
        object.add(tempObject);
    }

    public void addPlayer(Player player) {
        this.player = player;
    }

    public boolean playerExist() {
        return player != null;
    }

    public Player getPlayer() {
        return player;
    }

    public void addProjectile(GameObject tempObject) {
        projectiles.add(tempObject);
    }

    public void removeObject(GameObject tempObject) {
        object.remove(tempObject);
    }

    public boolean isUp() {
        return up;
    }

    public void setUp(boolean up) {
        this.up = up;
    }

    public boolean isDown() {
        return down;
    }

    public void setDown(boolean down) {
        this.down = down;
    }

    public boolean isRight() {
        return right;
    }

    public void setRight(boolean right) {
        this.right = right;
    }

    public boolean isLeft() {
        return left;
    }

    public void setLeft(boolean left) {
        this.left = left;
    }

    //Return the array of objects in the game
    public LinkedList<GameObject> getObjectArray() {
        return this.object;
    }

}
