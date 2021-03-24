package grammar.objects.terminals;

import grammar.objects.GrammarObject;
import grammar.objects.attributes.Attribute;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@SuppressWarnings("rawtypes")
public class Terminal<T> implements GrammarObject {
    private Map<String, Attribute> attributes = new HashMap<>();
    
    public static final Terminal EPSILON = new Terminal("eps");
    public static final Terminal EOS = new Terminal("$");

    public enum TerminalOption {
        WHITESPACELESS,
        VARIABLE(true, "[a-zA-Z]"),
        NUMBER(true, "[0-9]+");

        private boolean containsExtraValue = false;
        private String regex = "";

        TerminalOption() {}

        TerminalOption(boolean containsExtraValue) {
            this.containsExtraValue = containsExtraValue;
        }

        TerminalOption(boolean containsExtraValue, String regex) {
            this.containsExtraValue = containsExtraValue;
            this.regex = regex;
        }

        public boolean isContainsExtraValue() {
            return containsExtraValue;
        }

        public String getRegex() {
            return regex;
        }
    }

    private final String name;
    private TerminalOption terminalOption = null;

    private T extraValue;

    public Terminal(String name) {
        this.name = name;
    }

    public Terminal(String name, TerminalOption terminalOption) {
        this.name = name;
        this.terminalOption = terminalOption;
    }

    public Terminal(String name, TerminalOption terminalOption, T extraValue) {
        this.name = name;
        this.terminalOption = terminalOption;
        this.extraValue = extraValue;
    }

    public String getName() {
        return name;
    }

    public TerminalOption getTerminalOption() {
        return terminalOption;
    }

    public T getExtraValue() {
        return extraValue;
    }

    public void setTerminalOption(TerminalOption terminalOption) {
        this.terminalOption = terminalOption;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Terminal<?> terminal = (Terminal<?>) o;
        return Objects.equals(name, terminal.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, terminalOption, extraValue);
    }

    @Override
    public String toString() {
        if (terminalOption != null && terminalOption.containsExtraValue) {
            if (!attributes.isEmpty()) {
                return String.format("[%s {%s} : %s]", getName(), attributes, extraValue);
            } else {
                return String.format("[%s : %s]", getName(), extraValue);
            }
        }

        if (attributes.isEmpty()) {
            return getName();
        } else {
            return String.format("%s %s", getName(), attributes);
        }
    }

    @Override
    public Map<String, Attribute> getAttributes() {
        return attributes;
    }

    @Override
    public void setAttributes(Map<String, Attribute> attributes) {
        this.attributes = new HashMap<>();

        try {
            for (String attributeName : attributes.keySet()) {
                this.attributes.put(attributeName, (Attribute) attributes.get(attributeName).clone());
            }
        } catch (CloneNotSupportedException e){
            e.printStackTrace();
        }
    }
}
