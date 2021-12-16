package Controller;

import java.util.ArrayList;

/*
så f.eks så laver man listen under user classen, så lader man metoder til at tilføje 
og fjerne til listen, og så en metode til at hente hele listen
*/

public class User {

    ArrayList<Media> myList;
    String userName;

    public User(String userName) {
        this.userName = userName;
        myList = new ArrayList<>();
    }

    public void addMyList(Media m) {
        myList.add(m);
    }

    public boolean isMediaInList(Media m) {
        return myList.contains(m);
    }

    public void removeFromList(Media m) {
        myList.remove(m);
    }

    public ArrayList<Media> getList() {
        return myList;
    }

    public String getUserName() {
        return userName;
    }

    public String getMediaTitle(Media m) {
        return m.title;
    }
}