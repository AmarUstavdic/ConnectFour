package myproject.ConnectFour.view.gameplay;

import javax.swing.*;
import java.awt.*;

public class LeftGamePanel extends JPanel {

    private JPanel parent;
    private double parentWidth;
    private double parentHeight;
    private Dimension size;



    public LeftGamePanel(JPanel parent) {
        this.parent = parent;
        this.parentWidth = parent.getPreferredSize().getWidth();
        this.parentHeight = parent.getPreferredSize().getHeight();


        setLayout(new GridBagLayout());
        setBackground(new Color(33,33,33));



        GridBagConstraints gbc = new GridBagConstraints();
        PlayerBar opponentBar = new PlayerBar();
        PlayerBar playerBar = new PlayerBar();
        ConnectFourBoard board = new ConnectFourBoard();

        gbc.fill = GridBagConstraints.BOTH;
        // dig dipper about this layout manager


        gbc.insets = new Insets(0,0,10,0);
        gbc.weightx = 100;
        gbc.weighty = 10;
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(opponentBar, gbc);
        gbc.insets = new Insets(0,0,10,0);
        gbc.weightx = 100;
        gbc.weighty = 80;
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(board, gbc);
        gbc.insets = new Insets(0,0,0,0);
        gbc.weightx = 100;
        gbc.weighty = 10;
        gbc.gridx = 0;
        gbc.gridy = 2;
        add(playerBar, gbc);
    }



}
