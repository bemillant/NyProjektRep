package Controller;

import java.util.*;

public abstract class Media {
    protected String title;
    protected String year;
    protected List<String> categories;
    protected double rating;

    public Media(String title, String year, List<String> categories, double rating) {
        this.title = title;
        this.year = year;
        this.categories = categories;
        this.rating = rating;
    }

    public String getTitle() {
        return this.title;
    }

    public String getYear() {
        return this.year;
    }

    public List<String> getCategories() {
        return this.categories;
    }

    public double getRating() {
        return this.rating;
    }

}
