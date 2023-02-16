package main;

import entities.Monster.GoblinArcher;
import entities.Monster.Slime;
import entities.Player;
import entities.tiles.BigTree;
import entities.tiles.Block;
import entities.tiles.Dirt;
import entities.tiles.Grass;

import java.awt.image.BufferedImage;

public class LevelLoader {

    private Handler handler;
    private SpriteSheet spriteSheet;
    private Game game;
    AStarAlgorithm aStarAlgorithm;


    public LevelLoader(Handler handler, SpriteSheet spriteSheet, Game game, AStarAlgorithm aStarAlgorithm) {
        this.handler = handler;
        this.spriteSheet = spriteSheet;
        this.game = game;
        this.aStarAlgorithm = aStarAlgorithm;
    }

    /**
     * Level loading
     * <p>
     * pixel is given integer pixel in the
     * default RGB color model and default sRGB colorspace.
     * <p>
     * we extract the different colors and store them
     * in variables in order to create corresponding objects
     * we multiply xx and yy by 32 to correctly adjust for
     * the size of the object we add because xx and yy are pixel
     * coordinates to avoid overlap
     */
    public void loadLevel(BufferedImage image) {
        int w = image.getWidth();
        int h = image.getHeight();

        for (int xx = 0; xx < w; xx++) {
            for (int yy = 0; yy < h; yy++) {
                int pixel = image.getRGB(xx, yy);
                int red = (pixel >> 16) & 0xff;
                int green = (pixel >> 8) & 0xff;
                int blue = (pixel) & 0xff;

                //:::::::: GRASS ::::::::
                if (green == 255) {
                    handler.addObject(new Grass(xx * 32, yy * 32, 32, 32, ID.Grass_0, false, spriteSheet, handler, aStarAlgorithm));
                }
                if (green == 254) {
                    handler.addObject(new Grass(xx * 32, yy * 32, 32, 32, ID.Grass_1, false, spriteSheet, handler, aStarAlgorithm));
                }
                if (green == 253) {
                    handler.addObject(new Grass(xx * 32, yy * 32, 32, 32, ID.Grass_2, false, spriteSheet, handler, aStarAlgorithm));
                }

                //:::::::: Dirt ::::::::

                //d0: 165
                //d1: 155
                //d2: 145
                if (red == 165) {
                    handler.addObject(new Dirt(xx * 32, yy * 32, 32, 32, ID.Dirt_0, false, spriteSheet, handler, aStarAlgorithm));
                }
                if (red == 155) {
                    handler.addObject(new Dirt(xx * 32, yy * 32, 32, 32, ID.Dirt_1, false, spriteSheet, handler, aStarAlgorithm));
                }
                if (red == 145) {
                    handler.addObject(new Dirt(xx * 32, yy * 32, 32, 32, ID.Dirt_2, false, spriteSheet, handler, aStarAlgorithm));
                }

                // borders
                //n: 135
                //w: 130
                //e: 125
                //s: 120

                if (blue == 135) {
                    handler.addObject(new Dirt(xx * 32, yy * 32, 32, 32, ID.Dirt_N, false, spriteSheet, handler, aStarAlgorithm));
                }
                if (blue == 130) {
                    handler.addObject(new Dirt(xx * 32, yy * 32, 32, 32, ID.Dirt_W, false, spriteSheet, handler, aStarAlgorithm));
                }
                if (blue == 125) {
                    handler.addObject(new Dirt(xx * 32, yy * 32, 32, 32, ID.Dirt_E, false, spriteSheet, handler, aStarAlgorithm));
                }
                if (blue == 120) {
                    handler.addObject(new Dirt(xx * 32, yy * 32, 32, 32, ID.Dirt_S, false, spriteSheet, handler, aStarAlgorithm));
                }

                //ne: 115
                //nw: 110
                //se: 105
                //sw: 100
                if (blue == 115) {
                    handler.addObject(new Dirt(xx * 32, yy * 32, 32, 32, ID.Dirt_NE, false, spriteSheet, handler, aStarAlgorithm));
                }
                if (blue == 110) {
                    handler.addObject(new Dirt(xx * 32, yy * 32, 32, 32, ID.Dirt_NW, false, spriteSheet, handler, aStarAlgorithm));
                }
                if (blue == 105) {
                    handler.addObject(new Dirt(xx * 32, yy * 32, 32, 32, ID.Dirt_SE, false, spriteSheet, handler, aStarAlgorithm));
                }
                if (blue == 100) {
                    handler.addObject(new Dirt(xx * 32, yy * 32, 32, 32, ID.Dirt_SW, false, spriteSheet, handler, aStarAlgorithm));
                }

                //Inner_NE: 95
                //Inner_NW: 90
                //Inner_SE: 85
                //Inner_SW: 80
                if (blue == 95) {
                    handler.addObject(new Dirt(xx * 32, yy * 32, 32, 32, ID.Dirt_Inner_NE, false, spriteSheet, handler, aStarAlgorithm));
                }
                if (blue == 90) {
                    handler.addObject(new Dirt(xx * 32, yy * 32, 32, 32, ID.Dirt_Inner_NW, false, spriteSheet, handler, aStarAlgorithm));
                }
                if (blue == 85) {
                    handler.addObject(new Dirt(xx * 32, yy * 32, 32, 32, ID.Dirt_Inner_SE, false, spriteSheet, handler, aStarAlgorithm));
                }
                if (blue == 80) {
                    handler.addObject(new Dirt(xx * 32, yy * 32, 32, 32, ID.Dirt_Inner_SW, false, spriteSheet, handler, aStarAlgorithm));
                }

                //:::::::: Foliage 10-X::::::::
                //BigTree NW: 10
                //BigTree NE: 20
                //BigTree SW: 30
                //BigTree SE: 40
                if (green == 10) {
                    handler.addObject(new BigTree(xx * 32, yy * 32, 32, 32, ID.BigTree_NW, true, spriteSheet, handler, aStarAlgorithm));
                }
                if (green == 20) {
                    handler.addObject(new BigTree(xx * 32, yy * 32, 32, 32, ID.BigTree_NE, true, spriteSheet, handler, aStarAlgorithm));
                }
                if (green == 30) {
                    handler.addObject(new BigTree(xx * 32, yy * 32, 32, 32, ID.BigTree_SW, true, spriteSheet, handler, aStarAlgorithm));
                }
                if (green == 40) {
                    handler.addObject(new BigTree(xx * 32, yy * 32, 32, 32, ID.BigTree_SE, true, spriteSheet, handler, aStarAlgorithm));
                }

                //Adding outer wall
                if (red == 255) {
                    handler.addObject(new Block(xx * 32, yy * 32, 32, 32, ID.Block, true, spriteSheet, handler, aStarAlgorithm));
                }

            }
        }

        //Second layer of monsters/player
        //Todo load based on image.png with spawn positions instead of manually adding coordinates
        handler.addObject(new GoblinArcher(5 * 32, 3 * 32, 32, 32, ID.GoblinArcher, true, spriteSheet, handler, aStarAlgorithm));
        handler.addObject(new GoblinArcher(6 * 32, 3 * 32, 32, 32, ID.GoblinArcher, true, spriteSheet, handler, aStarAlgorithm));
        handler.addObject(new GoblinArcher(7 * 32, 3 * 32, 32, 32, ID.GoblinArcher, true, spriteSheet, handler, aStarAlgorithm));
        handler.addObject(new GoblinArcher(8 * 32, 3 * 32, 32, 32, ID.GoblinArcher, true, spriteSheet, handler, aStarAlgorithm));
        handler.addObject(new Slime(4 * 32, 4 * 32, 32, 32, ID.Slime, true, spriteSheet, handler, aStarAlgorithm));
        Player player = new Player(3 * 32, 3 * 32, 32, 32, ID.Player, handler, true, spriteSheet, game, aStarAlgorithm);
        handler.addObject(player);
        handler.addPlayer(player);

    }
}
