package boot.model;

import org.springframework.data.annotation.Id;

import java.util.Arrays;

public final class Movie {
    //not sure if to assign to builder
    @Id
    private String id;

    private String title;
    private String year;
    private String[] genres;
    private Double[] ratings;
    private String poster;
    private String contentRating;
    private String duration;
    private String releaseDate;
    private Double averageRating;
    private String originalTitle;
    private String storyline;
    private String[] actors;
    private Double imdbRating;
    private String posterurl;

    public Movie() {
    }

    public Movie(String id, String title, String year, String[] genres,
                 Double[] ratings, String poster, String contentRating,
                 String duration, String releaseDate, Double averageRating,
                 String originalTitle, String storyline, String[] actors,
                 Double imdbRating, String posterurl) {
        this.id = id;
        this.title = title;
        this.year = year;
        this.genres = genres;
        this.ratings = ratings;
        this.poster = poster;
        this.contentRating = contentRating;
        this.duration = duration;
        this.releaseDate = releaseDate;
        this.averageRating = averageRating;
        this.originalTitle = originalTitle;
        this.storyline = storyline;
        this.actors = actors;
        this.imdbRating = imdbRating;
        this.posterurl = posterurl;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String[] getGenres() {
        return genres;
    }

    public void setGenres(String[] genres) {
        this.genres = genres;
    }

    public Double[] getRatings() {
        return ratings;
    }

    public void setRatings(Double[] ratings) {
        this.ratings = ratings;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getContentRating() {
        return contentRating;
    }

    public void setContentRating(String contentRating) {
        this.contentRating = contentRating;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Double getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(Double averageRating) {
        this.averageRating = averageRating;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
    }

    public String getStoryline() {
        return storyline;
    }

    public void setStoryline(String storyline) {
        this.storyline = storyline;
    }

    public String[] getActors() {
        return actors;
    }

    public void setActors(String[] actors) {
        this.actors = actors;
    }

    public Double getImdbRating() {
        return imdbRating;
    }

    public void setImdbRating(Double imdbRating) {
        this.imdbRating = imdbRating;
    }

    public String getPosterurl() {
        return posterurl;
    }

    public void setPosterurl(String posterurl) {
        this.posterurl = posterurl;
    }

    @Override
    public String toString() {
        return "Movie{" +
                ", title='" + title + '\'' +
                ", year=" + year +
                ", genres=" + Arrays.toString(genres) +
                ", contentRating='" + contentRating + '\'' +
                ", duration='" + duration + '\'' +
                ", actors=" + Arrays.toString(actors) +
                ", imdbRating=" + imdbRating +
                '}';
    }


}
