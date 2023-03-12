package myproject.ConnectFour.view;

import myproject.ConnectFour.view.gameplay.GamePanel;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;

public class View extends JFrame {



    public View() {
        setTitle("Connect Four");
        setUndecorated(true);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(635,460));
        setSize(new Dimension(635, 460));

        // making round corers on frame
        RoundRectangle2D shape = new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), 30, 30);
        setShape(shape);



        GamePanel gamePanel = new GamePanel(this);

        add(gamePanel);
        setLocationRelativeTo(null);
        setVisible(true);
        pack();
    }
}
