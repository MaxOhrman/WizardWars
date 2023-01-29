package entities.Monster;

import entities.GameObject;
import main.Animator;
import main.Handler;
import main.ID;
import main.SpriteSheet;

import java.awt.*;

public class Slime extends GameObject {

    private Handler handler;
    Animator animator;

    public Slime(int x, int y, int width, int height, ID id, boolean enableCollision, SpriteSheet spriteSheet, Handler handler) {
        super(x, y, width, height, id, enableCollision, spriteSheet);
        this.handler = handler;
        this.animator = new Animator(800);
        animator.addSprite(spriteSheet.getSprite(3, 7, 32, 32));
        animator.addSprite(spriteSheet.getSprite(2, 7, 32, 32));
        animator.addSprite(spriteSheet.getSprite(1, 7, 32, 32));
    }

    @Override
    public void tick() {
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(animator.animateArray( (int)velX, (int)velY, false), x,y,null);

    }

    @Override
    public Rectangle getBounds() {
        int offset = 4; //Increased offset means a smaller collision box relative to object size
        return new Rectangle(x + offset, y + offset, width - (offset * 2), height - (offset * 2));
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
    private void collision() {
        for (int i = 0; i < handler.getObjectArray().size(); i++) {
            GameObject object = handler.getObjectArray().get(i);

            if (object.collisionEnabled()) {
                if (getBounds().intersects(object.getBounds())) {

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
}
