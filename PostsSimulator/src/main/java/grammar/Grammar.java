package grammar;

import exceptions.grammar.CreateTranslatorWithCurrentCodeException;
import exceptions.grammar.GrammarRuleParseException;
import grammar.objects.GrammarObject;
import grammar.objects.nonterminals.NonTerminal;
import grammar.objects.nonterminals.translators.CopyTranslator;
import grammar.objects.nonterminals.translators.ReturnTranslator;
import grammar.objects.nonterminals.translators.RightBranchingArgsTranslator;
import grammar.objects.nonterminals.translators.Translator;
import grammar.objects.terminals.Terminal;
import grammar.rules.Rule;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@SuppressWarnings("rawtypes")
public class Grammar {
    private final String RULE_USAGE_STRING = "Usage: [Non-terminal] -> " +
            "[Terminal|Non-terminal|$Translator_Name({ Translator code; })?]([ ]+[Terminal|Non-terminal|{ Translator code }])*";
    private final String[] terminalDeniedSubstrings = {"'", "$"};
    private final String[] nonTerminalDeniedSubstrings = {"'", "$"};

    private String grammarName;

    private List<Terminal> terminals;

    private List<NonTerminal> nonTerminals;

    private NonTerminal startNonTerminal = new NonTerminal("S");

    private List<Rule> ruleList = new ArrayList<>();

    private List<Translator> translators;

    public Grammar() {}

    public Grammar(String grammarName) {
        this.grammarName = grammarName;
    }

    public Grammar(String grammarName, List<Terminal> terminals, List<NonTerminal> nonTerminals, NonTerminal startNonTerminal, List<Rule> ruleList) {
        this.grammarName = grammarName;
        this.terminals = terminals;
        this.nonTerminals = nonTerminals;
        this.startNonTerminal = startNonTerminal;
        this.ruleList = ruleList;
    }

    public void buildGrammar() throws GrammarRuleParseException {
        checkTerminals();
        checkNonTerminals();
        checkStartNonTerminal();
        addAttributes();
        findTranslators();
        deleteRecursion();
    }

    private void deleteRecursion() {
        if (deleteDirectlyLeftRecursion()) {
            deleteRecursion();
        } else if (deleteRightBranching()) {
            deleteRecursion();
        }
    }

    private boolean deleteRightBranching() {
        for (int i = 0; i < ruleList.size(); i++) {
            Rule ruleI = ruleList.get(i);

            if (ruleI.getGrammarObjectsList().get(0).equals(Terminal.EPSILON)) {
                continue;
            }

            NonTerminal possibleNewNonTerminal = new NonTerminal(ruleI.getFromNonTerminal().getName() + "'r");

            possibleNewNonTerminal.setAttributes(ruleI.getFromNonTerminal().getAttributes());

            boolean foundRightBranching = false;
            for (int j = i + 1; j < ruleList.size(); j++) {
                Rule ruleJ = ruleList.get(j);

                if (ruleI.getFromNonTerminal().equals(ruleJ.getFromNonTerminal()) &&
                ruleI.getGrammarObjectsList().get(0).equals(ruleJ.getGrammarObjectsList().get(0))) {
                    int pos = 0;
                    while (true) {
                        if (pos >= ruleI.getGrammarObjectsList().size() || pos >= ruleJ.getGrammarObjectsList().size())
                            break;

                        if (!ruleI.getGrammarObjectsList().get(pos).equals(ruleJ.getGrammarObjectsList().get(pos)))
                            break;

                        int cnt = 0;

                        if (!(ruleI.getGrammarObjectsList().get(pos) instanceof Translator))
                            cnt++;

                        if (!(ruleJ.getGrammarObjectsList().get(pos) instanceof Translator))
                            cnt++;

                        if (cnt == 2) {
                            foundRightBranching = true;
                            break;
                        }

                        pos++;
                    }

                    if (!foundRightBranching) {
                        continue;
                    }

                    List<GrammarObject> newGrammarObjectList = new ArrayList<>();

                    for (int k = pos + 1; k < ruleJ.getGrammarObjectsList().size(); k++) {
                        GrammarObject grammarObject = ruleJ.getGrammarObjectsList().get(k);

                        if (!(grammarObject instanceof Translator)) {
                            newGrammarObjectList.add(grammarObject);
                            continue;
                        }

                        List<Translator.Argument> newArgumentList = new ArrayList<>();

                        for (Translator.Argument argument : ((Translator) grammarObject).getArgs()) {
                            if (argument.getRulePosition() >= 1) {
                                newArgumentList.add(
                                        new Translator.Argument(argument.getRulePosition() - 1));
                            } else {
                                newArgumentList.add(argument);
                            }
                        }

                        Translator newTranslator = new Translator(
                                grammarObject.getName(),
                                ((Translator) grammarObject).getCodeClass(),
                                newArgumentList,
                                ((Translator) grammarObject).getTranslatorType());

                        ruleList.add(new Rule(newTranslator, Collections.singletonList(Terminal.EPSILON)));
                        nonTerminals.add(newTranslator);

                        newGrammarObjectList.add(newTranslator);
                    }

                    if (newGrammarObjectList.isEmpty()) {
                        newGrammarObjectList.add(Terminal.EPSILON);
                    }

                    ruleList.set(j, new Rule(
                            possibleNewNonTerminal,
                            newGrammarObjectList));
                }
            }

            if (foundRightBranching) {
                nonTerminals.add(possibleNewNonTerminal);

                List<GrammarObject> newGrammarObjectList = new ArrayList<>();

                int pos = 0;
                while (true) {
                    if (pos >= ruleI.getGrammarObjectsList().size())
                        break;

                    if (ruleI.getGrammarObjectsList().get(pos) instanceof Translator) {
                        pos++;
                        continue;
                    }

                    break;
                }

                for (int k = pos + 1; k < ruleI.getGrammarObjectsList().size(); k++) {
                    GrammarObject grammarObject = ruleI.getGrammarObjectsList().get(k);

                    if (!(grammarObject instanceof Translator)) {
                        newGrammarObjectList.add(grammarObject);
                        continue;
                    }

                    List<Translator.Argument> newArgumentList = new ArrayList<>();

                    for (Translator.Argument argument : ((Translator) grammarObject).getArgs()) {
                        if (argument.getRulePosition() >= 1) {
                            newArgumentList.add(
                                new Translator.Argument(argument.getRulePosition() - 1));
                        } else {
                            newArgumentList.add(argument);
                        }
                    }

                    Translator newTranslator = new Translator(
                            grammarObject.getName(),
                            ((Translator) grammarObject).getCodeClass(),
                            newArgumentList,
                            ((Translator) grammarObject).getTranslatorType());

                    ruleList.add(new Rule(newTranslator, Collections.singletonList(Terminal.EPSILON)));
                    nonTerminals.add(newTranslator);

                    newGrammarObjectList.add(newTranslator);
                }

                if (newGrammarObjectList.isEmpty()) {
                    newGrammarObjectList.add(Terminal.EPSILON);
                }

                ruleList.set(i, new Rule(
                        possibleNewNonTerminal,
                        newGrammarObjectList));

                try {
                    Translator copyTranslator = new CopyTranslator(grammarName);
                    Translator returnTranslator = new ReturnTranslator(grammarName);
                    Translator rightBranchingArgsTranslator = new RightBranchingArgsTranslator(grammarName);

                    nonTerminals.add(copyTranslator);
                    nonTerminals.add(returnTranslator);
                    nonTerminals.add(rightBranchingArgsTranslator);

                    ruleList.add(new Rule(copyTranslator, Collections.singletonList(Terminal.EPSILON)));
                    ruleList.add(new Rule(returnTranslator, Collections.singletonList(Terminal.EPSILON)));
                    ruleList.add(new Rule(rightBranchingArgsTranslator, Collections.singletonList(Terminal.EPSILON)));

                    List<GrammarObject> endGrammarObjectList = new ArrayList<>();
                    for (int k = 0; k <= pos; k++) {
                        endGrammarObjectList.add(ruleI.getGrammarObjectsList().get(k));
                    }

                    endGrammarObjectList.add(rightBranchingArgsTranslator);
                    endGrammarObjectList.add(copyTranslator);
                    endGrammarObjectList.add(possibleNewNonTerminal);
                    endGrammarObjectList.add(returnTranslator);

                    ruleList.add(new Rule(
                            ruleI.getFromNonTerminal(),
                            endGrammarObjectList
                    ));
                } catch (CreateTranslatorWithCurrentCodeException e) {
                    e.printStackTrace();
                }

                return true;
            }
        }

        return false;
    }

    private boolean deleteDirectlyLeftRecursion() {
        for (int i = 0; i < ruleList.size(); i++) {
            if (ruleList.get(i).getFromNonTerminal().equals(ruleList.get(i).getGrammarObjectsList().get(0))) {
                NonTerminal recursiveNonTerminal = ruleList.get(i).getFromNonTerminal();
                NonTerminal newNonTerminal = new NonTerminal(recursiveNonTerminal.getName() + "'l");

                newNonTerminal.setAttributes(recursiveNonTerminal.getAttributes());

                boolean foundDirectlyLeftRecursion = false;

                for (Rule ruleJ : ruleList) {
                    if (ruleJ.getFromNonTerminal().equals(recursiveNonTerminal) &&
                            !ruleJ.getGrammarObjectsList().get(0).equals(recursiveNonTerminal)) {
                        foundDirectlyLeftRecursion = true;

                        break;
                    }
                }

                if (!foundDirectlyLeftRecursion) {
                    continue;
                }

                nonTerminals.add(newNonTerminal);

                for (int j = 0; j < ruleList.size(); j++) {
                    Rule ruleJ = ruleList.get(j);

                    if (ruleJ.getFromNonTerminal().equals(recursiveNonTerminal)) {
                        if (ruleJ.getGrammarObjectsList().get(0).equals(recursiveNonTerminal)) {
                            List<GrammarObject> newGrammarObjectList = new ArrayList<>();

                            for (int k = 1; k < ruleJ.getGrammarObjectsList().size(); k++) {
                                GrammarObject gObj = ruleJ.getGrammarObjectsList().get(k);

                                if (!(gObj instanceof Translator)) {
                                    newGrammarObjectList.add(gObj);
                                    continue;
                                }

                                if (k == ruleJ.getGrammarObjectsList().size() - 1) {
                                    continue;
                                }

                                List<Translator.Argument> newArgumentList = new ArrayList<>();

                                for (Translator.Argument argument : ((Translator) gObj).getArgs()) {
                                    if (argument.getRulePosition() >= 1) {
                                        newArgumentList.add(new Translator.Argument(
                                                argument.getRulePosition() - 1));
                                    } else {
                                        newArgumentList.add(new Translator.Argument(
                                                argument.getRulePosition()));
                                    }
                                }

                                Translator newTranslator = new Translator(
                                        gObj.getName(),
                                        ((Translator) gObj).getCodeClass(),
                                        newArgumentList,
                                        ((Translator) gObj).getTranslatorType());

                                ruleList.add(new Rule(newTranslator, Collections.singletonList(Terminal.EPSILON)));
                                nonTerminals.add(newTranslator);

                                newGrammarObjectList.add(newTranslator);
                            }

                            GrammarObject lastGrammarObject = ruleJ.getGrammarObjectsList().get(ruleJ.getGrammarObjectsList().size() - 1);

                            if (lastGrammarObject instanceof Translator) {
                                if (((Translator) lastGrammarObject).getTranslatorType().equals(Translator.TranslatorType.RETURN)) {
                                    int count = 0;
                                    for (GrammarObject g : ruleJ.getGrammarObjectsList()) {
                                        if (!(g instanceof Translator))
                                            count++;
                                    }

                                    List<Translator.Argument> newArgumentList = new ArrayList<>(((Translator) lastGrammarObject).getArgs());
                                    newArgumentList.set(0, new Translator.Argument(count));

                                    for (int q = 1; q < newArgumentList.size(); q++) {
                                        if (newArgumentList.get(q).getRulePosition() >= 1) {
                                            newArgumentList.set(q, new Translator.Argument(
                                                    newArgumentList.get(q).getRulePosition() - 1));
                                        }
                                    }

                                    Translator newTranslator = new Translator(
                                            lastGrammarObject.getName(),
                                            ((Translator) lastGrammarObject).getCodeClass(),
                                            newArgumentList,
                                            Translator.TranslatorType.ARGS);

                                    ruleList.add(new Rule(newTranslator, Collections.singletonList(Terminal.EPSILON)));
                                    nonTerminals.add(newTranslator);

                                    newGrammarObjectList.add(newTranslator);
                                }
                            }

                            ruleList.set(j, new Rule(newNonTerminal, newGrammarObjectList));
                        } else {
                            GrammarObject lastGrammarObject = ruleJ.getGrammarObjectsList().get(ruleJ.getGrammarObjectsList().size() - 1);

                            if (lastGrammarObject instanceof Translator) {
                                if (((Translator)lastGrammarObject).getTranslatorType().equals(Translator.TranslatorType.RETURN)) {
                                    int count = 0;
                                    for (GrammarObject g : ruleJ.getGrammarObjectsList()) {
                                        if (!(g instanceof Translator))
                                            count++;
                                    }

                                    List<Translator.Argument> newArgumentList = new ArrayList<>(((Translator) lastGrammarObject).getArgs());
                                    newArgumentList.set(0, new Translator.Argument(count + 1));

                                    Translator newTranslator = new Translator(
                                            lastGrammarObject.getName(),
                                            ((Translator) lastGrammarObject).getCodeClass(),
                                            newArgumentList,
                                            Translator.TranslatorType.ARGS);

                                    ruleList.add(new Rule(newTranslator, Collections.singletonList(Terminal.EPSILON)));
                                    nonTerminals.add(newTranslator);

                                    ruleJ.getGrammarObjectsList().set(
                                            ruleJ.getGrammarObjectsList().size() - 1,
                                            newTranslator);
                                }
                            }
                        }

                        try {
                            Translator copyTranslator = new CopyTranslator(grammarName);
                            Translator returnTranslator = new ReturnTranslator(grammarName);

                            nonTerminals.add(copyTranslator);
                            nonTerminals.add(returnTranslator);

                            ruleList.add(new Rule(copyTranslator, Collections.singletonList(Terminal.EPSILON)));
                            ruleList.add(new Rule(returnTranslator, Collections.singletonList(Terminal.EPSILON)));

                            ruleList.get(j).getGrammarObjectsList().add(copyTranslator);
                            ruleList.get(j).getGrammarObjectsList().add(newNonTerminal);
                            ruleList.get(j).getGrammarObjectsList().add(returnTranslator);
                        } catch (CreateTranslatorWithCurrentCodeException e) {
                            e.printStackTrace();
                        }
                    }
                }

                ruleList.add(new Rule(
                        newNonTerminal,
                        Collections.singletonList(Terminal.EPSILON)));

                return true;
            }
        }

        return false;
    }

    private void checkTerminals() throws GrammarRuleParseException {
        for (Terminal terminal : terminals) {
            if (terminal.equals(Terminal.EPSILON)) {
                throw new GrammarRuleParseException(String.format("Terminal '%s' is already reserved",  Terminal.EPSILON.getName()));
            }

            if (terminal.equals(Terminal.EOS)) {
                throw new GrammarRuleParseException(String.format("Terminal '%s' is already reserved", Terminal.EOS.getName()));
            }

            for (String terminalDeniedSubstring : terminalDeniedSubstrings) {
                if (terminal.getName().contains(terminalDeniedSubstring)) {
                    throw new GrammarRuleParseException(String.format("Substring '%s' is denied in terminals", terminalDeniedSubstring));
                }
            }
        }
    }

    private void checkNonTerminals() throws GrammarRuleParseException {
        for (NonTerminal nonTerminal : nonTerminals) {
            for (String nonTerminalDeniedSubstring : nonTerminalDeniedSubstrings) {
                if (nonTerminal.getName().contains(nonTerminalDeniedSubstring)) {
                    throw new GrammarRuleParseException(String.format("Substring '%s' is denied in non-terminals", nonTerminalDeniedSubstring));
                }
            }
        }
    }

    private void checkStartNonTerminal() throws GrammarRuleParseException {
        if (!nonTerminals.contains(startNonTerminal)) {
            throw new GrammarRuleParseException(String.format("Unable to resolve start-non-terminal %s'", startNonTerminal.getName()));
        }
    }

    private void findTranslators() {
        translators = new ArrayList<>();

        for (Rule rule : ruleList) {
            for (GrammarObject grammarObject : rule.getGrammarObjectsList()) {
                if (grammarObject instanceof Translator) {
                    if (translators.contains(grammarObject)) {
                        continue;
                    }

                    translators.add((Translator) grammarObject);
                }
            }
        }

        for (Translator translator : translators) {
            ruleList.add(new Rule(translator, Collections.singletonList((GrammarObject) Terminal.EPSILON)));
        }

        nonTerminals.addAll(translators);
    }

    private void addAttributes() {
        for (NonTerminal nonTerminal : nonTerminals) {
            if (startNonTerminal.equals(nonTerminal)) {
                startNonTerminal.setAttributes(nonTerminal.getAttributes());
                break;
            }
        }

        for (Rule rule : ruleList) {
            for (NonTerminal nonTerminal : nonTerminals) {
                if (rule.getFromNonTerminal().equals(nonTerminal)) {
                    rule.getFromNonTerminal().setAttributes(nonTerminal.getAttributes());
                    break;
                }
            }

            for (GrammarObject grammarObject : rule.getGrammarObjectsList()) {
                if (grammarObject instanceof Terminal) {
                    for (Terminal terminal : terminals) {
                        if (grammarObject.equals(terminal)) {
                            grammarObject.setAttributes(terminal.getAttributes());
                            break;
                        }
                    }
                } else {
                    for (NonTerminal nonTerminal : nonTerminals) {
                        if (grammarObject.equals(nonTerminal)) {
                            grammarObject.setAttributes(nonTerminal.getAttributes());
                            break;
                        }
                    }
                }
            }
        }
    }

    public List<Terminal> getTerminals() {
        return terminals;
    }

    public List<NonTerminal> getNonTerminals() {
        return nonTerminals;
    }

    public NonTerminal getStartNonTerminal() {
        return startNonTerminal;
    }

    public List<Rule> getRuleList() {
        return ruleList;
    }
}
