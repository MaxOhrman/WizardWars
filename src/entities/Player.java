package entities;

import inputs.KeyboardInputs;

import java.awt.*;

public class Player extends GameObject{

    KeyboardInputs key;

    public Player(int x, int y, ID id, KeyboardInputs keyboardInputs) {
        super(x, y, id);
        this.key = keyboardInputs;
    }

    @Override
    public void tick() {
        x += velX;
        y += velY;

        //
    }

    @Override
    public void render(Graphics g) {

    }

    @Override
    public Rectangle getBounds() {
        return null;
    }
}
