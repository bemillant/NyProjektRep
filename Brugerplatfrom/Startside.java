package Brugerplatfrom;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import Controller.User;

public class Startside extends Panelbuilder implements ActionListener {
    // opretter en variable framebuilder frame f
    Framebuilder f;

    public Startside(Framebuilder f, User u) {
        // Siden at det er startsiden, så opretter vi panelet samtidig med at vi
        // opretter framet - instiansierer frame
        this.f = f;

        ImageIcon image = new ImageIcon("Data/65525be2aa0a49729e74e924fe7aaa60.png");
        JButton btn = new JButton(image);
        GridBagConstraints c = new GridBagConstraints();
        c.anchor = GridBagConstraints.CENTER;

        btn.addActionListener(e -> changeScene(u));

        mainPanel.add(btn, c);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }

    public void changeScene(User u) {
        f.addPanel(new Home(f, u));
    }

}
