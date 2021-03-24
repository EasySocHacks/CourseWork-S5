package lexic;

import exceptions.lexic.tokenizer.TokenizerParseException;
import grammar.objects.terminals.Terminal;

import java.util.List;

@SuppressWarnings("rawtypes")
public class LexicalAnalyzer {
    private final List<Terminal> terminalList;
    private final String parsingString;
    private int parsingStringPosition = 0;

    private Terminal token = null;

    public LexicalAnalyzer(String parsingString, List<Terminal> terminalList) {
        this.parsingString = parsingString;
        this.terminalList = terminalList;
    }

    public Terminal getToken() {
        return token;
    }

    public void nextToken() throws TokenizerParseException {
        if (parsingStringPosition >= parsingString.length()) {
            token = Terminal.EOS;

            return;
        }

        char currentChar = parsingString.charAt(parsingStringPosition);

        while (Character.isWhitespace(currentChar)) {
            parsingStringPosition++;

            if (parsingStringPosition >= parsingString.length()) {
                token = Terminal.EOS;

                return;
            }

            currentChar = parsingString.charAt(parsingStringPosition);
        }

        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append(currentChar);

        if (isEndOfToken()) {
            parsingStringPosition++;
            token = new Terminal(stringBuilder.toString(), Terminal.TerminalOption.WHITESPACELESS);

            return;
        }

        while (true) {
            parsingStringPosition++;

            if (isEndOfToken()) {
                break;
            }

            currentChar = parsingString.charAt(parsingStringPosition);
            stringBuilder.append(currentChar);
        }

        int possibleTokenMatches = getPossibleTokenMatches(stringBuilder.toString());
        int regexTokenMatches = getRegexTokenMatches(stringBuilder.toString());

        Terminal matchingToken = null;
        Terminal regexMatchingToken = null;

        if (possibleTokenMatches == 1) {
            matchingToken = findMatchingToken(stringBuilder.toString());
        }

        if (regexTokenMatches == 1) {
            regexMatchingToken = findRegexMatchingToken(stringBuilder.toString());
        }

        if (matchingToken != null) {
            token = matchingToken;

            return;
        }

        if (regexMatchingToken != null) {
            token = new Terminal(regexMatchingToken.getName(), regexMatchingToken.getTerminalOption(), stringBuilder.toString());

            return;
        }

        throw new TokenizerParseException(String.format("Cannot resolve token '%s' at position %d",
                stringBuilder.toString(), parsingStringPosition - stringBuilder.length()));
    }

    private boolean isEndOfToken() {
        if (parsingStringPosition >= parsingString.length()) {
            return true;
        }

        char currentChar = parsingString.charAt(parsingStringPosition);

        if (Character.isWhitespace(currentChar)) {
            return true;
        }

        for (Terminal<?> terminal : terminalList) {
            if (!terminal.getName().equals("" + currentChar)) {
                continue;
            }

            if (terminal.getTerminalOption() != null && terminal.getTerminalOption() == Terminal.TerminalOption.WHITESPACELESS) {
                return true;
            }
        }

        return false;
    }

    private int getPossibleTokenMatches(String possibleTokenString) {
        int possibleTokenMatches = 0;

        for (Terminal<?> terminal : terminalList) {
            if (terminal.getName().startsWith(possibleTokenString)) {
                possibleTokenMatches++;
            }
        }

        return possibleTokenMatches;
    }

    private int getRegexTokenMatches(String possibleTokenString) {
        int regexTokenMatches = 0;

        for (Terminal<?> terminal : terminalList) {
            if (terminal.getTerminalOption() != null && possibleTokenString.matches(terminal.getTerminalOption().getRegex())) {
                regexTokenMatches++;
            }
        }

        return regexTokenMatches;
    }

    private Terminal<?> findMatchingToken(String tokenString) {
        for (Terminal<?> terminal : terminalList) {
            if (terminal.getName().equals(tokenString)) {
                return terminal;
            }
        }

        return null;
    }

    private Terminal<?> findRegexMatchingToken(String tokenString) {
        for (Terminal<?> terminal : terminalList) {
            if (terminal.getTerminalOption() != null && tokenString.matches(terminal.getTerminalOption().getRegex())) {
                return terminal;
            }
        }

        return null;
    }
}
