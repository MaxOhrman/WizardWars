package entities;

import main.ID;

import java.awt.*;

public class Block extends GameObject {

    public Block(int x, int y, int width, int height, ID id, boolean enableCollision) {
        super(x, y, width, height, id, enableCollision);
    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.black);
        g.fillRect(x,y,width,height);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }
}
