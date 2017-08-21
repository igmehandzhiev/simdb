package boot.service;

import boot.api.DiscoverMovie;
import boot.model.ApiMovie;
import boot.model.Genres;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class ApiServiceImpl implements ApiService {
    private DiscoverMovie dm = new DiscoverMovie();

    public final List<ApiMovie> findByTitleList(final String title, int page) {
        return getDm().findByTitleList(title, page);
    }

    @Async
    @Override
    public CompletableFuture<List<ApiMovie>> find() {
        return getDm().findAll();
    }

    @Async
    @Override
    public CompletableFuture<List<ApiMovie>> findByTitle(
            final String title, final int page) {
        return getDm().findByTitle(title, page);
    }


    @Async
    @Override
    public CompletableFuture<List<ApiMovie>> discoverMovies(
            final String year, final String genres,
            final String rating, final int page) {
        return getDm().discoverMovies(year, genres, rating, page);
    }

    @Async
    @Override
    public CompletableFuture<Integer> pagesByTitle(final String title) {
        return dm.pagesByTitle(title);
    }

    @Async
    @Override
    public CompletableFuture<Integer> pagesDiscovery(
            final String year, final String genres, final String rating) {
        return getDm().pagesDiscovery(year, genres, rating);
    }

    @Async
    @Override
    public CompletableFuture<List<Genres>> getGenres() {
        return getDm().getGenres();
    }

    public DiscoverMovie getDm() {
        return dm;
    }

}
