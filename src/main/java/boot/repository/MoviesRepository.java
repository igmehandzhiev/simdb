package boot.repository;

import boot.model.Movie;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

//@RepositoryRestResource(collectionResourceRel = "movie", path = "movie")
public interface MoviesRepository extends MongoRepository<Movie, String> {

    /*private final String title;
    private int year;
    private String[] genres;
    private double[] ratings;
    private String poster;
    private String contentRating;
    private String duration;
    private String releaseDate;
    private double averageRating;
    private String originalTitle;
    private String storyline;
    private String[] actors;
    private double imdbRating;
    private String posterurl;
    */

    public List<Movie> findAll();

    public List<Movie> findDistinctByTitleContaining(@Param("t") String t);

    public List<Movie> findDistinctByTitleContainingAndYearGreaterThanEqualAndImdbRatingGreaterThanEqual
            (@Param("t") String title, @Param("y") String year, @Param("r") Double rating);

    public List<Movie> findDistinctByGenresEquals(@Param("g") String[] genres);

    public List<Movie> findDistinctByTitleContainingAndYearEqualsAndGenresContainingAndImdbRatingGreaterThanEqual
            (@Param("t") String title, @Param("y") String year, @Param("g") String[] genres, @Param("r") Double rating);
}
