package Brugerplatfrom;

import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;

import Controller.*;

public class MediaScene extends Panelbuilder {

    Framebuilder f;
    MediaDatabase mdb;
    JButton play;
    JPanel mediaHolder;
    JLabel isPlayingLabel;
    Boolean isMediaPlaying = false;
    User u;
    Media m;
    JButton addToList;
    JButton removeFromList;

    public MediaScene(Framebuilder f, Media m, User user1) {
        this.u = user1;
        this.m = m;
        this.f = f;

        mdb = new MediaDatabase();


        mainPanel.setLayout(new BorderLayout());

        // Panel som overlapper mainPanel og som fungerer som sidens baggrundsskærm
        JPanel fillPanel = new JPanel();
        mainPanel.add(fillPanel, "Center");
        fillPanel.setLayout(new GridLayout(2, 0));

        // Panel som holder det medies billede og fylder halvdelen af skærmen - også her
        // der visualliseres med lys ved play
        mediaHolder = new JPanel();
        mediaHolder.setBackground(new Color(97, 201, 168));
        mediaHolder.setLayout(new BorderLayout());

        ImageIcon image = new ImageIcon(mdb.getPicture(m));
        JLabel imageHolder = new JLabel(image); // Skal laves da jpanels kræver containers.
        mediaHolder.add(imageHolder, "Center");

        // Panel som indeholder al dataen om det valgte medie. Delt op i to, med
        // play-knap, tilbage-knap og tilføj-til-min-liste på højre side
        JPanel mediaInfo = new JPanel();
        mediaInfo.setBackground(new Color(205, 118, 19));
        mediaInfo.setLayout(new GridLayout(1, 2));

        // Panel med informationer om medie,
        JPanel mediaFacts = new JPanel();
        mediaFacts.setBackground(new Color(205, 118, 19));
        mediaFacts.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        JPanel mediaData = new JPanel();
        mediaData.setBackground(new Color(205, 118, 19));
        c.gridx = 0;
        c.gridy = 1;
        c.gridheight = 3;
        c.anchor = GridBagConstraints.FIRST_LINE_START;
        mediaFacts.add(mediaData, c);
        GridLayout g = new GridLayout(4, 1);
        g.setVgap(10);
        mediaData.setLayout(g);

        JLabel mediaTitle = new JLabel(m.getTitle());
        mediaData.add(mediaTitle);
        JLabel mediaGenre = new JLabel(m.getCategories().toString());
        mediaData.add(mediaGenre);
        JLabel mediaYear = new JLabel(m.getYear());
        mediaData.add(mediaYear);
        JLabel mediaRating = new JLabel("" + m.getRating());
        mediaData.add(mediaRating);

        // Hvis mediet er en serie, så skal der også vises sæsoner og episoder
        if (m instanceof Series) {
            Series s = (Series) m;
            JPanel seasonsHolder = new JPanel();
            seasonsHolder.setBackground(new Color(205, 118, 19));
            GridLayout gl = new GridLayout(0, 1);
            gl.setVgap(10);
            seasonsHolder.setLayout(gl);
            ArrayList<String> seasons = new ArrayList<String>(s.getSeason());
            for (int i = 0; i < seasons.size(); i++) {
                String season = i + 1 + "";
                String episoder = seasons.get(i);
                // Tilføjet mellemrum for layout skyld - budget løsning
                JLabel showCase = new JLabel("      Season " + season + ": " + episoder + " episodes");
                seasonsHolder.add(showCase);
            }
            c.gridx = 2;
            c.gridy = 0;
            c.gridheight = 3;
            c.anchor = GridBagConstraints.FIRST_LINE_END;
            mediaFacts.add(seasonsHolder, c);
        }

        mediaInfo.add(mediaFacts);

        // ----------Højre side----------------

        //Indeholder en play-knap, en tilføj til min liste, hvis ens liste er tom, en fjern fra liste, hvis listen ikke er tom og en tilbage-knap
        JPanel  rightSidePanel = new JPanel();
        rightSidePanel.setBackground(new Color(205, 118, 19));
        rightSidePanel.setLayout(new GridLayout(1, 1));
        play = new JButton("Play!");
        play.addActionListener(e -> btnClicked(m));

        JPanel goBackAndAddPanel = new JPanel();
        goBackAndAddPanel.setBackground(new Color(205, 118, 19));
        goBackAndAddPanel.setLayout(new GridLayout(2, 1));
        JButton goback = new JButton("Back to homepage!");
        goback.setOpaque(true);
        goback.setBackground(new Color(205, 118, 19));
        goback.addActionListener(e -> returnHome());


        JPanel listBtns = new JPanel();
        listBtns.setBackground(new Color(205, 118, 19));
        listBtns.setLayout(new GridLayout(1, 2));

        addToList = new JButton("Add this title to your list!");
        addToList.setOpaque(true);
        addToList.setBackground(new Color(205, 118, 19));
        removeFromList = new JButton("Remove this title from your list!");
        removeFromList.setOpaque(true);
        removeFromList.setBackground(new Color(205, 118, 19));

        if (u.getList().size() >= 1) {
            for (int i = 0; i < u.getList().size(); i++) {
                if (u.getMediaTitle(u.getList().get(i)).equals(m.getTitle())) {
                    removeFromList.setText("Click to remove from list");
                    removeFromList.addActionListener(e -> removeFromList());
                } else {
                    addToList.setText("Click to add to add to list");
                    addToList.addActionListener(e -> addTitleToList());
                }
            }
            listBtns.add(addToList);
            listBtns.add(removeFromList);

        } else {
            addToList.addActionListener(e -> addTitleToList());
            listBtns.add(addToList);
        }

        goBackAndAddPanel.add(listBtns);
        goBackAndAddPanel.add(goback);

        rightSidePanel.add(play);
        rightSidePanel.add(goBackAndAddPanel);

        mediaInfo.add(rightSidePanel);

        fillPanel.add(mediaHolder);
        fillPanel.add(mediaInfo);

    }

    private void addTitleToList() {
        if (u.isMediaInList(m)) {
            addToList.setText(m.getTitle() + " is already on your list!");
        } else {
            u.addMyList(m);
            addToList.setText(m.getTitle() + " has been added to your list!");
        }
    }

    private void removeFromList() {
        for (int i = 0; i < u.getList().size(); i++) {
            if (u.getMediaTitle(u.getList().get(i)).equals(m.getTitle())) {
                u.removeFromList(u.getList().get(i));
            }
        }
        removeFromList.setText(m.getTitle() + " has been deleted from your list");
    }

    public void returnHome() {
        f.addPanel(new Home(f, u));
    }

    public void btnClicked(Media m) {
        if (isMediaPlaying) {
            mediaHolder.remove(isPlayingLabel);
            mediaHolder.setBackground(Color.black);
            isMediaPlaying = false;
            play.setText("Press to continue");
        } else {
            mediaHolder.setBackground(new Color(186, 59, 70));
            isPlayingLabel = new JLabel(m.getTitle() + " is playing...");
            isPlayingLabel.setSize(1500, 800);
            mediaHolder.add(isPlayingLabel, "Center");
            isMediaPlaying = true;
            play.setText("Press to Pause");
        }

    }

}
