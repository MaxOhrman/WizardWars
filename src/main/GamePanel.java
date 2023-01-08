package main;

import inputs.KeyboardInputs;
import inputs.MouseInputs;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class GamePanel extends JPanel {

    private float xDelta = 100, yDelta = 100;
    private float xDir = 1f, yDir = 1f;
    private Color color = new Color(150,20,90);
    private Random random;

    public GamePanel() {
        random = new Random();
        //Adding listeners for all inputs
        addKeyListener(new KeyboardInputs(this));
        MouseInputs mouseInputs = new MouseInputs(this);
        addMouseListener(mouseInputs);
        addMouseMotionListener(mouseInputs);
    }

    public void changeXDelta(int value) {
        this.xDelta += value;
    }

    public void changeYDelta(int value) {
        this.yDelta += value;
    }

    public void setRectPos(int x, int y) {
        this.xDelta = x;
        this.yDelta = y;
    }

    /**
     *   paintComponent(Graphics g) is a part of JComponent that JPanel extends
     *   PaintComponent is also called when the system thinks it is needed for example:
     * - Minimizing the window and bringing it back
     * - Every resize of the frame containing the panel
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        updateRectangle();
        g.setColor(color);
        g.fillRect((int)xDelta,(int)yDelta,200,50);

    }

    private void updateRectangle() {
        xDelta += xDir;
        if(xDelta > this.getWidth() || xDelta < 0) {
            xDir *= -1;
            color = getRndColor();
        }

        yDelta += yDir;
        if(yDelta > this.getHeight() || yDelta < 0) {
            yDir *= -1;
            color = getRndColor();
        }
    }

    private Color getRndColor() {
        int r = random.nextInt(255);
        int g = random.nextInt(255);
        int b = random.nextInt(255);
        return new Color(r,g,b);
    }

}
