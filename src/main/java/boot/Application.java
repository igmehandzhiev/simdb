package boot;


import boot.repository.MoviesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class Application implements CommandLineRunner {
    @Autowired
    private MoviesRepository moviesRepository;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... strings) throws Exception {

//        System.out.println("Movies found with findByGenres():");
//        System.out.println("-------------------------------");
//        String[] genres = {"Action", "Comedy"};
//        for (Movie movie : moviesRepository.findMovieByGenres(genres)) {
//            System.out.println(movie);
//        }
//        System.out.println();
//        System.out.println("Movies found with findByActorsContaining():");
//        System.out.println("-------------------------------");
//        String actor = "Aamir Khan";
//        for (Movie movie : moviesRepository.findMovieByActorsContaining(actor)) {
//            System.out.println(movie);
//        }
//        System.out.println();
//        System.out.println("Movies found with findByDuration>120():");
//        System.out.println("-------------------------------");
//
//        for (Movie movie : moviesRepository.findMovieByDurationContaining("120")) {
//            System.out.println(movie);
//        }
//        System.out.println();
//        System.out.println("Movies found with imdb>():");
//        System.out.println("-------------------------------");
//        for (Movie movie : moviesRepository.findMoviesByImdbRatingGreaterThanEqual(7.5)) {
//            System.out.println(movie);
//        }
//        System.out.println();
//        System.out.println("Movies found with titleContains():");
//        System.out.println("-------------------------------");
//        for (Movie movie : moviesRepository.findMoviesByTitleContaining("The")) {
//            System.out.println(movie);
//        }
//        System.out.println();
//        System.out.println();
//
//        System.out.println("Movie found with findMovieByTitle('The Shack'):");
//        System.out.println("--------------------------------");
//        System.out.println(moviesRepository.findMovieByTitle("The Shack"));
//        System.out.println("Movies found with findByYear('2017'):");
//        System.out.println("--------------------------------");
//        for (Movie movie : moviesRepository.findMovieByYearEquals("2017")) {
//            System.out.println(movie);
//        }
//        System.out.println("--------------------------------");
    }
}
