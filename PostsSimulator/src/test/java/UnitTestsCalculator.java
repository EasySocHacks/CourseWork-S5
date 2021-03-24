import exceptions.lexic.tokenizer.TokenizerParseException;
import exceptions.syntax.ExpressionParserException;
import org.junit.Test;
import syntax.ExpressionParser;

import static org.junit.Assert.assertEquals;

public class UnitTestsCalculator extends BaseTests {
    public UnitTestsCalculator() {
        super("calculatorGrammar");
    }

    @Test
    public void test_1() throws ExpressionParserException, TokenizerParseException {
        String expression = "1 + 2";
        String answerString = "1 + 2";
        int answerValue = 3;

        ExpressionParser.Node node = expressionParser.parse(expression);

        assertEquals(answerString, node.toString());
        assertEquals(answerValue, node.getGrammarObject().getAttributes().get("ans").getValue());
    }

    @Test
    public void test_2() throws ExpressionParserException, TokenizerParseException {
        String expression = "     1 +2 *(123/123*2)+   (1 * 2   )   ";
        String answerString = "1 + 2 * ( 123 / 123 * 2 ) + ( 1 * 2 )";
        int answerValue = 7;

        ExpressionParser.Node node = expressionParser.parse(expression);

        assertEquals(answerString, node.toString());
        assertEquals(answerValue, node.getGrammarObject().getAttributes().get("ans").getValue());
    }

    @Test
    public void test_3() throws ExpressionParserException, TokenizerParseException {
        String expression = "(1) * 2 * 2 * 2 * 2 * 2 * 2";
        String answerString = "( 1 ) * 2 * 2 * 2 * 2 * 2 * 2";
        int answerValue = 64;

        ExpressionParser.Node node = expressionParser.parse(expression);

        assertEquals(answerString, node.toString());
        assertEquals(answerValue, node.getGrammarObject().getAttributes().get("ans").getValue());
    }

    @Test
    public void test_4() throws ExpressionParserException, TokenizerParseException {
        String expression = "1 + (1 + 0 / 123 / 1 * 2 + 3*2) + 5 + 1 / 1 * 2";
        String answerString = "1 + ( 1 + 0 / 123 / 1 * 2 + 3 * 2 ) + 5 + 1 / 1 * 2";
        int answerValue = 15;

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
        String expression = "1 *+ 2";
        expressionParser.parse(expression);
    }

    @Test(expected = ExpressionParserException.class)
    public void parseExceptionTest_2() throws ExpressionParserException, TokenizerParseException {
        String expression = "";
        expressionParser.parse(expression);
    }

    @Test(expected = ExpressionParserException.class)
    public void parseExceptionTest_3() throws ExpressionParserException, TokenizerParseException {
        String expression = "+";
        expressionParser.parse(expression);
    }

    @Test(expected = ExpressionParserException.class)
    public void parseExceptionTest_4() throws ExpressionParserException, TokenizerParseException {
        String expression = "  + 1 ";
        expressionParser.parse(expression);
    }

    @Test(expected = ExpressionParserException.class)
    public void parseExceptionTest_5() throws ExpressionParserException, TokenizerParseException {
        String expression = "()";
        expressionParser.parse(expression);
    }
}
