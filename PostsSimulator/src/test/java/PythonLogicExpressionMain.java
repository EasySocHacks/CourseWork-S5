import exceptions.lexic.tokenizer.TokenizerParseException;
import exceptions.syntax.ExpressionParserException;
import grammar.Grammar;
import syntax.ExpressionParser;
import utils.Table;
import visualizer.GraphVisualizer;

public class PythonLogicExpressionMain {
    public static void main(String args[]) {
        Grammar grammar = GrammarBuilder.buildGrammar("pythonLogicExpressionGrammar");

        Table table = new Table();
        table.setValue('a', false);
        table.setValue('b', true);
        table.setValue('c', false);

        grammar.getStartNonTerminal().getAttributes().get("table").setValue(table);

        ExpressionParser expressionParser = new ExpressionParser(grammar);
        ExpressionParser.Node node = null;

        try {
            node = expressionParser.parse(" (a and b) or not (c xor (a or not b))");
        } catch (TokenizerParseException | ExpressionParserException e) {
            e.printStackTrace();
        }

        System.out.println(node);

        System.out.println("ANSWER: " + node.getGrammarObject().getAttributes().get("ans").getValue());

        GraphVisualizer graphVisualizer = new GraphVisualizer(node);
        graphVisualizer.visualize();
    }
}
