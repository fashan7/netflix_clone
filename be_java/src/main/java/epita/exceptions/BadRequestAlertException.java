package epita.exceptions;

public class BadRequestAlertException extends RuntimeException {
    public BadRequestAlertException(String message) {
        super(message);
    }
}
