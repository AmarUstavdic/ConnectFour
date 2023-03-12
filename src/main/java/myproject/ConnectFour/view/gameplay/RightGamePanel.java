package myproject.ConnectFour.view.gameplay;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class RightGamePanel extends JPanel {


    private Point resizeStart;
    private Dimension panelStartSize;
    private boolean isResizing = false;

    public RightGamePanel() {
        setLayout(new GridBagLayout());
        setBackground(Color.YELLOW);

        //setPreferredSize(new Dimension(200,getHeight()));
        Dimension minimumSize = new Dimension(100, 200);
        setMinimumSize(minimumSize);


        setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));

        // Add a mouse listener to the panel
        addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                // Check if the mouse is over the right edge of the panel
                if (e.getX() <= 5) {
                    // Save the current panel size and mouse position
                    resizeStart = e.getLocationOnScreen();
                    panelStartSize = getSize();
                    isResizing = true;
                    setCursor(Cursor.getPredefinedCursor(Cursor.E_RESIZE_CURSOR));
                }
            }

            public void mouseReleased(MouseEvent e) {
                // Reset the resize flag and cursor
                isResizing = false;
                setCursor(Cursor.getDefaultCursor());
            }
        });

        addMouseMotionListener(new MouseAdapter() {
            public void mouseDragged(MouseEvent e) {
                // Check if the panel is currently being resized
                if (isResizing) {
                    // Calculate the new panel width based on the mouse position
                    int newWidth = panelStartSize.width - (e.getXOnScreen() - resizeStart.x);
                    setPreferredSize(new Dimension(newWidth, panelStartSize.height));
                    revalidate();
                }
            }
        });
    }



}
