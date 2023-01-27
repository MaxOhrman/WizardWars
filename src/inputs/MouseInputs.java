package inputs;

import entities.GameObject;
import entities.Projectile;
import main.Camera;
import main.Handler;
import main.ID;

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
        // We need to add the coordinates relative to the camera
        int mouseX = (int) (e.getX() + camera.getX());
        int mouseY = (int) (e.getY() + camera.getY());

        //Let's find the element of player then we can create the projectile
        // at her position
        for(int i = 0; i < handler.getObjectArray().size(); i++) {
            GameObject currObject = handler.getObjectArray().get(i);

            if (handler.playerExist()) {
                handler.addProjectile(new Projectile(
                        handler.getPlayer().getX() + (currObject.getWidth()/2),
                        handler.getPlayer().getY() + (currObject.getHeight()/2),
                        8,8, ID.Projectile, handler,
                        false, mouseX, mouseY));
            }
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
