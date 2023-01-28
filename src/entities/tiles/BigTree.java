package entities.tiles;

import entities.GameObject;
import main.ID;
import main.SpriteSheet;

import java.awt.*;
import java.awt.image.BufferedImage;

public class BigTree extends GameObject {

    BufferedImage treeSprite;
    SpriteSheet spriteSheet;

    public BigTree(int x, int y, int width, int height, ID id, boolean enableCollision, SpriteSheet spriteSheet) {
        super(x, y, width, height, id, enableCollision, spriteSheet);
        this.spriteSheet = spriteSheet;
        this.treeSprite = spriteSheet.getSprite(2,14,64,64);

    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics g) {
        g.drawImage(treeSprite, x, y,null);
    }

    @Override
    public Rectangle getBounds() {
        int offset = 8; //Increased offset means a smaller collision box relative to object size
        return new Rectangle(x+offset, y+offset, width-(offset*2), height-(offset*2));
    }

}
