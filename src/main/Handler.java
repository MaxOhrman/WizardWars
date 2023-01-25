package main;

import entities.GameObject;

import java.awt.*;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * The Handlers purpose is to make sure
 * every object or entity we add to the game
 * gets updated and rendered at the game loops
 * set rate for tick() and render() speed
 */

public class Handler {

    LinkedList<GameObject> object = new LinkedList<>();
    LinkedList<GameObject> projectiles = new LinkedList<>();

    private boolean up = false, down = false, right = false, left = false;

    //TODO Add player exist boolean instead of several loops doing the same in different files

    /**
     * Iterate through every GameObject in object
     * and run its tick() method
     */
    public void tick() {
        for (GameObject tempObject : object) {
            tempObject.tick();
        }

        for (GameObject projectile : projectiles) {
            projectile.tick();
            if (!projectile.isAlive()) {
                projectiles.remove(projectile);
                break;
            }
        }
    }

    /**
     * Iterate through every GameObject in object
     * and run its render() method
     */
    public void render(Graphics g) {
        Iterator<GameObject> iterator = object.iterator();

        while(iterator.hasNext()) {
            GameObject object = iterator.next();
            object.render(g);
        }

        //Render Projectiles
        Iterator<GameObject> projectileIterator = projectiles.iterator();

        while(projectileIterator.hasNext()) {
            GameObject projectile = projectileIterator.next();
            projectile.render(g);
        }
    }

    /**
     * Adding a object to our list of objects that
     * for handling
     */
    public void addObject(GameObject tempObject) {
        object.add(tempObject);
    }

    public void addProjectile(GameObject tempObject) {
        projectiles.add(tempObject);
    }

    /**
     * Removing object from our list of objects
     */
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
