package main;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Animator {

    Exhaust timer;
    private int currentElement = 0;
    ArrayList<BufferedImage> sprites = new ArrayList<>();

    public Animator(long iterateTime) {
        this.timer = new Exhaust(iterateTime);
    }

    public void addSprite(BufferedImage img) {
        sprites.add(img);
    }

    public BufferedImage animateArray(int velx, int velY, Boolean bothDir) {

        if (!timer.isExhausted()) {
            if(currentElement < sprites.size()) {
                currentElement ++;
            }
            if (currentElement == sprites.size()) {
                currentElement = 0;
            }
            timer.setExhausted();
        }

        return sprites.get(currentElement);
    }
}
