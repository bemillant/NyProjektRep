package Controller;

public class Test {

    // Get medias from genre test
    public static void test1() {
        MediaDatabase mdb = new MediaDatabase();
        String genre = "Western";
        for (int i = 0; i < mdb.getMovieGenres(genre).size(); i++) {
            System.out.println(mdb.getMovieGenres(genre).get(i).title);
        }
    }

    // myList and toprated test
    public static void test2() {
        MediaDatabase mdb = new MediaDatabase();
        User bruger = new User("bruger");
        bruger.addMyList(mdb.topMovies().get(0));
        bruger.addMyList(mdb.topSeries().get(0));
        System.out.println(bruger.myList.get(0).title + " and " + bruger.myList.get(1).title);
    }

    // Search test
    public static void test3() {
        MediaDatabase mdb = new MediaDatabase();
        try {
            mdb.search("Grundlæggende programmering");
        } catch (MediaNotFoundException e) {
            System.out.println(e.getIllegalName() + e.getMessage());
        }
    }

    // Get picture test
    /*
     * public static void test4() { MediaDatabase mdb = new MediaDatabase(); if
     * ("filmplakater/12 Angry Men.jpg".equals(mdb.getPicture(mdb.movies.get(17))))
     * { System.out.println("The picture does exist"); } else {
     * System.out.println("The picture does not exist"); } }
     */
}
