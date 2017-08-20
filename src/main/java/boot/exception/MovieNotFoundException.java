package boot.exception;

public class MovieNotFoundException extends RuntimeException {
    public MovieNotFoundException(String id) {
        super(String.format("No todo entry found with id: <%s>", id));
    }
}
