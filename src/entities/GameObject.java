package entities;

import main.ID;

import java.awt.*;

/**
 * This will be the parent of all
 * gameObjects we create.
 */
public abstract class GameObject {

    protected int x, y;
    protected float velX = 0, velY = 0;
    protected ID id;
    protected boolean hasCollision;
    protected int width, height;
    protected boolean isAlive;

    public GameObject(int x, int y, int width, int height, ID id, boolean enableCollision) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.id = id;
        this.hasCollision = enableCollision;
        this.isAlive = false;
    }

    public abstract void tick();
    public abstract void render(Graphics g);
    public abstract Rectangle getBounds();

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public float getVelX() {
        return velX;
    }

    public void setVelX(float velX) {
        this.velX = velX;
    }

    public float getVelY() {
        return velY;
    }

    public void setVelY(float velY) {
        this.velY = velY;
    }

    public ID getId() {
        return id;
    }

    public void setId(ID id) {
        this.id = id;
    }

    public boolean collisionEnabled() {
        return hasCollision;
    }

    public boolean isHasCollision() {
        return hasCollision;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }
}
