package entities;

import main.*;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Player extends GameObject {

    Handler handler;
    Exhaust exhausted;
    SpriteSheet spriteSheet;
    Game game;
    ArrayList<BufferedImage> player_sprites = new ArrayList<>();


    public Player(int x, int y, int width, int height, ID id, Handler handler, boolean enableCollision, SpriteSheet spriteSheet, Game game) {
        super(x, y, width, height, id, enableCollision, spriteSheet, handler);
        this.handler = handler;
        this.exhausted = new Exhaust(600);
        this.spriteSheet = spriteSheet;
        this.game = game;
        this.player_sprites.add(spriteSheet.getSprite(1, 1, 32, 32)); //right
        this.player_sprites.add(spriteSheet.getSprite(2, 1, 32, 32)); //right
        this.player_sprites.add(spriteSheet.getSprite(3, 1, 32, 32)); //right
        this.player_sprites.add(spriteSheet.getSprite(1, 2, 32, 32)); //Left
        this.player_sprites.add(spriteSheet.getSprite(2, 2, 32, 32)); //left
        this.player_sprites.add(spriteSheet.getSprite(3, 2, 32, 32)); //left
    }

    /**
     * Player Movement
     * <p>
     * Handler has a set of booleans this
     * code acts upon. Those booleans are
     * controlled by the methods keyPressed
     * and keyReleased in KeyboardInputs class
     * <p>
     * The following code is written in a way
     * to smoothen the Player movement
     * <p>
     * example: w/up is held down.
     * As soon as we release it
     * else if (!handler.isDown())
     * becomes true at the next tick
     * we set our velocity in that
     * direction to 0.
     */
    @Override
    public void tick() {
        double vel = 1;
        x += velX;
        y += velY;

        collision();

        //Player movement
        if (handler.isUp()) {
            velY = -vel;
        } else if (!handler.isDown()) {
            velY = 0;
        }

        if (handler.isDown()) {
            velY = vel;
        } else if (!handler.isUp()) {
            velY = 0;
        }

        if (handler.isRight()) {
            velX = vel;
        } else if (!handler.isLeft()) {
            velX = 0;
        }

        if (handler.isLeft()) {
            velX = -vel;
        } else if (!handler.isRight()) {
            velX = 0;
        }
    }

    public void castSpell(int mouseX, int mouseY) {
        if (!exhausted.isExhausted()) {
            handler.addProjectile(new Projectile(
                    x + (getWidth() / 2),
                    y + (getWidth() / 2),
                    8, 8, ID.Projectile, handler,
                    false, mouseX, mouseY, spriteSheet));
            exhausted.setExhausted();
        }
    }

    @Override
    public void render(Graphics g) {
        if(velX > 0) {
            g.drawImage(player_sprites.get(Animator.getAnimationFrame()), (int)x, (int)y, null);
        } else if (velX < 0) {
            g.drawImage(player_sprites.get(Animator.getAnimationFrame()+3), (int)x, (int)y, null);
        } else {
            g.drawImage(player_sprites.get(0), (int)x, (int)y, null);
        }


    }

    /**
     * We create a rectangle representing the size of
     * this collision box
     * <p>
     * we also offset the start x and y relative to the player
     * for a smoother experience
     *
     * @return the rectangle that we consider is the bounds
     * of the player
     */
    @Override
    public Rectangle getBounds() {
        int offset = 4; //Increased offset means a smaller collision box relative to object size
        return new Rectangle((int)x + offset, (int)y + offset, (int)width - (offset * 2), (int)height - (offset * 2));
    }
}
