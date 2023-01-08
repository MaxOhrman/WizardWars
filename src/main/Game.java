package main;

public class Game implements Runnable {

    private final GameWindow gameWindow;
    private final GamePanel gamePanel;
    private Thread gameThread;
    private final int FPS_SET = 120; //The fps we want the game to run at
    private int frameCount = 0;
    private long lastCheck = 0;


    public Game() {
        this.gamePanel = new GamePanel();
        this.gameWindow = new GameWindow("Game Demo", this.gamePanel);
        this.gamePanel.setFocusable(true);
        // We set focus on the panel, only then can it receive key events(input).
        this.gamePanel.requestFocus();
        startGameLoop();
    }

    private void startGameLoop() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    /**
     * run will run at a separate thread
     * 1 second is 1 000 000 000 nanoseconds.
     * We divide it by the desired fps to get how often we want
     * to repaint the scene. timePerFrame is the duration each frame should last
     * if the desired time has elapsed(timePerFrame) then we draw a new frame
     * repaint(); use GamePanels paintComponent to suggest it repaint the game scene.
     */
    @Override
    public void run() {

        double timePerFrame = 1000000000.0 / FPS_SET;
        long lastFrame = System.nanoTime();
        long now;

        while(true) {
            now = System.nanoTime();
            if(now - lastFrame >= timePerFrame) {
                gamePanel.repaint();
                lastFrame = now;

                //Increment frame counter for the fps counter
                frameCount++;
            }

            runFpsCounter();
        }


    }

    /**
     * increment frames everytime we redraw the JPanel
     * if 1000 ms has passed (1s) we update lastCheck to current time
     * millis, we display the fps counter (redraws withing 1s)
     * and then we start to count again by resetting frame counter.
     */
    private void runFpsCounter() {
        if(System.currentTimeMillis() - lastCheck >= 1000)  {
            lastCheck = System.currentTimeMillis();
            System.out.println("FPS: " + frameCount);
            frameCount = 0;
        }
    }


}
