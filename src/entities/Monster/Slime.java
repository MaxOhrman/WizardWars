package entities.Monster;

import entities.GameObject;
import main.Animator;
import main.Handler;
import main.ID;
import main.SpriteSheet;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Slime extends GameObject {

    private final ArrayList<BufferedImage> slime_sprites = new ArrayList<>();
    private Handler handler;

    public Slime(int x, int y, int width, int height, ID id, boolean enableCollision, SpriteSheet spriteSheet, Handler handler) {
        super(x, y, width, height, id, enableCollision, spriteSheet);

        slime_sprites.add(spriteSheet.getSprite(1, 7, 32, 32));
        slime_sprites.add(spriteSheet.getSprite(2, 7, 32, 32));
        slime_sprites.add(spriteSheet.getSprite(3, 7, 32, 32));
        this.handler = handler;
    }

    @Override
    public void tick() {
        int vel = 1;
        x += velX;
        y += velY;

        int x1 = this.getX();
        int y1 = this.getY();

        int x2 = handler.getPlayer().getX();
        int y2 = handler.getPlayer().getY();

        double ac = Math.abs(y2 - y1);
        double cb = Math.abs(x2 - x1);

        double distance = Math.hypot(ac, cb);


        if(distance < 120) {
            velX = 1;
        } else if (distance > 120) {
            velX = 0;
        }

    }

    @Override
    public void render(Graphics g) {
        g.drawImage(slime_sprites.get(Animator.getAnimationFrame()), x, y, null);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }
}
