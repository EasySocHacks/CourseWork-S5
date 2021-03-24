package exceptions.grammar;

public class GrammarRuleParseException extends Exception {
    public GrammarRuleParseException(String message) {
        super(message);
    }

    public GrammarRuleParseException(Exception e) {
        super(e);
    }

    public GrammarRuleParseException(String message, Exception e) {
        super(message, e);
    }
}
