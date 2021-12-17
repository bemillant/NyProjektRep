package Brugergrænseflade;

import java.awt.*;

import javax.swing.*;

import Controller.MediaDatabase;

//Klassen skal bygge panels og derefter sende dem til FrameBuilder.java
//Er en superklasse til de forskellige panels

public class Panelbuilder {

    JPanel mainPanel;
    protected final Dimension skærmOpløsning = Toolkit.getDefaultToolkit().getScreenSize();

    MediaDatabase mdb = new MediaDatabase();

    public Panelbuilder() {

        mainPanel = new JPanel();
        mainPanel.setSize(skærmOpløsning.width, skærmOpløsning.height);
        mainPanel.setBackground(new Color(97, 201, 168));

        mainPanel.setLayout(new GridBagLayout());

    }

    // Returner mainPanel som bruges i Framebuilder
    public JPanel getPanelbuilder() {
        return mainPanel;
    }

    

}
