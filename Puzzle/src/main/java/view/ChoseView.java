package view;

import controller.*;
import javax.swing.*;
import java.awt.*;

public class ChoseView extends View {
    JButton threeButton = createButton("3x3");
    JButton fourButton = createButton("4x4");
    JButton fiveButton = createButton("5x5");
    JTextField usernameTextField = new JTextField();
    JLabel usernameLabel=createLabel("Username:");
    public ChoseView(Controller controller) {

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setSize(400,400);
        frame.setTitle("Chose");

        frame.setBackground(Color.BLACK);

        threeButton.setBounds(50, 20, 300, 40);
        fourButton.setBounds(50, 65, 300, 40);
        fiveButton.setBounds(50, 110, 300, 40);
        usernameLabel.setBounds(50, 155, 300, 40);
        usernameTextField.setBounds(50, 200, 300, 40);

        threeButton.addActionListener(e -> controller.openGame(3));
        fourButton.addActionListener(e -> controller.openGame(4));
        fiveButton.addActionListener(e -> controller.openGame(5));

        JLabel labelPane = new JLabel();
        labelPane.add(threeButton);
        labelPane.add(fourButton);
        labelPane.add(fiveButton);
        labelPane.add(usernameLabel);
        labelPane.add(usernameTextField);

        frame.setContentPane(labelPane);
    }
    public String getUsername(){
        return usernameTextField.getText();
    }
}

