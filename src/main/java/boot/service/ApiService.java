package boot.service;

import boot.model.ApiMovie;
import boot.model.Genres;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface ApiService {
    CompletableFuture<List<ApiMovie>> find();

    CompletableFuture<List<ApiMovie>> findByTitle(
            String title, int page);

    CompletableFuture<List<ApiMovie>> discoverMovies(
            String year, String genres, String rating, int page);

    CompletableFuture<Integer> pagesByTitle(
            String title);

    CompletableFuture<Integer> pagesDiscovery(
            String year, String genres, String rating);

    CompletableFuture<List<Genres>> getGenres();


    List<ApiMovie> findByTitleList(String title, int page);

}
