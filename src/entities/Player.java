package entities;

import main.*;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Player extends GameObject{

    Handler handler;
    Exhaust exhausted;
    BufferedImage player_sprite;
    SpriteSheet spriteSheet;
    Game game;




    public Player(int x, int y, int width, int height, ID id, Handler handler, boolean enableCollision, SpriteSheet spriteSheet, Game game) {
        super(x, y, width, height, id, enableCollision, spriteSheet);
        this.handler = handler;
        this.exhausted = new Exhaust(600);
        this.spriteSheet = spriteSheet;
        this.player_sprite = spriteSheet.getSprite(1,1,32,32);
        this.game = game;
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
        int vel = 2;
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

            if (object.hasCollision) {
                if(getBounds().intersects(object.getBounds())) {

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

            if (handler.isDown()  || handler.isUp() &&
                handler.isRight() || handler.isLeft()) {
                x += velX;
            }
        }

        //West or East side collision
        if( (westDistance > -distance) && (westDistance < distance) ||
            (eastDistance > -distance) && (eastDistance < distance) ) {

            if (handler.isLeft()  || handler.isRight() &&
                handler.isUp()    || handler.isDown()) {
                y += velY;
            }
        }

    }

    public void castSpell(int mouseX, int mouseY) {
        if(!exhausted.isExhausted()) {
            handler.addProjectile(new Projectile(
                    x + (getWidth()/2),
                    y + (getWidth()/2),
                    8,8, ID.Projectile, handler,
                    false, mouseX, mouseY, spriteSheet));
            System.out.println("PlayerX:" + this.x  + " PlayerY: " + this.y);
            System.out.println("MouseX:" + mouseX + " mouseY: " + mouseY);
            System.out.println("GameWidth:" + game.getWidth() + " GameHeight: " + game.getHeight());
            exhausted.setExhausted();
        }
    }

    //gamewidth 1920, gameHeight 1009


    @Override
    public void render(Graphics g) {
        g.drawImage(player_sprite, x, y,null);

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
        int offset = 4; //Increased offset means a smaller collision box relative to object size
        return new Rectangle(x+offset, y+offset, width-(offset*2), height-(offset*2));
    }
}
