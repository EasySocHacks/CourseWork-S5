package syntax;

import grammar.Grammar;
import grammar.objects.GrammarObject;
import grammar.objects.nonterminals.NonTerminal;
import grammar.objects.terminals.Terminal;
import grammar.rules.Rule;

import java.util.*;

@SuppressWarnings("rawtypes")
public class SyntaxAnalyzer {
    private final Grammar grammar;
    private final List<Rule> ruleList;
    private final Map<NonTerminal, List<Terminal>> firstMap;
    private final Map<NonTerminal, List<Terminal>> followMap;

    public SyntaxAnalyzer(Grammar grammar) {
        this.grammar = grammar;
        this.ruleList = grammar.getRuleList();

        firstMap = new HashMap<>();
        followMap = new HashMap<>();

        calculateFirstMap();
        calculateFollowMap();
    }

    private void calculateFirstMap() {
        for (NonTerminal nonTerminal : grammar.getNonTerminals()) {
            firstMap.put(nonTerminal, new ArrayList<>());
        }

        boolean hasChanges = true;

        while (hasChanges) {
            hasChanges = false;

            for (Rule rule : ruleList) {
                List<Terminal> alphaFirstList = getFirst(rule.getGrammarObjectsList());

                for (Terminal terminal : alphaFirstList) {
                    if (!firstMap.get(rule.getFromNonTerminal()).contains(terminal)) {
                        firstMap.get(rule.getFromNonTerminal()).add(terminal);

                        hasChanges = true;
                    }
                }
            }
        }
    }

    private void calculateFollowMap() {
        for (NonTerminal nonTerminal : grammar.getNonTerminals()) {
            followMap.put(nonTerminal, new ArrayList<>());
        }

        followMap.get(grammar.getStartNonTerminal()).add(Terminal.EOS);

        boolean hasChanges = true;

        while (hasChanges) {
            hasChanges = false;

            for (Rule rule : ruleList) {
                for (int i = 0; i < rule.getGrammarObjectsList().size(); i++) {
                    GrammarObject grammarObject = rule.getGrammarObjectsList().get(i);

                    if (!(grammarObject instanceof NonTerminal)) {
                        continue;
                    }

                    List<Terminal> firstList =
                            getFirst(rule.getGrammarObjectsList().subList(i + 1, rule.getGrammarObjectsList().size()));

                    if (firstList.contains(Terminal.EPSILON)) {
                        firstList.remove(Terminal.EPSILON);

                        List<Terminal> followList = getFollow(rule.getFromNonTerminal());

                        for (Terminal terminal : followList) {
                            if (!firstList.contains(terminal)) {
                                firstList.add(terminal);
                            }
                        }
                    }

                    for (Terminal terminal : firstList) {
                        if (!followMap.get(grammarObject).contains(terminal)) {
                            followMap.get(grammarObject).add(terminal);

                            hasChanges = true;
                        }
                    }
                }
            }
        }
    }

    public List<Terminal> getFollow(NonTerminal nonTerminal) {
        return new ArrayList<>(followMap.get(nonTerminal));
    }

    public List<Terminal> getFirst(NonTerminal nonTerminal) {
        return new ArrayList<>(firstMap.get(nonTerminal));
    }

    public List<Terminal> getFirst(List<GrammarObject> grammarObjectList) {
        if (grammarObjectList.size() == 0) {
            return new ArrayList<>(Collections.singletonList(Terminal.EPSILON));
        }

        GrammarObject firstGrammarObject = grammarObjectList.get(0);

        if (firstGrammarObject instanceof Terminal) {
            return new ArrayList<>(Collections.singletonList((Terminal) firstGrammarObject));
        }

        if (grammarObjectList.size() == 1) {
            return getFirst((NonTerminal) firstGrammarObject);
        }

        List<Terminal> firstList = getFirst((NonTerminal) firstGrammarObject);

        if (firstList.contains(Terminal.EPSILON)) {
            firstList.remove(Terminal.EPSILON);

            List<Terminal> anotherFirstList = getFirst(grammarObjectList.subList(1, grammarObjectList.size()));

            for (Terminal terminal : anotherFirstList) {
                if (!firstList.contains(terminal)) {
                    firstList.add(terminal);
                }
            }
        }

        return firstList;
    }
}
