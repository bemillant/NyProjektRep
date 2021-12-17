package Controller;

import javax.swing.*;

import Brugergrænseflade.*;


public class Controller {

    public Controller() {

        JFrame frame = new JFrame();
        Framebuilder f = new Framebuilder(frame);
        User user1 = new User("User1");
        f.addPanel(new Startside(f, user1));

    }

}
