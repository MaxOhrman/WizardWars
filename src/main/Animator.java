package main;

public class Animator {
    private static int tickCount;
    public static int animationFrame;

    /**
     * Tick() is called from handler
     * in here we handle global timings
     * such as animation change intervals
     */
    public static void tick() {

        //Create integer from 0-2 based on game tick rate
        tickCount++;

        if(tickCount > 30) {
            if (animationFrame <= 1) {
                animationFrame ++;
            } else {
                animationFrame = 0;
            }
            tickCount = 0;
        }

    }

    public static int getAnimationFrame(){
        return animationFrame;
    }


}
