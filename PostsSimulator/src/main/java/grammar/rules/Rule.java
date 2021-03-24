package grammar.rules;

import grammar.objects.GrammarObject;
import grammar.objects.nonterminals.NonTerminal;

import java.util.List;

public class Rule {
    private final NonTerminal fromNonTerminal;
    private final List<GrammarObject> grammarObjectsList;

    public Rule(NonTerminal fromNonTerminal, List<GrammarObject> grammarObjectsList) {
        this.fromNonTerminal = fromNonTerminal;
        this.grammarObjectsList = grammarObjectsList;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append(fromNonTerminal.toString()).append(" ");
        stringBuilder.append("->").append(" ");

        for (GrammarObject grammarObject : grammarObjectsList) {
            stringBuilder.append(grammarObject).append(" ");
        }

        return stringBuilder.toString();
    }

    public NonTerminal getFromNonTerminal() {
        return fromNonTerminal;
    }

    public List<GrammarObject> getGrammarObjectsList() {
        return grammarObjectsList;
    }
}
