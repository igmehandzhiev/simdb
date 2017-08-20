package boot.service;

import boot.model.ApiMovie;
import boot.model.Genres;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface APIService {
    public CompletableFuture<List<ApiMovie>> find();

    public CompletableFuture<List<ApiMovie>> findByTitle(String title, int page);

    public CompletableFuture<List<ApiMovie>> discoverMovies(String year, String genres, String rating, int page);

    public CompletableFuture<Integer> pagesByTitle(String title);

    public CompletableFuture<Integer> pagesDiscovery(String year, String genres, String rating);

    public CompletableFuture<List<Genres>> getGenres();
}
