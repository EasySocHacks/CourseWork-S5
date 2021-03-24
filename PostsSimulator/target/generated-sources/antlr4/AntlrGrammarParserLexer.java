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

import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class AntlrGrammarParserLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.7.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, LowName=15, Whitespace=16, 
		HighName=17, ClassName=18, Code=19, Args=20;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"T__0", "T__1", "T__2", "T__3", "T__4", "T__5", "T__6", "T__7", "T__8", 
		"T__9", "T__10", "T__11", "T__12", "T__13", "LowName", "Whitespace", "HighName", 
		"ClassName", "Code", "Args"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'terminals:'", "':'", "'{'", "','", "'}'", "'non-terminals:'", 
		"'start-non-terminal:'", "'rules:'", "'->'", "'$'", "'['", "']'", "'\r'", 
		"'\n'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, null, "LowName", "Whitespace", "HighName", "ClassName", "Code", 
		"Args"
	};
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}


	public AntlrGrammarParserLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "AntlrGrammarParser.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\26\u0125\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"+
		"\t\22\4\23\t\23\4\24\t\24\4\25\t\25\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3"+
		"\2\3\2\3\2\3\3\3\3\3\4\3\4\3\5\3\5\3\6\3\6\3\7\3\7\3\7\3\7\3\7\3\7\3\7"+
		"\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3"+
		"\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\t\3\t\3\t\3\t\3\t\3\t\3\t"+
		"\3\n\3\n\3\n\3\13\3\13\3\f\3\f\3\r\3\r\3\16\3\16\3\17\3\17\3\20\3\20\3"+
		"\20\3\20\3\20\6\20{\n\20\r\20\16\20|\3\21\3\21\3\22\6\22\u0082\n\22\r"+
		"\22\16\22\u0083\3\23\3\23\7\23\u0088\n\23\f\23\16\23\u008b\13\23\3\24"+
		"\3\24\3\24\6\24\u0090\n\24\r\24\16\24\u0091\3\24\6\24\u0095\n\24\r\24"+
		"\16\24\u0096\3\24\6\24\u009a\n\24\r\24\16\24\u009b\3\24\6\24\u009f\n\24"+
		"\r\24\16\24\u00a0\3\24\6\24\u00a4\n\24\r\24\16\24\u00a5\3\24\6\24\u00a9"+
		"\n\24\r\24\16\24\u00aa\3\24\6\24\u00ae\n\24\r\24\16\24\u00af\3\24\6\24"+
		"\u00b3\n\24\r\24\16\24\u00b4\3\24\6\24\u00b8\n\24\r\24\16\24\u00b9\3\24"+
		"\6\24\u00bd\n\24\r\24\16\24\u00be\3\24\6\24\u00c2\n\24\r\24\16\24\u00c3"+
		"\3\24\6\24\u00c7\n\24\r\24\16\24\u00c8\3\24\6\24\u00cc\n\24\r\24\16\24"+
		"\u00cd\3\24\6\24\u00d1\n\24\r\24\16\24\u00d2\3\24\6\24\u00d6\n\24\r\24"+
		"\16\24\u00d7\3\24\6\24\u00db\n\24\r\24\16\24\u00dc\3\24\6\24\u00e0\n\24"+
		"\r\24\16\24\u00e1\3\24\6\24\u00e5\n\24\r\24\16\24\u00e6\5\24\u00e9\n\24"+
		"\3\24\7\24\u00ec\n\24\f\24\16\24\u00ef\13\24\6\24\u00f1\n\24\r\24\16\24"+
		"\u00f2\3\24\3\24\3\25\3\25\7\25\u00f9\n\25\f\25\16\25\u00fc\13\25\3\25"+
		"\3\25\6\25\u0100\n\25\r\25\16\25\u0101\3\25\3\25\7\25\u0106\n\25\f\25"+
		"\16\25\u0109\13\25\3\25\3\25\7\25\u010d\n\25\f\25\16\25\u0110\13\25\3"+
		"\25\3\25\6\25\u0114\n\25\r\25\16\25\u0115\3\25\7\25\u0119\n\25\f\25\16"+
		"\25\u011c\13\25\3\25\7\25\u011f\n\25\f\25\16\25\u0122\13\25\3\25\3\25"+
		"\2\2\26\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16\33\17"+
		"\35\20\37\21!\22#\23%\24\'\25)\26\3\2\5\6\2*-//\61;c|\5\2\13\f\17\17\""+
		"\"\6\2\62;C\\aac|\2\u0157\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2"+
		"\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2"+
		"\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3"+
		"\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\3+\3\2"+
		"\2\2\5\66\3\2\2\2\78\3\2\2\2\t:\3\2\2\2\13<\3\2\2\2\r>\3\2\2\2\17M\3\2"+
		"\2\2\21a\3\2\2\2\23h\3\2\2\2\25k\3\2\2\2\27m\3\2\2\2\31o\3\2\2\2\33q\3"+
		"\2\2\2\35s\3\2\2\2\37z\3\2\2\2!~\3\2\2\2#\u0081\3\2\2\2%\u0085\3\2\2\2"+
		"\'\u00f0\3\2\2\2)\u00f6\3\2\2\2+,\7v\2\2,-\7g\2\2-.\7t\2\2./\7o\2\2/\60"+
		"\7k\2\2\60\61\7p\2\2\61\62\7c\2\2\62\63\7n\2\2\63\64\7u\2\2\64\65\7<\2"+
		"\2\65\4\3\2\2\2\66\67\7<\2\2\67\6\3\2\2\289\7}\2\29\b\3\2\2\2:;\7.\2\2"+
		";\n\3\2\2\2<=\7\177\2\2=\f\3\2\2\2>?\7p\2\2?@\7q\2\2@A\7p\2\2AB\7/\2\2"+
		"BC\7v\2\2CD\7g\2\2DE\7t\2\2EF\7o\2\2FG\7k\2\2GH\7p\2\2HI\7c\2\2IJ\7n\2"+
		"\2JK\7u\2\2KL\7<\2\2L\16\3\2\2\2MN\7u\2\2NO\7v\2\2OP\7c\2\2PQ\7t\2\2Q"+
		"R\7v\2\2RS\7/\2\2ST\7p\2\2TU\7q\2\2UV\7p\2\2VW\7/\2\2WX\7v\2\2XY\7g\2"+
		"\2YZ\7t\2\2Z[\7o\2\2[\\\7k\2\2\\]\7p\2\2]^\7c\2\2^_\7n\2\2_`\7<\2\2`\20"+
		"\3\2\2\2ab\7t\2\2bc\7w\2\2cd\7n\2\2de\7g\2\2ef\7u\2\2fg\7<\2\2g\22\3\2"+
		"\2\2hi\7/\2\2ij\7@\2\2j\24\3\2\2\2kl\7&\2\2l\26\3\2\2\2mn\7]\2\2n\30\3"+
		"\2\2\2op\7_\2\2p\32\3\2\2\2qr\7\17\2\2r\34\3\2\2\2st\7\f\2\2t\36\3\2\2"+
		"\2u{\t\2\2\2vw\7>\2\2w{\7>\2\2xy\7@\2\2y{\7@\2\2zu\3\2\2\2zv\3\2\2\2z"+
		"x\3\2\2\2{|\3\2\2\2|z\3\2\2\2|}\3\2\2\2} \3\2\2\2~\177\t\3\2\2\177\"\3"+
		"\2\2\2\u0080\u0082\4C\\\2\u0081\u0080\3\2\2\2\u0082\u0083\3\2\2\2\u0083"+
		"\u0081\3\2\2\2\u0083\u0084\3\2\2\2\u0084$\3\2\2\2\u0085\u0089\4C\\\2\u0086"+
		"\u0088\t\4\2\2\u0087\u0086\3\2\2\2\u0088\u008b\3\2\2\2\u0089\u0087\3\2"+
		"\2\2\u0089\u008a\3\2\2\2\u008a&\3\2\2\2\u008b\u0089\3\2\2\2\u008c\u00e9"+
		"\5\37\20\2\u008d\u00e9\5#\22\2\u008e\u0090\7?\2\2\u008f\u008e\3\2\2\2"+
		"\u0090\u0091\3\2\2\2\u0091\u008f\3\2\2\2\u0091\u0092\3\2\2\2\u0092\u00e9"+
		"\3\2\2\2\u0093\u0095\7-\2\2\u0094\u0093\3\2\2\2\u0095\u0096\3\2\2\2\u0096"+
		"\u0094\3\2\2\2\u0096\u0097\3\2\2\2\u0097\u00e9\3\2\2\2\u0098\u009a\7,"+
		"\2\2\u0099\u0098\3\2\2\2\u009a\u009b\3\2\2\2\u009b\u0099\3\2\2\2\u009b"+
		"\u009c\3\2\2\2\u009c\u00e9\3\2\2\2\u009d\u009f\7\61\2\2\u009e\u009d\3"+
		"\2\2\2\u009f\u00a0\3\2\2\2\u00a0\u009e\3\2\2\2\u00a0\u00a1\3\2\2\2\u00a1"+
		"\u00e9\3\2\2\2\u00a2\u00a4\4\62;\2\u00a3\u00a2\3\2\2\2\u00a4\u00a5\3\2"+
		"\2\2\u00a5\u00a3\3\2\2\2\u00a5\u00a6\3\2\2\2\u00a6\u00e9\3\2\2\2\u00a7"+
		"\u00a9\7=\2\2\u00a8\u00a7\3\2\2\2\u00a9\u00aa\3\2\2\2\u00aa\u00a8\3\2"+
		"\2\2\u00aa\u00ab\3\2\2\2\u00ab\u00e9\3\2\2\2\u00ac\u00ae\7\60\2\2\u00ad"+
		"\u00ac\3\2\2\2\u00ae\u00af\3\2\2\2\u00af\u00ad\3\2\2\2\u00af\u00b0\3\2"+
		"\2\2\u00b0\u00e9\3\2\2\2\u00b1\u00b3\7*\2\2\u00b2\u00b1\3\2\2\2\u00b3"+
		"\u00b4\3\2\2\2\u00b4\u00b2\3\2\2\2\u00b4\u00b5\3\2\2\2\u00b5\u00e9\3\2"+
		"\2\2\u00b6\u00b8\7+\2\2\u00b7\u00b6\3\2\2\2\u00b8\u00b9\3\2\2\2\u00b9"+
		"\u00b7\3\2\2\2\u00b9\u00ba\3\2\2\2\u00ba\u00e9\3\2\2\2\u00bb\u00bd\7\'"+
		"\2\2\u00bc\u00bb\3\2\2\2\u00bd\u00be\3\2\2\2\u00be\u00bc\3\2\2\2\u00be"+
		"\u00bf\3\2\2\2\u00bf\u00e9\3\2\2\2\u00c0\u00c2\7]\2\2\u00c1\u00c0\3\2"+
		"\2\2\u00c2\u00c3\3\2\2\2\u00c3\u00c1\3\2\2\2\u00c3\u00c4\3\2\2\2\u00c4"+
		"\u00e9\3\2\2\2\u00c5\u00c7\7_\2\2\u00c6\u00c5\3\2\2\2\u00c7\u00c8\3\2"+
		"\2\2\u00c8\u00c6\3\2\2\2\u00c8\u00c9\3\2\2\2\u00c9\u00e9\3\2\2\2\u00ca"+
		"\u00cc\7$\2\2\u00cb\u00ca\3\2\2\2\u00cc\u00cd\3\2\2\2\u00cd\u00cb\3\2"+
		"\2\2\u00cd\u00ce\3\2\2\2\u00ce\u00e9\3\2\2\2\u00cf\u00d1\7)\2\2\u00d0"+
		"\u00cf\3\2\2\2\u00d1\u00d2\3\2\2\2\u00d2\u00d0\3\2\2\2\u00d2\u00d3\3\2"+
		"\2\2\u00d3\u00e9\3\2\2\2\u00d4\u00d6\7#\2\2\u00d5\u00d4\3\2\2\2\u00d6"+
		"\u00d7\3\2\2\2\u00d7\u00d5\3\2\2\2\u00d7\u00d8\3\2\2\2\u00d8\u00e9\3\2"+
		"\2\2\u00d9\u00db\7(\2\2\u00da\u00d9\3\2\2\2\u00db\u00dc\3\2\2\2\u00dc"+
		"\u00da\3\2\2\2\u00dc\u00dd\3\2\2\2\u00dd\u00e9\3\2\2\2\u00de\u00e0\7~"+
		"\2\2\u00df\u00de\3\2\2\2\u00e0\u00e1\3\2\2\2\u00e1\u00df\3\2\2\2\u00e1"+
		"\u00e2\3\2\2\2\u00e2\u00e9\3\2\2\2\u00e3\u00e5\7`\2\2\u00e4\u00e3\3\2"+
		"\2\2\u00e5\u00e6\3\2\2\2\u00e6\u00e4\3\2\2\2\u00e6\u00e7\3\2\2\2\u00e7"+
		"\u00e9\3\2\2\2\u00e8\u008c\3\2\2\2\u00e8\u008d\3\2\2\2\u00e8\u008f\3\2"+
		"\2\2\u00e8\u0094\3\2\2\2\u00e8\u0099\3\2\2\2\u00e8\u009e\3\2\2\2\u00e8"+
		"\u00a3\3\2\2\2\u00e8\u00a8\3\2\2\2\u00e8\u00ad\3\2\2\2\u00e8\u00b2\3\2"+
		"\2\2\u00e8\u00b7\3\2\2\2\u00e8\u00bc\3\2\2\2\u00e8\u00c1\3\2\2\2\u00e8"+
		"\u00c6\3\2\2\2\u00e8\u00cb\3\2\2\2\u00e8\u00d0\3\2\2\2\u00e8\u00d5\3\2"+
		"\2\2\u00e8\u00da\3\2\2\2\u00e8\u00df\3\2\2\2\u00e8\u00e4\3\2\2\2\u00e9"+
		"\u00ed\3\2\2\2\u00ea\u00ec\5!\21\2\u00eb\u00ea\3\2\2\2\u00ec\u00ef\3\2"+
		"\2\2\u00ed\u00eb\3\2\2\2\u00ed\u00ee\3\2\2\2\u00ee\u00f1\3\2\2\2\u00ef"+
		"\u00ed\3\2\2\2\u00f0\u00e8\3\2\2\2\u00f1\u00f2\3\2\2\2\u00f2\u00f0\3\2"+
		"\2\2\u00f2\u00f3\3\2\2\2\u00f3\u00f4\3\2\2\2\u00f4\u00f5\7=\2\2\u00f5"+
		"(\3\2\2\2\u00f6\u00fa\7*\2\2\u00f7\u00f9\5!\21\2\u00f8\u00f7\3\2\2\2\u00f9"+
		"\u00fc\3\2\2\2\u00fa\u00f8\3\2\2\2\u00fa\u00fb\3\2\2\2\u00fb\u00fd\3\2"+
		"\2\2\u00fc\u00fa\3\2\2\2\u00fd\u00ff\7]\2\2\u00fe\u0100\4\62;\2\u00ff"+
		"\u00fe\3\2\2\2\u0100\u0101\3\2\2\2\u0101\u00ff\3\2\2\2\u0101\u0102\3\2"+
		"\2\2\u0102\u0103\3\2\2\2\u0103\u011a\7_\2\2\u0104\u0106\5!\21\2\u0105"+
		"\u0104\3\2\2\2\u0106\u0109\3\2\2\2\u0107\u0105\3\2\2\2\u0107\u0108\3\2"+
		"\2\2\u0108\u010a\3\2\2\2\u0109\u0107\3\2\2\2\u010a\u010e\7.\2\2\u010b"+
		"\u010d\5!\21\2\u010c\u010b\3\2\2\2\u010d\u0110\3\2\2\2\u010e\u010c\3\2"+
		"\2\2\u010e\u010f\3\2\2\2\u010f\u0111\3\2\2\2\u0110\u010e\3\2\2\2\u0111"+
		"\u0113\7]\2\2\u0112\u0114\4\62;\2\u0113\u0112\3\2\2\2\u0114\u0115\3\2"+
		"\2\2\u0115\u0113\3\2\2\2\u0115\u0116\3\2\2\2\u0116\u0117\3\2\2\2\u0117"+
		"\u0119\7_\2\2\u0118\u0107\3\2\2\2\u0119\u011c\3\2\2\2\u011a\u0118\3\2"+
		"\2\2\u011a\u011b\3\2\2\2\u011b\u0120\3\2\2\2\u011c\u011a\3\2\2\2\u011d"+
		"\u011f\5!\21\2\u011e\u011d\3\2\2\2\u011f\u0122\3\2\2\2\u0120\u011e\3\2"+
		"\2\2\u0120\u0121\3\2\2\2\u0121\u0123\3\2\2\2\u0122\u0120\3\2\2\2\u0123"+
		"\u0124\7+\2\2\u0124*\3\2\2\2$\2z|\u0083\u0087\u0089\u0091\u0096\u009b"+
		"\u00a0\u00a5\u00aa\u00af\u00b4\u00b9\u00be\u00c3\u00c8\u00cd\u00d2\u00d7"+
		"\u00dc\u00e1\u00e6\u00e8\u00ed\u00f2\u00fa\u0101\u0107\u010e\u0115\u011a"+
		"\u0120\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}