package view;


import controller.*;
import model.Game;

import javax.swing.*;
import java.awt.*;

public class EndOfGameView extends View {

    JLabel statisticsLabel=new JLabel();
    JButton playAgainButton = createButton("PlayAgain");
    JButton rankingButton = createButton("Ranking");

    public EndOfGameView(Controller controller) {

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setSize(400,400);
        frame.setTitle("End Of Game");

        frame.setBackground(Color.BLACK);

        statisticsLabel.setBounds(50, 20, 300, 40);
        playAgainButton.setBounds(50, 65, 300, 40);
        rankingButton.setBounds(50, 110, 300, 40);

        statisticsLabel.setForeground(Color.WHITE);
        statisticsLabel.setFont(new Font("Arial", Font.BOLD, 12)); // Font and size

        playAgainButton.addActionListener(e -> controller.playAgain());
        rankingButton.addActionListener(e -> controller.openRankings());

        JLabel labelPane = new JLabel();
        labelPane.add(statisticsLabel);
        labelPane.add(playAgainButton);
        labelPane.add(rankingButton);

        frame.setContentPane(labelPane);

    }

    public void setStatistics(Game game){
        statisticsLabel.setText("Game finished with "+game.numberOfMoves+" moves in "+game.time+" seconds!");
    }
}

