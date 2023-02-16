package entities.Monster;

import entities.GameObject;
import main.*;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Slime extends GameObject {

    private final ArrayList<BufferedImage> slime_sprites = new ArrayList<>();
    private Handler handler;

    public Slime(double x, double y, double width, double height, ID id, boolean enableCollision, SpriteSheet spriteSheet, Handler handler, AStarAlgorithm aStarAlgorithm) {
        super(x, y, width, height, id, enableCollision, spriteSheet, handler, aStarAlgorithm);
        this.isCreature = true;
        this.isAlive = true;
        slime_sprites.add(spriteSheet.getSprite(1, 7, 32, 32));
        slime_sprites.add(spriteSheet.getSprite(2, 7, 32, 32));
        slime_sprites.add(spriteSheet.getSprite(3, 7, 32, 32));
        this.handler = handler;
    }

    @Override
    public void tick() {
        x += velX;
        y += velY;
        timedDirSetter();
        collision();
        randomWalk(0.5);

    }

    @Override
    public void render(Graphics g) {
        super.render(g);
        g.drawImage(slime_sprites.get(Animator.getAnimationFrame()), (int)x, (int)y, null);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int) x, (int) y, (int) width, (int)height);
    }
}
