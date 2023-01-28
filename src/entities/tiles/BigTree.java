package entities.tiles;

import entities.GameObject;
import main.ID;
import main.SpriteSheet;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class BigTree extends GameObject {

    BufferedImage treeSprite;
    SpriteSheet spriteSheet;
    ArrayList<BufferedImage> bigTreeSprite = new ArrayList<>();

    public BigTree(int x, int y, int width, int height, ID id, boolean enableCollision, SpriteSheet spriteSheet) {
        super(x, y, width, height, id, enableCollision, spriteSheet);
        this.spriteSheet = spriteSheet;
        bigTreeSprite.add(spriteSheet.getSprite(2,14,32,32)); //[0] NW
        bigTreeSprite.add(spriteSheet.getSprite(3,14,32,32)); //[1] NE
        bigTreeSprite.add(spriteSheet.getSprite(2,15,32,32)); //[2] SW
        bigTreeSprite.add(spriteSheet.getSprite(3,15,32,32)); //[3] SE

    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics g) {
        if(this.id == ID.BigTree_NW) {
            g.drawImage(bigTreeSprite.get(0), x, y,null); //NW
        }
        if(this.id == ID.BigTree_NE) {
            g.drawImage(bigTreeSprite.get(1), x, y,null); //NE
        }
        if(this.id == ID.BigTree_SW) {
            g.drawImage(bigTreeSprite.get(2), x, y,null); //SW
        }
        if(this.id == ID.BigTree_SE) {
            g.drawImage(bigTreeSprite.get(3), x, y,null); //SE
        }
    }

    @Override
    public Rectangle getBounds() {
        int offset = 5; //Increased offset means a smaller collision box relative to object size
        return new Rectangle(x+offset, y+offset, width-(offset*2), height-(offset*2));
    }

}
