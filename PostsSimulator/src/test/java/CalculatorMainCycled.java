import exceptions.lexic.tokenizer.TokenizerParseException;
import exceptions.syntax.ExpressionParserException;
import grammar.Grammar;
import syntax.ExpressionParser;

import java.util.Scanner;

public class CalculatorMainCycled {
    public static void main(String args[]) {
        Scanner scanner = new Scanner(System.in);

        Grammar grammar = GrammarBuilder.buildGrammar("calculatorModificationGrammar");

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
