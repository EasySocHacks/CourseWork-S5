import grammar.Grammar;
import syntax.ExpressionParser;

public class BaseTests {
    protected Grammar grammar;
    protected ExpressionParser expressionParser;

    public BaseTests(String grammarName) {
        this.grammar = GrammarBuilder.buildGrammar(grammarName);
        this.expressionParser = new ExpressionParser(grammar);
    }
}
