package main;

import entities.Player;
import entities.tiles.Block;
import entities.tiles.Dirt;
import entities.tiles.Grass;
import inputs.KeyboardInputs;
import inputs.MouseInputs;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

public class Game extends Canvas implements Runnable {

//    private final GamePanel gamePanel;
    private Thread gameThread;
    private int frameCount = 0;
    private long lastCheck = 0;
    private boolean isRunning = false;
    private final Handler handler;
    private final Camera camera;
    private SpriteSheet spriteSheet;

    private BufferedImage buffered_sprite_sheet = null;
    private BufferedImage floor = null;

    public Game() {
        new GameWindow("A war of wizards", this, 400,400);
        handler = new Handler();
        camera = new Camera(0,0, this);

        //Adding listeners for all inputs
        addKeyListener(new KeyboardInputs(handler));
        MouseInputs mouseInputs = new MouseInputs(handler, camera);
        addMouseListener(mouseInputs);
        addMouseMotionListener(mouseInputs);
        setFocusable(true);
        this.requestFocus();

        //occupy the canvas with objects from level_1.png
        BufferedImageLoader loader = new BufferedImageLoader();
        BufferedImage level_1 = loader.loadImage("/level_1.png");

        //Load in sprites as BufferedImage then we create SpriteSheet
        buffered_sprite_sheet = loader.loadImage("/sprites.png");
        spriteSheet = new SpriteSheet(buffered_sprite_sheet);

        floor = spriteSheet.getSprite(2,10,32,32);

        loadLevel(level_1);

        startGameLoop();
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

        //Camera following functionality
        Graphics2D g2d = (Graphics2D) g;


        //////// We draw things to our game here////////
        //// graphics objects is added top to bottom ///

//        // Render Background
//        g.setColor(Color.red);
//        g.fillRect(0,0,1000,563);


        //Camera translation starts (Everything between will be translated)
        g2d.translate(-camera.getX(), -camera.getY());



        //Render game objects
        handler.render(g);

        //Camera translation ends (Everything between will be translated)
        g2d.translate(-camera.getX(), -camera.getY());



        ////////////////////////////////////////////////
        g.dispose();
        bufferStrategy.show();
    }

    /**
     * Level loading
     *
     * pixel is given integer pixel in the
     * default RGB color model and default sRGB colorspace.
     *
     * we extract the different colors and store them
     * in variables in order to create corresponding objects
     * we multiply xx and yy by 32 to correctly adjust for
     * the size of the object we add because xx and yy are pixel
     * coordinates to avoid overlap
     *
     */
    private void loadLevel(BufferedImage image) {
        int w = image.getWidth();
        int h = image.getHeight();

        for(int xx = 0; xx < w; xx++) {
            for(int yy=0; yy < h; yy++) {
                int pixel = image.getRGB(xx,yy);
                int red = (pixel >> 16) & 0xff;
                int green = (pixel >> 8) & 0xff;
                int blue = (pixel) & 0xff;

                //:::::::: GRASS ::::::::
                if (green == 255) {
                    handler.addObject(new Grass(xx*32,yy*32, 32,32, ID.Grass_0, false, spriteSheet));
                }
                if (green == 254) {
                    handler.addObject(new Grass(xx*32,yy*32, 32,32, ID.Grass_1, false, spriteSheet));
                }
                if (green == 253) {
                    handler.addObject(new Grass(xx*32,yy*32, 32,32, ID.Grass_2, false, spriteSheet));
                }

                //:::::::: Dirt ::::::::

                //d0: 165
                //d1: 155
                //d2: 145
                if (red == 165) {
                    handler.addObject(new Dirt(xx*32,yy*32, 32,32, ID.Dirt_0, false, spriteSheet));
                }
                if (red == 155) {
                    handler.addObject(new Dirt(xx*32,yy*32, 32,32, ID.Dirt_1, false, spriteSheet));
                }
                if (red == 145) {
                    handler.addObject(new Dirt(xx*32,yy*32, 32,32, ID.Dirt_2, false, spriteSheet));
                }

                // borders
                //n: 135
                //w: 130
                //e: 125
                //s: 120

                if (blue == 135) {
                    handler.addObject(new Dirt(xx*32,yy*32, 32,32, ID.Dirt_N, false, spriteSheet));
                }
                if (blue == 130) {
                    handler.addObject(new Dirt(xx*32,yy*32, 32,32, ID.Dirt_W, false, spriteSheet));
                }
                if (blue == 125) {
                    handler.addObject(new Dirt(xx*32,yy*32, 32,32, ID.Dirt_E, false, spriteSheet));
                }
                if (blue == 120) {
                    handler.addObject(new Dirt(xx*32,yy*32, 32,32, ID.Dirt_S, false, spriteSheet));
                }

                //ne: 115
                //nw: 110
                //se: 105
                //sw: 100
                if (blue == 115) {
                    handler.addObject(new Dirt(xx*32,yy*32, 32,32, ID.Dirt_NE, false, spriteSheet));
                }
                if (blue == 110) {
                    handler.addObject(new Dirt(xx*32,yy*32, 32,32, ID.Dirt_NW, false, spriteSheet));
                }
                if (blue == 105) {
                    handler.addObject(new Dirt(xx*32,yy*32, 32,32, ID.Dirt_SE, false, spriteSheet));
                }
                if (blue == 100) {
                    handler.addObject(new Dirt(xx*32,yy*32, 32,32, ID.Dirt_SW, false, spriteSheet));
                }

                //Inner_NE: 95
                //Inner_NW: 90
                //Inner_SE: 85
                //Inner_SW: 80
                if (blue == 95) {
                    handler.addObject(new Dirt(xx*32,yy*32, 32,32, ID.Dirt_Inner_NE, false, spriteSheet));
                }
                if (blue == 90) {
                    handler.addObject(new Dirt(xx*32,yy*32, 32,32, ID.Dirt_Inner_NW, false, spriteSheet));
                }
                if (blue == 85) {
                    handler.addObject(new Dirt(xx*32,yy*32, 32,32, ID.Dirt_Inner_SE, false, spriteSheet));
                }
                if (blue == 80) {
                    handler.addObject(new Dirt(xx*32,yy*32, 32,32, ID.Dirt_Inner_SW, false, spriteSheet));
                }


                //Adding outer wall
                if (red == 255) {
                    handler.addObject(new Block(xx*32,yy*32, 32,32, ID.Block, true, spriteSheet));
                }

                //Adding the player last
                handler.addPlayer(new Player(3*32,3*32, 32, 32, ID.Player, handler, true, spriteSheet));
            }
        }
    }


    /**
     * Tick will update at game loops n tickRate
     *  we update game objects through
     *  handler.tick() at set tick rate
     *  of the game
     */
    private void tick() {

        //TODO Move code to Camera instead with the check and call camera.tick here

        if(handler.playerExist()) {
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
        if(System.currentTimeMillis() - lastCheck >= 1000)  {
            lastCheck = System.currentTimeMillis();
            //TODO ADD FPS COUNTER IN-GAME
            //System.out.println("FPS: " + frameCount);
            frameCount = 0;
        }
    }

    public static void main(String[] args) {
        Game game = new Game();
    }


}
