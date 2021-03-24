package grammar.objects.nonterminals.translators;

import exceptions.grammar.CreateTranslatorWithCurrentCodeException;

public class CopyTranslator extends Translator {
    private static final String code =
            "for (String attributeName : args.get(0).keySet()) {" + System.lineSeparator() +
            "   if (args.get(0).get(attributeName).getValue() != null && args.get(1).get(attributeName).getValue() == null) {" + System.lineSeparator() +
            "       args.get(1).get(attributeName).setValue(args.get(0).get(attributeName).getValue());" + System.lineSeparator() +
            "   }" + System.lineSeparator() +
            "}";

    public CopyTranslator(String grammarName) throws CreateTranslatorWithCurrentCodeException {
        super(grammarName, "CopyTranslator", new Code("", code), TranslatorType.COPY_TRANSLATOR);
    }
}
