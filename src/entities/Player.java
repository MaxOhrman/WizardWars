package entities;

import java.awt.*;

public class Player extends GameObject{

    Handler handler;


//    public Player(int x, int y, int width, int height, ID id, Handler handler, boolean enableCollision) {
//        super(x, y, width, height, id, enableCollision);
//        this.handler = handler;
//    }


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
        x += velX;
        y += velY;

        collision();

        //Player movement
        if(handler.isUp()) {
            velY = -5;
        } else if (!handler.isDown()) {
            velY = 0;
        }

        if(handler.isDown()) {
            velY = 5;
        } else if (!handler.isUp()) {
            velY = 0;
        }

        if(handler.isRight()) {
            velX = 5;
        } else if (!handler.isLeft()) {
            velX = 0;
        }

        if(handler.isLeft()) {
            velX = -5;
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

                    x += velX * -1;
                    y += velY * -1;

                    switch (getObjectDirection(object, 6)) {
                        case "north" -> handler.setUp(false);
                        case "south" -> handler.setDown(false);
                        case "west" -> handler.setLeft(false);
                        case "east" -> handler.setRight(false);
                    }

                }
            }
        }
    }

    /**
     * //TODO Add Documentation
     * @param object
     * @param distance
     * @return
     */
    private String getObjectDirection(GameObject object, int distance) {

        int southDistance = object.getY() - (this.y + this.height);
        int northDistance = (this.y + (height % 32)) - (object.getY() + object.height);
        int westDistance = (object.getX() + object.width) - this.x;
        int eastDistance = object.getX() - (this.x + this.width);

        if ((northDistance > -distance) && (northDistance < distance)) {
            System.out.println("North");
            return "north";
        }

        if ((southDistance > -distance) && (southDistance < distance)) {
            System.out.println("south");
            return "south";
        }

        if((westDistance > -distance) && (westDistance < distance)) {
            System.out.println("west");
            return "west";
        }

        if((eastDistance > -distance) && (eastDistance < distance)) {
            System.out.println("east");
            return "east";
        }

        return "null";
    }


    @Override
    public void render(Graphics g) {
        g.setColor(Color.blue);
        g.fillRect(x,y, width, height);
    }

    @Override
    public Rectangle getBounds() {
        int offset = height % 32;
        return new Rectangle(x,y+offset-1,width,height-offset+1);
    }
}
