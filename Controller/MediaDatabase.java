package Controller;

//import java.net.URL;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class MediaDatabase {
    FileReader fr = new FileReader();
    ArrayList<Movie> movies = new ArrayList<>();
    ArrayList<Series> series = new ArrayList<>();

    public MediaDatabase(){
        fr.TextReader("/Data/film.txt");
        fr.TextReader("/Data/serier.txt");
        movies = fr.getMovies();
        series = fr.getSeries();
    }

    public ArrayList<Media> getMedia() {
        ArrayList<Media> allMedia = new ArrayList<>();
        allMedia.addAll(series);
        allMedia.addAll(movies);
        return allMedia;
    }

    public Media getThisMedia(int i) {
        return getMedia().get(i);
    }

    public ArrayList<Movie> getMovies() {
        return movies;
    }

    public String getMovieTitle(int i) {
        return getMovies().get(i).title;
    }


    public ArrayList<Series> getSeries() {
        return series;
    }

    public String getSeriesTitle(int i) {
        return getSeries().get(i).title;
    }

    // Returnerer en liste af alle film med genren angivet i parameteren
    //Ikke implementeret - fundet anden tilgang
    public ArrayList<Movie> getMovieGenres(String cat) {
        ArrayList<Movie> movieGenres = new ArrayList<>();
        for (int i = 0; i < getMovies().size(); i++) {
            if (getMovies().get(i).categories.contains(cat)) {
                movieGenres.add(getMovies().get(i));
            }
        }
        return movieGenres;
    }

    // Returnerer en liste af alle serier med genren angivet i parameteren
    //Ikke implementeret - fundet anden tilgang
    public ArrayList<Series> getSeriesGenres(String cat) {
        ArrayList<Series> seriesGenres = new ArrayList<>();
        for (int i = 0; i < getSeries().size(); i++) {
            if (getSeries().get(i).categories.contains(cat)) {
                seriesGenres.add(getSeries().get(i));
            }
        }
        return seriesGenres;
    }

    //returnerer en sorteret liste af film udfra rating
    //Ikke impementeret
    public ArrayList<Movie> topMovies() {
    ArrayList<Movie> topMovies = new ArrayList<>();
    topMovies.addAll(movies);
    topMovies.sort((o1, o2) -> Double.compare(o2.rating, o1.rating));
    return topMovies;
    }

    //returnerer en sorteret liste af serier udfra rating
    //Ikke implementeret
    public ArrayList<Series> topSeries() {
    ArrayList<Series> topSeries = new ArrayList<>();
    topSeries.addAll(series);
    topSeries.sort((o1, o2) -> Double.compare(o2.rating, o1.rating));
    return topSeries;
    }
    public Media search(String name) throws MediaNotFoundException {
        ArrayList<Media> allMedia = new ArrayList<>();
        allMedia.addAll(getMovies());
        allMedia.addAll(getSeries());
        for (int i = 0; i < allMedia.size(); i++) {
            if (allMedia.get(i).title.toLowerCase().equals(name.toLowerCase())) {
                return allMedia.get(i);
            }
        }
        throw new MediaNotFoundException(name);
    }

    //Outdated getPicture, som kan bruges til viderudvikling af database - tager udgangspunkt i URLer fremfor Strings til ImageIcon
    /* public URL getPicture(Media m) {
        if (m instanceof Movie) {
            java.net.URL imageText = getClass().getResource("/Data/filmplakater/" + m.title + ".jpg");
            return (imageText);
        } else {
            java.net.URL imageText = getClass().getResource("/Data/serieforsider/" + m.title + ".jpg");
            return (imageText);
        }
    } */

    //Sender pathen til billederne, som så bliver brugt i ImageIcon
    public String getPicture(Media m) {
        if (m instanceof Movie) {
            String imageText = "/Data/filmplakater/" + m.title + ".jpg";
            return (imageText);
        } else {
            String imageText = "/Data/serieforsider/" + m.title + ".jpg";
            return (imageText);
        }
    }

    //Random Medie
    public Media getRandomMedia() {
        int randomNum = ThreadLocalRandom.current().nextInt(0, getMedia().size() + 1);
        return getThisMedia(randomNum);

    }
}