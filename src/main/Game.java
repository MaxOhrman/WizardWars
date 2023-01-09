package main;

import inputs.KeyboardInputs;
import inputs.MouseInputs;

import java.awt.*;
import java.awt.image.BufferStrategy;

public class Game extends Canvas implements Runnable {

//    private final GamePanel gamePanel;
    private Thread gameThread;
    private int frameCount = 0;
    private long lastCheck = 0;
    private boolean isRunning = false;
    private final Handler handler;


    public Game() {
        new GameWindow("Game Demo", this, 1000,563);

        //Adding listeners for all inputs
        addKeyListener(new KeyboardInputs(this));
        MouseInputs mouseInputs = new MouseInputs(this);
        addMouseListener(mouseInputs);
        addMouseMotionListener(mouseInputs);

        startGameLoop();

        handler = new Handler();

        handler.addObject(new Box(100,100));
        handler.addObject(new Box(200,200));
    }

    private void startGameLoop() {
        isRunning = true;
        gameThread = new Thread(this);
        gameThread.start();
    }

    private void stopGameLoop() {
        isRunning = false;
        try {
            gameThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Game loop
     * run will run at a separate thread
     * 1 second is 1 000 000 000 nanoseconds.
     * We divide it by the desired tick rate to get how often we want
     * to update objects. If the desired time has elapsed
     * (timePerFrame) then we create a new tick
     * We also call render() here which will handle drawing of graphics
     */
    @Override
    public void run() {

        double tickRate = 60.0;
        double timePerFrame = 1000000000.0 / tickRate;
        long lastFrame = System.nanoTime();
        long now;

        while(isRunning) {
            now = System.nanoTime();
            if(now - lastFrame >= timePerFrame) {
                lastFrame = now;
                tick();

            }

            render();
            //Increment frame count for the fps counter
            frameCount++;
            FpsCounter();
        }
        stopGameLoop();
    }

    /**
     * This is where we will render all the graphics
     * createBufferStrategy will preload n amount of
     * frames after the current one
     */
    private void render() {
        BufferStrategy bufferStrategy = this.getBufferStrategy();
        if(bufferStrategy == null) {
            this.createBufferStrategy(3);
            return;
        }

        Graphics g = bufferStrategy.getDrawGraphics();
        //////// We draw things to our game here////////
        //// graphics objects is added top to bottom ///

        // Render Background (keep this at top)
        g.setColor(Color.red);
        g.fillRect(0,0,1000,563);

        //Render game objects
        handler.render(g);

        ////////////////////////////////////////////////
        g.dispose();
        bufferStrategy.show();
    }

    /**
     * Tick will update at game loops n tickRate
     *  we update game objects through
     *  handler.tick() at set tick rate
     *  of the game
     */
    private void tick() {
        handler.tick();
    }

    /**
     * Fps Counter
     * increment frames everytime we redraw the JPanel
     * if 1000 ms has passed (1s) we update lastCheck to current time
     * millis, we display the fps counter (redraws withing 1s)
     * and then we start to count again by resetting frame counter.
     */
    private void FpsCounter() {
        if(System.currentTimeMillis() - lastCheck >= 1000)  {
            lastCheck = System.currentTimeMillis();
            System.out.println("FPS: " + frameCount);
            frameCount = 0;
        }
    }


}
