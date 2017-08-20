package boot.model;

import java.util.List;

public class GenresRequest {
    List<Genres> genres;

    public GenresRequest() {
    }

    public List<Genres> getGenres() {
        return genres;
    }

    public void setGenres(List<Genres> genres) {
        this.genres = genres;
    }

    public void setFalse() {
        for (Genres genre :
                genres) {
            genre.setShow(false);
        }
    }

    @Override
    public String toString() {
        return "GenresRequest{" +
                "genres=" + genres +
                '}';
    }
}
