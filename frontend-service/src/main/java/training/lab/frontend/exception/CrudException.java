package training.lab.frontend.exception;

public class CrudException extends RuntimeException {
    public CrudException(String message) {
        super(message);
    }
}
