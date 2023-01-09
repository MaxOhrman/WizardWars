package entities;

import java.awt.*;

public class Player extends GameObject{


    public Player(int x, int y, ID id) {
        super(x, y, id);
    }

    @Override
    public void tick() {
        x += velX;
        y += velY;
    }

    @Override
    public void render(Graphics g) {

    }

    @Override
    public Rectangle getBounds() {
        return null;
    }
}
