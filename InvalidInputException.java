public class InvalidInputException extends IsraeliQueueException {
    public InvalidInputException(){}
    public InvalidInputException(String message) {
        super(message);
    }

    public InvalidInputException(String message, Throwable cause) {
        super(message, cause);
    }
}
