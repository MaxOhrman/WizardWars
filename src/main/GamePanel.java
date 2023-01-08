package main;

import inputs.KeyboardInputs;
import inputs.MouseInputs;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {

    private int xDelta = 100, yDelta = 100;
    private int frames = 0;
    private long lastCheck = 0;

    public GamePanel() {
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

    // Everytime we use repaint(); paintComponent is suggested to repaint the game scene.
    // paintComponent(Graphics g) is a part of JComponent that JPanel extends
    // PaintComponent is also called when the system thinks it is needed for example:
    // - Minimizing the window and bringing it back
    // - Every resize of the frame containing the panel
    // This is now a basic game loop looping because repaint() calls paintComponent()
    // causing a loop that redraws the scene
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.fillRect(xDelta,yDelta,200,50);


        //          :::::(FPS COUNTER):::::
        // increment frames everytime we redraw the JPanel
        // if 1000 ms has passed (1s) we update lastCheck to current time
        // millis, we display the fps counter (redraws withing 1s)
        // and then we start to count again by resetting frame counter.
        frames++;
        if(System.currentTimeMillis() - lastCheck >= 1000)  {
            lastCheck = System.currentTimeMillis();
            System.out.println("FPS: " + frames);
            frames = 0;
        }

        //Repaint calls paintComponent and we loop again.
        repaint();
    }


}
