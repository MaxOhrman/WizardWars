package main;

import entities.GameObject;

public class Camera {

    private float x, y;

    public Camera(float x, float y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Lock coordinates onto our character
     * we take the Players x and y coordinates
     * and decrease them by half the screen size
     * to center the camera on the player
     * <p>
     * we smoothen the screen by adding a partial of
     * the preferred Camera x/y position each tick
     * to "zoom" in on the player
     */
    public void tick(GameObject object) {

        x = (float) (((object.getX() + 32.0) * 4.0) - 1920.0 / 2.0);
        y = (float) (((object.getY() + 32.0) * 4.0) - 1009.0 / 2.0);

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
