package main;

import inputs.KeyboardInputs;
import inputs.MouseInputs;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

public class Game extends Canvas implements Runnable {

    private final Handler handler;
    private final Camera camera;
    private Thread gameThread;
    private int frameCount = 0;
    private long lastCheck = 0;
    private boolean isRunning = false;
    private final SpriteSheet spriteSheet;
    private AStarAlgorithm aStarAlgorithm;

    public Game() {
        new GameWindow("A war of wizards", this, 1920, 1080);
        handler = new Handler();
        camera = new Camera(0, 0);

        //Adding listeners for all inputs
        addKeyListener(new KeyboardInputs(handler));
        MouseInputs mouseInputs = new MouseInputs(handler, this);
        addMouseListener(mouseInputs);
        addMouseMotionListener(mouseInputs);
        setFocusable(true);
        this.requestFocus();

        //occupy the canvas with objects from level_1.png
        BufferedImageLoader loader = new BufferedImageLoader();
        BufferedImage level_1 = loader.loadImage("/level_1.png");

        //Load in sprites as BufferedImage then we create SpriteSheet
        BufferedImage buffered_sprite_sheet = loader.loadImage("/sprites.png");
        spriteSheet = new SpriteSheet(buffered_sprite_sheet);

        LevelLoader levelLoader = new LevelLoader(handler,spriteSheet,this, aStarAlgorithm);
        levelLoader.loadLevel(level_1);

        //Path finding
        this.aStarAlgorithm = new AStarAlgorithm(handler.getObjectArray());

        startGameLoop();
    }

    public static void main(String[] args) {
        Game game = new Game();
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

        while (isRunning) {
            now = System.nanoTime();
            if (now - lastFrame >= timePerFrame) {
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
        if (bufferStrategy == null) {
            this.createBufferStrategy(3);
            return;
        }

        Graphics g = bufferStrategy.getDrawGraphics();

        //Camera following functionality
        Graphics2D g2d = (Graphics2D) g;


        //////// We draw things to our game here////////
        //// graphics objects is added top to bottom ///

        // Render Background
        g.setColor(Color.red);
        g.fillRect(0, 0, 4000, 4000);


        //Camera translation starts (Everything between will be translated)
        g2d.translate(-camera.getX(), -camera.getY());


        //Scale objects 4 times bigger
        g2d.scale(4.0, 4.0);

        //Render game objects
        handler.render(g);


        //Camera translation ends (Everything between will be translated)
        g2d.translate(-camera.getX(), -camera.getY());


        ////////////////////////////////////////////////
        g.dispose();
        bufferStrategy.show();
    }

    /**
     * Tick will update at game loops n tickRate
     * we update game objects through
     * handler.tick() at set tick rate
     * of the game
     */
    private void tick() {

        //TODO Move code to Camera instead with the check and call camera.tick here

        if (handler.playerExist()) {
            camera.tick(handler.getPlayer());
        }

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
        if (System.currentTimeMillis() - lastCheck >= 1000) {
            lastCheck = System.currentTimeMillis();
            //TODO ADD FPS COUNTER IN-GAME
            System.out.println("FPS: " + frameCount);
            frameCount = 0;
        }
    }
}
