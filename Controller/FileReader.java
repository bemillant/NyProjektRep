package Controller;

import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;

public class FileReader {
    ArrayList<Movie> movies = new ArrayList<>(); // Laver en ArrayListe af movies, om vi fylder op med vores film.txt
    ArrayList<Series> series = new ArrayList<>(); // Laver en ArrayListe af series, om vi fylder op med vores serier.txt

    public void TextReader(String fileName) {
        File file = new File(fileName); // den fil som vi gerne vil gå igennem. Starter med at sørge for at den både
                                        // læser serier og film

        try {
            Scanner input = new Scanner(file); // Scanneren går igang med at gennemgå filen

            while (input.hasNextLine()) { // while-loop som kører til og med linje 100 i txt.filerne
                String line = input.nextLine(); // Linjen er hvad vi gerne med arbejde med - vi tager en ad gangen.
                String tempwords[] = line.split(";"); // deler linjen op ved semi-kolon og indsætter i
                String title = tempwords[0];
                String templine = line.replace(" ", "");
                String words[] = templine.split(";");
                String categories[] = words[2].split(",");
                List<String> categoriesArray = Arrays.asList(categories);
                double rating = Double.parseDouble(words[3].replace(",", "."));

                if (words.length < 5) {
                    movies.add(new Movie(title, words[1], categoriesArray, rating));

                }

                else {
                    String antalSeasons[] = words[4].split(",");
                    String episoder[] = new String[antalSeasons.length];

                    for (int i = 0; i < antalSeasons.length; i++) {
                        String tempArray[] = antalSeasons[i].split("-");
                        episoder[i] = tempArray[1];
                    }
                    List<String> episoderArray = Arrays.asList(episoder);

                    series.add(new Series(title, words[1], categoriesArray, rating, episoderArray));
                }
            }

            input.close();
        }

        catch (FileNotFoundException e) {
            System.out.println(e.getMessage()); // Skal vises visuelt
        }
    }

    public ArrayList<Movie> getMovies() {
        return movies;
    }

    public ArrayList<Series> getSeries() {
        return series;
    }
}