package grammar.objects.nonterminals.translators;

import exceptions.grammar.CreateTranslatorWithCurrentCodeException;

public class RightBranchingArgsTranslator extends Translator {
    private static final String code =
            "for (String attributeName : args.get(0).keySet()) {" + System.lineSeparator() +
                    "   if (args.get(0).get(attributeName).getValue() != null && args.get(1).get(attributeName).getValue() == null) {" + System.lineSeparator() +
                    "       args.get(1).get(attributeName).setValue(args.get(0).get(attributeName).getValue());" + System.lineSeparator() +
                    "   }" + System.lineSeparator() +
                    "}";

    public RightBranchingArgsTranslator(String grammarName) throws CreateTranslatorWithCurrentCodeException {
        super(grammarName, "CopyTranslator", new Code("", code), TranslatorType.RIGHT_BRANCHING_ARGS_TRANSLATOR);
    }
}
