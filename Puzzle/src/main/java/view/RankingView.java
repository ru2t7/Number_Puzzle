package view;


import controller.*;
import main.Reflection;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.util.Comparator;
import java.util.List;


public class RankingView extends View {
    JButton playAgainButton = createButton("Play Again");
    JLabel tableLabel3 = createLabel("3x3");
    JLabel tableLabel4 = createLabel("4x4");
    JLabel tableLabel5 = createLabel("5x5");
    JTable table3 = new JTable();
    JScrollPane scrollPane3 = new JScrollPane(table3);
    JTable table4 = new JTable();
    JScrollPane scrollPane4 = new JScrollPane(table4);
    JTable table5 = new JTable();
    JScrollPane scrollPane5 = new JScrollPane(table5);


    public RankingView(Controller controller) {

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setSize(900,400);
        frame.setTitle("Rankings");

        frame.setBackground(Color.BLACK);

        playAgainButton.setBounds(300, 20, 300, 40);
        tableLabel3.setBounds(0, 65, 300, 40);
        scrollPane3.setBounds(0, 110, 300, 310);
        tableLabel4.setBounds(300, 65, 300, 40);
        scrollPane4.setBounds(300, 110, 300, 310);
        tableLabel5.setBounds(600, 65, 300, 40);
        scrollPane5.setBounds(600, 110, 300, 310);


        playAgainButton.addActionListener(e -> controller.playAgain());

        JLabel labelPane = new JLabel();
        labelPane.add(playAgainButton);
        labelPane.add(scrollPane3);
        labelPane.add(scrollPane4);
        labelPane.add(scrollPane5);
        labelPane.add(tableLabel3);
        labelPane.add(tableLabel4);
        labelPane.add(tableLabel5);

        frame.setContentPane(labelPane);
}

    /*
    Displays the tables containing the rankings of the games
    */
    public void displayDataOBJ(List<Object> objects,JTable table) {
        String[] coll = Reflection.retrieveFieldNames(objects.get(0));
        DefaultTableModel model = new DefaultTableModel(coll, 0);
        for (Object object: objects) {
            Object[] row = Reflection.retrieveFieldValues(object);
            model.addRow(row);
        }

        table.setModel(model);
        TableRowSorter sorter = new TableRowSorter(model);
        sorter.setComparator(1, Comparator.comparingInt((Integer id) -> id).reversed());
        table.setModel(model);
        table.setRowSorter(sorter);
    }
    public JTable getTable3() {
        return table3;
    }

    public JTable getTable4() {
        return table4;
    }

    public JTable getTable5() {
        return table5;
    }
}

