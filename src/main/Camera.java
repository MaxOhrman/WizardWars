package main;

import entities.GameObject;

public class Camera {

    private float x, y;
    private final Game game;

    public Camera(float x, float y, Game game) {
        this.x = x;
        this.y = y;
        this.game = game;
    }

    /**
     * Lock coordinates onto our character
     *  we take the Players x and y coordinates
     *  and decrease them by half the screen size
     *  to center the camera on the player
     *
     * we smoothen the screen by adding a partial of
     * the preferred Camera x/y position each tick
     * to "zoom" in on the player
     */
    public void tick(GameObject object) {
        x += ((object.getX() - x) - game.getWidth()/2f) * 0.3f;
        y += ((object.getY() - y) - game.getHeight()/2f) * 0.3f;

        /*
        TODO stop camera from moving out of levels
          if (x < = 0) x= 0
          or maybe we allow it.
         */

    }


    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

}
