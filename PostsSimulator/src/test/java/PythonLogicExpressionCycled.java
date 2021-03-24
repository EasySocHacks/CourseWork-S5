import exceptions.lexic.tokenizer.TokenizerParseException;
import exceptions.syntax.ExpressionParserException;
import grammar.Grammar;
import syntax.ExpressionParser;
import utils.Table;

import java.util.Scanner;

public class PythonLogicExpressionCycled {
    public static void main(String args[]) {
        Scanner scanner = new Scanner(System.in);

        Grammar grammar = GrammarBuilder.buildGrammar("pythonLogicExpressionGrammar");

        Table table = new Table();
        table.setValue('a', false);
        table.setValue('b', true);
        table.setValue('c', false);

        grammar.getStartNonTerminal().getAttributes().get("table").setValue(table);

        ExpressionParser expressionParser = new ExpressionParser(grammar);
        while (true) {
            try {
                String line = scanner.nextLine();

                ExpressionParser.Node node = expressionParser.parse(line);

                System.out.println(node);
                System.out.println("ANSWER: " + node.getGrammarObject().getAttributes().get("ans").getValue());
            } catch (TokenizerParseException | ExpressionParserException e) {
                e.printStackTrace();
            }
        }
    }
}
