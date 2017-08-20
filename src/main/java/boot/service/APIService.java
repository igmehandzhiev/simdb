package boot.service;

import boot.model.ApiMovie;
import boot.model.Genres;

import java.util.List;

public interface APIService {
    public List<ApiMovie> find();

    public List<ApiMovie> findByTitle(String title, int page);

    public List<ApiMovie> discoverMovies(String year, String genres, String rating, int page);

    public int pagesByTitle(String title);

    public int pagesDiscovery(String year, String genres, String rating);

    public List<Genres> getGenres();
}
