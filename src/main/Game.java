package main;

public class Game implements Runnable {

    private GameWindow gameWindow;
    private GamePanel gamePanel;
    private Thread gameThread;
    private final int FPS_SET = 120; //The fps we want the game to run at

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

    // run will run at a separate thread
    @Override
    public void run() {

        // 1 second is 1 000 000 000 nanoseconds.
        // We divide it by the desired fps to get how often we want
        // to repaint the scene. timePerFrame is the duration each frame should last
        // if the desired time has elapsed(timePerFrame) then we draw a new frame
        // repaint(); use GamePanels paintComponent to suggest it repaint the game scene.
        double timePerFrame = 1000000000.0 / FPS_SET;
        long lastFrame = System.nanoTime();
        long now;

        while(true) {
            now = System.nanoTime();
            if(now - lastFrame >= timePerFrame) {
                gamePanel.repaint();
                lastFrame = now;
            }

        }

    }


}
