package exceptions.syntax;

public class ExpressionParserException extends Exception {
    public ExpressionParserException(String message) {
        super(message);
    }

    public ExpressionParserException(String message, Exception e) {
        super(message, e);
    }
}
