package exception;

public class IllegalRequestTypeException extends IllegalArgumentException {

    public IllegalRequestTypeException(String message) {
        super(message);
    }
}
