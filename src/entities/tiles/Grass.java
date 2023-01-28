package entities.tiles;

import entities.GameObject;
import main.ID;
import main.SpriteSheet;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Grass extends GameObject {

    private ArrayList<BufferedImage> grass_sprites = new ArrayList<>();

    public Grass(int x, int y, int width, int height, ID id, boolean enableCollision, SpriteSheet spriteSheet) {
        super(x, y, width, height, id, enableCollision, spriteSheet);
        grass_sprites.add(spriteSheet.getSprite(1, 10, 32, 32));
        grass_sprites.add(spriteSheet.getSprite(2, 10, 32, 32));
        grass_sprites.add(spriteSheet.getSprite(3, 10, 32, 32));

    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics g) {
        if (this.id == ID.Grass_0) {
            g.drawImage(grass_sprites.get(0), x, y, null);
        }
        if (this.id == ID.Grass_1) {
            g.drawImage(grass_sprites.get(1), x, y, null);
        }
        if (this.id == ID.Grass_2) {
            g.drawImage(grass_sprites.get(2), x, y, null);
        }
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }
}
