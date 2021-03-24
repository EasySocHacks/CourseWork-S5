package syntax;

import exceptions.lexic.tokenizer.TokenizerParseException;
import exceptions.syntax.ExpressionParserException;
import grammar.Grammar;
import grammar.objects.GrammarObject;
import grammar.objects.attributes.Attribute;
import grammar.objects.nonterminals.NonTerminal;
import grammar.objects.nonterminals.translators.Translator;
import grammar.objects.terminals.Terminal;
import grammar.rules.Rule;
import lexic.LexicalAnalyzer;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@SuppressWarnings("rawtypes")
public class ExpressionParser {
    private final Grammar grammar;
    private final SyntaxAnalyzer syntaxAnalyzer;
    private LexicalAnalyzer lexicalAnalyzer;

    public static class Node {
        private final GrammarObject grammarObject;
        private final List<Node> children;

        public Node(GrammarObject grammarObject) {
            this.grammarObject = grammarObject;

            children = new ArrayList<>();
        }

        public GrammarObject getGrammarObject() {
            return grammarObject;
        }

        public List<Node> getChildren() {
            return children;
        }

        @SuppressWarnings("rawtypes")
        @Override
        public String toString() {
            if (grammarObject instanceof Terminal) {
                if (grammarObject.equals(Terminal.EPSILON)) {
                    return "";
                }

                if (((Terminal) grammarObject).getExtraValue() != null) {
                    return ((Terminal) grammarObject).getExtraValue().toString();
                }

                return grammarObject.toString();
            }

            StringBuilder stringBuilder = new StringBuilder();

            boolean isFirstChild = true;

            for (Node child : children) {
                String childString = child.toString();

                if (!childString.equals("")) {
                    if (!isFirstChild) {
                        stringBuilder.append(" ");
                    }

                    isFirstChild = false;

                    stringBuilder.append(childString);
                }
            }

            return stringBuilder.toString();
        }
    }

    public ExpressionParser(Grammar grammar) {
        this.grammar = grammar;
        this.syntaxAnalyzer = new SyntaxAnalyzer(grammar);
    }

    public Node parse(String expression) throws TokenizerParseException, ExpressionParserException {
        lexicalAnalyzer = new LexicalAnalyzer(expression, grammar.getTerminals());

        lexicalAnalyzer.nextToken();

        if (lexicalAnalyzer.getToken() == Terminal.EOS) {
            throw new ExpressionParserException("Cannot parse an empty string");
        }

        return parseNonTerminal(grammar.getStartNonTerminal());
    }

    @SuppressWarnings("rawtypes")
    private List<Terminal> getRulePossibleTerminals(Rule rule) {
        List<Terminal> firstList = syntaxAnalyzer.getFirst(rule.getGrammarObjectsList());

        if (firstList.contains(Terminal.EPSILON)) {
            firstList.remove(Terminal.EPSILON);

            List<Terminal> fallowList = syntaxAnalyzer.getFollow(rule.getFromNonTerminal());

            for (Terminal terminal : fallowList) {
                if (!firstList.contains(terminal)) {
                    firstList.add(terminal);
                }
            }
        }

        return firstList;
    }

    private GrammarObject remind = null;

    private Node parseNonTerminal(NonTerminal copingNonTerminal) throws TokenizerParseException, ExpressionParserException {
        boolean doesSmth = false;

        NonTerminal nonTerminal = new NonTerminal(copingNonTerminal.getName());

        if (remind != null) {
            nonTerminal.setAttributes(remind.getAttributes());

            remind = null;
        } else {
            nonTerminal.setAttributes(copingNonTerminal.getAttributes());
        }

        Node node = new Node(nonTerminal);

        for (Rule rule : grammar.getRuleList()) {
            if (!rule.getFromNonTerminal().equals(nonTerminal)) {
                continue;
            }

            if (!getRulePossibleTerminals(rule).contains(lexicalAnalyzer.getToken())) {
                continue;
            }
            
            for (int i = 0; i < rule.getGrammarObjectsList().size(); i++) {
                GrammarObject grammarObject = rule.getGrammarObjectsList().get(i);

                if (grammarObject instanceof Terminal) {
                    if (grammarObject.equals(Terminal.EPSILON)) {
                        node.getChildren().add(new Node(Terminal.EPSILON));

                        break;
                    }

                    if (!lexicalAnalyzer.getToken().equals(grammarObject)) {
                        throw new ExpressionParserException(
                                String.format("Cannot resolve token '%s' in rule '%s'", lexicalAnalyzer.getToken(), rule));
                    }

                    Node newNode = new Node(lexicalAnalyzer.getToken());

                    newNode.grammarObject.setAttributes(lexicalAnalyzer.getToken().getAttributes());

                    if (remind != null) {
                        for (String attributeName : remind.getAttributes().keySet()) {
                            newNode.getGrammarObject().getAttributes().get(attributeName).setValue(
                                    remind.getAttributes().get(attributeName).getValue());
                        }

                        remind = null;
                    }

                    if (lexicalAnalyzer.getToken().getExtraValue() != null) {
                        if (!newNode.getGrammarObject().getAttributes().containsKey("extraValue")) {
                            newNode.getGrammarObject().getAttributes().put("extraValue",
                                    new Attribute("extraValue"));
                        }

                        newNode.getGrammarObject().getAttributes().get("extraValue").setValue(lexicalAnalyzer.getToken().getExtraValue());
                    }

                    node.getChildren().add(newNode);
                    lexicalAnalyzer.nextToken();

                    continue;
                }

                if (grammarObject instanceof Translator) {
                    doesSmth = true;

                    boolean isArgs = false;

                    if (((Translator) grammarObject).getTranslatorType().equals(Translator.TranslatorType.ARGS) ||
                            ((Translator) grammarObject).getTranslatorType().equals(Translator.TranslatorType.COPY_TRANSLATOR) ||
                            ((Translator) grammarObject).getTranslatorType().equals(Translator.TranslatorType.RIGHT_BRANCHING_ARGS_TRANSLATOR)) {
                        isArgs = true;

                        if (remind != null) {
                            node.children.add(new Node(remind));

                        } else {
                            if (rule.getGrammarObjectsList().get(i + 1) instanceof Translator) {
                                if (rule.getGrammarObjectsList().get(i + 2) instanceof NonTerminal) {
                                    node.children.add(new Node(new NonTerminal(rule.getGrammarObjectsList().get(i + 2).getName())));
                                    node.children.get(node.children.size() - 1).getGrammarObject().setAttributes(
                                            rule.getGrammarObjectsList().get(i + 2).getAttributes());
                                } else {
                                    node.children.add(new Node(new Terminal(rule.getGrammarObjectsList().get(i + 2).getName())));
                                    node.children.get(node.children.size() - 1).getGrammarObject().setAttributes(
                                            rule.getGrammarObjectsList().get(i + 2).getAttributes());
                                }
                            } else {
                                if (rule.getGrammarObjectsList().get(i + 1) instanceof NonTerminal) {
                                    node.children.add(new Node(new NonTerminal(rule.getGrammarObjectsList().get(i + 1).getName())));
                                    node.children.get(node.children.size() - 1).getGrammarObject().setAttributes(
                                            rule.getGrammarObjectsList().get(i + 1).getAttributes());
                                } else {
                                    node.children.add(new Node(new Terminal(rule.getGrammarObjectsList().get(i + 1).getName())));
                                    node.children.get(node.children.size() - 1).getGrammarObject().setAttributes(
                                            rule.getGrammarObjectsList().get(i + 1).getAttributes());
                                }
                            }
                        }
                    }

                    List<Map<String, Attribute>> args = new ArrayList<>();

                    for (Translator.Argument argument : ((Translator) grammarObject).getArgs()) {

                        if (argument.getRulePosition() == 0) {
                            args.add(node.grammarObject.getAttributes());
                        } else {
                            args.add(node.children.get(argument.getRulePosition() - 1).grammarObject.getAttributes());
                        }
                    }

                    if (((Translator) grammarObject).getTranslatorType().equals(Translator.TranslatorType.COPY_TRANSLATOR)) {
                        args.add(node.grammarObject.getAttributes());
                        args.add(node.children.get(node.children.size() - 1).grammarObject.getAttributes());
                    }

                    if (((Translator) grammarObject).getTranslatorType().equals(Translator.TranslatorType.RETURN_TRANSLATOR)) {
                        args.add(node.children.get(node.children.size() - 1).grammarObject.getAttributes());
                        args.add(node.grammarObject.getAttributes());
                    }

                    if (((Translator) grammarObject).getTranslatorType().equals(Translator.TranslatorType.RIGHT_BRANCHING_ARGS_TRANSLATOR)) {
                        args.add(node.children.get(node.children.size() - 2).grammarObject.getAttributes());
                        args.add(node.children.get(node.children.size() - 1).grammarObject.getAttributes());
                    }

                    try {
                        Method runMethod = ((Translator) grammarObject).getCodeClass().getDeclaredMethods()[0];

                        runMethod.invoke(null, args);
                    } catch (IllegalAccessException | InvocationTargetException e) {
                        throw new ExpressionParserException(String.format("Couldn't run translator '%s", grammarObject.getName()), e);
                    }

                    if (isArgs) {
                        remind = node.children.get(node.children.size() - 1).grammarObject;
                        node.children.remove(node.children.size() - 1);
                    }

                    continue;
                }

                node.getChildren().add(parseNonTerminal((NonTerminal) grammarObject));
            }

            break;
        }

        if (doesSmth) {
            node.children.add(new Node(Terminal.EPSILON));
        }

        if (node.getChildren().isEmpty()) {
            throw  new ExpressionParserException(
                    String.format("Cannot find rule for token '%s' and non-terminal '%s'", lexicalAnalyzer.getToken(), nonTerminal));
        }

        return node;
    }
}
