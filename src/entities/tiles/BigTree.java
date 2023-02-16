package entities.tiles;

import entities.GameObject;
import main.AStarAlgorithm;
import main.Handler;
import main.ID;
import main.SpriteSheet;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class BigTree extends GameObject {

    BufferedImage treeSprite;
    SpriteSheet spriteSheet;
    ArrayList<BufferedImage> bigTreeSprite = new ArrayList<>();

    public BigTree(double x, double y, double width, double height, ID id, boolean enableCollision, SpriteSheet spriteSheet, Handler handler, AStarAlgorithm aStarAlgorithm) {
        super(x, y, width, height, id, enableCollision, spriteSheet, handler, aStarAlgorithm);
        this.spriteSheet = spriteSheet;
        bigTreeSprite.add(spriteSheet.getSprite(2, 14, 32, 32)); //[0] NW
        bigTreeSprite.add(spriteSheet.getSprite(3, 14, 32, 32)); //[1] NE
        bigTreeSprite.add(spriteSheet.getSprite(2, 15, 32, 32)); //[2] SW
        bigTreeSprite.add(spriteSheet.getSprite(3, 15, 32, 32)); //[3] SE

    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics g) {
        if (this.id == ID.BigTree_NW) {
            g.drawImage(bigTreeSprite.get(0), (int) x, (int) y, null); //NW
        }
        if (this.id == ID.BigTree_NE) {
            g.drawImage(bigTreeSprite.get(1), (int) x, (int) y, null); //NE
        }
        if (this.id == ID.BigTree_SW) {
            g.drawImage(bigTreeSprite.get(2), (int) x, (int) y, null); //SW
        }
        if (this.id == ID.BigTree_SE) {
            g.drawImage(bigTreeSprite.get(3), (int) x, (int) y, null); //SE
        }
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int)x, (int)y, (int)width, (int)height);
    }

}
