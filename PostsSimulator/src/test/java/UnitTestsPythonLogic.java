import exceptions.lexic.tokenizer.TokenizerParseException;
import exceptions.syntax.ExpressionParserException;
import org.junit.Test;
import syntax.ExpressionParser;
import utils.Table;

import static org.junit.Assert.assertEquals;

public class UnitTestsPythonLogic extends BaseTests {
    public UnitTestsPythonLogic() {
        super("pythonLogicExpressionGrammar");
    }

    @Test
    public void test_1() throws ExpressionParserException, TokenizerParseException {
        String expression = "  a and b  or (c and d)   ";
        String answerString = "a and b or ( c and d )";

        Table table = new Table();
        table.setValue('a', false);
        table.setValue('b', true);
        table.setValue('c', false);
        table.setValue('d', false);

        grammar.getStartNonTerminal().getAttributes().get("table").setValue(table);

        boolean answerValue = false;

        ExpressionParser.Node node = expressionParser.parse(expression);

        assertEquals(answerString, node.toString());
        assertEquals(answerValue, node.getGrammarObject().getAttributes().get("ans").getValue());
    }

    @Test
    public void test_2() throws ExpressionParserException, TokenizerParseException {
        String expression = "  a and b  or (c and d)   ";
        String answerString = "a and b or ( c and d )";

        Table table = new Table();
        table.setValue('a', false);
        table.setValue('b', true);
        table.setValue('c', true);
        table.setValue('d', true);

        grammar.getStartNonTerminal().getAttributes().get("table").setValue(table);

        boolean answerValue = true;

        ExpressionParser.Node node = expressionParser.parse(expression);

        assertEquals(answerString, node.toString());
        assertEquals(answerValue, node.getGrammarObject().getAttributes().get("ans").getValue());
    }

    @Test
    public void test_3() throws ExpressionParserException, TokenizerParseException {
        String expression = " (a and b) or not (c xor (a or not b))";
        String answerString = "( a and b ) or not ( c xor ( a or not b ) )";

        Table table = new Table();
        table.setValue('a', false);
        table.setValue('b', true);
        table.setValue('c', false);

        grammar.getStartNonTerminal().getAttributes().get("table").setValue(table);

        boolean answerValue = true;

        ExpressionParser.Node node = expressionParser.parse(expression);

        assertEquals(answerString, node.toString());
        assertEquals(answerValue, node.getGrammarObject().getAttributes().get("ans").getValue());
    }

    @Test(expected = TokenizerParseException.class)
    public void lexicalAnalyzerExceptionTest() throws ExpressionParserException, TokenizerParseException {
        String expression = "abacaba";
        expressionParser.parse(expression);
    }

    @Test(expected = ExpressionParserException.class)
    public void parseExceptionTest_1() throws ExpressionParserException, TokenizerParseException {
        String expression = "a and or";
        expressionParser.parse(expression);
    }

    @Test(expected = ExpressionParserException.class)
    public void parseExceptionTest_2() throws ExpressionParserException, TokenizerParseException {
        String expression = "";
        expressionParser.parse(expression);
    }

    @Test(expected = ExpressionParserException.class)
    public void parseExceptionTest_3() throws ExpressionParserException, TokenizerParseException {
        String expression = "and";
        expressionParser.parse(expression);
    }

    @Test(expected = ExpressionParserException.class)
    public void parseExceptionTest_4() throws ExpressionParserException, TokenizerParseException {
        String expression = "  and a ";
        expressionParser.parse(expression);
    }

    @Test(expected = ExpressionParserException.class)
    public void parseExceptionTest_5() throws ExpressionParserException, TokenizerParseException {
        String expression = "()";
        expressionParser.parse(expression);
    }
}
