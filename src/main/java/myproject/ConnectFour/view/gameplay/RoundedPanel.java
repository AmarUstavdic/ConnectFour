package myproject.ConnectFour.view.gameplay;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.geom.RoundRectangle2D;

import javax.swing.JPanel;

public class RoundedPanel extends JPanel {

    private static final int ARC_WIDTH = 20;
    private static final int ARC_HEIGHT = 20;

    public RoundedPanel() {
        setOpaque(false); // make panel transparent

        // disabling mouse events on each rounded container
        addMouseListener(new MouseAdapter(){}); // set mouse listener to null
        addMouseMotionListener(new MouseAdapter(){}); // set mouse motion listener to null
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g.create();

        // Enable antialiasing for smoother edges
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Create rounded rectangle clipping path
        RoundRectangle2D rect = new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), ARC_WIDTH, ARC_HEIGHT);
        g2.setClip(rect);

        // Fill the background color
        g2.setColor(getBackground());
        g2.fillRect(0, 0, getWidth(), getHeight());

        g2.dispose();
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(200, 200);
    }

}
