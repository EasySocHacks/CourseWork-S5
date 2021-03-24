import exceptions.lexic.tokenizer.TokenizerParseException;
import exceptions.syntax.ExpressionParserException;
import grammar.Grammar;
import syntax.ExpressionParser;

public class CalculatorMain {
    public static void main(String args[]) {
        Grammar grammar = GrammarBuilder.buildGrammar("calculatorGrammar");

        ExpressionParser expressionParser = new ExpressionParser(grammar);
        ExpressionParser.Node node = null;

        try {
            node = expressionParser.parse("1 + (1 + 0 / 123 / 1 * 2 + 3*2) + 5 + 1 / 1 * 2");
        } catch (TokenizerParseException | ExpressionParserException e) {
            e.printStackTrace();
        }

        System.out.println(node);
        System.out.println("ANSWER: " + node.getGrammarObject().getAttributes().get("ans").getValue());
    }
}
