package boot.service;

import boot.api.DiscoverMovie;
import boot.model.ApiMovie;
import boot.model.Genres;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class APIServiceImpl implements APIService {
    DiscoverMovie dm = new DiscoverMovie();

    @Override
    public CompletableFuture<List<ApiMovie>> find() {
        return dm.findAll();
    }


    @Override
    public CompletableFuture<List<ApiMovie>> findByTitle(String title, int page) {
        return dm.findByTitle(title, page);
    }

    @Override
    public CompletableFuture<List<ApiMovie>> discoverMovies(String year, String genres, String rating, int page) {
        return dm.discoverMovies(year, genres, rating, page);
    }

    @Override
    public CompletableFuture<Integer> pagesByTitle(String title) {
        return dm.pagesByTitle(title);
    }

    @Override
    public CompletableFuture<Integer> pagesDiscovery(String year, String genres, String rating) {
        return dm.pagesDiscovery(year, genres, rating);
    }

    @Override
    public CompletableFuture<List<Genres>> getGenres() {
        return dm.getGenres();
    }
}
