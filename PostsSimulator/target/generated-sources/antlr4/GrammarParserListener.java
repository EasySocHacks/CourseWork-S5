// Generated from GrammarParser.g4 by ANTLR 4.7.1
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link GrammarParserParser}.
 */
public interface GrammarParserListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link GrammarParserParser#parse}.
	 * @param ctx the parse tree
	 */
	void enterParse(GrammarParserParser.ParseContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarParserParser#parse}.
	 * @param ctx the parse tree
	 */
	void exitParse(GrammarParserParser.ParseContext ctx);
}