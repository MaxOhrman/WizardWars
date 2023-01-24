package entities;

import main.Handler;
import main.ID;

import java.awt.*;
import java.util.ArrayList;

public class Projectile extends GameObject {

    private Handler handler;

    public Projectile(int x, int y, int width, int height, ID id, Handler handler, boolean enableCollision, int mouseX, int mouseY) {
        super(x, y, width, height, id, enableCollision);
        this.handler = handler;

        //Let's set the Projectile velocity towards the coordinates we clicked
        //Division is the travel time
        int speed = 10;

        double projectileDir = Math.toDegrees(Math.atan2(mouseY - y, mouseX - x));
        velX = (float)(Math.cos(Math.toRadians(projectileDir))*speed);
        velY = (float)(Math.sin(Math.toRadians(projectileDir ))*speed);
    }

    @Override
    public void tick() {
        x += velX;
        y += velY;

        //Todo add collision detection and delete this if collision
        //handler.getObjectArray().remove(findThisIndex());
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.green);
        g.fillOval(x,y,8,8);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(x,y,8,8);
    }

    private int findThisIndex() {
        ArrayList<GameObject> arrayCopy = new ArrayList<>(handler.getObjectArray());

        for(int i =0; i < arrayCopy.size(); i++ ) {
            if (arrayCopy.get(i).id == this.id) {
                return i;
            }
        }
        return 0;
    }
}
