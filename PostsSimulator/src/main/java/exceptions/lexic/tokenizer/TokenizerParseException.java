package exceptions.lexic.tokenizer;

public class TokenizerParseException extends Exception {
    public TokenizerParseException(String message) {
        super(message);
    }

    public TokenizerParseException(String message, Exception e) {
        super(message, e);
    }
}
