package entities;

import main.Handler;
import main.ID;
import main.SpriteSheet;

import java.awt.*;
import java.util.Random;

/**
 * This will be the parent of all
 * gameObjects we create.
 */
public abstract class GameObject {

    private float maxHealth;
    private float maxMana;
    protected double x, y;
    protected double velX = 0, velY = 0;
    protected ID id;
    public boolean hasCollision;
    protected double width, height;
    protected boolean isAlive;
    protected SpriteSheet ss;
    protected boolean up = false, down = false, right = false, left = false;
    protected int tickCount;
    protected Handler handler;
    protected boolean isCreature;
    protected float currentHealth;
    protected float currentMana;

    public GameObject(double x, double y, double width, double height, ID id, boolean enableCollision, SpriteSheet spriteSheet, Handler handler) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.id = id;
        this.hasCollision = enableCollision;
        this.isAlive = false;
        this.ss = spriteSheet;
        this.handler = handler;
        this.isCreature = false;
        this.maxHealth = 1000;
        this.maxMana = 100;
        this.currentHealth = maxHealth;
        this.currentMana = maxMana;
    }

    public abstract void tick();

    public void render(Graphics g) {
        if (isCreature && isAlive) {
            int xOffset = ((int)this.getWidth()/6);
            int yOffset = ((int)this.getHeight()/8);
            int healthBarWidth = (int)this.getWidth() - xOffset*2;
            float percentHpLeft = currentHealth/maxHealth;



            //Outline
            g.setColor(new Color(35,35,35));
            g.fillRect((int)this.x + xOffset, (int)this.getY() - yOffset, healthBarWidth,3);

            //Health bar
            g.setColor((new Color(9, 91, 2)));
            g.fillRect((int)this.x + xOffset, (int)this.getY() - yOffset, (int) (healthBarWidth*percentHpLeft),3);
        }
    }

    public abstract Rectangle getBounds();

    protected void timedDirSetter(){
        tickCount++;

        if(tickCount > 90) {
            setRandomDirection();
            tickCount = 0;
        }
    }

    private void setRandomDirection() {
        Random random = new Random();
        int rand = random.nextInt(8);

        switch (rand) {
            case 0 -> {
                up = true;
                left = false;
                right = false;
                down = false;
            }
            case 1 -> {
                up = false;
                left = true;
                right = false;
                down = false;
            }
            case 2 -> {
                up = false;
                left = false;
                right = true;
                down = false;
            }
            case 3 -> {
                up = false;
                left = false;
                right = false;
                down = true;
            }
            default -> {
                up = false;
                left = false;
                right = false;
                down = false;
            }
        }

    }

    public void setUp(boolean up) {
        this.up = up;
    }

    public void setDown(boolean down) {
        this.down = down;
    }

    public void setRight(boolean right) {
        this.right = right;
    }

    public void setLeft(boolean left) {
        this.left = left;
    }

    public double getPlayerDistance(){
        double x1 = this.getX();
        double y1 = this.getY();

        double x2 = handler.getPlayer().getX();
        double y2 = handler.getPlayer().getY();

        double ac = Math.abs(y2 - y1);
        double cb = Math.abs(x2 - x1);

        return Math.hypot(ac, cb);
    }

    public double getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public double getVelX() {
        return velX;
    }

    public void setVelX(float velX) {
        this.velX = velX;
    }

    public double getVelY() {
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

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }

    /**
     * We iterate through every object added to the level
     * and if our objects Rectangle intersect with any
     * item in the array of objects we have a collision
     * <p>
     * We try to smoothen out movements against walls using
     * getObjectDirection method to stop movement in
     * the direction of the object we are getting stuck on
     */
    protected void collision() {
        for (int i = 0; i < handler.getObjectArray().size(); i++) {
            GameObject object = handler.getObjectArray().get(i);

            if (object.hasCollision && object != this) {
                if (getBounds().intersects(object.getBounds())) {

                    setDown(false);
                    setUp(false);
                    setRight(false);
                    setLeft(false);

                    modifyPosByVelocity(object);

                    x += velX * -1;
                    y += velY * -1;

                }
            }
        }

    }

    /**
     * We find what side this intersects with the object and
     * adjust the coordinate relative to velocity
     *
     * @param object The object we want to compare our sides too
     */
    private void modifyPosByVelocity(GameObject object) {

        int distance = 6; //Defines the range we allow of the matching sides eg -6 to 6.
        int southDistance = object.getBounds().y - (getBounds().y + getBounds().height);
        int northDistance = (getBounds().y) - (object.getBounds().y + object.getBounds().height);
        int westDistance = (object.getBounds().x + object.getBounds().width) - getBounds().x;
        int eastDistance = object.getBounds().x - (getBounds().x + getBounds().width);

        //South or North side collision
        if ((southDistance > -distance) && (southDistance < distance) ||
                (northDistance > -distance) && (northDistance < distance)) {

            if (handler.isDown() || handler.isUp() &&
                    handler.isRight() || handler.isLeft()) {
                x += velX;
            }
        }

        //West or East side collision
        if ((westDistance > -distance) && (westDistance < distance) ||
                (eastDistance > -distance) && (eastDistance < distance)) {

            if (handler.isLeft() || handler.isRight() &&
                    handler.isUp() || handler.isDown()) {
                y += velY;
            }
        }

    }

    public void randomWalk(double vel) {
        if (up) {
            velY = -vel;
        } else if (!down) {
            velY = 0;
        }

        if (down) {
            velY = vel;
        } else if (!up) {
            velY = 0;
        }

        if (left) {
            velX = vel;
        } else if (!right) {
            velX = 0;
        }

        if (right) {
            velX = -vel;
        } else if (!left) {
            velX = 0;
        }
    }

    public float getMaxHealth() {
        return maxHealth;
    }

    public void setMaxHealth(float maxHealth) {
        this.maxHealth = maxHealth;
    }

    public float getMaxMana() {
        return maxMana;
    }

    public void setMaxMana(float maxMana) {
        this.maxMana = maxMana;
    }

    public float getCurrentHealth() {
        return currentHealth;
    }

    public void setCurrentHealth(float currentHealth) {
        this.currentHealth = currentHealth;
    }

    public float getCurrentMana() {
        return currentMana;
    }

    public void setCurrentMana(float currentMana) {
        this.currentMana = currentMana;
    }
}
