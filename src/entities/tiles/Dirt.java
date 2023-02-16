package entities.tiles;

import entities.GameObject;
import main.AStarAlgorithm;
import main.Handler;
import main.ID;
import main.SpriteSheet;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Dirt extends GameObject {

    private final ArrayList<BufferedImage> solid_dirt_sprites = new ArrayList<>();
    private final ArrayList<BufferedImage> dirt_borders = new ArrayList<>();

    public Dirt(int x, int y, int width, int height, ID id, boolean enableCollision, SpriteSheet spriteSheet, Handler handler, AStarAlgorithm aStarAlgorithm) {
        super(x, y, width, height, id, enableCollision, spriteSheet, handler, aStarAlgorithm);

        //Adding solid dirt
        solid_dirt_sprites.add(spriteSheet.getSprite(3, 8, 32, 32));
        solid_dirt_sprites.add(spriteSheet.getSprite(3, 9, 32, 32));
        solid_dirt_sprites.add(spriteSheet.getSprite(2, 12, 32, 32));

        //Adding borders n,w,e,s grass - dirt borders
        dirt_borders.add(spriteSheet.getSprite(2, 11, 32, 32)); //n [0]
        dirt_borders.add(spriteSheet.getSprite(1, 12, 32, 32)); //w [1]
        dirt_borders.add(spriteSheet.getSprite(3, 12, 32, 32)); //e [2]
        dirt_borders.add(spriteSheet.getSprite(2, 13, 32, 32)); //s [3]

        //Adding borders ne,nw,se,sw grass - dirt borders
        dirt_borders.add(spriteSheet.getSprite(3, 11, 32, 32)); //ne [4]
        dirt_borders.add(spriteSheet.getSprite(1, 11, 32, 32)); //nw [5]
        dirt_borders.add(spriteSheet.getSprite(3, 13, 32, 32)); //se [6]
        dirt_borders.add(spriteSheet.getSprite(1, 13, 32, 32)); //sw [7]

        //Adding borders Inner_ne,Inner_nw,Inner_se,Inner_sw grass - dirt borders
        dirt_borders.add(spriteSheet.getSprite(1, 9, 32, 32)); //Inner_ne [8]
        dirt_borders.add(spriteSheet.getSprite(2, 9, 32, 32)); //Inner_nw [9]
        dirt_borders.add(spriteSheet.getSprite(1, 8, 32, 32)); //Inner_se [10]
        dirt_borders.add(spriteSheet.getSprite(2, 8, 32, 32)); //Inner_sw [11]
    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics g) {
        if (this.id == ID.Dirt_0) {
            g.drawImage(solid_dirt_sprites.get(0), (int) x, (int) y, null);
        }
        if (this.id == ID.Dirt_1) {
            g.drawImage(solid_dirt_sprites.get(1), (int) x, (int) y, null);
        }
        if (this.id == ID.Dirt_2) {
            g.drawImage(solid_dirt_sprites.get(2), (int) x, (int) y, null);
        }

        //Render borders n,w,e,s grass - dirt borders
        if (this.id == ID.Dirt_N) {
            g.drawImage(dirt_borders.get(0), (int) x, (int) y, null);
        }
        if (this.id == ID.Dirt_W) {
            g.drawImage(dirt_borders.get(1), (int) x, (int) y, null);
        }
        if (this.id == ID.Dirt_E) {
            g.drawImage(dirt_borders.get(2), (int) x, (int) y, null);
        }
        if (this.id == ID.Dirt_S) {
            g.drawImage(dirt_borders.get(3), (int) x, (int) y, null);
        }

        //Render borders ne,nw,se,sw grass - dirt borders
        if (this.id == ID.Dirt_NE) {
            g.drawImage(dirt_borders.get(4), (int) x, (int) y, null);
        }
        if (this.id == ID.Dirt_NW) {
            g.drawImage(dirt_borders.get(5), (int) x, (int) y, null);
        }
        if (this.id == ID.Dirt_SE) {
            g.drawImage(dirt_borders.get(6), (int) x, (int) y, null);
        }
        if (this.id == ID.Dirt_SW) {
            g.drawImage(dirt_borders.get(7), (int) x, (int) y, null);
        }

        //Render inner borders Inner_ne,Inner_nw,Inner_se,Inner_sw grass - dirt borders
        if (this.id == ID.Dirt_Inner_NE) {
            g.drawImage(dirt_borders.get(8), (int) x, (int) y, null);
        }
        if (this.id == ID.Dirt_Inner_NW) {
            g.drawImage(dirt_borders.get(9), (int) x, (int) y, null);
        }
        if (this.id == ID.Dirt_Inner_SE) {
            g.drawImage(dirt_borders.get(10), (int) x, (int) y, null);
        }
        if (this.id == ID.Dirt_Inner_SW) {
            g.drawImage(dirt_borders.get(11), (int) x, (int) y, null);
        }

    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int)x, (int)y, (int)width, (int)height);
    }
}
