package myproject.ConnectFour.view.gameplay;


import javax.swing.*;
import java.awt.*;
import java.util.Objects;




public class PlayerBar extends RoundedPanel {


    public PlayerBar() {
        setLayout(new GridBagLayout());
        setBackground(new Color(46, 46, 46));


        /*
        ImageIcon icon = new ImageIcon(Objects.requireNonNull(getClass().getResource("/images/no-profile-picture-icon.jpg")));
        Image image = icon.getImage();
        int width = 50;
        int height = 50;
        Image scaledImage = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);  // scaling image
        ImageIcon scaledIcon = new ImageIcon(scaledImage);


        GridBagConstraints gbc = new GridBagConstraints();
        JLabel playerIconHolder = new JLabel();
        playerIconHolder.setIcon(scaledIcon);

        gbc.fill = GridBagConstraints.BOTH;

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.LINE_START;
        add(playerIconHolder, gbc);

         */
    }
}
