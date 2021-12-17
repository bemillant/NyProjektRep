package Brugergrænseflade;

import java.awt.*;
import javax.swing.*;

//Målen med denne klasse er at BYGGE den scene der skal vises
//Først: lav frame. Næste: fjern og tilføj panels

public class Framebuilder {
    JFrame frame;

    // Laver en dimension som er skærmens størrelse - som senere bruges i setSize()
    private final Dimension skærmOpløsning = Toolkit.getDefaultToolkit().getScreenSize();

    // Laver frame med en JFrame som inputparametre
    public Framebuilder(JFrame frame) {
        this.frame = frame;
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(skærmOpløsning.width, skærmOpløsning.height);
        // frame.setSize(500, 500);

    }

    // Metode som opdaterer scenen med et nyt panel - fjerner alt og tilføjer det
    // nye panel p
    public void addPanel(Panelbuilder p) {
        frame.getContentPane().removeAll();
        frame.repaint();
        frame.getContentPane().add(p.getPanelbuilder());

        // frame.setLayout(new BorderLayout());
        frame.setVisible(true);

    }

}
