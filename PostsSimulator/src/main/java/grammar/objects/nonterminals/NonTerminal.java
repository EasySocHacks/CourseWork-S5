package grammar.objects.nonterminals;

import grammar.objects.GrammarObject;
import grammar.objects.attributes.Attribute;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class NonTerminal implements GrammarObject {
    private final String name;
    private HashMap<String, Attribute> attributes = new HashMap<>();

    public NonTerminal(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NonTerminal that = (NonTerminal) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
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
