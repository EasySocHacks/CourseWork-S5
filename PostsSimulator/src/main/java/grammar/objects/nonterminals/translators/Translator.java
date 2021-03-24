package grammar.objects.nonterminals.translators;

import exceptions.grammar.CreateTranslatorWithCurrentCodeException;
import grammar.objects.nonterminals.NonTerminal;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

public class Translator extends NonTerminal {
    private Class<?> codeClass;
    private List<Argument> args;
    private TranslatorType translatorType;

    public static class Argument {
        private int rulePosition;

        public Argument(int rulePosition) {
            this.rulePosition = rulePosition;
        }

        public int getRulePosition() {
            return rulePosition;
        }
    }

    public Argument parseArgument(String argString) {
        argString = argString.strip();

        int rulePosition = Integer.parseInt(argString.substring(1, argString.length() - 1));

        return new Argument(rulePosition);
    }

    public List<Argument> parseArgumentList(String argsString) {
        if (argsString.length() <= 2) {
            return new ArrayList<>();
        }

        argsString = argsString.substring(1, argsString.length() - 1);

        List<Argument> argumentList = new ArrayList<>();

        for (String curArgs : argsString.split(",")) {
            argumentList.add(parseArgument(curArgs));
        }

        return argumentList;
    }

    public static class Code {
        private String argsString;
        private String codeString;

        public Code(String argsString, String codeString) {
            this.argsString = argsString;
            this.codeString = codeString;
        }

        public String getArgsString() {
            return argsString;
        }

        public String getCodeString() {
            return codeString;
        }
    }

    public enum TranslatorType {
        ARGS,
        RETURN,
        COPY_TRANSLATOR,
        RETURN_TRANSLATOR,
        RIGHT_BRANCHING_ARGS_TRANSLATOR;
    }

    private final static String classTemplateFormat =
            "import grammar.objects.nonterminals.NonTerminal;" + System.lineSeparator() +
            "import grammar.objects.attributes.Attribute;" + System.lineSeparator() +
            "import utils.Table;" + System.lineSeparator() +
            "import java.util.Map;" + System.lineSeparator() +
            "import java.util.List;" + System.lineSeparator() +
            System.lineSeparator() +
            "public class %s { " + System.lineSeparator() +
            "   public static void run(List<Map<String, Attribute>> args) { " + System.lineSeparator() +
            "       %s " + System.lineSeparator() +
            "   }" + System.lineSeparator() +
            "}";

    public static int translatorCount = 0;

    public Translator(String name, Class<?> codeClass, List<Argument> args, TranslatorType translatorType) {
        super(name);
        this.codeClass = codeClass;
        this.args = args;
        this.translatorType = translatorType;
    }

    public Translator(String grammarName, String name, Code code, TranslatorType translatorType)
            throws CreateTranslatorWithCurrentCodeException {
        super(name);

        this.args = parseArgumentList(code.argsString);
        this.translatorType = translatorType;

        try {
            Path sourcePath = Paths.get("src", "main", "resources", grammarName, "java", name + ".java");

            if (!code.codeString.equals("")) {
                Path sourceFilePath = sourcePath.getParent();
                sourceFilePath.toFile().mkdirs();

                Files.write(sourcePath, String.format(classTemplateFormat, name, code.codeString).getBytes());

                JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
                compiler.run(null, null, null, sourcePath.toFile().getAbsolutePath());

                Path classPath = sourcePath.getParent().resolve(name + ".class");
                Path newClassPath = Paths.get("src", "main", "resources", grammarName, "class", name + ".class");

                Path classFilePath = newClassPath.getParent();
                classFilePath.toFile().mkdirs();

                Files.move(classPath, newClassPath, StandardCopyOption.REPLACE_EXISTING);

                URL classURL = newClassPath.getParent().toFile().toURI().toURL();
                URLClassLoader classLoader = URLClassLoader.newInstance(new URL[]{classURL});

                codeClass = Class.forName(name, true, classLoader);
                codeClass.newInstance();
            } else {
                Path newClassPath = Paths.get("src", "main", "resources", grammarName, "class", name + ".class");

                URL classURL = newClassPath.getParent().toFile().toURI().toURL();
                URLClassLoader classLoader = URLClassLoader.newInstance(new URL[]{classURL});

                codeClass = Class.forName(name, true, classLoader);
                codeClass.newInstance();
            }
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | IOException e) {
            throw new CreateTranslatorWithCurrentCodeException(
                    String.format("Couldn't create translator's class with name '%s' and code %s%s -> %s%s",
                            name, System.lineSeparator(), code.argsString, code.codeString, System.lineSeparator()),
                    e);
        }
    }

    public Translator(String grammarName, Code code, TranslatorType translatorType) throws CreateTranslatorWithCurrentCodeException {
        this(grammarName, "Translator" + (Translator.translatorCount++), code, translatorType);
    }

    public Class<?> getCodeClass() {
        return codeClass;
    }

    public List<Argument> getArgs() {
        return args;
    }

    public void setArgs(List<Argument> args) {
        this.args = args;
    }

    public void setArgs(String argsString) {
        this.args = parseArgumentList(argsString);
    }

    public TranslatorType getTranslatorType() {
        return translatorType;
    }

    @Override
    public String toString() {
        return "$" + super.getName();
    }
}
