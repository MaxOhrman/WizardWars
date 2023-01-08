package main;

public class Game implements Runnable {

    private GameWindow gameWindow;
    private GamePanel gamePanel;

    public Game() {
        this.gamePanel = new GamePanel();
        this.gameWindow = new GameWindow("Game Demo", this.gamePanel);
        this.gamePanel.setFocusable(true);
        // We set focus on the panel, only then can it receive key events.
        this.gamePanel.requestFocus();
    }

    @Override
    public void run() {

    }


}
