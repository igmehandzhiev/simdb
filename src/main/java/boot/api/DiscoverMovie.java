package boot.api;


import boot.model.*;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.concurrent.CompletableFuture;


public class DiscoverMovie {


    private final RestTemplate restTemplate = new RestTemplateBuilder().build();

    @Async
    public CompletableFuture<List<ApiMovie>> findAll() {
        MoviesRequest moviesRequest;
        List<ApiMovie> movies;
        String url = "https://api.themoviedb.org/3/discover/movie?api_key=a151937bc1aec8b39512fddf626d4625&language=en-US&sort_by=popularity.desc&page=1";
        moviesRequest = restTemplate.getForObject(url, MoviesRequest.class);
        movies = moviesRequest.getResults();
        int pages = moviesRequest.getTotal_pages();
        int limit = Integer.parseInt(restTemplate.headForHeaders(url).getFirst("X-RateLimit-Limit"));
        for (int i = 2; i < limit; ++i) {
            url = "https://api.themoviedb.org/3/discover/movie?api_key=a151937bc1aec8b39512fddf626d4625&language=en-US&sort_by=popularity.desc&page="
                    + i;
//            request = IOUtils.toString(new URL(url), "UTF-8");
//            jsonObject = new JSONObject(request);
//            moviesRequest = gson.fromJson(jsonObject.toString(), MoviesRequest.class);
//            movies = moviesRequest.getResults();
            moviesRequest = restTemplate.getForObject(url, MoviesRequest.class);
            movies.addAll(moviesRequest.getResults());
        }
        return CompletableFuture.completedFuture(movies);
    }

    @Async
    public CompletableFuture<List<ApiMovie>> findByTitle(String title, int page) {
        MoviesRequest moviesRequest;
        List<ApiMovie> movies;
        String url = urlByTitle(title, page);
        moviesRequest = restTemplate.getForObject(url, MoviesRequest.class);
        movies = moviesRequest.getResults();
        return CompletableFuture.completedFuture(movies);
    }

    @Async
    public CompletableFuture<List<ApiMovie>> discoverMovies(String year, String genres, String rating, int page) {
        MoviesRequest moviesRequest;
        List<ApiMovie> movies;
        String url = urlByDiscovery(year, genres, rating, page);
        moviesRequest = restTemplate.getForObject(url, MoviesRequest.class);
        movies = moviesRequest.getResults();
        return CompletableFuture.completedFuture(movies);
    }

    @Async
    public CompletableFuture<Integer> pagesByTitle(String title) {
        MoviesRequest moviesRequest;
        String url = urlByTitle(title, 1);
        moviesRequest = restTemplate.getForObject(url, MoviesRequest.class);
        return CompletableFuture.completedFuture(moviesRequest.getTotal_pages());
    }

    @Async
    public CompletableFuture<Integer> pagesDiscovery(String year, String genres, String rating) {
        MoviesRequest moviesRequest;
        String url = urlByDiscovery(year, genres, rating, 1);
        moviesRequest = restTemplate.getForObject(url, MoviesRequest.class);
        return CompletableFuture.completedFuture(moviesRequest.getTotal_pages());
    }

    @Async
    public CompletableFuture<List<Genres>> getGenres() {
        String url = "https://api.themoviedb.org/3/genre/movie/list?api_key=a151937bc1aec8b39512fddf626d4625&language=en-US";
        GenresRequest genresRequest;

        genresRequest = restTemplate.getForObject(url, GenresRequest.class);
        genresRequest.setFalse();
        return CompletableFuture.completedFuture(genresRequest.getGenres());
    }

    private String urlByTitle(String title, int page) {
        return "https://api.themoviedb.org/3/search/movie?api_key=a151937bc1aec8b39512fddf626d4625&language=en-US"
                + "&query=" + title
                + "&page=" + page;
    }

    private String urlByDiscovery(String year, String genres, String rating, int page) {
        return "https://api.themoviedb.org/3/discover/movie?api_key=a151937bc1aec8b39512fddf626d4625&language=en-US&sort_by=popularity.desc"
                + "&primary_release_date.gte=" + year
                + "&vote_average.gte=" + rating
                + "&with_genres=" + genres
                + "&year=" + year
                + "&page=" + page;
    }

}
