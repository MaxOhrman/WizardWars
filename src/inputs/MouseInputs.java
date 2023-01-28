package inputs;

import main.Camera;
import main.Game;
import main.Handler;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class MouseInputs implements MouseListener, MouseMotionListener {

    private final Handler handler;
    private final Camera camera;
    Game game;

    public MouseInputs(Handler handler, Camera camera, Game game) {
        this.handler = handler;
        this.camera = camera;
        this.game = game;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mouseMoved(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        //Getting the position of the mouse doesn't translate
        //the correct position of our mouse
        //We need to add the coordinates relative to the game canvas
        int mouseX = e.getX()-(game.getWidth()/2)+64;
        int mouseY = e.getY()-(game.getHeight()/2)+64;


        if (handler.playerExist()) {
            handler.getPlayer().castSpell(mouseX,mouseY);
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }
}
