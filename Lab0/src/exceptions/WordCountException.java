package exceptions;

public class WordCountException extends Exception {
    public WordCountException(String message) {
        super(message);
    }

    public WordCountException(String message, Throwable cause) {
        super(message, cause);
    }
}