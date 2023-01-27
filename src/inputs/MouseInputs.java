package inputs;

import main.Camera;
import main.Handler;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class MouseInputs implements MouseListener, MouseMotionListener {

    private final Handler handler;
    private final Camera camera;

    public MouseInputs(Handler handler, Camera camera) {
        this.handler = handler;
        this.camera = camera;
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
        //We need to add the coordinates relative to the camera
        int mouseX = (int) (e.getX() + camera.getX());
        int mouseY = (int) (e.getY() + camera.getY());


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
