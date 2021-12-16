package Brugerplatfrom;

import java.awt.*;

import javax.swing.*;

import Controller.MediaDatabase;
import Controller.Media;
import Controller.Movie;
import Controller.Series;
import Controller.User;

public class SearchScene extends Panelbuilder {

    protected String[] genre = { "Crime", "Drama", "Biography", "History", "Sport", "War", "Romance", "Mystery",
            "Fantasy", "Adventure", "Thriller", "Film-Noir", "Family", "Sci-fi", "Musical", "Western", "Action",
            "Comedy", "Horror", "Talk-show" };

    protected String[] users;

    MediaDatabase mdb;
    Framebuilder f;
    JComboBox<String> genreList;
    JTextField searchBar;

    public SearchScene(Framebuilder f, String g, User u) {
        mdb = new MediaDatabase();
        this.f = f;
        GridBagConstraints c = new GridBagConstraints();

        String[] users = { u.getUserName() };

        String searchResult = g;


        //--Header panel som indeholder samme funktioner som hos Homepage, bare uden randomMedia og med en tilbageknap i stedet
        JPanel headerPanel = new JPanel(); 
        headerPanel.setBackground(new Color(255, 226, 194));
        headerPanel.setLayout(new GridLayout(0, 3));
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 2;
        c.insets = new Insets(5, 5, 5, 5);
        mainPanel.add(headerPanel, c);

        JPanel genreMenu = new JPanel();
        genreMenu.setBackground(new Color(255, 226, 194));
        genreMenu.setLayout(new GridLayout(1, 2));
        genreList = new JComboBox<String>(genre);
        genreList.setLayout(new GridLayout(1, 1));
        genreList.setSelectedItem(searchResult);
        genreList.addActionListener(e -> selectedGenre(u));
        genreMenu.add(genreList);


        JButton back = new JButton("Return home");
        back.addActionListener(e -> returnHome(u));

        genreMenu.add(back);
        headerPanel.add(genreMenu);
        // -----------------------------------------------

        // ----------
        JPanel searchPanel = new JPanel();
        searchPanel.setBackground(new Color(255, 226, 194));
        searchBar = new JTextField(20);
        searchBar.setText("Searched for: " + g);
        searchPanel.add(searchBar);
        headerPanel.add(searchPanel);
        searchBar.addActionListener(e -> searchTitle(u));

        // ------------
        JPanel userPanel = new JPanel();
        userPanel.setBackground(new Color(255, 226, 194));
        JComboBox<String> userComboBox = new JComboBox<String>(users);
        userPanel.add(userComboBox);
        headerPanel.add(userPanel);

        // ----------

        JPanel indhold = new JPanel(); // Laver et panel der skal indeholde film billeder mm.
        indhold.setBackground(new Color(255, 226, 194));
        c.fill = GridBagConstraints.HORIZONTAL;
        c.ipady = skærmOpløsning.height / 2; // Find ny højde
        c.weightx = 0.0;
        c.gridwidth = 3;
        c.gridx = 0;
        c.gridy = 1;
        c.insets = new Insets(5, 5, 5, 5);
        mainPanel.add(indhold, c);

        indhold.setLayout(new BorderLayout());
        JPanel mediePanel = new JPanel();
        indhold.add(mediePanel, "Center");

        JButton button;
        ImageIcon image;
        JPanel movies = new JPanel();
        movies.setBackground(new Color(57, 167, 132));
        movies.setLayout(new GridLayout(2, 2));
        for (int i = 0; i < mdb.getMovies().size(); i++) {
            Movie m = mdb.getMovies().get(i);
            if (m.getCategories().contains(g)) {
                image = new ImageIcon(mdb.getPicture(m));
                button = new JButton(image);
                button.setOpaque(true);
                button.setBackground(new Color(57, 167, 132));
                movies.add(button);
                button.addActionListener(e -> changeSceneMedia(m, u));
            }
        }

        // Tilføjer nu alle de forskellige film-panels til filmpanels som er en
        // scrollpanel
        JScrollPane filmPanel = new JScrollPane(movies, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        filmPanel.getHorizontalScrollBar().setUnitIncrement(16); // Sætter hastigheden ved scroll
        filmPanel.setBackground(new Color(57, 167, 132));

        JPanel series = new JPanel();
        series.setBackground(new Color(57, 167, 132));
        series.setLayout(new GridLayout(2, 2));
        for (int i = 0; i < mdb.getSeries().size(); i++) {
            Series m = mdb.getSeries().get(i);
            if (m.getCategories().contains(g)) {
                image = new ImageIcon(mdb.getPicture(m));
                button = new JButton(image);
                button.setOpaque(true);
                button.setBackground(new Color(57, 167, 132));
                series.add(button);
                button.addActionListener(e -> changeSceneMedia(m, u));
            }
        }
        JScrollPane seriePanel = new JScrollPane(series, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        seriePanel.getHorizontalScrollBar().setUnitIncrement(16); // Sætter hastigheden ved scroll
        seriePanel.setBackground(new Color(57, 167, 132));

        mediePanel.setLayout(new GridLayout(0, 2));
        mediePanel.add(filmPanel);
        mediePanel.add(seriePanel);

    }

    // Metoden til at skifte side til genrevisning
    private void selectedGenre(User u) {
        String g = genreList.getSelectedItem().toString();
        f.addPanel(new SearchScene(f, g, u));
    }

    private void searchTitle(User u) {
        String g = searchBar.getText();
        f.addPanel(new MediaScene(f, mdb.search(g), u));
    }

    private void changeSceneMedia(Media m, User u) {
        f.addPanel(new MediaScene(f, m, u));
    }

    public void returnHome(User u) {
        f.addPanel(new Home(f, u));
    }

}