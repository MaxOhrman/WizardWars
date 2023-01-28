package main;

public class Exhaust {
    /**
     * We want players and monsters to have delays between
     * shooting/casting projectiles
     * <p>
     * We use setExhausted() when shooting to
     * set the time when something was cast
     * <p>
     * we use isExhausted() to see if we can cast.
     */
    private long castTime;
    private long timeNow;
    private long exhaustTime;
    private boolean exhausted;

    public Exhaust(long exhaustTimeMs) {
        this.exhaustTime = exhaustTimeMs;
        this.exhausted = false;
    }


    private void checkTimer() {
        setTimeNow();
        if ((timeNow - castTime) > exhaustTime) {
            exhausted = false;
        }
    }

    private void setTimeNow() {
        this.timeNow = System.currentTimeMillis();
    }

    public void setExhausted() {
        castTime = System.currentTimeMillis();
        this.exhausted = true;
    }

    public boolean isExhausted() {
        checkTimer();
        return exhausted;
    }
}
