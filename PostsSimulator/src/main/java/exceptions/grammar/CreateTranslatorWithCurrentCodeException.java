package exceptions.grammar;

public class CreateTranslatorWithCurrentCodeException extends Exception {
    public CreateTranslatorWithCurrentCodeException(String message) {
        super(message);
    }

    public CreateTranslatorWithCurrentCodeException(String message, Exception e) {
        super(message, e);
    }
}
