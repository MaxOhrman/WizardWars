package main;

import javax.swing.*;

public class GameWindow {
    private JFrame jFrame;

    public GameWindow(String title, GamePanel gamePanel) {

        //Creating the frame
        this.jFrame = new JFrame(title);
        jFrame.setSize( 400,400);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.add(gamePanel);
        jFrame.setLocationRelativeTo(null); //Create the frame in the center of the screen
        jFrame.setVisible(true);


    }
}
