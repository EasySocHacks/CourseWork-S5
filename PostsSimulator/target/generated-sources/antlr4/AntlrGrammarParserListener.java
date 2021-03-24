// Generated from AntlrGrammarParser.g4 by ANTLR 4.7.1

    import grammar.Grammar;
    import grammar.objects.terminals.Terminal;
    import grammar.objects.terminals.Terminal.TerminalOption;
    import grammar.objects.nonterminals.NonTerminal;
    import grammar.rules.Rule;
    import grammar.objects.GrammarObject;
    import grammar.objects.nonterminals.translators.Translator;
    import grammar.objects.nonterminals.translators.Translator.Code;
    import grammar.objects.nonterminals.translators.Translator.TranslatorType;
    import grammar.objects.attributes.Attribute;
    import exceptions.grammar.CreateTranslatorWithCurrentCodeException;

import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link AntlrGrammarParserParser}.
 */
public interface AntlrGrammarParserListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link AntlrGrammarParserParser#parse}.
	 * @param ctx the parse tree
	 */
	void enterParse(AntlrGrammarParserParser.ParseContext ctx);
	/**
	 * Exit a parse tree produced by {@link AntlrGrammarParserParser#parse}.
	 * @param ctx the parse tree
	 */
	void exitParse(AntlrGrammarParserParser.ParseContext ctx);
	/**
	 * Enter a parse tree produced by {@link AntlrGrammarParserParser#terminalList}.
	 * @param ctx the parse tree
	 */
	void enterTerminalList(AntlrGrammarParserParser.TerminalListContext ctx);
	/**
	 * Exit a parse tree produced by {@link AntlrGrammarParserParser#terminalList}.
	 * @param ctx the parse tree
	 */
	void exitTerminalList(AntlrGrammarParserParser.TerminalListContext ctx);
	/**
	 * Enter a parse tree produced by {@link AntlrGrammarParserParser#terminal}.
	 * @param ctx the parse tree
	 */
	void enterTerminal(AntlrGrammarParserParser.TerminalContext ctx);
	/**
	 * Exit a parse tree produced by {@link AntlrGrammarParserParser#terminal}.
	 * @param ctx the parse tree
	 */
	void exitTerminal(AntlrGrammarParserParser.TerminalContext ctx);
	/**
	 * Enter a parse tree produced by {@link AntlrGrammarParserParser#terminalOption}.
	 * @param ctx the parse tree
	 */
	void enterTerminalOption(AntlrGrammarParserParser.TerminalOptionContext ctx);
	/**
	 * Exit a parse tree produced by {@link AntlrGrammarParserParser#terminalOption}.
	 * @param ctx the parse tree
	 */
	void exitTerminalOption(AntlrGrammarParserParser.TerminalOptionContext ctx);
	/**
	 * Enter a parse tree produced by {@link AntlrGrammarParserParser#nonTerminalList}.
	 * @param ctx the parse tree
	 */
	void enterNonTerminalList(AntlrGrammarParserParser.NonTerminalListContext ctx);
	/**
	 * Exit a parse tree produced by {@link AntlrGrammarParserParser#nonTerminalList}.
	 * @param ctx the parse tree
	 */
	void exitNonTerminalList(AntlrGrammarParserParser.NonTerminalListContext ctx);
	/**
	 * Enter a parse tree produced by {@link AntlrGrammarParserParser#nonTerminal}.
	 * @param ctx the parse tree
	 */
	void enterNonTerminal(AntlrGrammarParserParser.NonTerminalContext ctx);
	/**
	 * Exit a parse tree produced by {@link AntlrGrammarParserParser#nonTerminal}.
	 * @param ctx the parse tree
	 */
	void exitNonTerminal(AntlrGrammarParserParser.NonTerminalContext ctx);
	/**
	 * Enter a parse tree produced by {@link AntlrGrammarParserParser#startNonTerminal}.
	 * @param ctx the parse tree
	 */
	void enterStartNonTerminal(AntlrGrammarParserParser.StartNonTerminalContext ctx);
	/**
	 * Exit a parse tree produced by {@link AntlrGrammarParserParser#startNonTerminal}.
	 * @param ctx the parse tree
	 */
	void exitStartNonTerminal(AntlrGrammarParserParser.StartNonTerminalContext ctx);
	/**
	 * Enter a parse tree produced by {@link AntlrGrammarParserParser#ruleList}.
	 * @param ctx the parse tree
	 */
	void enterRuleList(AntlrGrammarParserParser.RuleListContext ctx);
	/**
	 * Exit a parse tree produced by {@link AntlrGrammarParserParser#ruleList}.
	 * @param ctx the parse tree
	 */
	void exitRuleList(AntlrGrammarParserParser.RuleListContext ctx);
	/**
	 * Enter a parse tree produced by {@link AntlrGrammarParserParser#ruleAttrs}.
	 * @param ctx the parse tree
	 */
	void enterRuleAttrs(AntlrGrammarParserParser.RuleAttrsContext ctx);
	/**
	 * Exit a parse tree produced by {@link AntlrGrammarParserParser#ruleAttrs}.
	 * @param ctx the parse tree
	 */
	void exitRuleAttrs(AntlrGrammarParserParser.RuleAttrsContext ctx);
	/**
	 * Enter a parse tree produced by {@link AntlrGrammarParserParser#attribute}.
	 * @param ctx the parse tree
	 */
	void enterAttribute(AntlrGrammarParserParser.AttributeContext ctx);
	/**
	 * Exit a parse tree produced by {@link AntlrGrammarParserParser#attribute}.
	 * @param ctx the parse tree
	 */
	void exitAttribute(AntlrGrammarParserParser.AttributeContext ctx);
	/**
	 * Enter a parse tree produced by {@link AntlrGrammarParserParser#translatorReturn}.
	 * @param ctx the parse tree
	 */
	void enterTranslatorReturn(AntlrGrammarParserParser.TranslatorReturnContext ctx);
	/**
	 * Exit a parse tree produced by {@link AntlrGrammarParserParser#translatorReturn}.
	 * @param ctx the parse tree
	 */
	void exitTranslatorReturn(AntlrGrammarParserParser.TranslatorReturnContext ctx);
	/**
	 * Enter a parse tree produced by {@link AntlrGrammarParserParser#translatorArgs}.
	 * @param ctx the parse tree
	 */
	void enterTranslatorArgs(AntlrGrammarParserParser.TranslatorArgsContext ctx);
	/**
	 * Exit a parse tree produced by {@link AntlrGrammarParserParser#translatorArgs}.
	 * @param ctx the parse tree
	 */
	void exitTranslatorArgs(AntlrGrammarParserParser.TranslatorArgsContext ctx);
	/**
	 * Enter a parse tree produced by {@link AntlrGrammarParserParser#lineSeparator}.
	 * @param ctx the parse tree
	 */
	void enterLineSeparator(AntlrGrammarParserParser.LineSeparatorContext ctx);
	/**
	 * Exit a parse tree produced by {@link AntlrGrammarParserParser#lineSeparator}.
	 * @param ctx the parse tree
	 */
	void exitLineSeparator(AntlrGrammarParserParser.LineSeparatorContext ctx);
	/**
	 * Enter a parse tree produced by {@link AntlrGrammarParserParser#atLeastWhitespaces}.
	 * @param ctx the parse tree
	 */
	void enterAtLeastWhitespaces(AntlrGrammarParserParser.AtLeastWhitespacesContext ctx);
	/**
	 * Exit a parse tree produced by {@link AntlrGrammarParserParser#atLeastWhitespaces}.
	 * @param ctx the parse tree
	 */
	void exitAtLeastWhitespaces(AntlrGrammarParserParser.AtLeastWhitespacesContext ctx);
	/**
	 * Enter a parse tree produced by {@link AntlrGrammarParserParser#maybeWhitespaces}.
	 * @param ctx the parse tree
	 */
	void enterMaybeWhitespaces(AntlrGrammarParserParser.MaybeWhitespacesContext ctx);
	/**
	 * Exit a parse tree produced by {@link AntlrGrammarParserParser#maybeWhitespaces}.
	 * @param ctx the parse tree
	 */
	void exitMaybeWhitespaces(AntlrGrammarParserParser.MaybeWhitespacesContext ctx);
}