package view;

import game.GamePanel;
import controller.*;

import javax.swing.*;
import java.awt.*;

public class GameView extends View {

    /*
    For testing purposes we add a skip button that can end the game at any point
    It can be turned off by making the testing variable false
    */
    boolean testing=true;
    public JButton skipButton= createButton("Skip for Testing");
    public GameView(Controller controller,int n,String username) {

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setTitle("Game");
        frame.setResizable(false);
        frame.setBackground(Color.BLACK);
        GamePanel gamePanel = new GamePanel(controller,n,username);
        JPanel panel=new JPanel();
        panel.setBackground(Color.BLACK);
        panel.add(gamePanel);
        if(testing){
            skipButton.addActionListener(e->gamePanel.end=true);
            panel.add(skipButton);
        }
        frame.add(panel);
        frame.pack();
    }

}

