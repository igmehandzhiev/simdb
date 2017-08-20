package boot.controller;

import boot.exception.MovieNotFoundException;
import boot.model.ApiMovie;
import boot.model.Genres;
import boot.service.APIService;
import boot.service.MovieService;
import boot.model.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movie")
public class MovieController {

    private final MovieService service;
    private final APIService apiService;

    @Autowired
    MovieController(MovieService service, APIService apiService) {
        this.service = service;
        this.apiService = apiService;
    }

    //    @RequestMapping(method = RequestMethod.POST)
//    @ResponseStatus(HttpStatus.CREATED)
//    MovieDTO create(@RequestBody @Valid MovieDTO movieEntry) {
//        return service.create(movieEntry);
//    }
//
//    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
//    MovieDTO delete(@PathVariable("id") String id) {
//        return service.delete(id);
//    }

    @RequestMapping(value = "/search/api/genres")
    List<Genres> getGenres() {
        return apiService.getGenres();
    }

    @RequestMapping(value = "/search/api/all")
    List<ApiMovie> findApiAll() {
        return apiService.find();
    }

    @RequestMapping(value = "/search/api/byTitle", method = RequestMethod.GET)
    List<ApiMovie> findApiByTitle(@RequestParam("t") String title, @RequestParam("p") int page) {
        return apiService.findByTitle(title, page);
    }

    @RequestMapping(value = "/search/api/byTitle/pages", method = RequestMethod.GET)
    int pagesByTitle(@RequestParam("t") String title) {
        return apiService.pagesByTitle(title);
    }

    @RequestMapping(value = "/search/api/discover", method = RequestMethod.GET)
    List<ApiMovie> discoverMovies(@RequestParam("y") String year, @RequestParam("g") String genres, @RequestParam("r") String rating, @RequestParam("p") int page) {
        return apiService.discoverMovies(year, genres, rating, page);
    }

    @RequestMapping(value = "/search/api/discover/pages", method = RequestMethod.GET)
    int pagesDiscovery(@RequestParam("y") String year, @RequestParam("g") String genres, @RequestParam("r") String rating) {
        return apiService.pagesDiscovery(year, genres, rating);
    }


    @RequestMapping(value = "/search/all", method = RequestMethod.GET)
    List<Movie> findAll() {
        return service.findAll();
    }

    @RequestMapping(value = "/search/byTitle", method = RequestMethod.GET)
    List<Movie> findByTitle(@RequestParam("t") String title) {
        return service.findByTitle(title);
    }

    @RequestMapping(value = "/search/byGenres", method = RequestMethod.GET)
    List<Movie> findByGenres(@RequestParam("g") String[] genres) {
        return service.findByGenres(genres);
    }

    @RequestMapping(value = "/search/tyr", method = RequestMethod.GET)
    List<Movie> findByTitleYearRating(@RequestParam("t") String title, @RequestParam("y") String year, @RequestParam("r") Double rating) {
        return service.findByTitleYearRating(title, year, rating);
    }

    @RequestMapping(value = "/search/tygr", method = RequestMethod.GET)
    List<Movie> findByTitleYearGenreRating(@RequestParam("t") String title, @RequestParam("y") String year,
                                           @RequestParam("g") String[] genres, @RequestParam("r") Double rating) {
        return service.findByTitleYearGenreRating(title, year, genres, rating);
    }


//    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
//    MovieDTO findById(@PathVariable("id") String id) {
//        return service.findById(id);
//    }
//    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
//    MovieDTO update(@RequestBody @Valid MovieDTO movieEntry) {
//        return service.update(movieEntry);
//    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public void handleTodoNotFound(MovieNotFoundException ex) {
    }
}
