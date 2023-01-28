package entities.tiles;

import entities.GameObject;
import main.ID;
import main.SpriteSheet;

import java.awt.*;

public class Block extends GameObject {

    public Block(int x, int y, int width, int height, ID id, boolean enableCollision, SpriteSheet spriteSheet) {
        super(x, y, width, height, id, enableCollision, spriteSheet);
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
