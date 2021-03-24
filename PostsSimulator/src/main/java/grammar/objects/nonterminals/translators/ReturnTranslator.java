package grammar.objects.nonterminals.translators;

import exceptions.grammar.CreateTranslatorWithCurrentCodeException;

public class ReturnTranslator extends Translator {
    private static final String code =
            "for (String attributeName : args.get(0).keySet())" + System.lineSeparator() +
            "   args.get(1).get(attributeName).setValue(args.get(0).get(attributeName).getValue());";

    public ReturnTranslator(String grammarName) throws CreateTranslatorWithCurrentCodeException {
        super(grammarName, "ReturnTranslator", new Code("", code), TranslatorType.RETURN_TRANSLATOR);
    }
}
