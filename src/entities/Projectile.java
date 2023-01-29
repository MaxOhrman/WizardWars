package entities;

import main.Handler;
import main.ID;
import main.SpriteSheet;

import java.awt.*;

public class Projectile extends GameObject {

    public boolean alive;
    private final Handler handler;

    public Projectile(double x, double y, double width, double height, ID id, Handler handler, boolean enableCollision, int mouseX, int mouseY, SpriteSheet spriteSheet) {
        super(x, y, width, height, id, enableCollision, spriteSheet, handler);
        this.handler = handler;
        this.alive = true;

        int speed = 6;
        double projectileDir = Math.toDegrees(Math.atan2(mouseY, mouseX));
        velX = (float) (Math.cos(Math.toRadians(projectileDir)) * speed);
        velY = (float) (Math.sin(Math.toRadians(projectileDir)) * speed);
    }

    @Override
    public void tick() {
        x += velX;
        y += velY;

        //TODO Check if collision with monster
        for (GameObject object : handler.getObjectArray()) {
            if (object.hasCollision && object != handler.getPlayer()) {
                if (getBounds().intersects(object.getBounds())) {
                    this.alive = false;
                }
            }
        }
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.green);
        g.fillOval((int)x, (int)y, 8, 8);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int)x, (int)y, 8, 8);
    }

    public boolean isAlive() {
        return this.alive;
    }
}
