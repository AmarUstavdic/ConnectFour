package myproject.ConnectFour.view.gameplay;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;


public class ConnectFourBoard extends RoundedPanel {

    double width = 0;
    double height = 0;

    private TokenFrame[][] tokenFrames;

    public ConnectFourBoard() {
        setLayout(null);
        setBackground(new Color(46, 46, 46));












        JPanel front = new RoundedPanel();
        front.setBounds(0,0,350,300);
        front.setBackground(new Color(46,46,46));
        front.setLayout(new GridLayout(6,7));
        front.setOpaque(false);

        JViewport viewport = new JViewport();
        viewport.setView(this);


        tokenFrames = new TokenFrame[6][7];
        int corner = 0;
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                corner = 0;
                if (i == 0 && j == 0) corner = 1;
                if (i == 0 && j == 6) corner = 2;
                if (i == 5 && j == 0) corner = 4;
                if (i == 5 && j == 6) corner = 3;
                tokenFrames[i][j] = new TokenFrame(corner);
                front.add(tokenFrames[i][j]);
            }
        }




        add(front);
    }



}

