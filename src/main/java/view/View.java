package view;

import javax.swing.*;
import java.awt.*;

    /*
        Contains the common functionalities of the Views, like the frame, the error and visibility methods
        and creating custom buttons and labels.
        It is useful for avoiding repeating code and making it easier to customise the look of the buttons and labels
   */
public class View extends JPanel{

    public static final Color myBlue =new Color(157, 201, 237);
    JFrame frame = new JFrame();
    public void setVisibility(boolean isVisible) {
        frame.setVisible(isVisible);
    }
    public void showError(String message) {
        JOptionPane.showMessageDialog(frame, message, "Swing Tester", JOptionPane.ERROR_MESSAGE);
    }
    public static JButton createButton(String text) {
        JButton button = new JButton(text);
        button.setBackground(myBlue); // Background color
        button.setForeground(Color.BLACK); // Text color
        button.setFont(new Font("Arial", Font.BOLD, 20)); // Font and size
        return button;
    }
    public static JLabel createLabel(String text) {
        JLabel label = new JLabel(text);
        label.setForeground(Color.WHITE); // Text color
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 20)); // Font and size
        return label;
    }
}
