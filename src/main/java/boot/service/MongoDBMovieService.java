package boot.service;

import boot.MovieDTO;
import boot.model.Movie;
import boot.repository.MoviesRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
public class MongoDBMovieService implements MovieService {
    private final MoviesRepository repository;

    private static final Logger LOGGER = LoggerFactory.getLogger(MongoDBMovieService.class);

    @Autowired
    public MongoDBMovieService(MoviesRepository moviesRepository) {
        this.repository = moviesRepository;
    }


//    @Override
//    public MovieDTO create(MovieDTO movie) {
//        Movie persisted = Movie.getBuilder()
//                .title(movie.getTitle())
//                .year(movie.getYear())
//                .actors(movie.getActors())
//                .duration(movie.getDuration())
//                .genres(movie.getGenres())
//                .imdbRating(movie.getImdbRating())
//                .originalTitle(movie.getOriginalTitle())
//                .poster(movie.getPoster())
//                .build();
//        persisted = repository.save(persisted);
//        return convertMovieDTO(persisted);
//    }

//    @Override
//    public MovieDTO delete(String id) {
//        Movie deleted = findMovieById(id);
//        repository.delete(deleted);
//        return convertMovieDTO(deleted);
//    }

    @Override
    public List<Movie> findAll() {
        List<Movie> movies = repository.findAll();
        LOGGER.info("Found {} movies entries", movies.size());
        return movies;
    }

    @Override
    public List<Movie> findByTitle(String title) {
        List<Movie> movies = repository.findDistinctByTitleContaining(title);
        LOGGER.info("Found {} movies entries", movies.size());
        return movies;

    }

    @Override
    public List<Movie> findByGenres(String[] genres) {
        List<Movie> movies = repository.findDistinctByGenresEquals(genres);
        LOGGER.info("Found {} movies entries", movies.size());
        return movies;
    }

    @Override
    public List<Movie> findByTitleYearRating(String title, String year, Double rating) {
        List<Movie> movies = repository.findDistinctByTitleContainingAndYearGreaterThanEqualAndImdbRatingGreaterThanEqual(title, year, rating);
        LOGGER.info("Found {} movies entries", movies.size());
        return movies;
    }

    @Override
    public List<Movie> findByTitleYearGenreRating(String title, String year, String[] genres, Double rating) {
        List<Movie> movies = repository.findDistinctByTitleContainingAndYearEqualsAndGenresContainingAndImdbRatingGreaterThanEqual(title, year, genres, rating);
        LOGGER.info("Found {} movies entries", movies.size());
        return movies;
    }

    private List<MovieDTO> convertToDTOs(List<Movie> models) {
        return models.stream()
                .map(this::convertMovieDTO)
                .collect(toList());
    }


    private MovieDTO convertMovieDTO(Movie model) {
        MovieDTO dto = new MovieDTO();
        dto.setId(model.getId());
        dto.setTitle(model.getTitle());
        dto.setYear(model.getYear());
        dto.setActors(model.getActors());
        dto.setDuration(model.getDuration());
        dto.setGenres(model.getGenres());
        dto.setImdbRating(model.getImdbRating());
        model.getOriginalTitle();
        model.getPoster();
        return dto;
    }
//    @Override
//    public MovieDTO update(MovieDTO MovieDTO) {
//        MovieDTO updated = findMovieDTOById(MovieDTO.getId());
//        updated.update(MovieDTO.getTitle(), MovieDTO.getYear());
//        updated = repository.save(updated);
//        return convertMovieDTO(updated);
//    }
}
