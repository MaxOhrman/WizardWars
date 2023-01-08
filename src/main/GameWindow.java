package main;

import javax.swing.*;
import java.awt.*;

public class GameWindow {
    private JFrame frame;

    public GameWindow(String title, Game game, int width, int height) {

        //Creating the frame
        this.frame = new JFrame(title);
        frame.setPreferredSize(new Dimension(width,height));
        frame.setMinimumSize(new Dimension(width,height));
        frame.setMinimumSize(new Dimension(width,height));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(game);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null); //Create the frame in the center of the screen
        frame.setVisible(true);


    }
}
