package boot.service;


import boot.model.Movie;

import java.util.List;

public interface MovieService {
//    MovieDTO create(MovieDTO movie);

//    MovieDTO delete(String id);

    List<Movie> findAll();

    List<Movie> findByTitle(String title);

    List<Movie> findByGenres(String[] genres);

    List<Movie> findByTitleYearRating(String title, String year, Double rating);

    List<Movie> findByTitleYearGenreRating(String title, String year, String[] genres, Double rating);


//    Movie update(Movie movie);
}
