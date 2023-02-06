package entities.Monster;

import entities.GameObject;
import main.*;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class GoblinArcher extends GameObject {

    ArrayList<BufferedImage> goblinArcher_sprites = new ArrayList<>();
    Handler handler;

    public GoblinArcher(double x, double y, double width, double height, ID id, boolean enableCollision, SpriteSheet spriteSheet, Handler handler) {
        super(x, y, width, height, id, enableCollision, spriteSheet, handler);
        this.handler = handler;
        this.isCreature = true;
        this.isAlive = true;
        //Facing left
        goblinArcher_sprites.add(spriteSheet.getSprite(1,16,32,32));
        goblinArcher_sprites.add(spriteSheet.getSprite(2,16,32,32));
        goblinArcher_sprites.add(spriteSheet.getSprite(3,16,32,32));
        //Facing right
        goblinArcher_sprites.add(spriteSheet.getSprite(1,17,32,32));
        goblinArcher_sprites.add(spriteSheet.getSprite(2,17,32,32));
        goblinArcher_sprites.add(spriteSheet.getSprite(3,17,32,32));
    }

    @Override
    public void tick() {
        x += velX;
        y += velY;
        timedDirSetter();
        collision();
        randomWalk(1);
    }

    @Override
    public void render(Graphics g) {
        super.render(g);
        if(velX < 0 || velY < 0) {
            g.drawImage(goblinArcher_sprites.get(Animator.getAnimationFrame()), (int) x, (int) y, null);
        } else if (velX > 0 || velY > 0) {
            g.drawImage(goblinArcher_sprites.get(Animator.getAnimationFrame()+3), (int) x, (int) y, null);
        } else {
            g.drawImage(goblinArcher_sprites.get(0), (int)x, (int)y, null);
        }
    }

    @Override
    public Rectangle getBounds() {
        int offset = 4; //Increased offset means a smaller collision box relative to object size
        return new Rectangle((int)x + offset, (int)y + offset, (int)width - (offset * 2), (int)height - (offset * 2));
    }

    public Rectangle getBoundsBig() {
        return  new Rectangle((int)x-16, (int)y-16, 64,64);
    }
}
