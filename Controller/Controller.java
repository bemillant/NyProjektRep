package Controller;

import Brugerplatfrom.*;
import javax.swing.*;
//import java.awt.*;

public class Controller {

    public Controller() {

        JFrame frame = new JFrame();
        Framebuilder f = new Framebuilder(frame);
        User user1 = new User("User1");
        // User user2 = new User("User2");
        // User user3 = new User("User3");
        f.addPanel(new Startside(f, user1));

        // f.addPanel(new Panel2());
    }

}
