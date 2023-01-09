package inputs;

import entities.Handler;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyboardInputs implements KeyListener {

    Handler handler;

    public KeyboardInputs(Handler handler) {
        this.handler = handler;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        if(key == KeyEvent.VK_W) handler.setUp(true);
        if(key == KeyEvent.VK_A) handler.setLeft(true);
        if(key == KeyEvent.VK_S) handler.setDown(true);
        if(key == KeyEvent.VK_D) handler.setRight(true);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();

        if(key == KeyEvent.VK_W) handler.setUp(false);
        if(key == KeyEvent.VK_A) handler.setLeft(false);
        if(key == KeyEvent.VK_S) handler.setDown(false);
        if(key == KeyEvent.VK_D) handler.setRight(false);
    }

}
