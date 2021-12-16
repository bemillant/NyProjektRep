package Brugerplatfrom;

import javax.swing.*;

import Controller.Media;
import Controller.MediaNotFoundException;
import Controller.Movie;
import Controller.Series;
import Controller.User;

import java.awt.*;

/*
    TO-DO:
    
*/

public class Home extends Panelbuilder {

    protected String[] genre = { "Crime", "Drama", "Biography", "History", "Sport", "War", "Romance", "Mystery",
            "Fantasy", "Adventure", "Thriller", "Film-Noir", "Family", "Sci-Fi", "Musical", "Western", "Action",
            "Comedy", "Horror", "Talk-show" };

    protected String[] users;

    Framebuilder f;
    JComboBox<String> genreList;
    JPanel headerPanel;
    JPanel footerPanel;

    JTextField searchBar;

    JComboBox<String> userComboBox;

    User user1;

    public Home(Framebuilder f, User user1) {
        this.f = f;
        this.user1 = user1;
        String[] users = { user1.getUserName() };

        GridBagConstraints c = new GridBagConstraints();

        // Header panel som skal indeholde alle søgefunktioner og lign.
        headerPanel = new JPanel();
        headerPanel.setBackground(new Color(255, 226, 194));
        headerPanel.setLayout(new GridLayout(0, 3));
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 2;
        c.insets = new Insets(5, 5, 5, 5);
        mainPanel.add(headerPanel, c);

        // Genrefunktion
        JPanel leftHeader = new JPanel();
        leftHeader.setLayout(new GridLayout(1, 0));
        JPanel genreMenu = new JPanel();
        genreMenu.setBackground(new Color(57, 167, 132));
        genreList = new JComboBox<String>(genre);
        genreMenu.add(genreList);
        leftHeader.add(genreMenu);

        JPanel randomMoviePanel = new JPanel();
        randomMoviePanel.setBackground(new Color(57, 167, 132));
        JButton randomBtn = new JButton("Give me a random title to watch!");
        randomBtn.addActionListener(e -> giveRandomMedia(getUser()));
        randomMoviePanel.add(randomBtn);
        leftHeader.add(randomMoviePanel);

        headerPanel.add(leftHeader);

        genreList.addActionListener(e -> selectedGenre(getUser()));
        // -----------------------------------------------

        // Søgefunktion
        JPanel searchPanel = new JPanel();
        searchPanel.setBackground(new Color(57, 167, 132));
        searchBar = new JTextField(25);
        searchBar.setText("Search for a title or genre here!");
        searchPanel.add(searchBar);
        headerPanel.add(searchPanel);
        searchBar.addActionListener(e -> searchTitle(getUser()));

        // -------------------------

        // Userfunktion
        JPanel userPanel = new JPanel();
        userPanel.setBackground(new Color(57, 167, 132));
        userComboBox = new JComboBox<String>(users);
        userPanel.add(userComboBox);
        headerPanel.add(userPanel);
        // -------------------------------

        // Footer som skal indeholde et langt panel som er min-liste
        footerPanel = new JPanel();
        footerPanel.setBackground(new Color(255, 226, 194));

        footerPanel.setLayout(new BorderLayout());
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 0.5;
        c.gridx = 0;
        c.gridy = 2;
        c.gridwidth = 2;
        c.insets = new Insets(5, 5, 5, 5);
        c.ipady = skærmOpløsning.height / 5;
        mainPanel.add(footerPanel, c);

        // -------Oprettelse af min liste--------------
        JPanel myListPanel = new JPanel();
        myListPanel.setBackground(new Color(57, 167, 132));
        myListPanel.setLayout(new GridLayout(1, 0));

        JScrollPane myList = new JScrollPane(myListPanel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        myList.getHorizontalScrollBar().setUnitIncrement(16); // Sætter hastigheden ved scroll
        myList.setBackground(new Color(255, 226, 194));

        if (getUser().getList().size() == 0) {
            JLabel textLabel = new JLabel("Add a title to your list and you'll see it here!");
            myListPanel.add(textLabel);
        } else {
            for (int i = 0; i < getUser().getList().size(); i++) {
                Media m = getUser().getList().get(i);
                ImageIcon image = new ImageIcon(mdb.getPicture(m));
                JButton button = new JButton(image);
                button.setOpaque(true);
                button.setBackground(new Color(57, 167, 132));
                myListPanel.add(button);
                button.addActionListener(e -> changeSceneMedia(m, getUser()));
            }
        }

        footerPanel.add(myList, "Center");

        // --------------------
        JPanel indhold = new JPanel(); // Laver et panel der skal indeholde film billeder mm.
        c.fill = GridBagConstraints.HORIZONTAL;
        c.ipady = skærmOpløsning.height / 2; // Find ny højde
        c.weightx = 0.0;
        c.gridwidth = 3;
        c.gridx = 0;
        c.gridy = 1;
        c.insets = new Insets(10, 5, 10, 5);
        mainPanel.add(indhold, c);

        indhold.setLayout(new BorderLayout());
        JPanel mediePanel = new JPanel();
        indhold.add(mediePanel, "Center");

        // -------- Tilføjelse af de to hovedefunktioner - oversigt over alle film og serier
        JButton button;
        ImageIcon image;
        JPanel movies = new JPanel();
        movies.setLayout(new GridLayout(0, 4));
        for (int i = 0; i < mdb.getMovies().size(); i++) {
            Movie m = mdb.getMovies().get(i);
            image = new ImageIcon(mdb.getPicture(m));
            button = new JButton(image);

            button.setOpaque(true);
            button.setBackground(new Color(255, 226, 194));

            movies.add(button);
            button.addActionListener(e -> changeSceneMedia(m, getUser()));

        }
        // Tilføjer nu alle de forskellige film-panels til filmpanels som er en
        // scrollpanel
        JScrollPane filmPanel = new JScrollPane(movies);
        filmPanel.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        filmPanel.getVerticalScrollBar().setUnitIncrement(16); // Sætter hastigheden ved scroll

        JPanel series = new JPanel();
        series.setLayout(new GridLayout(0, 4));
        for (int i = 0; i < mdb.getSeries().size(); i++) {

            Series m = mdb.getSeries().get(i);
            image = new ImageIcon(mdb.getPicture(m));
            button = new JButton(image);

            button.setOpaque(true);
            button.setBackground(new Color(255, 226, 194));

            series.add(button);
            button.addActionListener(e -> changeSceneMedia(m, getUser()));
        }
        JScrollPane seriePanel = new JScrollPane(series);
        seriePanel.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        seriePanel.getVerticalScrollBar().setUnitIncrement(16); // Sætter hastigheden ved scroll

        // Farve rundt om de to forskellige bokse - kan ændres til vores farve scheme
        filmPanel.setBackground(new Color(255, 238, 219));
        seriePanel.setBackground(new Color(255, 238, 219));

        mediePanel.setLayout(new GridLayout(0, 2));
        mediePanel.add(filmPanel);
        mediePanel.add(seriePanel);

        // -----------------

    }
    //Giver tilfældigt medie til afspilning
    private void giveRandomMedia(User u) {
        Media m = mdb.getRandomMedia();
        changeSceneMedia(m, u);
    }

    // Metoden til at skifte side til genrevisning
    private void selectedGenre(User u) {
        String g = genreList.getSelectedItem().toString();
        f.addPanel(new SearchScene(f, g, u));
    }

    //returner useren
    private User getUser() {
        return user1;
    }

    //Søger efter bestemt medie og går til mediesiden hvis fundet
    private void searchTitle(User u) {
        String g = searchBar.getText();
        try{
        f.addPanel(new MediaScene(f, mdb.search(g), u));
        } catch (MediaNotFoundException e ){
            searchBar.setText("The media was not found, try again!");
        }
    }

    //Skifter til medieside med mediet man klikker på
    private void changeSceneMedia(Media m, User u) {
        f.addPanel(new MediaScene(f, m, u));

    }

}
