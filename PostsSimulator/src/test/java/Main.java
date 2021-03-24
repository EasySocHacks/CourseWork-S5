import exceptions.lexic.tokenizer.TokenizerParseException;
import exceptions.syntax.ExpressionParserException;
import grammar.Grammar;
import syntax.ExpressionParser;
import visualizer.GraphVisualizer;

public class Main {
    public static void main(String args[]) {
        Grammar grammar = GrammarBuilder.buildGrammar("calculatorGrammar");

        ExpressionParser expressionParser = new ExpressionParser(grammar);
        ExpressionParser.Node node = null;

        try {
            node = expressionParser.parse("(1 + 2)");
        } catch (TokenizerParseException | ExpressionParserException e) {
            e.printStackTrace();
        }

        System.out.println("ANSWER: " + node.getGrammarObject().getAttributes().get("ans").getValue());

        GraphVisualizer graphVisualizer = new GraphVisualizer(node);
        graphVisualizer.visualize();
    }
}
