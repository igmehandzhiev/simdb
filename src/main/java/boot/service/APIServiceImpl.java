package boot.service;

import boot.api.DiscoverMovie;
import boot.model.ApiMovie;
import boot.model.Genres;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class APIServiceImpl implements APIService {
    DiscoverMovie dm = new DiscoverMovie();

    @Override
    public List<ApiMovie> find() {
        return dm.findAll();
    }


    @Override
    public List<ApiMovie> findByTitle(String title, int page) {
        return dm.findByTitle(title, page);
    }

    @Override
    public List<ApiMovie> discoverMovies(String year, String genres, String rating, int page) {
        return dm.discoverMovies(year, genres, rating, page);
    }

    @Override
    public int pagesByTitle(String title) {
        return dm.pagesByTitle(title);
    }

    @Override
    public int pagesDiscovery(String year, String genres, String rating) {
        return dm.pagesDiscovery(year, genres, rating);
    }

    @Override
    public List<Genres> getGenres() {
        return dm.getGenres();
    }
}
