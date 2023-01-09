package entities;

import java.awt.*;

public class Player extends GameObject{

    Handler handler;


    public Player(int x, int y, ID id, Handler handler) {
        super(x, y, id);
        this.handler = handler;
    }

    /**
     * Player Movement
     *
     * Handler has a set of booleans this
     * code acts upon. Those booleans are
     * controlled by the methods keyPressed
     * and keyReleased in KeyboardInputs class
     *
     * The following code is written in a way
     * to smoothen the Player movement
     *
     * example: w/up is held down.
     * As soon as we release it
     * else if (!handler.isDown())
     * becomes true at the next tick
     * we set our velocity in that
     * direction to 0.
     *
     */
    @Override
    public void tick() {
        x += velX;
        y += velY;

        //Player movement
        if(handler.isUp()) {
            velY = -5;
        } else if (!handler.isDown()) {
            velY = 0;
        }

        if(handler.isDown()) {
            velY = 5;
        } else if (!handler.isUp()) {
            velY = 0;
        }

        if(handler.isRight()) {
            velX = 5;
        } else if (!handler.isLeft()) {
            velX = 0;
        }

        if(handler.isLeft()) {
            velX = -5;
        } else if (!handler.isRight()) {
            velX = 0;
        }
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.blue);
        g.fillRect(x,y, 32, 48);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(x,y,32,48);
    }
}
