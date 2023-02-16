package entities.tiles;

import entities.GameObject;
import main.AStarAlgorithm;
import main.Handler;
import main.ID;
import main.SpriteSheet;

import java.awt.*;

public class Block extends GameObject {

    public Block(double x, double y, double width, double height, ID id, boolean enableCollision, SpriteSheet spriteSheet, Handler handler, AStarAlgorithm aStarAlgorithm) {
        super(x, y, width, height, id, enableCollision, spriteSheet, handler, aStarAlgorithm);
    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.black);
        g.fillRect((int)x, (int)y, (int)width, (int)height);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int)x, (int)y, (int)width, (int)height);
    }
}
