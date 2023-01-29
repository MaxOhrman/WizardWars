package entities.Monster;

import entities.GameObject;
import main.Animator;
import main.Handler;
import main.ID;
import main.SpriteSheet;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class GoblinArcher extends GameObject {

    ArrayList<BufferedImage> goblinArcher_sprites = new ArrayList<>();
    Handler handler;

    public GoblinArcher(double x, double y, double width, double height, ID id, boolean enableCollision, SpriteSheet spriteSheet, Handler handler) {
        super(x, y, width, height, id, enableCollision, spriteSheet, handler);
        this.handler = handler;
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
        tickDirSetter();


            double vel = 0.5;
            x += velX;
            y += velY;


            if (up) {
                velY = -vel;
            } else if (!down) {
                velY = 0;
            }

            if (down) {
                velY = vel;
            } else if (!up) {
                velY = 0;
            }

            if (left) {
                velX = vel;
            } else if (!right) {
                velX = 0;
            }

            if (right) {
                velX = -vel;
            } else if (!left) {
                velX = 0;
            }
    }


    @Override
    public void render(Graphics g) {
        if(velX < 0) {
            g.drawImage(goblinArcher_sprites.get(Animator.getAnimationFrame()), (int) x, (int) y, null);
        } else if (velX > 0) {
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
}
