grammar AntlrGrammarParser;

@header {
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
}

parse [String grammarName] returns [Grammar grammar]
    :   terminalListValue=terminalList
        lineSeparator
        nonTerminalListValue=nonTerminalList
        lineSeparator
        startNonTerminalValue=startNonTerminal
        lineSeparator
        ruleListValue=ruleList[$grammarName]
        { $grammar = new Grammar($grammarName, $terminalListValue.list, $nonTerminalListValue.list, $startNonTerminalValue.start, $ruleListValue.list); }
    ;

terminalList returns [List<Terminal> list]
    :   'terminals:' { $list = new ArrayList(); }
        (lineSeparator maybeWhitespaces terminalValue=terminal
        (
            maybeWhitespaces ':' maybeWhitespaces optionValue=terminalOption
            { $terminalValue.term = new Terminal($terminalValue.term.getName(), $optionValue.option); }
        )?
        (
            maybeWhitespaces '{' maybeWhitespaces
            attributeFirstValue=attribute
            { $terminalValue.term.getAttributes().put($attributeFirstValue.attr.getName(), $attributeFirstValue.attr); }
            (
                maybeWhitespaces ',' maybeWhitespaces
                attributeValue=attribute
                { $terminalValue.term.getAttributes().put($attributeValue.attr.getName(), $attributeValue.attr); }
            )*
            maybeWhitespaces '}'
        )?
        { $list.add($terminalValue.term); }
        )+
    ;

terminal returns [Terminal term]
    :   name=LowName { $term = new Terminal($name.text); }
    ;

terminalOption returns [TerminalOption option]
    :   name=HighName { $option = TerminalOption.valueOf($name.text); }
    ;

nonTerminalList returns [List<NonTerminal> list]
    :   'non-terminals:' { $list = new ArrayList(); }
        (
            lineSeparator maybeWhitespaces nonTerminalValue=nonTerminal
            (
                maybeWhitespaces '{' maybeWhitespaces
                attributeFirstValue=attribute
                { $nonTerminal.nonterm.getAttributes().put($attributeFirstValue.attr.getName(), $attributeFirstValue.attr); }
                (
                    maybeWhitespaces ',' maybeWhitespaces
                    attributeValue=attribute
                    { $nonTerminal.nonterm.getAttributes().put($attributeValue.attr.getName(), $attributeValue.attr); }
                )*
                maybeWhitespaces '}'
            )?
            { $list.add($nonTerminal.nonterm); }
        )+
    ;

nonTerminal returns [NonTerminal nonterm]
    :   name=HighName { $nonterm = new NonTerminal($name.text); }
    ;

startNonTerminal returns [NonTerminal start]
    :   'start-non-terminal:' atLeastWhitespaces name=HighName { $start = new NonTerminal($name.text); }
    ;

ruleList [String grammarName] returns [List<Rule> list]
    :   'rules:' { $list = new ArrayList(); }
        (lineSeparator maybeWhitespaces ruleAttrsValue=ruleAttrs[$grammarName]
        { $list.add(new Rule($ruleAttrsValue.fromNonTerminal, $ruleAttrsValue.grammarObjectsList)); })+
    ;

ruleAttrs [String grammarName] returns [NonTerminal fromNonTerminal, List<GrammarObject> grammarObjectsList]
    :   { $grammarObjectsList = new ArrayList(); }
        nonTerminalValue=nonTerminal { $fromNonTerminal = $nonTerminalValue.nonterm; }
        atLeastWhitespaces '->'
        (
            atLeastWhitespaces
            (
                (
                    terminalValue=terminal
                    translatorArgsValue=translatorArgs[$grammarName]
                    { $grammarObjectsList.addAll($translatorArgsValue.translatorList); }
                    { $grammarObjectsList.add($terminalValue.term); }
                )
                |
                (
                    nonTerminalValue=nonTerminal
                    translatorArgsValue=translatorArgs[$grammarName]
                    { $grammarObjectsList.addAll($translatorArgsValue.translatorList); }
                    { $grammarObjectsList.add($nonTerminalValue.nonterm); }
                )
                |
                (translatorReturnValue=translatorReturn[$grammarName]
                { $grammarObjectsList.addAll($translatorReturnValue.translatorList); })
            )
        )+
    ;

attribute returns [Attribute attr]
    :   name=LowName maybeWhitespaces
        { $attr = new Attribute($name.text); }
    ;

translatorReturn [String grammarName] returns [List<Translator> translatorList]
    :   { $translatorList = new ArrayList(); }
        (
            '{'
            (
                maybeWhitespaces
                argsValue=Args
                maybeWhitespaces
                '->'
                maybeWhitespaces
                (
                    (
                        codeValue=Code
                        maybeWhitespaces
                        { $translatorList.add(new Translator($grammarName, new Code($argsValue.text, $codeValue.text), TranslatorType.RETURN)); }
                    )
                    |
                    (
                        '$'
                        name=ClassName
                        maybeWhitespaces
                        { $translatorList.add(new Translator($grammarName, $name.text, new Code($argsValue.text, ""), TranslatorType.RETURN)); }
                    )
                )
            )+
            '}'
        )?
    ;
    catch [CreateTranslatorWithCurrentCodeException e] {
        e.printStackTrace();
    }

translatorArgs [String grammarName] returns [List<Translator> translatorList]
    :   { $translatorList = new ArrayList(); }
        (
            '['
            (
                maybeWhitespaces
                argsValue=Args
                maybeWhitespaces
                '->'
                maybeWhitespaces
                (
                    (
                        codeValue=Code
                        maybeWhitespaces
                        { $translatorList.add(new Translator($grammarName, new Code($argsValue.text, $codeValue.text), TranslatorType.ARGS)); }
                    )
                    |
                    (
                        '$'
                        name=ClassName
                        maybeWhitespaces
                        { $translatorList.add(new Translator($grammarName, $name.text, new Code($argsValue.text, ""), TranslatorType.ARGS)); }
                    )
                )
            )+
            ']'
        )?
    ;
    catch [CreateTranslatorWithCurrentCodeException e] {
        e.printStackTrace();
    }

lineSeparator
    : ('\r')? '\n'
    ;

atLeastWhitespaces
    :   (lineSeparator | Whitespace)+
    ;

maybeWhitespaces
    :   (lineSeparator | Whitespace)*
    ;

LowName
    :   (
            ('a'..'z')
            |
            '('
            |
            ')'
            |
            ('0'..'9')
            |
            '+'
            |
            '-'
            |
            '*'
            |
            '/'
            |
            '<<'
            |
            '>>'
        )+
    ;

Whitespace
    :   (' ' | '\t' | '\r' | '\n')
    ;

HighName
    :   ('A'..'Z')+
    ;

ClassName
    :   ('A'..'Z')
        (
            ('a'..'z')
            |
            ('A'..'Z')
            |
            '_'
            |
            ('0'..'9')
        )*
    ;

Code
    :   ((LowName | HighName | '='+ | '+'+ | '*'+ | '/'+ | ('0'..'9')+ | ';'+ | '.'+ | '('+ | ')'+ | '%'+ | '['+ | ']'+ | '"'+ | '\''+ | '!'+ |
        '&'+ | '|'+ | '^'+)
        Whitespace*)+
        ';'
    ;

Args
    :
        '('
        Whitespace*
        '['
        ('0'..'9')+
        ']'

        (
            Whitespace*
            ','
            Whitespace*
            '['
            ('0'..'9')+
            ']'
        )*

        Whitespace*
        ')'
    ;