package inputs;

import main.Game;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyboardInputs implements KeyListener {

    private Game gameCanvas;

    public KeyboardInputs(Game gameCanvas) {
        this.gameCanvas = gameCanvas;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_W -> System.out.println("W");
            case KeyEvent.VK_A -> System.out.println("A");
            case KeyEvent.VK_S -> System.out.println("S");
            case KeyEvent.VK_D -> System.out.println("D");
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}
