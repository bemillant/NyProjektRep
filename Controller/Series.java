package Controller;

import java.util.*;

public class Series extends Media {
    protected List<String> seasons;

    public Series(String title, String year, List<String> categories, double rating,
            List<String> seasons) {
        super(title, year, categories, rating);
        this.seasons = seasons;
    }

    public List<String> getSeason() {
        return seasons;
    }

}
