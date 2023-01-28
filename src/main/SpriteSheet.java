package main;


import java.awt.image.BufferedImage;

/**
 * We are going to take the sprites from file
 * and shop it up into different pieces
 * then we can use them individually
 */
public class SpriteSheet {
    private BufferedImage image;

    //image will be our sprites
    public SpriteSheet(BufferedImage image) {
        this.image = image;
    }

    //This way we can select what column and row we want to add a sprite from
    public BufferedImage getSprite(int col, int row, int width, int height) {
        return image.getSubimage((col*32)-32, (row*32)-32, width, height);
    }


}
