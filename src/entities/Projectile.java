package entities;

import main.Handler;
import main.ID;

import java.awt.*;
import java.util.Iterator;

public class Projectile extends GameObject {

    public boolean alive;
    private Handler handler;

    public Projectile(int x, int y, int width, int height, ID id, Handler handler, boolean enableCollision, int mouseX, int mouseY) {
        super(x, y, width, height, id, enableCollision);
        this.handler = handler;
        this.alive = true;

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

        Iterator<GameObject> it = handler.getObjectArray().iterator();
        while (it.hasNext()) {
            GameObject object = it.next();
            if(object.hasCollision && this != object && object.getId() != ID.Player) {
                if(getBounds().intersects(object.getBounds())) {
                    this.alive = false;
                }
            }
        }
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

    public boolean isAlive() {
        return this.alive;
    }
}
