import exceptions.grammar.GrammarRuleParseException;
import grammar.Grammar;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;

import java.util.Scanner;

public class GrammarBuilder {

    public static Grammar buildGrammar(String grammarName) {
        StringBuilder grammarStringBuilder = new StringBuilder();

        Scanner scanner = new Scanner(
                ClassLoader.getSystemClassLoader().getResourceAsStream("grammar/" + grammarName));

        while (scanner.hasNextLine()) {
            grammarStringBuilder.append(scanner.nextLine());

            if (scanner.hasNextLine()) {
                grammarStringBuilder.append(System.lineSeparator());
            }
        }

        AntlrGrammarParserLexer lexer = new AntlrGrammarParserLexer(CharStreams.fromString(grammarStringBuilder.toString()));

        CommonTokenStream commonTokenStream = new CommonTokenStream(lexer);
        AntlrGrammarParserParser grammarParser = new AntlrGrammarParserParser(commonTokenStream);
        Grammar grammar = grammarParser.parse(grammarName).grammar;
        try {
            grammar.buildGrammar();
        } catch (GrammarRuleParseException e) {
            e.printStackTrace();
        }

        return grammar;
    }
}
