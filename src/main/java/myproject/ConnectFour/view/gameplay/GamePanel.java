package myproject.ConnectFour.view.gameplay;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class GamePanel extends JPanel {

    private int mouseX, mouseY;
    private boolean isResizing;
    private JFrame parent;


    public GamePanel(JFrame parent) {
        this.parent = parent;

        setLayout(null);
        setSize(parent.getWidth(), parent.getHeight());
        setBackground(new Color(33, 33, 33));



        addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) { // handle mouse press events
                mouseX = e.getX();
                mouseY = e.getY();
            }
        });

        addMouseMotionListener(new MouseAdapter() {
            public void mouseDragged(MouseEvent e) { // handle mouse drag events
                int x = e.getXOnScreen() - mouseX;
                int y = e.getYOnScreen() - mouseY;
                parent.setLocation(x, y);
            }

            public void mouseMoved(MouseEvent e) {
                int x = e.getX();
                int y = e.getY();

                if (x < 5 || x > (getWidth()-5) || y < 5 || y > (getHeight()-5)) {
                    System.out.println(x + " " + y);
                    handleCursor(parent,x,y);
                } else {
                    setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
                }


            }
        });









        parent.addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent e) {
                setSize(parent.getWidth(),parent.getHeight());
            }
        });




        RoundedPanel sideBar = new RoundedPanel();
        sideBar.setBackground(new Color(46,46,46));
        sideBar.setBounds(380,15, 240, 430);

        PlayerBar opponent = new PlayerBar();
        opponent.setBounds(15,15, 350, 50);

        PlayerBar player = new PlayerBar();
        player.setBounds(15,395, 350, 50);

        ConnectFourBoard board = new ConnectFourBoard();
        board.setSize(new Dimension(350,300));
        board.setBounds(15, 80, 350, 300);

        add(sideBar);
        add(player);
        add(opponent);
        add(board);
    }


    private void handleCursor(JFrame frame, int x, int y) {
        if (x < 10 && y < 10) setCursor(Cursor.getPredefinedCursor(Cursor.NW_RESIZE_CURSOR));
        if (x < 10) setCursor(Cursor.getPredefinedCursor(Cursor.NE_RESIZE_CURSOR));
        if (x < 10) setCursor(Cursor.getPredefinedCursor(Cursor.SW_RESIZE_CURSOR));
        if (x < 10) setCursor(Cursor.getPredefinedCursor(Cursor.SE_RESIZE_CURSOR));

        if (x < 10) setCursor(Cursor.getPredefinedCursor(Cursor.W_RESIZE_CURSOR));
        if (x > frame.getWidth()-10) setCursor(Cursor.getPredefinedCursor(Cursor.E_RESIZE_CURSOR));
        if (y < 10) setCursor(Cursor.getPredefinedCursor(Cursor.N_RESIZE_CURSOR));
        if (y > frame.getHeight()-10) setCursor(Cursor.getPredefinedCursor(Cursor.S_RESIZE_CURSOR));
    }


}
