package inputs;

import entities.Handler;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyboardInputs implements KeyListener {

    Handler handler;
    private boolean up = false, down = false, right = false, left = false;

    public KeyboardInputs(Handler handler) {
        this.handler = handler;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        if(key == KeyEvent.VK_W) this.up = true;
        if(key == KeyEvent.VK_A) this.left = true;
        if(key == KeyEvent.VK_S) this.down = true;
        if(key == KeyEvent.VK_D) this.right = true;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();

        if(key == KeyEvent.VK_W) this.up = false;
        if(key == KeyEvent.VK_A) this.left = false;
        if(key == KeyEvent.VK_S) this.down = false;
        if(key == KeyEvent.VK_D) this.right = false;
    }

    public boolean isUp() {
        return up;
    }

    public boolean isDown() {
        return down;
    }

    public boolean isRight() {
        return right;
    }

    public boolean isLeft() {
        return left;
    }

}
