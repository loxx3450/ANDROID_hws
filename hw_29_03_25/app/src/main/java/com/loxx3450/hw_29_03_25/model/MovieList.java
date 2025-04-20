package com.loxx3450.hw_29_03_25.model;

import java.util.ArrayList;
import java.util.List;

public class MovieList {
    public static List<Movie> getMovies() {
        List<Movie> movies = new ArrayList<>();

        movies.add(new Movie("Inception", "A thief who steals corporate secrets through the use of dream-sharing technology."));
        movies.add(new Movie("The Matrix", "A computer hacker learns about the true nature of his reality."));
        movies.add(new Movie("Interstellar", "A team of explorers travel through a wormhole in space in an attempt to ensure humanity's survival."));
        movies.add(new Movie("The Dark Knight", "Batman faces off against the Joker, a criminal mastermind."));
        movies.add(new Movie("The Shawshank Redemption", "Two imprisoned men bond over a number of years, finding solace and redemption."));

        return movies;
    }
}
