package myproject.ConnectFour.view.gameplay;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;

public class TokenFrame extends JPanel {

    private int WIDTH;
    private int HEIGHT;
    private double CLIP_WIDTH;
    private double CLIP_HEIGHT;
    private int corner;    // 0 - none, 1 - topLeft, 2 - topRight, 3 - bottomRight, ...

    public TokenFrame(int corner) {
        this.corner = corner;
        this.WIDTH = 50;
        this.HEIGHT = 50;
        this.CLIP_WIDTH = WIDTH - 15;
        this.CLIP_HEIGHT = HEIGHT - 15;
        setPreferredSize(new Dimension((int)WIDTH, (int)HEIGHT));
        setOpaque(false);                       // so that color of panel in the back shows trough
    }


    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        RoundRectangle2D roundRectangle2D;
        Ellipse2D ellipse2D;
        Area clipArea;

        double clipX = (WIDTH / 2.0) - (CLIP_WIDTH / 2);
        double clipY = (HEIGHT / 2.0) - (CLIP_HEIGHT / 2);

        switch (corner) {
            case 1: roundRectangle2D = new RoundRectangle2D.Double(0, 0, WIDTH + 20, HEIGHT + 20, 20, 20); break;
            case 2: roundRectangle2D = new RoundRectangle2D.Double(-20, 0, WIDTH + 20, HEIGHT + 20, 20, 20); break;
            case 3: roundRectangle2D = new RoundRectangle2D.Double(-20, -20, WIDTH + 20, HEIGHT + 20, 20, 20); break;
            case 4: roundRectangle2D = new RoundRectangle2D.Double(0, -20, WIDTH + 20, HEIGHT + 20, 20, 20); break;
            default: roundRectangle2D = new RoundRectangle2D.Double(0, 0, WIDTH, WIDTH, 0, 0); break;
        }
        g2.setClip(roundRectangle2D);

        ellipse2D = new Ellipse2D.Double(clipX,clipY,CLIP_WIDTH,CLIP_HEIGHT);

        clipArea = new Area(roundRectangle2D);
        clipArea.subtract(new Area(ellipse2D));
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setClip(clipArea);

        g2.setColor(new Color(0, 40, 117));
        g2.fillRect(0,0,WIDTH,HEIGHT);

        // Draw the shadow of the border
        //g2.setClip(null);
        g2.setColor(new Color(0, 0, 0, 50)); // semi-transparent black color
        g2.fill(new Ellipse2D.Double(clipX + 3, clipY + 3, CLIP_WIDTH, CLIP_HEIGHT));

        // Draw a border around the clipped area
        g2.setClip(null);
        g2.setStroke(new BasicStroke(2));
        g2.setColor(new Color(0, 45, 165));
        Ellipse2D border = new Ellipse2D.Double(clipX, clipY, CLIP_WIDTH, CLIP_HEIGHT);
        g2.draw(border);
    }
}
