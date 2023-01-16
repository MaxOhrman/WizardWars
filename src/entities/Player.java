package entities;

import java.awt.*;

public class Player extends GameObject{

    Handler handler;


    public Player(int x, int y, int width, int height, ID id, Handler handler, boolean enableCollision) {
        super(x, y, width, height, id, enableCollision);
        this.handler = handler;
    }

    /**
     * Player Movement
     *
     * Handler has a set of booleans this
     * code acts upon. Those booleans are
     * controlled by the methods keyPressed
     * and keyReleased in KeyboardInputs class
     *
     * The following code is written in a way
     * to smoothen the Player movement
     *
     * example: w/up is held down.
     * As soon as we release it
     * else if (!handler.isDown())
     * becomes true at the next tick
     * we set our velocity in that
     * direction to 0.
     *
     */
    @Override
    public void tick() {
        int vel = 5;
        x += velX;
        y += velY;

        collision();

        //Player movement
        if(handler.isUp()) {
            velY = -vel;
        } else if (!handler.isDown()) {
            velY = 0;
        }

        if(handler.isDown()) {
            velY = vel;
        } else if (!handler.isUp()) {
            velY = 0;
        }

        if(handler.isRight()) {
            velX = vel;
        } else if (!handler.isLeft()) {
            velX = 0;
        }

        if(handler.isLeft()) {
            velX = -vel;
        } else if (!handler.isRight()) {
            velX = 0;
        }
    }

    /**
     * We iterate through every object added to the level
     * and if our objects Rectangle intersect with any
     * item in the array of objects we have a collision
     *
     * We try to smoothen out movements against walls using
     * getObjectDirection method to stop movement in
     * the direction of the object we are getting stuck on
     */
    private void collision() {
        for(int i = 0; i < handler.getObjectArray().size(); i++) {
            GameObject object = handler.getObjectArray().get(i);

            if (object.hasCollision && this != object) {
                if(getBounds().intersects(object.getBounds())) {

                    alterMovement(object);

                    x += velX * -1;
                    y += velY * -1;

                }
            }
        }
    }

    /**
     * //
     *
     * We find what side this intersects with the object and stop
     * the directional movement in that direktion by
     * changing the Handler set direction that
     * this tick() method uses to set velocity of the player
     *
     * @param object The object we want to compare our sides too
     */
    private void alterMovement(GameObject object) {

        int distance = 6;
        int southDistance = object.getBounds().y - (getBounds().y + getBounds().height);
        int northDistance = (getBounds().y) - (object.getBounds().y + object.getBounds().height);
        int westDistance = (object.getBounds().x + object.getBounds().width) - getBounds().x;
        int eastDistance = object.getBounds().x - (getBounds().x + getBounds().width);

        if ((northDistance > -distance) && (northDistance < distance)) {
            //North side collision
            handler.setUp(false);
        }

        if ((southDistance > -distance) && (southDistance < distance)) {
            //South side collision
            handler.setDown(false);
        }

        if((westDistance > -distance) && (westDistance < distance)) {
            //West side collision
            handler.setLeft(false);
        }

        if((eastDistance > -distance) && (eastDistance < distance)) {
            //East side collision
            handler.setRight(false);
        }

    }


    @Override
    public void render(Graphics g) {
        g.setColor(Color.blue);
        g.fillRect(x,y, width, height);
    }

    /**
     *
     * We create a rectangle representing the size of
     * this collision box
     *
     * we also offset the start x and y relative to the player
     * for a smoother experience
     *
     * @return the rectangle that we consider is the bounds
     * of the player
     *
     */
    @Override
    public Rectangle getBounds() {
        int offset = 4;

        return new Rectangle(x+offset, y+offset, width-(offset*2), height-(offset*2));
    }
}
